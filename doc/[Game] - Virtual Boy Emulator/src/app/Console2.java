//package app;
//
//// Java imports
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.*;
//import java.util.concurrent.locks.*;
//import javax.sound.sampled.*;
//
//// Project imports
//import app.*;
//import config.*;
//import util.*;
//import vue.*;
//
//// Human I/O interface and frame timer
//class Console extends Container {
//
//    // Instance fields
//    private Thread        frames;    // Frame processing thread
//    private ReentrantLock lock;      // Frame processing lock
//    private boolean       master;    // In control of frame processing
//    private MainWindow    parent;    // Containing application window
//    private Console       peer;      // Communication peer
//    private boolean       pending;   // The initial frame has been processed
//    private long          reference; // Reference time in nanoseconds
//    private boolean       stop;      // Frame processing will end
//    private boolean       terminate; // End the frame processing thread
//
//    // Output fields
//    private SourceDataLine audio;   // Audio output
//    private int[]          pixels;  // Video frame buffer
//    private byte[]         samples; // Audio stream buffer
//    private BufferedImage  video;   // Video output
//    private FloatControl   volume;  // Audio volume gain control
//
//
//
//    ///////////////////////////////////////////////////////////////////////////
//    //                             Constructors                              //
//    ///////////////////////////////////////////////////////////////////////////
//
//    // Package constructor
//    Console(MainWindow parent) {
//        super();
//
//        // Configure instance fields
//        frames      = new Thread(()->framesMain());
//        lock        = new ReentrantLock();
//        master      = true;
//        this.parent = parent;
//        pixels      = new int[384 * 224];
//        video       = new BufferedImage(384, 224, BufferedImage.TYPE_INT_RGB);
//
//        // Configure component
//        setFocusable(true);
//        setPreferredSize(new Dimension(384, 224));
//        Util.onKey(this, e->onKey(e), e->onKey(e));
//        Util.onMouse(this, e->requestFocus(), null);
//        frames.start();
//        reset();
//    }
//
//
//
//    ///////////////////////////////////////////////////////////////////////////
//    //                            Public Methods                             //
//    ///////////////////////////////////////////////////////////////////////////
//
//    // Draw the video output to the component
//    public void paint(Graphics g) {
//        int width  = getWidth();
//        int height = getHeight();
//        int scale  = Math.max(1, Math.min(width / 384, height / 224));
//        int wide   = scale * 384;
//        int tall   = scale * 224;
//        int left   = Math.max(0, width  - wide >> 1);
//        int top    = Math.max(0, height - tall >> 1);
//        super.paint(g);
//        g.drawImage(video, left, top, wide, tall, null);
//    }
//
//
//
//    ///////////////////////////////////////////////////////////////////////////
//    //                            Package Methods                            //
//    ///////////////////////////////////////////////////////////////////////////
//
//    // Release any resources used by this object
//    void dispose() {
//
//        // Disconnect from a communication peer
//        setPeer(null);
//
//        // Signal to the frame processing thread to terminate
//        pause();
//        synchronized (this)
//            { terminate = true; }
//        try {
//            synchronized (frames)
//                { frames.notifyAll(); }
//            frames.join();
//        } catch (Exception e) { }
//
//        // Release audio resources
//        closeAudio();
//    }
//
//    // Retrieve the parent window
//    MainWindow getWindow() {
//        return parent;
//    }
//
//    // Retrieve the communication peer
//    Console getPeer() {
//        return this.peer;
//    }
//
//    // Retrieve the parent window of the communication peer
//    MainWindow getPeerWindow() {
//        return peer == null ? null : peer.parent;
//    }
//
//    // Determine whether frame processing is active
//    boolean isRunning() {
//        return master ? lock.isLocked() : peer.isRunning();
//    }
//
//    // Pause the simulation and wait for frame processing to finish
//    void pause() {
//        pause(true);
//    }
//
//    // Pause the simulation, optionally waiting for frame processing to finish
//    void pause(boolean wait) {
//
//        // Have the peer handle the command
//        if (!master) {
//            peer.pause(wait);
//            return;
//        }
//
//        // Frame processing is not active
//        if (!isRunning())
//            return;
//
//        // Schedule frame processing to stop
//        synchronized (this)
//            { stop = true; }
//        if (wait)
//            waitFor();
//    }
//
//    // Process simulations for a given number of CPU cycles
//    boolean process(int cycles, boolean step, boolean ignoreFrame) {
//        VUE aVUE = parent.getVUE();
//        VUE bVUE = peer == null ? null : peer.parent.getVUE();
//        int aBrk = 0;
//        int bBrk = 0;
//
//        // State management
//        synchronized (this)
//            { stop = false; }
//
//        // Disable breakpoints for the first instruction
//        if (step) {
//            synchronized (aVUE)
//                { aVUE.setBreakMask(Breakpoint.FRAME); }
//            if (bVUE != null) synchronized (bVUE)
//                { bVUE.setBreakMask(Breakpoint.FRAME); }
//        }
//
//        // Process the simulations up to two times
//        for (int left = step ? 1 : cycles; left > 0;) {
//
//            // Exit conditions
//            synchronized (this) {
//                if (stop || !ignoreFrame && aBrk == MainWindow.BRK_FRAME)
//                    break;
//            }
//
//            // Emulate both simulations
//            synchronized (aVUE) {
//                if (peer == null)
//                    left -= left - aVUE.emulate(left);
//                else synchronized (bVUE) {
//                    left -= left - aVUE.emulate(left, bVUE)[0];
//                    bBrk = bVUE.getBreakCode();
//                }
//                aBrk = aVUE.getBreakCode();
//            }
//
//            // Check for breakpoints
//            if (peer != null)
//                peer.parent.onBreak(bBrk);
//            if (parent.onBreak(aBrk))
//                synchronized (this)
//                    { stop = true; }
//
//            // Update cycles remaining
//            if (!step)
//                continue;
//            left = cycles - 1;
//            step = false;
//
//            // Re-enable breakpoints
//            synchronized (aVUE)
//                { aVUE.setBreakMask(Breakpoint.ALWAYS); }
//            if (bVUE != null) synchronized (bVUE)
//                { bVUE.setBreakMask(Breakpoint.ALWAYS); }
//        }
//
//        return aBrk == MainWindow.BRK_FRAME;
//    }
//
//    // Update video output with the current frame image
//    void refresh() {
//        Config config = parent.getConfig();
//        VUE    vue    = parent.getVUE();
//        synchronized (vue) { vue.getFrame(pixels,
//            config.getLeftColor(), config.getRightColor()); }
//        video.setRGB(0, 0, 384, 224, pixels, 0, 384);
//        repaint();
//    }
//
//    // Reset presentation output and simulation state
//    void reset() {
//        VUE vue = parent.getVUE();
//
//        // Stop frame processing
//        boolean restart = isRunning();
//        pause();
//
//        // Reset audio
//        if (audio != null) {
//            audio.stop();
//            audio.flush();
//        }
//
//        // Reset video
//        for (int x = 0; x < pixels.length; x++)
//            pixels[x] = 0;
//        video.setRGB(0, 0, 384, 224, pixels, 0, 384);
//        repaint();
//
//        // Reset frame processing
//        pending = false;
//
//        // Reset simulation state
//        synchronized (vue)
//            { vue.reset(); }
//
//        // Restart frame processing
//        if (restart)
//            run();
//    }
//
//    // Start the simulation
//    void run() {
//
//        // Frame processing is already active
//        if (isRunning())
//            return;
//
//        // Configure communications
//        synchronized (Console.class) {
//            if (!master)
//                return;
//            if (peer != null)
//                peer.master = false;
//        }
//
//        // Begin frame processing
//        try {
//            synchronized (frames)
//                { frames.notifyAll(); }
//        } catch (Exception e) { }
//
//    }
//
//    // Specify a new communication peer
//    void setPeer(Console peer) {
//
//        // Error checking
//        synchronized (Console.class) {
//            if (peer == this.peer || peer != null && peer.peer != null)
//                return;
//        }
//
//        // Stop frame processing
//        boolean restart = isRunning();
//        Console prev    = this.peer;
//        pause();
//
//        synchronized (Console.class) {
//
//            // Disconnect from an existing peer
//            if (this.peer != null) {
//                prev.peer   = null;
//                prev.master = true;
//            }
//
//            // Associate with a new peer
//            this.peer = peer;
//            if (peer != null)
//                peer.peer = this;
//        }
//
//        // Restart frame processing
//        if (!restart)
//            return;
//        run();
//        if (prev != null)
//            prev.run();
//    }
//
//    // Wait for frame processing to end
//    void waitFor() {
//
//        // Have the peer handle the command
//        if (!master) {
//            peer.waitFor();
//            return;
//        }
//
//        // Frame processing will not stop
//        synchronized (this) {
//            if (!stop)
//                return;
//        }
//
//        // Frame processing is not active
//        if (!isRunning())
//            return;
//
//        // Wait for frame processing to finish
//        lock.lock();
//        try { lock.unlock(); } catch (Exception e) { }
//    }
//
//
//
//    ///////////////////////////////////////////////////////////////////////////
//    //                            Event Handlers                             //
//    ///////////////////////////////////////////////////////////////////////////
//
//    // Console key
//    private void onKey(KeyEvent e) {
//
//        // Check the controller key mask
//        int mask = parent.getConfig().getKeyMask(new Input(e));
//        if (mask == 0)
//            return;
//
//        // Update the simulation's keys
//        VUE vue = parent.getVUE();
//        synchronized (vue) {
//            int keys = vue.getKeys();
//            if (e.getID() == KeyEvent.KEY_PRESSED) {
//                keys |= mask;
//                switch (mask) {
//                    case VUE.LDOWN : keys &= ~VUE.LUP   ; break;
//                    case VUE.LLEFT : keys &= ~VUE.LRIGHT; break;
//                    case VUE.LRIGHT: keys &= ~VUE.LLEFT ; break;
//                    case VUE.LUP   : keys &= ~VUE.LDOWN ; break;
//                    case VUE.RDOWN : keys &= ~VUE.RUP   ; break;
//                    case VUE.RLEFT : keys &= ~VUE.RRIGHT; break;
//                    case VUE.RRIGHT: keys &= ~VUE.RLEFT ; break;
//                    case VUE.RUP   : keys &= ~VUE.RDOWN ; break;
//                }
//            } else keys &= ~mask;
//            vue.setKeys(keys);
//        }
//
//    }
//
//
//
//    ///////////////////////////////////////////////////////////////////////////
//    //                            Private Methods                            //
//    ///////////////////////////////////////////////////////////////////////////
//
//    // Stop audio output
//    private void closeAudio() {
//        if (audio == null)
//            return;
//
//        // Stop the audio
//        audio.stop();
//        audio.flush();
//        try { audio.close(); } catch (Exception e) { }
//
//        // Configure audio fields
//        audio  = null;
//        volume = null;
//    }
//
//    // Configure the audio output stream
//    private void configAudio() {
//        Config config = parent.getConfig();
//        VUE    vue    = parent.getVUE();
//
//        // Disable audio processing
//        if (!config.isAudioEnabled()) {
//            closeAudio();
//            synchronized (vue)
//                { vue.setAudioEnabled(false); }
//            return;
//        }
//
//        // Retrieve the sampling rate
//        int rate = (config.getAudioRate() + 25) / 50 * 50;
//        int prevRate;
//        synchronized (vue)
//            { prevRate = vue.getAudioRate(); }
//
//        // The audio stream needs to be restarted
//        if (audio != null && rate != prevRate)
//            closeAudio();
//
//        // Open a new audio stream
//        if (audio == null) {
//
//            // Attempt to open the stream
//            AudioFormat format = new AudioFormat(rate, 16, 2, true, false);
//            DataLine.Info info =
//                new DataLine.Info(SourceDataLine.class, format);
//            try {
//                audio = (SourceDataLine) AudioSystem.getLine(info);
//                audio.open(format, rate / 50 << 3);
//            }
//
//            // Failed to open audio stream
//            catch (Exception e) {
//                audio = null;
//                return;
//            }
//
//            // Retrieve the volume control
//            try {
//                volume = (FloatControl)
//                    audio.getControl(FloatControl.Type.MASTER_GAIN);
//            } catch (Exception e) { volume = null; }
//
//            // Configure the sample buffer and emulation context
//            samples = new byte[rate / 50 << 2];
//            synchronized (vue) {
//                vue.setAudioBuffer (samples);
//                vue.setAudioEnabled(true   );
//                vue.setAudioRate   (rate   );
//            }
//
//        }
//
//        // Update emulation settings
//        synchronized (vue) {
//            vue.setAudioAnalog(config.isAudioAnalog ());
//            vue.setAudioFilter(config.getAudioFilter());
//        }
//
//        // Update volume
//        if (volume != null) {
//            float volume = config.getAudioVolume();
//            volume = volume == 0 ? Float.NEGATIVE_INFINITY :
//                (float) Math.log10(volume * 20);
//            this.volume.setValue(volume);
//        }
//
//    }
//
//    // Wait for the previous frame to finish presenting
//    private void finish() {
//        VUE vue = parent.getVUE();
//        int rate;
//        synchronized (vue)
//            { rate = vue.getAudioRate(); }
//
//        // Calculate how long to wait before the next frame is presented
//        long toWait = audio == null ?
//            20 - (System.currentTimeMillis() - reference) :
//            (samples.length - audio.available()) * 1000 / (rate << 2);
//        ;
//
//        // The frame has not finished being presented
//        if (toWait > 0) try {
//            Thread.sleep(toWait);
//        } catch (Exception e) { }
//
//        // Track realtime
//        reference = System.currentTimeMillis();
//    }
//
//    // Frame timer processor -- Executes in its own thread
//    private void framesMain() {
//
//        // Loop until dispose() is called
//        for (;;) {
//
//            // Check for terminate status
//            synchronized (this) {
//                if (terminate)
//                    return;
//            }
//
//            // Pause the thread
//            try { lock.unlock(); } catch (Exception e) { }
//            try {
//                synchronized (frames)
//                    { frames.wait(); }
//            } catch (Exception e) { }
//
//            // Check for terminate status again
//            synchronized (this) {
//                if (terminate)
//                    return;
//            }
//
//            // Prepare for frame processing
//            lock.lock();
//            parent.onConsoleRun();
//            reference = System.currentTimeMillis();
//            if (peer != null) {
//                peer.parent.onConsoleRun();
//                peer.reference = reference;
//            }
//            synchronized (this)
//                { stop = false; }
//
//            // Repeat until frame processing is stopped
//            synchronized (video) {
//                for (boolean step = true; ; step = false) {
//
//                    // Exit condition
//                    synchronized (this) {
//                        if (stop)
//                            break;
//                    }
//
//                    // Configure audio streams
//                    configAudio();
//                    if (peer != null)
//                        peer.configAudio();
//
//                    // Process the current frame
//                    if (!process(400000, step, false))
//                        break;
//
//                    // Present the first frame
//                    if (!pending) {
//                        present();
//                        pending = true;
//                        continue;
//                    }
//
//                    // Wait and present the current frame
//                    finish();
//                    present();
//                } // Unconditional
//            }
//
//            // End of frame processing
//            if (audio != null)
//                audio.stop();
//            if (peer != null) {
//                if (peer.audio != null)
//                    peer.audio.stop();
//                peer.master = true;
//                peer.parent.onConsolePause();
//            }
//            parent.onConsolePause();
//        } // Unconditional
//
//    }
//
//    // Present video and audio output to the user
//    private void present() {
//        VUE vue = parent.getVUE();
//
//        // Output audio
//        if (audio != null) {
//            audio.start();
//            int pos;
//            synchronized (vue)
//                { pos = vue.getAudioPosition(); }
//            audio.write(samples, pos, samples.length - pos);
//            if (pos != 0)
//                audio.write(samples, 0, pos);
//        }
//
//        // Output video
//        refresh();
//
//        // Present the output on the peer
//        if (master && peer != null)
//            peer.present();
//    }
//
//}
