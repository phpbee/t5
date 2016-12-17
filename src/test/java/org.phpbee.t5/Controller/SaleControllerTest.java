package org.phpbee.t5.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.phpbee.t5.Entity.Sale;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Repository.TransactionRepository;
import org.phpbee.t5.TestBank.TestBankSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URISyntaxException;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class SaleControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SaleController saleController;

    private Transaction transaction;
    private Sale sale;

    private JacksonTester<Transaction> json;


    private MockMvc mockMvc;


    @Before
    public void setup() throws URISyntaxException {
        transactionRepository.deleteAll();

        transaction = new Transaction();
        transactionRepository.save(transaction);

        sale = new TestBankSale(TestBankSale.class.getName());

        transaction.addSale(sale);
        transactionRepository.save(transaction);


        this.mockMvc = standaloneSetup(saleController).build();

    }

    @Test
    public void getSale() throws Exception {
        this.mockMvc.perform(
                get("/transaction/{transactionId}/sale/{saleId}", transaction.getId(), sale.getId())
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.sale.id", is(sale.getId())))
                .andExpect(jsonPath("$.links[0].rel", is("self")))
                .andExpect(jsonPath("$.links[0].href", is("http://localhost/transaction/" + transaction.getId() + "/sale/" + sale.getId())))
                .andExpect(jsonPath("$.links[1].rel", is("transaction")))
                .andExpect(jsonPath("$.links[1].href", is("http://localhost/transaction/" + transaction.getId())))
        ;


    }

}