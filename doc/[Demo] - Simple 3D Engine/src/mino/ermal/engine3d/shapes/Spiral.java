package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.primitives.Edge;
import mino.ermal.engine3d.primitives.Vertex;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Mar 21, 2008
 * Time: 10:31:16 AM
 *
 * This class represents a 3D spiral
 *
 */
public class Spiral extends EdgeDomain {

    /**
     * Creates a 3D spiral starting form a edge domain.
     * It then aplies the spiral transformation equation to
     * all vertexes
     * @param length base edge domain length
     * @param r spiral radius
     * @param h spiral height (distance in depth for each turn)
     * @param detail level of detail
     */
    public Spiral(double length,double r,double h,int detail){

        //initialize the base edge domain object
        super(length,detail);

        //position in the center
        translate(new Vertex(length/-2,0,0));

        //apply the spiral transformation to each vertex
        for(int i=0;i<super.size();i++){
            Edge seg=(Edge)super.entity(i);
            calculateSpirale(seg.vertexStart(),r,h);
            calculateSpirale(seg.vertexEnd(),r,h);
        }
    }

    /**
     * Calculate the position of a point in a spiral.
     * Essentially the transformation equation of the spiral
     * is the same of that of the circle but with a third coordinate
     * expanding in depth.
     * Normally the depth would be Z but i have chosen to use X because
     * the edge domain is on the X axis
     * @param v vertex to be transformed
     * @param r spiral radius
     * @param h spiral height (distance in depth for each turn)
     */
    private void calculateSpirale(Vertex v,double r,double h){
        double x;
        //save old x value
        x=v.x();

        //calculate
        v.x(h*x/Math.PI);
        v.y(r*Math.cos(x));
        v.z(r*Math.sin(x));
    }
}
