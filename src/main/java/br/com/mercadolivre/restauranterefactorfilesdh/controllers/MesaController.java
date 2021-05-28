package br.com.mercadolivre.restauranterefactorfilesdh.controllers;

import br.com.mercadolivre.restauranterefactorfilesdh.dtos.MesaDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoFinalizadoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.services.PedidoService;
import br.com.mercadolivre.restauranterefactorfilesdh.services.impl.PedidoServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/mesa")
public class MesaController {

    PedidoService pedidoService = new PedidoServiceImpl();

    @GetMapping("/{id}")
    public List<PedidoFinalizadoDTO> retornaPedidosPorMesa(@PathVariable Integer id) {
        return pedidoService.retornaPedidosPorMesa(id);
    }

}
