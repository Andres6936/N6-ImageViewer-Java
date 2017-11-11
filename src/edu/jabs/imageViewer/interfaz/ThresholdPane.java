package edu.jabs.imageViewer.interfaz;

import java.awt.*;

import javax.swing.*;

/**
 * Pane where you enter the threshold value for binarization
 */
public class ThresholdPane extends JPanel
{
    //-----------------------------------------------------------------
    // GUI Fields
    //-----------------------------------------------------------------

    /**
     * Text Field for the threshold
     */
    private JTextField txtThreshold;

    /**
     * Threshold Label
     */
    private JLabel labThreshold;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It Create the pane to receive the threshold of binarization
     */
    public ThresholdPane( )
    {
        //The layout is set
        setLayout( new GridLayout( 1, 2 ) );

        //The graphical elements are initialized
        txtThreshold = new JTextField( );
        txtThreshold.setForeground( Color.BLUE );
        labThreshold = new JLabel( "Threshold:" );
        labThreshold.setHorizontalAlignment( JLabel.CENTER );
        add( labThreshold );
        add( txtThreshold );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It returns the threshold value
     * @return threshold It returns the value given by the user, or -1 if it is an invalid value
     */
    public double getThreshold( )
    {
        try
        {
            return Double.parseDouble( txtThreshold.getText( ) );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Invalid Threshold: " + txtThreshold.getText( ), "Binarization Threshold", JOptionPane.ERROR_MESSAGE );
            txtThreshold.setText( "0" );
            return -1;
        }
    }

    /**
     * It assigns a new threshold to the appropriate text field
     * @param newThreshold
     */
    public void setThreshold( double newThreshold )
    {
        txtThreshold.setText( newThreshold + "" );
    }
}
