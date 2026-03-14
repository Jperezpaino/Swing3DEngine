package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.object3d.EdgeBasedObject3D;
import mino.ermal.engine3d.primitives.Edge;
import mino.ermal.engine3d.primitives.Vertex;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Mar 21, 2008
 * Time: 10:22:47 AM
 *
 * This object creates a straight line of a certain length
 * divided into a number of edges.
 * This object also provides a basis for other edge based objects
 * like Spiral.
 *
 */
public class EdgeDomain extends EdgeBasedObject3D {

    /**
     * Creates a straight line of length "length" and divided into "detail"
     * edges.
     * @param length length
     * @param detail number of edges that compone this line
     */
    public EdgeDomain(double length, int detail){
        super();
        double delta=length/detail;
        for(int i=0;i<detail-1;i++){
            super.add(new Edge(new Vertex(i*delta,0,0),new Vertex((i+1)*delta,0,0)));
        }
    }
}
