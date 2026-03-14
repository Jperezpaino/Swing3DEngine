package mino.ermal.engine3d.svg;

import java.util.Objects;

  /**
   *
   */
  public class Size {

    /**
     *
     */
    public static final String TAG_WIDTH = "width";

    /**
     *
     */
    public static final String TAG_HEIGHT = "height";

    /**
     *
     */
    private int width;

    /**
     *
     */
    private int height;

    /**
     *
     */
    public Size() {
      this(0, 0);
    }

    /**
     *
     * @param _size {@code Size}
     */
    public Size(
        final Size _size) {
      this(
        _size.width(),
        _size.height()
      );
    }

    /**
     *
     * @param _width {@code int}
     * @param _height {@code int}
     */
    public Size(
        final int _width,
        final int _height) {
      super();
      this.width = _width;
      this.height = _height;
    }

    /**
     *
     * @return {@code int}
     */
    public final int width() {
      return this.width;
    }

    /**
     *
     * @param _width {@code int}
     */
    public final void width(
        final int _width) {
      this.width = _width;
    }

    /**
     *
     * @return {@code int}
     */
    public final int height() {
      return this.height;
    }

    /**
     *
     * @param _height {@code int}
     */
    public final void height(
        final int _height) {
      this.height = _height;
    }

    /**
     *
     * @return {@code Size}
     */
    public final Size size() {
      return this;
    }

    /**
     *
     * @param _size {@code Size}
     */
    public final void size(
        final Size _size) {
      this.size(
        _size.width(),
        _size.height()
      );
    }

    /**
     *
     * @param _width {@code int}
     * @param _height {@code int}
     */
    public final void size(
        final int _width,
        final int _height) {
      this.width = _width;
      this.height = _height;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
      return Objects.hash(
        this.width,
        this.height
      );
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(
        final Object _size) {
      if (this == _size) {
        return true;
      }
      if ((_size == null)
       || (this.getClass() != _size.getClass())) {
        return false;
      }
      final Size size = (Size) _size;
      return
        Integer.compare(size.width(), this.width) == 0
     && Integer.compare(size.height(), this.height) == 0;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
      return String.format(
        "Size [width=%d height=%d]",
        this.width(),
        this.height()
      );
    }

  }
