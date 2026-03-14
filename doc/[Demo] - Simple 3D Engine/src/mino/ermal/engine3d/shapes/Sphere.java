package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.primitives.Triangle;
import mino.ermal.engine3d.primitives.Vertex;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Mar 20, 2008
 * Time: 8:51:45 PM
 *
 * This class creates a sphere applying the sphere equation transformation
 * to the GridPlane that it extends
 *
 */
public class Sphere extends GridPlane {

    /**
     * creates a sphere applying the sphere equation transformation
     * to the GridPlane that it extends.
     * @param r radius
     * @param detailU width level of detail
     * @param detailV heinght level of detail
     */
    public Sphere(double r,int detailU,int detailV){

        //call of the base class constructor to create a grid domain
        super(2*Math.PI,Math.PI,detailU,detailV);

        //apply the sphere transformation to all vertexes
        for(int i=0;i<super.size();i++){
            Triangle tri=(Triangle) super.entity(i);
            calculateSphere(tri.vertex1(),r);
            calculateSphere(tri.vertex2(),r);
            calculateSphere(tri.vertex3(),r);
        }
    }

    /**
     * Calculates the sphere position of a point.
     * This position is obtained by multiplying the point(vertex) with the two
     * elementary rotation matrixes
     * the result is that of the unit sphere
     *    x=cos(x)*cos(y)
     *    y=cos(x)*sin(y)
     *    z=sin(x)
     * of course is only necessary to multiply with the radius length to
     * obtain a sphere with the desired radius
     * @param v vertex to be transformed
     * @param r sphere radius
     */
    private void calculateSphere(Vertex v,double r){
        double x;
        //save old x value necessary during calculations
        x=v.x();

        //calculate and update new values
        v.x(r*Math.cos(x)*Math.cos(v.y()));
        v.y(r*Math.cos(x)*Math.sin(v.y()));
        v.z(r*Math.sin(x));
    }
}
