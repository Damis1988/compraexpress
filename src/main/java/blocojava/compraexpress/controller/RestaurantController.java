package blocojava.compraexpress.controller;

import blocojava.compraexpress.interceptor.CustomerSession;
import blocojava.compraexpress.model.*;
import blocojava.compraexpress.repository.MenuRepository;
import blocojava.compraexpress.repository.ProductRepository;
import blocojava.compraexpress.repository.RestaurantRepository;
import blocojava.compraexpress.repository.SectorRepository;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "restaurant")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    CustomerSession customerSession;

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    SectorRepository sectorRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "listRestaurant")
    public String list(Map<String, Object> model){
        Iterable<Restaurant> all = restaurantRepository.findAll();
        Boolean guest = customerSession.getGuest();
        model.put("all", all);
        model.put("guest", guest);

        // mocking restaurant
        /*{
            Restaurant restaurant = new Restaurant();
            restaurant.setLegalName("Birosca da Maria");
            restaurant.setTradeName("McDonald's");
            restaurantRepository.save(restaurant);

            Menu menu = new Menu();
            restaurant.setMenu(menu);
            menu.setRestaurant(restaurant);
            menuRepository.save(menu);

            Sector sector1 = new Sector();
            sector1.setName("Bebidas");
            sector1.setMenu(menu);
            sectorRepository.save(sector1);

            Product product1 = new Product();
            product1.setName("Coca-Cola");
            product1.setPrice(5.5);
            product1.setSector(sector1);
            product1.setMenu(menu);
            productRepository.save(product1);

            Product product2 = new Product();
            product2.setName("Chai Latte");
            product2.setPrice(7.9);
            product2.setSector(sector1);
            product2.setMenu(menu);
            productRepository.save(product2);

            Sector sector2 = new Sector();
            sector2.setName("Hambúrguer");
            sector2.setMenu(menu);
            sectorRepository.save(sector2);

            Product product3 = new Product();
            product3.setName("CBO");
            product3.setPrice(19.9);
            product3.setSector(sector2);
            product3.setMenu(menu);
            productRepository.save(product3);

            Product product4 = new Product();
            product4.setName("Big Mac");
            product4.setPrice(17.90);
            product4.setSector(sector2);
            product4.setMenu(menu);
            productRepository.save(product4);
        }*/

        return "restaurant/list";
    }



    @PostMapping(value = "menu")
    public String view(@RequestParam("id") Long id,
                       Map<String,Object> model){
        Restaurant restaurant = restaurantRepository.findOne(id);
        model.put("restaurant", restaurant);

        Menu menu = menuRepository.findMenuByRestaurant_Id(restaurant.getId());
        Iterable<Sector> sectors = sectorRepository.findByMenu_Id(menu.getId());
        model.put("sectors", sectors);
        Iterable<Product> products = productRepository.findByMenu_Id(menu.getId());
        model.put("products", products);

        List<Item> items = customerSession.getCart();
        model.put("cart", items);
        return "restaurant/menu";
    }
}
