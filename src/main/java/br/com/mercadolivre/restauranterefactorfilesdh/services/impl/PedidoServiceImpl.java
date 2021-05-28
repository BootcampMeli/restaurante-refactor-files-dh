package br.com.mercadolivre.restauranterefactorfilesdh.services.impl;

import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoFinalizadoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.repositories.PedidoRepositorie;
import br.com.mercadolivre.restauranterefactorfilesdh.services.PedidoService;

import java.util.List;

public class PedidoServiceImpl implements PedidoService {

    @Override
    public List<PedidoFinalizadoDTO> retornaPedidosPorMesa(Integer id) {
        PedidoRepositorie pedidoRepositorie = new PedidoRepositorie();
        return pedidoRepositorie.retornaPedidosPorMesa(id);
    }

    @Override
    public PedidoFinalizadoDTO retornaPedido(Integer id) {
        PedidoRepositorie pedidoRepositorie = new PedidoRepositorie();
        return pedidoRepositorie.retornaPedido(id);
    }

    @Override
    public void inserePedido(PedidoDTO pedido, Integer id) {
        PedidoRepositorie pedidoRepositorie = new PedidoRepositorie();
        pedidoRepositorie.inserePedido(pedido, id);
    }
}
