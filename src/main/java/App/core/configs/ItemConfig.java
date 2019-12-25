package App.core.configs;

import App.core.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ItemConfig {

    @Autowired
    private HashMap<Integer, Item> itemMap = new HashMap<>();

    @Autowired
    private HashMap<Integer, Double> profitHistoryMap = new HashMap<>();

    @Bean
    public HashMap<Integer, Item> getItemMap() { return itemMap; }

    @Bean
    public HashMap<Integer, Double> getProfitHistoryMap() { return profitHistoryMap; }

    public Item getItem(int itemNumber) {
        return itemMap.get(itemNumber);
    }

    public int getItemNumberFromItemName(String itemName) {
        for (Integer i : itemMap.keySet()) {
            if (itemMap.get(i).getItemName().equals(itemName)) return i;
        }
        return -1;
    }

}
