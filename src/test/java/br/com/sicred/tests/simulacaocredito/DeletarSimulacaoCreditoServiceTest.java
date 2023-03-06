package br.com.sicred.tests.simulacaocredito;

import br.com.sicred.client.SimulacoesCreditoClient;
import br.com.sicred.dto.SimulacoesCreditoDTO;
import br.com.sicred.stub.SimulacaoStub;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.commons.httpclient.HttpStatus;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;


@Epic("REST API Simulação de Crédito")
@Feature("Validar a funcionalidade de excluir simulação de crédito")
public class DeletarSimulacaoCreditoServiceTest {

    SimulacoesCreditoDTO requestSimulacaoCredito;
    SimulacoesCreditoDTO responseSimulacoesCredito;


    @Test
    @Tags(value = {@Tag("regressivo"), @Tag("deletar_simulacao"), @Tag("withbug")})
    @Story("Deletar dados cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Realizar uma remoção com sucesso")
    public void RealizarUmaRemocaoSimulacaoCreditoJaCadastrado() {

        //Criar uma simulação de crédito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        //Deletar simulacao de crédito
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.deletarUmaSimulacaoCreditoJaCadastrada(responseSimulacoesCredito);
        responseSimulacaoAtualizada
                .then()
        .statusCode(HttpStatus.SC_NO_CONTENT)
        ;
        //TODO: Bug11 Status code esta retornado 200 porém conforme as regras da API o correto seria retorna 204 ao remover uma simulação

    }

    @Test
    @Tags(value = {@Tag("regressivo"), @Tag("deletar_simulacao_nao_cadastrada"), @Tag("withbug")})
    @Story("Deletar dados cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Realizar uma remoção de simulação não cadastrada")
    public void RealizarUmaRemocaoSimulacaoCreditoNaoCadastrado() {
        //Criar uma simulação de crédito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();

        //Deletar simulacao de crédito sem cadastro
        requestSimulacaoCredito.setId(999);
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.deletarUmaSimulacaoCreditoJaCadastrada(requestSimulacaoCredito);
        responseSimulacaoAtualizada
                .then()
         .statusCode(HttpStatus.SC_NO_CONTENT)
                .body("mensagem",is("simulação não encontrada"))
        ;
        //TODO: Bug12 Status code esta retornado 200 porém conforme as regras da API o correto seria retorna 204 e uma mesagem de erro
        // para quando a simulação pesquisada não existe.

    }


}