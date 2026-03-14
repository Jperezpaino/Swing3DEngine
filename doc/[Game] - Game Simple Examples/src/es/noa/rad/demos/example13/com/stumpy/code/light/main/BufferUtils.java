package es.noa.rad.demos.example13.com.stumpy.code.light.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.VolatileImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferUtils {
	
	public static GraphicsEnvironment GE;
	public static GraphicsDevice GD;
	public static GraphicsConfiguration GC;
	
	static{
		GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GD = GE.getDefaultScreenDevice();
		GC = GD.getDefaultConfiguration();
	}

	/**
	 * Returns a BufferedImage[] that has both a and b inside it respectively. 
	 * @param a BufferedImage[] that is added first; 
	 * @param b BufferedImage[] that is added second; 
	 * @return resulting array
	 */
	public static BufferedImage[] append(BufferedImage a[], BufferedImage b[])
	{
		BufferedImage[] c = new BufferedImage[a.length+b.length];

		for(int i = 0; i <= a.length -1;i++)
		{
			c[i] = a[i];
		}
		for(int i = 0; i <= b.length -1;i++)
		{
			c[i+a.length] = b[i];
		}
		return c;
	}
	
	/**
	 * Creates a compatible BufferedImage with the width and height specified. 
	 * @param width - with of the Image
	 * @param height - height of the Image
	 * @return compatible image
	 */
	public static BufferedImage getCompatibleImage(int width, int height)
	{
		return GC.createCompatibleImage(width, height);
	}
	
	public static BufferedImage copyNewSrc(BufferedImage orig)
	{
		ColorModel cm = orig.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = orig.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	public static BufferedImage getScaledImage(BufferedImage orig, int width, int height, boolean linear)
	{
		BufferedImage nu = BufferUtils.getCompatibleImage(width, height);
		Graphics2D g2d = (Graphics2D) nu.getGraphics();
		if(linear)
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		else
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(orig,0,0,width,height,null);
		return nu;
	}
	
	public static VolatileImage copyNewSrc(VolatileImage orig)
	{
		VolatileImage nu = BufferUtils.getCompatibleVImage(orig.getWidth(), orig.getHeight());
		Graphics2D g2d = (Graphics2D) nu.getGraphics();
		g2d.drawImage(orig,0,0,null);
		return nu;
	}
	
	public static VolatileImage copyNewSrcVol(BufferedImage orig)
	{
		VolatileImage nu = BufferUtils.getCompatibleVImage(orig.getWidth(), orig.getHeight());
		Graphics2D g2d = (Graphics2D) nu.getGraphics();
		g2d.drawImage(orig,0,0,null);
		return nu;
	}
	
	public static BufferedImage colorImage(BufferedImage scr, Color c)
	{
		BufferedImage buff = BufferUtils.getCompatibleImage(scr.getWidth(), scr.getHeight());
		Graphics2D g2d = (Graphics2D) buff.getGraphics();
		
		g2d.setColor(c);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1f));
		g2d.fillRect(0,0,scr.getWidth(),scr.getHeight());
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_ATOP, 1f));
		g2d.drawImage(scr,0,0,scr.getWidth(),scr.getHeight(),null);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g2d.drawImage(buff, 0, 0, buff.getWidth(), buff.getHeight(),null);
		return buff;
	}
	
	//--------------------------Image Loading Methods-----------------------\\
	     
    public static BufferedImage[] loadImageStrip( BufferedImage orig, int cols, int rows, int images, int width, int height)
    {
    	int x = 0;
    	int y = 0;
    	int start = 0;
    	BufferedImage[] temp = new BufferedImage[images];
    	for(int k = 1; k <= rows;k++)
		{
			for(int j = 1; j <= cols;j++)
			{
				if(start <= images-1)
				{
					temp[start] = loadImage( orig, x, y, width, height);
					x += width;
				}
				start++;
			}
			y += height;
			x = 0;
		}
    	return temp;
    }
    
    public static BufferedImage loadImage( BufferedImage orig, int x, int y, int imageWidth, int imageHeight )
    {
		
	    BufferedImage tempBuff = orig;
	    BufferedImage a = GC.createCompatibleImage(imageWidth, imageHeight, Transparency.TRANSLUCENT);
	    Graphics tempG = a.getGraphics();
	    tempG.drawImage(tempBuff.getSubimage(x, y, imageWidth, imageHeight), 0, 0, null);
	    
	    tempG.dispose();
	    return a;
    }
    public static VolatileImage getCompatibleVImage(int width, int height)
	{
    	return getCompatibleVImage(width,height,false);
	}
    
    public static VolatileImage getCompatibleVImage(int width, int height, boolean cleared)
	{
    	if(!cleared)
    		return GC.createCompatibleVolatileImage(width, height, Transparency.TRANSLUCENT);
    	VolatileImage image = GC.createCompatibleVolatileImage(width, height, Transparency.TRANSLUCENT);
    	Graphics2D g = (Graphics2D)image.getGraphics();
    		g.setColor(new Color(0,0,0,0));
    		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OUT));
    		g.fillRect(0, 0, image.getWidth(), image.getHeight());
    		return image;
    		//return GC.createCompatibleVolatileImage(width, height, Transparency.TRANSLUCENT);
	}
    
    public static BufferedImage loadImage(String path, boolean fromClass)
    {
    	if(fromClass)
    	{
    		try {
    			//return convertToArgb(ImageIO.read(ClassLoader.getSystemResource(path)));
       return convertToArgb(ImageIO.read(new File(path)));
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    			System.out.println("Could not find file: " + path);
    		}
    		return null;
    	}
    	else
    	{
    		try {
    			return convertToArgb(ImageIO.read(new File(path)));
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    			System.out.println("Could not find file: " + path);
    		}
    		return null;
    	}
    	
    }
    
    public static BufferedImage convertToArgb(BufferedImage scr)
    {
    	BufferedImage send = new BufferedImage(scr.getWidth(),scr.getHeight(),BufferedImage.TYPE_INT_ARGB);
    	send.getGraphics().drawImage(scr,0,0,null);
    	return send;
    }
    
    public static GraphicsEnvironment getGE()
    {
    	return GE;
    }

}
