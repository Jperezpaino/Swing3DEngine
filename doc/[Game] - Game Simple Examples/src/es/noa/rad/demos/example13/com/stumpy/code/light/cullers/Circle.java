package es.noa.rad.demos.example13.com.stumpy.code.light.cullers;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

import es.noa.rad.demos.example13.com.stumpy.code.light.main.Vector2;

public class Circle implements Culler{
	
	private static Rectangle2D RECT = new Rectangle2D.Double();
	
	private Vector2 loc = new Vector2();
	private double radius = 4;
	private int subQ = 6;
	
	private Polygon shadow;

	public Circle()
	{
		this(0,0);
	}
	
	public Circle(double x, double y)
	{
		this(x,y,4);
	}
	
	public Circle(double x, double y, double rad)
	{
		loc.x = x;
		loc.y = y;
		radius = rad;
	}
	
	@Override
	public boolean collision(double x, double y, double width , double hight)
	{
		RECT.setFrame(loc.x-radius,loc.y-radius, 2*radius,2*radius);
	    return RECT.intersects(x, y, width, hight);
	}
	
	@Override
	public int getType()
	{
		return Culler.TYPE_CIRCLE;
	}
	
	@Override
	public Polygon getShadow(Vector2 source) {
		shadow = new Polygon();
		Point p1,p2,p3,p4,p5;
		double angle = Math.atan2(loc.y - source.y,loc.x-source.x);
		p1 = new Point((int)(loc.x+radius*Math.cos(angle+1.57)),(int) (loc.y+radius*Math.sin(angle+1.57)));
		p2 = new Point((int)(loc.x+radius*Math.cos(angle-1.57)),(int) (loc.y+radius*Math.sin(angle-1.57)));
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
		anglePoint = new Vector2(source.x,source.y);
		anglePoint.sub(loc);
		anglePoint.normalise();
		anglePoint.mult(-5000);
		p5 = new Point((int) (loc.x+anglePoint.x),(int) (loc.y+anglePoint.y));
		shadow.addPoint(p1.x,p1.y);
		double amount = 3.14/subQ;
		for(double lerp = 1;lerp<subQ;lerp++)
		{
			shadow.addPoint((int)(loc.x+radius*Math.cos(angle+1.57-(amount*lerp))),(int) (loc.y+radius*Math.sin(angle+1.57-(amount*lerp))));
		}
		shadow.addPoint(p2.x,p2.y);
		shadow.addPoint(p4.x,p4.y);
		shadow.addPoint(p5.x,p5.y);
		shadow.addPoint(p3.x,p3.y);
		return shadow;
	}

}
