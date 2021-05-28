package br.com.mercadolivre.restauranterefactorfilesdh.dtos;

import lombok.Data;

@Data
public class PratoDTO {

    private Integer id;
    private Double preco;
    private String descricao;
    private Integer quantidade;
}
