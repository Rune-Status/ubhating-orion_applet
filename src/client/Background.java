package client;

public final class Background extends Rasterizer2D {

   public byte[] aByteArray1450;
   public final int[] anIntArray1451;
   public int anInt1452;
   public int anInt1453;
   public int anInt1454;
   public int anInt1455;
   public int anInt1456;
   private int anInt1457;

   
  /* public Background(byte spriteData[]) {
		try {
			Image image = Toolkit.getDefaultToolkit().createImage(spriteData);
			ImageIcon sprite = new ImageIcon(image);
			anInt1452 = sprite.getIconWidth();
			anInt1453 = sprite.getIconHeight();
			anInt1456 = anInt1452;
			anInt1457 = anInt1453;
			anInt1454 = 0;
			anInt1455 = 0;
			aByteArray1450 = new byte[anInt1452 * anInt1453];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, anInt1452,
					anInt1453, (int)aByteArray1450, 0, anInt1452);
			pixelgrabber.grabPixels();
			image = null;
			setTransparency(255, 0, 255);
		} catch (Exception _ex) {
			System.out.println(_ex);
		}
	}*/
	
	/*public Background(Sprite sprite) {
		anIntArray1451 = null;
		try {
			anInt1452 = sprite.myWidth;
			anInt1453 = sprite.myHeight;
			anInt1456 = anInt1452;
			anInt1457 = anInt1453;
			anInt1454 = 0;
			anInt1455 = 0;
			aByteArray1450 = new byte[anInt1452 * anInt1453];
			for(int i = 0; i < sprite.myPixels.length; i++){
				aByteArray1450[i] = (byte)sprite.myPixels[i];
			}
		} catch (Exception _ex) {
			System.out.println(_ex);
		}
	}
	
	public void setTransparency(int transRed, int transGreen, int transBlue) {
		for (int index = 0; index < aByteArray1450.length; index++)
			if (((aByteArray1450[index] >> 16) & 255) == transRed
					&& ((aByteArray1450[index] >> 8) & 255) == transGreen
					&& (aByteArray1450[index] & 255) == transBlue)
				aByteArray1450[index] = 0;
	}*/

   public Background(StreamLoader streamLoader, String s, int i) {
      Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
      Stream stream_1 = new Stream(streamLoader.getDataForName("index.dat"));
      stream_1.currentOffset = stream.readUnsignedWord();
      this.anInt1456 = stream_1.readUnsignedWord();
      this.anInt1457 = stream_1.readUnsignedWord();
      int j = stream_1.readUnsignedByte();
      this.anIntArray1451 = new int[j];

      int i1;
      for(i1 = 0; i1 < j - 1; ++i1) {
         this.anIntArray1451[i1 + 1] = stream_1.read3Bytes();
      }

      for(i1 = 0; i1 < i; ++i1) {
         stream_1.currentOffset += 2;
         stream.currentOffset += stream_1.readUnsignedWord() * stream_1.readUnsignedWord();
         ++stream_1.currentOffset;
      }

      this.anInt1454 = stream_1.readUnsignedByte();
      this.anInt1455 = stream_1.readUnsignedByte();
      this.anInt1452 = stream_1.readUnsignedWord();
      this.anInt1453 = stream_1.readUnsignedWord();
      i1 = stream_1.readUnsignedByte();
      int j1 = this.anInt1452 * this.anInt1453;
      this.aByteArray1450 = new byte[j1];
      int l1;
      if(i1 == 0) {
         for(l1 = 0; l1 < j1; ++l1) {
            this.aByteArray1450[l1] = stream.readSignedByte();
         }

      } else {
         if(i1 == 1) {
            for(l1 = 0; l1 < this.anInt1452; ++l1) {
               for(int i2 = 0; i2 < this.anInt1453; ++i2) {
                  this.aByteArray1450[l1 + i2 * this.anInt1452] = stream.readSignedByte();
               }
            }
         }

      }
   }

   public void method356() {
      this.anInt1456 /= 2;
      this.anInt1457 /= 2;
      byte[] abyte0 = new byte[this.anInt1456 * this.anInt1457];
      int i = 0;

      for(int j = 0; j < this.anInt1453; ++j) {
         for(int k = 0; k < this.anInt1452; ++k) {
            abyte0[(k + this.anInt1454 >> 1) + (j + this.anInt1455 >> 1) * this.anInt1456] = this.aByteArray1450[i++];
         }
      }

      this.aByteArray1450 = abyte0;
      this.anInt1452 = this.anInt1456;
      this.anInt1453 = this.anInt1457;
      this.anInt1454 = 0;
      this.anInt1455 = 0;
   }

   public void method357() {
      if(this.anInt1452 != this.anInt1456 || this.anInt1453 != this.anInt1457) {
         byte[] abyte0 = new byte[this.anInt1456 * this.anInt1457];
         int i = 0;

         for(int j = 0; j < this.anInt1453; ++j) {
            for(int k = 0; k < this.anInt1452; ++k) {
               abyte0[k + this.anInt1454 + (j + this.anInt1455) * this.anInt1456] = this.aByteArray1450[i++];
            }
         }

         this.aByteArray1450 = abyte0;
         this.anInt1452 = this.anInt1456;
         this.anInt1453 = this.anInt1457;
         this.anInt1454 = 0;
         this.anInt1455 = 0;
      }
   }

   public void method358() {
      byte[] abyte0 = new byte[this.anInt1452 * this.anInt1453];
      int j = 0;

      for(int k = 0; k < this.anInt1453; ++k) {
         for(int l = this.anInt1452 - 1; l >= 0; --l) {
            abyte0[j++] = this.aByteArray1450[l + k * this.anInt1452];
         }
      }

      this.aByteArray1450 = abyte0;
      this.anInt1454 = this.anInt1456 - this.anInt1452 - this.anInt1454;
   }

   public void method359() {
      byte[] abyte0 = new byte[this.anInt1452 * this.anInt1453];
      int i = 0;

      for(int j = this.anInt1453 - 1; j >= 0; --j) {
         for(int k = 0; k < this.anInt1452; ++k) {
            abyte0[i++] = this.aByteArray1450[k + j * this.anInt1452];
         }
      }

      this.aByteArray1450 = abyte0;
      this.anInt1455 = this.anInt1457 - this.anInt1453 - this.anInt1455;
   }

   public void method360(int i, int j, int k) {
      for(int i1 = 0; i1 < this.anIntArray1451.length; ++i1) {
         int j1 = this.anIntArray1451[i1] >> 16 & 255;
         j1 += i;
         if(j1 < 0) {
            j1 = 0;
         } else if(j1 > 255) {
            j1 = 255;
         }

         int k1 = this.anIntArray1451[i1] >> 8 & 255;
         k1 += j;
         if(k1 < 0) {
            k1 = 0;
         } else if(k1 > 255) {
            k1 = 255;
         }

         int l1 = this.anIntArray1451[i1] & 255;
         l1 += k;
         if(l1 < 0) {
            l1 = 0;
         } else if(l1 > 255) {
            l1 = 255;
         }

         this.anIntArray1451[i1] = (j1 << 16) + (k1 << 8) + l1;
      }

   }

   public void method361(int i, int k) {
      i += this.anInt1454;
      k += this.anInt1455;
      int l = i + k * Rasterizer2D.width;
      int i1 = 0;
      int j1 = this.anInt1453;
      int k1 = this.anInt1452;
      int l1 = Rasterizer2D.width - k1;
      int i2 = 0;
      int l2;
      if(k < Rasterizer2D.topY) {
         l2 = Rasterizer2D.topY - k;
         j1 -= l2;
         k = Rasterizer2D.topY;
         i1 += l2 * k1;
         l += l2 * Rasterizer2D.width;
      }

      if(k + j1 > Rasterizer2D.bottomY) {
         j1 -= k + j1 - Rasterizer2D.bottomY;
      }

      if(i < Rasterizer2D.topX) {
         l2 = Rasterizer2D.topX - i;
         k1 -= l2;
         i = Rasterizer2D.topX;
         i1 += l2;
         l += l2;
         i2 += l2;
         l1 += l2;
      }

      if(i + k1 > Rasterizer2D.bottomX) {
         l2 = i + k1 - Rasterizer2D.bottomX;
         k1 -= l2;
         i2 += l2;
         l1 += l2;
      }

      if(k1 > 0 && j1 > 0) {
         this.method362(j1, Rasterizer2D.pixels, this.aByteArray1450, l1, l, k1, i1, this.anIntArray1451, i2);
      }

   }

   private void method362(int i, int[] ai, byte[] abyte0, int j, int k, int l, int i1, int[] ai1, int j1) {
      int k1 = -(l >> 2);
      l = -(l & 3);

      for(int l1 = -i; l1 < 0; ++l1) {
         int j2;
         byte byte2;
         for(j2 = k1; j2 < 0; ++j2) {
            byte2 = abyte0[i1++];
            if(byte2 != 0) {
               ai[k++] = ai1[byte2 & 255];
            } else {
               ++k;
            }

            byte2 = abyte0[i1++];
            if(byte2 != 0) {
               ai[k++] = ai1[byte2 & 255];
            } else {
               ++k;
            }

            byte2 = abyte0[i1++];
            if(byte2 != 0) {
               ai[k++] = ai1[byte2 & 255];
            } else {
               ++k;
            }

            byte2 = abyte0[i1++];
            if(byte2 != 0) {
               ai[k++] = ai1[byte2 & 255];
            } else {
               ++k;
            }
         }

         for(j2 = l; j2 < 0; ++j2) {
            byte2 = abyte0[i1++];
            if(byte2 != 0) {
               ai[k++] = ai1[byte2 & 255];
            } else {
               ++k;
            }
         }

         k += j;
         i1 += j1;
      }

   }
}
