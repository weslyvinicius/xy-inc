package br.com.xyinc.persistence.repository;

import br.com.xyinc.persistence.entity.Estabelecimento;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Criado por @autor wcardoso2  em 3/19/2019
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class EstabelecimentoRepositoryTest {

    @Autowired
    IEstabelecimentoRepository estabelecimentoRepository;

    @Before
    public void init(){
        List<Estabelecimento> estabelecimentos = Arrays.asList(
                buildClass("Lanchonete", 27, 12),
                buildClass("Posto", 31, 18),
                buildClass("Joalheiria", 15, 12),
                buildClass("Floricultura", 19, 21),
                buildClass("Pub", 12, 8),
                buildClass("Supermercado", 23, 6),
                buildClass("Churrascaria", 28, 2));
        estabelecimentoRepository.saveAll(estabelecimentos);
    }

    private Estabelecimento buildClass(final String nome, final Integer posicaoX, final Integer posicaoY){
        return Estabelecimento.builder()
                .nome(nome)
                .posicaoX(posicaoX)
                .posicaoY(posicaoY)
                .build();

    }

    @After
    public void delete(){
        estabelecimentoRepository.deleteAll();
    }

    @Test
    public void testFindAllEstabelecimentos(){
        List<Estabelecimento> listaEstabelecimetos = estabelecimentoRepository.findAll();

        assertEquals(listaEstabelecimetos.size(), 7);
    }

}
