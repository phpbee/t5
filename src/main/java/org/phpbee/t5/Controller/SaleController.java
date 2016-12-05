package org.phpbee.t5.Controller;

import org.phpbee.t5.Entity.AbstractSale;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Repository.TransactionRepository;
import org.phpbee.t5.Resource.SaleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class SaleController {
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping(value = "/transaction/{transactionId}/sale/{saleId}", method = RequestMethod.GET)
    public SaleResource findById(@PathVariable(value = "transactionId") String transactionId, @PathVariable(value = "saleId") String saleId) {
        Transaction transaction = transactionRepository.findById(transactionId);
        AbstractSale sale = transaction.findSaleById(saleId);

        return new SaleResource(sale, transactionId);
    }


    @RequestMapping(value = "/transaction/{id}/sales", method = RequestMethod.GET)
    public Resources<SaleResource> getSales(@PathVariable(value = "id") String id) {

        HashMap<String, AbstractSale> sales = transactionRepository.findById(id).getSales();
        ArrayList<SaleResource> resourceList = new ArrayList<>();

        for (AbstractSale sale : sales.values()) {
            resourceList.add(new SaleResource(sale, id));
        }

        return new Resources<>(resourceList);
    }

}
