package ui;

import domain.models.Customer;
import domain.utils.CustomerUtils;
import domain.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            model.addAttribute("customer", customer);
            return "/customer-pages/customer-page";
        } else {
            return "/customer-pages/tmpplug";
        }
    }

    @PostMapping("/add-money")
    public String addMoney(@RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("money") int money,
                           Model model) {
        customerUtils.addMoneyToCustomer(name, surname, money);
        model.addAttribute("customer", customerUtils.getCustomerByName(name, surname));
        return "/customer-pages/customer-page";
    }
}
