package mino.ermal.engine3d.primitives;

import mino.ermal.engine3d.interfaces.Colorable;
import mino.ermal.engine3d.interfaces.Renderable;
import mino.ermal.engine3d.interfaces.Transformable;
import mino.ermal.engine3d.matrix.RotationMatrix3D;
import mino.ermal.engine3d.util.Vector;

  /**
   *
   */
  public class Vertex
      implements Renderable,
                 Colorable,
                 Transformable {

    /**
     *
     */
    private double x;

    /**
     *
     */
    private double y;

    /**
     *
     */
    private double z;

    /**
     *
     */
    private double w;

    /**
     *
     */
    public Color color;

    /**
     *
     */
    public Vertex(){
      this(
        0.0d,
        0.0d,
        0.0d
      );
    }

    /**
     *
     * @param _vertex {@code Vertex}
     */
    public Vertex(
        final Vertex _vertex){
      this(
        _vertex.x(),
        _vertex.y(),
        _vertex.z(),
        _vertex.w(),
        _vertex.color()
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public Vertex(
        final double _x,
        final double _y,
        final double _z){
      this(
        _x,
        _y,
        _z,
        1.0d
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _color {@code Color}
     */
    public Vertex(
        final double _x,
        final double _y,
        final double _z,
        final Color _color){
      this(
        _x,
        _y,
        _z,
        1.0d,
        _color
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     */
    public Vertex(
        final double _x,
        final double _y,
        final double _z,
        final double _w){
      this(
        _x,
        _y,
        _z,
        _w,
        new Color(Color.NO_COLOR)
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     * @param _color {@code Color}
     */
    public Vertex(
        final double _x,
        final double _y,
        final double _z,
        final double _w,
        final Color _color){
      this.x = _x;
      this.y = _y;
      this.z = _z;
      this.w = _w;
      this.color = _color;
    }

    /**
     *
     * @return {@code double}
     */
    public final double x() {
      return this.x;
    }

    /**
     *
     * @param _x {@code double}
     */
    public final void x(
        final double _x) {
      this.x = _x;
    }

    /**
     *
     * @return {@code double}
     */
    public final double y() {
      return this.y;
    }

    /**
     *
     * @param _y {@code double}
     */
    public final void y(
        final double _y) {
      this.y = _y;
    }

    /**
     *
     * @return {@code double}
     */
    public final double z() {
      return this.z;
    }

    /**
     *
     * @param _z {@code double}
     */
    public final void z(
        final double _z) {
      this.z = _z;
    }

    /**
     *
     * @return {@code double}
     */
    public final double w() {
      return this.w;
    }

    /**
     *
     * @param _w {@code double}
     */
    public final void w(
        final double _w) {
      this.w = _w;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final Color color() {
      return this.color;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void color(
        final Color _color) {
      this.color = _color;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void color(
        final int _red,
        final int _green,
        final int _blue) {
      if (this.color == null) {
        this.color = new Color(
          _red,
          _green, 
          _blue
        );
      } else {
        this.color.red(_red);
        this.color.green(_green);
        this.color.blue(_blue);
      }
    }

    /**
     *
     * @return {@code Vertex}
     */
    public final Vertex vertex() {
      return this;
    }

    /**
     *
     * @param _vertex {@code Vertex}
     */
    public final void vertex(
        final Vertex _vertex){
      this.vertex(
        _vertex.x(),
        _vertex.y(),
        _vertex.z(),
        _vertex.w(),
        _vertex.color()
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public final void vertex(
        final double _x,
        final double _y,
        final double _z){
      this.vertex(
        _x,
        _y,
        _z,
        this.w(),
        this.color()
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _color {@code Color}
     */
    public final void vertex(
        final double _x,
        final double _y,
        final double _z,
        final Color _color){
      this.vertex(
        _x,
        _y,
        _z,
        this.w(),
        _color
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     */
    public final void vertex(
        final double _x,
        final double _y,
        final double _z,
        final double _w){
      this.vertex(
        _x,
        _y,
        _z,
        _w,
        this.color()
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     * @param _color {@code Color}
     */
    public final void vertex(
        final double _x,
        final double _y,
        final double _z,
        final double _w,
        final Color _color){
      this.x = _x;
      this.y = _y;
      this.z = _z;
      this.w =_w;
      this.color = _color;
    }

    /**
     *
     * {@inheritDoc}
     */
    public final void scale(
        final double _factor) {
      this.x(this.x * _factor);
      this.y(this.y * _factor);
      this.z(this.z * _factor);
    }

    /**
     *
     * {@inheritDoc}
     */
    public final void scale(
        final Vector _vector) {
      this.x(this.x * _vector.x());
      this.y(this.y * _vector.y());
      this.z(this.z * _vector.z());
    }




    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix) {
        rotationMatrix.multiply(this);
    }

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
     * @param vector translation vector
     */
    public void translate(Vertex vector) {
        this.x+=vector.x;
        this.y+=vector.y;
        this.z+=vector.z;
    }

}
