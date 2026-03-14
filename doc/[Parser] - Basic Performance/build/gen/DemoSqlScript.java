package es;

 import
  es.noa.rad.core.parser.eclipse.data.dto.EclipseProjectDTO;
 import
  es.noa.rad.xml.data.XsdSchema;

 public class DemoSqlScript {
  static EclipseProjectDTO eclipseProjectObject = null;
  static XsdSchema xsdSqlScriptSchema = null;

  /**
    * Método principal.
    */
  public static void main(String[] args) {
   // Generamos el 'Project' del objeto XML.
   eclipseProjectObject = new EclipseProjectDTO();
   eclipseProjectObject.setProjectName("");
   eclipseProjectObject.setProjectDescription("");
   eclipseProjectObject.setProjectUsePmd(true);
   eclipseProjectObject.setProjectUseCheckStyle(true);

   // Generamos el 'Schema' del objeto XML.
   xsdSqlScriptSchema = new XsdSchema();
   xsdSqlScriptSchema.setSchemaPackage("es.noa.rad.scaffold.xml");
   xsdSqlScriptSchema.setSchemaDataPackage("data.xdto.xql");
   xsdSqlScriptSchema.setSchemaCreatorPackage("api.creator");
   xsdSqlScriptSchema.setSchemaPrefix("sqlScript"); // Prefijo por defecto de los elementos, tanto etiquetas como ficheros.
   xsdSqlScriptSchema.setUseTagSchemaPrefix(true); // En las Etiquetas si/no se utilizan prefijos.
   xsdSqlScriptSchema.setUseFileSchemaPrefix(true); // En los ficheros si/no se utilizan prefijos.
   xsdSqlScriptSchema.setSchemaNameSpace("");
   xsdSqlScriptSchema.setUseSchemaNameSpace(false);
 }
