package mino.ermal.engine3d.util;

  /**
   *
   */
  public class Matrix {

    /**
     *
     */
    private static final int DIMENSIONS = 4;

    /**
     *
     */
    private float[] data;

    /**
     *
     */
    public Matrix() {
      this(new float[(Matrix.DIMENSIONS * Matrix.DIMENSIONS)]);
    }

    /**
     *
     * @param _data {@code float[]}
     */
    public Matrix(
        final float[] _data) {
      super();
      if (_data.length != (Matrix.DIMENSIONS * Matrix.DIMENSIONS)) {
        throw new IllegalArgumentException(
          "Array length must be "
        + (Matrix.DIMENSIONS * Matrix.DIMENSIONS)
        + ".");
      }
      this.data = _data.clone();
    }

    /**
     *
     * @return {@code int}
     */
    public static int dimensions() {
      return Matrix.DIMENSIONS;
    }

    /**
     *
     * @return {@code float[]}
     */
    public final float[] data() {
      return this.data;
    }

    /**
     *
     * @param _data {@code float}
     */
    public final void data(
        final float[] _data) {
      if (_data.length != (Matrix.DIMENSIONS * Matrix.DIMENSIONS)) {
        throw new IllegalArgumentException(
          "Array length must be "
        + (Matrix.DIMENSIONS * Matrix.DIMENSIONS)
        + ".");
      }
      this.data = _data.clone();
    }

    /**
     *
     * @param _position {@code int}
     * @return {@code float}
     */
    public final float data(
        final int _position) {
      if ((_position < 0)
       || (_position >= (Matrix.DIMENSIONS * Matrix.DIMENSIONS))) {
        throw new IndexOutOfBoundsException(
          "Position indices must be between 0 and "
        + ((Matrix.DIMENSIONS * Matrix.DIMENSIONS) - 1)
        + ".");
      }
      return this.data[_position];
    }

    /**
     *
     * @param _position {@code int}
     * @param _value {@code float}
     */
    public final void data(
        final int _position,
        final float _value) {
      if ((_position < 0)
       || (_position >= (Matrix.DIMENSIONS * Matrix.DIMENSIONS))) {
        throw new IndexOutOfBoundsException(
          "Position indices must be between 0 and "
        + ((Matrix.DIMENSIONS * Matrix.DIMENSIONS) - 1)
        + ".");
      }
      this.data[_position] = _value;
    }

    /**
     *
     * @param _row {@code int}
     * @param _column {@code int}
     * @return {@code float}
     */
    public final float data(
        final int _row,
        final int _column) {
      if ((_row < 0)
       || (_row >= Matrix.DIMENSIONS)
       || (_column < 0)
       || (_column >= Matrix.DIMENSIONS)) {
        throw new IndexOutOfBoundsException(
          "Row and column indices must be between 0 and "
        + Matrix.DIMENSIONS
        + ".");
      }
      return this.data[_row * Matrix.DIMENSIONS + _column];
    }

    /**
     *
     * @param _row {@code int}
     * @param _column {@code int}
     * @param _value {@code float}
     */
    public final void data(
        final int _row,
        final int _column,
        final float _value) {
      if ((_row < 0)
       || (_row >= Matrix.DIMENSIONS)
       || (_column < 0)
       || (_column >= Matrix.DIMENSIONS)) {
        throw new IndexOutOfBoundsException(
          "Row and column indices must be between 0 and "
        + Matrix.DIMENSIONS
        + ".");
      }
      this.data[_row * Matrix.DIMENSIONS + _column] = _value;
    }

    /**
     *
     */
    public final void matrix() {
      this.matrix(new float[(Matrix.DIMENSIONS * Matrix.DIMENSIONS)]);
    }

    /**
     *
     * @param _data {@code float[]}
     */
    public final void matrix(
        final float[] _data) {
      if (_data.length != (Matrix.DIMENSIONS * Matrix.DIMENSIONS)) {
        throw new IllegalArgumentException(
          "Array length must be "
        + (Matrix.DIMENSIONS * Matrix.DIMENSIONS)
        + ".");
      }
      this.data = _data.clone();
    }

  }
