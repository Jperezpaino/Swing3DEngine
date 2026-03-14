package es.noa.rad.demos.example13.com.stumpy.code.light.test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import es.noa.rad.demos.example13.com.stumpy.code.light.cullers.Shape;
import es.noa.rad.demos.example13.com.stumpy.code.light.main.BufferUtils;
import es.noa.rad.demos.example13.com.stumpy.code.light.main.Vector2;

public class Test {
	private Window window;
	VolatileImage vImage; 
	Graphics2D v2d;
	public static void main(String[] args)
	{
		Test test = new Test();
		test.start();
	}
	
	public void start()
	{
		window = new Window(500,500,"Fak", false);
		vImage = BufferUtils.getCompatibleVImage(2048,2048);
		v2d = (Graphics2D) vImage.getGraphics();
		//main.addPoint(25,25);
		main.addPoint(cur);
		long lastTimer;
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		b2d = (Graphics2D) b.getGraphics();
		//b.getGraphics().drawImage(buff,0,0,null);
		v2d.drawImage(b,0,0,null);
		int frames = 0;
		int ticks = 0;
		lastTimer = System.currentTimeMillis();	
		while(true)
		{
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			
			
			while (unprocessed > 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}
			
			if (shouldRender) {
				frames++;
				render();
			}		
			try{
				Thread.sleep(2);
			} 
			catch (InterruptedException e){
				e.printStackTrace();
			}
			
			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println("Ticks: " + ticks + " FPS: " + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}
	Shape main = new Shape();
	Vector2 cur = new Vector2();
	public void tick()
	{
		cur.x = window.mouseX;
		cur.y = window.mouseY;
		if(window.leftMouseClick)
		{
			window.leftMouseClick = false;
			cur = new Vector2();
			main.addPoint(cur);
		}
		if(window.rightMouseClick)
		{
			main.removePoint(cur);
			shadow = true;
			window.rightMouseClick = false;
		}
		
	}
	boolean shadow =  false;
	double rotation = 0;
	BufferedImage b = BufferUtils.loadImage(".//res//light2.png", true);
	BufferedImage b2 = BufferUtils.loadImage(".//res//light2.png", true);
	Graphics2D b2d;
	public void render()
	{
		Graphics2D g2d = window.begin();
		g2d.setColor(Color.black);

		
		
		Shape shape = new Shape();
		shape.addPoint(25,25);
		shape.addPoint(25,50);
		shape.addPoint(60,80);
		shape.addPoint(35,35);
		//if(shadow)
			//main.rotate(-Math.toRadians(rotation));
		shape.addPoint(160,25);
		rotation += .1;
		if(rotation >= 360)
			rotation = 0;
		shape.translate(100,100);
		shape.rotate(Math.toRadians(rotation));
		//shape.scale(2,1.5);
		//if(shadow)
		//	main.rotate(Math.toRadians(rotation));
		shape.scale(1);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f));
		
		/*
		int draws = 1;
		for(double i = draws;i> 0;i--)
		{
			//main.scale(i/draws);
			//g2d.drawPolygon(drawPoly);
			//shape.getShadow(new Vector2(window.mouseX,window.mouseY));
			
			
			if(shadow)
			{
				Area s = shape.getShadow(new Vector2(window.mouseX,window.mouseY));
				s.add(main.getShadow(new Vector2(window.mouseX,window.mouseY)));
				Area f = new Area(new Rectangle(0,0,600,600));
				f.exclusiveOr(s);
				g2d.setClip(f);
			}
			else
			{
				Area f = new Area(new Rectangle(0,0,600,600));
				f.exclusiveOr(shape.getShadow(new Vector2(window.mouseX,window.mouseY)));
				g2d.setClip(f);
			}
			//g2d.fillOval(window.mouseX-200, window.mouseY-200, 400,400);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g2d.drawImage(b,3+window.mouseX-600, 3+window.mouseY-600, 1200,1200,null);
			g2d.drawImage(b,-3+window.mouseX-600, -3+window.mouseY-600, 1200,1200,null);
			
		}
		g2d.setClip(new Area(new Rectangle(0,0,900,900)));
		g2d.setColor(Color.white);
		*/
		g2d.fillPolygon(shape.getSPoly());
		g2d.fillPolygon(main.getSPoly());
		g2d.drawRect(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height);
		
		
		window.end();
	}
}
