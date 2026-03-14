package es.noa.rad.demos.example13.com.stumpy.code.light.cullers;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

import es.noa.rad.demos.example13.com.stumpy.code.light.main.Vector2;

public class Block implements Culler {
	private static Rectangle2D RECT = new Rectangle2D.Double();
	
	private Vector2 loc = new Vector2();
	private Vector2 size = new Vector2();
	
	private Polygon shadow;
	
	public Block()
	{
		this(0,0);
	}
	
	public Block(double x, double y)
	{
		this(x,y,4,4);
	}
	
	public Block(double x, double y, double width, double height)
	{
		getLoc().x = x;
		getLoc().y = y;
		getSize().x = width;
		getSize().y = height;
	}
	
	@Override
	public boolean collision(double x, double y, double width , double hight)
	{
		RECT.setFrame(getLoc().x+getSize().x,getLoc().y+getSize().y,getSize().x,getSize().y);
		return RECT.intersects(x, y, width, hight);
	}
	
	@Override
	public int getType()
	{
		return Culler.TYPE_BLOCK;
	}
	
	@Override
	public Polygon getShadow(Vector2 source) {
		
		Point p1 = null,p2 = null,p3,p4,p5 = null,p6 = null;
		shadow = new Polygon();
		if(source.x < getLoc().x + getSize().x/2 && source.x> getLoc().x - getSize().x/2 )
		{
			if(getLoc().y >= source.y)
			{
				p1 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y-getSize().y/2));
				p2 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y-getSize().y/2));
				p5 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y+getSize().y/2));
				p6 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y+getSize().y/2));
			}
			else
			{
				p1 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y+getSize().y/2));
				p2 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y+getSize().y/2));
				p5 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y-getSize().y/2));
				p6 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y-getSize().y/2));
			}
		}
		else if (source.y < getLoc().y + getSize().y/2 && source.y > getLoc().y - getSize().y/2 )
		{
			if(source.x <= getLoc().x)
			{
				p1 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y-getSize().y/2));
				p2 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y+getSize().y/2));
				p5 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y+getSize().y/2));
				p6 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y-getSize().y/2));
			}
			else
			{
				p1 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y-getSize().y/2));
				p2 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y+getSize().y/2));
				p5 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y+getSize().y/2));
				p6 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y-getSize().y/2));
			}
		}
		else
		{
			if(getLoc().x < source.x)
			{
				if(getLoc().y-getSize().y/2 < source.y)
				{
					p1 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y+getSize().y/2));
					p2 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y-getSize().y/2));
					p5 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y-getSize().y/2));
				}
				else
				{
					p1 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y-getSize().y/2));
					p2 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y+getSize().y/2));
					p5 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y+getSize().y/2));
				}
			}
			else
			{
				if(getLoc().y-getSize().y/2 < source.y)
				{
					p1 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y-getSize().y/2));
					p2 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y+getSize().y/2));
					p5 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y-getSize().y/2));
				}
				else
				{
					p1 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y-getSize().y/2));
					p2 = new Point((int)(getLoc().x - getSize().x/2),(int) (getLoc().y+getSize().y/2));
					p5 = new Point((int)(getLoc().x + getSize().x/2),(int) (getLoc().y+getSize().y/2));
				}
			}
		}
		Point p7 = null;
		if(getSize().x != getSize().y)
		{	
			Vector2 anglePoint = new Vector2(source.x,source.y);
			anglePoint.sub(getLoc());
			anglePoint.normalise();
			anglePoint.mult(-5000);
			p7 = new Point((int) (getLoc().x+anglePoint.x),(int) (getLoc().y+anglePoint.y));
		}
		Vector2 anglePoint = new Vector2(source.x,source.y);
		anglePoint.x -= p1.x;
		anglePoint.y -= p1.y;
		anglePoint.normalise();
		anglePoint.mult(-5000);
		p3 = new Point((int) (p1.x+anglePoint.x),(int) (p1.y+anglePoint.y));
		anglePoint = new Vector2(source.x,source.y);
		anglePoint.x -= p2.x;
		anglePoint.y -= p2.y;
		anglePoint.normalise();
		anglePoint.mult(-5000);
		p4 = new Point((int) (p2.x+anglePoint.x),(int) (p2.y+anglePoint.y));
		
		shadow.addPoint(p1.x,p1.y);
		shadow.addPoint(p3.x,p3.y);
		if(p7 != null)
			shadow.addPoint(p7.x,p7.y);
		shadow.addPoint(p4.x,p4.y);
		shadow.addPoint(p2.x,p2.y);
		shadow.addPoint(p5.x,p5.y);
		if(p6 != null)
			shadow.addPoint(p6.x,p6.y);
		return shadow;
	}
	
	public boolean containsPoint(double x, double y)
	{
		RECT.setFrame(getLoc().x+getSize().x,getLoc().y+getSize().y,getSize().x,getSize().y);
		return RECT.contains(x, y);
	}
	
	public void setLoc(double x, double y)
	{
		loc.x = x;
		loc.y = y;
	}

	public Vector2 getLoc() {
		return loc;
	}

	public void setLoc(Vector2 loc) {
		this.loc = loc;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

}
