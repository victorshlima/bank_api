package br.com.dbtest.bank.resource.exception;


import br.com.dbtest.bank.Exception.IdNaoValidoServiceException;
import br.com.dbtest.bank.Exception.NaoExisteDaoException;
import br.com.dbtest.bank.domain.DetalheErro;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//Monitora a classe com o erro
    //clicar no direito e verificar no metodo qual seria a classe de exceção
    //quando essa classe aparece no log @ExceptionHandler é acionado e podemos tratar a excecao
    //NullPointerException.class, IllegalArgumentException.class
    //org.hibernate.exception.ConstraintViolationException.class
    // public ResponseEntity<Object> Objeto de resposta
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Um exceção foi lançada.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolada(org.hibernate.exception.ConstraintViolationException ex,
                                                    WebRequest request) {
        // ex de runtime exception
        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Constraint violada: " + ex.getConstraintName()) //Não retorno o campo, mas é possivel
                        //retornar o indice para identifcar qual o registro com informacoes duplicadas
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.CONFLICT)
                        //CONFLICT cadastro redundante
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({org.hibernate.PropertyValueException.class})
    public ResponseEntity<Object> propriedadeNula(org.hibernate.PropertyValueException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("O atributo '"+ ex.getPropertyName() +"' não pode ser nulo.")
                        // RETORNAR APENAS UM CAMPO COM ERRO
                        //TANVEZ VALIDAR UM SCHEMA NO BODY QUANDO OCORRER ERRO  PORSSA RETORNAR MAIS INFOS
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.BAD_REQUEST)
                        //BADREQUEST - valor incorreto na request
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), //necessário um header na response
                HttpStatus.BAD_REQUEST, // resposta do erro
                request);  //referente ao webrequest da assinatura do metodo
    }

    @ExceptionHandler({NaoExisteDaoException.class})
    public ResponseEntity<Object> entidadeNaoEncontrada(NaoExisteDaoException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Recurso não encontrado na base de dados.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.NOT_FOUND)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), //necessário um header na response
                HttpStatus.NOT_FOUND, // resposta do erro
                request);  //referente ao webrequest da assinatura do metodo
    }


    @ExceptionHandler({IdNaoValidoServiceException.class})
    public ResponseEntity<Object> entidadeNaoEncontrada(IdNaoValidoServiceException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Recurso não encontrado na base de dados.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.BAD_REQUEST)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), //necessário um header na response
                HttpStatus.BAD_REQUEST, // resposta do erro
                request);  //referente ao webrequest da assinatura do metodo
    }

    //com a WebRequest podemos recuperar o path e mo methodo Http da excecao
    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getHttpMethod(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getMethod();
    }

}