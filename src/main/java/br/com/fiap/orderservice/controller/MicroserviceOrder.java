package br.com.fiap.orderservice.controller;

import br.com.fiap.orderservice.dto.OrderDTO;
import br.com.fiap.orderservice.dto.OrderItemDTO;
import br.com.fiap.orderservice.dto.TransacaoPagamentoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@Slf4j
@RestController
public class MicroserviceOrder {

    private HashMap<String, OrderDTO> mapOrders = new HashMap<>();


    @GetMapping("/order/findById/{idPedido}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable(value="idPedido", required = true) String idPedido){
        System.out.println("**** ID PEDIDO: "+ idPedido);
        OrderDTO order = new OrderDTO();
        ResponseEntity res;

        try{
            if( mapOrders.containsKey(idPedido)){
                 order = mapOrders.get(idPedido);
                 res = new ResponseEntity(order, HttpStatus.OK);
            } else res = new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            res = new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return res;
    }

    @PostMapping("/order/save")
    public String salvarPedido(@RequestBody OrderDTO aOrder){

        String url = "http://localhost:8080/order/findById/";
        OrderDTO order = new OrderDTO();
        try{
            if( aOrder.getIdOrder() == null || aOrder.getIdOrder().equals("")){
                order = aOrder;
                //Gera um novo ID
                String idNovo = String.valueOf(Math.random());
                idNovo = idNovo.replace(".","");
                order.setIdOrder(idNovo);
                //Preenche o Id na URL de retorno
                url += order.getIdOrder();
                System.out.println("CR=order= "+ order.toString());
                mapOrders.put( order.getIdOrder(), order );
            }
        }catch(Exception e){
            System.out.println("Exceção: "+e.getMessage());
            url += null;
        }

        return url;
    }

    @PutMapping("/order/update/{id}")
    public String atualizarPedido(@PathVariable (value = "id", required = true) String id,
                                  @RequestBody OrderDTO aOrder){

        String url = "http://localhost:8080/order/findById/";
        OrderDTO order = new OrderDTO();
        try{
            if( id == null || id.equals("") )
                throw new Exception("Favor informar o Id do registro para alteração.");

            order = mapOrders.get( id );
            if( order == null ) throw new Exception("Pedido não encontrado!");

            //Atualiza atributos do order
            order.setShippingAddress(aOrder.getShippingAddress());
            order.setQuantidadeItens(aOrder.getQuantidadeItens());
            order.setPrecoTotal(new BigDecimal(aOrder.getPrecoTotal()));
            order.setFormaPagamento(aOrder.getFormaPagamento());
            //Atualiza atributos do OrderItem
            for(OrderItemDTO item : order.getLstItensPedido()) {
                for( OrderItemDTO aItem : aOrder.getLstItensPedido()) {
                    if( item.getDecricao().equalsIgnoreCase(aItem.getDecricao())) {
                        item.setDecricao(aItem.getDecricao());
                        item.setPrecoTotal(new BigDecimal(aItem.getPrecoTotal()));
                        item.setPrecoUnitario( new BigDecimal(aItem.getPrecoUnitario()));
                        item.setQuantidade(aItem.getQuantidade());
                    }
                }
            }
            //Atualiza atributos
            TransacaoPagamentoDTO pgto = order.getTransacao();
            TransacaoPagamentoDTO pgtoNovo = order.getTransacao();
            pgto.setBandeira(pgtoNovo.getBandeira());
            pgto.setDataValidadeCartao(pgtoNovo.getDataValidadeCartao());
            pgto.setId(pgtoNovo.getId());
            pgto.setNumeroCartao(pgtoNovo.getNumeroCartao());
        }catch(Exception e){
            System.out.println("Exceção: "+e.getMessage());
            url += null;
        }

        return url;
    }

    @DeleteMapping("/order/delete/{id}")
    public String excluirPedido(@PathVariable( value = "id", required = true) String id){
        String retorno = "";
        OrderDTO order = new OrderDTO();
        try{
            if( id == null && id.equals("") )
                throw new Exception("Favor informar o Id do registro para exclusão.");
            if( !mapOrders.containsKey(id) )
                throw new Exception("Id não consta da base de dados.");

            mapOrders.remove(id);
            retorno = "Registro " + id + " excluído com sucesso!";
        }catch(Exception e){
            retorno = "Exceção: "+e.getMessage();
            System.out.println(retorno);
        }

        return retorno;
    }

}
