package br.com.dbtest.bank.resource.rest;

import br.com.dbtest.bank.domain.Lancamento;
import br.com.dbtest.bank.service.LancamentoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(
        value = "/lanc",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class LancamentoRestController {

    static final Logger logger = LogManager.getLogger(LancamentoRestController.class.getName());




    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity<Void> Exec(@RequestBody Lancamento lancamento){

        logger.trace("@PostMapping - LancamentoService ");

        lancamentoService.lancamento(lancamento);
        System.out.println("PostMapping");
        System.out.println(lancamento.toString());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                 .path("/{id}") // adiciono na URl retornada um {id}
                .buildAndExpand(lancamento.getId()) // adicionamos  um valor no local do od que adicnionamos no path
                .toUri();// variavel loction com a URL de localizacao
        return    ResponseEntity.created(location).build();
        // CREATED HTTP STATUS 201
    }
//    @PatchMapping("/exec1")
//    @ResponseStatus(HttpStatus.OK)
//    public Lancamento Exec1(@RequestBody Lancamento lancamento)
//    {
//        System.out.println(lancamento.toString());
//        return lancamentoService.lancamento(lancamento);
//    }
//    @PatchMapping("/exec")
//    @ResponseStatus(HttpStatus.OK)
//    public Lancamento listar1(@RequestParam("agenciaOrig") int agenciaOrig,
//                         @RequestParam("contaOrig") int contaOrig,
//                         @RequestParam("agenciaDest") int agenciaDest,
//                         @RequestParam("contaDest") int contaDest,
//                         @RequestParam("valor") double valor,
//                         @RequestParam("tipo") String tipo)
//    {
//        return lancamentoService.lancamento(agenciaOrig,contaOrig, agenciaDest, contaDest, valor, tipo  );
//
//    }
}
