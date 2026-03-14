package es.noa.rad.demos.example13.com.stumpy.code.light.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import es.noa.rad.demos.example13.com.stumpy.code.light.cullers.Culler;

public class Light{
	private static final Color BLANK  = new Color(0,0,0,0);
	private static BufferedImage LIGHT_1;
	
	private static VolatileImage LIGHT_BUFFER1;
	//private static VolatileImage LIGHT_BUFFER2;
	private static Graphics2D BUFFER1_G2D;
	//private static Graphics2D BUFFER2_G2D;
	
	static
	{
		LIGHT_1 = BufferUtils.loadImage("res/light2.png", true);
		LIGHT_BUFFER1 = BufferUtils.getCompatibleVImage(2048,2048);
		BUFFER1_G2D = (Graphics2D) LIGHT_BUFFER1.getGraphics();
	}
	
	private ArrayList<Culler> cullers;
	
	public Vector2 maxSize = new Vector2(512,512);
	public Vector2 minSize = new Vector2(4,4);
	public Vector2 sizeFlux = new Vector2(0,0);
	public Vector2 loc = new Vector2(0,0);
	public Vector2 size = new Vector2(264,264);
	private Color color = Color.BLUE;
	private BufferedImage scrLight; 
	
	private BufferedImage bakedLight;

	private LightMap father;
	public float intensity = 1;
	public float decay = 0;
	public boolean alive = false;
	public boolean dynamic = true;
	private boolean baked = false;

	public Light(double x, double y, double width, double height, Color c)
	{	
		this(x,y,width,height,c,Light.LIGHT_1);
	}
	
	public Light(double x, double y, double width, double height, Color c, BufferedImage scr)
	{	
		loc.x = x;
		loc.y = y;
		size.x = width;
		size.y = height;
		color = c;
		alive = true;
		scrLight = scr;
		clear();
	}
	
	public void jump(double x, double y)
	{
		loc.x = x;
		loc.y = y;
	}
	
	public void setLightMap(LightMap map)
	{
		father = map;
	}
	
	public void setCullers(ArrayList<Culler> shapes)
	{
		cullers = shapes;
	}
	
	public void update()
	{
		size.add(sizeFlux);
		if(size.x >= maxSize.x || size.x <= minSize.x)
			sizeFlux.x *= -1;
		if(size.y >= maxSize.y || size.x <= minSize.y)
			sizeFlux.y *= -1;
		intensity -= decay;
		if(intensity < 0)
			alive = false;
	}
	
	private static Rectangle2D RECT = new Rectangle2D.Double();
	public boolean containsPoint(double x, double y)
	{
		RECT.setFrame(loc.x-size.x/2,loc.y-size.y/2,size.x,size.y);
		return RECT.contains(x, y);
	}
	
	public void setColor(Color c)
	{
		color = c;
		clear();
	}
	
	public void render(Graphics g)
	{
		if(!father.getScreenBounds().intersects(loc.x-size.x/2, loc.y-size.y/2, size.x,size.y))
			return;
		Graphics2D g2d = (Graphics2D) g.create();	
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, intensity));
		if(baked)
			g2d.drawImage(bakedLight,(int)(loc.x-size.x/2),(int) (loc.y-size.y/2),null);
		else
		{
			clear();
			if(father.dynamicLight() && dynamic)
				createShadows();
			g2d.setClip((int) (loc.x-size.x/2),(int) (loc.y-size.y/2),(int)size.x,(int)size.y);
			g2d.drawImage(LIGHT_BUFFER1,(int)(loc.x-size.x/2),(int) (loc.y-size.y/2),null);
		}
		g2d.dispose();
	}
	
	private void createShadows()
	{
		for(int i = 0;i<= cullers.size()-1;i++)
		{
			if(cullers.get(i).collision(loc.x-size.x/2,loc.y-size.y/2, size.x,size.y))
			{
				//System.out.println("tes");
				drawArea(cullers.get(i).getShadow(loc));
			}
		}
	}
	
	private void drawArea(Polygon a)
	{
		a.translate((int)(-loc.x+size.x/2), (int) (-loc.y+size.y/2));
		//a.transform(AffineTransform.getTranslateInstance(-loc.x+size.x/2, -loc.y+size.y/2));
		BUFFER1_G2D.setColor(BLANK);
		BUFFER1_G2D.fill(a);
	}
	
	private void clear()
	{
		BUFFER1_G2D.setClip(0, 0,(int) size.x, (int) size.y);
		BUFFER1_G2D.setColor(color);
		BUFFER1_G2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		BUFFER1_G2D.fillRect(0,0,(int)size.x,(int)size.y);
		BUFFER1_G2D.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_ATOP));
		BUFFER1_G2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		//System.out.println(""+size.x+" "+size.y);
		BUFFER1_G2D.drawImage(scrLight,0,0,(int)size.x,(int)size.y,null);
	}
	
	public void debakeLight()
	{
		baked = false;
		dynamic = true;
	}
	
	public void bakeLight()
	{
		bakedLight = BufferUtils.getCompatibleImage((int)size.x,(int)size.y);
		Graphics2D bakedg2d = (Graphics2D) bakedLight.getGraphics();
		bakedg2d.setColor(color);
		bakedg2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1f));
		bakedg2d.fillRect(0,0,(int)size.x,(int)size.y);
		bakedg2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_ATOP, 1f));
		bakedg2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		bakedg2d.drawImage(scrLight,0,0,(int)size.x,(int)size.y,null);
		for(int i = 0;i<= cullers.size()-1;i++)
		{
			if(cullers.get(i).collision(loc.x-size.x/2,loc.y-size.y/2, size.x,size.y))
			{
				drawBakedArea(cullers.get(i).getShadow(loc),bakedg2d);
			}
		}
		baked = true;
		dynamic = false;
	}
	
	private void drawBakedArea(Polygon a, Graphics2D g2d)
	{
		a.translate((int)(-loc.x+size.x/2), (int) (-loc.y+size.y/2));
		//a.transform(AffineTransform.getTranslateInstance(-loc.x+size.x, -loc.y+size.y));
		g2d.setColor(BLANK);
		g2d.fill(a);
	}
	
	//private void createSoftClippingImage()
	//{
	//	LIGHT_BUFFER2 = BufferUtils.getCompatibleVImage(2048,2048);
	//	BUFFER2_G2D = (Graphics2D) LIGHT_BUFFER2.getGraphics();
	//}

}
