package client;


import com.cache.ItemDefinition;

public final class Widget {

   public static final int TYPE_CONTAINER = 0;
   public static final int TYPE_MODEL_LIST = 1;//empty
   public static final int TYPE_INVENTORY = 2;
   public static final int TYPE_RECTANGLE = 3;
   public static final int TYPE_TEXT = 4;
   public static final int TYPE_SPRITE = 5;
   public static final int TYPE_MODEL = 6;
   public static final int TYPE_ITEM_LIST = 7;
   public static final int TYPE_ITEM_DISPLAY = 8;

   public static final int OPTION_OK = 1;
   public static final int OPTION_USABLE = 2;
   public static final int OPTION_CLOSE = 3;
   public static final int OPTION_TOGGLE_SETTING = 4;
   public static final int OPTION_RESET_SETTING = 5;
   public static final int OPTION_CONTINUE = 6;
   public static final int OPTION_DROPDOWN = 9;
   public static final int OPTION_MENUITEM = 10;

   public Sprite sprite1;
   public Sprite hoverSprite1;
   public Sprite hoverSprite2;
   public int anInt208;
   public int originalWidth;
   public Sprite[] sprites;
   public static Widget[] cache;
   public int[] anIntArray212;
   public int anInt214;
   public int[] spritesX;
   public int defaultHoverColor;
   public int optionType;
   public String spellName;
   public int secondaryColor;
   public int width;
   public String tooltip;
   public String selectedActionName;
   public boolean aBoolean223;
   public int scroll_pos;
   public String[] actions;
   public int[][] valueIndexArray;
   public boolean draw2DPixels;
   public String aString228;
   public int anInt230;
   public int invSpritePadX;
   public int textColor;
   public int anInt233;
   public int mediaID;
   public boolean aBoolean235;
   public int parentID;
   public int spellUsableOn;
   private static MRUNodes aMRUNodes_238;
   public int secondaryHoverColor;
   public int[] children;
   public int[] childX;
   public boolean usableItemInterface;
   public TextRasterizer2D text_type;
   public int invSpritePadY;
   public int[] anIntArray245;
   public int anInt246;
   public int[] spritesY;
   public String widget_string;
   public boolean isInventoryInterface;
   public int id;
   public int[] item_supply;
   public int[] item_ids;
   public byte opacity;
   private int anInt255;
   private int anInt256;
   public int anInt257;
   public int anInt258;
   public boolean aBoolean259;
   public Sprite sprite2;
   public int scrollMax;
   public int type;
   public int anInt263;
   private static final MRUNodes aMRUNodes_264 = new MRUNodes(30);
   public int anInt265;
   public boolean aBoolean266;
   public int height;
   public boolean aBoolean268;
   public int anInt269;
   public int anInt270;
   public int anInt271;
   public int[] childY;


   public void swapInventoryItems(int i, int j) {
      int k = this.item_ids[i];
      this.item_ids[i] = this.item_ids[j];
      this.item_ids[j] = k;
      k = this.item_supply[i];
      this.item_supply[i] = this.item_supply[j];
      this.item_supply[j] = k;
   }
   
   public static void unpack(StreamLoader streamLoader, TextRasterizer2D[] textDrawingAreas, StreamLoader streamLoader_1) {
      aMRUNodes_238 = new MRUNodes('\uc350');
      Stream stream = new Stream(streamLoader.getDataForName("data"));
      //Stream stream = new Stream(FileOperations.ReadFile(SignLink.findcachedir() + "interfaces2.dat"));
      int i = -1;
      int j = stream.readUnsignedWord();
      cache = new Widget[j];

      while(stream.currentOffset < stream.buffer.length) {
         int k = stream.readUnsignedWord();
         if(k == '\uffff') {
            i = stream.readUnsignedWord();
            k = stream.readUnsignedWord();
         }

         Widget widget = cache[k] = new Widget();
         widget.id = k;
         widget.parentID = i;
         widget.type = stream.readUnsignedByte();
         widget.optionType = stream.readUnsignedByte();
         widget.anInt214 = stream.readUnsignedWord();
         int width = stream.readUnsignedWord();
         widget.width = width;
         if(widget.type == 3)
        	 widget.originalWidth = width;
         widget.height = stream.readUnsignedWord();
         widget.opacity = (byte)stream.readUnsignedByte();
         widget.anInt230 = stream.readUnsignedByte();
         if(widget.anInt230 != 0) {
            widget.anInt230 = (widget.anInt230 - 1 << 8) + stream.readUnsignedByte();
         } else {
            widget.anInt230 = -1;
         }

         int i1 = stream.readUnsignedByte();
         int k1;
         if(i1 > 0) {
            widget.anIntArray245 = new int[i1];
            widget.anIntArray212 = new int[i1];

            for(k1 = 0; k1 < i1; ++k1) {
               widget.anIntArray245[k1] = stream.readUnsignedByte();
               widget.anIntArray212[k1] = stream.readUnsignedWord();
            }
         }

         k1 = stream.readUnsignedByte();
         int l2;
         int k4;
         if(k1 > 0) {
            widget.valueIndexArray = new int[k1][];

            for(l2 = 0; l2 < k1; ++l2) {
               k4 = stream.readUnsignedWord();
               widget.valueIndexArray[l2] = new int[k4];

               for(int s1 = 0; s1 < k4; ++s1) {
                  widget.valueIndexArray[l2][s1] = stream.readUnsignedWord();
               }
            }
         }

         if(widget.type == 0) {
            widget.scrollMax = stream.readUnsignedWord();
            widget.aBoolean266 = stream.readUnsignedByte() == 1;
            l2 = stream.readUnsignedWord();
            widget.children = new int[l2];
            widget.childX = new int[l2];
            widget.childY = new int[l2];

            for(k4 = 0; k4 < l2; ++k4) {
               widget.children[k4] = stream.readUnsignedWord();
               widget.childX[k4] = stream.readSignedWord();
               widget.childY[k4] = stream.readSignedWord();
            }
         }

         if(widget.type == 1) {
            stream.readUnsignedWord();
            stream.readUnsignedByte();
         }

         if(widget.type == 2) {
            widget.item_ids = new int[widget.width * widget.height];
            widget.item_supply = new int[widget.width * widget.height];
            widget.aBoolean259 = stream.readUnsignedByte() == 1;
            widget.isInventoryInterface = stream.readUnsignedByte() == 1;
            widget.usableItemInterface = stream.readUnsignedByte() == 1;
            widget.aBoolean235 = stream.readUnsignedByte() == 1;
            widget.invSpritePadX = stream.readUnsignedByte();
            widget.invSpritePadY = stream.readUnsignedByte();
            widget.spritesX = new int[20];
            widget.spritesY = new int[20];
            widget.sprites = new Sprite[20];

            for(l2 = 0; l2 < 20; ++l2) {
               k4 = stream.readUnsignedByte();
               if(k4 == 1) {
                  widget.spritesX[l2] = stream.readSignedWord();
                  widget.spritesY[l2] = stream.readSignedWord();
                  String var15 = stream.readString();
                  if(streamLoader_1 != null && var15.length() > 0) {
                     int i5 = var15.lastIndexOf(",");
                     if(var15.substring(0, i5).toLowerCase().equals("cache")){
                    	 int id = Integer.parseInt(var15.substring(i5 + 1));
                    	 widget.sprites[l2] = Client.cacheSpriteI[id];
                     }else
                    	 widget.sprites[l2] = method207(Integer.parseInt(var15.substring(i5 + 1)), streamLoader_1, var15.substring(0, i5));
                  }
               }
            }

            widget.actions = new String[5];

            for(l2 = 0; l2 < 5; ++l2) {
               widget.actions[l2] = stream.readString();
               if(widget.actions[l2].length() == 0) {
                  widget.actions[l2] = null;
               }
				if(widget.parentID == 3824)
					widget.actions[4] = "Buy 100";
				if(widget.parentID == 3822)
					widget.actions[4] = "Sell 100";
				if(widget.parentID == 1644)
					widget.actions[2] = "Operate";
            }
         }

         if(widget.type == 3) {
            widget.draw2DPixels = stream.readUnsignedByte() == 1;
         }

         if(widget.type == 4 || widget.type == 1) {
            widget.aBoolean223 = stream.readUnsignedByte() == 1;
            l2 = stream.readUnsignedByte();
            if(textDrawingAreas != null) {
               widget.text_type = textDrawingAreas[l2];
            }

            widget.aBoolean268 = stream.readUnsignedByte() == 1;
         }

         if(widget.type == 4) {
            widget.widget_string = stream.readString().replaceAll("RuneScape", Client.NAME).replaceAll("Runescape", Client.NAME).replaceAll("runescape", Client.NAME).replaceAll("Jagex", Client.NAME).replaceAll("jagex", Client.NAME);
            widget.aString228 = stream.readString();
         }

         if(widget.type == 1 || widget.type == 3 || widget.type == 4) {
            widget.textColor = stream.readDWord();
         }

         if(widget.type == 3 || widget.type == 4) {
            widget.secondaryColor = stream.readDWord();
            widget.defaultHoverColor = stream.readDWord();
            widget.secondaryHoverColor = stream.readDWord();
         }

         if(widget.type == 5 || widget.type == 17) {
            String var14 = stream.readString();
            if(streamLoader_1 != null && var14.length() > 0) {
               k4 = var14.lastIndexOf(",");
               if(var14.substring(0, k4).toLowerCase().equals("cache")){
            	   int id = Integer.parseInt(var14.substring(k4 + 1));
            	   /*if(id == 7)
            		   widget.hoverSprite1 = Client.cacheSpriteI[8];*/
            	   widget.sprite1 = Client.cacheSpriteI[id];
               }else
            	   widget.sprite1 = method207(Integer.parseInt(var14.substring(k4 + 1)), streamLoader_1, var14.substring(0, k4));
            }

            var14 = stream.readString();
            if(streamLoader_1 != null && var14.length() > 0) {
               k4 = var14.lastIndexOf(",");
               if(var14.substring(0, k4).toLowerCase().equals("cache")){
            	   int id = Integer.parseInt(var14.substring(k4 + 1));
            	   widget.sprite2 = Client.cacheSpriteI[id];
               }else
            	   widget.sprite2 = method207(Integer.parseInt(var14.substring(k4 + 1)), streamLoader_1, var14.substring(0, k4));
            }
            
            if(widget.type == 17) {//hovers
            	var14 = stream.readString();
                if(streamLoader_1 != null && var14.length() > 0) {
                   k4 = var14.lastIndexOf(",");
                   if(var14.substring(0, k4).toLowerCase().equals("cache")){
                	   int id = Integer.parseInt(var14.substring(k4 + 1));
                	   widget.hoverSprite1 = Client.cacheSpriteI[id];
                   }else
                	   widget.hoverSprite1 = method207(Integer.parseInt(var14.substring(k4 + 1)), streamLoader_1, var14.substring(0, k4));
                }
                
                var14 = stream.readString();
                if(streamLoader_1 != null && var14.length() > 0) {
                   k4 = var14.lastIndexOf(",");
                   if(var14.substring(0, k4).toLowerCase().equals("cache")){
                	   int id = Integer.parseInt(var14.substring(k4 + 1));
                	   widget.hoverSprite2 = Client.cacheSpriteI[id];
                   }else
                	   widget.hoverSprite2 = method207(Integer.parseInt(var14.substring(k4 + 1)), streamLoader_1, var14.substring(0, k4));
                }
            }
            
         }

         if(widget.type == 6) {
            l2 = stream.readUnsignedByte();
            if(l2 != 0) {
               widget.anInt233 = 1;
               widget.mediaID = (l2 - 1 << 8) + stream.readUnsignedByte();
            }

            l2 = stream.readUnsignedByte();
            if(l2 != 0) {
               widget.anInt255 = 1;
               widget.anInt256 = (l2 - 1 << 8) + stream.readUnsignedByte();
            }

            l2 = stream.readUnsignedByte();
            if(l2 != 0) {
               widget.anInt257 = (l2 - 1 << 8) + stream.readUnsignedByte();
            } else {
               widget.anInt257 = -1;
            }

            l2 = stream.readUnsignedByte();
            if(l2 != 0) {
               widget.anInt258 = (l2 - 1 << 8) + stream.readUnsignedByte();
            } else {
               widget.anInt258 = -1;
            }

            widget.anInt269 = stream.readUnsignedWord();
            widget.anInt270 = stream.readUnsignedWord();
            widget.anInt271 = stream.readUnsignedWord();
         }

         if(widget.type == 7) {
            widget.item_ids = new int[widget.width * widget.height];
            widget.item_supply = new int[widget.width * widget.height];
            widget.aBoolean223 = stream.readUnsignedByte() == 1;
            l2 = stream.readUnsignedByte();
            if(textDrawingAreas != null) {
               widget.text_type = textDrawingAreas[l2];
            }

            widget.aBoolean268 = stream.readUnsignedByte() == 1;
            widget.textColor = stream.readDWord();
            widget.invSpritePadX = stream.readSignedWord();
            widget.invSpritePadY = stream.readSignedWord();
            widget.isInventoryInterface = stream.readUnsignedByte() == 1;
            widget.actions = new String[5];

            for(k4 = 0; k4 < 5; ++k4) {
               widget.actions[k4] = stream.readString();
               if(widget.actions[k4].length() == 0) {
                  widget.actions[k4] = null;
               }
            }
         }

         if(widget.optionType == 2 || widget.type == 2) {
            widget.selectedActionName = stream.readString();
            widget.spellName = stream.readString();
            widget.spellUsableOn = stream.readUnsignedWord();
         }

         if(widget.type == 8) {
            widget.widget_string = stream.readString();
         }

         if(widget.optionType == 1 || widget.optionType == 4 || widget.optionType == 5 || widget.optionType == 6) {
            widget.tooltip = stream.readString();
            if(widget.tooltip.length() == 0) {
               if(widget.optionType == 1) {
                  widget.tooltip = "Ok";
               }

               if(widget.optionType == 4) {
                  widget.tooltip = "Select";
               }

               if(widget.optionType == 5) {
                  widget.tooltip = "Select";
               }

               if(widget.optionType == 6) {
                  widget.tooltip = "Continue";
               }
            }
         }
      }

      aMRUNodes_238 = null;
   }

   private Model method206(int i, int j) {
      Model model = (Model)aMRUNodes_264.insertFromCache((long)((i << 16) + j));
      if(model != null) {
         return model;
      } else {
         if(i == 1) {
            model = Model.method462(j);
         }

         if(i == 2) {
            model = EntityDef.forID(j).method160();
         }

         if(i == 3) {
            model = Client.local_player.method453();
         }

         if(i == 4) {
            model = ItemDefinition.get(j).method202(50);
         }

         if(i == 5) {
            model = null;
         }

         if(model != null) {
            aMRUNodes_264.removeFromCache(model, (long)((i << 16) + j));
         }

         return model;
      }
   }

   private static Sprite method207(int i, StreamLoader streamLoader, String s) {
      long l = (TextClass.method585(s) << 8) + (long)i;
      Sprite sprite = (Sprite)aMRUNodes_238.insertFromCache(l);
      if(sprite != null) {
         return sprite;
      } else {
         try {
            sprite = new Sprite(streamLoader, s, i);
            aMRUNodes_238.removeFromCache(sprite, l);
            return sprite;
         } catch (Exception var7) {
            return null;
         }
      }
   }

   public static void method208(boolean flag, Model model) {
      byte i = 0;
      byte j = 5;
      if(!flag) {
         aMRUNodes_264.unlinkAll();
         if(model != null && j != 4) {
            aMRUNodes_264.removeFromCache(model, (long)((j << 16) + i));
         }

      }
   }

   public Model method209(int j, int k, boolean flag) {
      Model model;
      if(flag) {
         model = this.method206(this.anInt255, this.anInt256);
      } else {
         model = this.method206(this.anInt233, this.mediaID);
      }

      if(model == null) {
         return null;
      } else if(k == -1 && j == -1 && model.anIntArray1640 == null) {
         return model;
      } else {
         Model model_1 = new Model(true, Class36.method532(k) & Class36.method532(j), false, model);
         if(k != -1 || j != -1) {
            model_1.method469();
         }

         if(k != -1) {
            model_1.method470(k);
         }

         if(j != -1) {
            model_1.method470(j);
         }

         model_1.method479(64, 768, -50, -10, -50, true);
         return model_1;
      }
   }

}
