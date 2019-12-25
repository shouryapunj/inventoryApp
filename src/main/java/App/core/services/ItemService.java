package App.core.services;

import App.core.configs.ItemConfig;
import App.core.dto.Item;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class ItemService {

    @Autowired
    private HashMap<Integer, Item> itemMap;

    @Autowired
    private HashMap<Integer, Double> profitHistoryMap;

    @Autowired
    private ItemConfig itemConfig;

    private double profit;

    private int reportNumber = 1;

    public void createItem(String iName, double cPrice, double sPrice) {
        Item item = new Item();
        item.setItemName(iName);
        item.setCostPrice(cPrice);
        item .setSellingPrice(sPrice);
        item.setQuantity(0);
        itemMap.put(item.getItemNumber(), item);
        System.out.println("InventoryApp# Added new item = " + item.toString());
    }

    public void removeItem(String iName) {
        int iNo =  itemConfig.getItemNumberFromItemName(iName);
        if (iNo == -1) return;
        double cPrice = itemMap.get(iNo).getCostPrice();
        profit += cPrice * -1 * itemMap.get(iNo).getQuantity();
        itemMap.remove(iNo);
        System.out.println("InventoryApp# Removed item = " + iName);
    }

    public void updateBuy(String iName, int qty) {
        int iNo = itemConfig.getItemNumberFromItemName(iName);
        int oldQty = itemMap.get(iNo).getQuantity();
        itemMap.get(iNo).setQuantity(oldQty + qty);
        System.out.println("InventoryApp# Bought item = " + iName + ", quantity = " + qty);
    }

    public void updateSell(String iName, int qty) {
        int iNo = itemConfig.getItemNumberFromItemName(iName);
        int oldQty = itemMap.get(iNo).getQuantity();
        profitCalculator(iNo, qty);
        itemMap.get(iNo).setQuantity(oldQty - qty);
        System.out.println("InventoryApp# Sold item = " + iName + ", quantity = " + qty);
    }

    public void  updateItemSellingPrice(String iName, double sPrice) {
        int iNo = itemConfig.getItemNumberFromItemName(iName);
        Item item = itemMap.get(iNo);
        item.setSellingPrice(sPrice);
        System.out.println("InventoryApp# Updated selling price of item = " + iName + ", selling price = " + sPrice);
    }

    public void report() {
        System.out.println("");
        System.out.println("--------INVENTORY REPORT----------");
        System.out.println("Item Name\t\tBought At\t\t\tSold At\t\tAvailable Qty\t\tValue");
        System.out.println("---------------------------------------------------------------------------------------");
        SortedMap<String, Item> opMap = new TreeMap<>();
        for (Integer i : itemMap.keySet()) {
             opMap.put(itemMap.get(i).getItemName(),itemMap.get(i));
        }
        double totalValue = 0;
        for (String s : opMap.keySet()) {
            System.out.println(s+ "\t\t\t\t" + DoubleRounder.round(opMap.get(s).getCostPrice(),2) + "\t\t\t " + DoubleRounder.round(opMap.get(s).getSellingPrice(),2) + "\t\t\t" + opMap.get(s).getQuantity() + "\t\t\t   " + DoubleRounder.round(opMap.get(s).getCostPrice()*opMap.get(s).getQuantity(),2));
            totalValue += DoubleRounder.round(opMap.get(s).getCostPrice()*opMap.get(s).getQuantity(),2);
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Total value\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + DoubleRounder.round(totalValue,2));
        System.out.println("Profit since previous report\t\t\t\t\t\t\t\t\t\t" + DoubleRounder.round(profit,2));
        System.out.println("");
        profitHistoryMap.put(reportNumber,profit);
        reportNumber++;
        profit = 0;
    }

    public void profitCalculator(int iNo, int qty) {
        double cPrice = itemMap.get(iNo).getCostPrice();
        double sPrice = itemMap.get(iNo).getSellingPrice();
        double p = (sPrice - cPrice) * qty;
        profit += p;

    }
}
