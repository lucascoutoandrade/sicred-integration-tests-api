package br.com.sicred.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SimulacoesCreditoDTO {

     private int id;
     private String nome;
     private String cpf;
     private String email;
     private float valor;
     private int parcelas;
     private boolean seguro;

}
