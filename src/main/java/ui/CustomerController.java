package ui;

import domain.models.Customer;
import domain.models.Order;
import domain.utils.CustomerUtils;
import domain.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerUtils customerUtils;

    @Autowired
    private OrderUtils orderUtils;

    @GetMapping("/")
    public String startPage(Model model) {
        return "/customer-pages/start-page";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        return "/customer-pages/sign-up";
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        return "/customer-pages/sign-in";
    }

    @PostMapping("/sign-up-process")
    public String create(@RequestParam("name") String name,
                         @RequestParam("surname") String surname,
                         @RequestParam("password") String password,
                         Model model) {
        Customer customer = new Customer(name, surname, password);

        customerUtils.addCustomer(customer);

        model.addAttribute("customer", customer);
        model.addAttribute("orders", orderUtils.getAllByCustomer(customer));
        return  "/customer-pages/customer-page";
    }

    @GetMapping("/sign-in-process")
    public String signInProcess(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("password") String password,
                                Model model) {
        Customer customer = customerUtils.getCustomerByName(name, surname);

        if (customer.getPassword().equals(password)) {
            addOrdersToModel(customer, model);
            model.addAttribute("customer", customer);
            return "/customer-pages/customer-page";
        } else {
            return "/customer-pages/tmpplug";
        }
    }

    @GetMapping("/main-page")
    public String mainPage(@RequestParam("customer") int customer_id,
                                Model model) {
        Customer customer = customerUtils.getById(customer_id);
        addOrdersToModel(customer, model);
        model.addAttribute("customer", customer);
        return "/customer-pages/customer-page";
    }

    @PostMapping("/add-money")
    public String addMoney(@RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("money") int money,
                           Model model) {
        customerUtils.addMoneyToCustomer(name, surname, money);

        Customer customer = customerUtils.getCustomerByName(name, surname);
        model.addAttribute("customer", customer);
        addOrdersToModel(customer, model);
        return "/customer-pages/customer-page";
    }

    @PostMapping("/pay-and-process-cart")
    public String payProcessCart(@RequestParam("cart") int order_id,
                                 @RequestParam("customer") int customer_id,
                                 Model model) {
        Order order = orderUtils.getById(order_id);
        Customer customer = customerUtils.getById(customer_id);
        if (!order.getOrderList().isEmpty() && customer.getMoney() > 0) {
            orderUtils.processOrder(order, customer);
        }
        addOrdersToModel(customer, model);
        model.addAttribute("customer", customer);
        return "/customer-pages/customer-page";
    }

    private void addOrdersToModel(Customer customer, Model model) {
        List<Order> orders = orderUtils.getAllByCustomer(customer);
        List<Order> processedOrders = new ArrayList<>();
        Order cart = null;
        for (Order order : orders) {
            if (order.isProcessed()) {
                processedOrders.add(order);
            } else {
                if (cart == null) {
                    cart = order;
                } else {
                    throw new IllegalStateException();
                }
            }
        }
        if (cart == null) {
            cart = orderUtils.createNewOrder(customer);
        }
        model.addAttribute("orders", processedOrders);
        model.addAttribute("cart", cart);
    }
}
