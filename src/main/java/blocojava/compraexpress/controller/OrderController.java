package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import blocojava.compraexpress.model.*;
import blocojava.compraexpress.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("secure/order")
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

    // manages request for order preview page
    @GetMapping(value = "viewOrder")
    public String viewOrder(Map<String, Object> model){
        ArrayList<Item> cart = (ArrayList<Item>) customerSession.getCart();
        model.put("cart", cart);
        return "cart/view";
    }

    // adds, update or remove an item from the cart
    @PostMapping(value = "addItem")
    public String manageItems(@RequestParam("restaurant") Long id_restaurant, @RequestParam("product") Long id_product, @RequestParam("qty") Integer qty, HttpSession session,Map<String, Object> model ) {
        Product productModel = productRepository.findOne(id_product);

        ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<Item>();
        }

        int index = exists(id_product, cart);
        if (index == -1) {
            if (qty > 0) {
                Item item = new Item();
                item.setProduct(productModel);
                item.setQuantity(qty);
                cart.add(item);
            }
        } else {
            if (qty > 0) {
                cart.get(index).setQuantity(qty);
            } else {
                cart.remove(index);
            }
        }
        session.setAttribute("cart", cart);

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

    // checks if item already in the cart
    private int exists(Long id, List<Item> items) {
        for (Item item : items) {
            if (item.getProduct().getId().equals(id)){
                return items.indexOf(item);
            }
        }
        return -1;
    }

    // finalize purchase
    @GetMapping(value = "finalize")
    public String finalize(Map<String, Object> model){

        Order order = new Order();
        orderRepository.save(order);
        for (Item item : customerSession.getCart()) {
            item.setOrder(order);
            itemRepository.save(item);
        }
        Customer customer = customerSession.getLoggedUser();
        if (!customerSession.getGuest()) {
            customer = customerRepository.findOne(customerSession.getLoggedUser().getId());
            order.setCustomer(customer);
        }
        orderRepository.save(order);
        customerSession.setCart(new ArrayList<>());

        Random random = new Random();
        int orderNumber = random.nextInt(850) + 1;
        model.put("number", orderNumber);

        int minutes = random.nextInt(30) + 15;
        model.put("minutes", minutes);

        return "cart/finalized";
    }
}
