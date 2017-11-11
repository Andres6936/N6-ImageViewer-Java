package edu.jabs.imageViewer.mundo;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

/**
 * Image color map
 */
public class Image
{
    //-----------------------------------------------------------------
    // Constants
    //-----------------------------------------------------------------

    /**
     * Maximum Image Width
     */
    public static final int MAXIMUM_WIDTH = 400;

    /**
     * Maximum Image Height
     */
    public static final int MAXIMUM_HEIGHT = 300;

    //-----------------------------------------------------------------
    // Fields
    //-----------------------------------------------------------------

    /**
     * Array of colors
     */
    private Color bitmap[][];

    /**
     * Image width
     */
    private int width;

    /**
     * Image Height
     */
    private int height;

    //-----------------------------------------------------------------
    // Constructors
    //-----------------------------------------------------------------

    /**
     * It builds an image from a BMP file.  The image numbers the pixels from the upper left corner with (0,0).
     * The X coordinate goes from 0 to width-1 and the Y coordinate goes from 0 to height-1. If the image has a width greater than MAXIMUM_WIDTH or a height greater than MAXIMUM_HEIGHT,
     * then the image is clipped to the limits.
     * @param file Name and path of the file. file != null.
     * @throws IOException Error reading the file
     */
    public Image( String file ) throws IOException
    {
        bitmap = new Color[MAXIMUM_HEIGHT][MAXIMUM_WIDTH];
        loadImage( file );
    }

    //-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

    /**
     * It returns a pixel color given its coordinates
     * @param x Horizontal Coordinate
     * @param y Vertical Coordinate
     * @return The pixel color at (x,y) or null if the coordinates or null if the coordinate are off the limits.
     */
    public Color getPixelColor( int x, int y )
    {
        if( x >= width || y >= height )
            return null;
        else
            return bitmap[ y ][ x ];
    }

    /**
     * It returns the image height in pixels
     * @return height
     */
    public int getHeight( )
    {
        return height;
    }

    /**
     * It returns the image width in pixels
     * @return width
     */
    public int getWidth( )
    {
        return width;
    }

    /**
     * It loads the image in the file
     * @param fileName - name and path of the file
     * @throws IOException Error loading the image
     */
    private void loadImage( String fileName ) throws IOException
    {
        File file = new File( fileName );
        BufferedImage bmp;

        try
        {
            bmp = ImageIO.read( file );
        }
        catch( IOException e )
        {
            throw new IOException( "Image not found");
        }

        if( bmp.getWidth( ) < MAXIMUM_WIDTH )
            width = bmp.getWidth( );
        else
            width = MAXIMUM_WIDTH;

        if( bmp.getHeight( ) < MAXIMUM_HEIGHT )
            height = bmp.getHeight( );
        else
            height = MAXIMUM_HEIGHT;

        for( int i = 0; i < height; i++ )
            for( int j = 0; j < width; j++ )
            {
                bitmap[ i ][ j ] = new Color( bmp.getRGB( j, i ) );
            }
    }

    /**
     * It returns the bitmap as a BufferedImage
     * @return image as a BufferedImage Object
     */
    public BufferedImage getImageBuffer( )
    {
        BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        for( int i = 0; i < height; i++ )
            for( int j = 0; j < width; j++ )
            {
                image.setRGB( j, i, bitmap[ i ][ j ].getRGB( ) );
            }
        return image;
    }

    /**
     * Negative image: The negative is computed changing each RGB component and taking the absolute value after subtracting 255.
     */
    public void turnNegative( )
    {
        //the array is run and the components of the new color are computed
        for( int i = 0; i < height; i++ )
            for( int j = 0; j < width; j++ )
            {
                Color oldColor = bitmap[ i ][ j ];
                int newR = Math.abs( oldColor.getRed( ) - 255 );
                int newG = Math.abs( oldColor.getGreen( ) - 255 );
                int newB = Math.abs( oldColor.getBlue( ) - 255 );
                bitmap[ i ][ j ] = new Color( newR, newG, newB );
            }
    }

    /**
     * Mirror image: the entire image columns are exchanged
     */
    public void mirrorImage( )
    {
        //Scroll halfway down the matrix to exchange the colors of the column
        for( int i = 0; i < height; i++ )
            for( int j = 0; j < width / 2; j++ )
            {
                Color temp = bitmap[ i ][ j ];
                bitmap[ i ][ j ] = bitmap[ i ][ width - 1 - j ];
                bitmap[ i ][ width - 1 - j ] = temp;
            }
    }

    /**
     * Binarization: Each image pixel is changed by a black or white pixel.  To do this a threshold is required: if the pixel color is greater than or equal to the threshold
     * the it is changed to white and if the pixel color is lees than the threshold then it is changed to black.
     * @param threshold Binarization Threshold.
     */
    public void imageBinarization( double threshold )
    {
        // Each pixel color that is less than the threshold is changed to black and
        // those that are greater than or equal to the threshold are changed to white
        for( int i = 0; i < height; i++ )
            for( int j = 0; j < width; j++ )
            {
                Color pixel = bitmap[ i ][ j ];
                int average = ( pixel.getBlue( ) + pixel.getGreen( ) + pixel.getRed( ) ) / 3;
                if( average < threshold )
                    bitmap[ i ][ j ] = Color.BLACK;
                else
                    bitmap[ i ][ j ] = Color.WHITE;
            }
    }

    /**
     * Pixel Image: The image is divided in various parts and for each part, the pixel color is changed to the average color of the part.
     * In this case, the part is sized with the smaller dividers of the width and height of the image
     */
    public void pixelImage( )
    {
        //Pixels are image size divisors
        int widthPixel = lesserDividerLessThanOne( width );
        int heightPixel = lesserDividerLessThanOne( height );

        //The array is run by parts to make the modifications
        for( int x = 0; x < width; x += widthPixel )
        {
            for( int y = 0; y < height; y += heightPixel )
            {
                //It gets the average color of the part
                Color averageColor = averageColor( x, y, x + widthPixel - 1, y + heightPixel - 1 );
                //The part color is changed to the average color
                changePartColor( averageColor, x, y, x + widthPixel - 1, y + heightPixel - 1 );
            }
        }
    }

    /**
     * Gray Scale: Each pixel components are averaged and a new color is built where each component (RGB) has the average value
     */
    public void grayScale( )
    {
        //The array is run to changed it to gray
        for( int i = 0; i < height; i++ )
            for( int j = 0; j < width; j++ )
            {
                int rgbGray = ( bitmap[ i ][ j ].getBlue( ) + bitmap[ i ][ j ].getGreen( ) + bitmap[ i ][ j ].getRed( ) ) / 3;
                bitmap[ i ][ j ] = new Color( rgbGray, rgbGray, rgbGray );
            }
    }

    /**
     * Convolution: Operation of the image with the convolution matrix given by the user
     * @param convolution Square Matrix of odd dimension. convolution != null.
     * @param dimension Size of the convolution matrix. dimension applies to the content of the array.
     */
    public void applyConvolution( double[][] convolution, int dimension )
    {
        //A copy of the original image is obtained, but it has a framework
        // of black pixels to allow the operation of the image corners
        // with the convolution array
        Color borderCopy[][] = copyWithBorder( dimension / 2 );

        //Computes the sum of the convolution factors
        double convolutionSum = 0;
        for( int i = 0; i < dimension; i++ )
            for( int j = 0; j < dimension; j++ )
                convolutionSum += convolution[ i ][ j ];

        //The array is run down to change the image
        for( int i = 0; i < height; i++ )
            for( int j = 0; j < width; j++ )
            {
                //For each pixel a calculation is made with the convolution array
                double redSum = 0;
                double greenSum = 0;
                double blueSum = 0;

                //The division is made in most cases(except at the edges)
                //Subtracting the sum of the convolution factors
                double divisor = convolutionSum;

                //The sum is done with the original image pixels
                for( int k = -dimension / 2; k <= dimension / 2; k++ )
                    for( int l = -dimension / 2; l <= dimension / 2; l++ )
                    {
                        redSum += convolution[ k + dimension / 2 ][ l + dimension / 2 ] * borderCopy[ i + k + dimension / 2 ][ j + l + dimension / 2 ].getRed( );
                        greenSum += convolution[ k + dimension / 2 ][ l + dimension / 2 ] * borderCopy[ i + k + dimension / 2 ][ j + l + dimension / 2 ].getGreen( );
                        blueSum += convolution[ k + dimension / 2 ][ l + dimension / 2 ] * borderCopy[ i + k + dimension / 2 ][ j + l + dimension / 2 ].getBlue( );

                        //If it is an edge pixel it does not count for the divisor
                        if( i + l < 0 || i + l > height || j + k < 0 || j + k > width )
                            divisor -= convolution[ k + dimension / 2 ][ l + dimension / 2 ];
                    }

                if( divisor > 0 )
                {
                    redSum /= divisor;
                    greenSum /= divisor;
                    blueSum /= divisor;

                    if( redSum > 255 )
                        redSum = 255;
                    else if( redSum < 0 )
                        redSum = 0;

                    if( greenSum > 255 )
                        greenSum = 255;
                    else if( greenSum < 0 )
                        greenSum = 0;

                    if( blueSum > 255 )
                        blueSum = 255;
                    else if( blueSum < 0 )
                        blueSum = 0;

                    //Change the image pixel
                    bitmap[ i ][ j ] = new Color( ( int )redSum, ( int )greenSum, ( int )blueSum );
                }
                else
                {
                    if( redSum > 255 )
                        redSum = 255;
                    else if( redSum < 0 )
                        redSum = 0;

                    if( greenSum > 255 )
                        greenSum = 255;
                    else if( greenSum < 0 )
                        greenSum = 0;

                    if( blueSum > 255 )
                        blueSum = 255;
                    else if( blueSum < 0 )
                        blueSum = 0;

                    //Change the image pixel
                    bitmap[ i ][ j ] = new Color( ( int )redSum, ( int )greenSum, ( int )blueSum );
                }
            }
    }

    /**
     * It returns the average color of the image
     * @return average color of the entire image
     */
    public Color averageColor( )
    {
        return averageColor( 0, 0, width - 1, height - 1 );
    }

    /**
     * It searches for the average color of the image part.  The average color is formed by the average red, green and blue
     * @param xInitial x coordinate of the first pixel.
     * @param yInitial y coordinate of the first pixel.
     * @param xFinal x coordinate of the last pixel.
     * @param yFinal y coordinate of the last pixel.
     * @return Average color of the region
     */
    private Color averageColor( int xInitial, int yInitial, int xFinal, int yFinal )
    {
        int averageRed = 0, averageGreen = 0, averageBlue = 0;
        int totalPixels = ( xFinal - xInitial + 1 ) * ( yFinal - yInitial + 1 );

        //It runs down the region to average color components
        for( int i = yInitial; i <= yFinal; i++ )
            for( int j = xInitial; j <= xFinal; j++ )
            {
                averageRed += bitmap[ i ][ j ].getRed( );
                averageGreen += bitmap[ i ][ j ].getGreen( );
                averageBlue += bitmap[ i ][ j ].getBlue( );
            }

        averageRed /= totalPixels;
        averageGreen /= totalPixels;
        averageBlue /= totalPixels;
        return new Color( averageRed, averageGreen, averageBlue );
    }

    /**
     * Computes the smallest divisor of the given number which is greater than 1.
     * @param number which divisor is searched.
     * @return minimum divisor greater than one
     */
    private int lesserDividerLessThanOne( int number )
    {
        boolean found = false;
        int divisor = 2;

        //If the number is even the minimum divisor is 2
        if( number % divisor == 0 )
            return divisor;

        else
        {
            //If the number is odd it searches for an odd divisor
            divisor = 3;
            while( divisor < number && !found )
            {
                if( number % divisor == 0 )
                    found = true;
                else
                    divisor += 2;
            }
            return divisor;
        }
    }

    /**
     * It changes the pixel color of the region to the given one
     * @param color Color of the new region
     * @param xInitial x coordinate of the first pixel.
     * @param yInitial y coordinate of the first pixel.
     * @param xFinal x coordinate of the last pixel.
     * @param yFinal y coordinate of the last pixel.
     */
    private void changePartColor( Color color, int xInitial, int yInitial, int xFinal, int yFinal )
    {
        for( int i = yInitial; i <= yFinal && i < height; i++ )
            for( int j = xInitial; j <= xFinal && j < width; j++ )
            {
                bitmap[ i ][ j ] = color;
            }
    }

    /**
     * It creates a copy of the image but it adds a border of black pixels, to allow an easier operation of the image corners with the convolution array
     * without altering the outcome of the edges
     * @param border width of the border(on one side)
     * @return The image is copied(Color map)
     */
    private Color[][] copyWithBorder( int border )
    {
        //It creates a copy of the original image which includes a framework of black pixels
        Color[][] copy = new Color[height + 2 * border][width + 2 * border];

        // It scrolls the image including the edge
        for( int i = 0; i < height + border * 2; i++ )
            for( int j = 0; j < width + border * 2; j++ )
            {
                //If it is an edge pixel then it is black
                if( i < border || i >= height + border || j < border || j >= width + border )
                    copy[ i ][ j ] = Color.BLACK;
                else
                    //otherwise it is taken from the image
                    copy[ i ][ j ] = new Color( bitmap[ i - border ][ j - border ].getRGB( ) );
            }
        return copy;
    }

    //-----------------------------------------------------------------
    // Extension Methods
    //-----------------------------------------------------------------

    /**
     * Extension Method 1
     * @return Answer 1
     */
    public String method1( )
    {
        return "Answer 1";
    }

    /**
     * Extension Method 2
     * @return Answer 2
     */
    public String method2( )
    {
        return "Answer 2";
    }
}