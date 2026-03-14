package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.primitives.Triangle;
import mino.ermal.engine3d.primitives.Vertex;


  public class Torus
    extends GridPlane {

    /**
     * Creates a torus shape with the main radius mainRadius and secondary radius internalRadius
     * with respective level of detail torusSegments,tubeSegments.
     * @param mainRadius main radius
     * @param torusSegments level of detail of the main circle
     * @param internalRadius secondary radius
     * @param tubeSegments level of detail of the secondary cirlce
     */

    public Torus(
      final double mainRadius,
      final int torusSegments,
      final double internalRadius,
      final int tubeSegments){

        //call of the base class constructor to create a grid domain
        super(2*Math.PI,2*Math.PI,tubeSegments,torusSegments);

        //transforms the grid domain into a tube
        for(int i=0;i<super.size();i++){
            Triangle tri=(Triangle)super.entity(i);
            calculateTube(tri.vertex1(),internalRadius);
            calculateTube(tri.vertex2(),internalRadius);
            calculateTube(tri.vertex3(),internalRadius);
        }

        //translates the tube domain by mainRadius
        this.translate(new Vertex(mainRadius,0,0));

        //transforms the tube into a torus
        for(int j=0;j<super.size();j++){
            Triangle tri=(Triangle)super.entity(j);
            calculateTorus(tri.vertex1());
            calculateTorus(tri.vertex2());
            calculateTorus(tri.vertex3());
        }
    }

    /**
     * Calculates the firts phase of torus creation, that of a tube
     * It transforms only the x and z coordinates with the circle equation
     * creating a tube in the y direction
     * @param v vertex to be transformed
     * @param internalRadius secondary radius
     */
    private void calculateTube(Vertex v,double internalRadius){
        v.z(internalRadius*Math.sin(v.x()));
        v.x(internalRadius*Math.cos(v.x()));

    }

    /**
     * transforms the tube vertexes into a torus by applying this time
     * the circle equation only to the x and y coordinates
     * @param v vertex to be transformed
     */
    private void calculateTorus(Vertex v){
        double x;
        x=v.x();

        v.x(x*Math.cos(v.y()));
        v.y(x*Math.sin(v.y()));
    }
}