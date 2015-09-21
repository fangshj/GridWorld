import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.Image;
import java.io.IOException;

public class ImplementImageProcesserTest {


	@Test
	public void testShowChanelB() throws IOException {
		ImplementImageIO imageioer = new ImplementImageIO();
	    ImplementImageProcesser processor = new ImplementImageProcesser();
	    Image img;
	    img = imageioer.myRead("./../goal");
	    
	}

	@Test
	public void testShowChanelG() {
		ImplementImageIO imageioer = new ImplementImageIO();
	    ImplementImageProcesser processor = new ImplementImageProcesser();
	    Image img;
	    img = imageioer.myRead("./../goal");
	}

	@Test
	public void testShowChanelR() {
		ImplementImageIO imageioer = new ImplementImageIO();
	    ImplementImageProcesser processor = new ImplementImageProcesser();
	    Image img;
	    img = imageioer.myRead("./../goal");
	}

	@Test
	public void testShowGray() {
		ImplementImageIO imageioer = new ImplementImageIO();
	    ImplementImageProcesser processor = new ImplementImageProcesser();
	    Image img;
	    img = imageioer.myRead("./../goal");
	}

}
