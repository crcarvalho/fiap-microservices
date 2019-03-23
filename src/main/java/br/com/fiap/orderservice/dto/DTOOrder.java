package br.com.fiap.orderservice.dto;

import lombok.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DTOOrder {

    private String email;
    private String nome;
    private String shippingAddress;
    private String idOrder;
    private Integer quantidadeItens;
    private Double precoTotal;
    private String formaPagamento;
    private DTOTransacaoPagamento transacao;
    private String dtPedido;
    private String statusPedido;
    private List<DTOOrderItem> lstItensPedido;

    @Override
    public String toString() {
        return String.valueOf( "Pedido: { Email: " + getEmail()
                              +", Id: " + getIdOrder()
                              +", Nome: " + getNome()
                              +", Endereço: "+ getShippingAddress()
                              +", Preço total: "+ getPrecoTotal() +" }");
    }
}
