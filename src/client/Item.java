package client;


final class Item extends Animable {

   public int ID;
   public int x;
   public int y;
   public int anInt1559;


   public final Model getRotatedModel() {
      ItemDef itemDef = ItemDef.forID(this.ID);
      return itemDef.method201(this.anInt1559);
   }

}
