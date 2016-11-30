package org.phpbee.t5.Controller;

import org.phpbee.t5.Entity.AbstractSale;
import org.phpbee.t5.Entity.TransactionEntity;
import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SaleController {
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping(value = "/transaction/{transactionId}/sale/{saleId}", method = RequestMethod.GET)
    public AbstractSale findById(@PathVariable(value = "transactionId") String transactionId, @PathVariable(value = "saleId") String saleId) {
        TransactionEntity transaction = transactionRepository.findById(transactionId);
        return transaction.findSaleById(saleId);
    }


    @RequestMapping(value="/transaction/{id}/sales", method=RequestMethod.GET)
    public HashMap<String, AbstractSale> getSales(@PathVariable(value="id") String id) {
        return transactionRepository.findById(id).getSales();
    }

}
