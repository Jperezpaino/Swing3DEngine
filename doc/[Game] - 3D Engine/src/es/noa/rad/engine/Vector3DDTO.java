package es.noa.rad.engine;

 public class Vector3DDTO {
  private float xVectorComponent;
  private float yVectorComponent;
  private float zVectorComponent;
  
  public Vector3DDTO() {
   super();
   this.xVectorComponent = 0;
   this.yVectorComponent = 0;
   this.zVectorComponent = 0;
  }
  
  public Vector3DDTO(
         final Vector3DDTO vector3DDTOParam) {
   super();   
   this.xVectorComponent =
    vector3DDTOParam.getXVectorComponent();
   this.yVectorComponent =
    vector3DDTOParam.getYVectorComponent();
   this.zVectorComponent =
    vector3DDTOParam.getZVectorComponent();
  }
  
  public Vector3DDTO(
         final float xVectorComponentParam,
         final float yVectorComponentParam,
         final float zVectorComponentParam) {
   super();   
   this.xVectorComponent =
    xVectorComponentParam;
   this.yVectorComponent =
    yVectorComponentParam;
   this.zVectorComponent =
    zVectorComponentParam;
  }

  public final Vector3DDTO getVector3D() {
   return this;
  }
  
  public final void setVector3D() {
   this.xVectorComponent = 0;
   this.yVectorComponent = 0;
   this.zVectorComponent = 0;
  }
  
  public final void setVector3D(
         final Vector3DDTO vector3DDTOParam) {
   this.xVectorComponent =
    vector3DDTOParam.getXVectorComponent();
   this.yVectorComponent =
    vector3DDTOParam.getYVectorComponent();
   this.zVectorComponent =
    vector3DDTOParam.getZVectorComponent();
  }
  
  public final void setVector3D(
         final float xVectorComponentParam,
         final float yVectorComponentParam,
         final float zVectorComponentParam) {
   this.xVectorComponent =
    xVectorComponentParam;
   this.yVectorComponent =
    yVectorComponentParam;
   this.zVectorComponent =
    zVectorComponentParam;
  } 
  
  public final float getXVectorComponent() {
   return this.xVectorComponent;
  }

  public final void setXVectorComponent(
         final float xVectorComponentParam) {
   this.xVectorComponent = xVectorComponentParam;
  }

  public final float getYVectorComponent() {
   return this.yVectorComponent;
  }

  public final void setYVectorComponent(
         final float yVectorComponentParam) {
   this.yVectorComponent = yVectorComponentParam;
  }

  public final float getZVectorComponent() {
   return this.zVectorComponent;
  }

  public final void setZVectorComponent(
         final float zVectorComponentParam) {
   this.zVectorComponent = zVectorComponentParam;
  }
   
 }
