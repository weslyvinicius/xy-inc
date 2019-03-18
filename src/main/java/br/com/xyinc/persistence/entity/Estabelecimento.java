package br.com.xyinc.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Estabelecimento extends AbstractEntity {

    private String nome;

    private Long posicaoX;

    private Long posicaoY;

    @Builder
    public Estabelecimento(Long id, String nome, Long posicaoX, Long posicaoY) {
        this.id = id;
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }
}
