package mino.ermal.engine3d.svg;

import java.util.Objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import mino.ermal.engine3d.svg.interfaces.SvgElement;

  /**
   *
   */
  public class Svg
      implements SvgElement {

    /**
     *
     */
    private static final String ELEMENT_NAME = "svg";

    /**
     *
     */
    private static final String TAG_XMLNS = "xmlns";

    /**
     *
     */
    private static final String VALUE_XMLNS = "http://www.w3.org/2000/svg";

    /**
     *
     */
    private String xmlns;

    /**
     *
     */
    private Size size;

    /**
     *
     */
    private ViewBox viewBox;

    /**
     *
     */
    public Svg() {
      this(
        Svg.VALUE_XMLNS,
        new Size(),
        new ViewBox()
      );
    }

    /**
     *
     * @param _svg {@code Svg}
     */
    public Svg(
        final Svg _svg) {
      this(
        _svg.xmlns(),
        _svg.size(),
        _svg.viewBox()
      );
    }

    /**
     *
     * @param _xmlns {@code String}
     * @param _size {@code Size}
     * @param _viewBox {@code ViewBox}
     */
    public Svg(
        final String _xmlns,
        final Size _size,
        final ViewBox _viewBox) {
      super();
      this.xmlns = _xmlns;
      this.size = _size;
      this.viewBox = _viewBox;
    }

    /**
     *
     * @return {@code String}
     */
    public final String xmlns() {
      return this.xmlns;
    }

    /**
     *
     * @param _xmlns {@code String}
     */
    public final void xmlns(
        final String _xmlns) {
      this.xmlns = _xmlns;
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
      return this.viewBox;
    }

    /**
     *
     * @param _viewBox {@code ViewBox}
     */
    public final void viewBox(
        final ViewBox _viewBox) {
      this.viewBox = _viewBox;
    }

    /**
     *
     * @return {@code Svg}
     */
    public final Svg svg() {
      return this;
    }

    /**
     *
     * @param _svg {@code Svg}
     */
    public final void svg(
        final Svg _svg) {
      this.svg(
        _svg.xmlns(),
        _svg.size(),
        _svg.viewBox()
      );
    }

    /**
     *
     * @param _xmlns {@code String}
     * @param _size {@code Size}
     * @param _viewBox {@code ViewBox}
     */
    public final void svg(
        final String _xmlns,
        final Size _size,
        final ViewBox _viewBox) {
      this.xmlns = _xmlns;
      this.size = _size;
      this.viewBox = _viewBox;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String name() {
      return Svg.ELEMENT_NAME;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
      return Objects.hash(
        this.xmlns,
        this.size,
        this.viewBox
      );
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(
        final Object _svg) {
      if (this == _svg) {
        return true;
      }
      if ((_svg == null)
       || (this.getClass() != _svg.getClass())) {
        return false;
      }
      final Svg svg = (Svg) _svg;
      return
        svg.xmlns().equals(this.xmlns)
     && svg.size().equals(this.size)
     && svg.viewBox().equals(this.viewBox);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
      return String.format(
        "Svg [xmlns=%s size=%s viewBox=%s]",
        this.xmlns().toString(),
        this.size().toString(),
        this.viewBox().toString()
      );
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void toSvgString(
        final Document _document) {
      final Element svg = _document.createElement(Svg.ELEMENT_NAME);
      svg.setAttribute(
        Svg.TAG_XMLNS,
        this.xmlns()
      );
      svg.setAttribute(
        Size.TAG_WIDTH,
        String.valueOf(this.size().width())
      );
      svg.setAttribute(
        Size.TAG_HEIGHT,
        String.valueOf(this.size().height())
      );
      svg.setAttribute(
        ViewBox.TAG_VIEWBOX,
        this.viewBox().toSvgString()
      );
      
      _document.appendChild(svg);
    }

  }
