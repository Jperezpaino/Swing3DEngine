package mino.ermal.engine3d.primitives;

import mino.ermal.engine3d.interfaces.Colorable;
import mino.ermal.engine3d.interfaces.Renderable;
import mino.ermal.engine3d.interfaces.Transformable;
import mino.ermal.engine3d.matrix.RotationMatrix3D;
import mino.ermal.engine3d.util.Vector;

  /**
   *
   */
  public class Triangle
      implements Renderable,
                 Colorable,
                 Transformable {

    /**
     *
     */
    private Vertex vertex1;

    /**
     *
     */
    private Vertex vertex2;

    /**
     *
     */
    private Vertex vertex3;

    /**
     *
     */
    private Color color;

    /**
     *
     */
    public Triangle(){
      this(
        new Vertex(),
        new Vertex(),
        new Vertex()
      );
    }

    /**
     * 
     * @param _triangle {@code Triangle}
     */
    public Triangle(
        final Triangle _triangle){
      this(
        _triangle.vertex1(),
        _triangle.vertex2(),
        _triangle.vertex3(),
        _triangle.color()
      );
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     * @param _vertex2 {@code Vertex}
     * @param _vertex3 {@code Vertex}
     */
    public Triangle(
        final Vertex _vertex1,
        final Vertex _vertex2,
        final Vertex _vertex3){
      this(
        _vertex1,
        _vertex2,
        _vertex3,
        new Color(Color.NO_COLOR)
      );
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     * @param _vertex2 {@code Vertex}
     * @param _vertex3 {@code Vertex}
     * @param _color {@code Color}
     */
    public Triangle(
        final Vertex _vertex1,
        final Vertex _vertex2,
        final Vertex _vertex3,
        final Color _color){
      this.vertex1 = _vertex1;
      this.vertex2 = _vertex2;
      this.vertex3 = _vertex3;
      this.color = _color;
    }

    /**
     *
     * @return {@code Vertex}
     */
    public final Vertex vertex1() {
      return this.vertex1;
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     */
    public final void vertex1(
        final Vertex _vertex1) {
      this.vertex1 = _vertex1;
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public final void vertex1(
        final double _x,
        final double _y,
        final double _z) {
      if (this.vertex1 == null) {
        this.vertex1 = new Vertex(
          _x,
          _y, 
          _z
        );
      } else {
        this.vertex1().x(_x);
        this.vertex1().y(_y);
        this.vertex1().z(_z);
      }
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     */
    public final void vertex1(
        final double _x,
        final double _y,
        final double _z,
        final double _w){
      if (this.vertex1 == null) {
        this.vertex1 = new Vertex(
          _x,
          _y, 
          _z,
          _w
        );
      } else {
        this.vertex1().x(_x);
        this.vertex1().y(_y);
        this.vertex1().z(_z);
        this.vertex1().w(_w);
      }
    }

    /**
     *
     * @return {@code Vertex}
     */
    public final Vertex vertex2() {
      return this.vertex2;
    }

    /**
     *
     * @param _vertex2 {@code Vertex}
     */
    public final void vertex2(
        final Vertex _vertex2) {
      this.vertex2 = _vertex2;
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public final void vertex2(
        final double _x,
        final double _y,
        final double _z) {
      if (this.vertex2 == null) {
        this.vertex2 = new Vertex(
          _x,
          _y, 
          _z
        );
      } else {
        this.vertex2().x(_x);
        this.vertex2().y(_y);
        this.vertex2().z(_z);
      }
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     */
    public final void vertex2(
        final double _x,
        final double _y,
        final double _z,
        final double _w){
      if (this.vertex2 == null) {
        this.vertex2 = new Vertex(
          _x,
          _y, 
          _z,
          _w
        );
      } else {
        this.vertex2().x(_x);
        this.vertex2().y(_y);
        this.vertex2().z(_z);
        this.vertex2().w(_w);
      }
    }

    /**
     *
     * @return {@code Vertex}
     */
    public final Vertex vertex3() {
      return this.vertex3;
    }

    /**
     *
     * @param _vertex3 {@code Vertex}
     */
    public final void vertex3(
        final Vertex _vertex3) {
      this.vertex3 = _vertex3;
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public final void vertex3(
        final double _x,
        final double _y,
        final double _z) {
      if (this.vertex3 == null) {
        this.vertex3 = new Vertex(
          _x,
          _y, 
          _z
        );
      } else {
        this.vertex3().x(_x);
        this.vertex3().y(_y);
        this.vertex3().z(_z);
      }
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     */
    public final void vertex3(
        final double _x,
        final double _y,
        final double _z,
        final double _w){
      if (this.vertex3 == null) {
        this.vertex3 = new Vertex(
          _x,
          _y, 
          _z,
          _w
        );
      } else {
        this.vertex3().x(_x);
        this.vertex3().y(_y);
        this.vertex3().z(_z);
        this.vertex3().w(_w);
      }
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
     * @return {@code Triangle}
     */
    public final Triangle triangle(){
      return this;
    }

    /**
     *
     * @param _triangle {@code Triangle}
     */
    public final void triangle(
        final Triangle _triangle){
      this.triangle(
        _triangle.vertex1(),
        _triangle.vertex2(),
        _triangle.vertex3(),
        _triangle.color()
      );
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     * @param _vertex2 {@code Vertex}
     * @param _vertex3 {@code Vertex}
     */
    public final void triangle(
        final Vertex _vertex1,
        final Vertex _vertex2,
        final Vertex _vertex3){
      this.triangle(
        _vertex1,
        _vertex2,
        _vertex3,
        this.color()
      );
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     * @param _vertex2 {@code Vertex}
     * @param _vertex3 {@code Vertex}
     * @param _color {@code Color}
     */
    public final void triangle(
        final Vertex _vertex1,
        final Vertex _vertex2,
        final Vertex _vertex3,
        final Color _color){
      this.vertex1 = _vertex1;
      this.vertex2 = _vertex2;
      this.vertex3 = _vertex3;
      this.color = _color;
    }

    /**
     *
     * {@inheritDoc}
     */
    public final void scale(
        final double _factor) {
      this.vertex1().scale(_factor);
      this.vertex2().scale(_factor);
      this.vertex3().scale(_factor);
    }

    /**
     *
     * {@inheritDoc}
     */
    public final void scale(
        final Vector _vector) {
      this.vertex1().scale(_vector);
      this.vertex2().scale(_vector);
      this.vertex3().scale(_vector);
    }






    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix) {
        vertex1.rotate(rotationMatrix);
        vertex2.rotate(rotationMatrix);
        vertex3.rotate(rotationMatrix);
    }

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
     * @param vector translation vector
     */
    public void translate(Vertex vector) {
        vertex1.translate(vector);
        vertex2.translate(vector);
        vertex3.translate(vector);
    }

}
