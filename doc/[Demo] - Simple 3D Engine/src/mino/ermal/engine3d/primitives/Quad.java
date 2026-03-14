package mino.ermal.engine3d.primitives;

import mino.ermal.engine3d.interfaces.Colorable;
import mino.ermal.engine3d.interfaces.Renderable;
import mino.ermal.engine3d.interfaces.Transformable;
import mino.ermal.engine3d.matrix.RotationMatrix3D;
import mino.ermal.engine3d.util.Vector;

  /**
   *
   */
  public class Quad
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
    private Vertex vertex4;

    /**
     *
     */
    private Color color;

    /**
     *
     */
    public Quad(){
      this(
        new Vertex(),
        new Vertex(),
        new Vertex(),
        new Vertex()
      );
    }

    /**
     * 
     * @param _quad {@code Quad}
     */
    public Quad(
        final Quad _quad){
      this(
        _quad.vertex1(),
        _quad.vertex2(),
        _quad.vertex3(),
        _quad.vertex4(),
        _quad.color()
      );
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     * @param _vertex2 {@code Vertex}
     * @param _vertex3 {@code Vertex}
     * @param _vertex4 {@code Vertex}
     */
    public Quad(
        final Vertex _vertex1,
        final Vertex _vertex2,
        final Vertex _vertex3,
        final Vertex _vertex4){
      this(
        _vertex1,
        _vertex2,
        _vertex3,
        _vertex4,
        new Color(Color.NO_COLOR)
      );
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     * @param _vertex2 {@code Vertex}
     * @param _vertex3 {@code Vertex}
     * @param _vertex4 {@code Vertex}
     * @param _color {@code Color}
     */
    public Quad(
        final Vertex _vertex1,
        final Vertex _vertex2,
        final Vertex _vertex3,
        final Vertex _vertex4,
        final Color _color){
      this.vertex1 = _vertex1;
      this.vertex2 = _vertex2;
      this.vertex3 = _vertex3;
      this.vertex4 = _vertex4;
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
     * @return {@code Vertex}
     */
    public final Vertex vertex4() {
      return this.vertex4;
    }

    /**
     *
     * @param _vertex4 {@code Vertex}
     */
    public final void vertex4(
        final Vertex _vertex4) {
      this.vertex4 = _vertex4;
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public final void vertex4(
        final double _x,
        final double _y,
        final double _z) {
      if (this.vertex4 == null) {
        this.vertex4 = new Vertex(
          _x,
          _y, 
          _z
        );
      } else {
        this.vertex4().x(_x);
        this.vertex4().y(_y);
        this.vertex4().z(_z);
      }
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     */
    public final void vertex4(
        final double _x,
        final double _y,
        final double _z,
        final double _w){
      if (this.vertex4 == null) {
        this.vertex4 = new Vertex(
          _x,
          _y, 
          _z,
          _w
        );
      } else {
        this.vertex4().x(_x);
        this.vertex4().y(_y);
        this.vertex4().z(_z);
        this.vertex4().w(_w);
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
     * @return {@code Quad}
     */
    public final Quad quad(){
      return this;
    }

    /**
     *
     * @param _quad {@code Quad}
     */
    public final void quad(
        final Quad _quad){
      this.quad(
        _quad.vertex1(),
        _quad.vertex2(),
        _quad.vertex3(),
        _quad.vertex4(),
        _quad.color()
      );
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     * @param _vertex2 {@code Vertex}
     * @param _vertex3 {@code Vertex}
     * @param _vertex4 {@code Vertex}
     */
    public final void quad(
        final Vertex _vertex1,
        final Vertex _vertex2,
        final Vertex _vertex3,
        final Vertex _vertex4){
      this.quad(
        _vertex1,
        _vertex2,
        _vertex3,
        _vertex4,
        this.color()
      );
    }

    /**
     *
     * @param _vertex1 {@code Vertex}
     * @param _vertex2 {@code Vertex}
     * @param _vertex3 {@code Vertex}
     * @param _vertex4 {@code Vertex}
     * @param _color {@code Color}
     */
    public final void quad(
        final Vertex _vertex1,
        final Vertex _vertex2,
        final Vertex _vertex3,
        final Vertex _vertex4,
        final Color _color){
      this.vertex1 = _vertex1;
      this.vertex2 = _vertex2;
      this.vertex4 = _vertex4;
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
      this.vertex4().scale(_factor);
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
      this.vertex4().scale(_vector);
    }






    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix) {
        vertex1.rotate(rotationMatrix);
        vertex2.rotate(rotationMatrix);
        vertex3.rotate(rotationMatrix);
        vertex4.rotate(rotationMatrix);
    }

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
     * @param vector translation vector
     */
    public void translate(Vertex vector) {
        vertex1.translate(vector);
        vertex2.translate(vector);
        vertex3.translate(vector);
        vertex4.translate(vector);
    }

}
