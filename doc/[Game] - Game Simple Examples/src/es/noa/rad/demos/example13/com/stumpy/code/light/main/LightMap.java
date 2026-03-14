package es.noa.rad.demos.example13.com.stumpy.code.light.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import es.noa.rad.demos.example13.com.stumpy.code.light.cullers.Culler;

public class LightMap 
{
	
private static final Color BLANK = new Color(0,0,0,0);
	
	public ArrayList<Culler> cullers = new ArrayList<Culler>(50);
	
	private boolean AA_ON = true;
	private boolean DYNAMIC_LIGHT = true;
	private boolean LIGHT_ON = true;

	private int scrWidth;
	private int scrHeight;
	private Rectangle screenBounds = new Rectangle(0,0,0,0);
	private int mapWidth;
	private int mapHeight;
	private int lightCount = 0 ;
	private int maxLights = 120;
	private int scale;
	private ArrayList<Light> lights = new ArrayList<Light>(25);
	private VolatileImage lightmap;
	private Graphics2D mapg2d;
	
	private Color ambientColor = new Color(0,0,0,255);
	private float ambientLight = 0f;
	private float colorBlend = .4f;
	
	public boolean getAA()
	{
		return AA_ON;
	}
	
	public boolean dynamicLight()
	{
		return DYNAMIC_LIGHT;
	}
	
	public boolean lightOn()
	{
		return LIGHT_ON;
	}
	
	public int getScale()
	{
		return scale;
	}
	
	public void setScale(int i)
	{
		scale = i;
	
	}
	
	public void setAA(boolean c)
	{
		AA_ON = c;
	}
	
	public void setDynamic(boolean c)
	{
		DYNAMIC_LIGHT = true;
	}
	
	public void setLightOn(boolean c)
	{
		LIGHT_ON = c;
	}
	
	public void clearLights()
	{
		lights.clear();
	}
	
	public void setColorBlend(float f)
	{
		colorBlend = f;
	}
	
	public void setAmbientColor(Color c)
	{
		ambientColor = c;
	}
	
	public void setAmbientLuminosity(float f)
	{
		ambientColor = new Color(ambientColor.getRed(),ambientColor.getGreen(),ambientColor.getBlue(),(int)(f*255));
	}
	
	public void setAmbientLuminosity(int f)
	{
		ambientColor = new Color(ambientColor.getRed(),ambientColor.getGreen(),ambientColor.getBlue(),f);
	}
	
	public void setAmbientLight(float f)
	{
		ambientLight = f;
	}
	
	public Rectangle getScreenBounds()
	{
		return screenBounds;
	}
	
	public void setScreenBounds(double x, double y, double w, double h)
	{
		screenBounds.x = (int) x;
		screenBounds.y = (int) y;
		screenBounds.width = (int) w;
		screenBounds.height = (int) h;
	}
	
	public void addLight(Light l)
	{
		if(lightCount >= maxLights)
			return;
		lights.add(l);
		lights.get(lights.size()-1).setLightMap(this);
		lights.get(lights.size()-1).setCullers(cullers);
	}
	
	public void addLight(Light l, int where)
	{
		if(!LIGHT_ON || lightCount >= maxLights)
			return;
		lights.add(where, l);
		lights.get(lights.size()-1).setLightMap(this);
		lights.get(lights.size()-1).setCullers(cullers);
	}
	
	public LightMap(int w, int h, int scale)
	{
		this.scale = scale;
	
		scrWidth = w;
		scrHeight = h;
		mapWidth = w;
		mapHeight = h;
		lightmap = BufferUtils.getCompatibleVImage(mapWidth, mapHeight);
		mapg2d = (Graphics2D) lightmap.getGraphics();
	}
	
	public void update()
	{
		for(int i = 0;i<= lights.size()-1;i++)
		{
			lights.get(i).update();
			if(!lights.get(i).alive)
				lights.remove(i);
		}
		lightCount = lights.size();
	}
	
	public void render(Graphics g)
	{
		render(g,0,0);
	}
	
	public void render(Graphics g, int offX, int offY)
	{
		if(!LIGHT_ON)
			return;
		clear();
		mapg2d.translate(-offX,-offY);
		mapg2d.setColor(ambientColor);
		mapg2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ambientLight));
		mapg2d.fillRect(offX,offY,mapWidth,mapHeight);
		for(int i = 0; i <= lights.size()-1;i++)
		{
			lights.get(i).render(mapg2d);
		}
		mapg2d.setColor(ambientColor);

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, colorBlend));
		g2d.drawImage(lightmap,offX,offY,scrWidth,scrHeight,null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_ATOP, 1));
		g2d.drawImage(lightmap,offX,offY,scrWidth,scrHeight,null);
		g2d.dispose();
		mapg2d.translate(offX,offY);
	}
	
	private void clear()
	{
		mapg2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		mapg2d.setColor(BLANK);
		mapg2d.fillRect(0, 0, mapWidth, mapHeight);
	}
	
	public ArrayList<Light> getLights()
	{
		return lights;
	}
	
	public void bakeLights()
	{
		for(int i = 0; i <= lights.size()-1;i++)
		{
			lights.get(i).bakeLight();
			lights.get(i).dynamic = false;
		}
	}
	
	public void debakeLights()
	{
		for(int i = 0; i <= lights.size()-1;i++)
		{
			lights.get(i).debakeLight();
			lights.get(i).dynamic = true;
		}
	}

}
