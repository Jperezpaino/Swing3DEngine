package es.noa.rad.demo.swing.menupopup.res.java2s;

import javax.swing.JPopupMenu;

public class MainLightWeightPopup {
  public static void main(String[] argv) throws Exception {
    boolean lwPopup = JPopupMenu.getDefaultLightWeightPopupEnabled(); // true

    // Globally use heavyweight components for all popup menus
    JPopupMenu.setDefaultLightWeightPopupEnabled(false);
  }
}