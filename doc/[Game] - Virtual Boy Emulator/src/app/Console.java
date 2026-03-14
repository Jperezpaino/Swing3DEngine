package app;

// Java imports
import java.awt.*;
import java.awt.image.*;
import java.util.*;

// Project imports
import app.*;
import config.*;
import util.*;
import vue.*;

// Video display and multi-threaded frame timer
class Console extends Container {

    // Instance fields
    private boolean       active;     // The timer is active
    private BufferedImage frame;      // Display imagejppaino 
    private boolean       isMaster;   // Controlling timer operations
    private Console       peer;       // Communication peer
    private MainWindow    parent;     // Parent application window
    private int[]         pixels;     // Pre-allocated pixel buffer
    private boolean       processing; // Currently processing a frame
    private boolean       reenable;   // Timer should be re-enabled
    private TimerTask     task;       // Timer event handler
    private Timer         timer;      // Frame scheduler
    private boolean       toStop;     // The timer is scheduled to stop



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    Console(MainWindow parent) {
        super();

        // Configure instance fields
        frame       = new BufferedImage(384, 224, BufferedImage.TYPE_INT_RGB);
        isMaster    = true;
        this.parent = parent;
        pixels      = new int[384 * 224];
        timer       = new Timer(true);

        // Configure component
        setFocusable(true);
        setPreferredSize(new Dimension(384, 224));
        Util.onMouse(this, e->requestFocus(), null);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Draw the image inside the component
    public void paint(Graphics g) {
        int width  = getWidth();
        int height = getHeight();
        int scale  = Math.max(1, Math.min(width / 384, height / 224));
        int wide   = 384 * scale;
        int tall   = 224 * scale;
        int left   = Math.max(0, width  - wide >> 1);
        int top    = Math.max(0, height - tall >> 1);
        super.paint(g);
        g.drawImage(frame, left, top, wide, tall, null);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Enable frame processing, optionally with a communication peer
    synchronized void activate(Console peer) {

        // Error checking
        if (isActive())
            return;

        // Configure peers
        isMaster  = true;
        this.peer = peer;
        if (peer != null) {
            peer.isMaster = false;
            peer.peer     = this;
        }

        // Enable frame processing
        active = true;
        timer.schedule(task = new TimerTask() {
            public void run() { onTimer(); }
        }, 0, 20);
    }

    //  Disable frame processing, waiting for it to finish
    void deactivate() {
        deactivate(true);
    }

    // Disable frame processing, optionally waiting for it to finish
    void deactivate(boolean wait) {

        // Error checking
        if (!isActive())
            return;

        // Schedule the timer to stop
        if (isMaster)
            synchronized (this)
                { toStop = true; }
        else peer.deactivate(false);

        // Wait for frame processing to stop
        if (wait)
            waitFor();
    }

    // Determine whether frame processing is enabled
    boolean isActive() {
        if (isMaster)
            synchronized (this)
                { return active; }
        else return peer.isActive();
    }

    // Retrieve the current frame image from the emulation context
    void refresh() {
        Config config = parent.getConfig();
        parent.getVUE().getFrame(pixels,
            config.getLeftColor(), config.getRightColor());
        frame.setRGB(0, 0, 384, 224, pixels, 0, 384);
        repaint();
    }

    // Wait for frame processing to finish
    void waitFor() {
        if (isMaster)
            while (isActive())
                synchronized (frame) { }
        else peer.waitFor();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Process one frame from timer thread
    private void onTimer() {

        // Pre-processing
        synchronized (this) {

            // Error checking
            if (!(active || isMaster && peer.isActive()))
                return;

            // Frame processing is already underway--took too long
            if (processing) {
                task.cancel();
                timer.purge();
                reenable = true;
                return;
            }

            // Begin frame processing
            processing = true;
            reenable   = false;
        }

        // Process a frame
        synchronized (frame) {
            VUE a    = parent.getVUE();
            VUE b    = peer == null ? null : peer.parent.getVUE();
            int aBrk = 0;
            int bBrk = 0;

            // Emulate until a break occurs
            synchronized (a) {
                if (peer == null)
                    a.emulate(400000);
                else synchronized (b) {
                    int[] wut = a.emulate(400000, b);
                    bBrk = b.getBreakCode();
                }
                aBrk = a.getBreakCode();
            }

            // Break processing
            if (parent.onBreak(aBrk) ||
                peer != null && peer.parent.onBreak(bBrk))
                synchronized (this) { toStop = true; }

            // Post-processing
            synchronized (this) {

                // Timer operations are scheduled to stop
                if (toStop) {
                    active   = false;
                    reenable = false;
                    toStop   = false;
                    task.cancel();
                    timer.purge();
                    if (peer != null)
                        peer.isMaster = true;
                }

                // Complete frame processing
                processing = false;
                if (reenable)
                    activate(peer);
            }

        } // frame

    }

}
