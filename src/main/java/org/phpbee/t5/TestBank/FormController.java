package org.phpbee.t5.TestBank;

import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Repository.TransactionRepository;
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
import java.lang.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public final class FormController extends WebMvcConfigurerAdapter {

    @Bean
    public RequestedStatus requestedStatuses() {
        return new RequestedStatus();
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/TestBank/sale")
    public String showForm(Form form) {
        return "TestBank/sale/form";
    }

    @PostMapping("/TestBank/sale")
    public String checkForm(@Valid Form form, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "TestBank/sale/form";
        }
        try {
            Transaction transaction = transactionRepository.findById(form.getTransactionId());
            TestBankSale sale = new TestBankSale(TestBankSale.class.getName());

            URI returnURL = new URI(form.getReturnURL());
            returnURL = appendUri(returnURL, "saleId=" + sale.getId());
            sale.setReturnURL(returnURL);

            sale.setRequestedStatus(form.getRequestedStatus());
            transaction.addSale(sale);
            transactionRepository.save(transaction);

            return "redirect:" + sale.getReturnURL().toString();
        } catch (URISyntaxException e) {
            throw new Exception(e.getMessage());
        }
    }

    @ModelAttribute("requestedStatuses")
    public List<String> getRequestedStatuses() {
        return RequestedStatus.values();
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