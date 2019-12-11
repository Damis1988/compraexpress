package blocojava.compraexpress.controller;

import blocojava.compraexpress.repository.ItemRepository;
import blocojava.compraexpress.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping(value = "/")
    public String viewOrder(Map<String, Object> model){
        //receive order through model and generate invoice to be validated or edited by consumer
        return null;
    }
}
