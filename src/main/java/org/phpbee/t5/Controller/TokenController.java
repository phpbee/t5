package org.phpbee.t5.Controller;

import org.phpbee.t5.Entity.TokenEntity;
import org.phpbee.t5.Entity.TransactionEntity;
import org.phpbee.t5.Repository.TokenRepository;
import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @RequestMapping(value="/token", method=RequestMethod.POST)
    public ResponseEntity create() {
        TokenEntity tokenEntity = new TokenEntity();
        tokenRepository.save(tokenEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(tokenEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value="/token/{id}", method=RequestMethod.GET)
    public TokenEntity findById(@PathVariable(value="id") String id) {
        return tokenRepository.findById(id);
    }

}
