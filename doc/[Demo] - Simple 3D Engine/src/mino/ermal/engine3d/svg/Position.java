package mino.ermal.engine3d.svg;

import java.util.Objects;

  /**
   *
   */
  public class Position {

    /**
     *
     */
    public static final String TAG_X = "x";

    /**
     *
     */
    public static final String TAG_Y = "y";

    /**
     *
     */
    private int x;

    /**
     *
     */
    private int y;

    /**
     *
     */
    public Position() {
      this(0, 0);
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
     * @param _x {@code int}
     * @param _y {@code int}
     */
    public Position(
        final int _x,
        final int _y) {
      super();
      this.x = _x;
      this.y = _y;
    }

    /**
     *
     * @return {@code int}
     */
    public final int x() {
      return this.x;
    }

    /**
     *
     * @param _x {@code int}
     */
    public final void x(
        final int _x) {
      this.x = _x;
    }

    /**
     *
     * @return {@code int}
     */
    public final int y() {
      return this.y;
    }

    /**
     *
     * @param _y {@code int}
     */
    public final void y(
        final int _y) {
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
     * @param _x {@code int}
     * @param _y {@code int}
     */
    public final void position(
        final int _x,
        final int _y) {
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
        Integer.compare(position.x(), this.x) == 0
     && Integer.compare(position.y(), this.y) == 0;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
      return String.format(
        "Position [x=%d y=%d]",
        this.x(),
        this.y()
      );
    }

  }
