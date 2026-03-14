package mino.ermal.engine3d.interfaces;

import mino.ermal.engine3d.matrix.RotationMatrix3D;
import mino.ermal.engine3d.primitives.Vertex;
import mino.ermal.engine3d.util.Vector;

  /**
   *
   */
  public interface Transformable {

    /**
     *
     * @param _factor {@code double}
     */
    public void scale(
      double _factor);

    /**
     *
     * @param _vector {@code Vector}
     */
    public void scale(
      final Vector _vector);
















    /**
     * This method rotates the object multiplicating with rotation matrix
     * wich must be created and prepared previously
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix);

    /**
     * This method translates an object by the x,y,z values of the
     * vector in the x,y,z directions
     * @param vector translation vector
     */
    public void translate(Vertex vector);


    
}
