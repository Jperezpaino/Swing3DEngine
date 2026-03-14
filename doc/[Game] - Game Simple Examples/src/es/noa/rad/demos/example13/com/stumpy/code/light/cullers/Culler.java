package es.noa.rad.demos.example13.com.stumpy.code.light.cullers;

import java.awt.Polygon;

import es.noa.rad.demos.example13.com.stumpy.code.light.main.Vector2;

public interface Culler {
	
	public static final int TYPE_BLOCK = 0;
	public static final int TYPE_CIRCLE = 1;
	public static final int TYPE_SHAPE = 2;
	
	public Polygon getShadow(Vector2 source);
	
	public int getType();
	
	public boolean collision(double x, double y, double width, double hight);

}
