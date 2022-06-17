package ui;

import domain.models.Good;
import domain.models.Order;
import domain.utils.CustomerUtils;
import domain.utils.GoodUtils;
import domain.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private CustomerUtils customerUtils;
    @Autowired
    private OrderUtils orderUtils;
    @Autowired
    private GoodUtils goodUtils;

    @GetMapping("/manage-cart")
    public String manageCartPage(@RequestParam("customer_id") int customer_id,
                                 @RequestParam("order_id") int order_id,
                                 Model model) {
        model.addAttribute("customer", customerUtils.getById(customer_id));
        model.addAttribute("order", orderUtils.getById(order_id));
        model.addAttribute("goods", goodUtils.getAll());
        return "/order-pages/manage-cart";
    }

    @PostMapping("/clear-cart")
    public String clearCart(@RequestParam("customer_id") int customer_id,
                            @RequestParam("order_id") int order_id,
                            Model model) {
        Order order = orderUtils.getById(order_id);
        orderUtils.clearCartOrder(order);
        model.addAttribute("customer", customerUtils.getById(customer_id));
        model.addAttribute("order", order);
        model.addAttribute("goods", goodUtils.getAll());
        return "/order-pages/manage-cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("customer_id") int customer_id,
                            @RequestParam("order_id") int order_id,
                            @RequestParam("good") String goodName,
                            @RequestParam("amount") int amount,
                            Model model) {
        try {
            Good good = goodUtils.getByName(goodName);
            Order order = orderUtils.getById(order_id);
            orderUtils.addToOrder(order, good, amount);
            model.addAttribute("order", order);
        } catch (IllegalArgumentException e) {
            model.addAttribute("order", orderUtils.getById(order_id));
        }
        model.addAttribute("customer", customerUtils.getById(customer_id));
        model.addAttribute("goods", goodUtils.getAll());
        return "/order-pages/manage-cart";
    }
}
