//ImagaReaderRunner.java
import imagereader.Runner;
public class ImagaReaderRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplementImageIO imageioer = new ImplementImageIO();
	    ImplementImageProcesser processor = new ImplementImageProcesser();
	    Runner.run(imageioer, processor);
	}

}
