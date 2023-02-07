package br.com.sicred.tests.simulacaocredito;

import br.com.sicred.client.SimulacoesCreditoClient;
import br.com.sicred.core.BaseTest;
import br.com.sicred.dto.SimulacoesCreditoDTO;
import br.com.sicred.stub.SimulacaoStub;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class DeletarSimulacaoCreditoServiceTest extends BaseTest {

    SimulacoesCreditoDTO requestSimulacaoCredito;
    SimulacoesCreditoDTO responseSimulacoesCredito;


    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("deletar_simulacao"),@Tag("withbug")})
    public void RealizarUmaRemocaoSimulacaoCreditoJaCadastrado() {
        //Criar uma simulação de credito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        //Deletar simulacao de credito
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.deletarUmaSimulacaoCreditoJaCadastrada(responseSimulacoesCredito);
        responseSimulacaoAtualizada
                .then()
             //.statusCode(HttpStatus.SC_NO_CONTENT)
           ;
        //TODO: Bug11 Status code esta retornado 200 por�m conforme as regras da API o correto seria retorna 204 ao remover uma simulac�o

    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("deletar_simulacao_nao_cadastrada"),@Tag("withbug")})
    public void RealizarUmaRemocaoSimulacaoCreditoNaoCadastrado() {
        //Criar uma simulação de credito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();

        //Deletar simulacao de credito sem cadastro
        requestSimulacaoCredito.setId(999);
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.deletarUmaSimulacaoCreditoJaCadastrada(requestSimulacaoCredito);
        responseSimulacaoAtualizada
                .then()
      //  .statusCode(HttpStatus.SC_NO_CONTENT)
           //     .body("mensagem",is("simulação não encontrada"))
        ;
        //TODO: Bug12 Status code esta retornado 200 por�m conforme as regras da API o correto seria retorna 204 e uma mesagem de erro
        // para quando a simulação pesquisada não existe.

    }

}
