package es.noa.rad.engine;


 public interface TransformableDefinition {
  
  public void add(
         final Vector3DDTO vector3DDTOParam);

  public void add(
         final float xVectorComponentParam,
         final float yVectorComponentParam,
         final float zVectorComponentParam);
  
  public void addXVectorComponent(
         final float xVectorComponentParam);
  
  public void addYVectorComponent(
         final float yVectorComponentParam);
  
  public void addZVectorComponent(
         final float zVectorComponentParam);
  
  public void subtract(
         final Vector3DDTO vector3DDTOParam);

  public void subtract(
         final float xVectorComponentParam,
         final float yVectorComponentParam,
         final float zVectorComponentParam);
  
  public void subtractXVectorComponent(
         final float xVectorComponentParam);
  
  public void subtractYVectorComponent(
         final float yVectorComponentParam);
  
  public void subtractZVectorComponent(
         final float zVectorComponentParam);
  
  public void multiply(
         final float vectorMultiplyValueParam);
  
  public void divide(
         final float vectorDivideValueParam);
  
  public void rotateXVectorComponent(
         final float xVectorAngleParam);
  
  public void rotateYVectorComponent(
         final float yVectorAngleParam);
  
  public void rotateZVectorComponent(
         final float zVectorAngleParam);
  
  public void rotateXVectorComponent(
         final float xVectorCosineParam,
         final float xVectorSineParam);
  
  public void rotateYVectorComponent(
         final float yVectorCosineParam,
         final float yVectorSineParam);
  
  public void rotateZVectorComponent(
         final float zVectorCosineParam,
         final float zVectorSineParam);
  
  public void normalize();
  
  public float length() ;
  
  
  /*public void add(Transform3D xform);

  public void subtract(Transform3D xform);

  public void addRotation(Transform3D xform);

  public void subtractRotation(Transform3D xform);*/

 }
