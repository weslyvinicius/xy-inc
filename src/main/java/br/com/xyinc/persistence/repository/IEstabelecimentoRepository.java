package br.com.xyinc.persistence.repository;

import br.com.xyinc.persistence.entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
@Repository
public interface IEstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

    Estabelecimento findByPosicaoXAndPosicaoY(final Integer posX, final Integer posY);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM estabelecimento e " +
           " WHERE power((e.posicao_x-(:posX)),2) + power(e.posicao_y-(:posY),2) <= POWER(:distancia, 2)", nativeQuery = true)
    List<Estabelecimento> buscaEstabelecimentoPorRaio( @Param("posX") Integer posicaoX, @Param("posY") Integer posicaoY, @Param("distancia") Integer distancia);
}
