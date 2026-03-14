package es.noa.rad.demos.example13.com.stumpy.code.light.test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import es.noa.rad.demos.example13.com.stumpy.code.light.cullers.Block;
import es.noa.rad.demos.example13.com.stumpy.code.light.cullers.Culler;
import es.noa.rad.demos.example13.com.stumpy.code.light.cullers.Shape;
import es.noa.rad.demos.example13.com.stumpy.code.light.main.BufferUtils;
import es.noa.rad.demos.example13.com.stumpy.code.light.main.Light;
import es.noa.rad.demos.example13.com.stumpy.code.light.main.LightMap;


public class MainTest {
	private Window window;
	private LightMap lightmap;
	private boolean go = true;
	
	private BufferedImage bkg, light2;

	private Light light;
	private Block block;
	private int lightmode = 0;
	private int blockmode = 1;
	private int mode = 0;
	
	private float ambLight = 0;
	private float colorBlend = .4f;
	private float luma= .4f;
	
	public static void main(String[] args)
	{
		MainTest test = new MainTest();
		test.start();
	}
	
	public MainTest()
	{
		bkg = BufferUtils.loadImage("res/bkg.jpg",true);
		light2 = BufferUtils.loadImage("res/light2.png",true);
		go = true;
		window = new Window(800, 600, "Light", false);
		lightmap = new LightMap(window.getWidth(),window.getHeight(),1);
		lightmap.setAmbientColor(Themes.getColor(Themes.GREEN));
		lightmap.setAmbientLuminosity(luma);
		lightmap.setColorBlend(colorBlend);
		lightmap.setAmbientLight(ambLight);
		for(int i = 0;i< 50;i++)
		{
			lightmap.cullers.add(new Block(Rnd.randomInt(window.getWidth()),Rnd.randomInt(window.getHeight()),2+Rnd.randomInt(64),2+Rnd.randomInt(64)));
		}
		Shape shape = new Shape();
		shape.addPoint(25,25);
		shape.addPoint(25,50);
		shape.addPoint(50,50);
		shape.addPoint(66,25+17);
		shape.addPoint(50,25);		
		shape.scale(2);
		shape.translate(100,100);
		lightmap.cullers.add(shape);
		for(int i = 0;i <= 0;i++)
		{
			light = new Light(Rnd.randomInt(window.getWidth()),Rnd.randomInt(window.getHeight()),256,256,Themes.getColor(Themes.CYAN));
			light.intensity = 1;
			lightmap.addLight(light);
		}
		//lightmap.bakeLights();
		light = new Light(window.getWidth()/2,window.getHeight()/2,464,464,Themes.getColor(Themes.CYAN));
		light.intensity = 1;
		lightmap.addLight(light);
		//light.bakeLight();
		lightmap.setScreenBounds(0, 0, window.getWidth(), window.getHeight());
	}
	
	public void start()
	{
		long lastTimer;
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;

		int frames = 0;
		int ticks = 0;
		lastTimer = System.currentTimeMillis();	
		while(go)
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
		System.exit(0);
	}
	
	public void tick()
	{
		if(window.keyReleased == KeyEvent.VK_ESCAPE)
			go = false;
		if(window.keyReleased == KeyEvent.VK_1)
			mode = lightmode;
		else if(window.keyReleased == KeyEvent.VK_2)
			mode = blockmode;
		else if(window.keyPressed == KeyEvent.VK_A)
		{
			ambLight -= .01f;
			if(ambLight <= 0)
				ambLight = 0;
			lightmap.setAmbientLight(ambLight);
		}
		else if(window.keyPressed == KeyEvent.VK_S)
		{
			ambLight += .01f;
			if(ambLight >= 1)
				ambLight = 1;
			lightmap.setAmbientLight(ambLight);
		}
		else if(window.keyPressed == KeyEvent.VK_Z)
		{
			colorBlend -= .01f;
			if(colorBlend <= 0)
				colorBlend = 0;
			lightmap.setColorBlend(colorBlend);
		}
		else if(window.keyPressed == KeyEvent.VK_X)
		{
			colorBlend += .01f;
			if(colorBlend >= 1)
				colorBlend = 1;
			lightmap.setColorBlend(colorBlend);
		}
		if(mode == lightmode)
		{
			if(window.leftMouseClick)
			{
				if(light  == null)
				{
					for(int i = 0; i<= lightmap.getLights().size()-1;i++)
					{
						if(lightmap.getLights().get(i).containsPoint(window.mouseX,window.mouseY))
						{
							light = lightmap.getLights().get(i);
							//lightmap.getLights().get(i).debakeLight();
						}
					}
				}
				else
				{
					//light.bakeLight();
					light = null;
				}
				window.leftMouseClick = false;
			}
			if(window.rightMouseClick)
			{
				if(window.keyPressed == KeyEvent.VK_CONTROL)
				{
					int size = 228;
					Light l = new Light(0,0,size,size, Themes.getColor(Themes.CYAN));
					l.jump(window.mouseX, window.mouseY);
					l.decay = 0;
					l.intensity = 1;
					lightmap.addLight(l);
					light = l;
				}
				window.rightMouseClick = false;
			}
			if(window.keyPressed == KeyEvent.VK_DELETE)
			{
				lightmap.getLights().remove(light);
				light = null;
			}
			if(light == null)
				return;
			if(window.keyPressed == KeyEvent.VK_UP)
			{
				light.size.add(2);
			}
			if(window.keyPressed == KeyEvent.VK_DOWN)
			{
				if(light.size.y > 64)
					light.size.sub(2);
			}
			
			if(window.keyReleased == KeyEvent.VK_Q)
			{
				window.keyReleased = 0;
				lightmap.setScale(lightmap.getScale()+1);
			}
			if(window.keyReleased == KeyEvent.VK_W)
			{
				window.keyReleased = 0;
				if(lightmap.getScale() > 1)
					lightmap.setScale(lightmap.getScale()-1);
			}
			light.jump(window.mouseX, window.mouseY);
		}
		else if(mode == blockmode)
		{
			lightmap.debakeLights();
			if(window.leftMouseClick)
			{
				if(block  == null)
				{
					for(int i = 0; i<= lightmap.cullers.size()-1;i++)
					{
						if(lightmap.cullers.get(i).getType() == Culler.TYPE_BLOCK)
						{
							if(block.containsPoint(window.mouseX,window.mouseY))
								block = (Block) lightmap.cullers.get(i);
						}
					}
					if(window.keyPressed == KeyEvent.VK_CONTROL)
					{
						block = new Block(window.mouseX,window.mouseY,16,16);
						lightmap.cullers.add(block);
					}
				}
				else
					block = null;
				window.leftMouseClick = false;
			}
			if(window.keyPressed == KeyEvent.VK_DELETE)
			{
				lightmap.cullers.remove(block);
				block = null;
			}
			if(block == null)
				return;
			/*if(window.keyPressed == KeyEvent.VK_UP)
			{
				block.size.y += 2;
			}
			if(window.keyPressed == KeyEvent.VK_DOWN)
			{
				if(block.size.y > 2)
					block.size.y -= 2;
			}
			if(window.keyPressed == KeyEvent.VK_RIGHT)
			{
				block.size.x += 2;
			}
			if(window.keyPressed == KeyEvent.VK_LEFT)
			{
				if(block.size.x > 2)
					block.size.x -= 2;
			}*/
			if(block != null)
				block.setLoc(window.mouseX, window.mouseY);
		}

		lightmap.update();
	}
	VolatileImage vol;
	public void render()
	{
		if(vol == null)
			vol = BufferUtils.getCompatibleVImage(window.getWidth(), window.getHeight());
		Graphics2D g2d = window.begin();
		Graphics2D v2d = (Graphics2D) vol.getGraphics();
		v2d.setColor(Color.black);
		v2d.fillRect(0, 0, window.getWidth(),window.getHeight());
		v2d.drawImage(bkg,0,0,window.getWidth(),window.getHeight(),null);
		for(int i = 0;i<= lightmap.cullers.size()-1;i++)
		{
			if(lightmap.cullers.get(i).getType() == Culler.TYPE_BLOCK)
			{
				Block b = (Block) lightmap.cullers.get(i);
				v2d.setColor(Color.black);
				v2d.fillRect((int) (b.getLoc().x-b.getSize().x/2), (int) (b.getLoc().y-b.getSize().y/2), (int) b.getSize().x, (int) b.getSize().y);
			}
			else if(lightmap.cullers.get(i).getType() == Culler.TYPE_SHAPE)
			{
				v2d.setColor(Color.black);
				v2d.fillPolygon(((Shape) lightmap.cullers.get(i)).getSPoly());
			}
			
		}
		lightmap.render(v2d,0,0);
		g2d.setComposite(AlphaComposite.Src);
		g2d.drawImage(vol,0,0,null);
		g2d.dispose();
		window.end();
	}

}
