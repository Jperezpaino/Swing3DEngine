package es.noa.rad.engine;

  public class Vector3DBO extends Vector3DDTO implements TransformableDefinition {

   @Override
   public final void add(
          final Vector3DDTO vector3DDTOParam) {
    super.setXVectorComponent(
     super.getXVectorComponent() + vector3DDTOParam.getXVectorComponent());
    super.setYVectorComponent(
     super.getYVectorComponent() + vector3DDTOParam.getYVectorComponent());
    super.setZVectorComponent(
     super.getZVectorComponent() + vector3DDTOParam.getZVectorComponent());  
   }
   
   @Override
   public final void add(
          final float xVectorComponentParam,
          final float yVectorComponentParam,
          final float zVectorComponentParam) {
    super.setXVectorComponent(
     super.getXVectorComponent() + xVectorComponentParam);
    super.setYVectorComponent(
     super.getYVectorComponent() + yVectorComponentParam);
    super.setZVectorComponent(
     super.getZVectorComponent() + zVectorComponentParam);    
   }

   @Override
   public final void addXVectorComponent(
          final float xVectorComponentParam) {
    super.setXVectorComponent(
     super.getXVectorComponent() + xVectorComponentParam);
   }

   @Override
   public final void addYVectorComponent(
          final float yVectorComponentParam) {
    super.setYVectorComponent(
     super.getYVectorComponent() + yVectorComponentParam);
   }

   @Override
   public final void addZVectorComponent(
          final float zVectorComponentParam) {
    super.setZVectorComponent(
     super.getZVectorComponent() + zVectorComponentParam);
   }

   @Override
   public final void subtract(
          final Vector3DDTO vector3DDTOParam) {
    super.setXVectorComponent(
     super.getXVectorComponent() - vector3DDTOParam.getXVectorComponent());
    super.setYVectorComponent(
     super.getYVectorComponent() - vector3DDTOParam.getYVectorComponent());
    super.setZVectorComponent(
     super.getZVectorComponent() - vector3DDTOParam.getZVectorComponent());  
   }
   
   @Override
   public final void subtract(
          final float xVectorComponentParam,
          final float yVectorComponentParam,
          final float zVectorComponentParam) {
    super.setXVectorComponent(
     super.getXVectorComponent() - xVectorComponentParam);
    super.setYVectorComponent(
     super.getYVectorComponent() - yVectorComponentParam);
    super.setZVectorComponent(
     super.getZVectorComponent() - zVectorComponentParam);    
   }

   @Override
   public final void subtractXVectorComponent(
          final float xVectorComponentParam) {
    super.setXVectorComponent(
     super.getXVectorComponent() - xVectorComponentParam);
   }

   @Override
   public final void subtractYVectorComponent(
          final float yVectorComponentParam) {
    super.setYVectorComponent(
     super.getYVectorComponent() - yVectorComponentParam);
   }

   @Override
   public final void subtractZVectorComponent(
          final float zVectorComponentParam) {
    super.setZVectorComponent(
     super.getZVectorComponent() - zVectorComponentParam);
   }

   @Override
   public final void multiply(
          final float vectorMultiplyValueParam) {
    super.setXVectorComponent(
     super.getXVectorComponent() * vectorMultiplyValueParam);
    super.setYVectorComponent(
     super.getYVectorComponent() * vectorMultiplyValueParam);
    super.setZVectorComponent(
     super.getZVectorComponent() * vectorMultiplyValueParam);    
   }

   @Override
   public final void divide(
          final float vectorDivideValueParam) {
    super.setXVectorComponent(
     super.getXVectorComponent() / vectorDivideValueParam);
    super.setYVectorComponent(
     super.getYVectorComponent() / vectorDivideValueParam);
    super.setZVectorComponent(
     super.getZVectorComponent() / vectorDivideValueParam);   
   }

   @Override
   public final void rotateXVectorComponent(
          final float xVectorAngleParam) {
    this.rotateXVectorComponent(
     (float) Math.cos(xVectorAngleParam),
     (float) Math.sin(xVectorAngleParam));
   }

   @Override
   public final void rotateYVectorComponent(
          final float yVectorAngleParam) {
    this.rotateYVectorComponent(
     (float) Math.cos(yVectorAngleParam),
     (float) Math.sin(yVectorAngleParam));
   }

   @Override
   public final void rotateZVectorComponent(
          final float zVectorAngleParam) {
    this.rotateZVectorComponent(
     (float) Math.cos(zVectorAngleParam),
     (float) Math.sin(zVectorAngleParam));
   }

   @Override
   public final void rotateXVectorComponent(
          final float xVectorCosineParam,
          final float xVectorSineParam) {
    float newYVectorComponent =
       ((float) super.getYVectorComponent() * xVectorCosineParam)
     - ((float) super.getZVectorComponent() * xVectorSineParam);
    float newZVectorComponent =
       ((float) super.getYVectorComponent() * xVectorSineParam) 
     + ((float) super.getZVectorComponent() * xVectorCosineParam);
    super.setYVectorComponent(newYVectorComponent);
    super.setZVectorComponent(newZVectorComponent);    
   }

   @Override
   public final void rotateYVectorComponent(
          final float yVectorCosineParam,
          final float yVectorSineParam) {
    float newXVectorComponent =
       ((float) super.getZVectorComponent() * yVectorSineParam)
     + ((float) super.getXVectorComponent() * yVectorCosineParam);
    float newZVectorComponent =
       ((float) super.getZVectorComponent() * yVectorCosineParam)
     - ((float) super.getXVectorComponent() * yVectorSineParam);
    super.setXVectorComponent(newXVectorComponent);
    super.setZVectorComponent(newZVectorComponent);       
   }

   @Override
   public final void rotateZVectorComponent(
                final float zVectorCosineParam,
                final float zVectorSineParam) {
    // TODO Auto-generated method stub
    float newXVectorComponent =
       ((float) super.getXVectorComponent() * zVectorCosineParam)
     - ((float) super.getYVectorComponent() * zVectorSineParam);
    float newYVectorComponent =
       ((float) super.getXVectorComponent() * zVectorSineParam) 
     + ((float) super.getYVectorComponent() * zVectorCosineParam);
    super.setXVectorComponent(newXVectorComponent);
    super.setYVectorComponent(newYVectorComponent);   
   }

   @Override
   public final void normalize() {
    this.divide(this.length());    
   }
   
   @Override
   public final float length() {
    return (float) Math.sqrt(
       ((float)super.getXVectorComponent() * super.getXVectorComponent())
     + ((float)super.getYVectorComponent() * super.getYVectorComponent())
     + ((float)super.getZVectorComponent() * super.getZVectorComponent())
    );
   }

  }
