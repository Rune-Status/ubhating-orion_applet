package client;

import java.io.FileWriter;

public final class NpcDefinition {

   public int anInt55 = -1;
   private static int anInt56;
   private int anInt57 = -1;
   public int anInt58 = -1;
   private int anInt59 = -1;
   private static Stream stream;
   public int combatLevel = -1;
   public String name;
   public String[] actions;
   public int anInt67 = -1;
   public byte aByte68 = 1;
   private int[] anIntArray70;
   private static int[] streamIndices;
   private int[] anIntArray73;
   public int headicon = -1;
   private int[] anIntArray76;
   public int anInt77 = -1;
   public long type = -1L;
   public int anInt79 = 32;
   private static NpcDefinition[] cache;
   public static Client clientInstance;
   public int anInt83 = -1;
   public boolean interactive = true;
   private int anInt85;
   private int anInt86 = 128;
   public boolean map_marker = true;
   public int[] configs;
   public byte[] description;
   private int anInt91 = 128;
   private int anInt92;
   public boolean aBoolean93 = false;
   private int[] anIntArray94;
   public static MRUNodes mruNodes = new MRUNodes(30);
   public boolean getModelValues = false;

   
   public static Sprite getSprite2(int i, int j, int k, int maxSize) {
		// System.out.println("Sprite for: " + i);
		/*if (k == 0) {
			Sprite sprite = (Sprite) mruNodes.insertFromCache(i);
			if (sprite != null && sprite.max_width != j
					&& sprite.max_width != -1) {
				sprite.remove();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}*/
	   int rotateY = 200;
	   int rotateX = 200;
	   int offset1 = 0;
	   int offset2 = 0;
	   int unknown1 = 0;
		
		NpcDefinition npc = forID(i);
		if(npc.anIntArray94 == null)
			return null;
		/*
		 int l;
      if(super.anim >= 0 && super.anInt1529 == 0) {
         l = Animation.anims[super.anim].anIntArray353[super.anInt1527];
         int i1 = -1;
         if(super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
            i1 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
         }

         return this.desc.method164(i1, l, Animation.anims[super.anim].anIntArray357);
      } else {
         l = -1;
         if(super.anInt1517 >= 0) {
            l = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
         }

         return this.desc.method164(-1, l, (int[])null);
      }
		 */
		int l = -1;
		if(npc.anInt77 != -1)
			l = Animation.anims[npc.anInt77].anIntArray353[0];
		Model model = npc.method164(-1, l, (int[])null);
		if (model == null){
			return null;
		}
		/*if(npc.anInt77 != -1)
			model.method470(Animation.anims[npc.anInt77].anIntArray353[0]);*/
		Sprite sprite = null;
		Sprite enabledSprite = new Sprite(maxSize, maxSize);
		//System.out.println("Sprite Loaded for: " + i);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = Rasterizer2D.pixels;
		int i2 = Rasterizer2D.width;
		int j2 = Rasterizer2D.height;
		int k2 = Rasterizer2D.clip_left;
		int l2 = Rasterizer2D.clip_right;
		int i3 = Rasterizer2D.clip_top;
		int j3 = Rasterizer2D.clip_bottom;
		Texture.aBoolean1464 = false;
		Rasterizer2D.initDrawingArea(maxSize, maxSize, enabledSprite.myPixels);
		Rasterizer2D.draw_filled_rect(maxSize, 0, 0, 0, maxSize);
		Texture.method364();
		double zoom = (520*Math.log(npc.aByte68))+520;
		int k3 = (int) zoom;//zoom
		//System.out.println("k3 "+k3);
		if (k == -1)
			k3 = (int) (k3 * 1.5D);
		if (k > 0)
			k3 = (int) (k3 * 1.04D);
		int l3 = Texture.anIntArray1470[rotateY] * k3 >> 16;
		int i4 = Texture.anIntArray1471[rotateY] * k3 >> 16;
		model.method482(rotateX, unknown1,
				rotateY, offset1, l3
						+ model.modelHeight / 2 + offset2, i4
						+ offset2);
		/*
		 int l3 = Texture.anIntArray1470[itemDef.modelRotationY] * k3 >> 16;
		int i4 = Texture.anIntArray1471[itemDef.modelRotationY] * k3 >> 16;
		model.method482(itemDef.modelRotationX, itemDef.anInt204,
				itemDef.modelRotationY, itemDef.modelOffset1, l3
						+ model.modelHeight / 2 + itemDef.modelOffset2, i4
						+ itemDef.modelOffset2);
		 */
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--)
				if (enabledSprite.myPixels[i5 + j4 * maxSize] == 0)
					if (i5 > 0
							&& enabledSprite.myPixels[(i5 - 1) + j4 * maxSize] > 1)
						enabledSprite.myPixels[i5 + j4 * maxSize] = 1;
					else if (j4 > 0
							&& enabledSprite.myPixels[i5 + (j4 - 1) * maxSize] > 1)
						enabledSprite.myPixels[i5 + j4 * maxSize] = 1;
					else if (i5 < 31
							&& enabledSprite.myPixels[i5 + 1 + j4 * maxSize] > 1)
						enabledSprite.myPixels[i5 + j4 * maxSize] = 1;
					else if (j4 < 31
							&& enabledSprite.myPixels[i5 + (j4 + 1) * maxSize] > 1)
						enabledSprite.myPixels[i5 + j4 * maxSize] = 1;

		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--)
					if (enabledSprite.myPixels[j5 + k4 * maxSize] == 0)
						if (j5 > 0
								&& enabledSprite.myPixels[(j5 - 1) + k4 * maxSize] == 1)
							enabledSprite.myPixels[j5 + k4 * maxSize] = k;
						else if (k4 > 0
								&& enabledSprite.myPixels[j5 + (k4 - 1) * maxSize] == 1)
							enabledSprite.myPixels[j5 + k4 * maxSize] = k;
						else if (j5 < 31
								&& enabledSprite.myPixels[j5 + 1 + k4 * maxSize] == 1)
							enabledSprite.myPixels[j5 + k4 * maxSize] = k;
						else if (k4 < 31
								&& enabledSprite.myPixels[j5 + (k4 + 1) * maxSize] == 1)
							enabledSprite.myPixels[j5 + k4 * maxSize] = k;

			}

		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (enabledSprite.myPixels[k5 + l4 * maxSize] == 0
							&& k5 > 0
							&& l4 > 0
							&& enabledSprite.myPixels[(k5 - 1) + (l4 - 1) * maxSize] > 0)
						enabledSprite.myPixels[k5 + l4 * maxSize] = 0x302020;

			}

		}
		if (k == 0)
			mruNodes.removeFromCache(enabledSprite, i);
		Rasterizer2D.initDrawingArea(j2, i2, ai1);
		Rasterizer2D.set_clip(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		enabledSprite.anInt1445 = maxSize;
		enabledSprite.max_width = j;
		return enabledSprite;
	}
   

   public static NpcDefinition forID(int i) {
      for(int entityDef = 0; entityDef < 20; ++entityDef) {
         if(cache[entityDef].type == (long)i) {
            return cache[entityDef];
         }
      }

      anInt56 = (anInt56 + 1) % 20;
      NpcDefinition var2 = cache[anInt56] = new NpcDefinition();
      stream.currentOffset = streamIndices[i];
      var2.type = (long)i;
      var2.readValues(stream);
      switch(i) {
      
  	  /*
-10986;
-10742;
-10866;
  	   * 
  	   * var2.getModelValues = true;
61
.2368
.2370
.2361
.3253
.553
1455
.2365
.2332
.3379
.561
8
  	   */
    /*  case 1618:
    	  var2.anIntArray76 = new int[10];
          var2.anIntArray70 = new int[10];
          var2.anIntArray76[0] = 2368;
          var2.anIntArray70[0] = -10986;
          var2.anIntArray76[1] = 2370;
          var2.anIntArray70[1] = -10986;
          var2.anIntArray76[2] = 2361;
          var2.anIntArray70[2] = -10986;
          var2.anIntArray76[3] = 2365;
          var2.anIntArray70[3] = -10986;
          var2.anIntArray76[4] = 2332;
          var2.anIntArray70[4] = -10986;
          var2.anIntArray76[5] = 3253;
          var2.anIntArray70[5] = -10866;
          var2.anIntArray76[6] = 3379;
          var2.anIntArray70[6] = -10866;
          var2.anIntArray76[7] = 561;
          var2.anIntArray70[7] = -10742;
          var2.anIntArray76[8] = 553;
          var2.anIntArray70[8] = -10742;
          var2.anIntArray76[9] = 1455;
          var2.anIntArray70[9] = -10742;
      	  break;
      
      case 83:
    	  var2.anIntArray76 = new int[5];
          var2.anIntArray70 = new int[5];
          var2.anIntArray76[0] = 918;
          var2.anIntArray70[0] = -10724;
          var2.anIntArray76[1] = 898;
          var2.anIntArray70[1] = -10742;
          var2.anIntArray76[2] = 900;
          var2.anIntArray70[2] = -10742;
          var2.anIntArray76[3] = 929;
          var2.anIntArray70[3] = -10713;
          var2.anIntArray76[4] = 115;
  		var2.anIntArray70[4] = -10866;
      	  break;
      
      case 1615:
    	  var2.anIntArray76 = new int[3];
          var2.anIntArray70 = new int[3];
          var2.anIntArray76[0] = 4015;
          var2.anIntArray70[0] = -10986;
          var2.anIntArray76[1] = 16;
          var2.anIntArray70[1] = -10742;
          var2.anIntArray76[2] = 920;
          var2.anIntArray70[2] = -10866;
      	  break;
      
      case 2613:
    	  var2.anIntArray76 = new int[7];
          var2.anIntArray70 = new int[7];
          var2.anIntArray76[0] = 3005;
  		var2.anIntArray70[0] = -10986;
  		var2.anIntArray76[1] = 406;
  		var2.anIntArray70[1] = -10866;
  		var2.anIntArray76[2] = 33;
  		var2.anIntArray70[2] = -10866;
  		var2.anIntArray76[3] = 28;
  		var2.anIntArray70[3] = -10866;
  		var2.anIntArray76[4] = 24;
  		var2.anIntArray70[4] = -10866;
  		var2.anIntArray76[5] = 20;
  		var2.anIntArray70[5] = -10866;
  		var2.anIntArray76[6] = 908;
  		var2.anIntArray70[6] = -10742;
      	  break;
      
      case 2455:
    	  var2.anIntArray76 = new int[7];
          var2.anIntArray70 = new int[7];
          var2.anIntArray76[0] = 1562;
  		var2.anIntArray70[0] = -10742;
  		var2.anIntArray76[1] = 10275;
  		var2.anIntArray70[1] = -10986;
  		var2.anIntArray76[2] = 10304;
  		var2.anIntArray70[2] = -10986;
  		var2.anIntArray76[3] = 10291;
  		var2.anIntArray70[3] = -10986;
  		var2.anIntArray76[4] = 10287;
  		var2.anIntArray70[4] = -10986;
  		var2.anIntArray76[5] = 10283;
  		var2.anIntArray70[5] = -10986;
  		var2.anIntArray76[6] = 10279;
  		var2.anIntArray70[6] = -10986;
      	  break;
      
      case 1155:
    	  var2.anIntArray76 = new int[4];
          var2.anIntArray70 = new int[4];
          var2.anIntArray76[0] = 22404;
  		var2.anIntArray70[0] = -10742;
  		var2.anIntArray76[1] = 22412;
  		var2.anIntArray70[1] = -10866;
  		var2.anIntArray76[2] = 10659;
  		var2.anIntArray70[2] = -10866;
  		var2.anIntArray76[3] = 916;
  		var2.anIntArray70[3] = -10742;
      	  break;
      
      case 1138:
    	  var2.anIntArray76 = new int[6];
          var2.anIntArray70 = new int[6];
    		var2.anIntArray76[0] = 8481;
    		var2.anIntArray70[0] = -10866;
    		var2.anIntArray76[1] = 10295;
    		var2.anIntArray70[1] = -10742;
    		var2.anIntArray76[2] = 10287;
    		var2.anIntArray70[2] = -10742;
    		var2.anIntArray76[3] = 61;
    		var2.anIntArray70[3] = -10986;
    		var2.anIntArray76[4] = 21570;
    		var2.anIntArray70[4] = -10986;
    		var2.anIntArray76[5] = 10361;
    		var2.anIntArray70[5] = -10866;
      	  break;
      
      case 1199:
    	  var2.anIntArray76 = new int[6];
          var2.anIntArray70 = new int[6];
    		var2.anIntArray76[0] = 4550;
    		var2.anIntArray70[0] = -10986;
    		var2.anIntArray76[1] = 9152;
    		var2.anIntArray70[1] = -10866;
    		var2.anIntArray76[2] = 7958;
    		var2.anIntArray70[2] = -10866;
    		var2.anIntArray76[3] = 16701;
    		var2.anIntArray70[3] = -10866;
    		var2.anIntArray76[4] = 7595;
    		var2.anIntArray70[4] = -10866;
    		var2.anIntArray76[5] = 25886;
    		var2.anIntArray70[5] = -10742;
      	  break;
      
      case 114:
    	  var2.anIntArray76 = new int[2];
          var2.anIntArray70 = new int[2];
    		var2.anIntArray76[0] = 6482;
    		var2.anIntArray70[0] = -10986;
    		var2.anIntArray76[1] = 8722;
    		var2.anIntArray70[1] = -10866;
      	  break;
      
      case 117:
    	  var2.anIntArray76 = new int[5];
          var2.anIntArray70 = new int[5];
    		var2.anIntArray76[0] = 4550;
    		var2.anIntArray70[0] = -10986;
    		var2.anIntArray76[1] = 5437;
    		var2.anIntArray70[1] = -10986;
    		var2.anIntArray76[2] = 39145;
    		var2.anIntArray70[2] = -10866;
    		var2.anIntArray76[3] = 8076;
    		var2.anIntArray70[3] = -10866;
    		var2.anIntArray76[4] = 4546;
    		var2.anIntArray70[4] = -10742;
      	  break;
      
      case 181:
    	  var2.anIntArray76 = new int[5];
          var2.anIntArray70 = new int[5];
    		var2.anIntArray76[0] = 25238;
    		var2.anIntArray70[0] = -10866;
    		var2.anIntArray76[1] = 8741;
    		var2.anIntArray70[1] = -10866;
    		var2.anIntArray76[2] = 6798;
    		var2.anIntArray70[2] = -10866;
    		var2.anIntArray76[3] = 4626;
    		var2.anIntArray70[3] = -10986;
    		var2.anIntArray76[4] = 4550;
    		var2.anIntArray70[4] = -10986;
      	  break;
      
      case 1593:
          var2.anIntArray76 = new int[2];
          var2.anIntArray70 = new int[2];
    		var2.anIntArray76[0] = 5547;
    		var2.anIntArray70[0] = -10986;
    		var2.anIntArray76[1] = 6461;
    		var2.anIntArray70[1] = -10986;
      	  break;
      
      case 89:
    	  var2.anIntArray76 = new int[5];
          var2.anIntArray70 = new int[5];
    		var2.anIntArray76[0] = 7289;
    		var2.anIntArray70[0] = -10986;
    		var2.anIntArray76[1] = 8666;
    		var2.anIntArray70[1] = -10866;
    		var2.anIntArray76[2] = 6241;
    		var2.anIntArray70[2] = -10866;
    		var2.anIntArray76[3] = 7244;
    		var2.anIntArray70[3] = -10866;
    		var2.anIntArray76[4] = 115;
    		var2.anIntArray70[4] = -10866;
      	  break;
      
      case 107:
    	  var2.anIntArray76 = new int[2];
          var2.anIntArray70 = new int[2];
    		var2.anIntArray76[0] = 3627;
    		var2.anIntArray70[0] = -10986;
    		var2.anIntArray76[1] = 3738;
    		var2.anIntArray70[1] = -10875;
      	  break;
      
      case 132:
    	  var2.anIntArray76 = new int[2];
          var2.anIntArray70 = new int[2];
    		var2.anIntArray76[0] = 6932;
    		var2.anIntArray70[0] = -10866;
    		var2.anIntArray76[1] = 7721;
    		var2.anIntArray70[1] = -10986;
      	  break;
      
      case 100:
    	  var2.anIntArray76 = new int[5];
          var2.anIntArray70 = new int[5];
    		var2.anIntArray76[0] = 14238;
    		var2.anIntArray70[0] = -10986;
    		var2.anIntArray76[1] = 41;
    		var2.anIntArray70[1] = -10866;
    		var2.anIntArray76[2] = 916;
    		var2.anIntArray70[2] = -10742;
    		var2.anIntArray76[3] = 8086;
    		var2.anIntArray70[3] = -10986;
    		var2.anIntArray76[4] = 11177;
    		var2.anIntArray70[4] = -10742;
      	  break;
      
      case 708:
    	  var2.anIntArray76 = new int[6];
          var2.anIntArray70 = new int[6];
    		var2.anIntArray76[0] = 926;
    		var2.anIntArray70[0] = -10866;
    		var2.anIntArray76[1] = 924;
    		var2.anIntArray70[1] = -10868;
    		var2.anIntArray76[2] = 937;
    		var2.anIntArray70[2] = -10855;
    		var2.anIntArray76[3] = 4550;
    		var2.anIntArray70[3] = -10986;
    		var2.anIntArray76[4] = 127;
    		var2.anIntArray70[4] = -10866;
    		var2.anIntArray76[5] = 11200;
    		var2.anIntArray70[5] = -10866;
      	  break;
      
      case 53:
    	  var2.anIntArray76 = new int[3];
          var2.anIntArray70 = new int[3];
    		var2.anIntArray76[0] = 61;
    		var2.anIntArray70[0] = -10986;
    		var2.anIntArray76[1] = 127;
    		var2.anIntArray70[1] = -10866;
    		var2.anIntArray76[2] = 115;
    		var2.anIntArray70[2] = -10866;
      	  break;
      
      case 106:
    	var2.anIntArray76 = new int[4];
        var2.anIntArray70 = new int[4];
  		var2.anIntArray76[0] = 3491;
  		var2.anIntArray70[0] = -10986;
  		var2.anIntArray76[1] = 43038;
  		var2.anIntArray70[1] = -10742;
  		var2.anIntArray76[2] = 3346;
  		var2.anIntArray70[2] = -10866;
  		var2.anIntArray76[3] = 3377;
  		var2.anIntArray70[3] = -10980;
    	  break;*/
	  //case 125:
	  /*case 178:
		var2.getModelValues = true;
	  break;*/
	 /* case 410:
		/*
		Model 0: 208//head
Model 1: 250//beard
Model 2: 292//shirt
Model 3: 324//??stripes on shirt
Model 4: 317//cape
Model 5: 170//hands
Model 6: 176//hands
Model 7: 267//legs
Model 8: 185//boots
		*/
		/*var2.anIntArray94 = new int[8];
		var2.anIntArray94[0] = 250;
		var2.anIntArray94[1] = 292;
		var2.anIntArray94[2] = 324;
		var2.anIntArray94[3] = 317;
		var2.anIntArray94[4] = 170;
		var2.anIntArray94[5] = 176;
		var2.anIntArray94[6] = 267;
		var2.anIntArray94[7] = 185;
	  break;*/
	  /*case 172:
		var2.anIntArray94 = new int[8];
		var2.anIntArray94[0] = 208;
		var2.anIntArray94[1] = 181;
		var2.anIntArray94[2] = 250;
		var2.anIntArray94[3] = 292;
		var2.anIntArray94[4] = 170;
		var2.anIntArray94[5] = 176;
		var2.anIntArray94[6] = 265;
		var2.anIntArray94[7] = 14943;
		var2.anIntArray76 = new int[11];
        var2.anIntArray70 = new int[11];
		var2.anIntArray76[0] = 36300;
		var2.anIntArray76[1] = 36292;
		var2.anIntArray76[2] = 57280;
		var2.anIntArray76[3] = 54183;
		var2.anIntArray76[4] = 54503;
		var2.anIntArray76[5] = 960;
		var2.anIntArray76[6] = 22464;
		var2.anIntArray76[7] = 43968;
		//qp cape
		var2.anIntArray76[8] = 8741;
		var2.anIntArray76[9] = 25238;
		var2.anIntArray76[10] = 6798;
		
		var2.anIntArray70[0] = 941;
		var2.anIntArray70[1] = 941;
		var2.anIntArray70[2] = 941;
		var2.anIntArray70[3] = 792;
		var2.anIntArray70[4] = 792;
		var2.anIntArray70[5] = 43113;
		var2.anIntArray70[6] = 43096;
		var2.anIntArray70[7] = 43096;
		//qp cape
		var2.anIntArray70[8] = 43113;
		var2.anIntArray70[9] = 43113;
		var2.anIntArray70[10] = 43096;
		var2.getModelValues = true;
	  break;*/
      case 73:
//    	  System.out.println(var2.anIntArray94[0]+" "+var2.anIntArray94[1]);
//    	var2.anIntArray94 = new int[8];
//  		var2.anIntArray94[0] = 250;
//  		var2.anIntArray94[1] = 292;
//  		var2.anIntArray94[2] = 324;
//  		var2.anIntArray94[3] = 317;
//  		var2.anIntArray94[4] = 170;
//  		var2.anIntArray94[5] = 176;
//  		var2.anIntArray94[6] = 267;
//  		var2.anIntArray94[7] = 185;
    	  var2.getModelValues = true;
//    	  var2.description = (""+var2.anIntArray94[0]+" "+var2.anIntArray94[1])"".getBytes();
    	  break;
      case 945:
         var2.name = Client.NAME+" Guide";
         break;
      case 2258:
         var2.actions = new String[5];
         var2.actions[0] = "Talk-to";
         var2.actions[2] = "Trade";
         var2.actions[3] = "Teleport";
      }

      return var2;
   }
   
   public static void NPCDump() {
		try {
			FileWriter fw = new FileWriter(System.getProperty("user.home")+"/Desktop/Dumps/NPC Dump.txt");
			for(int id = 0; id < totalNPCs; id++) {
				NpcDefinition ed = NpcDefinition.forID(id);
				fw.write("case "+id+":");
				fw.write(System.getProperty("line.separator"));
				fw.write("entityDef.name = \""+ed.name+"\";");
				fw.write(System.getProperty("line.separator"));
				fw.write("entityDef.description = \""+ed.description+"\";");
				fw.write(System.getProperty("line.separator"));
				fw.write("entityDef.combatLevel = "+ed.combatLevel+";");
				fw.write(System.getProperty("line.separator"));
//				fw.write("entityDef.walkAnim = "+ed.walkAnim+";");
//				fw.write(System.getProperty("line.separator"));
//				fw.write("entityDef.standAnim = "+ed.standAnim+";");
//				fw.write(System.getProperty("line.separator"));
				if(ed.actions != null) {
					fw.write("entityDef.actions = new String["+ed.actions.length+"];");
					fw.write(System.getProperty("line.separator"));
					for(int range = 0; range < ed.actions.length; range++) {
						if(ed.actions[range] != null) {
							fw.write("entityDef.actions["+range+"] = \""+ed.actions[range]+"\";");
							fw.write(System.getProperty("line.separator"));
						}
					}
				}
				if(ed.anIntArray94 != null) { 
					fw.write("entityDef.models = new int["+ed.anIntArray94.length+"];");
					fw.write(System.getProperty("line.separator"));
					for(int range = 0; range < ed.anIntArray94.length; range++) {
						fw.write("entityDef.models["+range+"] = "+ed.anIntArray94[range]+";");
						fw.write(System.getProperty("line.separator"));
					}
				}
//				if(ed.originalModelColors != null) {
//					fw.write("entityDef.originalModelColors = new int["+ed.originalModelColors.length+"];");
//					fw.write(System.getProperty("line.separator"));
//					for(int range = 0; range < ed.originalModelColors.length; range++) {
//						fw.write("entityDef.originalModelColors["+range+"] = "+ed.originalModelColors[range]+";");
//						fw.write(System.getProperty("line.separator"));
//					}
//				}
//				if(ed.modifiedModelColors != null) {
//					fw.write("entityDef.modifiedModelColors = new int["+ed.modifiedModelColors.length+"];");
//					fw.write(System.getProperty("line.separator"));
//					for(int range = 0; range < ed.modifiedModelColors.length; range++) {
//						fw.write("entityDef.modifiedModelColors["+range+"] = "+ed.modifiedModelColors[range]+";");
//						fw.write(System.getProperty("line.separator"));
//					}
//				}
				fw.write("entityDef.anInt91 = "+ed.anInt91+";");
				fw.write(System.getProperty("line.separator"));
				fw.write("entityDef.anInt86 = "+ed.anInt86+";");
				fw.write(System.getProperty("line.separator"));
				fw.write("entityDef.aByte68 = "+ed.aByte68+";");
				fw.write(System.getProperty("line.separator"));
				fw.write("break;"); 
				fw.write(System.getProperty("line.separator"));
				fw.write(System.getProperty("line.separator"));
			}
			fw.close();
			System.out.println("NPC Dump Finished.");
			System.out.println("Total NPCs: "+totalNPCs);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

   public Model method160() {
      if(this.configs != null) {
         NpcDefinition var5 = this.method161();
         return var5 == null?null:var5.method160();
      } else if(this.anIntArray73 == null) {
         return null;
      } else {
         boolean flag1 = false;

         for(int aclass30_sub2_sub4_sub6s = 0; aclass30_sub2_sub4_sub6s < this.anIntArray73.length; ++aclass30_sub2_sub4_sub6s) {
            if(!Model.method463(this.anIntArray73[aclass30_sub2_sub4_sub6s])) {
               flag1 = true;
            }
         }

         if(flag1) {
            return null;
         } else {
            Model[] var6 = new Model[this.anIntArray73.length];

            for(int model = 0; model < this.anIntArray73.length; ++model) {
               var6[model] = Model.method462(this.anIntArray73[model]);
            }

            Model var7;
            if(var6.length == 1) {
               var7 = var6[0];
            } else {
               var7 = new Model(var6.length, var6);
            }

            if(this.anIntArray76 != null) {
               for(int k = 0; k < this.anIntArray76.length; ++k) {
                  var7.method476(this.anIntArray76[k], this.anIntArray70[k]);
               }
            }

            return var7;
         }
      }
   }

   public NpcDefinition method161() {
      int j = -1;
      if(this.anInt57 != -1) {
         VarBit varBit = VarBit.cache[this.anInt57];
         int k = varBit.anInt648;
         int l = varBit.anInt649;
         int i1 = varBit.anInt650;
         int j1 = Client.anIntArray1232[i1 - l];
         j = clientInstance.variousSettings[k] >> l & j1;
      } else if(this.anInt59 != -1) {
         j = clientInstance.variousSettings[this.anInt59];
      }

      return j >= 0 && j < this.configs.length && this.configs[j] != -1?forID(this.configs[j]):null;
   }

   public static int totalNPCs;
   
   public static void unpackConfig(StreamLoader streamLoader) {
      stream = new Stream(streamLoader.getDataForName("npc.dat"));
      Stream stream2 = new Stream(streamLoader.getDataForName("npc.idx"));
      totalNPCs = stream2.get_unsigned_short();
      streamIndices = new int[totalNPCs];
      int i = 2;

      int k;
      for(k = 0; k < totalNPCs; ++k) {
         streamIndices[k] = i;
         i += stream2.get_unsigned_short();
      }

      cache = new NpcDefinition[20];

      for(k = 0; k < 20; ++k) {
         cache[k] = new NpcDefinition();
      }

   }

   public static void nullLoader() {
      mruNodes = null;
      streamIndices = null;
      cache = null;
      stream = null;
   }

   public Model method164(int j, int k, int[] ai) {
      if(this.configs != null) {
         NpcDefinition var8 = this.method161();
         return var8 == null?null:var8.method164(j, k, ai);
      } else {
         Model model = (Model)mruNodes.insertFromCache(this.type);
         if(model == null) {
            boolean model_1 = false;

            for(int aclass30_sub2_sub4_sub6s = 0; aclass30_sub2_sub4_sub6s < this.anIntArray94.length; ++aclass30_sub2_sub4_sub6s) {
               if(!Model.method463(this.anIntArray94[aclass30_sub2_sub4_sub6s])) {
                  model_1 = true;
               }
            }

            if(model_1) {
               return null;
            }

            Model[] var10 = new Model[this.anIntArray94.length];

            int k1;
            for(k1 = 0; k1 < this.anIntArray94.length; ++k1) {
               var10[k1] = Model.method462(this.anIntArray94[k1]);
            }

            if(var10.length == 1) {
               model = var10[0];
            } else {
               model = new Model(var10.length, var10);
            }

            if(this.anIntArray76 != null) {
               for(k1 = 0; k1 < this.anIntArray76.length; ++k1) {
                  model.method476(this.anIntArray76[k1], this.anIntArray70[k1]);
               }
            }
			
			if(this.getModelValues){
				this.getModelValues = false;
				System.out.println("Values for npc: "+this.type);
				model.getModelValues();
			}

            model.method469();
            model.method479(64 + this.anInt85, 850 + this.anInt92, -30, -50, -30, true);
            mruNodes.removeFromCache(model, this.type);
         }

         Model var9 = Model.aModel_1621;
         var9.method464(model, Class36.method532(k) & Class36.method532(j));
         if(k != -1 && j != -1) {
            var9.method471(ai, j, k);
         } else if(k != -1) {
            var9.method470(k);
         }

         if(this.anInt91 != 128 || this.anInt86 != 128) {
            var9.method478(this.anInt91, this.anInt91, this.anInt86);
         }

         var9.method466();
         var9.anIntArrayArray1658 = (int[][])null;
         var9.anIntArrayArray1657 = (int[][])null;
         if(this.aByte68 == 1) {
            var9.aBoolean1659 = true;
         }
		 
         return var9;
      }
   }

   private void readValues(Stream stream) {
      while(true) {
         int i = stream.readUnsignedByte();
         if(i == 0) {
            return;
         }

         int i1;
         int i2;
         if(i == 1) {
            i1 = stream.readUnsignedByte();
            this.anIntArray94 = new int[i1];

            for(i2 = 0; i2 < i1; ++i2) {
               this.anIntArray94[i2] = stream.get_unsigned_short();
            }
         } else if(i == 2) {
            this.name = stream.readString();
         } else if(i == 3) {
            this.description = stream.readBytes();
         } else if(i == 12) {
            this.aByte68 = stream.readSignedByte();
         } else if(i == 13) {
            this.anInt77 = stream.get_unsigned_short();
         } else if(i == 14) {
            this.anInt67 = stream.get_unsigned_short();
         } else if(i == 17) {
            this.anInt67 = stream.get_unsigned_short();
            this.anInt58 = stream.get_unsigned_short();
            this.anInt83 = stream.get_unsigned_short();
            this.anInt55 = stream.get_unsigned_short();
         } else if(i >= 30 && i < 40) {
            if(this.actions == null) {
               this.actions = new String[5];
            }

            this.actions[i - 30] = stream.readString();
            if(this.actions[i - 30].equalsIgnoreCase("hidden")) {
               this.actions[i - 30] = null;
            }
         } else if(i == 40) {
            i1 = stream.readUnsignedByte();
            this.anIntArray76 = new int[i1];
            this.anIntArray70 = new int[i1];

            for(i2 = 0; i2 < i1; ++i2) {
               this.anIntArray76[i2] = stream.get_unsigned_short();
               this.anIntArray70[i2] = stream.get_unsigned_short();
            }
         } else if(i == 60) {
            i1 = stream.readUnsignedByte();
            this.anIntArray73 = new int[i1];

            for(i2 = 0; i2 < i1; ++i2) {
               this.anIntArray73[i2] = stream.get_unsigned_short();
            }
         } else if(i == 90) {
            stream.get_unsigned_short();
         } else if(i == 91) {
            stream.get_unsigned_short();
         } else if(i == 92) {
            stream.get_unsigned_short();
         } else if(i == 93) {
            this.map_marker = false;
         } else if(i == 95) {
            this.combatLevel = stream.get_unsigned_short();
         } else if(i == 97) {
            this.anInt91 = stream.get_unsigned_short();
         } else if(i == 98) {
            this.anInt86 = stream.get_unsigned_short();
         } else if(i == 99) {
            this.aBoolean93 = true;
         } else if(i == 100) {
            this.anInt85 = stream.readSignedByte();
         } else if(i == 101) {
            this.anInt92 = stream.readSignedByte() * 5;
         } else if(i == 102) {
            this.headicon = stream.get_unsigned_short();
         } else if(i == 103) {
            this.anInt79 = stream.get_unsigned_short();
         } else if(i == 106) {
            this.anInt57 = stream.get_unsigned_short();
            if(this.anInt57 == '\uffff') {
               this.anInt57 = -1;
            }

            this.anInt59 = stream.get_unsigned_short();
            if(this.anInt59 == '\uffff') {
               this.anInt59 = -1;
            }

            i1 = stream.readUnsignedByte();
            this.configs = new int[i1 + 1];

            for(i2 = 0; i2 <= i1; ++i2) {
               this.configs[i2] = stream.get_unsigned_short();
               if(this.configs[i2] == '\uffff') {
                  this.configs[i2] = -1;
               }
            }
         } else if(i == 107) {
            this.interactive = false;
         }
      }
   }

}
