package es.noa.rad.demo.data;

 import
  java.util.ArrayList;
 import
  java.util.List;
import es.noa.rad.xml.data.XsdSchema;

 public class ProjectDTO {
  private String projectName;
  private List<String> projectImportList;
  private List<XsdSchema> projectSchemaList;
  
  /**
    * 
    * @param projectNameParam
    */
  public ProjectDTO(
         final String projectNameParam,
         final List<String> projectImportListParam,
         final List<XsdSchema> projectSchemaListParam) {
   super();
   this.projectName = projectNameParam;
   this.projectImportList = projectImportListParam;
   this.projectSchemaList = projectSchemaListParam;
  }
  
  /**
    * 
    */

  public ProjectDTO() {
   super();
   this.projectName = "";
   this.projectImportList = new ArrayList<String>();
   this.projectSchemaList = new ArrayList<XsdSchema>();
  }
  



  /**
    * 
    * @return
    */
  public final String getProjectName() {
   return this.projectName;
  }

  /**
    * 
    * @param projectNameParam
    */
  public final void setProjectName(
         final String projectNameParam) {
   this.projectName = projectNameParam;
  }

  /**
    * 
    * @param projectImportListElementParam
    */
  public final void addProjectImportListElement(
         final String projectImportListElementParam) {
   this.projectImportList.add(projectImportListElementParam);
  }

  /**
    * 
    * @param projectImportListElementParam
    * @param positionParam
    */
  public final void addPositionProjectImportListElement(
         final String projectImportListElementParam,
         final int positionParam) {
   int listLength = this.getProjectImportListSize();
   if (positionParam < listLength) {
    this.projectImportList.add(positionParam, projectImportListElementParam);
   } else {
    this.projectImportList.add(projectImportListElementParam);
   }
  }

  /**
    *
    * @param positionParam
    */
  public final void deleteProjectImportListElement(
         final int positionParam) {
   int listLength = this.getProjectImportListSize();
   if (positionParam < listLength) {
    this.projectImportList.remove(positionParam);
   }
  }

  /**
    * 
    * @return
    */
  public final List<String> getProjectImportList() {
   return this.projectImportList;
  }

  /**
    * 
    * @param positionParam
    * @return
    */
  public final String getProjectImportListElement(
         final int positionParam) {
   if (positionParam < this.getProjectImportListSize()) {
    return this.projectImportList.get(positionParam);
   }
   return this.projectImportList.get(this.getProjectImportListSize());
  }

  /**
    * 
    * @return
    */
  public final int getProjectImportListSize() {
   return this.projectImportList.size();
  }

  /**
    * 
    * @param projectImportListParam
    */
  public final void setProjectImportList(
         final List<String> projectImportListParam) {
   this.projectImportList = projectImportListParam;
  }

  /**
    * 
    * @param projectImportListElementParam
    * @param positionParam
    */
  public final void setProjectImportListElement(
         final String projectImportListElementParam,
         final int positionParam) {
   if (positionParam < this.getProjectImportListSize()) {
    this.projectImportList.add(
     positionParam, projectImportListElementParam);
   } else {
    this.projectImportList.add(
     projectImportListElementParam);
   }
  }

  /**
    * 
    * @return
    */
  public final boolean isProjectImportListEmpty() {
   return this.projectImportList.isEmpty();
  }

  /**
    * 
    * @param projectSchemaListElementParam
    */
  public final void addProjectSchemaListElement(
         final XsdSchema projectSchemaListElementParam) {
   this.projectSchemaList.add(projectSchemaListElementParam);
  }

  /**
    * 
    * @param projectSchemaListElementParam
    * @param positionParam
    */
  public final void addPositionProjectSchemaListElement(
         final XsdSchema projectSchemaListElementParam,
         final int positionParam) {
   int listLength = this.getProjectSchemaListSize();
   if (positionParam < listLength) {
    this.projectSchemaList.add(positionParam, projectSchemaListElementParam);
   } else {
    this.projectSchemaList.add(projectSchemaListElementParam);
   }
  }

  /**
    *
    * @param positionParam
    */
  public final void deleteProjectSchemaListElement(
         final int positionParam) {
   int listLength = this.getProjectSchemaListSize();
   if (positionParam < listLength) {
    this.projectSchemaList.remove(positionParam);
   }
  }

  /**
    * 
    * @return
    */
  public final List<XsdSchema> getProjectSchemaList() {
   return this.projectSchemaList;
  }

  /**
    * 
    * @param positionParam
    * @return
    */
  public final XsdSchema getProjectSchemaListElement(
         final int positionParam) {
   if (positionParam < this.getProjectSchemaListSize()) {
    return this.projectSchemaList.get(positionParam);
   }
   return this.projectSchemaList.get(this.getProjectSchemaListSize());
  }

  /**
    * 
    * @return
    */
  public final int getProjectSchemaListSize() {
   return this.projectSchemaList.size();
  }

  /**
    * 
    * @param projectSchemaListParam
    */
  public final void setProjectSchemaList(
         final List<XsdSchema> projectSchemaListParam) {
   this.projectSchemaList = projectSchemaListParam;
  }

  /**
    * 
    * @param projectSchemaListElementParam
    * @param positionParam
    */
  public final void setProjectSchemaListElement(
         final XsdSchema projectSchemaListElementParam,
         final int positionParam) {
   if (positionParam < this.getProjectSchemaListSize()) {
    this.projectSchemaList.add(
     positionParam, projectSchemaListElementParam);
   } else {
    this.projectSchemaList.add(
     projectSchemaListElementParam);
   }
  }

  /**
    * 
    * @return
    */
  public final boolean isProjectSchemaListEmpty() {
   return this.projectSchemaList.isEmpty();
  }

 }
