package br.com.dbtest.bank.resource.rest;

import br.com.dbtest.bank.domain.ContaCorrente;
import br.com.dbtest.bank.service.ContaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(
        value = "/transfs",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ContaRestController {

    static final Logger logger = LogManager.getLogger(ContaRestController.class.getName());

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ContaCorrente conta) {
        logger.trace(" @PostMapping - save");
        contaService.save(conta);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(conta.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContaCorrente> listar() {
        return contaService.findAll();
    }

}
