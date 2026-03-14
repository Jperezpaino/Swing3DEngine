package mino.ermal.engine3d.svg.interfaces;

import org.w3c.dom.Document;

  /**
   *
   */
  public interface SvgElement {

    /**
     *
     * @return {@code String}
     */
    public String name();

    /**
     *
     * @param _document {@code Document}
     */
    public void toSvgString(
      final Document _document);

  }
