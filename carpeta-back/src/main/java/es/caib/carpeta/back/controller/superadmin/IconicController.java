package es.caib.carpeta.back.controller.superadmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class IconicController {

    @RequestMapping(value = "/superadmin/iconic", method = RequestMethod.GET)
    public ModelAndView iconicGET(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView("iconic");
        return mav;

    }

    @RequestMapping(value = "/superadmin/iconic", method = RequestMethod.POST)
    public void iconicPOST(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String iconic = request.getParameter("iconic");
        String newColorHex = request.getParameter("color");

        String urlStr = "https://raw.githubusercontent.com/iconic/open-iconic/master/png/" + iconic + "-8x.png";

        URL url = new URL(urlStr);

        InputStream in = url.openStream();
        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream fos = new ByteArrayOutputStream();

        byte[] data = new byte[1024];
        int count;
        while ((count = bis.read(data, 0, 1024)) != -1) {
            fos.write(data, 0, count);
        }

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(fos.toByteArray()));

        Color newColor;
        {
            final Color c = Color.decode(newColorHex);
            newColor = new Color((c.getRed() << 16) + (c.getGreen() << 8) + c.getBlue());

        }

        int width = image.getWidth();
        int height = image.getHeight();

        int r = newColor.getRed();
        int g = newColor.getGreen();
        int b = newColor.getBlue();

        WritableRaster raster = image.getRaster();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {

                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = r;
                pixels[1] = g;
                pixels[2] = b;
                raster.setPixel(xx, yy, pixels);

            }
        }

        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + iconic + ".png\"");

        ImageIO.write(image, "png", response.getOutputStream());

    }
}
