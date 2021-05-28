package br.com.mercadolivre.restauranterefactorfilesdh.services;

import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoFinalizadoDTO;

import java.util.List;

public interface PedidoService {

    public List<PedidoFinalizadoDTO> retornaPedidosPorMesa(Integer id);
    public PedidoFinalizadoDTO retornaPedido(Integer id);
    public void inserePedido(PedidoDTO pedido, Integer id);
}
