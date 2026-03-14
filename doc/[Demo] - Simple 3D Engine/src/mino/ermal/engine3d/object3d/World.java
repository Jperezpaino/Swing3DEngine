package mino.ermal.engine3d.object3d;

import java.util.Vector;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Sep 30, 2007
 * Time: 4:35:45 PM
 *
 * The World class represents a set of 3d objects
 * It is basically an 3D object containing a set of 3D objects.
 *
 */
public class World extends Object3D{
    
    public World(){
        super();
    }

    public World(Vector objects){
        super(objects);
    }

}
