package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.object3d.EdgeBasedObject3D;
import mino.ermal.engine3d.primitives.Edge;
import mino.ermal.engine3d.primitives.Vertex;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 18.49.37
 *
 * This class creates a Cube (only edge based,
 * the triangle based has can be implemented too)
 *
 */
public class Cube extends EdgeBasedObject3D {

    /**
     * Creates a new wireframe cube
     */
    public Cube(){
		super();
		super.add(new Edge(new Vertex(0,0,0),new Vertex(1,0,0)));
		super.add(new Edge(new Vertex(0,0,0),new Vertex(0,1,0)));
		super.add(new Edge(new Vertex(0,0,0),new Vertex(0,0,1)));

		super.add(new Edge(new Vertex(0,1,0),new Vertex(1,1,0)));
		super.add(new Edge(new Vertex(0,1,0),new Vertex(0,1,1)));

		super.add(new Edge(new Vertex(1,0,0),new Vertex(1,1,0)));
		super.add(new Edge(new Vertex(0,1,1),new Vertex(0,0,1)));

		super.add(new Edge(new Vertex(0,0,1),new Vertex(1,0,1)));
		super.add(new Edge(new Vertex(1,0,0),new Vertex(1,0,1)));

		super.add(new Edge(new Vertex(0,1,1),new Vertex(1,1,1)));
		super.add(new Edge(new Vertex(1,1,0),new Vertex(1,1,1)));
		super.add(new Edge(new Vertex(1,0,1),new Vertex(1,1,1)));

	}
	

}
