package client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.Hashtable;

public final class Sprite extends Rasterizer2D {

    public static String location = SignLink.findcachedir() + "Sprites/";

    public int[] myPixels;
    public int myWidth;
    public int myHeight;
    public int anInt1442;
    public int anInt1443;
    public int max_width;
    public int anInt1445;

    public Sprite(int i, int j) {
        this.myPixels = new int[i * j];
        this.myWidth = this.max_width = i;
        this.myHeight = this.anInt1445 = j;
        this.anInt1442 = this.anInt1443 = 0;
    }

    public void setTransparency(int transRed, int transGreen, int transBlue) {
        for (int index = 0; index < myPixels.length; index++)
            if (((myPixels[index] >> 16) & 255) == transRed
                    && ((myPixels[index] >> 8) & 255) == transGreen
                    && (myPixels[index] & 255) == transBlue)
                myPixels[index] = 0;
    }

    public Sprite(String img, int width, int height) {
        try {
            Image image = Toolkit.getDefaultToolkit().createImage(
                    FileOperations.ReadFile(img));
            myWidth = width;
            myHeight = height;
            width = myWidth;
            height = myHeight;
            anInt1442 = 0;
            anInt1443 = 0;
            myPixels = new int[myWidth * myHeight];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth,
                    myHeight, myPixels, 0, myWidth);
            pixelgrabber.grabPixels();
            image = null;
        } catch (Exception _ex) {
            System.out.println(_ex);
        }
    }

    public Sprite(String img) {
        try {
            // imageName = img; // its not used?
            Image image = Toolkit.getDefaultToolkit().getImage(location + img + ".png");
            ImageIcon sprite = new ImageIcon(image);
            myWidth = sprite.getIconWidth();
            myHeight = sprite.getIconHeight();
            width = myWidth;
            height = myHeight;
            anInt1442 = 0;
            anInt1443 = 0;
            // SpritePosX = 0;
            // SpritePosY = 0;
            myPixels = new int[myWidth * myHeight];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth,
                    myHeight, myPixels, 0, myWidth);
            pixelgrabber.grabPixels();
            image = null;
            setTransparency(255, 0, 255);
        } catch (Exception _ex) {
            System.out.println(_ex);
        }
    }

    public Sprite(byte spriteData[]) {
        try {
            Image image = Toolkit.getDefaultToolkit().createImage(spriteData);
            ImageIcon sprite = new ImageIcon(image);
            myWidth = sprite.getIconWidth();
            myHeight = sprite.getIconHeight();
            max_width = myWidth;
            anInt1445 = myHeight;
            anInt1442 = 0;
            anInt1443 = 0;
            myPixels = new int[myWidth * myHeight];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth,
                    myHeight, myPixels, 0, myWidth);
            pixelgrabber.grabPixels();
            image = null;
            setTransparency(255, 0, 255);
        } catch (Exception _ex) {
            System.out.println(_ex);
        }
    }

    public Sprite(byte[] abyte0, Component component) {
        try {
            Image _ex = Toolkit.getDefaultToolkit().createImage(abyte0);
            MediaTracker mediatracker = new MediaTracker(component);
            mediatracker.addImage(_ex, 0);
            mediatracker.waitForAll();
            this.myWidth = _ex.getWidth(component);
            this.myHeight = _ex.getHeight(component);
            this.max_width = this.myWidth;
            this.anInt1445 = this.myHeight;
            this.anInt1442 = 0;
            this.anInt1443 = 0;
            this.myPixels = new int[this.myWidth * this.myHeight];
            PixelGrabber pixelgrabber = new PixelGrabber(_ex, 0, 0, this.myWidth, this.myHeight, this.myPixels, 0, this.myWidth);
            pixelgrabber.grabPixels();
        } catch (Exception var6) {
            System.out.println("Error converting jpg");
        }

    }

    public void dumpImage(String directory, String name, Sprite sprite, boolean transparency) {
        try {
            DirectColorModel model = new DirectColorModel(24, 0xFF0000, 0x00FF00, 0x0000FF);
            int[] bandmasks = new int[]{
                    model.getRedMask(), model.getGreenMask(), model.getBlueMask()
            };
            int w = sprite.myWidth;
            int h = sprite.myHeight;
            int[] pix = sprite.myPixels;
            if (transparency)
                for (int i = 0; i < pix.length; i++)
                    if (pix[i] == 0)
                        pix[i] = 0xFF00FF;
            DataBufferInt buffer = new DataBufferInt(pix, pix.length);
            WritableRaster raster = Raster.createPackedRaster(buffer, w, h, w, bandmasks, null);
            BufferedImage img = new BufferedImage(model, raster, true, new Hashtable<Object, Object>());
            ImageIO.write(img, "png", new File("./" + directory + name + ".png"));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public Image toImage() {
        try {
            DirectColorModel model = new DirectColorModel(24, 0xFF0000, 0x00FF00, 0x0000FF);
            int[] bandmasks = new int[]{
                    model.getRedMask(), model.getGreenMask(), model.getBlueMask()
            };
            int w = this.myWidth;
            int h = this.myHeight;
            int[] pix = this.myPixels;
            for (int i = 0; i < pix.length; i++)
                if (pix[i] == 0)
                    pix[i] = 0xFF00FF;
            DataBufferInt buffer = new DataBufferInt(pix, pix.length);
            WritableRaster raster = Raster.createPackedRaster(buffer, w, h, w, bandmasks, null);
            BufferedImage img = new BufferedImage(model, raster, true, new Hashtable<Object, Object>());
            int color = img.getRGB(0, 0);
            Image image = makeColorTransparent(img, new Color(color));
            return image;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static Image makeColorTransparent(BufferedImage im, final Color color) {
        RGBImageFilter filter = new RGBImageFilter() {

            // the color we are looking for... Alpha bits are set to opaque
            public int markerRGB = color.getRGB() | 0xFF000000;

            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    public void drawSprite(int i, int k, int color) {
        int tempWidth = myWidth + 2;
        int tempHeight = myHeight + 2;
        int[] tempArray = new int[tempWidth * tempHeight];
        for (int x = 0; x < myWidth; x++) {
            for (int y = 0; y < myHeight; y++) {
                if (myPixels[x + y * myWidth] != 0)
                    tempArray[(x + 1) + (y + 1) * tempWidth] = myPixels[x + y * myWidth];
            }
        }
        for (int x = 0; x < tempWidth; x++) {
            for (int y = 0; y < tempHeight; y++) {
                if (tempArray[(x) + (y) * tempWidth] == 0) {
                    if (x < tempWidth - 1 && tempArray[(x + 1) + ((y) * tempWidth)] > 0 && tempArray[(x + 1) + ((y) * tempWidth)] != 0xffffff) {
                        tempArray[(x) + (y) * tempWidth] = color;
                    }
                    if (x > 0 && tempArray[(x - 1) + ((y) * tempWidth)] > 0 && tempArray[(x - 1) + ((y) * tempWidth)] != 0xffffff) {
                        tempArray[(x) + (y) * tempWidth] = color;
                    }
                    if (y < tempHeight - 1 && tempArray[(x) + ((y + 1) * tempWidth)] > 0 && tempArray[(x) + ((y + 1) * tempWidth)] != 0xffffff) {
                        tempArray[(x) + (y) * tempWidth] = color;
                    }
                    if (y > 0 && tempArray[(x) + ((y - 1) * tempWidth)] > 0 && tempArray[(x) + ((y - 1) * tempWidth)] != 0xffffff) {
                        tempArray[(x) + (y) * tempWidth] = color;
                    }
                }
            }
        }
        i--;
        k--;
        i += anInt1442;
        k += anInt1443;
        int l = i + k * Rasterizer2D.width;
        int i1 = 0;
        int j1 = tempHeight;
        int k1 = tempWidth;
        int l1 = Rasterizer2D.width - k1;
        int i2 = 0;
        if (k < Rasterizer2D.clip_top) {
            int j2 = Rasterizer2D.clip_top - k;
            j1 -= j2;
            k = Rasterizer2D.clip_top;
            i1 += j2 * k1;
            l += j2 * Rasterizer2D.width;
        }
        if (k + j1 > Rasterizer2D.clip_bottom) {
            j1 -= (k + j1) - Rasterizer2D.clip_bottom;
        }
        if (i < Rasterizer2D.clip_left) {
            int k2 = Rasterizer2D.clip_left - i;
            k1 -= k2;
            i = Rasterizer2D.clip_left;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (i + k1 > Rasterizer2D.clip_right) {
            int l2 = (i + k1) - Rasterizer2D.clip_right;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (!(k1 <= 0 || j1 <= 0)) {
            method349(Rasterizer2D.pixels, tempArray, i1, l, k1, j1, l1, i2);
        }
    }

    public Sprite(StreamLoader streamLoader, String s, int i) {
        Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
        Stream stream_1 = new Stream(streamLoader.getDataForName("index.dat"));
        stream_1.currentOffset = stream.get_unsigned_short();
        this.max_width = stream_1.get_unsigned_short();
        this.anInt1445 = stream_1.get_unsigned_short();
        int j = stream_1.readUnsignedByte();
        int[] ai = new int[j];

        int i1;
        for (i1 = 0; i1 < j - 1; ++i1) {
            ai[i1 + 1] = stream_1.read3Bytes();
            if (ai[i1 + 1] == 0) {
                ai[i1 + 1] = 1;
            }
        }

        for (i1 = 0; i1 < i; ++i1) {
            stream_1.currentOffset += 2;
            stream.currentOffset += stream_1.get_unsigned_short() * stream_1.get_unsigned_short();
            ++stream_1.currentOffset;
        }

        this.anInt1442 = stream_1.readUnsignedByte();
        this.anInt1443 = stream_1.readUnsignedByte();
        this.myWidth = stream_1.get_unsigned_short();
        this.myHeight = stream_1.get_unsigned_short();
        i1 = stream_1.readUnsignedByte();
        int j1 = this.myWidth * this.myHeight;
        this.myPixels = new int[j1];
        int l1;
        if (i1 == 0) {
            for (l1 = 0; l1 < j1; ++l1) {
                this.myPixels[l1] = ai[stream.readUnsignedByte()];
            }

        } else {
            if (i1 == 1) {
                for (l1 = 0; l1 < this.myWidth; ++l1) {
                    for (int i2 = 0; i2 < this.myHeight; ++i2) {
                        this.myPixels[l1 + i2 * this.myWidth] = ai[stream.readUnsignedByte()];
                    }
                }
            }

        }
    }

    public void method343() {
        Rasterizer2D.initDrawingArea(this.myHeight, this.myWidth, this.myPixels);
    }

    public void method344(int i, int j, int k) {
        for (int i1 = 0; i1 < this.myPixels.length; ++i1) {
            int j1 = this.myPixels[i1];
            if (j1 != 0) {
                int k1 = j1 >> 16 & 255;
                k1 += i;
                if (k1 < 1) {
                    k1 = 1;
                } else if (k1 > 255) {
                    k1 = 255;
                }

                int l1 = j1 >> 8 & 255;
                l1 += j;
                if (l1 < 1) {
                    l1 = 1;
                } else if (l1 > 255) {
                    l1 = 255;
                }

                int i2 = j1 & 255;
                i2 += k;
                if (i2 < 1) {
                    i2 = 1;
                } else if (i2 > 255) {
                    i2 = 255;
                }

                this.myPixels[i1] = (k1 << 16) + (l1 << 8) + i2;
            }
        }

    }

    public void method345() {
        try {
            int totalPixels[] = new int[max_width * anInt1445];
            for (int h = 0; h < myHeight; h++) {
                for (int w = 0; w < myWidth; w++) {
                    totalPixels[(h + anInt1443) * max_width + (w + anInt1442)] = myPixels[h * myWidth + w];
                }
            }
            myPixels = totalPixels;
            myWidth = max_width;
            myHeight = anInt1445;
            anInt1442 = 0;
            anInt1443 = 0;
            return;
        } catch (RuntimeException runtimeexception) {
            SignLink.reporterror("26341, " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public void method346(int i, int j) {
        i += this.anInt1442;
        j += this.anInt1443;
        int l = i + j * Rasterizer2D.width;
        int i1 = 0;
        int j1 = this.myHeight;
        int k1 = this.myWidth;
        int l1 = Rasterizer2D.width - k1;
        int i2 = 0;
        int l2;
        if (j < Rasterizer2D.clip_top) {
            l2 = Rasterizer2D.clip_top - j;
            j1 -= l2;
            j = Rasterizer2D.clip_top;
            i1 += l2 * k1;
            l += l2 * Rasterizer2D.width;
        }

        if (j + j1 > Rasterizer2D.clip_bottom) {
            j1 -= j + j1 - Rasterizer2D.clip_bottom;
        }

        if (i < Rasterizer2D.clip_left) {
            l2 = Rasterizer2D.clip_left - i;
            k1 -= l2;
            i = Rasterizer2D.clip_left;
            i1 += l2;
            l += l2;
            i2 += l2;
            l1 += l2;
        }

        if (i + k1 > Rasterizer2D.clip_right) {
            l2 = i + k1 - Rasterizer2D.clip_right;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }

        if (k1 > 0 && j1 > 0) {
            this.method347(l, k1, j1, i2, i1, l1, this.myPixels, Rasterizer2D.pixels);
        }

    }

    private void method347(int i, int j, int k, int l, int i1, int k1, int[] ai, int[] ai1) {
        int l1 = -(j >> 2);
        j = -(j & 3);

        for (int i2 = -k; i2 < 0; ++i2) {
            int k2;
            for (k2 = l1; k2 < 0; ++k2) {
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
            }

            for (k2 = j; k2 < 0; ++k2) {
                ai1[i++] = ai[i1++];
            }

            i += k1;
            i1 += l;
        }

    }

    public void drawSprite1(int i, int j) {
        short k = 128;
        i += this.anInt1442;
        j += this.anInt1443;
        int i1 = i + j * Rasterizer2D.width;
        int j1 = 0;
        int k1 = this.myHeight;
        int l1 = this.myWidth;
        int i2 = Rasterizer2D.width - l1;
        int j2 = 0;
        int i3;
        if (j < Rasterizer2D.clip_top) {
            i3 = Rasterizer2D.clip_top - j;
            k1 -= i3;
            j = Rasterizer2D.clip_top;
            j1 += i3 * l1;
            i1 += i3 * Rasterizer2D.width;
        }

        if (j + k1 > Rasterizer2D.clip_bottom) {
            k1 -= j + k1 - Rasterizer2D.clip_bottom;
        }

        if (i < Rasterizer2D.clip_left) {
            i3 = Rasterizer2D.clip_left - i;
            l1 -= i3;
            i = Rasterizer2D.clip_left;
            j1 += i3;
            i1 += i3;
            j2 += i3;
            i2 += i3;
        }

        if (i + l1 > Rasterizer2D.clip_right) {
            i3 = i + l1 - Rasterizer2D.clip_right;
            l1 -= i3;
            j2 += i3;
            i2 += i3;
        }

        if (l1 > 0 && k1 > 0) {
            this.method351(j1, l1, Rasterizer2D.pixels, this.myPixels, j2, k1, i2, k, i1);
        }

    }

    public void drawSprite(int i, int k) {
        i += this.anInt1442;
        k += this.anInt1443;
        int l = i + k * Rasterizer2D.width;
        int i1 = 0;
        int j1 = this.myHeight;
        int k1 = this.myWidth;
        int l1 = Rasterizer2D.width - k1;
        int i2 = 0;
        int l2;
        if (k < Rasterizer2D.clip_top) {
            l2 = Rasterizer2D.clip_top - k;
            j1 -= l2;
            k = Rasterizer2D.clip_top;
            i1 += l2 * k1;
            l += l2 * Rasterizer2D.width;
        }

        if (k + j1 > Rasterizer2D.clip_bottom) {
            j1 -= k + j1 - Rasterizer2D.clip_bottom;
        }

        if (i < Rasterizer2D.clip_left) {
            l2 = Rasterizer2D.clip_left - i;
            k1 -= l2;
            i = Rasterizer2D.clip_left;
            i1 += l2;
            l += l2;
            i2 += l2;
            l1 += l2;
        }

        if (i + k1 > Rasterizer2D.clip_right) {
            l2 = i + k1 - Rasterizer2D.clip_right;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }

        if (k1 > 0 && j1 > 0) {
            this.method349(Rasterizer2D.pixels, this.myPixels, i1, l, k1, j1, l1, i2);
        }

    }

    private void method349(int[] ai, int[] ai1, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = -(l >> 2);
        l = -(l & 3);

        for (int i2 = -i1; i2 < 0; ++i2) {
            int i;
            int k2;
            for (k2 = l1; k2 < 0; ++k2) {
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    ++k;
                }

                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    ++k;
                }

                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    ++k;
                }

                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    ++k;
                }
            }

            for (k2 = l; k2 < 0; ++k2) {
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    ++k;
                }
            }

            k += j1;
            j += k1;
        }

    }

    private void method351(int i, int j, int[] ai, int[] ai1, int l, int i1, int j1, int k1, int l1) {
        int j2 = 256 - k1;

        for (int k2 = -i1; k2 < 0; ++k2) {
            for (int l2 = -j; l2 < 0; ++l2) {
                int k = ai1[i++];
                if (k != 0) {
                    int i3 = ai[l1];
                    ai[l1++] = ((k & 16711935) * k1 + (i3 & 16711935) * j2 & -16711936) + ((k & '\uff00') * k1 + (i3 & '\uff00') * j2 & 16711680) >> 8;
                } else {
                    ++l1;
                }
            }

            l1 += j1;
            i += l;
        }

    }

    public void method352(int i, int j, int[] ai, int k, int[] ai1, int i1, int j1, int k1, int l1, int i2) {
        try {
            int _ex = -l1 / 2;
            int k2 = -i / 2;
            int l2 = (int) (Math.sin((double) j / 326.11D) * 65536.0D);
            int i3 = (int) (Math.cos((double) j / 326.11D) * 65536.0D);
            l2 = l2 * k >> 8;
            i3 = i3 * k >> 8;
            int j3 = (i2 << 16) + k2 * l2 + _ex * i3;
            int k3 = (i1 << 16) + k2 * i3 - _ex * l2;
            int l3 = k1 + j1 * Rasterizer2D.width;

            for (j1 = 0; j1 < i; ++j1) {
                int i4 = ai1[j1];
                int j4 = l3 + i4;
                int k4 = j3 + i3 * i4;
                int l4 = k3 - l2 * i4;

                for (k1 = -ai[j1]; k1 < 0; ++k1) {
                    Rasterizer2D.pixels[j4++] = this.myPixels[(k4 >> 16) + (l4 >> 16) * this.myWidth];
                    k4 += i3;
                    l4 -= l2;
                }

                j3 += l2;
                k3 += i3;
                l3 += Rasterizer2D.width;
            }
        } catch (Exception var22) {
            ;
        }

    }

    public void method353(int i, double d, int l1) {
        byte j = 15;
        byte k = 20;
        byte l = 15;
        short j1 = 256;
        byte k1 = 20;

        try {
            int _ex = -k / 2;
            int j2 = -k1 / 2;
            int k2 = (int) (Math.sin(d) * 65536.0D);
            int l2 = (int) (Math.cos(d) * 65536.0D);
            k2 = k2 * j1 >> 8;
            l2 = l2 * j1 >> 8;
            int i3 = (l << 16) + j2 * k2 + _ex * l2;
            int j3 = (j << 16) + j2 * l2 - _ex * k2;
            int k3 = l1 + i * Rasterizer2D.width;

            for (i = 0; i < k1; ++i) {
                int l3 = k3;
                int i4 = i3;
                int j4 = j3;

                for (l1 = -k; l1 < 0; ++l1) {
                    int k4 = this.myPixels[(i4 >> 16) + (j4 >> 16) * this.myWidth];
                    if (k4 != 0) {
                        Rasterizer2D.pixels[l3++] = k4;
                    } else {
                        ++l3;
                    }

                    i4 += l2;
                    j4 -= k2;
                }

                i3 += k2;
                j3 += l2;
                k3 += Rasterizer2D.width;
            }
        } catch (Exception var21) {
            ;
        }

    }

    public void method354(Background background, int i, int j) {
        j += this.anInt1442;
        i += this.anInt1443;
        int k = j + i * Rasterizer2D.width;
        int l = 0;
        int i1 = this.myHeight;
        int j1 = this.myWidth;
        int k1 = Rasterizer2D.width - j1;
        int l1 = 0;
        int k2;
        if (i < Rasterizer2D.clip_top) {
            k2 = Rasterizer2D.clip_top - i;
            i1 -= k2;
            i = Rasterizer2D.clip_top;
            l += k2 * j1;
            k += k2 * Rasterizer2D.width;
        }

        if (i + i1 > Rasterizer2D.clip_bottom) {
            i1 -= i + i1 - Rasterizer2D.clip_bottom;
        }

        if (j < Rasterizer2D.clip_left) {
            k2 = Rasterizer2D.clip_left - j;
            j1 -= k2;
            j = Rasterizer2D.clip_left;
            l += k2;
            k += k2;
            l1 += k2;
            k1 += k2;
        }

        if (j + j1 > Rasterizer2D.clip_right) {
            k2 = j + j1 - Rasterizer2D.clip_right;
            j1 -= k2;
            l1 += k2;
            k1 += k2;
        }

        if (j1 > 0 && i1 > 0) {
            this.method355(this.myPixels, j1, background.aByteArray1450, i1, Rasterizer2D.pixels, 0, k1, k, l1, l);
        }

    }

    private void method355(int[] ai, int i, byte[] abyte0, int j, int[] ai1, int k, int l, int i1, int j1, int k1) {
        int l1 = -(i >> 2);
        i = -(i & 3);

        for (int j2 = -j; j2 < 0; ++j2) {
            int l2;
            for (l2 = l1; l2 < 0; ++l2) {
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    ++i1;
                }

                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    ++i1;
                }

                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    ++i1;
                }

                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    ++i1;
                }
            }

            for (l2 = i; l2 < 0; ++l2) {
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    ++i1;
                }
            }

            i1 += l;
            k1 += j1;
        }

    }
}
