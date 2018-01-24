package cn.com.winning.ssgj.base.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiWriter;
import com.sun.jimi.core.options.JPGOptions;

public class ImageUtils {
	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	public static boolean ergodDirectory(String directory) throws FileNotFoundException, IOException {
		try {
			File file = new File(directory);
			if (file.isDirectory()) {
				String[] fileList = file.list();
				for (int i = 0; i < fileList.length; i++) {
					File readfile = new File(directory + File.pathSeparator + fileList[i]);
					if (readfile.isDirectory()) {
						if (readfile.isDirectory())
							ergodDirectory(directory + File.pathSeparator + fileList[i]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFound");
		}

		return true;
	}

	public static void resize(String source, String desc, int width, int height) throws IOException {
		Image sourceImage = ImageIO.read(new File(source));
		BufferedImage bi = new BufferedImage(width, height, 1);
		bi.getGraphics().drawImage(sourceImage.getScaledInstance(width, height, 4), 0, 0, null);

		FileOutputStream out = new FileOutputStream(desc);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bi);
		out.close();
	}

	public static void resizeByFixedHeight(String source, String desc, int fixedHeight) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, ".") + "_" + StringUtils.leftPad(String.valueOf(fixedHeight), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, Integer.valueOf(100));
		logger.debug("source jpg file is:{}" + jpgSource);

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedWidth = (int) (sourceImageWidth / sourceImageHeight * fixedHeight);

		resize(jpgSource, desc, resizedWidth, fixedHeight);
	}

	public static void resizeByFixedWidth(String source, String desc, int fixedWidth) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, ".") + "_" + StringUtils.leftPad(String.valueOf(fixedWidth), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, Integer.valueOf(100));
		logger.debug("source jpg file is:{}" + jpgSource);

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedHeight = (int) (sourceImageHeight / sourceImageWidth * fixedWidth);

		resize(jpgSource, desc, fixedWidth, resizedHeight);
	}

	public static void resizeByMaxSize(String source, String desc, int maxSize) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, ".") + "_" + StringUtils.leftPad(String.valueOf(maxSize), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, Integer.valueOf(100));
		logger.debug("source jpg file is:{}" + jpgSource);

		double ratio = 0.0D;

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		if (sourceImageHeight > sourceImageWidth)
			ratio = maxSize / sourceImageHeight;
		else {
			ratio = maxSize / sourceImageWidth;
		}

		int resizedWidth = (int) (sourceImageWidth * ratio);
		int resizedHeight = (int) (sourceImageHeight * ratio);

		resize(jpgSource, desc, resizedWidth, resizedHeight);
	}

	public static void resizeByRatio(String source, String desc, double ratio) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, ".") + "_" + StringUtils.leftPad(String.valueOf(ratio * 100.0D), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, Integer.valueOf(100));
		logger.debug("source jpg file is:{}" + jpgSource);

		String jpgSourceFile = toJPG(source, null, Integer.valueOf(100));
		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedWidth = (int) (sourceImageWidth * ratio);
		int resizedHeight = (int) (sourceImageHeight * ratio);

		resize(jpgSourceFile, desc, resizedWidth, resizedHeight);
	}

	public static String toJPG(String source, String dest, Integer quality) throws JimiException {
		if ((dest == null) || (dest.trim().equals(""))) {
			dest = source;
		}
		if (!dest.toLowerCase().trim().endsWith(".jpg")) {
			dest = dest + ".jpg";

			if ((quality == null) || ("".equals(quality.toString())) || (quality.intValue() < 0) || (quality.intValue() > 100)) {
				quality = Integer.valueOf(75);
			}
			JPGOptions options = new JPGOptions();
			options.setQuality(quality.intValue());
			ImageProducer image = Jimi.getImageProducer(source);
			JimiWriter writer = Jimi.createJimiWriter(dest);
			writer.setSource(image);

			writer.setOptions(options);
			writer.putImage(dest);
		}
		return dest;
	}
}