package org.phpbee.t5.customer.sale.test;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.validation.Valid;

@Controller
public class FormController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/customer/sale/test")
    public String showForm(Form form) {
        return "customer/sale/test/form";
    }

    @PostMapping("/customer/sale/test")
    public String checkForm(@Valid Form form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customer/sale/test/form";
        }

        return "redirect:/results";
    }
}