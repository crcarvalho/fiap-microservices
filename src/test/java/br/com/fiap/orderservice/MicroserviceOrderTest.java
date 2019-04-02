package br.com.fiap.orderservice;

import br.com.fiap.orderservice.controller.MicroserviceOrder;
import br.com.fiap.orderservice.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class MicroserviceOrderTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MicroserviceOrder repository;

    @Test
    public void notFoundUser() throws Exception {
        String idPedido = "123456";
        mvc.perform( this.repository.findOrderById(idPedido)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
