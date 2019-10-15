package br.com.dbtest.bank.resource.rest;

import br.com.dbtest.bank.domain.ContaCorrente;
import br.com.dbtest.bank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//Metodo Cliente faz a chamada GET - /cursos será chamado o método Listar
@RestController
//Recursos são nomeados no plural
@RequestMapping(
        value = "/transfs",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class ContaRestController {

    @Autowired
    private ContaService contaService;

    @PostMapping  //  HTTP STATUS 201 CREATED
    //NAO vamos retorno um objeto curso mas ism um corpo vazio na resposta
    //No cabeção será inserio a localizado do objeto inseriso no banco
    public ResponseEntity<Void> salvar (@RequestBody ContaCorrente conta){
        contaService.save(conta);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}") // adiciono na URl retornada um {id}
                .buildAndExpand(conta.getId()) // adicionamos  um valor no local do od que adicnionamos no path
                .toUri();// variavel loction com a URL de localizacao
        //OBJETO DE RETORNO
        return ResponseEntity.created(location).build();
        // CREATED HTTP STATUS 201
        //build envia um http status com response entity
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContaCorrente> listar() {
        return contaService.findAll();
    }

}
