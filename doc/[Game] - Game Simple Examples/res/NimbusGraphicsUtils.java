/*
* $Id: NimbusGraphicsUtils.java,v 1.9 2005/12/05 15:00:55 kizune Exp $
*
* Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
* Santa Clara, California 95054, U.S.A. All rights reserved.
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/
package org.jdesktop.swingx.plaf.nimbus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.Kernel;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthGraphicsUtils;

/**
 * NimbusGraphicsUtils - extends SynthGraphicsUtils to force all Synth painted text to be antialiased and provides some
 * static helper methods.
 *
 * @author Created by Jasper Potts (Jan 4, 2007)
 * @version 1.0
 */
public class NimbusGraphicsUtils extends SynthGraphicsUtils {
    private Map<?,?> desktopHints;

    /**
     * Get rendering hints from a Graphics instance.
     * "hintsToSave" is a Map of RenderingHint key-values.
     * For each hint key present in that map, the value of that
     * hint is obtained from the Graphics and stored as the value
     * for the key in savedHints.
     * 
     * @param g2d the graphics surface
     * @param hintsToSave the list of rendering hints to set on the graphics
     * @param savedHints a set where to save the previous rendering hints, might be null
     * @return the previous set of rendering hints
     */
    public static RenderingHints getRenderingHints(Graphics2D g2d,
                                                   Map<?, ?> hintsToSave,
                                                   RenderingHints savedHints) {
         if (savedHints == null) {
             savedHints = new RenderingHints(null);
         } else {
             savedHints.clear();
         }
         if (hintsToSave.size() == 0) {
             return savedHints;
         }
         /* RenderingHints.keySet() returns Set */
         for (Object o : hintsToSave.keySet()) {
             RenderingHints.Key key = (RenderingHints.Key)o;
             Object value = g2d.getRenderingHint(key);
             savedHints.put(key, value);
         }
         return savedHints;
    }

    /**
     * Overrides paintText in SynthGraphicsUtils to force all Synth painted text to be antialiased
     */
    @Override
    public void paintText(SynthContext ss, Graphics g, String text, int x, int y, int mnemonicIndex) {
        Graphics2D g2 = (Graphics2D) g;

        // XXX: In Java SE 6, Synth already uses the desktop hints, this code should just check whether java.version < 1.6
        if (desktopHints == null) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            desktopHints = (Map<?, ?>) (toolkit.getDesktopProperty("awt.font.desktophints"));
        }

        Object oldAA = null;
        RenderingHints oldHints = null;

        if (desktopHints != null) {
            oldHints = getRenderingHints(g2, desktopHints, null);
            g2.addRenderingHints(desktopHints);
        } else {
            oldAA = g2.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }

        super.paintText(ss, g, text, x, y, mnemonicIndex);

        if (oldHints != null) {
            g2.addRenderingHints(oldHints);
        } else if (oldAA != null) {
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                oldAA);
        }
    }

    /**
     * Load an image using ImageIO from resource in org.jdesktop.swingx.plaf.nimbus.images. Catches and prints all
     * Exceptions so that it can safely be used in a static context.
     *
     * @param imgName The name of the image to load, eg. "border.png"
     * @return The loaded image
     */
    public static BufferedImage loadImage(String imgName) {
        try {
            return ImageIO.read(NimbusGraphicsUtils.class.getClassLoader().getResource("org/jdesktop/swingx/plaf/nimbus/images/" + imgName));
        } catch (Exception e) {
            System.err.println("Error loading image \"org/jdesktop/swingx/plaf/nimbus/images/" + imgName+"\"");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a Color object from a web color string of the form "FF00AB" or "#FF00AB".
     *
     * @param c The color string
     * @return The Color described
     */
    public static Color getWebColor(String c){
        if (c.startsWith("#")) c = c.substring(1);
        return new Color(
                Integer.parseInt(c.substring(0,2),16),
                Integer.parseInt(c.substring(2,4),16),
                Integer.parseInt(c.substring(4,6),16)
        );
    }

    /**
     * Get a Color that is 50% inbetween the two web colors given. The Web colors are of the form "FF00AB" or "#FF00AB".
     *
     * @param c1 The first color string
     * @param c2 The second color string
     * @return The Color middle color
     */
    public static Color getMidWebColor(String c1,String c2){
        if (c1.startsWith("#")) c1 = c1.substring(1);
        if (c2.startsWith("#")) c2 = c2.substring(1);
        int rTop = Integer.parseInt(c1.substring(0,2),16);
        int gTop = Integer.parseInt(c1.substring(2,4),16);
        int bTop = Integer.parseInt(c1.substring(4,6),16);
        int rBot = Integer.parseInt(c2.substring(0,2),16);
        int gBot = Integer.parseInt(c2.substring(2,4),16);
        int bBot = Integer.parseInt(c2.substring(4,6),16);
        int rMid = rTop+((rBot-rTop)/2);
        int gMid = gTop+((gBot-gTop)/2);
        int bMid = bTop+((bBot-bTop)/2);
        return new Color(rMid,gMid,bMid);
    }

    // =================================================================================================================
    // Utils for focus

    /**
     * <p>Returns a new translucent compatible image of the specified width
     * and height.</p>
     *
     * @param width the width of the new image
     * @param height the height of the new image
     * @return a new translucent compatible <code>BufferedImage</code> of the
     *   specified width and height
     */
    public static BufferedImage createCompatibleTranslucentImage(int width,
                                                                 int height) {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().
                    getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width, height,
                                                   Transparency.TRANSLUCENT);
    }

    /**
     * Extract the alpha channel from a image as a greyscale image
     *
     * @param srcImg the source image
     * @param dstWidth The width of the destintaion, allows padding to be added to alpha
     * @param dstHeight The height of the destintaion, allows padding to be added to alpha
     * @return new greyscale image with the alpha channel from <code>srcImg</code> in the center
     */
    public static BufferedImage getAlphaMask(BufferedImage srcImg, int dstWidth, int dstHeight){
        int offsetX = (dstWidth-srcImg.getWidth())/2;
        int offsetY = (dstHeight-srcImg.getHeight())/2;
         final BufferedImage greyImg = new BufferedImage(
                 dstWidth,dstHeight,BufferedImage.TYPE_BYTE_GRAY);
        int[] buffer = new int[srcImg.getWidth()*srcImg.getHeight()];
        WritableRaster a = srcImg.getAlphaRaster();
        a.getPixels(0,0,srcImg.getWidth(),srcImg.getHeight(),buffer);
        greyImg.getRaster().setPixels(offsetX,offsetY,srcImg.getWidth(),srcImg.getHeight(),buffer);
        return greyImg;
    }

    /**
     * Creates and returns a new color image filled with color <code>c</code> and with alpha channel <code>alpha</code>.
     *
     * @param alpha The alpha channel for the new image
     * @param c The fill color
     * @return New image filled with <code>c</code> and with alpha channel <code>alpha</code>
     */
    public static BufferedImage createColorMaskImage(BufferedImage alpha, Color c){
        final int pixel = c.getRed()<<16 | c.getGreen() << 8 | c.getBlue();
        final int w=alpha.getWidth(), h=alpha.getHeight();
        BufferedImage dstImg = createCompatibleTranslucentImage(w,h);
        byte[] alphaData = ((DataBufferByte) alpha.getRaster().getDataBuffer()).getData();
        int[] dstData = ((DataBufferInt) dstImg.getRaster().getDataBuffer()).getData();
        for (int i = 0; i < dstData.length; i++) {
            dstData[i] = ((int)alphaData[i]) << 24 | pixel;
        }
        return dstImg;
    }

    /**
     * Generate a pair of 1D Gaussian kernels
     *
     * @param radius the blur radius
     * @return array containg horizontal and vertical kernels
     */
    public static Kernel[] getSeparateGaussianKernel(int radius) {
        float kernel[] = new float[radius * 2 + 1];
        double sum = 0.;
        int w = 2 * radius + 1;

        double deviation = radius / 3.; // This guarantees non zero values in the kernel
        double devSqr2 = 2 * Math.pow(deviation, 2);
        double piDevSqr2 = Math.PI * devSqr2;
        double piDevSqrt = Math.sqrt(piDevSqr2);

        for (int i = 0; i < w; i++) {
            kernel[i]
                    = (float) (Math.pow(Math.E,
                            -((i - radius) * (i - radius)) / devSqr2)
                    /
                    piDevSqrt);
            sum += kernel[i];
        }

        // Make elements sum to 1
        for (int i = 0; i < w; i++) {
            kernel[i] /= sum;
        }

        // Build two kernels
        Kernel hk = new Kernel(w, 1, kernel);
        Kernel vk = new Kernel(1, w, kernel);

        return new Kernel[]{hk, vk};
    }

}