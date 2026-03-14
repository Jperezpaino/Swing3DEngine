package mino.ermal.engine3d.interfaces;

import mino.ermal.engine3d.primitives.Color;

  /**
   *
   */
  public interface Colorable {

      /**
       *
       * @return {@code Color}
       */
      public Color color();

      /**
       *
       * @param _color {@code Color}
       */
      public void color(
        final Color _color);

      /**
       *
       * @param _red {@code int}
       * @param _greeen {@code int}
       * @param _blue {@code int}
       */
      public void color(
        final int _red,
        final int _greeen,
        final int _blue);

  }
