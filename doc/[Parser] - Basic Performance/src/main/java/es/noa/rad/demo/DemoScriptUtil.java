package es.noa.rad.demo;

 import
  es.noa.rad.demo.data.ProjectDTO;
 import
  es.noa.rad.util.language.string.UtilFormatString;
import es.noa.rad.xml.data.XsdSchema;

 /**
   *
   */
 public class DemoScriptUtil {

  /**
    *
    * schemaParam XsdSchema
    * elementParam XsdElement
    */
  public static String getScript(
         final ProjectDTO projectParam) {
   String script = "";
   //script += DemoScriptUtil.getPackageScriptJavaDoc(projectParam);
   script += DemoScriptUtil.getPackageScript(projectParam);
   /* Declaramos el Flag de control de Imports. */
   script += "%IMPORT_DATA%";
   //script += DemoScriptUtil.getDeclareClassScriptJavaDoc(projectParam);
   script += DemoScriptUtil.getDeclareClassScript(projectParam);
   script += DemoScriptUtil.getDeclareVariableScript(projectParam);
   script += DemoScriptUtil.getDeclareMainMethodScriptJavaDoc(projectParam);
   script += DemoScriptUtil.getDeclareMainMethodScript(projectParam);
   
//   script += DemoScriptUtil.getImportClassScript(schemaParam, elementParam);
//   script += DemoScriptUtil.getDefinitionClassScriptAnnotation(schemaParam, elementParam);
//   script += DemoScriptUtil.getDefinitionClassScript(schemaParam, elementParam);

//   script += DemoScriptUtil.getDeclareConstructorsScript(schemaParam, elementParam);
//   if (elementParam instanceof XsdComplexElement) {
//    if (!((XsdComplexElement) elementParam).isElementElementListEmpty()) {
//     for(int i = 0; i < ((XsdComplexElement) elementParam).getElementElementListSize(); i++) {
//      if (((XsdComplexElement) elementParam).getElementElementListElement(i).getElementMaxOccurs() == -1) {
//       script += DemoScriptUtil.getElementListAddScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListAddScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListAddPositionScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListAddPositionScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListDeleteScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListDeleteScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListGetterScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListGetterScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListGetterElementScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListGetterElementScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListGetterSizeScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListGetterSizeScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListSetterScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListSetterScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListSetterElementScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListSetterElementScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListIsEmptyScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementListIsEmptyScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//      } else {
//       script += DemoScriptUtil.getElementGetterScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementGetterScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementSetterScriptJavaDoc(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       script += DemoScriptUtil.getElementSetterScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//      }
//     }
//    }
//   }
//   // Añadimos los Helper
//   // Comprobamos si tiene atributos
//   if (!elementParam.isElementAttributeListEmpty()) {   
//    script += DemoScriptUtil.getDeclareInicializeDefaultAttributesMethodScriptJavaDoc(schemaParam, elementParam);
//    script += DemoScriptUtil.getDeclareInicializeDefaultAttributesMethodScript(schemaParam, elementParam);
//   }
//   script += DemoScriptUtil.getDeclareInicializeDefaultElementsMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareInicializeDefaultElementsMethodScript(schemaParam, elementParam);
//   // Comprobamos si tiene atributos
//   if (!elementParam.isElementAttributeListEmpty()) {   
//    script += DemoScriptUtil.getDeclareInicializeParserAttributesMethodScriptJavaDoc(schemaParam, elementParam);   
//    script += DemoScriptUtil.getDeclareInicializeParserAttributesMethodScript(schemaParam, elementParam);
//   }
//   script += DemoScriptUtil.getDeclareInicializeParserElementsMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareInicializeParserElementsMethodScript(schemaParam, elementParam);   
//   script += DemoScriptUtil.getDeclareEmptyDataMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareEmptyDataMethodScript(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareEmptyAttributeMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareEmptyAttributeMethodScript(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareEmptyMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareEmptyMethodScript(schemaParam, elementParam);
//   // Añadimos mos metodos de representación
//   script += DemoScriptUtil.getDeclareToXmlStringMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareToXmlStringMethodScript(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareToXsdStringMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareToXsdStringMethodScript(schemaParam, elementParam);
//   // Añadimos los Helper de representación
//   if (elementParam instanceof XsdComplexElement) {
//    script += DemoScriptUtil.getDeclareBuildTagContentMethodScriptJavaDoc(schemaParam, elementParam);
//    script += DemoScriptUtil.getDeclareBuildTagContentMethodScript(schemaParam, elementParam);
//   }
//   // Añadimos mos metodos de propiedades
//   script += DemoScriptUtil.getDeclarePropertiesMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclarePropertiesMethodScript(schemaParam);      
//   script += DemoScriptUtil.getDeclareReferenceClassMethodScriptJavaDoc(schemaParam, elementParam);
//   script += DemoScriptUtil.getDeclareReferenceClassMethodScript(schemaParam, elementParam);
   script += " }\n";
   script = script.replaceFirst(
    "%IMPORT_DATA%",
    DemoScriptUtil.getImportClassScript(projectParam));
   return script;
  }

  /**
    * Obtenemos el javadoc de la declaración del paquete de la clase.
    */   
  public final static String getPackageScriptJavaDoc(
         final ProjectDTO projectParam) {
   String script = "";
   String packageName = getPackageNameValue(projectParam);
   String className = getClassNameValue(projectParam);
   script += "/**\n";
   script += "  * " + packageName + "\n";
   script += "  *   ." + className + ".java\n";
   script += "  */\n";
   return script;
  }

  /**
    * Obtenemos la declaración del paquete de la clase.
    */   
  public final static String getPackageScript(
         final ProjectDTO projectParam) {
   String script = "";
   String packageName = getPackageNameValue(projectParam);
   script += "package " + packageName + ";\n";
   return script;
  }

  /**
    * Obtenemos los distintos import necesarios en la clase.
    */ 
  public final static String getImportClassScript(
         final ProjectDTO projectParam) { 
   String script = "";
   if (!projectParam.isProjectImportListEmpty()) {
    script += "\n";    
    for(int i = 0; i < projectParam.getProjectImportListSize(); i++) {
     script += " import\n";
     script += "  " + projectParam.getProjectImportListElement(i) + ";\n";
    }
   }
   script += "\n";
   return script;
  }

  /**
    * Obtenemos el javadoc de la declaración de la clase.
    */ 
  public final static String getDeclareClassScriptJavaDoc(
         final ProjectDTO projectParam) { 
   String script = "";  
   String className = getClassNameValue(projectParam);   
   String controlParserString = ""; //Contenido de la cadena a limitar
   String controlStartString = ""; // Inicio de la cadena a limitar
   script += " /**\n";
   script += "   * <p>\n";
   controlParserString = "Definición de la clase '" + className + "'.";
   controlStartString = "   *  ";
   script += parserStringSize(controlParserString, controlStartString, true);        
   script += "   * </p>\n";
   script += "   *\n";
   script += "   */\n";
   return script;
  }

  /**
    * Obtenemos la declaración de la clase.
    */ 
  public final static String getDeclareClassScript(
         final ProjectDTO projectParam) {
   String script = "";
   String className = getClassNameValue(projectParam);   
   script += " public class ";
   script += className;
   script += " {\n";
   return script;
  }

  /**
    * Obtenemos las variables de construcción.
    */    
  public final static String getDeclareVariableScript(
         final ProjectDTO projectParam) {         
   String script = "";
   script += "  static EclipseProjectDTO eclipseProjectObject = null;\n";   
   if (!projectParam.isProjectSchemaListEmpty()) {  
    for(int i = 0; i < projectParam.getProjectSchemaListSize(); i++) {
     String classXsdSchemaObjectName = getClassXsdSchemaObjectNameValue(projectParam.getProjectSchemaListElement(i)); 
     script += "  static XsdSchema " + classXsdSchemaObjectName + " = null;\n";
    }
   } 
   script += "\n";
   return script;
  }

  /**
    * Obtenemos las variables de construcción.
    */    
  public final static String getDeclareMainMethodScriptJavaDoc(
         final ProjectDTO projectParam) {         
   String script = "";
   script += "  /**\n";
   script += "    * Método principal.\n"; 
   script += "    */\n";
   return script;
  }

  /**
    * Obtenemos las variables de construcción.
    */    
  public final static String getDeclareMainMethodScript(
         final ProjectDTO projectParam) {         
   String script = "";  
   script += "  public static void main(String[] args) {\n";
   script += "   // Generamos el 'Project' del objeto XML.\n";
   script += "   eclipseProjectObject = new EclipseProjectDTO();\n";
   script += "   eclipseProjectObject.setProjectName(\"\");\n";
   script += "   eclipseProjectObject.setProjectDescription(\"\");\n";
   script += "   eclipseProjectObject.setProjectUsePmd(true);\n";
   script += "   eclipseProjectObject.setProjectUseCheckStyle(true);\n";
   if (!projectParam.isProjectSchemaListEmpty()) {    
    for(int i = 0; i < projectParam.getProjectSchemaListSize(); i++) {
     final XsdSchema schemaObject = projectParam.getProjectSchemaListElement(i);       
     final String classXsdSchemaObjectName = getClassXsdSchemaObjectNameValue(schemaObject);
     script += "\n";     
     script += "   // Generamos el 'Schema' del objeto XML.\n";
     script += "   " + classXsdSchemaObjectName + " = new XsdSchema();\n";
     script += "   " + classXsdSchemaObjectName + ".setSchemaPackage(\"" + schemaObject.getSchemaPackage() + "\");\n";
     script += "   " + classXsdSchemaObjectName + ".setSchemaDataPackage(\"" + schemaObject.getSchemaDataPackage() + "\");\n";
     script += "   " + classXsdSchemaObjectName + ".setSchemaCreatorPackage(\"" + schemaObject.getSchemaCreatorPackage() + "\");\n";
     script += "   " + classXsdSchemaObjectName + ".setSchemaPrefix(\"" + schemaObject.getSchemaPrefix() + "\"); // Prefijo por defecto de los elementos, tanto etiquetas como ficheros.\n";
     script += "   " + classXsdSchemaObjectName + ".setUseTagSchemaPrefix(" + schemaObject.isUseTagSchemaPrefix() + "); // En las Etiquetas si/no se utilizan prefijos.\n";
     script += "   " + classXsdSchemaObjectName + ".setUseFileSchemaPrefix(" + schemaObject.isUseFileSchemaPrefix() + "); // En los ficheros si/no se utilizan prefijos.\n";
     script += "   " + classXsdSchemaObjectName + ".setSchemaNameSpace(\"" + schemaObject.getSchemaNameSpace() + "\");\n";
     script += "   " + classXsdSchemaObjectName + ".setUseSchemaNameSpace(" + schemaObject.isUseSchemaNameSpace() + ");\n"; 
    }
   }
   return script;
  }
  
//
//  /**
//    *
//    */  
//  public static String getDeclareConstructorsScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);     
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   script += DemoScriptUtil.getDeclareSimpleConstructorScriptJavaDoc(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareSimpleConstructorScript(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareSimpleSmallParamConstructorScriptJavaDoc(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareSimpleSmallParamConstructorScript(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareSimpleBigParamConstructorScriptJavaDoc(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareSimpleBigParamConstructorScript(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareComplexConstructorScriptJavaDoc(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareComplexConstructorScript(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareObjectConstructorScriptJavaDoc(schemaParam, elementParam, variableClassName);
//   script += DemoScriptUtil.getDeclareObjectConstructorScript(schemaParam, elementParam, variableClassName);
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getDeclareSimpleConstructorScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";    
//   script += "  /**\n";
//   parserString = "Constructor genérico de la clase '" + classNameParam + "' no recibe parámetro alguno y establece los valores del objeto a los predeterminados por la propia definición de la clase.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);  
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getDeclareSimpleConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";   
//   script += "  public\n";
//   script += "    ";
//   script += classNameParam;           
//   script += "() {\n";
//   script += "   super(\n";
//   script += "    " + classNameParam + ".XML_NAME_SPACE,\n";
//   script += "    " + classNameParam + ".XML_TAG_NAME);\n";
//   script += "\n";
//   // Comprobamos si tiene atributos
//   if (!elementParam.isElementAttributeListEmpty()) {
//    script += "   /*\n";
//    script += "    * Inicializamos los atributos que componen la etiqueta XML.\n";
//    script += "    */\n";
//    script += "   this.inicializeDefaultAttributes();\n";
//    script += "\n";
//   }
//   script += "   /*\n";
//   script += "    * Inicializamos los elementos que componen la etiqueta XML.\n";
//   script += "    */\n";
//   script += "   this.inicializeDefaultElements();\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }      
//  
//  /**
//    *
//    */
//  public static String getDeclareSimpleSmallParamConstructorScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";    
//   script += "  /**\n";   
//   parserString = "Constructor especifico de la clase '" + classNameParam + "', recibe los siguientes parámetros y establece los valores del objeto a los predeterminados por los propios valores de los parámetros recibidos.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);  
//   script += "    *\n";
//   script += "    * @param xmlNameSpaceParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code String}.\n";
//   script += "    *  </p>\n";
//   script += "    * @param xmlTagNameParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code String}.\n";
//   script += "    *  </p>\n";
//   script += "    * @param xmlIndentationParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code Integer}.\n";
//   script += "    *  </p>\n";
//   script += "    * @param xmlAttributesParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code List<XmlAttributeDTO>}.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getDeclareSimpleSmallParamConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";
//   script += "  public\n";
//   script += "    ";
//   script += classNameParam;           
//   script += "(\n";
//   script += "         final String\n          xmlNameSpaceParam,\n";
//   script += "         final String\n          xmlTagNameParam,\n";
//   script += "         final Integer\n          xmlIndentationParam,\n";
//   script += "         final List<XmlAttributeDTO>\n          xmlAttributesParam) {\n";
//   script += "   super(xmlNameSpaceParam,\n";
//   script += "         xmlTagNameParam,\n";
//   script += "         xmlIndentationParam,\n";
//   script += "         xmlAttributesParam);\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Inicializamos los elementos que componen la etiqueta XML.\n";
//   script += "    */\n";
//   script += "   this.inicializeDefaultElements();\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }     
//  
//  /**
//    *
//    */  
//  public static String getDeclareSimpleBigParamConstructorScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";    
//   script += "  /**\n";   
//   parserString = "Constructor especifico de la clase '" + classNameParam + "', recibe los siguientes parámetros y establece los valores del objeto a los predeterminados por los propios valores de los parámetros recibidos.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true); 
//   script += "    *\n";   
//   script += "    * @param xmlNameSpaceParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code String}.\n";
//   script += "    *  </p>\n";
//   script += "    * @param xmlTagNameParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code String}.\n";
//   script += "    *  </p>\n";
//   script += "    * @param xmlIndentationParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code Integer}.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getDeclareSimpleBigParamConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";   
//   script += "  public\n";
//   script += "    ";
//   script += classNameParam;           
//   script += "(\n";
//   script += "         final String\n          xmlNameSpaceParam,\n";
//   script += "         final String\n          xmlTagNameParam,\n";
//   script += "         final Integer\n          xmlIndentationParam) {\n";
//   script += "   super(xmlNameSpaceParam,\n";
//   script += "         xmlTagNameParam,\n";
//   script += "         xmlIndentationParam);\n";
//   script += "\n";
//   // Comprobamos si tiene atributos
//   if (!elementParam.isElementAttributeListEmpty()) {
//    script += "   /*\n";
//    script += "    * Inicializamos los atributos que componen la etiqueta XML.\n";
//    script += "    */\n";
//    script += "   this.inicializeDefaultAttributes();\n";
//    script += "\n";
//   }
//   script += "   /*\n";
//   script += "    * Inicializamos los elementos que componen la etiqueta XML.\n";
//   script += "    */\n";
//   script += "   this.inicializeDefaultElements();\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }     
//  
//  /**
//    *
//    */
//  public static String getDeclareComplexConstructorScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";     
//   script += "  /**\n";   
//   parserString = "Constructor especifico de la clase '" + classNameParam + "', recibe los siguientes parámetros y establece los valores del objeto a los predeterminados por los propios valores de los parámetros recibidos.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true); 
//   script += "    *\n";   
//   script += "    * @param xmlNodeParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code Node}.\n";
//   script += "    *  </p>\n";
//   script += "    * @param xmlIndentationParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code Integer}.\n";
//   script += "    *  </p>\n";
//   script += "    * @param xmlNameSpaceParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code String}.\n";
//   script += "    *  </p>\n"; 
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getDeclareComplexConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";   
//   script += "  public\n";
//   script += "    ";
//   script += classNameParam;           
//   script += "(\n";
//   script += "         final Node\n          xmlNodeParam,\n";
//   script += "         final Integer\n          xmlIndentationParam,\n";
//   script += "         final String\n          xmlNameSpaceParam) {\n";
//   script += "   super();\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Establecemos el 'NameSpace' al valor recibido como parámetro, si este es\n";
//   script += "    * distinto de nulo, en caso contrario utilizamos al valor por defecto\n";
//   script += "    * definido en la clase.\n";
//   script += "    */\n";
//   script += "   if (xmlNameSpaceParam == null) {\n";
//   script += "    this.setXmlNameSpace(\n";
//   script += "     " + classNameParam + ".XML_NAME_SPACE);\n";
//   script += "   } else {\n";
//   script += "    this.setXmlNameSpace(xmlNameSpaceParam);\n";
//   script += "   }\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Establecemos el 'Tag' del objeto XML al valor por defecto definido en la\n";
//   script += "    * clase.\n";
//   script += "    */\n";
//   script += "   this.setXmlTagName(\n";
//   script += "    " + classNameParam + ".XML_TAG_NAME);\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Establecemos la identación del objeto XML al valor recibido como\n";
//   script += "    * parámetro.\n";
//   script += "    */\n";
//   script += "   this.setXmlIndentation(xmlIndentationParam);\n";
//
//   // Comprobamos si tiene atributos
//   if (!elementParam.isElementAttributeListEmpty()) {
//    script += "\n";
//    script += "   /*\n";
//    script += "    * Inicializamos y procesamos los atributos que componen la etiqueta XML.\n";
//    script += "    */\n";
//    script += "   this.inicializeParserAttributes((Element) xmlNodeParam);\n";
//   }   
//   script += "\n"; 
//   script += "   /*\n";
//   script += "    * Reorganizamos los atributos, y los colocamos en la posición correcta\n";
//   script += "    * según su definición, y orden alfabético.\n";
//   script += "    */\n";
//   script += "   XmlParserUtil.parserAttributes(this, (Element) xmlNodeParam);\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Inicializamos y procesamos los elementos que componen la etiqueta XML.\n";
//   script += "    */\n";
//   script += "   this.inicializeParserElements((Element) xmlNodeParam);\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getDeclareObjectConstructorScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) { 
//   String script = "";
//   String parserString = "";
//   String startString = ""; 
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(classNameParam + "Param");
//   script += "  /**\n";   
//   parserString = "Constructor especifico de la clase '" + classNameParam + "', recibe los siguientes parámetros y establece los valores del objeto a los predeterminados por los propios valores de los parámetros recibidos.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);  
//   script += "    *\n";   
//   script += "    * @param ";
//   script += UtilFormatString.unCapitalize(variableParamClassName) + "\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code " + classNameParam + "}.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getDeclareObjectConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,
//         final String classNameParam) {
//   String script = "";
//   String parserString = "";
//   String startString = ""; 
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(classNameParam + "Param");
//   script += "  public\n";
//   script += "    ";
//   script += classNameParam;           
//   script += "(\n";
//   script += "         final ";
//   script += classNameParam + "\n";
//   script += "          ";
//   script += variableParamClassName + ") {\n";
//   script += "   super(" + variableParamClassName + ");\n"; 
//   if (elementParam instanceof XsdSimpleElement) {
//   	script += "\n";
//	   script += "   /*\n";
//    parserString = "Al ser un nodo de texto, inicializamos las propiedades corespondientes a este tipo de objetos con la equidad del objeto '" + variableParamClassName + "'.";
//    startString = "    * ";
//    script += parserStringSize(parserString, startString, true);       
//    script += "    */\n";
//    script += "   super.setTagValue(\n";
//    script += "    " + variableParamClassName + ".getTagValue());\n";
//    script += "   super.setUseCDATA(\n";
//    script += "    " + variableParamClassName + ".isUseCDATA());\n";	   
//   } else if (elementParam instanceof XsdComplexElement) {	   
//    for(int i = 0; i < ((XsdComplexElement) elementParam).getElementElementListSize(); i++) {
//     //String prefix = getPrefix(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//     String prefix = getPrefixOfFiles(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//     String propertyValue =
//      UtilFormatString.unCapitalize(
//        prefix
//      + UtilFormatString.capitalize(((XsdComplexElement) elementParam).getElementElementListElement(i).getElementName()));     
//     if (((XsdComplexElement) elementParam).getElementElementListElement(i).getElementMaxOccurs() == -1) {
//      propertyValue += "XDTOList";
//     } else {
//      propertyValue += "XDTOElement";      
//     }        
//     script += "\n";     
//     script += "   /*\n";
//     parserString = "Inicializamos la propiedad privada '" + propertyValue + "', con la equidad del objeto '" + variableParamClassName + "'.";
//     startString = "    * ";
//     script += parserStringSize(parserString, startString, true);     
//     script += "    */\n";    	
//     script += "   this.";
//     script += propertyValue + "\n";
//     script += "    = " + variableParamClassName +"\n       .get";
//     script += UtilFormatString.capitalize(propertyValue) + "();\n";
//    }
//   }
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */  
//  public static String getDeclareEmptyMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  /**\n";
//   script += "    * @return {@code boolean}\n";
//   script += "    *  <p>\n";
//   script += "    *   Devolvemos el estado del objeto, en lo referente a la presencia de\n";
//   script += "    *   contenido dentro de todas las partes que componen el 'Tag'.\n";   
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getDeclareEmptyMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  @Override\n";
//   script += "  public final boolean\n";
//   script += "    isEmptyTag() {\n";
//   script += "   boolean isEmpty = true;\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Comprobamos el estado de todos los elementos del objeto y de los\n";
//   script += "    * atributos, comprobando si estan informados, y devolvemos el resultado.\n";
//   script += "    */\n"; 
//   script += "   isEmpty &= this.isAttributeEmptyTag();\n";
//   script += "   isEmpty &= this.isDataEmptyTag();\n";
//   script += "\n";   
//   script += "   return isEmpty;\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getDeclareEmptyDataMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  /**\n";
//   script += "    * @return {@code boolean}\n";
//   script += "    *  <p>\n";
//   script += "    *   Devolvemos el estado del objeto, en lo referente a la presencia de\n";
//   script += "    *   contenido dentro del 'Tag'.\n";
//   script += "    *  </p>\n";  
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getDeclareEmptyDataMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  @Override\n";
//   script += "  public final boolean\n";
//   script += "    isDataEmptyTag() {\n";
//   script += "   boolean isEmpty = true;\n";
//   if (elementParam instanceof XsdComplexElement) {
//    if (((XsdComplexElement) elementParam).haveSimpleElement()) {
//     script += "   XdtoObjectViewDefinition xdtoObjectViewDefinition;\n";
//    }	   
//    if (((XsdComplexElement) elementParam).haveListElement()) {   
//     script += "   List<? extends XdtoObjectViewDefinition> xdtoObjectViewDefinitionList;\n";
//    }
//   }
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Comprobamos el estado de todos los elementos del objeto y devolvemos el\n";
//   script += "    * resultado.\n";
//   script += "    */\n";   
//   if (elementParam instanceof XsdSimpleElement) {
//    script += "   isEmpty &= XmlParserUtil.isTagDataEmpty(this);\n";
//   } else if (elementParam instanceof XsdComplexElement) {
//    if (!((XsdComplexElement) elementParam).isElementElementListEmpty()) {
//     for(int i = 0; i < ((XsdComplexElement) elementParam).getElementElementListSize();i++) {
//      if (((XsdComplexElement) elementParam).getElementElementListElement(i).getElementMaxOccurs() == -1) {
//       script += "   xdtoObjectViewDefinitionList\n";
//       //String prefix = getPrefix(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//       String prefix = getPrefixOfFiles(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));   
//       String variableClassName =
//        prefix
//      + UtilFormatString.capitalize(
//         UtilFormatString.capitalizeAll(((XsdComplexElement) elementParam).getElementElementListElement(i).getElementName(), '-').replaceAll("-", "")
//        )
//      + "XDTO";
//       script += "    = this.get" + variableClassName + "List();\n";
//       script += "   isEmpty &= XmlParserUtil.isTagEmpty(xdtoObjectViewDefinitionList);\n";       
//      } else {
//       script += "   xdtoObjectViewDefinition\n";
//       //String prefix = getPrefix(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));         
//       String prefix = getPrefixOfFiles(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));   
//       String variableClassName =
//        prefix
//      + UtilFormatString.capitalize(
//         UtilFormatString.capitalizeAll(((XsdComplexElement) elementParam).getElementElementListElement(i).getElementName(), '-').replaceAll("-", "")
//        )
//      + "XDTO";
//       script += "    = this.get" + variableClassName + "Element();\n";
//       script += "   isEmpty &= XmlParserUtil.isTagEmpty(xdtoObjectViewDefinition);\n";
//      }
//      if (i < ((XsdComplexElement) elementParam).getElementElementListSize() - 1) {
//       script += "\n";
//      }            
//     }
//    }
//   }
//   script += "\n";   
//   script += "   return isEmpty;\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getDeclareEmptyAttributeMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  /**\n";
//   script += "    * @return {@code boolean}\n";
//   script += "    *  <p>\n";
//   script += "    *   Devolvemos el estado del objeto, en lo referente a la presencia de\n";
//   script += "    *   contenido dentro de los atributos existentes en el 'Tag'.\n";   
//   script += "    *  </p>\n"; 
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getDeclareEmptyAttributeMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  @Override\n";
//   script += "  public final boolean\n";
//   script += "    isAttributeEmptyTag() {\n";
//   script += "   boolean isEmpty;\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Comprobamos el estado de los atributos, comprobando si estan informados,\n";
//   script += "    * y devolvemos el resultado.\n";
//   script += "    */\n";
//   script += "   isEmpty = XmlParserUtil.isAttributeEmpty(this);\n";
//   script += "\n";   
//   script += "   return isEmpty;\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
// 
//  /**
//    *
//    */  
//  public static String getDeclarePropertiesMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);      
//   String variableClassName =
//     prefix
//   + UtilFormatString.capitalize(
//      UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//     )
//   + "XDTO";
//   script += "  /**\n";
//   script += "    * <p>\n";
//   script += "    *  Método 'Reference' de la clase '" + variableClassName + "'.\n";
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   script += "    *  Este método devuelve la referencia de la clase de propieades que esta\n";
//   script += "    *  vinculada o definida para la representación del objeto XML.\n";
//   script += "    * <p>\n";
//   script += "    *\n";   
//   script += "    * @return {@code XmlParserProperties}\n";
//   script += "    *  <p>\n";
//   script += "    *   Devolvemos el objeto de propiedades definido para la representación\n";
//   script += "    *   del objeto XML.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclarePropertiesMethodScript(
//         final XsdSchema schemaParam) {
//   String script = "";
//   script += "  @Override\n";
//   script += "  public final XmlParserProperties\n";
//   script += "    getProperties() {\n";
//   script += "   return " + getPrefixOfFiles(schemaParam) + "CCreatorProperties.getInstance();\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareToXmlStringMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  /**\n";
//   script += "    * @return {@code String}\n";
//   script += "    *  <p>\n";
//   script += "    *   Devolvemos la representación escrita del objeto con formato XML.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareToXmlStringMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  @Override\n";
//   script += "  public final String\n";
//   script += "    toXmlString() {\n";
//   script += "   final StringBuilder xmlResult = new StringBuilder();\n";
//   script += "   xmlResult.append(\n";
//   script += "    XmlParserUtil.getStartTagString(this));\n";
//   script += "   if (this.isEmptyTag()) {\n";
//   script += "    xmlResult.append(\n";
//   script += "     XmlParserUtil.getEmptyEndTagString(this));\n";
//   script += "   } else {\n";
//   script += "    if (this.isDataEmptyTag() && !this.isAttributeEmptyTag()) {\n";
//   script += "     xmlResult.append(\n";
//   script += "      XmlParserUtil.getEmptyEndTagString(this));\n";
//   script += "    } else {\n";
//   script += "     xmlResult.append(\n";
//   script += "      ExternalSystem.get(ExternalSystem._TAG_END_));\n";
//   if (elementParam instanceof XsdSimpleElement) {
//    script += "     xmlResult.append(\n";
//    script += "      XmlParserUtil.getTextTagString(this));\n";
//   } else if (elementParam instanceof XsdComplexElement) {
//    if (!((XsdComplexElement) elementParam).isElementElementListEmpty()) {
//     script += "     xmlResult.append(\n";
//     script += "      ExternalSystem.get(ExternalSystem._EOL_LF_));\n";
//     script += "     xmlResult.append(\n";
//     script += "      this.buildTagContent());\n";
//    }
//   }
//   script += "     xmlResult.append(\n";
//   script += "      XmlParserUtil.getEndTagString(this));\n";
//   script += "    }\n";
//   script += "   }\n";
//   script += "   xmlResult.append(\n";
//   script += "    ExternalSystem.get(ExternalSystem._EOL_LF_));\n";
//   script += "   return xmlResult.toString();\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareToXsdStringMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  /**\n";
//   script += "    * @return {@code String}\n";
//   script += "    *  <p>\n";
//   script += "    *   Devolvemos la representación escrita del objeto con formato XSD.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareToXsdStringMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  @Override\n";
//   script += "  public final String\n";
//   script += "    toXsdString() {\n";
//   script += "   final StringBuilder xsdResult = new StringBuilder();\n";
//   script += "   return xsdResult.toString();\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getElementDefinitionScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   script += "  /**\n";
//   parserString = "Objeto anidado dentro de la etiqueta padre, correspondiente al elemento '" + variableClassName + "'.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getElementDefinitionScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam); 
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "Element");
//   String minOccurs = String.valueOf(elementParam.getElementMinOccurs());
//   String maxOccurs = String.valueOf(elementParam.getElementMaxOccurs());
//   if(maxOccurs.equals("-1")) {
//    maxOccurs = "unbounded";
//   }   
//   script += "  @XsdSequenceNodeAnnotation(\n";
//   script += "   name = \"" + elementParam.getElementName() + "\",\n";
//   script += "   minOccurs = \"" + minOccurs + "\",\n";
//   script += "   maxOccurs = \""+ maxOccurs + "\"\n";
//   script += "  )\n";
//   script += "  private ";
//   script += variableClassName + "\n";
//   String variable = "";
//   variable += "   ";
//   variable += variableParamClassName + "\n";
//   variable += "    = new ";
//   variable += variableClassName + "();\n";
//   script += variable;
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getElementListDefinitionScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   script += "  /**\n";
//   parserString = "Objeto anidado de la etiqueta padre correspondiente a el elemento de tipo lista '" + variableClassName + "'.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getElementListDefinitionScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   // Es un elemento, se deben recuperar los datos del elemento.
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);      
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   String minOccurs = String.valueOf(elementParam.getElementMinOccurs());
//   String maxOccurs = String.valueOf(elementParam.getElementMaxOccurs());
//   if(maxOccurs.equals("-1")) {
//    maxOccurs = "unbounded";
//   }   
//   script += "  @XsdSequenceNodeAnnotation(\n";
//   script += "   name = \"" + elementParam.getElementName() + "\",\n";
//   script += "   minOccurs = \"" + minOccurs + "\",\n";
//   script += "   maxOccurs = \""+ maxOccurs + "\"\n";
//   script += "  )\n";
//   script += "  private List<";
//   script += variableClassName + ">\n";
//   script += "   ";
//   script += variableParamClassName + "\n";
//   script += "    = new ArrayList<";
//   script += variableClassName + ">();\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getElementSimpleConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "Element");
//   script += "\n";
//   script += "   /*\n";
//   parserString = "Inicializamos la propiedad privada '" + variableParamClassName + "', con valores por defecto.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    */\n";
//   script += "   this.";
//   script += variableParamClassName + "\n";
//   script += "    = new ";
//   script += variableClassName + "();\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getElementListSimpleConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);      
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "\n";
//   script += "   /*\n";
//   parserString = "Inicializamos la propiedad privada '" + variableParamClassName + "' de tipo lista, con valores por defecto.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    */\n";
//   script += "   this.";
//   script += variableParamClassName + "\n";
//   script += "    = new ArrayList<";
//   script += variableClassName + ">();\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getElementComplexConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "Element");
//   script += "\n";
//   script += "   /*\n";
//   parserString = "Inicializamos la propiedad privada '" + variableParamClassName + "', con valores definidos a través del contenido del objeto 'xmlElementParam'.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    */\n";
//   script += "   this.";
//   script += variableParamClassName + "\n";
//   script += "    = (";
//   script += variableClassName + ") XmlParserUtil.getData(\n";
//   script += "     xmlElementParam,\n";
//   script += "     nameSpace,\n";
//   script += "     " + variableClassName + ".XML_NAME_SPACE,\n";
//   script += "     " + variableClassName + ".XML_TAG_NAME,\n";
//   script += "     ";
//   script += variableClassName+ ".class,\n";
//   script += "     indentation);\n";
//   return script;
//  }
//  
//  /**
//    *
//    */
//  public static String getElementListComplexConstructorScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";  
//   String prefix = getPrefixOfFiles(schemaParam);     
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "\n";
//   script += "   /*\n";
//   parserString = "Inicializamos la propiedad privada '" + variableParamClassName + "' de tipo lista, con valores definidos a través del contenido del objeto 'xmlElementParam'.";
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    */\n";
//   script += "   this.";
//   script += variableParamClassName + "\n";
//   script += "    = UtilCompareList.cast(\n";
//   script += "     XmlParserUtil.getArrayData(\n";
//   script += "      xmlElementParam,\n";
//   script += "      nameSpace,\n";
//   script += "      " + variableClassName + ".XML_NAME_SPACE,\n";
//   script += "      " + variableClassName + ".XML_TAG_NAME,\n";
//   script += "      ";
//   script += variableClassName+ ".class,\n";
//   script += "      indentation));\n";
//   return script;
//  }
//
//  /**
//    *
//    */  
//  public static String getElementGetterScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "Element");   
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Getter' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método devuelve el valor de la propiedad privada '" + variableParamClassName + "' a través de un {@code return} del tipo {@code " + variableClassName + "}.";   
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @return {@code " + variableClassName + "}\n";
//   script += "    *  <p>\n";
//   parserString = "Devolvemos el valor de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */  
//  public static String getElementGetterScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "Element"); 
//   script += "  public final " + variableClassName + "\n";
//   script += "    get" + UtilFormatString.capitalize(variableParamClassName) + "() {\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Devolvemos el valor de la propiedad.\n";
//   script += "    */\n";   
//   script += "   return this." + variableParamClassName + ";\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementSetterScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "Element");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ElementParam");    
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Setter' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método establece un nuevo valor a la propiedad privada '" + variableParamClassName + "', de tipo {@code " + variableClassName + "}, a través del parámetro recibido '" + variableParamClassDefinition + "' de tipo {@code " + variableClassName + "}.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @param " + variableParamClassDefinition + "\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code " + variableClassName + "}, que define el nuevo valor de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementSetterScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam, elementParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "Element");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ElementParam");        
//   script += "  public final void\n";
//   script += "    set" + UtilFormatString.capitalize(variableParamClassName) + "(\n";
//   script += "         final " + variableClassName + "\n";
//   script += "          " + variableParamClassDefinition + ") {\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Definimos el nuevo valor de la propiedad.\n";
//   script += "    */\n";   
//   script += "   this." + variableParamClassName + "\n";
//   script += "    = " + variableParamClassDefinition + ";\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListAddScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ListElementParam");
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Add' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método añade un nuevo valor a la lista de la propiedad privada '" + variableParamClassName + "', de tipo {@code List<" + variableClassName + ">}, a través del parámetro recibido '" + variableParamClassDefinition + "' de tipo {@code " + variableClassName + "}.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);  
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @param " + variableParamClassDefinition + "\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code " + variableClassName + "}, que define el nuevo valor a añadir a la lista de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListAddScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//    String variableParamClassName =
//     UtilFormatString.unCapitalize(variableClassName + "List");
//    String variableParamClassDefinition =
//     UtilFormatString.unCapitalize(variableClassName + "ListElementParam");
//   script += "  public final void\n";
//   script += "    add" + UtilFormatString.capitalize(variableParamClassName) + "Element(\n";
//   script += "         final " + variableClassName + "\n";
//   script += "          " + variableParamClassDefinition + ") {\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Comprobamos si está definida la lista, sino la creamos.\n";
//   script += "    */\n";
//   script += "   if (this." + variableParamClassName + " == null) {\n";
//   script += "    this." + variableParamClassName + "\n";
//   script += "     = new ArrayList<" + variableClassName + ">();\n";
//   script += "   }\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Añadimos el nuevo elemento, en la posición final de la lista.\n";
//   script += "    */\n";
//   script += "   this." + variableParamClassName + ".add(\n";
//   script += "    " + variableParamClassDefinition + ");\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListAddPositionScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = ""; 
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ListElementParam");
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Add by Position' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método añade un nuevo valor en una determinada posición de la lista de la propiedad privada '" + variableParamClassName + "', de tipo {@code List<" + variableClassName + ">}, a través del parámetro recibido '" + variableParamClassDefinition + "' de tipo {@code " + variableClassName + "}, y el parámetro recibido 'positionParam' de tipo {@code Integer}, que marca la posición en la que realizar la inserción, en caso de ser una posición mayor a la longitud de la lista, se insertara al final de la misma un nuevo elemento.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @param " + variableParamClassDefinition + "\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code " + variableClassName + "}, que define el nuevo valor a añadir a la lista de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);   
//   script += "    *  </p>\n";
//   script += "    * @param positionParam\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code Integer}, que define el valor o posición dentro de la lista de la propiedad privada '" + variableParamClassName + "' en la que se quiere insertar el nuevo elemento.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListAddPositionScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";  
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ListElementParam");   
//   script += "  public final void\n";
//   script += "    addPosition" + UtilFormatString.capitalize(variableParamClassName) + "Element(\n";
//   script += "         final " + variableClassName + "\n";
//   script += "          " + variableParamClassDefinition + ",\n";
//   script += "         final Integer\n";
//   script += "          positionParam) {\n";
//   script += "   Integer listPosition = new Integer(positionParam);\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Comprobamos si está definida la lista, sino la creamos.\n";
//   script += "    */\n";
//   script += "   if (this." + variableParamClassName + " == null) {\n";
//   script += "    this." + variableParamClassName + "\n";
//   script += "     = new ArrayList<" + variableClassName + "" + ">();\n";
//   script += "   }\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Comprobamos que la posición a añadir sea mayor que el tamaño de la\n";
//   script += "    * lista, e igualamos esta al tamaño actual de la lista para no exceder los\n";
//   script += "    * limites, en caso contrario utilizamos esta posición recibida que entra\n";
//   script += "    * dentro de los limites.\n";
//   script += "    */\n";
//   script += "   if (listPosition.intValue()\n    > this.get" + variableClassName + "ListSize()) {\n";
//   script += "    listPosition\n     = new Integer(this.get" + variableClassName + "ListSize());\n";
//   script += "   }\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Añadimos el nuevo elemento, en la posición determinada de la lista.\n";
//   script += "    */\n";
//   script += "   this." + variableParamClassName + ".add(\n";
//   script += "    listPosition.intValue(),\n    " + variableParamClassDefinition + ");\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListDeleteScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Delete' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método elimina un valor de una determinada posición de la lista de la propiedad privada '" + variableParamClassName + "', de tipo {@code List<" + variableClassName + ">}, a través del parámetro recibido 'positionParam' de tipo {@code Integer}, que marca la posición en la que realizar el borrado, en caso de ser una posición mayor a la longitud de la lista no se realizara ningún borrado.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true); 
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @param positionParam\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code Integer}, que define el valor o posición dentro de la lista de la propiedad privada '" + variableParamClassName + "' en la que se quiere realizar un borrado de un elemento.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);  
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListDeleteScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = ""; 
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "  public final void\n";
//   script += "    delete" + UtilFormatString.capitalize(variableParamClassName) + "Element(\n";
//   script += "         final Integer\n";
//   script += "          positionParam) {\n";
//   script += "\n";      
//   script += "   /*\n";
//   script += "    * Comprobamos si está definida la lista  y que la posición a eliminar sea\n";
//   script += "    * menor al tamaño de la lista, en caso contrario no eliminamos nada, ya\n";
//   script += "    * que excedemos los límites de la lista.\n";
//   script += "    */\n";
//   script += "   if (this." + variableParamClassName + " != null\n";
//   script += "    && positionParam < this.get" + variableClassName + "ListSize()) {\n";
//   script += "\n";   
//   script += "    /*\n";
//   script += "     * Eliminamos el elemento de la posición determinada de la lista.\n";
//   script += "     */\n";
//   script += "    this." + variableParamClassName + ".remove(positionParam.intValue());\n";
//   script += "   }\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListGetterScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Getter' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método devuelve el valor de la lista de la propiedad privada '" + variableParamClassName + "' a través de un {@code return} del tipo {@code List<" + variableClassName + ">}.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @return {@code List<" + variableClassName + ">}\n";
//   script += "    *  <p>\n";
//   parserString = "Devolvemos el valor de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListGetterScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "  public final List<" + variableClassName + ">\n";
//   script += "    get" + UtilFormatString.capitalize(variableParamClassName) + "() {\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Devolvemos el valor de la lista al completo.\n";
//   script += "    */\n";
//   script += "   return this." + variableParamClassName + ";\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListGetterElementScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Getter Element' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método devuelve el valor de un elemento de la lista de la propiedad privada '" + variableParamClassName + "' a través de un {@code return} del tipo {@code " + variableClassName + "}, a través del parámetro recibido 'positionParam' de tipo {@code Integer}, que marca la posición de la lista a recuperar, en caso de ser una posición mayor a la longitud de la lista se recuperara el ultimo valor contenido por la lista.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @param positionParam\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code Integer}, que define el valor o posición dentro de la lista de la propiedad privada '" + variableParamClassName + "' en la que se quiere devolver el valor de ese elemento.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    *  </p>\n";
//   script += "    * @return {@code " + variableClassName + "}\n";
//   script += "    *  <p>\n";
//   parserString = "Devolvemos el valor del elemento marcado por la posición de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListGetterElementScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = ""; 
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List"); 
//   script += "  public final " + variableClassName + "\n";
//   script += "    get" + UtilFormatString.capitalize(variableParamClassName) + "Element(\n";
//   script += "         final Integer\n";
//   script += "          positionParam) {\n";
//   script += "   Integer listPosition = new Integer(positionParam);\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Comprobamos si está definida la lista, sino la creamos.\n";
//   script += "    */\n";
//   script += "   if (this." + variableParamClassName + " == null) {\n";
//   script += "    this." + variableParamClassName + "\n";
//   script += "     = new ArrayList<" + variableClassName + "" + ">();\n";
//   script += "   }\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Comprobamos que la posición a recuperar sea mayor que el tamaño de la\n";
//   script += "    * lista, e igualamos esta al tamaño actual de la lista para no exceder los\n";
//   script += "    * limites, en caso contrario utilizamos esta posición recibida que entra\n";
//   script += "    * dentro de los limites.\n";
//   script += "    */\n";
//   script += "   if (listPosition.intValue()\n    > this.get" + variableClassName + "ListSize()) {\n";
//   script += "    listPosition\n     = new Integer(this.get" + variableClassName + "ListSize());\n";
//   script += "   }\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Devolvemos el elemento, de la posición determinada de la lista.\n";
//   script += "    */\n";
//   script += "   return this." + variableParamClassName + ".get(listPosition.intValue());\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListGetterSizeScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Getter Size' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método devuelve el valor de la longitud o tamaño de la lista de la propiedad privada '" + variableParamClassName + "' a través de un {@code return} del tipo {@code Integer}.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @return {@code Integer}\n";
//   script += "    *  <p>\n";
//   parserString = "Devolvemos el valor de la longitud o tamaño de la lista de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListGetterSizeScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";  
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List"); 
//   script += "  public final Integer\n";
//   script += "    get" + UtilFormatString.capitalize(variableParamClassName) + "Size() {\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Comprobamos si está definida la lista, sino la creamos.\n";
//   script += "    */\n";
//   script += "   if (this." + variableParamClassName + " == null) {\n";
//   script += "    this." + variableParamClassName + "\n";
//   script += "     = new ArrayList<" + variableClassName + ">();\n";
//   script += "   }\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Devolvemos el número de elementos de la lista.\n";
//   script += "    */\n";
//   script += "   return new Integer(this." + variableParamClassName + ".size());\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListSetterScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = ""; 
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ListParam");   
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Setter' de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método establece un nuevo valor a la propiedad privada '" + variableParamClassName + "', de tipo {@code List<" + variableClassName + ">}, a través del parámetro recibido '" + variableParamClassDefinition + "' de tipo {@code List<" + variableClassName + ">}.";
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);     
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @param " + variableParamClassDefinition + "\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code List<" + variableClassName + ">}, que define el nuevo valor de la propiedad privada '" + variableParamClassName + "'.";
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListSetterScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";  
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ListParam");
//    
//   script += "  public final void\n";
//   script += "    set" + UtilFormatString.capitalize(variableParamClassName) + "(\n";
//   script += "         final List<" + variableClassName + ">\n";
//   script += "          " + variableParamClassDefinition + ") {\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Definimos el nuevo valor de la lista al completo.\n";
//   script += "    */\n";
//   script += "   this." + variableParamClassName + "\n";
//   script += "     = " + variableParamClassDefinition + ";\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListSetterElementScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ListElementParam");
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Setter Element' de la propiedad privada '" + variableParamClassName + "'."; 
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);      
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método establece un nuevo valor a un elemento de la lista en una determinada posición de la lista de la propiedad privada '" + variableParamClassName + "', de tipo {@code List<" + variableClassName + ">}, a través del parámetro recibido '" + variableParamClassDefinition + "' de tipo {@code " + variableClassName + "}, y el parámetro recibido 'positionParam' de tipo {@code Integer}, que marca la posición en la que realizar la inserción, en caso de ser una posición mayor a la longitud de la lista, se insertara al final de la misma un nuevo elemento."; 
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);      
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @param " + variableParamClassDefinition + "\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code " + variableClassName + "}, que define el nuevo valor a establecer al elemento de la lista de la propiedad privada '" + variableParamClassName + "'."; 
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);      
//   script += "    *  </p>\n";
//   script += "    * @param positionParam\n";
//   script += "    *  <p>\n";
//   parserString = "Parámetro de tipo {@code Integer}, que define el valor o posición dentro de la lista de la propiedad privada '" + variableParamClassName + "' en la que se quiere establecer el nuevo valor del elemento."; 
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);      
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListSetterElementScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   String variableParamClassDefinition =
//    UtilFormatString.unCapitalize(variableClassName + "ListElementParam"); 
//   script += "  public final void\n";
//   script += "    set" + UtilFormatString.capitalize(variableParamClassName) + "Element(\n";
//   script += "         final " + variableClassName + "\n";
//   script += "          " + variableParamClassDefinition + ",\n";
//   script += "         final Integer\n";
//   script += "          positionParam) {\n";
//   script += "   Integer listPosition;\n";
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Comprobamos si está definida la lista, sino la creamos.\n";
//   script += "    */\n";
//   script += "   if (this." + variableParamClassName + " == null) {\n";
//   script += "    this." + variableParamClassName + "\n";
//   script += "     = new ArrayList<" + variableClassName + ">();\n";
//   script += "   }\n";
//   script += "\n";      
//   script += "   /*\n";
//   script += "    * Comprobamos que la posición a añadir sea mayor que el tamaño de la\n";
//   script += "    * lista, e igualamos esta al tamaño actual de la lista para no exceder los\n";
//   script += "    * limites, en caso contrario utilizamos esta posición recibida que entra\n";
//   script += "    * dentro de los limites.\n";
//   script += "    */\n";
//   script += "   if (positionParam > this.get" + variableClassName + "ListSize()) {\n";
//   script += "    listPosition\n     = new Integer(this.get" + variableClassName + "ListSize());\n";
//   script += "   } else {\n";
//   script += "    listPosition\n     = new Integer(positionParam);\n";
//   script += "   }\n";
//   script += "\n";      
//   script += "   /*\n";
//   script += "    * Añadimos el nuevo elemento, en la posición determinada de la lista.\n";
//   script += "    */\n";
//   script += "   this." + variableParamClassName + ".add(\n";
//   script += "    listPosition.intValue(),\n    " + variableParamClassDefinition + ");\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListIsEmptyScriptJavaDoc(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = "";
//   String parserString = "";
//   String startString = ""; 
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "  /**\n";
//   script += "    * <p>\n";
//   parserString = "Método 'Is Info' de la propiedad privada '" + variableParamClassName + "'."; 
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);      
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   parserString = "Este método devuelve el estado que presenta la lista de la propiedad privada '" + variableParamClassName + "', con respecto a si está vacía de elementos o no a través de un {@code return} del tipo {@code Boolean}."; 
//   startString = "    *  ";
//   script += parserStringSize(parserString, startString, true);      
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * @return {@code Boolean}\n";
//   script += "    *  <p>\n";
//   parserString = "Devolvemos el valor 'true' o 'false' con respecto a si está vacía de elementos o no, la lista de la propiedad privada '" + variableParamClassName + "', devuelve 'true' en caso de estar vacía y 'false' en caso de presentar elementos."; 
//   startString = "    *   ";
//   script += parserStringSize(parserString, startString, true);      
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//  
//  /**
//    *
//    */  
//  public static String getElementListIsEmptyScript(
//         final XsdSchema schemaParam,      
//         final XsdElement elementParam) {
//   String script = ""; 
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String variableParamClassName =
//    UtilFormatString.unCapitalize(variableClassName + "List");
//   script += "  public final Boolean\n";
//   script += "    is" + UtilFormatString.capitalize(variableParamClassName) + "Empty() {\n";
//   script += "\n";   
//   script += "   /*\n";
//   script += "    * Comprobamos si está definida la lista, sino la creamos.\n";
//   script += "    */\n";
//   script += "   if (this." + variableParamClassName + " == null) {\n";
//   script += "    this." + variableParamClassName + "\n";
//   script += "     = new ArrayList<" + variableClassName + ">();\n";
//   script += "   }\n";
//   script += "\n";      
//   script += "   /*\n";
//   script += "    * Devolvemos el estado de la lista.\n";
//   script += "    */\n";
//   script += "   return new Boolean(this." + variableParamClassName + ".isEmpty());\n";
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getAttributeStaticDefinitionScriptJavaDoc( 
//         final XsdAttribute attributeParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   script += "  /**\n";
//   parserString = "Variable estática y pública de tipo {@code String} que define el nombre del atributo '" + attributeParam.getAttributeName() + "' de la etiqueta XML."; 
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);  
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getAttributeStaticDefinitionScript( 
//         final XsdAttribute attributeParam) {
//   String script = "";
//   script += "  public static final String XML_TAG_ATTRIBUTE_";
//   script += UtilFormatString.upperCase(attributeParam.getAttributeName()).replaceAll("-", "_");
//   script += "_NAME\n";
//   script += "   = \"" + attributeParam.getAttributeName() + "\"; //$NON-NLS-1$\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getAttributeDefinitionScriptJavaDoc( 
//         final XsdAttribute attributeParam) {
//   String script = "";
//   script += "  /**\n";
//   script += "    * Atributo '" + attributeParam.getAttributeName() + "' de la etiqueta XML.\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getAttributeDefinitionScript( 
//         final XsdSchema schemaParam,
//         final XsdElement elementParam,         
//         final XsdAttribute attributeParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";
//   String stopString = "";  
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//    prefix
//  + UtilFormatString.capitalize(
//     UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//    )
//  + "XDTO";
//   String varibleName = variableClassName + ".XML_TAG_ATTRIBUTE_" + UtilFormatString.upperCase(attributeParam.getAttributeName()).replaceAll("-", "_") + "_NAME";   
////   script += "  @XsdAttributeAnnotation(\n";
////   script += "   name = \"" + attributeParam.getAttributeName() + "\",\n";
////   String attribute = schemaParam.getSchemaNameSpace();
////   if(!attributeParam.getAttributeNameSpace().equals("")) {
////    attribute = attributeParam.getAttributeNameSpace();
////   }
////   script += "   namespace = \"" + attribute + "\"";
////   
////   if(!(attributeParam.getAttributeAnnotation() == null)) {    
////    // Comprobamos si tiene documentación.    
////    if(attributeParam.getAttributeAnnotation().getAnnotationDocumentationListSize() > 0) {
////     script += ",\n";       
////     script += "   documentation = {\n";
////     for(int i = 0; i < attributeParam.getAttributeAnnotation().getAnnotationDocumentationListSize(); i++) {
////      XsdDocumentation docElement = attributeParam.getAttributeAnnotation().getAnnotationDocumentationListElement(i);
////      script += "    @XsdDocumentationAnnotation(\n";
////      script += "     lang = \"" + docElement.getDocumentationLang() + "\",\n";
////      script += "     comment = \"";      
////      parserString = docElement.getDocumentationTagValue();
////      startString = "             + \"";
////      stopString = "\"";
////      script += parserStringSize(parserString, startString, stopString, false);
////      if(i < attributeParam.getAttributeAnnotation().getAnnotationDocumentationListSize() - 1) {
////       script += "    ),\n";
////      } else {
////       script += "    )\n";
////      }
////     }
////     script += "   }";
////    }
////    
////    // comprobamos si tiene una definición de base.
////    if(!attributeParam.getAttributeAnnotation().getAnnotationBase().equals("")) {
////     script += ",\n";
////     script += "   base = \"" + attributeParam.getAttributeAnnotation().getAnnotationBase() + "\""; 
////    }
////    
////    // comprobamos si tiene restricciones
////    if(attributeParam.getAttributeAnnotation().getElementRestrictionListSize() > 0) {
////     script += ",\n";
////     script += "   restriction = {\n";
////     for(int i = 0; i < attributeParam.getAttributeAnnotation().getElementRestrictionListSize(); i++) {
////      XsdRestriction restrictionElement = attributeParam.getAttributeAnnotation().getElementRestrictionListElement(i);
////      script += "    @XsdBaseRestrictionAnnotation(\n";
////      script += "     type = XsdBaseRestrictionAnnotationType." + restrictionElement.getRestrictionType() + ",\n";
////      script += "     value = \"" + restrictionElement.getRestrictionValue() + "\"\n"; 
////      if(i < attributeParam.getAttributeAnnotation().getElementRestrictionListSize() - 1) {
////       script += "    ),\n";
////      } else {
////       script += "    )\n";
////      }
////     }
////     script += "   }";      
////    }
////    
////    //  comprobamos si la obligatoriedad
////    script += ",\n";
////    script += "   required = " + attributeParam.getAttributeAnnotation().getAnnotationRequired() + "\n";     
////   } else {
////    script += "\n";
////   }   
////   script += "  )\n";
//   
//   script += "  private final transient XmlAttributeDTO xmlAttribute";
//   script += UtilFormatString.capitalizeAll(attributeParam.getAttributeName(), '-').replaceAll("-", "");
//   script += "Pattern\n";
//   script += "   = new XmlAttributeDTO(\n";
//   script += "    " + varibleName;
//   script += ",\n";
//   script += "    ";   
//   if (attributeParam.getAttributeDefaultValue().equals(ExternalSystem.get("EMPTY"))) {
//    script += "ExternalSystem.get(ExternalSystem._EMPTY_),\n";
//   } else {
//    script += "\"" + attributeParam.getAttributeDefaultValue() + "\",\n";
//   }
//   script += "    ";      
//   if(attributeParam.getAttributeNameSpace().equals("")) {
//    script += "ExternalSystem.get(ExternalSystem._EMPTY_),\n";       
//   } else {   
//    script += "\"" + attributeParam.getAttributeNameSpace() + "\",\n";
//   }
//   script += "    ";      
//   if (attributeParam.getAttributeNameSpace().equals(ExternalSystem.get("EMPTY"))) {
//    script += "false";
//   } else {
//    script += "true";
//   }
//   script += ", ";
//   if (attributeParam.isAttributeRequired()) {
//    script += "true";
//   } else {
//    script += "false";
//   }      
//   script += "\n";
//   script += "  );\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getAttributeSimpleConstructorScript( 
//         final XsdAttribute attributeParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";   
//   String attributeName = "xmlAttribute" + UtilFormatString.capitalizeAll(attributeParam.getAttributeName(), '-').replaceAll("-", "") + "Pattern";
//   script += "\n";
//   script += "   /*\n";
//   parserString = "Inicializamos el atributo '" + attributeName + "', con valores por defecto."; 
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);
//   script += "    */\n";
//   script += "   xmlAttributeDTO = this." + attributeName + ";\n";
//   script += "   this.addXmlAttributesElement(\n";
//   script += "    xmlAttributeDTO);\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getAttributeComplexConstructorScript( 
//         final XsdAttribute attributeParam) {
//   String script = "";
//   String parserString = "";
//   String startString = "";     
//   String attributeName = "xmlAttribute" + UtilFormatString.capitalizeAll(attributeParam.getAttributeName(), '-').replaceAll("-", "") + "Pattern";
//   script += "\n";
//   script += "   /*\n";
//   parserString = "Inicializamos el atributo '" + attributeName + "', con valores definidos a través del contenido del objeto 'xmlElementParam'."; 
//   startString = "    * ";
//   script += parserStringSize(parserString, startString, true);   
//   script += "    */\n";
//   script += "   xmlAttributeDTO = this." + attributeName + ";\n";
//   script += "   this.addXmlAttributesElement(\n";
//   script += "    XmlParserUtil.parserAttribute(\n";
//   script += "     xmlElementParam,\n";   
//   script += "     xmlAttributeDTO));\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareInicializeDefaultAttributesMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);      
//   String variableClassName =
//     prefix
//   + UtilFormatString.capitalize(
//      UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//     )
//   + "XDTO";
//   script += "  /**\n"; 
//   script += "    * <p>\n";
//   script += "    *  Método 'Helper' de la clase '" + variableClassName + "'.\n";
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   script += "    *  Este método representa una función de uso común para la clase y esta\n";
//   script += "    *  destinada a servir de ayuda a otros procesos más complejos dentro de la\n";
//   script += "    *  clase, suministrando una funcionalidad básica de inicialización de\n";
//   script += "    *  atributos, con valores por defecto.\n";
//   script += "    * <p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareInicializeDefaultAttributesMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  private void\n";
//   script += "    inicializeDefaultAttributes() {\n";
//   script += "   XmlAttributeDTO xmlAttributeDTO;\n";
//   for(int i = 0; i < elementParam.getElementAttributeListSize(); i++) {
//    script += DemoScriptUtil.getAttributeSimpleConstructorScript(elementParam.getElementAttributeListElement(i));
//   }    
//   script += "  }\n";
//   script += "\n";   
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareInicializeDefaultElementsMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);      
//   String variableClassName =
//     prefix
//   + UtilFormatString.capitalize(
//      UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//     )
//   + "XDTO";
//   script += "  /**\n"; 
//   script += "    * <p>\n";
//   script += "    *  Método 'Helper' de la clase '" + variableClassName + "'.\n";
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   script += "    *  Este método representa una función de uso común para la clase y esta\n";
//   script += "    *  destinada a servir de ayuda a otros procesos más complejos dentro de la\n";
//   script += "    *  clase, suministrando una funcionalidad básica de inicialización de las\n";
//   script += "    *  distintas propiedades de la clase, con valores por defecto.\n";
//   script += "    * <p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareInicializeDefaultElementsMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  private void\n";
//   script += "    inicializeDefaultElements() {\n";
//   if (elementParam instanceof XsdSimpleElement) {
//	script += "\n";
//    script += "   /*\n";
//    script += "    * Al ser un nodo de texto, inicializamos las propiedades corespondientes\n";
//    script += "    * a este tipo de objetos.\n";
//    script += "    */\n";
//    script += "   super.setTagValue(null);\n";
//    script += "   super.setUseCDATA(false);\n";
//   } else if (elementParam instanceof XsdComplexElement) {  	   
//    if (!((XsdComplexElement) elementParam).isElementElementListEmpty()) {
//     for(int i = 0; i < ((XsdComplexElement) elementParam).getElementElementListSize(); i++) {
//      if (((XsdComplexElement) elementParam).getElementElementListElement(i).getElementMaxOccurs() == -1) {
//       script += DemoScriptUtil.getElementListSimpleConstructorScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//      } else {
//       script += DemoScriptUtil.getElementSimpleConstructorScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//      }          
//     }
//    }
//   }
//   script += "  }\n";
//   script += "\n";   
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareInicializeParserAttributesMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);   
//   String variableClassName =
//     prefix
//   + UtilFormatString.capitalize(
//      UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//     )
//   + "XDTO";
//   script += "  /**\n"; 
//   script += "    * <p>\n";
//   script += "    *  Método 'Helper' de la clase '" + variableClassName + "'.\n";
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   script += "    *  Este método representa una función de uso común para la clase y esta\n";
//   script += "    *  destinada a servir de ayuda a otros procesos más complejos dentro de la\n";
//   script += "    *  clase, suministrando una funcionalidad básica de inicialización de\n";
//   script += "    *  atributos, con valores definidos a través del parámetro recibido\n";
//   script += "    *  'xmlElementParam' de tipo {@code Element}.\n";
//   script += "    * <p>\n";
//   script += "    *\n";
//   script += "    * @param xmlElementParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code Element}, que contiene una estructura XML que\n";
//   script += "    *   permite definir los valores de los distintos atributos que forman parte\n";
//   script += "    *   de la clase.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareInicializeParserAttributesMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  private void\n";
//   script += "    inicializeParserAttributes(\n";
//   script += "          final Element\n";
//   script += "           xmlElementParam) {\n";
//   script += "   XmlAttributeDTO xmlAttributeDTO;\n";
//   for(int i = 0; i < elementParam.getElementAttributeListSize(); i++) {
//	   script += DemoScriptUtil.getAttributeComplexConstructorScript(elementParam.getElementAttributeListElement(i));
//   }    
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareInicializeParserElementsMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);      
//   String variableClassName =
//     prefix
//   + UtilFormatString.capitalize(
//      UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//     )
//   + "XDTO";
//   script += "  /**\n"; 
//   script += "    * <p>\n";
//   script += "    *  Método 'Helper' de la clase '" + variableClassName + "'.\n";
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   script += "    *  Este método representa una función de uso común para la clase y esta\n";
//   script += "    *  destinada a servir de ayuda a otros procesos más complejos dentro de la\n";
//   script += "    *  clase, suministrando una funcionalidad básica de inicialización de las\n";
//   script += "    *  distintas propiedades de la clase, con valores definidos a través del\n";
//   script += "    *  parámetro recibido 'xmlElementParam' de tipo {@code Element}.\n";
//   script += "    * <p>\n";
//   script += "    *\n";
//   script += "    * @param xmlElementParam\n";
//   script += "    *  <p>\n";
//   script += "    *   Parámetro de tipo {@code Element}, que contiene una estructura XML que\n";
//   script += "    *   permite definir los valores de las distintas propiedades que forman\n";
//   script += "    *   parte de la clase.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareInicializeParserElementsMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   script += "  private void\n";
//   script += "    inicializeParserElements(\n";
//   script += "          final Element\n";
//   script += "           xmlElementParam) {\n";
//   if (elementParam instanceof XsdSimpleElement) {
//    script += "\n";
//    script += "   /*\n";
//    script += "    * Al ser un nodo de texto, procesamos su contenido y lo añadimos al valor\n";
//    script += "    * del objeto.\n";
//    script += "    *\n";
//    script += "    * Comprobamos si el elemento utiliza la etiqueta 'CDATA'.\n";
//    script += "    */\n";
//    script += "   if (XmlParserUtil.isContentInCDATA(xmlElementParam)) {\n";
//    script += "\n";       
//    script += "    /*\n";
//    script += "     * El contenido esta dentro de un 'CDATA' añadimos el valor y definimos el\n";
//    script += "     * objeto como tal.\n";
//    script += "     */\n";
//    script += "    super.setTagValue(XmlParserUtil.getCDATATextContent(xmlElementParam));\n";
//    script += "    super.setUseCDATA(true);\n";
//    script += "   } else {\n";
//    script += "\n";       
//    script += "    /*\n";
//    script += "     * El contenido esta dentro de la propia etiqueta añadimos el valor y\n";
//    script += "     * definimos el objeto como tal.\n";
//    script += "     */\n";
//    script += "    super.setTagValue(XmlParserUtil.getTextContent(xmlElementParam));\n";
//    script += "    super.setUseCDATA(false);\n";
//    script += "   }\n";
//   } else if (elementParam instanceof XsdComplexElement) {
//    script += "\n";   	   
//    script += "   /*\n";
//    script += "    * Obtenemos la identación del objeto y la utilizamos en todas las\n";
//    script += "    * definiciones de objetos.\n";
//    script += "    */\n";
//    script += "   final Integer indentation = this.getXmlIndentation();\n";	
//    script += "\n";       
//    script += "   /*\n";
//    script += "    * Obtenemos el 'NameSpace' definido en el objeto y lo utilizamos en todas\n"; 
//    script += "    * las definiciones de objetos.\n"; 
//    script += "    */\n";
//    script += "   final String nameSpace = this.getXmlNameSpace();\n";
//    if (!((XsdComplexElement) elementParam).isElementElementListEmpty()) {
//     for(int i = 0; i < ((XsdComplexElement) elementParam).getElementElementListSize(); i++) {
//      if (((XsdComplexElement) elementParam).getElementElementListElement(i).getElementMaxOccurs() == -1) {
//       script += DemoScriptUtil.getElementListComplexConstructorScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//      } else {
//       script += DemoScriptUtil.getElementComplexConstructorScript(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//      }     
//     }
//    }
//   }
//   script += "  }\n";
//   script += "\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareBuildTagContentMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);      
//   String variableClassName =
//     prefix
//   + UtilFormatString.capitalize(
//      UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//     )
//   + "XDTO";
//   script += "  /**\n"; 
//   script += "    * <p>\n";
//   script += "    *  Método 'Helper' de la clase '" + variableClassName + "'.\n";
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   script += "    *  Este método representa una función de uso común para la clase y está\n";
//   script += "    *  destinada a servir de ayuda a otros procesos más complejos de la clase,\n";
//   script += "    *  suministrando una funcionalidad básica de generación de salidas de\n";
//   script += "    *  texto, construyendo a partir de los valores de las propiedades de la\n";
//   script += "    *  clase, el contenido interno de una etiqueta XML.\n";
//   script += "    * <p>\n";
//   script += "    *\n";
//   script += "    * @return {@code String}\n";
//   script += "    *  <p>\n";
//   script += "    *   Devolvemos el contenido interno de una etiqueta XML, construida a\n";
//   script += "    *   partir de los valores de las propiedades de la clase.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareBuildTagContentMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";   
//   script += "  private String\n";
//   script += "    buildTagContent() {\n";
//   script += "   final StringBuilder xmlResult = new StringBuilder();\n";
//   if (elementParam instanceof XsdComplexElement) {
//    if (((XsdComplexElement) elementParam).haveSimpleElement()) {
//     script += "   XdtoObjectViewDefinition xdtoObjectViewDefinition;\n";
//    }	   
//    if (((XsdComplexElement) elementParam).haveListElement()) {   
//     script += "   List<? extends XdtoObjectViewDefinition> xdtoObjectViewDefinitionList;\n";
//    }
//   }
//   script += "\n";
//   script += "   /*\n";
//   script += "    * Procesamos uno a uno cada uno de los elementos, que componen el objeto y\n";
//   script += "    * vamos construyendo el resultado XML.\n";
//   script += "    */\n";   
//   if (!((XsdComplexElement) elementParam).isElementElementListEmpty()) {
//    for(int i = 0; i < ((XsdComplexElement) elementParam).getElementElementListSize();i++) {
//     if (((XsdComplexElement) elementParam).getElementElementListElement(i).getElementMaxOccurs() == -1) {
//      script += "   xdtoObjectViewDefinitionList\n";
//      //String prefix = getPrefix(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//      String prefix = getPrefixOfFiles(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));     
//      String variableClassName =
//       prefix
//     + UtilFormatString.capitalize(
//        UtilFormatString.capitalizeAll(((XsdComplexElement) elementParam).getElementElementListElement(i).getElementName(), '-').replaceAll("-", "")
//       )
//     + "XDTO";
//      script += "    = this.get" + variableClassName + "List();\n";
//      script += "   xmlResult.append(\n";      
//      script += "    XmlParserUtil.getTagContent(xdtoObjectViewDefinitionList));\n";         
//     } else {
//      script += "   xdtoObjectViewDefinition\n";
//      //String prefix = getPrefix(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));
//      String prefix = getPrefixOfFiles(schemaParam, ((XsdComplexElement) elementParam).getElementElementListElement(i));     
//      String variableClassName =
//       prefix
//     + UtilFormatString.capitalize(
//        UtilFormatString.capitalizeAll(((XsdComplexElement) elementParam).getElementElementListElement(i).getElementName(), '-').replaceAll("-", "")
//       )
//     + "XDTO";
//      script += "    = this.get" + variableClassName + "Element();\n";
//      script += "   xmlResult.append(\n";      
//      script += "    XmlParserUtil.getTagContent(\n";
//      script += "     getEntityClass(), xdtoObjectViewDefinition));\n";      
//     }
//     if (i < ((XsdComplexElement) elementParam).getElementElementListSize() - 1) {
//      script += "\n";
//     }            
//    }
//   }
//   script += "\n";
//   script += "   return xmlResult.toString();\n";   
//   script += "  }\n";
//   script += "\n";   
//   return script;
//  }
//
//  
//  
//  /**
//    *
//    */
//  public static String getDeclareReferenceClassMethodScriptJavaDoc(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);      
//   String variableClassName =
//     prefix
//   + UtilFormatString.capitalize(
//      UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//     )
//   + "XDTO";
//   script += "  /**\n"; 
//   script += "    * <p>\n";
//   script += "    *  Método 'Reference' de la clase '" + variableClassName + "'.\n";
//   script += "    * </p>\n";
//   script += "    *\n";
//   script += "    * <p>\n";
//   script += "    *  Este método devuelve la propia definicion de la clase que estamos\n";
//   script += "    *  utilizando, util para casos en los que sea necesario instanciar la clase\n";
//   script += "    *  de manera reflexiva.\n";
//   script += "    * <p>\n";
//   script += "    *\n";
//   script += "    * @return Class<EclipseProjectProjectDescriptionXDTO>\n";
//   script += "    *  <p>\n";
//   script += "    *   Devolvemos la propia definicion de la clase que estamos utilizando.\n";
//   script += "    *  </p>\n";
//   script += "    */\n";
//   return script;
//  }
//
//  /**
//    *
//    */
//  public static String getDeclareReferenceClassMethodScript(
//         final XsdSchema schemaParam,
//         final XsdElement elementParam) {
//   String script = "";
//   String prefix = getPrefixOfFiles(schemaParam);      
//   String variableClassName =
//     prefix
//   + UtilFormatString.capitalize(
//      UtilFormatString.capitalizeAll(elementParam.getElementName(), '-').replaceAll("-", "")
//     )
//   + "XDTO"; 
//   script += "  public static final Class<" + variableClassName + ">\n";
//   script += "    getEntityClass() {\n";
//   script += "   return " + variableClassName + ".class;\n";   
//   script += "  }\n";
//   script += "\n";   
//   return script;
//  }
//  

//  
//  /**
//    *
//    */
//  private static String getPrefixOfFiles(
//          final XsdSchema schemaParam,
//          final XsdElement elementParam) {
//   String prefixMain = getPrefixOfFiles(schemaParam);
//   String prefixElement = getPrefixOfFiles(elementParam.getElementSchema());
//   if(!prefixMain.equals(prefixElement)) {
//    return prefixElement;
//   }
//   return prefixMain;
//  }
//
//  /**
//    *
//    */
//  private static String getPrefixOfFiles(
//          final XsdSchema schemaParam) {
//   String prefix = "";
//   // Comprobamos si los ficheros utilizan prefijo.
//   if (schemaParam.isUseFileSchemaPrefix()) {
//    prefix = UtilFormatString.capitalize(schemaParam.getSchemaPrefix());
//   }
//   return prefix;
//  }
//  
//
//  /**
//    *
//    */
//  private static String getPrefixOfTags(
//          final XsdSchema schemaParam) {
//   String prefix = "";
//   // Comprobamos si los ficheros utilizan prefijo.
//   if (schemaParam.isUseTagSchemaPrefix()) {
//    prefix = UtilFormatString.unCapitalize(schemaParam.getSchemaPrefix());
//   }
//   return prefix;
//  }
//  
  
  private final static String getPackageNameValue(
          final ProjectDTO projectParam) {
   String script = "";
   script += "es";
   return script;
  }  
  
  private final static String getClassNameValue(
          final ProjectDTO projectParam) {
   String script = "";
   script += "Demo" + UtilFormatString.capitalizeAll(projectParam.getProjectName());
   return script;
  }

  private final static String getClassXsdSchemaObjectNameValue(
          final XsdSchema xsdSchema) {
   String script = "";
   script += "xsd" + UtilFormatString.capitalizeAll(xsdSchema.getSchemaPrefix()) + "Schema";
   return script;
  }

  private final static String parserStringSize(
          final String parserStringParam,
          final String startStringParam,
          final String stopStringParam,
          final boolean useStartFirstLineParam) {
   String[] words = parserStringParam.split(" ");
   int wordsPosition = 0;
   String result = "";
   int resultLeght = 0;
   while(wordsPosition < words.length) {
    if(wordsPosition > 0) {
     result += startStringParam;    
    } else { 
     if(useStartFirstLineParam) {
      result = startStringParam;
     }
    }
    resultLeght = startStringParam.length() + stopStringParam.length();
    while((resultLeght <= 80) && (wordsPosition < words.length)) {       
     result = result + words[wordsPosition] + " ";
     resultLeght += words[wordsPosition].length() + 1; 
     wordsPosition++;
    }
    if (wordsPosition < words.length) {
     wordsPosition--;        
     result = result.substring(0, (result.length() - words[wordsPosition].length() - 1));
     resultLeght = resultLeght - words[wordsPosition].length() - 2;
    }
    if(resultLeght > 80) {
     wordsPosition--;        
     result = result.substring(0, (result.length() - words[wordsPosition].length() - 1));    
    }
    result = result.substring(0, result.length() - 1);
    result += stopStringParam;
    result += "\n";
   }
   return result;
  }

  private final static String parserStringSize(
          final String parserStringParam,
          final String startStringParam,
          final boolean useStartFirstLineParam) {
   return parserStringSize(parserStringParam, startStringParam, "", useStartFirstLineParam);
  }

 }
