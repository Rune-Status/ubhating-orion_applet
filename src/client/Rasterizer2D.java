package client;


public class Rasterizer2D extends NodeSub {

   public static int[] pixels;
   public static int width;
   public static int height;
   public static int clip_top;
   public static int clip_bottom;
   public static int clip_left;
   public static int clip_right;
   public static int center_x;
   public static int viewport_center_y;
   public static int anInt1387;


   public static void initDrawingArea(int i, int j, int[] ai) {
      pixels = ai;
      width = j;
      height = i;
      set_clip(i, 0, j, 0);
   }

   public static void defaultDrawingAreaSize() {
      clip_left = 0;
      clip_top = 0;
      clip_right = width;
      clip_bottom = height;
      center_x = clip_right;
      viewport_center_y = clip_right / 2;
   }

   public static void set_clip(int height, int x, int width, int y) {
      if(x < 0) {
         x = 0;
      }

      if(y < 0) {
         y = 0;
      }

      if(width > Rasterizer2D.width) {
         width = Rasterizer2D.width;
      }

      if(height > Rasterizer2D.height) {
         height = Rasterizer2D.height;
      }

      clip_left = x;
      clip_top = y;
      clip_right = width;
      clip_bottom = height;
	  center_x = clip_right;
      viewport_center_y = clip_right / 2;
      anInt1387 = clip_bottom / 2;
   }

   public static void setAllPixelsToZero() {
      int i = width * height;

      for(int j = 0; j < i; ++j) {
         pixels[j] = 0;
      }

   }

   public static void draw_filled_rect(int color, int y, int width, int height, int alpha, int x) {
      if(x < clip_left) {
         width -= clip_left - x;
         x = clip_left;
      }

      if(y < clip_top) {
         height -= clip_top - y;
         y = clip_top;
      }

      if(x + width > clip_right) {
         width = clip_right - x;
      }

      if(y + height > clip_bottom) {
         height = clip_bottom - y;
      }

      int l1 = 256 - alpha;
      int i2 = (color >> 16 & 255) * alpha;
      int j2 = (color >> 8 & 255) * alpha;
      int k2 = (color & 255) * alpha;
      int k3 = Rasterizer2D.width - width;
      int l3 = x + y * Rasterizer2D.width;

      for(int i4 = 0; i4 < height; ++i4) {
         for(int j4 = -width; j4 < 0; ++j4) {
            int l2 = (pixels[l3] >> 16 & 255) * l1;
            int i3 = (pixels[l3] >> 8 & 255) * l1;
            int j3 = (pixels[l3] & 255) * l1;
            int k4 = (i2 + l2 >> 8 << 16) + (j2 + i3 >> 8 << 8) + (k2 + j3 >> 8);
            pixels[l3++] = k4;
         }

         l3 += k3;
      }

   }

   public static void draw_filled_rect(int i, int j, int k, int l, int i1) {
      if(k < clip_left) {
         i1 -= clip_left - k;
         k = clip_left;
      }

      if(j < clip_top) {
         i -= clip_top - j;
         j = clip_top;
      }

      if(k + i1 > clip_right) {
         i1 = clip_right - k;
      }

      if(j + i > clip_bottom) {
         i = clip_bottom - j;
      }

      int k1 = width - i1;
      int l1 = k + j * width;

      for(int i2 = -i; i2 < 0; ++i2) {
         for(int j2 = -i1; j2 < 0; ++j2) {
            pixels[l1++] = l;
         }

         l1 += k1;
      }

   }

   public static void fillPixels(int i, int j, int k, int l, int i1) {
      method339(i1, l, j, i);
      method339(i1 + k - 1, l, j, i);
      method341(i1, l, k, i);
      method341(i1, l, k, i + j - 1);
   }

   public static void method338(int i, int j, int k, int l, int i1, int j1) {
      method340(l, i1, i, k, j1);
      method340(l, i1, i + j - 1, k, j1);
      if(j >= 3) {
         method342(l, j1, k, i + 1, j - 2);
         method342(l, j1 + i1 - 1, k, i + 1, j - 2);
      }

   }

   public static void method339(int i, int j, int k, int l) {
      if(i >= clip_top && i < clip_bottom) {
         if(l < clip_left) {
            k -= clip_left - l;
            l = clip_left;
         }

         if(l + k > clip_right) {
            k = clip_right - l;
         }

         int i1 = l + i * width;

         for(int j1 = 0; j1 < k; ++j1) {
            pixels[i1 + j1] = j;
         }

      }
   }

   private static void method340(int i, int j, int k, int l, int i1) {
      if(k >= clip_top && k < clip_bottom) {
         if(i1 < clip_left) {
            j -= clip_left - i1;
            i1 = clip_left;
         }

         if(i1 + j > clip_right) {
            j = clip_right - i1;
         }

         int j1 = 256 - l;
         int k1 = (i >> 16 & 255) * l;
         int l1 = (i >> 8 & 255) * l;
         int i2 = (i & 255) * l;
         int i3 = i1 + k * width;

         for(int j3 = 0; j3 < j; ++j3) {
            int j2 = (pixels[i3] >> 16 & 255) * j1;
            int k2 = (pixels[i3] >> 8 & 255) * j1;
            int l2 = (pixels[i3] & 255) * j1;
            int k3 = (k1 + j2 >> 8 << 16) + (l1 + k2 >> 8 << 8) + (i2 + l2 >> 8);
            pixels[i3++] = k3;
         }

      }
   }

   public static void method341(int i, int j, int k, int l) {
      if(l >= clip_left && l < clip_right) {
         if(i < clip_top) {
            k -= clip_top - i;
            i = clip_top;
         }

         if(i + k > clip_bottom) {
            k = clip_bottom - i;
         }

         int j1 = l + i * width;

         for(int k1 = 0; k1 < k; ++k1) {
            pixels[j1 + k1 * width] = j;
         }

      }
   }

   private static void method342(int i, int j, int k, int l, int i1) {
      if(j >= clip_left && j < clip_right) {
         if(l < clip_top) {
            i1 -= clip_top - l;
            l = clip_top;
         }

         if(l + i1 > clip_bottom) {
            i1 = clip_bottom - l;
         }

         int j1 = 256 - k;
         int k1 = (i >> 16 & 255) * k;
         int l1 = (i >> 8 & 255) * k;
         int i2 = (i & 255) * k;
         int i3 = j + l * width;

         for(int j3 = 0; j3 < i1; ++j3) {
            int j2 = (pixels[i3] >> 16 & 255) * j1;
            int k2 = (pixels[i3] >> 8 & 255) * j1;
            int l2 = (pixels[i3] & 255) * j1;
            int k3 = (k1 + j2 >> 8 << 16) + (l1 + k2 >> 8 << 8) + (i2 + l2 >> 8);
            pixels[i3] = k3;
            i3 += width;
         }

      }
   }

}
