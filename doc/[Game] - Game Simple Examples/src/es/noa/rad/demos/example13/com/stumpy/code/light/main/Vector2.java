package es.noa.rad.demos.example13.com.stumpy.code.light.main;

public class Vector2 {
	public double x,y;
	
	public Vector2()
	{
		this(0,0);
	}
	
	public Vector2(double dx, double dy)
	{
		x = dx;
		y = dy;
	}
	
	public Vector2(Vector2 v)
	{
		x = v.x;
		y = v.y;
	}
	
	public void add(double numx, double numy)
	{
		x += numx;
		y += numy;
	}
	
	public void sub(double numx, double numy)
	{
		x -= numx;
		y -= numy;
	}
	
	public void div(double numx, double numy)
	{
		x /= numx;
		y /= numy;
	}
	
	public void mult(double numx, double numy)
	{
		x *= numx;
		y *= numy;
	}
	
	public void add(Vector2 other)
	{
		x += other.x;
		y += other.y;
	}
	
	public void sub(Vector2 other)
	{
		x -= other.x;
		y -= other.y;
	}
	
	public void mult(Vector2 other)
	{
		x *= other.x;
		y *= other.y;
	}
	
	public void div(Vector2 other)
	{
		x /= other.x;
		y /= other.y;
	}
	
	public void add(double num)
	{
		x += num;
		y += num;
	}
	
	public void sub(double num)
	{
		x -= num;
		y -= num;
	}
	
	public void div(double num)
	{
		x /= num;
		y /= num;
	}
	
	public void mult(double num)
	{
		x *= num;
		y *= num;
	}
	
	public void normalise()
	{
		double temp = getLength();
		x /= temp;
		y /= temp;
	}
	
	public Vector2 moveToVec(Vector2 other, double amount)
	{
		Vector2 anglePoint = new Vector2(other.x,other.y);
		anglePoint.x -= x;
		anglePoint.y -= y;
		anglePoint.normalise();
		anglePoint.mult(amount);
		return anglePoint;
	}
	
	public Vector2 normVector()
	{
		double temp = getLength();
		return new Vector2(x/temp,y/temp);
	}
	
	public double getLength()
	{
		return Math.sqrt((x*x)+(y*y));
	}
	
	public double dist(Vector2 other)
	{
		return Math.sqrt((x-other.x)*(x-other.x)+(y-other.y)*(y-other.y));
	}
	
	public boolean compare(Vector2 other)
	{
		if(other.x == x && other.y == y)
			return true;
		else 
			return false;
	}
	
	public void rotateByRadians(Vector2 origin, double radians) {
        double cosVal = Math.cos(radians);
        double sinVal = Math.sin(radians);

        double ox = x - origin.x;
        double oy = y - origin.y;

        x = origin.x + ox * cosVal - oy * sinVal;
        y = origin.y + ox * sinVal + oy * cosVal;
    }
	
	public void rotateByRadians(double lx, double ly, double radians) {
        double cosVal = Math.cos(radians);
        double sinVal = Math.sin(radians);

        double ox = x - lx;
        double oy = y - ly;

        x = lx + ox * cosVal - oy * sinVal;
        y = ly + ox * sinVal + oy * cosVal;
    }


}
