import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;


public class ImplementImageProcesser implements IImageProcessor {

	class getBlueFilter extends RGBImageFilter {
		public getBlueFilter() {
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int x, int y, int z) {
			return (0xff0000ff & z);
		}
	}
	
	class getGreenFilter extends RGBImageFilter {
		public getGreenFilter() {
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int x, int y, int z) {
			return (0xff00ff00 & z);
		}
	}
	
	class getRedFilter extends RGBImageFilter {
		public getRedFilter() {
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int x, int y, int z) {
			return (0xffff0000 & z);
		}
	}
	
	@Override
	public Image showChanelB(Image arg0) {
		getBlueFilter blue = new getBlueFilter();
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(arg0.getSource(), blue));
	}

	@Override
	public Image showChanelG(Image arg0) {
		getGreenFilter green = new getGreenFilter();
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(arg0.getSource(), green));
	}

	@Override
	public Image showChanelR(Image arg0) {
		getRedFilter red = new getRedFilter();
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(arg0.getSource(), red));
	}

	
	class getGrayFilter extends RGBImageFilter {
		public getGrayFilter() {
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int x, int y, int z) {
			int R = (z & 0x00ff0000) >> 16;
			int G = (z & 0x0000ff00) >> 8;
			int B = z & 0x000000ff;
			int gray = (int)(0.299 * (double)R + 0.587 * (double)G + 0.114 * (double)B);
			int grayNew = (z & 0xff000000) + (gray << 16) + (gray << 8) + gray;
			return grayNew;
		}
	}
	
	@Override
	public Image showGray(Image arg0) {
		getGrayFilter gray = new getGrayFilter();
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(arg0.getSource(), gray));
	}

}
