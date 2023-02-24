package br.com.sicred.tests.simulacaocredito;

import br.com.sicred.client.SimulacoesCreditoClient;
import br.com.sicred.core.BaseTest;
import br.com.sicred.dto.SimulacoesCreditoDTO;
import br.com.sicred.stub.SimulacaoStub;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.hamcrest.Matchers.*;


@Epic("REST API Simulação de Crédito")
@Feature("Validar a funcionalidade de buscar da simulações de crédito")
public class BuscarSimulacaoCreditoServiceTest extends BaseTest {


    List<SimulacoesCreditoDTO> responseSimulacoesCredito;

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("buscar_todas_simulacoes")})
    @Story("Pesquisar dados cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : buscar todas simulações cadastradas")
    public void buscarTodasSimulacoesDeCreditoCadastradas() {

        //Populando lista de simulacoes de credito
        for (int simulacoes = 0; simulacoes < 3; simulacoes++) {
            var requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
            SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);
        }
        //Pesquisando toda lista de simulacoes de credito cadastrado
        responseSimulacoesCredito = SimulacoesCreditoClient.pesquisarListaSimulacaoCreditoCadastrados();
        MatcherAssert.assertThat(responseSimulacoesCredito, not(empty()));
        Assertions.assertTrue(responseSimulacoesCredito.size() > 2);


    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("nenhuma_simulacao_Cadastra"),@Tag("withbug")})
    @Story("Pesquisar dados cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description :Nenhuma simulação cadastrada")
    public void buscarSimulacoesDeCreditoComListaVaziaDeveRetornarStatusCode204() {
        // Removendo todas simulacoes cadastradas
        List<Integer> idsSimulacoes = SimulacoesCreditoClient.buscarIdsDasSimulacoesCadastradas();
        for (Integer idsSimulacoe : idsSimulacoes) {
            var requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
            requestSimulacaoCredito.setId(idsSimulacoe);
            SimulacoesCreditoClient.deletarUmaSimulacaoCreditoJaCadastrada(requestSimulacaoCredito);

        }
        //Pesquindo sem ter simulacoes de crédito cadastras
        List<SimulacoesCreditoDTO> listSimulacoesCredito = SimulacoesCreditoClient.pesquisarSimulacaoCreditoListaVazia();
        MatcherAssert.assertThat(listSimulacoesCredito, is(empty()));
        Assertions.assertEquals(listSimulacoesCredito.size(), 0);

        //TODO:Bug13 API não esta rertonando status 204. quando não existe simulações de credito

    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("buscar_simulacoes_por_cpf")})
    @Story("Pesquisar dados cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Buscar simulações atraves do CPF")
    public void buscarSimulacoesDeCreditoPorCPF() {
        //criando simulacao de credito

        var requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        //Pesquindo sem ter simulacoes de crédito cadastras
        Response responseSimulacoesPorCPF = SimulacoesCreditoClient.pesquisarSimulacaoDeCreditoPorCPF(requestSimulacaoCredito);
        responseSimulacoesPorCPF
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(not(emptyOrNullString())),
                        "nome", is(not(emptyOrNullString())),
                        "cpf", is(not(emptyOrNullString())),
                        "email", is(not(emptyOrNullString())),
                        "valor", is(not(emptyOrNullString())),
                        "parcelas", is(not(emptyOrNullString())),
                        "seguro", is(not(emptyOrNullString())));
    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("buscar_cpf_inexistente")})
    @Story("Pesquisar dados cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Buscar simulações de crédito para CPF não cadastrado")
    public void buscarSimulacoesDeCreditoPorCPFNaoExistente() {
        var requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        requestSimulacaoCredito.setCpf("10010010010");
        Response responseSimulacoesPorCPF = SimulacoesCreditoClient.pesquisarSimulacaoDeCreditoPorCPF(requestSimulacaoCredito);
        responseSimulacoesPorCPF
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("mensagem", is("CPF 10010010010 não encontrado"));

    }
}
