# Microserviço de Pedido

### Método GET

* PATH: [DOMINIO]/order/findById/{id}
* DESCRIÇÃO: Busca de pedido, item pedido e transação de pagamento atraves do id do pedido.


### Método POST

* PATH: [DOMINIO]/order/save
* DESCRIÇÃO: Criação de pedido com itens e transação de pagamento.
* PAYLOAD: 
```json
{
    "email":"STRING",
    "nome":"STRING",
    "shippingAddress" : "STRING",
    "quantidadeItens" : INTEGER,
    "precoTotal" : DOUBLE,
    "formaPagamento" : "STRING",
    "transacao" : {
    	"id" : "STRING",
    	"numeroCartao" : "STRING",
    	"dataValidadeCartao" : "STRING",
    	"bandeira" : "STRING"
    },
    "dtPedido" : "STRING",
    "statusPedido" : "STRING",
    "lstItensPedido":
    	[{
    		 "decricao" : "STRING",
		     "precoUnitario" : DOUBLE,
		     "quantidade" : INTEGER,
		     "precoTotal" : DOUBLE	
    	}] 
}
```
* FORMATO
	- dtPedido: "MM/DD/YYYY"
### Método PUT

* PATH: [DOMINIO]/order/update/{id}
* DESCRIÇÃO: Atualização do pedido com itens e transação de pagamento.
* PAYLOAD: 
```json
{
    "idOrder" : "",
    "shippingAddress" : "STRING",
    "quantidadeItens" : INTEGER,
    "precoTotal" : DOUBLE,
    "formaPagamento" : "STRING",
    "lstItensPedido":
    	[{
    		 "decricao" : "STRING",
		 "precoUnitario" : DOUBLE,
		 "quantidade" : INTEGER,
		 "precoTotal" : DOUBLE	
    	}] 
}
```
### Método DELETE

* PATH: [DOMINIO]/order/delete/{id}
* DESCRIÇÃO: Exclui um pedido seus itens e transação atraves do ID do pedido.


