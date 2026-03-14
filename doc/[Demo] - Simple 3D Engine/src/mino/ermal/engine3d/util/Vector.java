package mino.ermal.engine3d.util;

import java.util.Objects;

/**
   *
   */
  public class Vector {

    /**
     *
     */
    private static final int DIMENSIONS = 3;

    /**
     *
     */
    private double x;

    /**
     *
     */
    private double y;

    /**
     *
     */
    private double z;

    /**
     *
     */
    public Vector() {
      this(0.0d, 0.0d, 0.0d);
    }

    /**
     *
     * @param _vector {@code Vector}
     */
    public Vector(
        final Vector _vector) {
      this(
        _vector.x(),
        _vector.y(),
        _vector.z()
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public Vector(
        final double _x,
        final double _y,
        final double _z) {
      super();
      this.x = _x;
      this.y = _y;
      this.z = _z;
    }

    /**
     *
     * @return {@code int}
     */
    public static int dimensions() {
      return Vector.DIMENSIONS;
    }

    /**
     *
     * @return {@code double}
     */
    public final double x() {
      return this.x;
    }

    /**
     *
     * @param _x {@code double}
     */
    public final void x(
        final double _x) {
      this.x = _x;
    }

    /**
     *
     * @return {@code double}
     */
    public final double y() {
      return this.y;
    }

    /**
     *
     * @param _y {@code double}
     */
    public final void y(
        final double _y) {
      this.y = _y;
    }

    /**
     *
     * @return {@code double}
     */
    public final double z() {
      return this.z;
    }

    /**
     *
     * @param _z {@code double}
     */
    public final void z(
        final double _z) {
      this.z = _z;
    }

    /**
     *
     * @return {@code Vector}
     */
    public final Vector vector() {
      return this;
    }

    /**
     *
     * @param _vector {@code Vector}
     */
    public final void vector(
        final Vector _vector) {
      this.vector(
        _vector.x(),
        _vector.y(),
        _vector.z()
      );
    }

    /**
     *
     * @param _x {@code double}
     * @param _y {@code double}
     * @param _z {@code double}
     */
    public final void vector(
        final double _x,
        final double _y,
        final double _z) {
      this.x = _x;
      this.y = _y;
      this.z = _z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
      return Objects.hash(
        this.x,
        this.y,
        this.z
      );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(
        final Object _vector) {
      if (this == _vector) {
        return true;
      }
      if ((_vector == null)
       || (this.getClass() != _vector.getClass())) {
        return false;
      }
      final Vector vector = (Vector) _vector;
      return
        Double.compare(vector.x(), this.x) == 0
     && Double.compare(vector.y(), this.y) == 0
     && Double.compare(vector.z(), this.z) == 0;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
      return String.format(
        "Vector [x=%.2f y=%.2f z=%.2f]",
        this.x(),
        this.y(),
        this.z()
      );
    }

  }
