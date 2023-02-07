package br.com.sicred.tests.restricaocredito;

import br.com.sicred.client.RestricoesClient;
import br.com.sicred.core.BaseTest;
import br.com.sicred.core.Enums.Clientes;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;


public class RestricaoCreditoServiceTest extends BaseTest {

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("com_restricoes"),@Tag("withbug")})
    @DisplayName("Realizar uma consultar na API com CPF que POSSUI restrição.")
    public void validarConsultaNaAPIRestricoesComClienteQuePOSSUIRestricao() {
        Response responseRetricoesGet = RestricoesClient.validarPesquisaDeClientesComRestricoesPeloCPF(Clientes.CLIENTE_COM_RESTRICAO_NO_CPF.get());
        responseRetricoesGet
                .then()
                .statusCode(HttpStatus.SC_OK)
              //.body("mensagem", is("O CPF "+CLIENTE_COM_RESTRICAO+" possui restrição"))
        //TODO: Bug14 esperada: "O CPF 99999999999 possui restrição", mas retorno "O CPF 19626829001 tem problema"

        ;
    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("sem_restricoes")})
    @DisplayName("Realizar uma consultar na API com CPF que não possui restrição.")
    public void validarConsultaNaAPIRestricoesComClienteQueNAOPossuiRestricao() {
        Response responseRetricoesGet = RestricoesClient.validarPesquisaDeClientesComRestricoesPeloCPF(Clientes.CLIENTE_SEM_RESTRICAO_NO_CPF.get());
        responseRetricoesGet
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);


    }

}
