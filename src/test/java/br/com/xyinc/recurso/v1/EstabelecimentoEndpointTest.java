package br.com.xyinc.recurso.v1;

import br.com.xyinc.persistence.entity.Estabelecimento;
import br.com.xyinc.recurso.v1.dto.EstabelecimentoPorDistanciaDTO;
import br.com.xyinc.service.IEstabelecimentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Criado por @autor wcardoso2  em 3/19/2019
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EstabelecimentoEndpointTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IEstabelecimentoService estabelecimentoService;

    private static final String URL_TODOS_OS_ESTABELECIMENTO = "/v1/estabelecimentos";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Before
    public void init(){

        List<Estabelecimento> estabelecimentos = Arrays.asList(
                buildClass("Lanchonete", 27, 12),
                buildClass("Posto", 31, 18),
                buildClass("Joalheria", 15, 12));


        BDDMockito.given(estabelecimentoService.listAll()).willReturn(estabelecimentos);
        BDDMockito.given(estabelecimentoService.salvar(Mockito.any(Estabelecimento.class))).willReturn(new Estabelecimento());
        BDDMockito.given(estabelecimentoService.buscaEstabelecimentosPorRaio( Mockito.any(EstabelecimentoPorDistanciaDTO.class))).willReturn(estabelecimentos);
    }

    private Estabelecimento buildClass(final String nome, final Integer posicaoX, final Integer posicaoY){
        return Estabelecimento.builder()
                .nome(nome)
                .posicaoX(posicaoX)
                .posicaoY(posicaoY)
                .build();

    }

    @Test
    public void testListAll() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(URL_TODOS_OS_ESTABELECIMENTO)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    public void testCadastrar() throws Exception {

        mvc.perform(post(URL_TODOS_OS_ESTABELECIMENTO)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(serializeToString(buildClass("Teste 1", 80, 80))))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testCadastrarError() throws Exception {

        mvc.perform(post(URL_TODOS_OS_ESTABELECIMENTO)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(serializeToString(buildClass(null, null, null))))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testBuscaEstabelecimentoPorRaio() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(URL_TODOS_OS_ESTABELECIMENTO)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(3)));

    }



    public static String serializeToString(Object objectToSerialize) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(objectToSerialize);
    }

}
