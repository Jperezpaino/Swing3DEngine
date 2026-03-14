package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.object3d.EdgeBasedObject3D;
import mino.ermal.engine3d.primitives.Edge;
import mino.ermal.engine3d.primitives.Vertex;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 27-dic-2006
 * Time: 20.25.47
 *
 * This one generates random points (edges with length 0 actually)
 * in space. I just thought it would be fun to see how it looked.
 *
 */
public class Galactic extends EdgeBasedObject3D {

    /**
     * Create random points in space
     */
    public Galactic(){
		super();

        for(int i=0;i<1000;i++){

            //generate random x,y and z coordinates
            double x=Math.random()*300-150;
			double y=Math.random()*300-150;
			double z=Math.random()*300-150;

            //if it is within a 100 pixel radius then add it to the primitives set
            if(Math.sqrt(x*x+y*y+z*z)<=100) super.add(new Edge(new Vertex(x,y,z),new Vertex(x,y,z)));
		}
	}
}
