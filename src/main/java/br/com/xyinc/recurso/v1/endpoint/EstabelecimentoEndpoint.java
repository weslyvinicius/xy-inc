package br.com.xyinc.recurso.v1.endpoint;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.exception.exception_custom.ValidacaoException;
import br.com.xyinc.persistence.entity.Estabelecimento;
import br.com.xyinc.recurso.v1.dto.EstabelecimentoDTO;
import br.com.xyinc.recurso.v1.dto.EstabelecimentoPorDistanciaDTO;
import br.com.xyinc.recurso.v1.mapper.IEstabelecimentoMapper;
import br.com.xyinc.service.IEstabelecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/

@Api(value = "API REST Estabecimento End Point")
@Slf4j
@RestController
@RequestMapping("v1/estabelecimentos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EstabelecimentoEndpoint {

    private final IEstabelecimentoService estabelecimentoService;

    private final IEstabelecimentoMapper estabelecimentoMapper;

    private ValidacaoException error;


    @ApiOperation(value = "Serviço que retorna todos os estabelecimentos cadastrados.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EstabelecimentoDTO> listAll() {
        log.info("Início do serviço de busca de todos os estabelecimentos");
        return estabelecimentoMapper.fromListEstabelecimentoDTO(estabelecimentoService.listAll());
    }

    @ApiOperation("Serviço de cadastro de um estabelecimento.")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public EstabelecimentoDTO cadastrar(@Valid @RequestBody EstabelecimentoDTO estabelecimentoDTO) {
        log.info("Início do serviço do cadastramento do estabelecimento: {}", estabelecimentoDTO);
        Estabelecimento estabelecimentoEntity = estabelecimentoService.salvar(estabelecimentoMapper.fromEntity(estabelecimentoDTO));
        return estabelecimentoMapper.fromEstabelecimentoDTO(estabelecimentoEntity);
    }

    @ApiOperation("Serviço de busca os estabelecimentos em um raio informando.")
    @GetMapping(value = "/{coordenadaX}/{coordenadaY}/{distancia}")
    public ResponseEntity<?> buscaEstabelecimentoPorRaio(@PathVariable(value = "coordenadaX") Integer coordenadaX,
                                                         @PathVariable("coordenadaY") Integer coordenadaY,
                                                         @PathVariable("distancia") Integer distancia) {
        log.info("Início do serviço busca os estabelecimento por um raio coordenadaX: {}", coordenadaX);
        log.info("Início do serviço busca os estabelecimento por um raio coordenadaY: {}", coordenadaY);
        log.info("Início do serviço busca os estabelecimento por um raio distancia: {}", distancia);

        validandoCordenadasEDistancia(coordenadaX, coordenadaY, distancia);
        EstabelecimentoPorDistanciaDTO estabelecimentoPorDistanciaDTO = EstabelecimentoPorDistanciaDTO.builder()
                                                                        .posicaoX(coordenadaX)
                                                                        .posicaoY(coordenadaY)
                                                                        .distanciaMaxima(distancia).build();
        List<Estabelecimento> listaEstabelecimentos = estabelecimentoService.buscaEstabelecimentosPorRaio(estabelecimentoPorDistanciaDTO);
        return new ResponseEntity<>(estabelecimentoMapper.fromListEstabelecimentoDTO(listaEstabelecimentos), HttpStatus.OK);
    }

    private void validandoCordenadasEDistancia( final Integer coordenadaX,
                                                final Integer coordenadaY,
                                                final Integer distancia) {
        log.info("Validanado os parametros do das cordenadas e distancia");

        error = new ValidacaoException();

        if ( coordenadaX < 0)
            error.addMessage("validacao_default.parametro_deve_ser_maior_que_zero", "coordenadaX");

        if (coordenadaY < 0)
            error.addMessage("validacao_default.parametro_deve_ser_maior_que_zero", "coordenadaY");

        if (distancia < 0)
            error.addMessage("validacao_default.parametro_deve_ser_maior_que_zero", "distancia");

        if (error.existemErros())
            throw error;

    }
}
