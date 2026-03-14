package mino.ermal.engine3d.util;

  /**
   *
   */
  public final class ColorUtil {

    /**
     *
     */
    private ColorUtil() {
    }

    /**
     *
     * @param _color {@code mino.ermal.engine3d.primitives.Color}
     * @return {@code java.awt.Color}
     */
    public static java.awt.Color color(
        final mino.ermal.engine3d.primitives.Color _color) {
      return new java.awt.Color(
        _color.red(),
        _color.green(),
        _color.blue()
      );
    }

    /**
     *
     * @param _color {@code java.awt.Color}
     * @return {@code mino.ermal.engine3d.primitives.Color}
     */
    public static mino.ermal.engine3d.primitives.Color color(
        final java.awt.Color _color) {
      return new mino.ermal.engine3d.primitives.Color(
        _color.getRed(),
        _color.getGreen(),
        _color.getBlue()
      );
    }

    /**
     *
     * @param _color {@code mino.ermal.engine3d.primitives.Color}
     * @param _lighting {@code double}
     * @return {@code java.awt.Color}
     */
    public static mino.ermal.engine3d.primitives.Color lighting(
        final mino.ermal.engine3d.primitives.Color _color,
        final double _lighting) {

      /* Aseguramos que la iluminación está en el rango válido [0, 1]. */
      final double clearLighting
        = Math.max(0, Math.min(1, Math.abs(_lighting)));

      /*
       * Multiplicamos cada componente RGB por la iluminación y los limitamos al
       * rango valido  [0, 255].
       */
      final int red = Math.min(
        255, Math.max(0, ((int) (_color.red() * clearLighting)))
      );
      final int green = Math.min(
        255, Math.max(0, ((int) (_color.green() * clearLighting)))
      );
      final int blue = Math.min(
        255, Math.max(0, ((int) (_color.blue() * clearLighting)))
      );

      return new mino.ermal.engine3d.primitives.Color(
        red,
        green,
        blue
      );
    }

    /**
     *
     * @param _color {@code java.awt.Color}
     * @param _lighting {@code double}
     * @return {@code mino.ermal.engine3d.primitives.Color}
     */
    public static java.awt.Color lighting(
        final java.awt.Color _color,
        final double _lighting) {

      /* Aseguramos que la iluminación está en el rango válido [0, 1]. */
      final double clearLighting
        = Math.max(0, Math.min(1, Math.abs(_lighting)));

      /*
       * Multiplicamos cada componente RGB por la iluminación y los limitamos al
       * rango valido  [0, 255].
       */
      final int red = Math.min(
        255, Math.max(0, ((int) (_color.getRed() * clearLighting)))
      );
      final int green = Math.min(
        255, Math.max(0, ((int) (_color.getGreen() * clearLighting)))
      );
      final int blue = Math.min(
        255, Math.max(0, ((int) (_color.getBlue() * clearLighting)))
      );

      return new java.awt.Color(
        red,
        green,
        blue
      );
    }

  }
