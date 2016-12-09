package org.phpbee.t5.Controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
//@AutoConfigureTestDatabase
public class TransactionControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionController transactionController;

    private Transaction transaction;

    private JacksonTester<Transaction> json;


    private MockMvc mockMvc;


    @Before
    public void setup() {
        transactionRepository.deleteAll();

        transaction = new Transaction();
        transactionRepository.save(transaction);

        System.out.println("============================");


        this.mockMvc = standaloneSetup(transactionController).build();

    }

    @Test
    public void getUnexistedTransaction() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/transaction/{id}", String.class, "UnexistedTransactionID");
        //assertThat(response.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void getExistedTransaction() throws Exception {
        String id = transaction.getId();
        this.mockMvc.perform(get("/transaction/{id}", id)
                //.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.transaction.id", is(id)))
                .andExpect(jsonPath("$.links[0].rel", is("self")))
                .andExpect(jsonPath("$.links[0].href", is("http://localhost/transaction/" + id)))
                .andExpect(jsonPath("$.links[1].rel", is("sales")))
                .andExpect(jsonPath("$.links[1].href", is("http://localhost/transaction/" + id + "/sales")))
        ;


    }

    @Test
    public void postTransaction() throws Exception {
        this.mockMvc.perform(post("/transaction/")
        )
                .andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("http*://localhost/transaction/*"))
        ;
        ;


    }
}