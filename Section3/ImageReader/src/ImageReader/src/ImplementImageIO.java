import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import java.io.*;

import imagereader.IImageIO;


public class ImplementImageIO implements IImageIO {
	private static int width;  // width of image
    private static int height; // height of image
	private int pix[];  // pixes' messages
	
	
	@Override
	public Image myRead(String arg0) throws IOException {
		
	  //get the source input string 
	    FileInputStream fi     = new FileInputStream(arg0);
	    BufferedInputStream bi = new BufferedInputStream(fi); 
       
	  //read the head messages
	    byte[] arrayHead = new byte[14];
	    bi.read(arrayHead, 0, 14);
	    
	  //read the main messages
	    byte[] arrayMessages = new byte[40];
	    bi.read(arrayMessages, 0, 40);
	    
	  //get height and width of the picture
	    width  = changeInt(arrayMessages, 7);
	    height = changeInt(arrayMessages, 11);
	    
	  //get the main color messages of the picture and put in array
	    getImaInfo(bi);
	    
	    fi.close();
	    bi.close();
	    
	  //create image from the messages we get above
	    return createImage();

	}
	
	//change the 1/0 to intType
	public int changeInt(byte[] arrayMessages, int start) {
		int realInt = (int) ((arrayMessages[start] & 0xff) << 24)  
                | ((arrayMessages[start - 1] & 0xff) << 16)  
                | ((arrayMessages[start - 2] & 0xff) << 8)  
                | (arrayMessages[start - 3] & 0xff);  
        return realInt; 
	}
	
	//get the image's color messages
	public void getImaInfo(BufferedInputStream bi) throws IOException {
		pix = new int[height * width];
		
		// get the number fit bytes
		int fitBytes = 4 - (width * 3) % 4;
		fitBytes = (fitBytes == 4 ? 0 : fitBytes);
		int k = 0;
		
		// get the color info by travel the string
		// note that the info is store in the BGR order
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				k = width * (height - 1 - i) + j;
				int a = (0xFF) << 24;
				int b = ((int)bi.read() & 0xFF);
				int c = ((int)bi.read() & 0xFF) << 8;
				int d = ((int)bi.read() & 0xFF) << 16;
				pix[k] = a | d | c | b;
			}
		}
	}
	
	// create the image by pix, width and height.
	public Image createImage() {
		return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pix, 0, width));
	}
	

	@Override
	public Image myWrite(Image img, String arg1) throws IOException {
		// Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_BGR);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // put the img to argl
	    FileOutputStream fin = new FileOutputStream(arg1);
	    ImageIO.write(bimage, "bmp", fin);
	    
	    // return the img itself
	    return img;
	}

}
