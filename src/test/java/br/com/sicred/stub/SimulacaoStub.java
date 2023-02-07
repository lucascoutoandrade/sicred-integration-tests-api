package br.com.sicred.stub;

import br.com.sicred.core.Enums.ParcelasCredito;
import br.com.sicred.core.Enums.ValoresCredito;
import br.com.sicred.dto.SimulacoesCreditoDTO;
import net.datafaker.Faker;


public class SimulacaoStub {


    public static SimulacoesCreditoDTO criarSimulacaoCreditoValida() {
        Faker faker = new Faker();
        return SimulacoesCreditoDTO.builder()
                .nome(Faker.instance().name().fullName())
                .cpf(faker.cpf().valid().replaceAll("[^0-9a-zA-Z]+",""))
                .email(faker.internet().emailAddress())
                .valor(Faker.instance().number().numberBetween(ValoresCredito.VALOR_DO_CREDITO_MIN_PERMITIDO.get(), ValoresCredito.VALOR_DO_CREDITO_MAX_PERMITIDO.get()))
                .parcelas(Faker.instance().number().numberBetween(ParcelasCredito.NUMERO_PARCELAS_MIN_PERMITIDO.get(), ParcelasCredito.NUMERO_PARCELAS_MAX_PERMITIDO.get()))
                .seguro(true).build();
    }

    public static SimulacoesCreditoDTO criarSimulacaoCreditoSemInformarAtributoObrigatorio(String atributo) {
        Faker faker = new Faker();

        switch (atributo.toUpperCase()) {
            case "CPF":
                return SimulacoesCreditoDTO.builder()
                        .nome(Faker.instance().name().fullName())
                        .email(faker.internet().emailAddress())
                        .valor(Faker.instance().number().numberBetween(ValoresCredito.VALOR_DO_CREDITO_MIN_PERMITIDO.get(), ValoresCredito.VALOR_DO_CREDITO_MAX_PERMITIDO.get()))
                        .parcelas(Faker.instance().number().numberBetween(ParcelasCredito.NUMERO_PARCELAS_MIN_PERMITIDO.get(), ParcelasCredito.NUMERO_PARCELAS_MAX_PERMITIDO.get()))
                        .seguro(true).build();


            case "EMAIL":
                return SimulacoesCreditoDTO.builder()
                        .nome(Faker.instance().name().fullName())
                        .cpf(faker.cpf().valid())
                        .valor(Faker.instance().number().numberBetween(ValoresCredito.VALOR_DO_CREDITO_MIN_PERMITIDO.get(), ValoresCredito.VALOR_DO_CREDITO_MAX_PERMITIDO.get()))
                        .parcelas(Faker.instance().number().numberBetween(ParcelasCredito.NUMERO_PARCELAS_MIN_PERMITIDO.get(), ParcelasCredito.NUMERO_PARCELAS_MAX_PERMITIDO.get()))
                        .seguro(true).build();


            case "VALOR":
                return SimulacoesCreditoDTO.builder()
                        .nome(Faker.instance().name().fullName())
                        .cpf(faker.cpf().valid())
                        .email(faker.internet().emailAddress())
                        .parcelas(Faker.instance().number().numberBetween(ParcelasCredito.NUMERO_PARCELAS_MIN_PERMITIDO.get(), ParcelasCredito.NUMERO_PARCELAS_MAX_PERMITIDO.get()))
                        .seguro(true).build();

            case "NOME":
                return SimulacoesCreditoDTO.builder()
                        .cpf(faker.cpf().valid())
                        .email(faker.internet().emailAddress())
                        .valor(Faker.instance().number().numberBetween(ValoresCredito.VALOR_DO_CREDITO_MIN_PERMITIDO.get(), ValoresCredito.VALOR_DO_CREDITO_MAX_PERMITIDO.get()))
                        .parcelas(Faker.instance().number().numberBetween(ParcelasCredito.NUMERO_PARCELAS_MIN_PERMITIDO.get(), ParcelasCredito.NUMERO_PARCELAS_MAX_PERMITIDO.get()))
                        .seguro(true).build();

            case "PARCELAS":
                return SimulacoesCreditoDTO.builder()
                        .nome(Faker.instance().name().fullName())
                        .cpf(faker.cpf().valid())
                        .email(faker.internet().emailAddress())
                        .valor(Faker.instance().number().numberBetween(ValoresCredito.VALOR_DO_CREDITO_MIN_PERMITIDO.get(), ValoresCredito.VALOR_DO_CREDITO_MAX_PERMITIDO.get()))
                        .seguro(true).build();


            case "SEGURO":
                return SimulacoesCreditoDTO.builder()
                        .cpf(Faker.instance().cpf().valid())
                        .nome(Faker.instance().name().fullName())
                        .email(faker.internet().emailAddress())
                        .valor(Faker.instance().number().numberBetween(ValoresCredito.VALOR_DO_CREDITO_MIN_PERMITIDO.get(), ValoresCredito.VALOR_DO_CREDITO_MAX_PERMITIDO.get()))
                        .parcelas(Faker.instance().number().numberBetween(ParcelasCredito.NUMERO_PARCELAS_MIN_PERMITIDO.get(), ParcelasCredito.NUMERO_PARCELAS_MAX_PERMITIDO.get()))
                        .build();


        }
        return null;
    }
}
