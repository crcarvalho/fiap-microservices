package br.com.fiap.orderservice.dto;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private String decricao;
    private Double precoUnitario;
    private Integer quantidade;
    private Double precoTotal;

}
