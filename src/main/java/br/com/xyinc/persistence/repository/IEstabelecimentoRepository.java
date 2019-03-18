package br.com.xyinc.persistence.repository;

import br.com.xyinc.persistence.entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
@Repository
public interface IEstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
}
