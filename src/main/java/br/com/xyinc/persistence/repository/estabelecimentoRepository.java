package br.com.xyinc.persistence.repository;

import br.com.xyinc.persistence.entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
public interface estabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
}
