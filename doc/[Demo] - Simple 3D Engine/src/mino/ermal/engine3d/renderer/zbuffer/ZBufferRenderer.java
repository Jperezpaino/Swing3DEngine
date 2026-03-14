package mino.ermal.engine3d.renderer.zbuffer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import mino.ermal.engine3d.object3d.EdgeBasedObject3D;
import mino.ermal.engine3d.object3d.Object3D;
import mino.ermal.engine3d.object3d.World;
import mino.ermal.engine3d.primitives.Color;
import mino.ermal.engine3d.primitives.Edge;
import mino.ermal.engine3d.primitives.Quad;
import mino.ermal.engine3d.primitives.Triangle;
import mino.ermal.engine3d.renderer.D3Renderer;
import mino.ermal.engine3d.util.ColorUtil;
import mino.ermal.engine3d.util.Vector;
import mino.ermal.engine3d.util.VectorUtil;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Sep 30, 2007
 * Time: 4:41:48 PM
 *
 * Z-Buffer based renderer
 *
 */
public class ZBufferRenderer implements D3Renderer {

    //perspective division constant
    private static final double D =400;

    private Color wireframeColor = new Color(255,0,0);

    private boolean renderAxis=false; //render axis flag
    private boolean wireframed=false; //render wireframe flag
    private boolean filled=true; //render filled flag

    private final void renderEdgeBased(
        final Object3D _object,
        final Image _view,
        final ImageObserver _observer,
        final int[][] _zbuffer) {
      final Graphics graphics = _view.getGraphics();
      final int width = _view.getWidth(_observer);
      final int height = _view.getHeight(_observer);

      for (int i = 0; _object != null && i < _object.size(); i++) {
        final Edge edge = (Edge) _object.entity(i);

        /* Establecemos el color con el que pintar la entidad. */
        graphics.setColor(ColorUtil.color(edge.color()));

        /* 
         * Calculamos la transformación segun la perspectiva para todos los
         * vértices y para las coordenadas 'x', 'y' y 'z'.
         */
        int[] x = new int[2];
        x[0] = ((int) ((edge.vertexStart().x() / (1 + edge.vertexStart().z() / D)) + (width / 2)));
        x[1] = ((int) ((edge.vertexEnd().x() / (1 + edge.vertexEnd().z() / D)) + (width / 2)));

        int[] y = new int[2];
        y[0] = ((int) ((edge.vertexStart().y() / (1 + edge.vertexStart().z() / D)) + (height / 2)));
        y[1] = ((int) ((edge.vertexEnd().y() / (1 + edge.vertexEnd().z() / D)) + (height / 2)));

        int[] z = new int[2];
        z[0] = ((int) (edge.vertexStart().z()));
        z[1] = ((int) (edge.vertexEnd().z()));
        
        /* Dibujamos borde. */
        ZbufferUtil.drawLine(graphics, x[0], y[0], z[0], x[1], y[1], z[1], _zbuffer);
      }

    }

    private final void renderTriangleBased(
        final Object3D _object,
        final Image _view,
        final ImageObserver _observer,
        final int[][] _zbuffer) {
      final Graphics graphics = _view.getGraphics();
      final int width = _view.getWidth(_observer);
      final int height = _view.getHeight(_observer);

      for (int i = 0; _object != null && i < _object.size(); i++) {
        final Triangle triangle = (Triangle) _object.entity(i);

        /*
         * Obtenemos la normal del triangulo para el calculo de la iluminación.
         */
        final Vector normal = VectorUtil.computeNormal(triangle);

        /*
         * Calculamos el coseno entre la normal del triangulo y el vector de
         * dirección de la luz para calcular la intensidad del color en función
         * del valor.
         */
        final double lighting = VectorUtil.cosAngle(
          normal,
          new Vector(
            Math.cos(Math.PI/2),
            (Math.cos(Math.PI/2) / 2),
            Math.cos(Math.PI/2)
          )
        );

        Color color = triangle.color();
        if (color.equals(Color.NO_COLOR)) {
          color = _object.color();
        }

        /* Establecemos el color con el que pintar la entidad. */
        graphics.setColor(ColorUtil.color(ColorUtil.lighting(color, lighting)));

        /* 
         * Calculamos la transformación segun la perspectiva para todos los
         * vértices y para las coordenadas 'x', 'y' y 'z'.
         */
        int[] x = new int[3];
        x[0] = ((int) ((triangle.vertex1().x() / (1 + triangle.vertex1().z() / D)) + (width / 2)));
        x[1] = ((int) ((triangle.vertex2().x() / (1 + triangle.vertex2().z() / D)) + (width / 2)));
        x[2] = ((int) ((triangle.vertex3().x() / (1 + triangle.vertex3().z() / D)) + (width / 2)));

        int[] y = new int[3];
        y[0] = ((int) ((triangle.vertex1().y() / (1 + triangle.vertex1().z() / D)) + (height / 2)));
        y[1] = ((int) ((triangle.vertex2().y() / (1 + triangle.vertex2().z() / D)) + (height / 2)));
        y[2] = ((int) ((triangle.vertex3().y() / (1 + triangle.vertex3().z() / D)) + (height / 2)));

        int[] z = new int[3];
        z[0] = ((int) (triangle.vertex1().z()));
        z[1] = ((int) (triangle.vertex2().z()));
        z[2] = ((int) (triangle.vertex3().z()));

        /*
         * Si el indicador 'filled' es true, renderizamos los triángulos
         * rellenos.
         */
        if (this.filled) {
          ZbufferUtil.fillTriangle(graphics, x, y, z, _zbuffer);
        }

        /*
         * Si el indicador 'wireframed' es true, renderizamos los bordes de los
         * triángulos.
         */
        if (wireframed) {
          graphics.setColor(ColorUtil.color(this.wireframeColor));
          ZbufferUtil.drawLine(graphics,x[0], y[0], z[0], x[1], y[1], z[1], _zbuffer );
          ZbufferUtil.drawLine(graphics,x[1], y[1], z[1], x[2], y[2], z[2], _zbuffer );
          ZbufferUtil.drawLine(graphics,x[2], y[2], z[2], x[0], y[0], z[0], _zbuffer );
        }
      }

    }

    private void renderTriangleBased2(Object3D object3d,Image view, ImageObserver observer,int[][] zbuffer){

        Graphics gr=view.getGraphics(); //obtain graphic object from offscreen image
        
        int w=view.getWidth(observer); //obtain image width
        int h=view.getHeight(observer); //obtain image height

        for(int i=0;i<object3d.entities().size();i++){
            Triangle tri=(Triangle)object3d.entities().get(i);

            //calculate perspective transformation for all vertexes
            //and for x,y and z coordinates
            int[] x=new int[3];
            x[0]=(int)(tri.vertex1().x()/(1+tri.vertex1().z()/D))+w/2;
            x[1]=(int)(tri.vertex2().x()/(1+tri.vertex2().z()/D))+w/2;
            x[2]=(int)(tri.vertex3().x()/(1+tri.vertex3().z()/D))+w/2;

            int[] y=new int[3];
            y[0]=(int)(tri.vertex1().y()/(1+tri.vertex1().z()/D))+h/2;
            y[1]=(int)(tri.vertex2().y()/(1+tri.vertex2().z()/D))+h/2;
            y[2]=(int)(tri.vertex3().y()/(1+tri.vertex3().z()/D))+h/2;

            int[] z=new int[3];
            z[0]=(int)tri.vertex1().z();
            z[1]=(int)tri.vertex2().z();
            z[2]=(int)tri.vertex3().z();

            //obtains the normal vector of the triangle
            Vector normale= VectorUtil.computeNormal(tri);


            /*
            calculates cos between triangle normal and light direction vector
            and calculate color intensity in function of cos value
             */
            double cos= VectorUtil.cosAngle(normale,new Vector(Math.cos(Math.PI/2),Math.cos(Math.PI/2)/2,Math.cos(Math.PI/2)));

            int r,g,b;
            cos=Math.abs(cos);
            r=(int)(cos*tri.color().red());
            g=(int)(cos*tri.color().green());
            b=(int)(cos*tri.color().blue());

            //set graphic color
            gr.setColor(new java.awt.Color(r,g,b));

            //if filled flag is true render filled triangles
            if(filled) ZbufferUtil.fillTriangle(gr,x,y,z,zbuffer);

            //if wireframed flag is true draw triangle edges
            if(wireframed) {
                gr.setColor(ColorUtil.color(this.wireframeColor));
                ZbufferUtil.drawLine(gr,x[0], y[0], z[0], x[1], y[1], z[1], zbuffer );
                ZbufferUtil.drawLine(gr,x[1], y[1], z[1], x[2], y[2], z[2], zbuffer );
                ZbufferUtil.drawLine(gr,x[2], y[2], z[2], x[0], y[0], z[0], zbuffer );
            }

    }

}

    private final void renderQuadBased(
        final Object3D _object,
        final Image _view,
        final ImageObserver _observer,
        final int[][] _zbuffer) {
      final Graphics graphics = _view.getGraphics();
      final int width = _view.getWidth(_observer);
      final int height = _view.getHeight(_observer);

      for (int i = 0; _object != null && i < _object.size(); i++) {
        final Quad quad = (Quad) _object.entity(i);

        /*
         * Obtenemos la normal del triangulo para el calculo de la iluminación.
         */
        final Vector normal = VectorUtil.computeNormal(quad);

        /*
         * Calculamos el coseno entre la normal del cuadrado y el vector de
         * dirección de la luz para calcular la intensidad del color en función
         * del valor.
         */
        final double lighting = VectorUtil.cosAngle(
          normal,
          new Vector(
            Math.cos(Math.PI/2),
            (Math.cos(Math.PI/2) / 2),
            Math.cos(Math.PI/2)
          )
        );

        Color color = quad.color();
        if (color.equals(Color.NO_COLOR)) {
          color = _object.color();
        }

        /* Establecemos el color con el que pintar la entidad. */
        graphics.setColor(ColorUtil.color(ColorUtil.lighting(color, lighting)));

        /* 
         * Calculamos la transformación segun la perspectiva para todos los
         * vértices y para las coordenadas 'x', 'y' y 'z'.
         */
        int[] x = new int[3];
        x[0] = ((int) ((quad.vertex1().x() / (1 + quad.vertex1().z() / D)) + (width / 2)));
        x[1] = ((int) ((quad.vertex2().x() / (1 + quad.vertex2().z() / D)) + (width / 2)));
        x[2] = ((int) ((quad.vertex3().x() / (1 + quad.vertex3().z() / D)) + (width / 2)));

        int[] y = new int[3];
        y[0] = ((int) ((quad.vertex1().y() / (1 + quad.vertex1().z() / D)) + (height / 2)));
        y[1] = ((int) ((quad.vertex2().y() / (1 + quad.vertex2().z() / D)) + (height / 2)));
        y[2] = ((int) ((quad.vertex3().y() / (1 + quad.vertex3().z() / D)) + (height / 2)));

        int[] z = new int[3];
        z[0] = ((int) (quad.vertex1().z()));
        z[1] = ((int) (quad.vertex2().z()));
        z[2] = ((int) (quad.vertex3().z()));

        /*
         * Si el indicador 'filled' es true, renderizamos los triángulos
         * rellenos.
         */
        if (this.filled) {
          ZbufferUtil.fillTriangle(graphics, x, y, z, _zbuffer);
        }

        /*
         * Si el indicador 'wireframed' es true, renderizamos los bordes de los
         * triángulos.
         */
        if (wireframed) {
          graphics.setColor(ColorUtil.color(this.wireframeColor));
          ZbufferUtil.drawLine(graphics,x[0], y[0], z[0], x[1], y[1], z[1], _zbuffer );
          ZbufferUtil.drawLine(graphics,x[1], y[1], z[1], x[2], y[2], z[2], _zbuffer );
          ZbufferUtil.drawLine(graphics,x[2], y[2], z[2], x[0], y[0], z[0], _zbuffer );
        }
      }

    }




    
    /**
     *
     * @param world world object containing the set of 3d objects
     * @param view offscreen image to render the world in
     * @param observer image observer object necessary to get width and height of the
     */
	public void render(World world,Image view, ImageObserver observer){

        int w=view.getWidth(observer); //obtain image width
        int h=view.getHeight(observer); //obtain image height

        int[][] zbuffer=new int[w][h];
        //initialize zbuffer with tha max depth value
        for(int k=0; k<w; k++){
            for(int n=0; n<h; n++){
                zbuffer[k][n]=65534;
            }
        }
        for(int i=0;i<world.size();i++){
            Object3D object3d=(Object3D)world.entity(i);

            //render the object appropriately according to their type
            if(object3d instanceof EdgeBasedObject3D){
			    renderEdgeBased(object3d,view,observer,zbuffer);
                Object3D obj=object3d.getCoordinateAxis();

                //if render axis flag is true and the object has an axis object then render it
                if(renderAxis && obj!=null) renderEdgeBased(obj,view,observer,zbuffer);
            }else{
                renderTriangleBased(object3d,view,observer,zbuffer);
                Object3D obj=object3d.getCoordinateAxis();

                //if render axis flag is true and the object has an axis object then render it
                if(renderAxis && obj!=null) renderEdgeBased(obj,view,observer,zbuffer);
            }
        }
    }

    /**
     * @see mino.ermal.engine3d.renderer.D3Renderer#setWireframeColor(int, int, int)
     * @param r red component
     * @param g green component
     * @param b blue component
     */
    public void setWireframeColor(int r,int g,int b){
        if(r>=0 && r<256) this.wireframeColor.red(r);
        if(g>=0 && g<256) this.wireframeColor.green(g);
        if(b>=0 && b<256) this.wireframeColor.blue(b);
    }

    /**
     * @see mino.ermal.engine3d.renderer.D3Renderer#setRenderAxis(boolean)
     * @param renderAxis render axis flag
     */
    public void setRenderAxis(boolean renderAxis) {
        this.renderAxis = renderAxis;
    }
    /**
     * @return render axis flag
     */
    public boolean getRenderAxis() {
        return this.renderAxis;
    }

    /**
     * @see mino.ermal.engine3d.renderer.D3Renderer#setWireframed(boolean)
     * @param wireframed render wireframe flag
     */
    public void setWireframed(boolean wireframed) {
        this.wireframed=wireframed;
    }
    /**
     * @return render wireframe flag
     */
    public boolean getWireframed() {
        return this.wireframed;
    }

    /**
     * @see mino.ermal.engine3d.renderer.D3Renderer#setFilled(boolean)
     * @param filled render filled flag
     */
    public void setFilled(boolean filled) {
        this.filled=filled;
    }
    /**
     * @return render filled flag
     */
    public boolean getFilled() {
        return this.filled;
    }
}
