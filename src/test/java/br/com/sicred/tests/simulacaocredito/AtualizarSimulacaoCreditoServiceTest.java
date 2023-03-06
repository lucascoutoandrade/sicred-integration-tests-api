package br.com.sicred.tests.simulacaocredito;

import br.com.sicred.client.SimulacoesCreditoClient;
import br.com.sicred.core.BaseTest;
import br.com.sicred.core.enums.ParcelasCredito;
import br.com.sicred.core.enums.ValoresCredito;
import br.com.sicred.dto.SimulacoesCreditoDTO;
import br.com.sicred.stub.SimulacaoStub;
import io.qameta.allure.*;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;



import static net.datafaker.providers.base.BaseFaker.instance;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@Epic("REST API Simulação de Crédito")
@Feature("Validar a funcionalidade de atualização da simulação de crédito")
public class AtualizarSimulacaoCreditoServiceTest extends BaseTest {
    SimulacoesCreditoDTO requestSimulacaoCredito;
    SimulacoesCreditoDTO responseSimulacoesCredito;


    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("atualizar_cpf")})
    @Story("Atualizar Dados Cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Atualizar o campo CPF")
    @DisplayName("Test Description : Atualizar o campo CPF")
    public void RealizarUmaAtualizacaoSimulacaoCreditoJaCadastradoNoAtributoCPF() {


            //Criar uma simulação de credito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        // Atualizar CPF da simulação criada anteriomente
        responseSimulacoesCredito.setCpf(instance().cpf().valid());
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.atualizarCPFUmaSimulacaoDeCreditoJaCadastrada(responseSimulacoesCredito, requestSimulacaoCredito);
        responseSimulacaoAtualizada
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("cpf", not(requestSimulacaoCredito.getCpf()),
                        "cpf", is(responseSimulacoesCredito.getCpf()));

    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("atualizar_nome")})
    @Story("Atualizar Dados Cadastrais")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description : Atualizar o campo NOME")
    public void RealizarUmaAtualizacaoSimulacaoCreditoJaCadastradoNoAtributoNome() {
        //Criar uma simulação de credito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        // Atualizar Nome da simulação criada anteriomente
        responseSimulacoesCredito.setNome(instance().name().fullName());
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.atualizarUmaSimulacaoDeCreditoJaCadastrada(responseSimulacoesCredito);
        responseSimulacaoAtualizada
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", not(requestSimulacaoCredito.getNome()),
                        "nome", is(responseSimulacoesCredito.getNome()));

    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("atualizar_email")})
    @Story("Atualizar Dados Cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Atualizar o campo EMAIL")
    public void RealizarUmaAtualizacaoSimulacaoCreditoJaCadastradoNoAtributoEmail() {
        Faker faker = new Faker();
        //Criar uma simulação de credito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        // Atualizar Email da simulação criada anteriomente
        responseSimulacoesCredito.setEmail(faker.internet().emailAddress());
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.atualizarUmaSimulacaoDeCreditoJaCadastrada(responseSimulacoesCredito);
        responseSimulacaoAtualizada
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("email", not(requestSimulacaoCredito.getEmail()),
                        "email", is(responseSimulacoesCredito.getEmail()));


    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("atualizar_valor"),@Tag("withbug")})
    @Story("Atualizar Dados Cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Atualizar o campo VALOR")
    public void RealizarUmaAtualizacaoSimulacaoCreditoJaCadastradoNoAtributoValor() {
        //Criar uma simulação de credito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        // Atualizar Valor da simulação criada anteriomente
        responseSimulacoesCredito.setValor(instance().number().numberBetween(ValoresCredito.VALOR_DO_CREDITO_MIN_PERMITIDO.get(), ValoresCredito.VALOR_DO_CREDITO_MAX_PERMITIDO.get()));
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.atualizarUmaSimulacaoDeCreditoJaCadastrada(responseSimulacoesCredito);
        responseSimulacaoAtualizada
                .then()
                .statusCode(HttpStatus.SC_OK)
        //TODO:Bug10 API de Atualização não esta atualizando o atributo valor
        .body("valor",not(requestSimulacaoCredito.getValor()),
                        "valor",is(responseSimulacoesCredito.getValor()));
    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("atualizar_parcela")})
    @Story("Atualizar Dados Cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Atualizar o campo PARCELA")
    public void RealizarUmaAtualizacaoSimulacaoCreditoJaCadastradoNoAtributoParcela() {
        //Criar uma simulação de credito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        //Atualizar Valor da simulação criada anteriomente
        responseSimulacoesCredito.setParcelas(instance().number().numberBetween(ParcelasCredito.NUMERO_PARCELAS_MIN_PERMITIDO.get(), ParcelasCredito.NUMERO_PARCELAS_MAX_PERMITIDO.get()));
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.atualizarUmaSimulacaoDeCreditoJaCadastrada(responseSimulacoesCredito);
        responseSimulacaoAtualizada
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("parcelas", not(requestSimulacaoCredito.getParcelas()),
                        "parcelas", is(responseSimulacoesCredito.getParcelas()));

        }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("atualizar_seguro")})
    @Story("Atualizar Dados Cadastrais")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Atualizar o campo SEGURO")
    public void RealizarUmaAtualizacaoSimulacaoCreditoJaCadastradoNoAtributoSeguro() {
        //Criar uma simulação de credito
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);

        //Atualizar Valor da simulação criada anteriomente
        responseSimulacoesCredito.setSeguro(false);
        Response responseSimulacaoAtualizada = SimulacoesCreditoClient.atualizarUmaSimulacaoDeCreditoJaCadastrada(responseSimulacoesCredito);
        responseSimulacaoAtualizada
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("seguro", not(requestSimulacaoCredito.isSeguro()),
                        "seguro", is(responseSimulacoesCredito.isSeguro()),
                        "seguro", is(false));

         }

         @Test
         @Tags(value = {@Tag("regressivo"),@Tag("atualizar_cliente_nao_cadastrado")})
         @Story("Atualizar Dados Cadastrais")
         @Severity(SeverityLevel.NORMAL)
         @Description("Test Description : Tentativa de atualiza cliente não cadastrado")
    public void RealiazarUmaAtualizacaoSimulacaoCreditoParaClienteNaoCadastrado(){
             //Criar uma simulação de credito
             requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
             Response responseSimulacaoAtualizada = SimulacoesCreditoClient.atualizarUmaSimulacaoDeCreditoJaCadastrada(requestSimulacaoCredito);
             responseSimulacaoAtualizada
                     .then()
                     .statusCode(HttpStatus.SC_NOT_FOUND)
                     .body("mensagem", is("CPF "+requestSimulacaoCredito.getCpf()+" não encontrado"));


         }


}
