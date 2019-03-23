package br.com.fiap.orderservice.controller;

import br.com.fiap.orderservice.dto.DTOOrder;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
public class MicroserviceOrder {

    private HashMap<String, DTOOrder> mapOrders = new HashMap<>();


    @GetMapping("/order/findById/{idPedido}")
    public DTOOrder findOrderById(@PathVariable(value="idPedido", required = true) String idPedido){
        System.out.println("**** ID PEDIDO: "+ idPedido);
        DTOOrder order = new DTOOrder();

        try{
            if( mapOrders.containsKey(idPedido)){
                 order = mapOrders.get(idPedido);
            }
        }catch(Exception e){

        }

        return order;
    }

    @PostMapping("/order/save")
    public String salvarPedido(@RequestBody DTOOrder aOrder){

        String url = "http://localhost:8080/order/findById/";
        DTOOrder order = new DTOOrder();
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

    @PutMapping("/order/update")
    public String atualizarPedido(@RequestBody DTOOrder aOrder){

        String url = "http://localhost:8080/order/findById/";
        DTOOrder order = new DTOOrder();
        try{
            if( aOrder.getIdOrder() == null || aOrder.getIdOrder().equals("") )
                throw new Exception("Favor informar o Id do registro para alteração.");

            order = aOrder;
            //Gera um novo ID
            String idNovo = String.valueOf(Math.random());
            idNovo = idNovo.replace(".","");
            order.setIdOrder(idNovo);
            //Preenche o Id na URL de retorno
            url += order.getIdOrder();
            System.out.println("CR=order= "+ order.toString());
            mapOrders.put( order.getIdOrder(), order );

        }catch(Exception e){
            System.out.println("Exceção: "+e.getMessage());
            url += null;
        }

        return url;
    }

    @DeleteMapping("/order/delete")
    public String excluirPedido(@PathVariable( value = "id", required = true) String id){
        String retorno = "";
        DTOOrder order = new DTOOrder();
        try{
            if( id == null && id.equals("") )
                throw new Exception("Favor informar o Id do registro para exclusão.");
            if( mapOrders.containsKey(id) )
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
