package App.core.controllers;

import App.core.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET, value = "/report")
    public void generateReport() {
        itemService.report();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createItem/{itemName}/{costPrice}/{sellingPrice}")
    public void createItem(@PathVariable("itemName") String iName, @PathVariable("costPrice") double cPrice, @PathVariable("sellingPrice") double sPrice) {
        itemService.createItem(iName, cPrice, sPrice);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/removeItem/{itemName}")
    public void removeItem(@PathVariable("itemName") String iName ) {
        itemService.removeItem(iName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateBuy/{itemName}/{quantity}")
    public void updateBuy(@PathVariable("itemName") String iName, @PathVariable("quantity") int qty) {
        itemService.updateBuy(iName, qty);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateSell/{itemName}/{quantity}")
    public void updateSell(@PathVariable("itemName") String iName, @PathVariable("quantity") int qty) {
        itemService.updateSell(iName, qty);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateItem/{itemName}/{sellingPrice}")
    public void updateItemSellingPrice(@PathVariable("itemName") String iName, @PathVariable("sellingPrice") double sPrice) {
        itemService.updateItemSellingPrice(iName, sPrice);
    }
}
