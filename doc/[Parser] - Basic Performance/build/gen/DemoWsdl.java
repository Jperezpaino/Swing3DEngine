package es;

 import
  es.noa.rad.core.parser.eclipse.data.dto.EclipseProjectDTO;
 import
  es.noa.rad.xml.data.XsdSchema;

 public class DemoWsdl {
  static EclipseProjectDTO eclipseProjectObject = null;
  static XsdSchema xsdXsdSchema = null;
  static XsdSchema xsdSoapSchema = null;
  static XsdSchema xsdWsdlSchema = null;

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
   xsdXsdSchema = new XsdSchema();
   xsdXsdSchema.setSchemaPackage("es.noa.rad.util.xml.schema");
   xsdXsdSchema.setSchemaDataPackage("xsd.data.xdto");
   xsdXsdSchema.setSchemaCreatorPackage("xsd.api.creator");
   xsdXsdSchema.setSchemaPrefix("xsd"); // Prefijo por defecto de los elementos, tanto etiquetas como ficheros.
   xsdXsdSchema.setUseTagSchemaPrefix(false); // En las Etiquetas si/no se utilizan prefijos.
   xsdXsdSchema.setUseFileSchemaPrefix(true); // En los ficheros si/no se utilizan prefijos.
   xsdXsdSchema.setSchemaNameSpace("xsd");
   xsdXsdSchema.setUseSchemaNameSpace(true);

   // Generamos el 'Schema' del objeto XML.
   xsdSoapSchema = new XsdSchema();
   xsdSoapSchema.setSchemaPackage("es.noa.rad.wsdl.xml");
   xsdSoapSchema.setSchemaDataPackage("data.xdto.soap");
   xsdSoapSchema.setSchemaCreatorPackage("api.creator");
   xsdSoapSchema.setSchemaPrefix("soap"); // Prefijo por defecto de los elementos, tanto etiquetas como ficheros.
   xsdSoapSchema.setUseTagSchemaPrefix(false); // En las Etiquetas si/no se utilizan prefijos.
   xsdSoapSchema.setUseFileSchemaPrefix(true); // En los ficheros si/no se utilizan prefijos.
   xsdSoapSchema.setSchemaNameSpace("soap");
   xsdSoapSchema.setUseSchemaNameSpace(true);

   // Generamos el 'Schema' del objeto XML.
   xsdWsdlSchema = new XsdSchema();
   xsdWsdlSchema.setSchemaPackage("es.noa.rad.wsdl.xml");
   xsdWsdlSchema.setSchemaDataPackage("data.xdto.wsdl");
   xsdWsdlSchema.setSchemaCreatorPackage("api.creator");
   xsdWsdlSchema.setSchemaPrefix("wsdl"); // Prefijo por defecto de los elementos, tanto etiquetas como ficheros.
   xsdWsdlSchema.setUseTagSchemaPrefix(false); // En las Etiquetas si/no se utilizan prefijos.
   xsdWsdlSchema.setUseFileSchemaPrefix(true); // En los ficheros si/no se utilizan prefijos.
   xsdWsdlSchema.setSchemaNameSpace("");
   xsdWsdlSchema.setUseSchemaNameSpace(false);
 }
