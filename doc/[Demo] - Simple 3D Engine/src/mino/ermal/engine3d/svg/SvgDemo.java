package mino.ermal.engine3d.svg;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

  /**
   *
   */
  public class SvgDemo {

    /**
     *
     * @param args {@code String[]}
     */
    public static void main(
        final String[] args) {
      final Svg svg = new Svg();
      svg.size(new Size(500, 500));
      svg.viewBox(
        new ViewBox(
          new Position(0, 0),
          new Size(500, 500)
        )
      );
      SvgDemo.save(svg);
    }

    private static final void save(
        final Svg _svg) {
      try {
        /* Creamos el documento XML. */
        final DocumentBuilderFactory documentBuilderFactory
          = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder
          = documentBuilderFactory.newDocumentBuilder();
        final Document document
          = documentBuilder.newDocument();

        _svg.toSvgString(document);

        /* Guardamos el documento en un archivo. */
        final TransformerFactory transformerFactory
          = TransformerFactory.newInstance();
        final Transformer transformer
          = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        final DOMSource source = new DOMSource(document);
        final StreamResult result = new StreamResult(new File("output.svg"));
        transformer.transform(source, result);

      } catch (ParserConfigurationException | TransformerException e) {
        e.printStackTrace();
      }
    }

  }


//      // Crear un rectángulo
//      Element rect = doc.createElement("rect");
//      rect.setAttribute("x", "50");
//      rect.setAttribute("y", "50");
//      rect.setAttribute("width", "200");
//      rect.setAttribute("height", "100");
//      rect.setAttribute("fill", "blue");
//      rect.setAttribute("stroke", "black");
//      rect.setAttribute("stroke-width", "3");
//      svg.appendChild(rect);

//      // Crear un círculo
//      Element circle = doc.createElement("circle");
//      circle.setAttribute("cx", "150");
//      circle.setAttribute("cy", "150");
//      circle.setAttribute("r", "50");
//      circle.setAttribute("fill", "red");
//      svg.appendChild(circle);
