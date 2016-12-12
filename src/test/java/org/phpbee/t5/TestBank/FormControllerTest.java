package org.phpbee.t5.TestBank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.Exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class FormControllerTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FormController formController;


    private Transaction transaction;



    private MockMvc mockMvc;


    @Before
    public void setup() {
        transactionRepository.deleteAll();

        transaction = new Transaction();
        transactionRepository.save(transaction);

        this.mockMvc = standaloneSetup(formController).build();


    }

    @Test
    public void postSale() throws Exception {
        String returnUrl = "http://localhost/sale";
        this.mockMvc.perform(post("/TestBank/sale")
                .param("transactionId", transaction.getId())
                .param("requestedStatus", "Approved")
                .param("returnURL", returnUrl)
        )
                .andExpect(status().is3xxRedirection())
                //.andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern(returnUrl + "?saleId=*"))
        ;


    }



}