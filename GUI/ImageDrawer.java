/*Author: 			  www.codejava.net
* Creation Date: 	unknown
* Due Date: 		  March 3, 2015
* Course: 			  CSC243
* Professor Name: Dr. Spiegel
* Assignment: 		#2 - Racko GUI
* Filename: 		  ImageDrawer.java
* Purpose:		  	This is a utility that scales the image to fit in the canvas of a component.
*/
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
/**
*Draws an image scaled to a component.
*@author www.codejava.net
*/
public class ImageDrawer
{
    /**
    *Draws the image scaled to the component based on the dimensions of the image and component.
    *@param image       Image to scale
    *@param canvas      Component to scale the image to
    *@param g           Graphics to use
    */
    public static void drawScaledImage(Image image, Component canvas, Graphics g)
    {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        double imgAspect = (double) imgHeight / imgWidth;

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        double canvasAspect = (double) canvasHeight / canvasWidth;

        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
            // the image is smaller than the canvas
            x1 = (canvasWidth - imgWidth)  / 2;
            y1 = (canvasHeight - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;

        } else {
            if (canvasAspect > imgAspect) {
                y1 = canvasHeight;
                // keep image aspect ratio
                canvasHeight = (int) (canvasWidth * imgAspect);
                y1 = (y1 - canvasHeight) / 2;
            } else {
                x1 = canvasWidth;
                // keep image aspect ratio
                canvasWidth = (int) (canvasHeight / imgAspect);
                x1 = (x1 - canvasWidth) / 2;
            }
            x2 = canvasWidth + x1;
            y2 = canvasHeight + y1;
        }

        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
    }
}
