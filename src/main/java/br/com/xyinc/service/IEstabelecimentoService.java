package br.com.xyinc.service;

import br.com.xyinc.persistence.entity.Estabelecimento;

import java.util.List;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
public interface IEstabelecimentoService {

    /**
     * Serviço de busca todos os estabelecimentos cadastrados.
     * @return Lista de Estabelecimentos
     */
    List<Estabelecimento> listAll();
}
