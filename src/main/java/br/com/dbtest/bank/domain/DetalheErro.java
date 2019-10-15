package br.com.dbtest.bank.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;



//APENAS METODOS GETTER
//PADRAO DE PROJETO BUILDER

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalheErro implements Serializable {

    private Integer statusCode; // 404 - 503

    private String statusMessage; // Not FOund 404 503 bad request

    private String httpMethod; // tipo de metodo

    private String erro; // mensagem de erro personalizada ou de exceção

    private String detalhe; // detalhar no erro

    private String path; // retorna a URL

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getErro() {
        return erro;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public String getPath() {
        return path;
    }

    public static Builder builder() {
        return new Builder();
    }

    //BUILDER
    public static class Builder {

        private DetalheErro erro;
        //CRIA o Builder, este metodo  serve para criar os objetos do tipo de detalhe erro
        Builder (){
            this.erro = new DetalheErro(); // vai recebe rum instancia do detalhe erro private DetalheErro erro
        }

        //O Builder vai receber um HTTP Status e apartir dele vai inicializar todas as variaveis de erro
        //Desta forma uma vez desenvolvido o Builder pode ser utilizado em todos os erros
        public Builder addStatus(HttpStatus status) {
            this.erro.statusCode = status.value();
            this.erro.statusMessage = status.getReasonPhrase();
            return this;
        }

        public Builder addHttpMethod(String method) {
            this.erro.httpMethod = method;
            return this;
        }

        public Builder addErro(String erro) {
            this.erro.erro = erro;
            return this;
        }

        public Builder addDetalhe(String detalhe) {
            this.erro.detalhe = detalhe;
            return this;
        }

        public Builder addPath(String path) {
            this.erro.path = path;
            return this;
        }

        public DetalheErro build() {
            return this.erro;
        }
    }
}




