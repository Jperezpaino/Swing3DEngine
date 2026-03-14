package es;

 import
  es.noa.rad.core.parser.eclipse.data.dto.EclipseProjectDTO;
 import
  es.noa.rad.xml.data.XsdSchema;

 public class DemoEclipseProject {
  static EclipseProjectDTO eclipseProjectObject = null;
  static XsdSchema xsdEclipseProjectSchema = null;

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
   xsdEclipseProjectSchema = new XsdSchema();
   xsdEclipseProjectSchema.setSchemaPackage("es.noa.rad.eclipse.xml");
   xsdEclipseProjectSchema.setSchemaDataPackage("data.xdto.epd");
   xsdEclipseProjectSchema.setSchemaCreatorPackage("api.creator");
   xsdEclipseProjectSchema.setSchemaPrefix("eclipseProject"); // Prefijo por defecto de los elementos, tanto etiquetas como ficheros.
   xsdEclipseProjectSchema.setUseTagSchemaPrefix(false); // En las Etiquetas si/no se utilizan prefijos.
   xsdEclipseProjectSchema.setUseFileSchemaPrefix(true); // En los ficheros si/no se utilizan prefijos.
   xsdEclipseProjectSchema.setSchemaNameSpace("");
   xsdEclipseProjectSchema.setUseSchemaNameSpace(false);
 }
