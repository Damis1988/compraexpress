package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import blocojava.compraexpress.model.*;
import blocojava.compraexpress.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    public String addItem(@RequestParam("restaurant") Long id_restaurant,
                          @RequestParam("product") Long id_product,
                          @RequestParam("qty") Integer qty,
                          Map<String, Object> model){

        // TODO impedir de adicionar itens de outros restaurantes

        ArrayList<Item> cart = (ArrayList<Item>) customerSession.getCart();
        if (cart == null) {
            cart = new ArrayList<>();
        }
        if (qty > 0) {
            Product product = productRepository.findOne(id_product);
            Item item = new Item(qty, product);
            cart.add(item);
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

    @PostMapping(value = "updateItemQty")
    public String update(@RequestParam("restaurant") Long id_restaurant,
                             @RequestParam("product") Long id_product,
                             @RequestParam("qty") Integer qty,
                             Map<String, Object> model){

        ArrayList<Item> cart = (ArrayList<Item>) customerSession.getCart();
        Product product = productRepository.findOne(id_product);
        cart = updateqty(cart, product, qty);

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

    public ArrayList<Item> updateqty(ArrayList<Item> cart, Product product, Integer newQty){

        for (Item i : cart) {
            if (i.getProduct().getId().equals(product.getId())) {
                if (newQty > 0) {
                    i.setQuantity(newQty);
                    cart.set(cart.indexOf(i), i);
                } else {
                    cart.remove(i);
                }
                return cart;
            }
        }
        return cart;
    }

    @GetMapping(value = "finalize")
    public String finalize(Map<String, Object> model){

        Order order = new Order();
        order.setItems(customerSession.getCart());
        if (!customerSession.getGuest()) {
            Customer customer = customerSession.getLoggedUser();
            order.setCustomer(customer);
            orderRepository.save(order);

            ArrayList<Order> orders = (ArrayList<Order>) customer.getOrder();
            orders.add(order);
            customer.setOrder(orders);
            customerRepository.save(customer);
        }

        ArrayList<Item> newCart = new ArrayList<>();
        customerSession.setCart(newCart);
        //model.put("order", order);

        Random random = new Random();
        int orderNumber = random.nextInt(850) + 1;
        model.put("number", orderNumber);

        int minutes = random.nextInt(30) + 15;
        model.put("minutes", minutes);

        return "cart/finalized";
    }
}
