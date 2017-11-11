package edu.jabs.imageViewer.interfaz;

import java.awt.*;

import javax.swing.*;

/**
 * Pane where the convolution matrix data is entered
 */
public class ArrayPane extends JPanel
{
    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------

    /**
     * Fields where the values are entered
     */
    private JTextField factors[][];

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds a pane to receive the convolution matrix values
     */
    public ArrayPane( )
    {
        int dimension = ImageViewerGUI.CONVOLUTION_DIMENSION;

        factors = new JTextField[dimension][dimension];

        //the layout is set
        setLayout( new GridLayout( dimension, dimension ) );

        //the text fields are initialized
        for( int i = 0; i < dimension; i++ )
            for( int j = 0; j < dimension; j++ )
            {
                factors[ i ][ j ] = new JTextField( "0" );
                add( factors[ i ][ j ] );
            }
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It cleans the factor values
     */
    public void clean( )
    {
        for( int i = 0; i < ImageViewerGUI.CONVOLUTION_DIMENSION; i++ )
            for( int j = 0; j < ImageViewerGUI.CONVOLUTION_DIMENSION; j++ )
                factors[ i ][ j ].setText( "0" );
    }

    /**
     * It returns the convolution array defined by the user
     * @return convolution array
     */
    public double[][] getArray( )
    {
        int dimension = ImageViewerGUI.CONVOLUTION_DIMENSION;
        double matrix[][] = new double[dimension][dimension];

        for( int i = 0; i < dimension; i++ )
            for( int j = 0; j < dimension; j++ )
            {
                try
                {
                    matrix[ i ][ j ] = Double.parseDouble( factors[ i ][ j ].getText( ) );
                }
                catch( Exception e )
                {
                    JOptionPane.showMessageDialog( this, "Invalid Factor: " + factors[ i ][ j ].getText( ), "Convolution Array", JOptionPane.ERROR_MESSAGE );
                    factors[ i ][ j ].setText( "0" );
                    return null;
                }
            }
        return matrix;
    }
}