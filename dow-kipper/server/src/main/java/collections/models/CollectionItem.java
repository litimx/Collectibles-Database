package collections.models;

import java.math.BigDecimal;

public class CollectionItem {
    private Collection collection;
    private Item item;

    private boolean isSold;

    private BigDecimal listedPrice;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public BigDecimal getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(BigDecimal listedPrice) {
        this.listedPrice = listedPrice;
    }

    @Override
    public String toString() {
        return "CollectionItem{" +
                "collection=" + collection +
                ", item=" + item +
                ", isSold=" + isSold +
                ", listedPrice=" + listedPrice +
                '}';
    }
}
