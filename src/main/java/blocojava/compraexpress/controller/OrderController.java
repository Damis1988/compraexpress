package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import blocojava.compraexpress.model.Item;
import blocojava.compraexpress.model.Product;
import blocojava.compraexpress.model.Restaurant;
import blocojava.compraexpress.repository.ItemRepository;
import blocojava.compraexpress.repository.OrderRepository;
import blocojava.compraexpress.repository.ProductRepository;
import blocojava.compraexpress.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    CustomerSession customerSession;

    @GetMapping(value = "/view")
    public String viewOrder(Map<String, Object> model){
        //TODO receive order through model and generate invoice to be validated or edited by consumer
        return null;
    }

    @PostMapping(value = "addItem")
    public String addItem(@RequestParam("restaurant") Long id_restaurant,
                          @RequestParam("product") Long id_product,
                          @RequestParam("qty") Integer qty,
                          Map<String, Object> model){

        ArrayList<Item> items = (ArrayList) customerSession.getCart();
        Product product = productRepository.findOne(id_product);

        for (Item i : items) {
            if (i.getProduct().equals(product)) {
                if (qty > 0) {
                    i.setQuantity(qty);
                    items.set(items.indexOf(i), i);
                } else if (qty == 0) {
                    items.remove(i);
                }
                customerSession.setCart(items);
                model.put("cart", items);
                Restaurant restaurant = restaurantRepository.findOne(id_restaurant);
                model.put("restaurant", restaurant);
                return "restaurant/menu";
            }
        }

        Item item = new Item(qty, product);
        items.add(item);
        customerSession.setCart(items);
        model.put("cart", items);
        Restaurant restaurant = restaurantRepository.findOne(id_restaurant);
        model.put("restaurant", restaurant);
        return "restaurant/menu";
    }
}
