package ui;

import domain.models.Customer;
import domain.utils.CustomerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @Autowired
    private CustomerUtils customerUtils;

    @GetMapping("/")
    public String startPage(Model model) {
        return "start-page";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        return "sign-up";
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        return "sign-in";
    }

    @PostMapping("/sign-up-process")
    public String create(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
        Customer customer = new Customer(name, password);

        customerUtils.addCustomer(customer);

        model.addAttribute("customer", customer);
        return  "customer-page";
    }

    @GetMapping("/sign-in-process")
    public String signInProcess(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
        Customer tmpCustomer = new Customer(name, password);

        if (customerUtils.isValidCustomer(tmpCustomer)) {
            model.addAttribute("customer", tmpCustomer);
            return "customer-page";
        } else {
            return "tmpplug";
        }
    }
}
