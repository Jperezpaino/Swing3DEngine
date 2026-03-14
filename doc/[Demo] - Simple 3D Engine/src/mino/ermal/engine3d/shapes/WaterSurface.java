package mino.ermal.engine3d.shapes;

import mino.ermal.engine3d.primitives.Triangle;
import mino.ermal.engine3d.primitives.Vertex;
import mino.ermal.engine3d.util.Vector;
import mino.ermal.engine3d.util.VectorUtil;

  public class WaterSurface
      extends GridPlane {

//    /**
//     *
//     * @param _width {@code double}
//     * @param _height {@code double}
//     * @param _widthSegments {@code int}
//     * @param _heightSegments {@code int}
//     */
//    public WaterSurface(
//        final double _width,
//        final double _height,
//        final int _widthSegments,
//        final int _heightSegments,
//        final double _waves[][]) {
//      final double widthDelta= (_width / _widthSegments);
//      final double heightDelta= (_height / _heightSegments);
//
//      for (int x = 0; x < _widthSegments; x++) {
//        for (int y = 0; y < _heightSegments; y++) {
//          final Vertex vertex1T1 = new Vertex((x * widthDelta), (y * heightDelta), 0);
//          this.calculateDepth(vertex1T1, _waves);
//          final Vertex vertex2T1 = new Vertex((x * widthDelta), ((y + 1) * heightDelta), 0);
//          this.calculateDepth(vertex2T1, _waves);
//          final Vertex vertex3T1 = new Vertex(((x + 1) * widthDelta), ((y + 1) * heightDelta), 0);
//          this.calculateDepth(vertex3T1, _waves);
//          super.add(
//            new Triangle(
//              vertex1T1,
//              vertex2T1,
//              vertex3T1
//            )
//          );
//          final Vertex vertex1T2 = new Vertex((x * widthDelta), (y * heightDelta), 0);
//          this.calculateDepth(vertex1T2, _waves);
//          final Vertex vertex2T2 = new Vertex(((x + 1) * widthDelta), (y * heightDelta), 0);
//          this.calculateDepth(vertex2T2, _waves);
//          final Vertex vertex3T2 = new Vertex(((x + 1) * widthDelta), ((y + 1) * heightDelta), 0);
//          this.calculateDepth(vertex3T2, _waves);
//          super.add(
//            new Triangle(
//              vertex1T2,
//              vertex2T2,
//              vertex3T2
//            )
//          );
//        }
//      }
//    }
//
//    private final void calculateDepth(
//        final Vertex _vertex,
//        final double _waves[][]) {
//      double sum = 0.0D;
//      Vector vector = new Vector (_vertex.x(), _vertex.y(), _vertex.z());
//      for (int i = 0; i < _waves.length; i++) {
//        sum += _waves[i][2] * Math.cos(6.2831853071795862D * _waves[i][3] * VectorUtil.distance(vector, new Vector(_waves[i][0], _waves[i][1], 0.0D)));
//      }
//      _vertex.z(sum);
//    }
    
    public WaterSurface(double u, double v, int detailU, int detailV, double waves[][]) {
        super(u, v, detailU, detailV);
        for(int i = 0; i < super.size(); i++) {
            Triangle tri = (Triangle)super.entity(i);
            calculateDepth(tri.vertex1(), waves);
            calculateDepth(tri.vertex2(), waves);
            calculateDepth(tri.vertex3(), waves);
        }
    }

    private void calculateDepth(Vertex v, double waves[][]) {
        double sum = 0.0D;
        Vector vector = new Vector (v.x(), v.y(), v.z());
        for(int i = 0; i < waves.length; i++) {
            sum += waves[i][2] * Math.cos(6.2831853071795862D * waves[i][3] * VectorUtil.distance(vector, new Vector(waves[i][0], waves[i][1], 0.0D)));
        }
        v.z(sum);
    }
}

