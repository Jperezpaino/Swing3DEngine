package mino.ermal.engine3d.primitives;

import mino.ermal.engine3d.interfaces.Colorable;
import mino.ermal.engine3d.interfaces.Renderable;
import mino.ermal.engine3d.interfaces.Transformable;
import mino.ermal.engine3d.matrix.RotationMatrix3D;
import mino.ermal.engine3d.util.Vector;

  /**
   *
   */
  public class Edge
      implements Renderable,
                 Colorable,
                 Transformable {

    /**
     *
     */
    private Vertex vertexStart;

    /**
     *
     */
    private Vertex vertexEnd;

    /**
     *
     */
    private Color color;

    /**
     *
     */
    public Edge(){
      this(
        new Vertex(),
        new Vertex()
      );
    }

    /**
     * 
     * @param _edge {@code Edge}
     */
    public Edge(
        final Edge _edge){
      this(
        _edge.vertexStart(),
        _edge.vertexEnd(),
        _edge.color()
      );
    }

    /**
     *
     * @param _vertexStart {@code Vertex}
     * @param _vertexEnd {@code Vertex}
     */
    public Edge(
        final Vertex _vertexStart,
        final Vertex _vertexEnd){
      this(
        _vertexStart,
        _vertexEnd,
        new Color(Color.NO_COLOR)
      );
    }

    /**
     *
     * @param _vertexStart {@code Vertex}
     * @param _vertexEnd {@code Vertex}
     * @param _color {@code Color}
     */
    public Edge(
        final Vertex _vertexStart,
        final Vertex _vertexEnd,
        final Color _color){
      this.vertexStart = _vertexStart;
      this.vertexEnd = _vertexEnd;
      this.color = _color;
    }

    /**
     *
     * @return {@code Vertex}
     */
    public final Vertex vertexStart() {
      return this.vertexStart;
    }

    /**
     *
     * @param _vertexStart {@code Vertex}
     */
    public final void vertexStart(
        final Vertex _vertexStart) {
      this.vertexStart = _vertexStart;
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public final void vertexStart(
        final double _x,
        final double _y,
        final double _z) {
      if (this.vertexStart == null) {
        this.vertexStart = new Vertex(
          _x,
          _y, 
          _z
        );
      } else {
        this.vertexStart().x(_x);
        this.vertexStart().y(_y);
        this.vertexStart().z(_z);
      }
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     */
    public final void vertexStart(
        final double _x,
        final double _y,
        final double _z,
        final double _w){
      if (this.vertexStart == null) {
        this.vertexStart = new Vertex(
          _x,
          _y, 
          _z,
          _w
        );
      } else {
        this.vertexStart().x(_x);
        this.vertexStart().y(_y);
        this.vertexStart().z(_z);
        this.vertexStart().w(_w);
      }
    }

    /**
     *
     * @return {@code Vertex}
     */
    public final Vertex vertexEnd() {
      return this.vertexEnd;
    }

    /**
     *
     * @param _vertexEnd {@code Vertex}
     */
    public final void vertexEnd(
        final Vertex _vertexEnd) {
      this.vertexEnd = _vertexEnd;
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public final void vertexEnd(
        final double _x,
        final double _y,
        final double _z) {
      if (this.vertexEnd == null) {
        this.vertexEnd = new Vertex(
          _x,
          _y, 
          _z
        );
      } else {
        this.vertexEnd().x(_x);
        this.vertexEnd().y(_y);
        this.vertexEnd().z(_z);
      }
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     * @param _w {@code double}
     */
    public final void vertexEnd(
        final double _x,
        final double _y,
        final double _z,
        final double _w){
      if (this.vertexEnd == null) {
        this.vertexEnd = new Vertex(
          _x,
          _y, 
          _z,
          _w
        );
      } else {
        this.vertexEnd().x(_x);
        this.vertexEnd().y(_y);
        this.vertexEnd().z(_z);
        this.vertexEnd().w(_w);
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
     * @return {@code Edge}
     */
    public final Edge edge(){
      return this;
    }

    /**
     *
     * @param _edge {@code Edge}
     */
    public final void edge(
        final Edge _edge){
      this.edge(
        _edge.vertexStart(),
        _edge.vertexEnd(),
        _edge.color()
      );
    }

    /**
     *
     * @param _vertexStart {@code Vertex}
     * @param _vertexEnd {@code Vertex}
     */
    public final void edge(
        final Vertex _vertexStart,
        final Vertex _vertexEnd,
        final Vertex _vertex3){
      this.edge(
        _vertexStart,
        _vertexEnd,
        this.color()
      );
    }

    /**
     *
     * @param _vertexStart {@code Vertex}
     * @param _vertexEnd {@code Vertex}
     * @param _color {@code Color}
     */
    public final void edge(
        final Vertex _vertexStart,
        final Vertex _vertexEnd,
        final Color _color){
      this.vertexStart = _vertexStart;
      this.vertexEnd = _vertexEnd;
      this.color = _color;
    }

    /**
     *
     * {@inheritDoc}
     */
    public final void scale(
        final double _factor) {
      this.vertexStart().scale(_factor);
      this.vertexEnd().scale(_factor);
    }

    /**
     *
     * {@inheritDoc}
     */
    public final void scale(
        final  Vector _vector) {
      this.vertexStart().scale(_vector);
      this.vertexEnd().scale(_vector);
    }





    
    
    
    
    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
     * @param rotationMatrix rotation matrix
     */
    public void rotate(RotationMatrix3D rotationMatrix) {
        vertexStart.rotate(rotationMatrix);
        vertexEnd.rotate(rotationMatrix);
	}

    /**
     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
     * @param vector translation vector
     */
    public void translate(Vertex vector) {
        vertexStart.translate(vector);
        vertexEnd.translate(vector);
	}

}
