package client;


import com.cache.ItemDefinition;

final class Item extends Animable {

    public int id;
    public int x;
    public int y;
    public int quantity;


    public final Model getRotatedModel() {
        ItemDefinition itemDef = ItemDefinition.get(this.id);
        return itemDef.method201(this.quantity);
    }

}
