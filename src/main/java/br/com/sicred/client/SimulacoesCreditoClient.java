package br.com.sicred.client;

import br.com.sicred.dto.SimulacoesCreditoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimulacoesCreditoClient {

    public static SimulacoesCreditoDTO criarUmaNovaSimulacaoDeCredito(SimulacoesCreditoDTO simulacoesCredito) {
      return  given().contentType(ContentType.JSON)
                .body(simulacoesCredito)
                .when()
                .post("simulacoes")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(SimulacoesCreditoDTO.class);


    }
    public static Response criarUmaNovaSimulacaoDeCreditoReturnResponse(SimulacoesCreditoDTO simulacoesCredito) {
        return  given()
                .body(simulacoesCredito)
                .when()
                .post("simulacoes")
                .then()
                .extract().response();

    }

    public static Response criarUmaNovaSimulacaoDeCreditoComCPFJaCadastrado(SimulacoesCreditoDTO simulacoesCredito) {
        return  given()
                .body(simulacoesCredito)
                .when()
                .post("simulacoes")
                .then()
                .extract().response();

    }
    public static Response criarUmaNovaSimulacaoDeCreditoSemInformarAtributosObrigatorios(SimulacoesCreditoDTO simulacoesCredito) {
        return  given()
                .body(simulacoesCredito)
                .when()
                .post("simulacoes")
                .then()
                .extract().response();

    }

    public static Response atualizarUmaSimulacaoDeCreditoJaCadastrada(SimulacoesCreditoDTO simulacoesCredito) {

        return  given()
                .contentType(ContentType.JSON)
                .basePath("simulacoes")
                .body(simulacoesCredito)
                .when()
                .put(simulacoesCredito.getCpf())
                .then()
                .extract().response();

    }
    public static Response atualizarCPFUmaSimulacaoDeCreditoJaCadastrada(SimulacoesCreditoDTO novoCpf, SimulacoesCreditoDTO simulacaoCriada) {

        return  given()
                .contentType(ContentType.JSON)
                .basePath("simulacoes")
                .body(novoCpf)
                .when()
                .put(simulacaoCriada.getCpf())
                .then()
                .extract().response();

    }


    public static Response deletarUmaSimulacaoCreditoJaCadastrada(SimulacoesCreditoDTO responseSimulacoesCredito) {
        return given()
                .contentType(ContentType.JSON)
                .basePath("simulacoes")
                .when()
                .delete(String.valueOf(responseSimulacoesCredito.getId()))
                .then()
                .extract().response();
    }

    public static List<SimulacoesCreditoDTO> pesquisarListaSimulacaoCreditoCadastrados() {

          return  given()
                .basePath("simulacoes")
                .when()
                .get()
                .then()
                  .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().getList("",SimulacoesCreditoDTO.class);


    }

    public static List<Integer> buscarIdsDasSimulacoesCadastradas() {
        return  given()
                .basePath("simulacoes")
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().getList("id");


    }

    public static List<SimulacoesCreditoDTO> pesquisarSimulacaoCreditoListaVazia() {
        return  given()
                .basePath("simulacoes")
                .when()
                .get()
                .then()
              //.statusCode(HttpStatus.SC_NO_CONTENT)
                .extract().body().jsonPath().getList("",SimulacoesCreditoDTO.class);

    }

    public static Response pesquisarSimulacaoDeCreditoPorCPF(SimulacoesCreditoDTO requestSimulacaoCredito) {
       return    given()
                .basePath("simulacoes")
                .when()
                .get(requestSimulacaoCredito.getCpf())
                .then()
               .extract().response();

    }
}
