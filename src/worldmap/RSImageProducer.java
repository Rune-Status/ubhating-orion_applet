package worldmap;

/**
 * Class: RSImageProducer.java
 * Originally: Class3.java
 * */

import java.awt.*;
import java.awt.image.*;

public class RSImageProducer
	implements ImageProducer, ImageObserver
{

	public void initializeDrawingArea()
	{
		DrawingArea.initializeDrawingArea(pixels, width, height);
	}

	public RSImageProducer(int i, int j, Component component)
	{
		width = i;
		height = j;
		pixels = new int[i * j];
		colorModel = new DirectColorModel(32, 0xff0000, 65280, 255);
		image = component.createImage(this);
		method66();
		component.prepareImage(image, this);
		method66();
		component.prepareImage(image, this);
		method66();
		component.prepareImage(image, this);
		initializeDrawingArea();
	}

	public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1)
	{
		return true;
	}

	public void requestTopDownLeftRightResend(ImageConsumer imageconsumer)
	{
		System.out.println("TDLR");
	}

	public synchronized boolean isConsumer(ImageConsumer imageconsumer)
	{
		return imageConsumer == imageconsumer;
	}

	public synchronized void removeConsumer(ImageConsumer imageconsumer)
	{
		if(imageConsumer == imageconsumer)
			imageConsumer = null;
	}

	public void drawGraphics(Graphics g, int i, int j)
	{
		method66();
		g.drawImage(image, i, j, this);
	}

	public void startProduction(ImageConsumer imageconsumer)
	{
		addConsumer(imageconsumer);
	}

	public synchronized void addConsumer(ImageConsumer imageconsumer)
	{
		imageConsumer = imageconsumer;
		imageconsumer.setDimensions(width, height);
		imageconsumer.setProperties(null);
		imageconsumer.setColorModel(colorModel);
		imageconsumer.setHints(14);
	}

	public synchronized void method66()
	{
		if(imageConsumer == null)
		{
			return;
		} else
		{
			imageConsumer.setPixels(0, 0, width, height, colorModel, pixels, 0, width);
			imageConsumer.imageComplete(2);
			return;
		}
	}

	public int pixels[];
	public int width;
	public int height;
	public ColorModel colorModel;
	public ImageConsumer imageConsumer;
	public Image image;
}
