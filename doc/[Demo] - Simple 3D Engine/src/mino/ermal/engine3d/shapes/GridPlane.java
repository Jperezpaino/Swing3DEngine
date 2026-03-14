package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.object3d.TriangleBasedObject3D;
import mino.ermal.engine3d.primitives.Triangle;
import mino.ermal.engine3d.primitives.Vertex;

  /**
   *
   */
  public class GridPlane
      extends TriangleBasedObject3D {

    /**
     *
     * @param _width {@code double}
     * @param _height {@code double}
     * @param _widthSegments {@code int}
     * @param _heightSegments {@code int}
     */
    public GridPlane(
        final double _width,
        final double _height,
        final int _widthSegments,
        final int _heightSegments) {
      final double widthDelta= (_width / _widthSegments);
      final double heightDelta= (_height / _heightSegments);

      for (int x = 0; x < _widthSegments; x++) {
        for (int y = 0; y < _heightSegments; y++) {
          super.add(
            new Triangle(
              new Vertex((x * widthDelta), (y * heightDelta), 0),
              new Vertex((x * widthDelta), ((y + 1) * heightDelta), 0),
              new Vertex(((x + 1) * widthDelta), ((y + 1) * heightDelta), 0)
            )
          );
          super.add(
            new Triangle(
              new Vertex((x * widthDelta), (y * heightDelta), 0),
              new Vertex(((x + 1) * widthDelta), (y * heightDelta), 0),
              new Vertex(((x + 1) * widthDelta), ((y + 1) * heightDelta), 0)
            )
          );
        }
      }
    }

  }
