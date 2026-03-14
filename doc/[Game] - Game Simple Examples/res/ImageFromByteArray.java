package es.noa.rad.demo.swt;

/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

/*
 * Cursor example snippet: create a color cursor from a source and a mask
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 * 
 * @since 3.0
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ImageFromByteArray {

  static byte[] srcData = { (byte) 0x11, (byte) 0x11, (byte) 0x11, (byte) 0x00, (byte) 0x00,
      (byte) 0x11, (byte) 0x11, (byte) 0x11, (byte) 0x11, (byte) 0x10, (byte) 0x00, (byte) 0x01,
      (byte) 0x10, (byte) 0x00, (byte) 0x01, (byte) 0x11, (byte) 0x11, (byte) 0x00, (byte) 0x22,
      (byte) 0x01, (byte) 0x10, (byte) 0x33, (byte) 0x00, (byte) 0x11, (byte) 0x10, (byte) 0x02,
      (byte) 0x22, (byte) 0x01, (byte) 0x10, (byte) 0x33, (byte) 0x30, (byte) 0x01, (byte) 0x10,
      (byte) 0x22, (byte) 0x22, (byte) 0x01, (byte) 0x10, (byte) 0x33, (byte) 0x33, (byte) 0x01,
      (byte) 0x10, (byte) 0x22, (byte) 0x22, (byte) 0x01, (byte) 0x10, (byte) 0x33, (byte) 0x33,
      (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
      (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x11, (byte) 0x11, (byte) 0x01, (byte) 0x10,
      (byte) 0x11, (byte) 0x11, (byte) 0x10, (byte) 0x01, (byte) 0x11, (byte) 0x11, (byte) 0x01,
      (byte) 0x10, (byte) 0x11, (byte) 0x11, (byte) 0x10, (byte) 0x00, (byte) 0x00, (byte) 0x00,
      (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x10, (byte) 0x44,
      (byte) 0x44, (byte) 0x01, (byte) 0x10, (byte) 0x55, (byte) 0x55, (byte) 0x01, (byte) 0x10,
      (byte) 0x44, (byte) 0x44, (byte) 0x01, (byte) 0x10, (byte) 0x55, (byte) 0x55, (byte) 0x01,
      (byte) 0x10, (byte) 0x04, (byte) 0x44, (byte) 0x01, (byte) 0x10, (byte) 0x55, (byte) 0x50,
      (byte) 0x01, (byte) 0x11, (byte) 0x00, (byte) 0x44, (byte) 0x01, (byte) 0x10, (byte) 0x55,
      (byte) 0x00, (byte) 0x11, (byte) 0x11, (byte) 0x10, (byte) 0x00, (byte) 0x01, (byte) 0x10,
      (byte) 0x00, (byte) 0x01, (byte) 0x11, (byte) 0x11, (byte) 0x11, (byte) 0x11, (byte) 0x00,
      (byte) 0x00, (byte) 0x11, (byte) 0x11, (byte) 0x11, };

  public static void main(String[] args) {
    Display display = new Display();
    Color white = display.getSystemColor(SWT.COLOR_WHITE);
    Color black = display.getSystemColor(SWT.COLOR_BLACK);
    Color yellow = display.getSystemColor(SWT.COLOR_YELLOW);
    Color red = display.getSystemColor(SWT.COLOR_RED);
    Color green = display.getSystemColor(SWT.COLOR_GREEN);
    Color blue = display.getSystemColor(SWT.COLOR_BLUE);

    // Create a source ImageData of depth 4
    PaletteData palette = new PaletteData(new RGB[] { black.getRGB(), white.getRGB(),
        yellow.getRGB(), red.getRGB(), blue.getRGB(), green.getRGB() });
    ImageData sourceData = new ImageData(16, 16, 4, palette, 1, srcData);

    Shell shell = new Shell(display);
    final Image source = new Image(display, sourceData);
    shell.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent e) {
        GC gc = e.gc;
        gc.drawImage(source, 20, 20);
      }
    });
    shell.setSize(150, 150);
    shell.open();

    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    source.dispose();
    display.dispose();
  }
}