package br.com.mercadolivre.restauranterefactorfilesdh.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MesaDTO {

    private Integer id;
    private List<PedidoDTO> pedidos = new ArrayList<>();
}
