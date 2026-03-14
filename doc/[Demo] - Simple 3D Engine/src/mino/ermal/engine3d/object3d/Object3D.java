package mino.ermal.engine3d.object3d;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import mino.ermal.engine3d.interfaces.Colorable;
import mino.ermal.engine3d.interfaces.Renderable;
import mino.ermal.engine3d.interfaces.Transformable;
import mino.ermal.engine3d.matrix.RotationMatrix3D;
import mino.ermal.engine3d.primitives.Color;
import mino.ermal.engine3d.primitives.Vertex;
import mino.ermal.engine3d.shapes.CoordinateAxis;
import mino.ermal.engine3d.util.Vector;

  public class Object3D
      implements Renderable,
                 Colorable,
                 Transformable {

    /**
     *
     */
    private List<Renderable> entities;

    /**
     *
     */
    private Color color;

    /**
     *
     */
    public Object3D() {
      this.entities = new ArrayList<Renderable>();
      this.color = new Color(Color.NO_COLOR);
    }

    /**
     *
     * @param _entities {@code List<Renderable>}
     */
    public Object3D(
        final List<Renderable> _entities){
      this.entities = _entities;
    }

    /**
     *
     * @return {@code List<Renderable>}
     */
    public final List<Renderable> entities() {
      return this.entities;
    }

    /**
     *
     * @param _index {@code int}
     * @return {@code Renderable}
     */
    public final Renderable entity(
        final int _index) {
      return this.entities.get(_index);
    }
    
    /**
     *
     * @param _entities {@code List<Renderable>}
     */
    public final void entities(
        final List<Renderable> _entities) {
      this.entities = _entities;
    }

    /**
     *
     * @param _entity {@code Renderable}
     */
    public final void entity(
        final Renderable _entity) {
      this.entities.add(_entity);
    }

    /**
     *
     * @param _index {@code int}
     * @param _entity {@code Renderable}
     */
    public final void entity(
        final int _index,
        final Renderable _entity) {
      this.entities.set(_index, _entity);
    }

    /**
     *
     * @param _entities {@code List<Renderable>}
     */
    public final void add(
        final Collection<Renderable> _entities) {
      this.entities.addAll(_entities);
    }

    /**
     *
     * @param _entity {@code Renderable}
     */
    public final void add(
        final Renderable _entity) {
      this.entities.add(_entity);
    }

    /**
     *
     * @param _entities {@code List<Renderable>}
     */
    public final void remove(
        final Collection<Renderable> _entities) {
      this.entities.removeAll(_entities);
    }

    /**
     *
     * @param _entity {@code Renderable}
     */
    public final void remove(
        final Renderable _entity) {
      this.entities.remove(_entity);
    }

    /**
     *
     */
    public final void clear() {
        this.entities.clear();
    }

    /**
     *
     * @return {@code int}
     */
    public final int size() {
      return this.entities.size();
    }

    /**
     *
     * @param _entity {@code Renderable}
     * @return {@code boolean}
     */
    public final boolean contains(
        final Renderable _entity) {
      return this.entities.contains(_entity);
    }

    /**
     *
     * @return {@code boolean}
     */
    public final boolean isEmpty() {
      return this.entities.isEmpty();
    }

    /**
     *
     * @return {@code Iterator<Renderable>}
     */
    public final Iterator<Renderable> iterator() {
      return this.entities.iterator();
    }

    /**
     *
     * @return {@code Renderable[]}
     */
    public final Renderable[] toArray() {
      return this.entities.toArray(new Renderable[0]);
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
     * {@inheritDoc}
     */
    public final void scale(
        final double _factor) {
      final Iterator<Renderable> iterator = this.iterator();
      while (iterator.hasNext()) {
        final Renderable entity = iterator.next();
        if (entity instanceof Transformable) {
          ((Transformable) entity).scale(_factor);
        }
      }
      if (coordinateAxis!=null) {
        coordinateAxis.scale(_factor);
      }
    }

    /**
     *
     * {@inheritDoc}
     */
    public final void scale(
        final Vector _vector) {
      final Iterator<Renderable> iterator = this.iterator();
      while (iterator.hasNext()) {
        final Renderable entity = iterator.next();
        if (entity instanceof Transformable) {
          ((Transformable) entity).scale(_vector);
        }
      }
      if (coordinateAxis!=null) {
        coordinateAxis.scale(_vector);
      }
    }













    public void rotate(
        final RotationMatrix3D rotationMatrix) {
      final Iterator<Renderable> iterator = this.iterator();
      while (iterator.hasNext()) {
        final Renderable entity = iterator.next();
        if (entity instanceof Transformable) {
          ((Transformable) entity).rotate(rotationMatrix);
        }
      }
      if(coordinateAxis!=null) {
        coordinateAxis.rotate(rotationMatrix);
      }
    }

    public void translate(
        final Vertex vector) {
      final Iterator<Renderable> iterator = this.iterator();
      while (iterator.hasNext()) {
        final Renderable entity = iterator.next();
        if (entity instanceof Transformable) {
          ((Transformable) entity).translate(vector);
        }
      }
      if(coordinateAxis!=null) {
        coordinateAxis.translate(vector);
      }
    }



//////primitive set that make up the object
////public Vector transformables;
////
//////coordinate axis unit vectors (used to render the object relative coordinates)
private CoordinateAxis coordinateAxis;
////
////    /**
////     * Adds a new primitive to the actual set
////     * @param transformable to be added
////     */
////    public void add(Transformable transformable){
////		transformables.add(transformable);
////	}
////
////    /**
////     * @see mino.ermal.engine3d.interfaces.Transformable#scale(double)
////     * @param factor scale factor
////     */
////    public void scale(double factor) {
////		for(int i=0;i<transformables.size();i++){
////			((Transformable)transformables.get(i)).scale(factor);
////		}
////        if(coordinateAxis!=null) coordinateAxis.scale(factor);
////    }
////
////    /**
////     * @see mino.ermal.engine3d.interfaces.Transformable#scale(mino.ermal.engine3d.primitives.Vertex)
////     * @param vector Vertex value
////     */
////    public void scale(Vertex vector) {
////		for(int i=0;i<transformables.size();i++){
////			((Transformable)transformables.get(i)).scale(vector);
////		}
////        if(coordinateAxis!=null) coordinateAxis.scale(vector);
////    }
////
////    /**
////     * @see mino.ermal.engine3d.interfaces.Transformable#rotate(mino.ermal.engine3d.matrix.RotationMatrix3D)
////     * @param rotationMatrix rotation matrix
////     */
////    public void rotate(RotationMatrix3D rotationMatrix) {
////		for(int i=0;i<transformables.size();i++){
////			((Transformable)transformables.get(i)).rotate(rotationMatrix);
////		}
////        if(coordinateAxis!=null) coordinateAxis.rotate(rotationMatrix);
////    }
//
//    /**
//     * @see mino.ermal.engine3d.interfaces.Transformable#translate(mino.ermal.engine3d.primitives.Vertex)
//     * @param vector translation vector
//     */
//    public void translate(Vertex vector) {
//		for(int i=0;i<transformables.size();i++){
//			((Transformable)transformables.get(i)).translate(vector);
//		}
//        if(coordinateAxis!=null) coordinateAxis.translate(vector);
//    }

    /**
     * Adds the coordinate axis with length given by length parameter
     * @param length length of the coordinate axes
     */
    public void addCoordinateAxis(double length) {
        this.coordinateAxis = new CoordinateAxis(length);
    }

    /**
     * Returns the coordinate axis object
     * @return CoordinateAxis object
     */
    public CoordinateAxis getCoordinateAxis() {
        return coordinateAxis;
    }
}
