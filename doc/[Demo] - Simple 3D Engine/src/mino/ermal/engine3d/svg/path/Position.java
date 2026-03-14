package mino.ermal.engine3d.svg.path;

import java.util.Objects;

  /**
   *
   */
  public class Position {

    /**
     *
     */
    private float x;

    /**
     *
     */
    private float y;

    /**
     *
     */
    public Position() {
      this(0.0f, 0.0f);
    }

    /**
     *
     * @param _position {@code Position}
     */
    public Position(
        final Position _position) {
      this(
        _position.x(),
        _position.y()
      );
    }

    /**
     *
     * @param _x {@code float}
     * @param _y {@code float}
     */
    public Position(
        final float _x,
        final float _y) {
      super();
      this.x = _x;
      this.y = _y;
    }

    /**
     *
     * @return {@code float}
     */
    public final float x() {
      return this.x;
    }

    /**
     *
     * @param _x {@code float}
     */
    public final void x(
        final float _x) {
      this.x = _x;
    }

    /**
     *
     * @return {@code float}
     */
    public final float y() {
      return this.y;
    }

    /**
     *
     * @param _y {@code float}
     */
    public final void y(
        final float _y) {
      this.y = _y;
    }

    /**
     *
     * @return {@code Position}
     */
    public final Position position() {
      return this;
    }

    /**
     *
     * @param _position {@code Position}
     */
    public final void position(
        final Position _position) {
      this.position(
        _position.x(),
        _position.y()
      );
    }

    /**
     *
     * @param _x {@code float}
     * @param _y {@code float}
     */
    public final void position(
        final float _x,
        final float _y) {
      this.x = _x;
      this.y = _y;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
      return Objects.hash(
        this.x,
        this.y
      );
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(
        final Object _position) {
      if (this == _position) {
        return true;
      }
      if ((_position == null)
       || (this.getClass() != _position.getClass())) {
        return false;
      }
      final Position position = (Position) _position;
      return
        Float.compare(position.x(), this.x) == 0
     && Float.compare(position.y(), this.y) == 0;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
      return String.format(
        "Position [x=%.2f y=%.2f]",
        this.x(),
        this.y()
      );
    }

  }
