package mino.ermal.engine3d.svg;

import java.util.Objects;

import mino.ermal.engine3d.svg.interfaces.SvgTag;

  /**
   *
   */
  public class ViewBox
      implements SvgTag {

    /**
     *
     */
    public static final String TAG_VIEWBOX = "viewBox";

    /**
     *
     */
    private Position position;

    /**
     *
     */
    private Size size;

    /**
     *
     */
    public ViewBox() {
      this(
        new Position(),
        new Size()
      );
    }

    /**
     *
     * @param _viewBox {@code ViewBox}
     */
    public ViewBox(
        final ViewBox _viewBox) {
      this(
        _viewBox.position(),
        _viewBox.size()
      );
    }

    /**
     *
     * @param _position {@code Position}
     * @param _size {@code Size}
     */
    public ViewBox(
        final Position _position,
        final Size _size) {
      super();
      this.position = _position;
      this.size = _size;
    }

    /**
     *
     * @return {@code Position}
     */
    public final Position position() {
      return this.position;
    }

    /**
     *
     * @param _position {@code Position}
     */
    public final void position(
        final Position _position) {
      this.position = _position;
    }

    /**
     *
     * @return {@code Size}
     */
    public final Size size() {
      return this.size;
    }

    /**
     *
     * @param _size {@code Size}
     */
    public final void size(
        final Size _size) {
      this.size = _size;
    }

    /**
     *
     * @return {@code ViewBox}
     */
    public final ViewBox viewBox() {
      return this;
    }

    /**
     *
     * @param _viewBox {@code ViewBox}
     */
    public final void viewBox(
        final ViewBox _viewBox) {
      this.viewBox(
        _viewBox.position(),
        _viewBox.size()
      );
    }

    /**
     *
     * @param _position {@code Position}
     * @param _size {@code Size}
     */
    public final void viewBox(
        final Position _position,
        final Size _size) {
      this.position = _position;
      this.size = _size;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
      return Objects.hash(
        this.position,
        this.size
      );
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(
        final Object _viewBox) {
      if (this == _viewBox) {
        return true;
      }
      if ((_viewBox == null)
       || (this.getClass() != _viewBox.getClass())) {
        return false;
      }
      final ViewBox viewBox = (ViewBox) _viewBox;
      return
        viewBox.position().equals(this.position)
     && viewBox.size().equals(this.size);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
      return String.format(
        "ViewBox [position=%s size=%s]",
        this.position().toString(),
        this.size().toString()
      );
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toSvgString() {
      return String.format(
        "%d %d %d %d",
        this.position().x(),
        this.position().y(),
        this.size().width(),
        this.size().height()
      );
    }

  }
