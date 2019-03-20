package br.com.xyinc.service.impl;

import br.com.xyinc.persistence.entity.Estabelecimento;
import br.com.xyinc.persistence.repository.IEstabelecimentoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Criado por @autor wcardoso2  em 3/19/2019
 **/
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EstabelecimentoServicoTest {

    @MockBean
    private IEstabelecimentoRepository estabelecimentoRepository;

    @Before
    public void setUp() throws Exception {
        BDDMockito.given(this.estabelecimentoRepository.findAll()).willReturn(new ArrayList<>());
        BDDMockito.given(this.estabelecimentoRepository.save(Mockito.any(Estabelecimento.class))).willReturn(new Estabelecimento());
        BDDMockito.given(this.estabelecimentoRepository.buscaEstabelecimentoPorRaio(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(new ArrayList<>());
    }

    @Test
    public void testListAll(){
        List<Estabelecimento> listaEstabelecimentos = estabelecimentoRepository.findAll();
        assertNotNull(listaEstabelecimentos);
    }

    @Test
    public void testSalvar(){
        BDDMockito.given(this.estabelecimentoRepository.findByPosicaoXAndPosicaoY(Mockito.anyInt(), Mockito.anyInt())).willReturn(null);
       Estabelecimento estabelecimento = estabelecimentoRepository.save(new Estabelecimento());
        assertNotNull(estabelecimento);
    }

    @Test
    public void testSalvarUpdate(){
        Estabelecimento estabelecimentoUpdate = new Estabelecimento();
        estabelecimentoUpdate.setNome("Teste1");

        BDDMockito.given(this.estabelecimentoRepository.findByPosicaoXAndPosicaoY(Mockito.anyInt(), Mockito.anyInt())).willReturn(estabelecimentoUpdate);
        Estabelecimento estabelecimento = estabelecimentoRepository.save(new Estabelecimento());
        assertNotNull(estabelecimento);
    }

    @Test
    public void testBuscaEstabelecimentosPorRaio(){
        final Integer posixaoX = 20;
        final Integer posicaoY = 10;
        final Integer distancia = 10;
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.buscaEstabelecimentoPorRaio(posixaoX, posicaoY, distancia);
        assertNotNull(estabelecimentos);
    }
}
