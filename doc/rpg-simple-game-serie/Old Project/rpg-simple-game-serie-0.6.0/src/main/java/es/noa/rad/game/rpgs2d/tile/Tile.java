/**
 * Package: es.noa.rad.game.rpgs2d.tile.
 */
package es.noa.rad.game.rpgs2d.tile;

import java.awt.image.BufferedImage;

  /**
   *
   */
  public class Tile {

    /**
     *
     */
    private BufferedImage image;

    /**
     *
     */
    private boolean collisionable;

    /**
     * @param _image {@code BufferedImage}
     * @param _collisionable {@code boolean}
     */
    public Tile(
        final BufferedImage _image,
        final boolean _collisionable) {
      super();
      this.image = _image;
      this.collisionable = _collisionable;
    }

    /**
     *
     */
    public Tile() {
      super();
      this.image = null;
      this.collisionable = false;
    }

    /**
     * @return {@code BufferedImage}
     */
    public final BufferedImage getImage() {
      return this.image;
    }

    /**
     * @param _image {@code BufferedImage}
     */
    public final void setImage(
        final BufferedImage _image) {
      this.image = _image;
    }


    /**
     * @return {@code boolean}
     */
    public final boolean isCollisionable() {
      return this.collisionable;
    }

    /**
     * @param _collisionable {@code boolean}
     */

    public final void setCollisionable(
        final boolean _collisionable) {
      this.collisionable = _collisionable;
    }

  }
