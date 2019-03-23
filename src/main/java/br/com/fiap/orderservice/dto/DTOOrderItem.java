package br.com.fiap.orderservice.dto;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DTOOrderItem {

    private String decricao;
    private Double precoUnitario;
    private Integer quantidade;
    private Double precoTotal;

}
