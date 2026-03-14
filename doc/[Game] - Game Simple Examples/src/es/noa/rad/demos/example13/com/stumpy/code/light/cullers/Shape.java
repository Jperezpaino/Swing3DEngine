package es.noa.rad.demos.example13.com.stumpy.code.light.cullers;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import es.noa.rad.demos.example13.com.stumpy.code.light.main.Vector2;

public class Shape implements Culler{
	
	private ArrayList<Vector2> points = new ArrayList<Vector2>();
	private Rectangle2D bounds;
	
	private Polygon shadow;
	public Shape()
	{
		
	}
	
	public void addPoint(Vector2 p)
	{
		calculateBounds();
		points.add(p);
	}
	
	public void removePoint(Vector2 p)
	{
		calculateBounds();
		points.remove(p);
	}
	
	public void addPoint(double x, double y)
	{
		calculateBounds();
		addPoint(new Vector2(x,y));
	}
	
	public void clear()
	{
		points.clear();
	}
	
	@Override
	public boolean collision(double x, double y, double width , double hight)
	{
		if(bounds == null)
			calculateBounds();
	    return bounds.intersects(x, y, width, hight);
	}
	
	@Override
	public int getType()
	{
		return Culler.TYPE_SHAPE;
	}
	
	@Override
	public Polygon getShadow(Vector2 source) {
		//shadow = new Polygon();
		Polygon mainShape = getSPoly();

		
		Area shadow = new Area();
		Vector2 p1 = points.get(0); 
		for(int i = 1;i<= points.size()-1;i++)
		{
			Vector2 p2 = points.get(i);
			if(!shadow.contains(p2.x, p2.y))
			{
				Vector2 cast1 = p1.moveToVec(source, -5000);
				Vector2 cast2 = p2.moveToVec(source, -5000);
				Polygon part = new Polygon();
				part.addPoint((int)p1.x,(int)p1.y);
				part.addPoint((int)p2.x,(int)p2.y);
				part.addPoint((int)cast2.x,(int)cast2.y);
				part.addPoint((int)cast1.x,(int)cast1.y);
				shadow.add(new Area(part));
			}
			p1 = p2;
		}
		
		Area main = new Area(mainShape);
		shadow.subtract(main);
		//shadow.exclusiveOr(main);
		shadow.transform(AffineTransform.getTranslateInstance(-bounds.getCenterX(), -bounds.getCenterY()));
		shadow.transform(AffineTransform.getScaleInstance(.99, .99));
		shadow.transform(AffineTransform.getTranslateInstance(bounds.getCenterX(), bounds.getCenterY()));
		this.shadow = new Polygon();
	      
	    PathIterator path = shadow.getPathIterator(null);
	    while (!path.isDone()) 
	    {
	    	double[] point = new double[2];
	    	if(path.currentSegment(point) != PathIterator.SEG_CLOSE)
	    		this.shadow.addPoint((int)point[0],(int)point[1]);
	    	path.next();
	    }
	
		return this.shadow;
	}
	
	public void rotate(double radions)
	{
		if(bounds == null)
			calculateBounds();
		for(int i = 0;i<= points.size()-1;i++)
		{
			points.get(i).rotateByRadians(bounds.getCenterX(),bounds.getCenterY(), radions);
		}
	}
	
	public void scale(double num)
	{
		scale(num,num);
	}

	public void scale(double dx, double dy)
	{
		if(bounds == null)
			calculateBounds();
		Vector2 affineOrigin = new Vector2(bounds.getCenterX(),bounds.getCenterY());
		translate(-affineOrigin.x,-affineOrigin.y);
		for(int i = 0;i<= points.size()-1;i++)
		{
			points.get(i).mult(dx,dy);
		}
		translate(affineOrigin.x,affineOrigin.y);
	}
	
	private void calculateBounds() {
		bounds = getSPoly().getBounds2D();
	}

	public void translate(double dx, double dy)
	{
		if(points.isEmpty())
			return;
		for(int i = 0;i<= points.size()-1;i++)
		{
			points.get(i).add(dx,dy);
		}
		if(bounds == null)
			return;
		bounds.setRect(bounds.getX()+dx,bounds.getY()+dy,bounds.getWidth(),bounds.getHeight());
	}
	
	public Rectangle getBounds()
	{
		return getSPoly().getBounds();
	}
	
	public Rectangle2D getDoubleBounds()
	{
		bounds = getSPoly().getBounds2D();
		return bounds;
	}
	
	public void copyOver(Shape other)
	{
		for(int i = 0; i <= other.points.size()-1;i++)
		{
			addPoint(other.points.get(i).x,other.points.get(i).y);
		}
		calculateBounds();
	}
	
	public Polygon getSPoly()
	{
		shadow = new Polygon();
		for(int i = 0; i <= points.size()-1;i++)
		{
			shadow.addPoint((int)points.get(i).x,(int) points.get(i).y);
		}
		return shadow;
	}
	/*
	private Vector2 getIntersection(Vector2 s1, Vector2 e1, Vector2 s2, Vector2 e2) {
	      double denom = ((e1.y - s1.y) * (e2.x - s2.x))
	            - ((e1.x - s1.x) * (e2.y - s2.y));
	      double nume_a = ((e1.x - s1.x) * (s2.y - s1.y))
	            - ((e1.y - s1.y) * (s2.x - s1.x));
	      double nume_b = ((e2.x - s1.x) * (s2.y - s1.y)) - ((e2.y - s2.y) * (s2.x - s1.x));

	      if (denom == 0.0f) {
	         return null;
	      }

	      double ua = nume_a / denom;
	      double ub = nume_b / denom;

	      if ((ua >= 0.0f) && (ua <= 1.0f) && (ub >= 0.0f) && (ub <= 1.0f)) {

	         // Get the intersection point.
	         double intersectX = (int) (s1.x + ua * (e1.x - s1.x));
	         double intersectY = (int) (s1.y + ua * (e1.y - s1.y));

	         return new Vector2(intersectX, intersectY);
	      }

	      return null;
	   }
	
	private Line2D.Double[] getLines(Vector2[] points)
	{
		Line2D.Double[] lines = new Line2D.Double[points.length-2];
		Vector2 p2 = points[0];
		int lineIndex = 0;
		for(int i =1;i<= points.length-2;i++)
		{
			lines[lineIndex++] = new Line2D.Double(p2.x,p2.y,points[i].x,points[i].y);
			p2 = points[i]; 
		}
		return lines;
	}
	
	private Vector2 findPoint(Vector2 s, Vector2 e, Line2D.Double[] lines)
	{
		for(int i =0;i<=lines.length-1;i++)
		{
			if(lines[i].intersectsLine(s.x,s.y,e.x,e.y))
			{
				Vector2 s1 = new Vector2(lines[i].x1,lines[i].y1);
				Vector2 e1 = new Vector2(lines[i].x2,lines[i].y2);
				return getIntersection(s,e,s1,e1);
			}
		}
		return null;
	}
	
	private Vector2 polyIntersects(Vector2 s, Vector2 e, Polygon shape)
	{
		ArrayList<Line2D.Double> lines = TestyStatic.getLines(shape);
		for(int i =0;i<=lines.size()-1;i++)
		{
			if(lines.get(i).intersectsLine(s.x,s.y,e.x,e.y))
			{
				Vector2 s1 = new Vector2(lines.get(i).x1,lines.get(i).y1);
				Vector2 e1 = new Vector2(lines.get(i).x2,lines.get(i).y2);
				return getIntersection(s,e,s1,e1);
			}
		}
		return null;
	}
	*/
}
