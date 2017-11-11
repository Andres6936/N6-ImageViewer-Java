package edu.jabs.imageViewer.interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Buttons Pane
 */
public class ButtonsPane extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * NEGATIVE
     */
    public final static String NEGATIVE = "NEGATIVE";

    /**
     * MIRROR
     */
    public final static String MIRROR = "MIRROR";

    /**
     * BINARIZE
     */
    public final static String BINARIZE = "BINARIZE";

    /**
     * PIXEL
     */
    public final static String PIXEL = "PIXEL";

    /**
     * GRAYSCALE
     */
    public final static String GRAYSCALE = "GRAYSCALE";

    /**
     * CONVOLUTION
     */
    public final static String CONVOLUTION = "CONVOLUTION";

    /**
     * Extension 1
     */
    public final static String EXTENSION_1 = "EXTENSION_1";

    /**
     * Extension 2
     */
    public final static String EXTENSION_2 = "EXTENSION_2";

    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------

    /**
     * Negative Button
     */
    private JButton butNegative;

    /**
     * Mirror Button
     */
    private JButton butMirror;

    /**
     * Binarize Button
     */
    private JButton butBinarize;

    /**
     * Pixel button
     */
    private JButton butPixele;

    /**
     * Gray Scale Button
     */
    private JButton butGrayScale;

    /**
     * Convolution Operator Button
     */
    private JButton butConvolution;

    /**
     * Extension Button 1
     */
    private JButton butExtension1;

    /**
     * Extension Button 2
     */
    private JButton butExtension2;

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Main application window
     */
    private ImageViewerGUI window;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds the buttons pane
     * @param theWindow A reference to the main application window. theWindow != null.
     */
    public ButtonsPane( ImageViewerGUI theWindow )
    {
        //The reference is saved
        window = theWindow;

        //the layout is set
        setLayout( new GridLayout( 6, 1 ) );

        //The graphical elements are initialized
        butNegative = new JButton( "Negative" );
        butNegative.setActionCommand( NEGATIVE );
        butNegative.addActionListener( this );

        butMirror = new JButton( "Mirror" );
        butMirror.setActionCommand( MIRROR );
        butMirror.addActionListener( this );

        butBinarize = new JButton( "Binarization" );
        butBinarize.setActionCommand( BINARIZE );
        butBinarize.addActionListener( this );

        butPixele = new JButton( "Pixel" );
        butPixele.setActionCommand( PIXEL );
        butPixele.addActionListener( this );

        butGrayScale = new JButton( "Gray Scale" );
        butGrayScale.setActionCommand( GRAYSCALE );
        butGrayScale.addActionListener( this );

        butConvolution = new JButton( "Convolution" );
        butConvolution.setActionCommand( CONVOLUTION );
        butConvolution.addActionListener( this );

        butExtension1 = new JButton( "Extension 1" );
        butExtension1.setActionCommand( EXTENSION_1 );
        butExtension1.addActionListener( this );

        butExtension2 = new JButton( "Extension 2" );
        butExtension2.setActionCommand( EXTENSION_2 );
        butExtension2.addActionListener( this );

        //The elements are added to the pane
        add( butNegative );
        add( butMirror );
        add( butBinarize );
        add( butPixele );
        add( butGrayScale );
        add( butConvolution );
        add( butExtension1 );
        add( butExtension2 );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It executes the action events
     * @param event Action event. event != null.
     */
    public void actionPerformed( ActionEvent event )
    {
        String command = event.getActionCommand( );
        if( command.equals( NEGATIVE ) )
        {
            window.turnNegative( );
        }
        else if( command.equals( MIRROR ) )
        {
            window.mirrorImage( );
        }
        else if( command.equals( BINARIZE ) )
        {
            window.showThresholdDialogue( );
        }
        else if( command.equals( PIXEL ) )
        {
            window.pixelImage( );
        }
        else if( command.equals( GRAYSCALE ) )
        {
            window.grayScale( );
        }
        else if( command.equals( CONVOLUTION ) )
        {
            window.showConvolutionMatrixDialogue( );
        }
        else if( command.equals( EXTENSION_1 ) )
        {
            window.reqFuncOption1( );
        }
        else if( command.equals( EXTENSION_2 ) )
        {
            window.reqFuncOption2( );
        }
    }
}