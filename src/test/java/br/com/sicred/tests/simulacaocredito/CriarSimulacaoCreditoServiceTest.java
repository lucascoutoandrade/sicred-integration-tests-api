package br.com.sicred.tests.simulacaocredito;

import br.com.sicred.client.SimulacoesCreditoClient;
import br.com.sicred.core.BaseTest;
import br.com.sicred.core.Enums.ParcelasCredito;
import br.com.sicred.core.Enums.ValoresCredito;
import br.com.sicred.dto.SimulacoesCreditoDTO;
import br.com.sicred.stub.SimulacaoStub;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;


public class CriarSimulacaoCreditoServiceTest extends BaseTest {
    private static final Logger log = Logger.getLogger(CriarSimulacaoCreditoServiceTest.class.getName());

    SimulacoesCreditoDTO requestSimulacaoCredito;
    Response responseSimulacoesCredito;


    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("cadastrar_simulacao"),@Tag("withbug")})
    public void criarSimulacaoDeCreditoComSucesso() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        var responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoReturnResponse(requestSimulacaoCredito);
        responseSimulacoesCredito
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("cpf", is(requestSimulacaoCredito.getCpf()),
                        "email", is(requestSimulacaoCredito.getEmail()),
                        "nome", is(requestSimulacaoCredito.getNome()),
                        "parcelas", is(requestSimulacaoCredito.getParcelas()),
                        "valor", is(requestSimulacaoCredito.getValor()));

        //TODO:Bug01 API de cadastro permiter cadastrar com uso de mascara no atributo cpf "999.999.999-99"


    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("cadastrar_simulacao_ja_existente"),@Tag("withbug")})
    public void criarSimulacaoDeCreditoComCPFJaCadastradoNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        var responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCredito(requestSimulacaoCredito);
        String cpf = responseSimulacoesCredito.getCpf();
        requestSimulacaoCredito.setCpf(cpf);
        Response responseSimulacoesCreditoJaCadastrado = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoComCPFJaCadastrado(requestSimulacaoCredito);
        responseSimulacoesCreditoJaCadastrado
                .then()
        //TODO:Bug02 Esperado: status code "409" mas Apresentado: "400"
        //.statusCode(HttpStatus.SC_CONFLICT)
        //TODO:bug03 Esperado: msg "CPF j� existente" mas Apresentado: "CPF duplicado"
        //.body("mensagem", is("CPF j� existente"))
        ;

    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("cadastrar_sem_cpf")})
    public void criarSimulacaoDeCreditoSEMInformarAtributoObrigatorioCpfNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoSemInformarAtributoObrigatorio("cpf");
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoSemInformarAtributosObrigatorios(requestSimulacaoCredito);
        responseSimulacoesCredito
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("erros.cpf", is("CPF não pode ser vazio"));
    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("cadastrar_sem_email")})
    public void criarSimulacaoDeCreditoSEMInformarAtributoObrigatorioEmailNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoSemInformarAtributoObrigatorio("email");
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoSemInformarAtributosObrigatorios(requestSimulacaoCredito);
        responseSimulacoesCredito
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("erros.email", is("E-mail não deve ser vazio"));
    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("cadastrar_sem_nome")})
    public void criarSimulacaoDeCreditoSEMInformarAtributoObrigatorioNomeNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoSemInformarAtributoObrigatorio("nome");
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoSemInformarAtributosObrigatorios(requestSimulacaoCredito);
        responseSimulacoesCredito
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("erros.nome", is("Nome não pode ser vazio"));
    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("cadastrar_sem_parcelas")})
    public void criarSimulacaoDeCreditoSEMInformarAtributoObrigatorioParcelasNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoSemInformarAtributoObrigatorio("parcelas");
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoSemInformarAtributosObrigatorios(requestSimulacaoCredito);
        responseSimulacoesCredito
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"));
    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("cadastrar_sem_seguro"),@Tag("withbug")})
    public void criarSimulacaoDeCreditoSEMInformarAtributoObrigatorioSeguroNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoSemInformarAtributoObrigatorio("seguro");
        responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoSemInformarAtributosObrigatorios(requestSimulacaoCredito);
        responseSimulacoesCredito
                .then()
        //TODO:Bug04 Esperado: retornar erro <400> Apresentado: <201>
        //.statusCode(HttpStatus.SC_BAD_REQUEST)
        //TODO:Bug05 Esperado: retornar msg "seguro não deve ser vazio"  Apresentado: criado com sucesso
        //  Obs:API nao esta tratando mensagem de erro ao realizar a requisicao sem informar o atributo seguro (retorna 500  "Internal Server Error")

        //.body("erros.seguro", is("seguro não deve ser vazio"))
        ;

    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("valor_menor_que_o_permitido"),@Tag("withbug")})
    public void criarSimulacaoDeCreditoComValorMenorQueOPermitidoNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        requestSimulacaoCredito.setValor(ValoresCredito.VALOR_DO_CREDITO_ABAIXO_DO_PERMITIDO.get());
        var responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoReturnResponse(requestSimulacaoCredito);

        responseSimulacoesCredito
                .then()
        //TODO: Bug06 Esperado: retornar erro <400> Apresentado: <201>
        //.statusCode(HttpStatus.SC_BAD_REQUEST)
        //TODO:Bug07 Esperado: retornar msg "Valor deve ser maior ou igual a R$ 1.000"  Apresentado: criado com sucesso
        //.body("erros.valor",is(  "Valor deve ser maior ou igual a R$ 1.000"))
        ;

    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("valor_maior_que_o_permitido"),@Tag("withbug")})
    public void criarSimulacaoDeCreditoComValorMaiorQueOPermitidoNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        requestSimulacaoCredito.setValor(ValoresCredito.VALOR_DO_CREDITO_ACIMA_DO_PERMITIDO.get());
        var responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoReturnResponse(requestSimulacaoCredito);
      //TODO: Bug08 API de cadastro esta permitindo cadastrar com valores MAIORES que o permitido caso seja usado " . " Ex:(40.001)
        responseSimulacoesCredito
                .then()
          .statusCode(HttpStatus.SC_BAD_REQUEST)
          .body("erros.valor",is(  "Valor deve ser menor ou igual a R$ 40.000"))
        ;

    }   
    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("parcela_maior_que_o_permitido"),@Tag("withbug")})
    public void criarSimulacaoDeCreditoComParcelaMaiorQueOPermitidoNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        requestSimulacaoCredito.setParcelas(ParcelasCredito.NUMERO_PARCELAS_ACIMA_DO_PERMITIDO.get());
        var responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoReturnResponse(requestSimulacaoCredito);
        //TODO: Bug09 API de cadastro esta permitindo cadastrar com parcelas acima do permitido (>48)
        responseSimulacoesCredito
                .then()
         // .statusCode(HttpStatus.SC_BAD_REQUEST)
         // .body("erros.parcelas",is(  "Parcelas deve ser igual ou menor que 48"))

        ;

    }
    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("parcela_menor_que_o_permitido")})
    public void criarSimulacaoDeCreditoComParcelaAbaixoDoPermitidoNaoDeveCadastrar() {
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        requestSimulacaoCredito.setParcelas(ParcelasCredito.NUMERO_PARCELAS_ABAIXO_DO_PERMITIDO.get());
        var responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoReturnResponse(requestSimulacaoCredito);
        responseSimulacoesCredito
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("erros.parcelas",is(  "Parcelas deve ser igual ou maior que 2"))
        ;

    }

    @Test
    @Tags(value = {@Tag("regressivo"),@Tag("cadastrar_com_email_invalido")})
    public void criarSimulacaoDeCreditoUtilizandoUmEmailInvalidoNaoDeveCadastrar() {
        Faker faker = new Faker();
        requestSimulacaoCredito = SimulacaoStub.criarSimulacaoCreditoValida();
        requestSimulacaoCredito.setEmail(faker.internet().emailAddress().replace("@",""));
        var responseSimulacoesCredito = SimulacoesCreditoClient.criarUmaNovaSimulacaoDeCreditoReturnResponse(requestSimulacaoCredito);
        Object mensagemErro = responseSimulacoesCredito
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().path("erros.email");
        Assertions.assertTrue(mensagemErro.equals("E-mail deve ser um e-mail válido")||mensagemErro.equals("não é um endereço de e-mail"));

    }

}
