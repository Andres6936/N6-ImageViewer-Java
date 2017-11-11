package edu.jabs.imageViewer.interfaz;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

/**
 * Image Viewer GUI
 */
public class ImageViewerGUI extends JFrame
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * Convolution Dimension
     */
    public static final int CONVOLUTION_DIMENSION = 3;

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Image Pane
     */
    private ImagePane imagePane;

    /**
     * Buttons Pane
     */
    private ButtonsPane buttonsPane;

    /**
     * Dialogue to ask for the convolution matrix
     */
    private ConvolutionArrayDialogue arrayDialogue;

    /**
     * Dialogue to ask for the binarization threshold
     */
    private ThresholdBinarizationDialogue thresholdDialogue;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds the image viewer gui
     */
    public ImageViewerGUI( )
    {
        //The layout is set
        setLayout( new BorderLayout( ) );

        //The image pane is initialized and added
        imagePane = new ImagePane( );
        add( imagePane, BorderLayout.CENTER );

        //The buttons pane is initialized and added
        buttonsPane = new ButtonsPane( this );
        add( buttonsPane, BorderLayout.EAST );

        //The convolution array dialogue is initialized
        arrayDialogue = new ConvolutionArrayDialogue( this );

        //The binarization threshold dialogue is initialized
        thresholdDialogue = new ThresholdBinarizationDialogue( this );

        setTitle( "Image Viewer" );
        pack( );
        setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It returns the average image color
     * @return average color
     */
    public Color averageColor( )
    {
        return imagePane.averageColor( );
    }

    /**
     * Option 1: turn the image into its negative
     */
    public void turnNegative( )
    {
        imagePane.turnNegative( );
    }

    /**
     * option : Mirror Image
     */
    public void mirrorImage( )
    {
        imagePane.mirrorImage( );
    }

    /**
     * It shows the binarization threshold dialogue
     */
    public void showThresholdDialogue( )
    {
        thresholdDialogue.setVisible( true );
    }

    /**
     * Option 3: Image binarization
     * @param threshold binarization threshold
     */
    public void imageBinarization( double threshold )
    {
        imagePane.imageBinarization( threshold );
    }

    /**
     * Option 4: Pixel Image
     */
    public void pixelImage( )
    {
        imagePane.pixelImage( );
    }

    /**
     * Option 5: convert to a grayscale image
     */
    public void grayScale( )
    {
        imagePane.grayScale( );
    }

    /**
     * It shows the convolution array dialogue
     */
    public void showConvolutionMatrixDialogue( )
    {
        arrayDialogue.setVisible( true );
    }

    /**
     * Option 6: Apply the convolution operator represented by an array
     * @param conv Convolution array
     */
    public void applyConvolution( double conv[][] )
    {
        imagePane.applyConvolution( conv );
    }

    //-----------------------------------------------------------------
    // Extension Methods
    //-----------------------------------------------------------------

    /**
     * Extension 1
     */
    public void reqFuncOption1( )
    {
        imagePane.extension1( );
    }

    /**
     * Extension 2
     */
    public void reqFuncOption2( )
    {
        imagePane.extension2( );
    }

    //-----------------------------------------------------------------
    // Main method
    //-----------------------------------------------------------------
    /**
     * Program Execution method.
     * @param args There are no execution arguments
     */
    public static void main( String[] args )
    {
        ImageViewerGUI i = new ImageViewerGUI( );
        i.setVisible( true );
    }
}