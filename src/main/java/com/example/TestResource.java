package com.example;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/test")
public class TestResource {
	@ConfigProperty(name = "dicom.data.dir")
	String dataDir;

	@ConfigProperty(name = "dicom.image.name")
	String imageName;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() throws Exception {
		getRenderedImage();
		return "Success";
	}

	private void getRenderedImage() throws Exception {
		byte[] bytes = Files.readAllBytes(Paths.get(dataDir, imageName));
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

		ImageReader reader = getDicomImageReader();
		DicomImageReadParam param = (DicomImageReadParam) reader.getDefaultReadParam();
		ImageInputStream iis = ImageIO.createImageInputStream(bis);
		reader.setInput(iis, false);
		BufferedImage bi = reader.read(0, param);
		iis.close();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, "jpeg", baos);
	}

	private ImageReader getDicomImageReader() {
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("DICOM");
		if (!readers.hasNext()) {
			ImageIO.scanForPlugins();
			readers = ImageIO.getImageReadersByFormatName("DICOM");
			if (!readers.hasNext())
				throw new RuntimeException("DICOM Image Reader not registered");
		}
		return readers.next();
	}
}