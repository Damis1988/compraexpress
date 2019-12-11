package blocojava.compraexpress.controller;

import blocojava.compraexpress.model.Restaurant;
import blocojava.compraexpress.repository.RestaurantRepository;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "restaurant")
public class MenuController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping(value = "listRestaurant")
    public String list(Map<String, Object> model){
        Iterable<Restaurant> all = restaurantRepository.findAll();
        model.put("all", all);
        return "restaurant/list";
    }

    @GetMapping(value = "menu")
    public String view(@RequestParam("option") String option, Map<String,Object> model){
        Restaurant restaurant = restaurantRepository.findByTradeName(option);
        model.put("restaurant", restaurant);
        return "restaurant/menu";
    }

    @GetMapping(value = "menu/{id}")
    public String view(@PathVariable("id") Long id, Map<String,Object> model){
        Restaurant restaurant = restaurantRepository.findOne(id);
        model.put("restaurant", restaurant);
        return "restaurant/menu";
    }
}
