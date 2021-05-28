package br.com.mercadolivre.restauranterefactorfilesdh.controllers;

import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoFinalizadoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.services.PedidoService;
import br.com.mercadolivre.restauranterefactorfilesdh.services.impl.PedidoServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    PedidoService pedidoService = new PedidoServiceImpl();

    @GetMapping("/{id}")
    public PedidoFinalizadoDTO retornaPedido(@PathVariable Integer id){
        return pedidoService.retornaPedido(id);
    }

    @PostMapping("/{id}")
    public void inserePedidoNaMesa(@RequestBody PedidoDTO pedido, @PathVariable Integer id){
        pedidoService.inserePedido(pedido, id);
    }

}
