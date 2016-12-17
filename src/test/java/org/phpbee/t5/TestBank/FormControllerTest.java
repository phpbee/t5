package org.phpbee.t5.TestBank;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.lang.Exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class FormControllerTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FormController formController;

    private Transaction transaction;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    private String returnUrl = "http://localhost/sale";

    @Bean
    public TransactionRepository transactionRepository() {
        return Mockito.mock(TransactionRepository.class);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        transactionRepository.deleteAll();

        transaction = new Transaction();
        transactionRepository.save(transaction);

        this.mockMvc = webAppContextSetup(this.wac).build();


    }

    @Test
    public void postSale() throws Exception {
        this.mockMvc.perform(post("/TestBank/sale")
                .param("transactionId", transaction.getId())
                .param("requestedStatus", "Approved")
                .param("returnURL", returnUrl)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern(returnUrl + "?saleId=*"))
        ;


    }

    @Test
    public void postEmptyForm() throws Exception {
        this.mockMvc.perform(post("/TestBank/sale")
                .param("transactionId", "")
                .param("returnURL", "")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("may not be null")));
        ;


    }


    @Test
    public void postInvalidURL() throws Exception {
        this.mockMvc.perform(post("/TestBank/sale")
                .param("transactionId", transaction.getId())
                .param("requestedStatus", "Approved")
                .param("returnURL", "726872678")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("must be a valid URL")))
        ;


    }

    @Test
    public void postUnexistedTransactionID() throws Exception {
        this.mockMvc.perform(post("/TestBank/sale")
                .param("transactionId", "nonexistent")
                .param("requestedStatus", "Declined")
                .param("returnURL", returnUrl)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Transaction does not exists")))
        ;


    }



}