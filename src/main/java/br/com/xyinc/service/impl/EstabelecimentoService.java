package br.com.xyinc.service.impl;

import br.com.xyinc.persistence.entity.Estabelecimento;
import br.com.xyinc.persistence.repository.IEstabelecimentoRepository;
import br.com.xyinc.recurso.v1.dto.EstabelecimentoPorDistanciaDTO;
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
        log.info("Início do metodo service de listar todos os estabelecimentos");
        return estabelecimentoRepository.findAll();
    }

    @Override
    public Estabelecimento salvar(final Estabelecimento estabelecimento) {
        log.info("Início do metodo service salvar um estabelecimento");

        Estabelecimento estabelecimentoSalvo;

        log.info("Verifica se o estabelecimento já existe na mesma posicao X e Y");
        Estabelecimento estabelecimentoJaExitente =  estabelecimentoRepository.findByPosicaoXAndPosicaoY(estabelecimento.getPosicaoX(), estabelecimento.getPosicaoY() );
        if (estabelecimentoJaExitente != null){
            log.info("Caso já exista o cadastro na mesma posicao atualizado o estabelecimento.");
            estabelecimentoJaExitente.setNome(estabelecimento.getNome());
            estabelecimentoSalvo = estabelecimentoRepository.save(estabelecimentoJaExitente);
        }else{
            estabelecimentoSalvo = estabelecimentoRepository.save(estabelecimento);
        }

        return estabelecimentoSalvo;
    }

    @Override
    public List<Estabelecimento> buscaEstabelecimentosPorRaio(final EstabelecimentoPorDistanciaDTO estabelecimentoPorDistanciaDTO) {
        log.info("Início do metodo service de busca estabelecimento por raio");
        return estabelecimentoRepository.buscaEstabelecimentoPorRaio(estabelecimentoPorDistanciaDTO.getPosicaoX(), estabelecimentoPorDistanciaDTO.getPosicaoY(), estabelecimentoPorDistanciaDTO.getDistanciaMaxima());
    }
}
