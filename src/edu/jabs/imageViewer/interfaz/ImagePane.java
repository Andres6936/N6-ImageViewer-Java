package edu.jabs.imageViewer.interfaz;


import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.*;

import edu.jabs.imageViewer.mundo.Image;

/**
 * Pane to draw the image
 */
public class ImagePane extends JPanel
{
    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Image presented in pane
     */
    private Image image;

    /**
     * Buffered Image
     */
    private BufferedImage drawImage;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds the pane where the image will be displayed.  If there is no image the pane will be empty
     */
    public ImagePane( )
    {
        try
        {
            image = new Image( "data/image.bmp" );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Loading image ...", JOptionPane.ERROR_MESSAGE );
            return;
        }
        drawImage = image.getImageBuffer( );
        setPreferredSize( new Dimension( drawImage.getWidth( ), drawImage.getHeight( ) ) );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It returns the average image color
     * @return average image color
     */
    public Color averageColor( )
    {
        if( image != null )
        {
            return image.averageColor( );
        }
        return null;
    }

    /**
     * It processes the image with the method that make it negative
     */
    public void turnNegative( )
    {
        if( image != null )
        {
            image.turnNegative( );
            repaint( );
        }
    }

    /**
     * It processes the image with the method that make its reflection
     */
    public void mirrorImage( )
    {
        if( image != null )
        {
            image.mirrorImage( );
            repaint( );
        }
    }

    /**
     * It processes the image with the method that make a binarization
     * @param threshold Threshold change
     */
    public void imageBinarization( double threshold )
    {
        if( image != null )
        {
            image.imageBinarization( threshold );
            repaint( );
        }
    }

    /**
     * It processes the image with the method that make a pixelation
     */
    public void pixelImage( )
    {
        if( image != null )
        {
            image.pixelImage( );
            repaint( );
        }
    }

    /**
     * It processes the image with the method for turning to shades of gray
     */
    public void grayScale( )
    {
        if( image != null )
        {
            image.grayScale( );
            repaint( );
        }
    }

    /**
     * It processes the image with the method for applying a convolution operator, expressed as a array of values
     * @param conv Convolution matrix. conv != null.
     */
    public void applyConvolution( double conv[][] )
    {
        if( image != null )
        {
            //Applies the convolution array
            image.applyConvolution( conv, ImageViewerGUI.CONVOLUTION_DIMENSION );
            repaint( );
        }
    }

    /**
     * Draw the image
     * @param g Pane Graphics
     */
    public void paint( Graphics g )
    {
        super.paint( g );
        if( image != null )
        {
            drawImage = image.getImageBuffer( );
            g.drawImage( drawImage, 0, 0, null, null );
        }
    }

    //-----------------------------------------------------------------
    // Extension methods
    //-----------------------------------------------------------------

    /**
     * It processes the image with extension method 1
     */
    public void extension1( )
    {
        if( image != null )
        {
            String answer = image.method1( );
            JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
            repaint( );
        }
    }

    /**
     * It processes the image with extension method 2
     */
    public void extension2( )
    {
        if( image != null )
        {
            String answer = image.method2( );
            JOptionPane.showMessageDialog( this, answer, "Answer", JOptionPane.INFORMATION_MESSAGE );
            repaint( );
        }
    }

}