package es;

 import
  es.noa.rad.core.parser.eclipse.data.dto.EclipseProjectDTO;
 import
  es.noa.rad.xml.data.XsdSchema;

 public class DemoMusicBrainz {
  static EclipseProjectDTO eclipseProjectObject = null;
  static XsdSchema xsdMusicBrainzSchema = null;

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
   xsdMusicBrainzSchema = new XsdSchema();
   xsdMusicBrainzSchema.setSchemaPackage("es.noa.rad.musicbrainz.xml");
   xsdMusicBrainzSchema.setSchemaDataPackage("data.xdto.mbd");
   xsdMusicBrainzSchema.setSchemaCreatorPackage("api.creator");
   xsdMusicBrainzSchema.setSchemaPrefix("musicBrainz"); // Prefijo por defecto de los elementos, tanto etiquetas como ficheros.
   xsdMusicBrainzSchema.setUseTagSchemaPrefix(false); // En las Etiquetas si/no se utilizan prefijos.
   xsdMusicBrainzSchema.setUseFileSchemaPrefix(true); // En los ficheros si/no se utilizan prefijos.
   xsdMusicBrainzSchema.setSchemaNameSpace("");
   xsdMusicBrainzSchema.setUseSchemaNameSpace(false);
 }
