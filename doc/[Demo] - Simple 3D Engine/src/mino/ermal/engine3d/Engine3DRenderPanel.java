package mino.ermal.engine3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

import mino.ermal.engine3d.interfaces.Colorable;
import mino.ermal.engine3d.interfaces.Transformable;
import mino.ermal.engine3d.matrix.RotationMatrix3D;
import mino.ermal.engine3d.object3d.Object3D;
import mino.ermal.engine3d.object3d.World;
import mino.ermal.engine3d.primitives.Vertex;
import mino.ermal.engine3d.renderer.D3Renderer;
import mino.ermal.engine3d.renderer.zbuffer.ZBufferRenderer;
import mino.ermal.engine3d.shapes.Cylinder;
import mino.ermal.engine3d.util.Vector;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 18.25.31
 *
 * This panel is where the drawing takes place.
 * It implements Transformable and D3Renderer to make it straight forward
 * for whatever object uses this panel to execute transformations and rendering.
 * It can be integrated to whatever user interface we want.
 *
 */
public class Engine3DRenderPanel extends JPanel implements Transformable, Colorable, D3Renderer {

    //map containing 3d objects
    private final Map objectList=new LinkedHashMap();
    private World world; //world object to put 3d objects here before rendering
    private D3Renderer renderer; //the renderer

    /**
     * Creates a new 3DRenderer Panel
     */
    public Engine3DRenderPanel() {
        super();
        init();
    }

    /**
     * Initialize 3D objects and put them in a list.
     */
	private void init(){
        Object3D object3d;

        renderer=new ZBufferRenderer(); //create a new Z-buffer renderer
        //set the wireframe color to white
        renderer.setWireframeColor(255,255,255);
        world=new World(); //create a new world object

        //initialize the list of 3d objects
        double waves[][] = {
                { 45D, 50D, 0.02D, 3.5D },
                { 100D, 30D, 0.0070000000000000001D, 5D },
                { -35D, 90D, 0.10000000000000001D, 0.69999999999999996D },
                { -100D, 60D, 0.00040000000000000002D, 20D },
                { 160D, -560D, 0.00069999999999999999D, 20D }
        };

//        object3d = new WaterSurface(1.0D, 1.0D, 10, 10, waves);
//        object3d.scale(200D);
//        object3d.translate(new Vertex(-100D, -100D, 0.0D));
//        object3d.color(0, 255, 255);
//        object3d.addCoordinateAxis(40D);
//        objectList.put("Water", object3d);

//
//        object3d=new Sphere(1,50,50);
//        object3d.scale(90);
//        object3d.color(0,0,255);
//        object3d.addCoordinateAxis(90);
//        objectList.put("Sphere",object3d);
//
//        object3d=new Torus(15,30,5,20);
//        object3d.scale(5);
//        object3d.color(255, 0, 255);
//        object3d.addCoordinateAxis(50);
//        objectList.put("Torus",object3d);
//
        object3d = new Cylinder(0.5, 1, 60);
        object3d.scale(80);
        object3d.color(0,255,0);
        object3d.addCoordinateAxis(80);
        objectList.put("Cylinder",object3d);
        world.add(object3d);
//
//        object3d=new Cube();
//        double cube_scale_factor=200;
//        double cube_translate_factor=-cube_scale_factor/2;
//        object3d.scale(cube_scale_factor);
//        object3d.translate(new Vertex(cube_translate_factor,cube_translate_factor,cube_translate_factor));
//        object3d.addCoordinateAxis(100);
//        objectList.put("Cube",object3d);
//
//        object3d=new Galactic();
//        object3d.scale(2);
//        object3d.addCoordinateAxis(50);
//        objectList.put("Galactic",object3d);

//        object3d = new GridPlane(1.0d, 1.0d, 10, 10);
//        object3d.scale(200D);
//        object3d.translate(new Vertex(-100,-100,0));
//        object3d.color(0, 255, 255);
//        object3d.addCoordinateAxis(100);
//        objectList.put("GridPlane",object3d);
//        world.add(object3d);

//        object3d=new Spiral(300,1,0.1,500);
//        object3d.scale(50);
//        object3d.addCoordinateAxis(30);
//        objectList.put("Spiral",object3d);


    }

    /**
     * Superclass method override.
     * This method creates an offscreen image and renders offscreen first
     * before effectively drawing the result in the panel graphics for speed and
     * image flickering problem
     * @see javax.swing.JPanel#paint(java.awt.Graphics)
     * @param gr Graphics object used to paint
     */
	public void paint(Graphics gr){

        //create an offscreen image of the size of this panel
        Image view=this.createImage(this.getWidth(),this.getHeight());

        //obtain image graphics
        Graphics igr=view.getGraphics();

        //black background in simple 3D rendering looks cool :D
        igr.setColor(Color.black);
        //paint all image with black as background
        igr.fillRect(this.getX(),this.getY(),this.getWidth(),this.getHeight());

        //render in this image
        renderer.render(world,view,this);

        //draw the image on screen
        gr.drawImage(view,0,0,this);
    }

    /**
     * It is invoked to select and change the current rendered object
     * @param objName the key of the object contained in the map
     */
    public void select(String objName){

        //remove previous objects on the world
        world.clear();

        //add the selected object to the world
        world.add((Object3D)objectList.get(objName));

        //refresh the painting
        repaint();
	}

    /**
     * Gets the iterator object of the key set of the map containing the objects.
     * This is useful to the control panel that will be using this one to know the objects
     * contained herein
     * @return the key set iterator object
     */
    public Iterator getShapeList(){
		return objectList.keySet().iterator();
	}

    /**
     * refresh rendering on resize when no changes occur to the objects
     */
    public void render() {
        repaint();
    }


    /*
    the following methods are the implementations of the interfaces
    Transformable and D3Renderer
     */

    public void scale(double factor) {
		world.scale(factor);
		repaint();
	}

	public void scale(Vector vector) {
		world.scale(vector);
		repaint();
	}

	public void rotate(RotationMatrix3D rotationMatrix) {
		world.rotate(rotationMatrix);
		repaint();
	}

	public void translate(Vertex vector) {
		world.translate(vector);
		repaint();
	}

    public void setColor(int r, int g, int b) {
        //not needed
    }

    public void render(World world, Image view, ImageObserver observer) {
        //not needed
    }

    public void setWireframeColor(int r, int g, int b) {
        renderer.setWireframeColor(r,g,b);
    }

    
    public void setRenderAxis(boolean renderAxis) {
        renderer.setRenderAxis(renderAxis);
    }
    public boolean getRenderAxis() {
        return renderer.getRenderAxis();
    }


    public void setWireframed(boolean wireframed) {
        renderer.setWireframed(wireframed);
    }
    public boolean getWireframed() {
        return renderer.getWireframed();
    }


    public void setFilled(boolean filled) {
        renderer.setFilled(filled);
    }
    public boolean getFilled() {
        return renderer.getFilled();
    }

    @Override
    public mino.ermal.engine3d.primitives.Color color() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void color(mino.ermal.engine3d.primitives.Color _color) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void color(int _red, int _greeen, int _blue) {
        // TODO Auto-generated method stub
        
    }

}
