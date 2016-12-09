package org.phpbee.t5.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:test.properties")
//@AutoConfigureTestDatabase
public class TransactionControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;


    private Transaction transaction;

    @Before
    public void setup() {
        transactionRepository.deleteAll();

        transaction = new Transaction();
        transactionRepository.save(transaction);

    }

    @Test
    public void getUnexistedTransaction() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/transaction/{id}", String.class, "UnexistedTransactionID");
        assertThat( response.getStatusCode() , equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void getExistedTransaction() {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/transaction/{id}", String.class, transaction.getId());
        assertThat( response.getStatusCode() , equalTo(HttpStatus.NOT_FOUND));
    }

}