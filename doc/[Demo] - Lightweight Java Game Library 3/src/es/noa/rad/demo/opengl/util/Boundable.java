package es.noa.rad.demo.opengl.util;

public interface Boundable<T> {
    int min(int axis);
    int max(int axis);
    void extend(int axis);
    T splitLeft(int splitAxis, int splitPos);
    T splitRight(int splitAxis, int splitPos);
}
