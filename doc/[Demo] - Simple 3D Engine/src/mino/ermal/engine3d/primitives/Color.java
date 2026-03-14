package mino.ermal.engine3d.primitives;

import java.util.Objects;

  /**
   *
   */
  public class Color {

    /**
     *
     */
    private static final int MIN_VALUE = 0;

    /**
     *
     */
    private static final int MAX_VALUE = 255;

    /**
     *
     */
    public static final Color NO_COLOR = new Color (
      Color.MAX_VALUE,
      Color.MIN_VALUE,
      Color.MAX_VALUE
    );

    /**
     *
     */
    public int red;

    /**
     *
     */
    public int green;

    /**
     *
     */
    public int blue;

    /**
     *
     */
    public Color() {
      this(
        Color.MAX_VALUE,
        Color.MAX_VALUE,
        Color.MAX_VALUE
      );
    }

    /**
     *
     * @param _color {@code Color}
     */
    public Color(
        final Color _color) {
      this(
        _color.red(),
        _color.green(),
        _color.blue()
      );
    }

    /**
     *
     * @param _red {@code int}
     * @param _green {@code int}
     * @param _blue {@code int}
     */
    public Color(
        final int _red,
        final int _green,
        final int _blue) {
      this.red =_red;
      this.green =_green;
      this.blue = _blue;
    }

    /**
     *
     * @return {@code int}
     */
    public final int red() {
      return this.red;
    }

    /**
     *
     * @param _red {@code int}
     */
    public final void red(
        final int _red) {
      if ((_red >= Color.MIN_VALUE)
       && (_red <= Color.MAX_VALUE)) {
        this.red = _red;
      } else {
        throw new IllegalArgumentException(
          "El valor de 'Red' debe estar comprendido entre 0 y 255."
        );
      }
    }

    /**
     *
     * @return {@code int}
     */
    public final int green() {
      return this.green;
    }

    /**
     *
     * @param _green {@code int}
     */
    public final void green(
        final int _green) {
      if ((_green >= Color.MIN_VALUE)
       && (_green <= Color.MAX_VALUE)) {
        this.green = _green;
      } else {
        throw new IllegalArgumentException(
          "El valor de 'Green' debe estar comprendido entre 0 y 255."
        );
      }
    }

    /**
     *
     * @return {@code int}
     */
    public final int blue() {
      return this.blue;
    }

    /**
     *
     * @param _blue {@code int}
     */
    public final void blue(
        final int _blue) {
      if ((_blue >= Color.MIN_VALUE)
       && (_blue <= Color.MAX_VALUE)) {
        this.blue = _blue;
      } else {
        throw new IllegalArgumentException(
          "El valor de 'Blue' debe estar comprendido entre 0 y 255."
        );
      }
    }

    /**
     *
     * @return {@code Color}
     */
    public final Color color() {
      return this;
    }

    /**
     * 
     * @param _color {@code Color}
     */
    public final void color(
        final Color _color) {
      this.color(
        _color.red,
        _color.green,
        _color.blue
      );
    }

    /**
     *
     * @param _red {@code int}
     * @param _green {@code int}
     * @param _blue {@code int}
     */
    public final void color(
        final int _red,
        final int _green,
        final int _blue) {
      this.red = _red;
      this.green = _green;
      this.blue = _blue;
    }

    /**
     *
     * @return {@code String}
     */
    public final String RGB() {
      return
        "RGB("
      + this.red() + ", "
      + this.green() + ", "
      + this.blue()
      + ")";
    }

    /**
     *
     * @return {@code String}
     */
    public final String hex() {
      return String.format(
        "#%02X%02X%02X",
        this.red(),
        this.green(),
        this.blue()
      );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
      return Objects.hash(
        this.red,
        this.green,
        this.blue
      );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(
        final Object _color) {
      if (this == _color) {
        return true;
      }
      if ((_color == null)
       || (this.getClass() != _color.getClass())) {
        return false;
      }
      final Color color = (Color) _color;
      return
        Integer.compare(color.red(), this.red) == 0
     && Integer.compare(color.green(), this.green) == 0
     && Integer.compare(color.blue(), this.blue) == 0;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
      return String.format(
        "Color [red=%03d green=%03d blue=%03d hex=%s]",
        this.red(),
        this.green(),
        this.blue(),
        this.hex()
      );
    }

  }