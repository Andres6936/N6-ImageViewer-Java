package edu.jabs.imageViewer.interfaz;

import java.awt.*;

import javax.swing.*;

/**
 * Dialogue to ask the threshold for binarization
 */
public class ThresholdBinarizationDialogue extends JFrame
{
    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------
    /**
     * Threshold Pane
     */
    private ThresholdPane thresholdPane;

    /**
     * Buttons Pane
     */
    private ThresholdButtonsPane buttonsPane;

    /**
     * Main application window
     */
    private ImageViewerGUI window;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * The threshold for binarization dialogue is built
     * @param theWindow Main application window
     */
    public ThresholdBinarizationDialogue( ImageViewerGUI theWindow )
    {
        //The layout is set
        setLayout( new BorderLayout( ) );

        //The window size is set
        setPreferredSize( new Dimension( 190, 80 ) );

        //The window reference is saved
        window = theWindow;

        //the Pane is initialized
        thresholdPane = new ThresholdPane( );

        //Suggested as threshold the average color of the entire image
        Color average = window.averageColor( );
        double threshold = ( average.getBlue( ) + average.getGreen( ) + average.getRed( ) ) / 3;
        thresholdPane.setThreshold( threshold );
        add( thresholdPane, BorderLayout.CENTER );

        //The button pane is initialized
        buttonsPane = new ThresholdButtonsPane( this );
        add( buttonsPane, BorderLayout.SOUTH );

        setTitle( "Binarization Threshold" );
        pack( );
        setResizable( false );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------
    /**
     * It processes the accept button
     */
    public void accept( )
    {
        double threshold = thresholdPane.getThreshold( );
        if( threshold != -1 )
            window.imageBinarization( threshold );
        setVisible( false );
    }

    /**
     * It processes the cancel button
     */
    public void cancel( )
    {
        setVisible( false );
    }
}