package br.com.mercadolivre.restauranterefactorfilesdh.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoDTO {

    private Integer id;
    private List<PratoDTO> pratos = new ArrayList<>();
    private boolean fechado = false;
}
