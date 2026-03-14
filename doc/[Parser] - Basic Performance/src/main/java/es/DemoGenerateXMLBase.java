package es;

 import
  es.noa.rad.core.json.Json;
 import
  es.noa.rad.core.json.JsonArray;
 import
  es.noa.rad.core.json.JsonObject;
 import
  es.noa.rad.core.json.JsonValue;
 import
  es.noa.rad.demo.DemoScriptUtil;
 import
  es.noa.rad.demo.data.ProjectDTO;
 import
  es.noa.rad.util.core.exception.AbstractException;
 import
  es.noa.rad.util.file.UtilFile;
 import
  es.noa.rad.util.file.data.dto.FileDTO;
 import
  es.noa.rad.util.language.string.UtilFormatString;
 import
  es.noa.rad.xml.data.XsdSchema;

 public class DemoGenerateXMLBase {
  static ProjectDTO projectObject = null;
  static XsdSchema projectSchema = null;
  
  /**
    * Método principal.
    */
  public static void main(String[] args) {
   // Cargamos el fichero de definición
   FileDTO file = new FileDTO();
   file.setDirectoryFile("C:\\Desarrollo\\Workspace-core\\[Parser] - Basic Performance\\res\\");
   file.setNameFile("xmlProjectsDefinition");
   file.setExtensionFile("json");
   try {
    file = UtilFile.loadFile(file);
    JsonObject documentoData = Json.parse(new String(file.getDataFile())).asObject();
    JsonArray demosObject = documentoData.get("demos").asArray();
    for (JsonValue demoObject : demosObject) {
     projectObject = new ProjectDTO();     
     JsonObject xmlDemoObject = demoObject.asObject().get("demoXml").asObject(); 
     projectObject.setProjectName(xmlDemoObject.getString("name", null));
     JsonArray xmlDemoImports = xmlDemoObject.get("import").asArray();
     for (JsonValue xmlDemoImportObject : xmlDemoImports) {
      projectObject.addProjectImportListElement(xmlDemoImportObject.asString());
     }
     JsonArray xmlDemoSchemas = xmlDemoObject.get("schema").asArray();
     for (JsonValue xmlDemoSchemaObject : xmlDemoSchemas) {
      projectSchema = new XsdSchema();
      projectSchema.setSchemaPrefix(xmlDemoSchemaObject.asObject().getString("prefix", null));
      projectSchema.setUseTagSchemaPrefix(xmlDemoSchemaObject.asObject().getBoolean("useTagPrefix", false));    
      projectSchema.setUseFileSchemaPrefix(xmlDemoSchemaObject.asObject().getBoolean("useFilePrefix", false));
      projectSchema.setSchemaPackage(xmlDemoSchemaObject.asObject().getString("package", null));
      projectSchema.setSchemaDataPackage(xmlDemoSchemaObject.asObject().getString("dataPackage", null));
      projectSchema.setSchemaCreatorPackage(xmlDemoSchemaObject.asObject().getString("creatorPackage", null));
      projectSchema.setSchemaNameSpace(xmlDemoSchemaObject.asObject().getString("nameSpace", null));
      projectSchema.setUseSchemaNameSpace(xmlDemoSchemaObject.asObject().getBoolean("useNameSpace", false));
      /* Añadimos los tag que tenga definidos. */
      
      projectObject.addProjectSchemaListElement(projectSchema);
     }
     saveData(projectObject, "C:\\Desarrollo\\Workspace-core\\[Parser] - Basic Performance\\build\\gen\\");
    }
   } catch (AbstractException e) {
    e.printStackTrace();
   }
  }

  /**
    * Método para salvar los ficheros. 
    */
  public static void saveData(
         final ProjectDTO projectObjectParam,
         final String filePathParam) {
   String fileName ="";
   fileName = "Demo" + UtilFormatString.capitalizeAll(projectObject.getProjectName());
   FileDTO fileSave = new FileDTO(UtilFormatString.capitalize(fileName), filePathParam, "java", null, false);
   fileSave.setDataFile(DemoScriptUtil.getScript(projectObjectParam).getBytes());
   try {
    UtilFile.createFile(fileSave);
   } catch (AbstractException e) {
    e.printStackTrace();
   }
  }

 }
