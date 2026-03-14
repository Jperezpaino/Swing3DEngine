package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.object3d.TriangleBasedObject3D;
import mino.ermal.engine3d.primitives.Triangle;
import mino.ermal.engine3d.primitives.Vertex;


  public class Cylinder
      extends TriangleBasedObject3D {

    /**
     * creates a cylinder with the desired level of detail given by
     * an angle value expressed in radiants, the smaller this value is
     * the greater will be the level of detail and vice versa
     * @param radius radius
     * @param height height
     * @param detail level of detail
     */
    public Cylinder(
        final double radius,
        final double height,
        final int detail){
      super();
      double deltaRadio= (2 * (Math.PI / detail));
      for(double i=0;i<=2*Math.PI;i+= deltaRadio){
			super.add(new Triangle(new Vertex(radius*Math.cos(i),height,radius*Math.sin(i)),new Vertex(radius*Math.cos(i),-height,radius*Math.sin(i)),new Vertex(radius*Math.cos(i+ deltaRadio),height,radius*Math.sin(i+ deltaRadio))));
			super.add(new Triangle(new Vertex(radius*Math.cos(i),-height,radius*Math.sin(i)),new Vertex(radius*Math.cos(i+ deltaRadio),-height,radius*Math.sin(i+ deltaRadio)),new Vertex(radius*Math.cos(i+ deltaRadio),height,radius*Math.sin(i+ deltaRadio))));
        /* Tapa inferior. */
        super.add(
          new Triangle(
            new Vertex(radius*Math.cos(i), height, radius*Math.sin(i)),
            new Vertex(0,height,0),
            new Vertex(radius*Math.cos(i+ deltaRadio),height,radius*Math.sin(i+ deltaRadio))));
        /* Tapa superior. */
        super.add(
          new Triangle(
            new Vertex(radius*Math.cos(i), -height, radius*Math.sin(i)),
            new Vertex(radius*Math.cos(i+ deltaRadio),-height,radius*Math.sin(i+ deltaRadio)),
            new Vertex(0,-height,0)));
		}
	}
}
