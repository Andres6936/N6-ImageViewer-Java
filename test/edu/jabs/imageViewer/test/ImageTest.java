package edu.jabs.imageViewer.test;

import java.awt.*;
import java.io.*;

import junit.framework.*;
import edu.jabs.imageViewer.mundo.Image;

/**
 * Image viewer test class
 */
public class ImageTest extends TestCase
{
    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Test Image
     */
    private Image image;

    /**
     * Test Image Height
     */
    private int height;

    /**
     * Test Image Width
     */
    private int width;

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * A test case is prepared with a 10 x 20 blank image
     */
    private void setupScenario1( )
    {
        try
        {
            //It initializes the image
            image = new Image( "test/data/image1.bmp" );
            width = 10;
            height = 20;
        }
        catch( IOException e )
        {
            fail( "could not create the image" );
        }
    }

    /**
     * It makes a case with a black image which limit width and height. The image must have such dimensions.
     */
    private void setupScenario2( )
    {
        try
        {
            //It initializes the image
            image = new Image( "test/data/image2.bmp" );
            width = Image.MAXIMUM_WIDTH;
            height = Image.MAXIMUM_HEIGHT;
        }
        catch( IOException e )
        {
            fail( "could not create the image" );
        }
    }

    /**
     * It makes a case with a blue image which limit width and height. The image is 1000 x 900.
     */
    private void setupScenario3( )
    {
        try
        {
            //It initializes the image
            image = new Image( "test/data/image3.bmp" );
            width = 1000;
            height = 900;
        }
        catch( IOException e )
        {
            fail( "could not create the image" );
        }
    }

    /**
     * It tries to load an image that is less than the maximum image
     */
    public void testLoadLittleImage( )
    {
        //The scenario is set
        setupScenario1( );

        //The width and height of the image should be the expected values
        assertEquals( width, image.getWidth( ) );
        assertEquals( height, image.getHeight( ) );
        //the limits of the image must be less than the maximum
        assertTrue( image.getHeight( ) < Image.MAXIMUM_HEIGHT );
        assertTrue( image.getWidth( ) < Image.MAXIMUM_WIDTH );
    }

    /**
     * It tries to load an image with the exact limits
     */
    public void testLoadExactImage( )
    {
        //The scenario is set
        setupScenario2( );

        //The width and height of the image should be the expected values
        assertEquals( width, image.getWidth( ) );
        assertEquals( height, image.getHeight( ) );
    }

    /**
     * It tries to load an image greater than the maximum image
     */
    public void testLoadBigImage( )
    {
        //The scenario is set
        setupScenario3( );

        //The width and height of the image should be the expected values
        assertTrue( width > Image.MAXIMUM_WIDTH );
        assertTrue( height > Image.MAXIMUM_HEIGHT );

        //The width and height of the image should be the maximum values becaus e the image i clipped
        assertEquals( Image.MAXIMUM_WIDTH, image.getWidth( ) );
        assertEquals( Image.MAXIMUM_HEIGHT, image.getHeight( ) );
    }

    /**
     * It tests that the loaded image matches the all-white image
     */
    public void testLoadWhiteImage( )
    {
        //The scenario is set
        setupScenario1( );

        //All image bits are white
        for( int i = 0; i < image.getWidth( ); i++ )
        {
            for( int j = 0; j < image.getHeight( ); j++ )
            {
                assertEquals( Color.white.getRGB( ), image.getPixelColor( i, j ).getRGB( ) );
            }
        }
    }

    /**
     * It tests the average computation
     */
    public void testAverageColorWhiteImage( )
    {
        //The scenario is set
        setupScenario1( );
        //The average color must be white
        assertEquals( Color.white.getRGB( ), image.averageColor( ).getRGB( ) );
    }

    /**
     * It tests that the loaded image matches the black image
     */
    public void testCargaImagenNegra( )
    {
        //The scenario is set
        setupScenario2( );

        //The average color must be black
        for( int i = 0; i < image.getWidth( ); i++ )
        {
            for( int j = 0; j < image.getHeight( ); j++ )
            {
                assertEquals( Color.black.getRGB( ), image.getPixelColor( i, j ).getRGB( ) );
            }
        }
    }
}
