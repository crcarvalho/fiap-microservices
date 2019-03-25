package br.com.fiap.orderservice.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private String decricao;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    private BigDecimal precoTotal;


    public void setPrecoTotal(BigDecimal value){
        this.precoTotal = value.setScale(2, BigDecimal.ROUND_UP);
    }

    public Double getPrecoTotal(){
        return this.precoTotal.doubleValue();
    }


    public void setPrecoUnitario(BigDecimal value){
        this.precoTotal = value.setScale(2, BigDecimal.ROUND_UP);
    }

    public Double getPrecoUnitario(){
        return this.precoTotal.doubleValue();
    }

}
