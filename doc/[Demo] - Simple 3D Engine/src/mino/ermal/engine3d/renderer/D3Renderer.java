package mino.ermal.engine3d.renderer;

import mino.ermal.engine3d.object3d.World;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Sep 30, 2007
 * Time: 4:34:09 PM
 *
 * D3Renderer interface let's us use different renderers for example Zbuffer type
 * renderer and other ones uniformly.
 *
 */
public interface D3Renderer {
    /**
     * This method is called to effectively render a world
     * @param world world object containing the set of 3d objects
     * @param view off screen image to render the world in
     * @param observer image observer object necessary to get width and height of the
     * off screen image which can change in dimension dynamically
     */

    public void render(World world,Image view, ImageObserver observer);

    /**
     * Sets the wire frame color
     * @param r red component
     * @param g green component
     * @param b blue component
     */
    public void setWireframeColor(int r,int g,int b);

    /**
     * Sets the flag whether the coordinate axis has to be rendered or not
     * @param renderAxis render axis flag
     */
    public void setRenderAxis(boolean renderAxis);
    /**
     * @return render axis flag
     */
    public boolean getRenderAxis();

    /**
     * Setter for the wireframed flag. If true it renders also wireframes
     * @param wireframed render wireframe flag
     */
    public void setWireframed(boolean wireframed);
    /**
     * @return render wireframe flag
     */
    public boolean getWireframed();

    /**
     * Setter for the filled flag. If true it renders filled triangles
     * @param filled render filled flag
     */
    public void setFilled(boolean filled);

    /**
     * @return render filled flag
     */
    public boolean getFilled();
}
