package mino.ermal.engine3d.matrix;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: 10-dic-2006
 * Time: 14.52.06
 *
 * @see Matrix3D
 *
 * This Matrix3D subclass is specific for the rotation transformation
 */


public class RotationMatrix3D extends Matrix3D {
    /**
     * This constructor creates a complex 3D rotation matrix which rotates
     * around x,y and z by x_angle,y_angle and z_angle
     * by multiplying 3 elementary rotation matrices
     * @param x_angle x angle expressed in radians
     * @param y_angle y angle expressed in radians
     * @param z_angle z angle expressed in radians
     */
    public RotationMatrix3D(double x_angle,double y_angle,double z_angle){
		//initializes the X rotation matrix
        super(1,                  0,                 0,       0,
			  0,  Math.cos(x_angle), Math.sin(x_angle),       0,
			  0, -Math.sin(x_angle), Math.cos(x_angle),       0,
			  0,                  0,                 0,       1);

        //multiplies with the Y rotation matrix
        multiply(
                new Matrix3D(
                        Math.cos(y_angle),  0,  -Math.sin(y_angle),   0,
						0                ,  1,                   0,   0,
						Math.sin(y_angle),  0,   Math.cos(y_angle),   0,
						0                ,  0,                   0,   1)
        );

        //multiplies with the Z rotation matrix
        multiply(
                new Matrix3D(
                         Math.cos(z_angle),  Math.sin(z_angle),   0,  0,
						-Math.sin(z_angle),  Math.cos(z_angle),   0,  0,
						                 0,                  0,   1,  0,
						                 0,                  0,   0,  1)
        );
	}
}
