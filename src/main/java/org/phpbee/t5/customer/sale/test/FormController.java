package org.phpbee.t5.customer.sale.test;

import org.phpbee.t5.Entity.Sale;
import org.phpbee.t5.Entity.TransactionEntity;
import org.phpbee.t5.Repository.TransactionRepository;
import org.phpbee.t5.Validator.ValueInListConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Controller
public final class FormController extends WebMvcConfigurerAdapter {

    @Bean
    public FormLists requestedStatuses() {
        return new FormLists();
    }

    @Autowired
    FormLists valueInListConstraintValidatorLists;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/customer/sale/test")
    public String showForm(Form form) {
        return "customer/sale/test/form";
    }

    @PostMapping("/customer/sale/test")
    public String checkForm(@Valid Form form, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "customer/sale/test/form";
        }

        TransactionEntity transaction = transactionRepository.findById(form.getTransactionId());
        Sale sale = new Sale();
        sale.setRequestedStatus(form.getRequestedStatus());
        transaction.addSale(sale);
        transactionRepository.save(transaction);

        try {
            URI returnURL = new URI(form.getReturnURL());
            returnURL = appendUri(returnURL, "saleId=" + sale.getId());
            return "redirect:" + returnURL.toString();
        } catch (URISyntaxException e) {
            throw new Exception(e.getMessage());
        }
    }

    @ModelAttribute("requestedStatuses")
    public List<String> populateFeatures() throws ValueInListConstraintException {
        return Arrays.asList(valueInListConstraintValidatorLists.get("RequestedStatuses"));
    }

    private static URI appendUri(URI oldUri, String appendQuery) throws URISyntaxException {
        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());

        return newUri;
    }

}