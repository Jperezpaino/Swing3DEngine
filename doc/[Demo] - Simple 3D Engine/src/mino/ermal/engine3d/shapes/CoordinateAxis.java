package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.object3d.EdgeBasedObject3D;
import mino.ermal.engine3d.primitives.Edge;
import mino.ermal.engine3d.primitives.Vertex;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Mar 21, 2008
 * Time: 10:22:47 AM
 *
 * Creates three coordinate edges
 * By convention red is the color of the X coordinate vector,
 * green that of the Y coordinate and blue that of the Z one.
 */
public class CoordinateAxis extends EdgeBasedObject3D {

    /**
     * Creates three coordinate axes with the given length
     * @param length axis length
     */
    public CoordinateAxis(double length){
        super();

        Vertex o=new Vertex(0,0,0);  //origin vertex
        Vertex x=new Vertex(length,0,0); //X direction axis
        Vertex y=new Vertex(0,length,0); //Y direction axis
        Vertex z=new Vertex(0,0,length); //Z direction axis

        Edge ox=new Edge(o,x);
        ox.color(125,0,0);
        Edge oy=new Edge(o,y);
        oy.color(0,125,0);
        Edge oz=new Edge(o,z);
        oz.color(0,0,125);

        super.add(ox);
        super.add(oy);
        super.add(oz);
    }
}