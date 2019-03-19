package br.com.xyinc.service.impl;

import br.com.xyinc.persistence.entity.Estabelecimento;
import br.com.xyinc.persistence.repository.IEstabelecimentoRepository;
import br.com.xyinc.service.IEstabelecimentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
@Service
@Slf4j
public class EstabelecimentoService implements IEstabelecimentoService {

    @Autowired
    IEstabelecimentoRepository estabelecimentoRepository;

    @Override
    public List<Estabelecimento> listAll() {
        log.info("Inicio do metodo service de listar todos os estabelecimentos");
        return estabelecimentoRepository.findAll();
    }
}
