package App.core.dto;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {

    private static final AtomicInteger atmInt = new AtomicInteger(0);

    private int itemNumber;

    private String itemName;

    private double costPrice;

    private double sellingPrice;

    private int quantity;

    public Item() {
        this.itemNumber = atmInt.incrementAndGet();
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public Item(String itemName, double costPrice, double sellingPrice, int quantity) {
        this.itemNumber = atmInt.incrementAndGet();
        this.itemName = itemName;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "itemNumber=" + itemNumber +
                ", itemName='" + itemName + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", quantity=" + quantity +
                '}';
    }
}
