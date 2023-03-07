package br.com.sicred.client;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestricoesClient {
    private static final String ENDPOINT_RETRICOES = "restricoes";

    public static Response validarPesquisaDeClientesComRestricoesPeloCPF(String clienteRestrito) {
      return  given().filter(new AllureRestAssured())
              .contentType(ContentType.JSON)
                .basePath(ENDPOINT_RETRICOES)
                .when()
                .get(clienteRestrito)
                .then()
                .extract().response();


    }





}
