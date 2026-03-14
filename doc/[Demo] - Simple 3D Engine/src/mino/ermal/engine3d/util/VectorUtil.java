package mino.ermal.engine3d.util;

import mino.ermal.engine3d.primitives.Quad;
import mino.ermal.engine3d.primitives.Triangle;

  /**
   *
   */
  public final class VectorUtil {

    /**
     *
     */
    private VectorUtil() {
    }

    /**
     *
     * @param _vectorU {@code Vector}
     * @param _vectorV {@code Vector}
     * @return {@code Vector}
     */
    public static Vector cross(
        final Vector _vectorU,
        final Vector _vectorV){
      return new Vector(
        ((_vectorU.y() *_vectorV.z()) - (_vectorU.z() * _vectorV.y())),
        ((_vectorU.z() *_vectorV.x()) - (_vectorU.x() * _vectorV.z())),
        ((_vectorU.x() *_vectorV.y()) - (_vectorU.y() * _vectorV.x()))
      );
    }

    /**
     *
     * @param _vectorU {@code Vector}
     * @param _vectorV {@code Vector}
     * @return {@code double}
     */
    public static double dot(
        final Vector _vectorU,
        final Vector _vectorV){
      return (
        (_vectorU.x() * _vectorV.x()) 
      + (_vectorU.y() * _vectorV.y())
      + (_vectorU.z() * _vectorV.z())
      );
    }

    /**
     *
     * @param _vector {@code Vector}
     * @return {@code double}
     */
    public static double magnitude(
        final Vector _vector){
      return Math.sqrt(
        (_vector.x() * _vector.x())
      + (_vector.y() * _vector.y())
      + (_vector.z() * _vector.z())
      );
    }

    /**
     *
     * @param _vector {@code Vector}
     * @return {@code Vector}
     */
    public static Vector normalize(
        final Vector _vector) {
      final double length = VectorUtil.magnitude(_vector);
      
      if (length == 0) {
        return new Vector(0.0d, 0.0d, 0.0d);
      }
      
      return new Vector(
        _vector.x() / length,
        _vector.y() / length,
        _vector.z() / length
      );
    }

    /**
     *
     * @param _vectorU {@code Vector}
     * @param _vectorV {@code Vector}
     * @return {@code double}
     */
    public static double distance(
        final Vector _vectorU,
        final Vector _vectorV) {
      Vector difference = new Vector(
        (_vectorU.x() - _vectorV.x()),
        (_vectorU.y() - _vectorV.y()),
        (_vectorU.z() - _vectorV.z())
      );
      return VectorUtil.magnitude(difference);
    }

    /**
     *
     * @param _vectorU {@code Vector}
     * @param _vectorV {@code Vector}
     * @return {@code double}
     */
    public static double cosAngle(
        final Vector _vectorU,
        final Vector _vectorV){
      final Vector vectorU = VectorUtil.normalize(_vectorU);
      final Vector vectorV = VectorUtil.normalize(_vectorV);
      if ((VectorUtil.magnitude(vectorU) == 0)
       || (VectorUtil.magnitude(vectorV) == 0)) {
        return 0.0d;
      }
      return VectorUtil.dot(vectorU,vectorV);
    }

    /**
     *
     * @param _triangle {@code Triangle}
     * @return {@code Vector}
     */
    public static Vector computeNormal(
        final Triangle _triangle){
        
      return VectorUtil.cross(
        new Vector(
          (_triangle.vertex2().x() - _triangle.vertex1().x()),
          (_triangle.vertex2().y() - _triangle.vertex1().y()),
          (_triangle.vertex2().z() - _triangle.vertex1().z())
        ),
        new Vector(
          (_triangle.vertex3().x() - _triangle.vertex1().x()),
          (_triangle.vertex3().y() - _triangle.vertex1().y()),
          (_triangle.vertex3().z() - _triangle.vertex1().z())
        )
      );
    }

    /**
     *
     * @param _quad {@code Quad}
     * @return {@code Vector}
     */
    public static Vector computeNormal(
        final Quad _quad){

      final Vector normalFirstTriangle = VectorUtil.computeNormal(
        new Triangle(
          _quad.vertex1(),
          _quad.vertex2(),
          _quad.vertex3()
        )
      );

      final Vector normalSecondTriangle = VectorUtil.computeNormal(
        new Triangle(
          _quad.vertex1(),
          _quad.vertex3(),
          _quad.vertex4()
        )
      );

      final Vector normal = new Vector(
        (normalFirstTriangle.x() + normalSecondTriangle.x()) / 2,
        (normalFirstTriangle.y() + normalSecondTriangle.y()) / 2,
        (normalFirstTriangle.z() + normalSecondTriangle.z()) / 2
      );

      return VectorUtil.normalize(normal);
    }

  }




