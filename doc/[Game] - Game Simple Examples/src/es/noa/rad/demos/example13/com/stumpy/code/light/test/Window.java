package es.noa.rad.demos.example13.com.stumpy.code.light.test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferStrategy bufferstrat = null;
	private Canvas render;
	private Graphics2D g2d;
	
	public boolean rightMouseClick = false;
	public boolean leftMouseClick = false;
	
	public int mouseX;
	public int mouseY;
	
	public int keyPressed;
	public int keyReleased;
	
	public Window( int width, int height, String title, boolean fullScreen){
		super();
		setTitle(title);
		setIgnoreRepaint(true);
		setResizable(false);
		render = new Canvas();
		render.setIgnoreRepaint(true);
		render.setFocusable(false);
		if(fullScreen)
			setUndecorated(true);
		int nHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int nWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        nHeight /= 2;
        nWidth /= 2;
        
        setBounds(nWidth-(width/2), nHeight-(height/2), width, height);
		render.setBounds(nWidth-(width/2), nHeight-(height/2), width, height);
		
		add(render);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent arg0) {
				keyPressed = arg0.getKeyCode();
			}
			public void keyReleased(KeyEvent arg0) {
				keyPressed = 0;
				keyReleased = arg0.getKeyCode();
			}
			public void keyTyped(KeyEvent arg0) {
				
			}
			
		});
		render.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent arg0) {
		
			}
			public void mouseMoved(MouseEvent arg0) {
				mouseX =arg0.getX();
				mouseY = arg0.getY();
			}
			
		});
		render.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3)
					rightMouseClick = true;
				if(e.getButton() == MouseEvent.BUTTON1)
					leftMouseClick = true;
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				
				System.out.println("Mouse Down at: " + e.getX() + ", " + e.getY());
			}
			public void mouseReleased(MouseEvent e) {	

				System.out.println("Mouse Up at: " + e.getX() + ", " + e.getY());
			}
			
		});
		
		render.createBufferStrategy(3);
		bufferstrat = render.getBufferStrategy();
		g2d = (Graphics2D) bufferstrat.getDrawGraphics();
		if(fullScreen)
			fullScreen();
	}
	
	public void kill()
	{
		render = null;
	}
	
	public Window( int width, int height, String title){
		this(width,height,title,false);
	}
	
	public void resize2(int width, int height)
	{
		int nHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int nWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        nHeight /= 2;
        nWidth /= 2;
        
        setBounds(nWidth-(width/2), nHeight-(height/2), width, height);
		render.setBounds(nWidth-(width/2), nHeight-(height/2), width, height);
		
		pack();
		render.createBufferStrategy(2);
		bufferstrat = render.getBufferStrategy();
		g2d = (Graphics2D) bufferstrat.getDrawGraphics();
	}
	
	private void fullScreen()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setBounds(0, 0, screenSize.width, screenSize.height);
		render.setBounds(0, 0, screenSize.width, screenSize.height);
		
		pack();
		render.createBufferStrategy(2);
		bufferstrat = render.getBufferStrategy();
		g2d = (Graphics2D) bufferstrat.getDrawGraphics();
	}
	
	public Canvas getRenderer()
	{
		return render;
	}
	
	public Graphics2D begin()
	{
		g2d = (Graphics2D) bufferstrat.getDrawGraphics();
		g2d.setClip(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		return g2d;
	}
	
	public void end()
	{
		do{
			do{
	                g2d.dispose();
	         }while(bufferstrat.contentsRestored());
	          bufferstrat.show();
		}while(bufferstrat.contentsLost());
	}
}
