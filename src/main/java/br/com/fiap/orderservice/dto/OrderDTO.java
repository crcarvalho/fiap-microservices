package br.com.fiap.orderservice.dto;

import lombok.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String email;
    private String nome;
    private String shippingAddress;
    private String idOrder;
    private Integer quantidadeItens;
    private BigDecimal precoTotal;
    private String formaPagamento;
    private TransacaoPagamentoDTO transacao;
    private String dtPedido;
    private String statusPedido;
    private List<OrderItemDTO> lstItensPedido;

    @Override
    public String toString() {
        return String.valueOf( "Pedido: { Email: " + getEmail()
                              +", Id: " + getIdOrder()
                              +", Nome: " + getNome()
                              +", Endereço: "+ getShippingAddress()
                              +", Preço total: "+ getPrecoTotal() +" }");
    }


    public void setPrecoTotal(BigDecimal value){
        this.precoTotal = value.setScale(2, BigDecimal.ROUND_UP);
    }

    public Double getPrecoTotal(){
        return this.precoTotal.doubleValue();
    }
}
