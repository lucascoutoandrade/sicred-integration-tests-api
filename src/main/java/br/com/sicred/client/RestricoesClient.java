package br.com.sicred.client;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestricoesClient {

    public static Response validarPesquisaDeClientesComRestricoesPeloCPF(String clienteRestrito) {
      return  given().filter(new AllureRestAssured())
              .contentType(ContentType.JSON)
                .basePath("restricoes")
                .when()
                .get(clienteRestrito)
                .then()
                .extract().response();


    }





}
