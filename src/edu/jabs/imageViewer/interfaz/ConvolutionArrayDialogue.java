package edu.jabs.imageViewer.interfaz;

import java.awt.*;

import javax.swing.*;

/**
 * Dialogue to ask for the convolution matrix
 */
public class ConvolutionArrayDialogue extends JFrame
{
    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------
    /**
     * Array Pane
     */
    private ArrayPane arrayPane;

    /**
     * Buttons Pane
     */
    private ArrayButtonsPane buttonsPane;

    /**
     * Main application window
     */
    private ImageViewerGUI window;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds the dialogue for the convolution array values
     * @param theWindow Main window application. theWindow != null.
     */
    public ConvolutionArrayDialogue( ImageViewerGUI theWindow )
    {
        //the layout is set
        setLayout( new BorderLayout( ) );

        //the window reference is saved
        window = theWindow;

        //The image pane is initialized
        arrayPane = new ArrayPane( );
        add( arrayPane, BorderLayout.CENTER );

        //The button pane is initialized
        buttonsPane = new ArrayButtonsPane( this );
        add( buttonsPane, BorderLayout.SOUTH );

        setTitle( "Convolution Matrix" );
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
        double conv[][] = arrayPane.getArray( );
        if( conv != null )
            window.applyConvolution( conv );
        setVisible( false );
    }

    /**
     * It processes the clean button
     */
    public void clean( )
    {
        arrayPane.clean( );
    }

    /**
     * It processes the cancel button
     */
    public void cancel( )
    {
        setVisible( false );
    }
}