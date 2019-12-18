package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import blocojava.compraexpress.model.*;
import blocojava.compraexpress.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    SectorRepository sectorRepository;

    @GetMapping(value = "/view")
    public String viewOrder(Map<String, Object> model){

        ArrayList<Item> cart = (ArrayList<Item>) customerSession.getCart();
        model.put("cart", cart);
        return "cart/view";
    }

    @PostMapping(value = "addItem")
    public String buy(@RequestParam("restaurant") Long id_restaurant,@RequestParam("product") Long id,@RequestParam("qty") Integer qty, HttpSession session,Map<String, Object> model ) {
        Product productModel = productRepository.findOne(id);

        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if (cart == null || cart.size() == 0) {
            cart = new ArrayList<Item>();
            Item item = new Item();
            item.setProduct(productModel);
            item.setQuantity(qty);
            cart.add(item);
            session.setAttribute("cart", cart);
        } else {
            int index = exists(id,cart);
            if (index == -1) {
                Item item = new Item();
                item.setProduct(productModel);
                item.setQuantity(qty);
                cart.add(item);
            } else {
                int quantity = cart.get(index).getQuantity() + qty;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }

        customerSession.setCart(cart);
        model.put("cart", cart);

        Restaurant restaurant = restaurantRepository.findOne(id_restaurant);
        model.put("restaurant", restaurant);
        Menu menu = menuRepository.findMenuByRestaurant_Id(id_restaurant);
        Iterable<Sector> sectors = sectorRepository.findByMenu_Id(menu.getId());
        model.put("sectors", sectors);
        Iterable<Product> products = productRepository.findByMenu_Id(menu.getId());
        model.put("products", products);

        return "restaurant/menu";
    }

    private int exists(Long id, List<Item> items) {
        for (Item item : items) {
            if (item.getId() != null && item.getId().equals(id)){
                return items.indexOf(item);
            }
        }
        return -1;
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String remove(@RequestParam("id") Long id, HttpSession session) {
        Product productModel = new Product();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = this.exists(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "resraurant/menu";
    }

}
