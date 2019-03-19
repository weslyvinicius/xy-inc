package br.com.xyinc.recurso.v1.endpoint;

import br.com.xyinc.exception.exception_custom.ValidacaoException;
import br.com.xyinc.persistence.entity.Estabelecimento;
import br.com.xyinc.recurso.v1.dto.EstabelecimentoDTO;
import br.com.xyinc.recurso.v1.dto.EstabelecimentoPorDistanciaDTO;
import br.com.xyinc.recurso.v1.mapper.EstabelecimentoMapper;
import br.com.xyinc.service.IEstabelecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/

@Api(value = "API REST Estabecimento End Point")
@RestController
@RequestMapping("v1/estabelecimentos")
@Slf4j
public class EstabelecimentoEndpoint {

    @Autowired
    private IEstabelecimentoService estabelecimentoService;

    @Autowired
    private EstabelecimentoMapper estabelecimentoMapper;

    private ValidacaoException error;

    @ApiOperation(value = "Serviço que retorna todos os estabelecimentos cadastrados.")
    @GetMapping
    public ResponseEntity<?> listAll() {
        log.info("Início do serviço de busca de todos os estabelecimentos");
        return new ResponseEntity<>(estabelecimentoMapper.convertListEstabecimentoEntityParaListEstabelecimentoDTO(estabelecimentoService.listAll()), HttpStatus.OK);
    }

    @ApiOperation("Serviço de cadastro de um estabelecimento.")
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody EstabelecimentoDTO estabelecimentoDTO) {
        log.info("Início do serviço do cadastramento do estabelecimento: {}", estabelecimentoDTO);
        Estabelecimento estabelecimentoEntity = estabelecimentoService.salvar(estabelecimentoMapper.convertEstabelecimentoDTOParaEstabelecimentoEntity(estabelecimentoDTO));
        return new ResponseEntity<>(estabelecimentoMapper.convertEstabelecimentoEntityParaEstabelecimentoDTO(estabelecimentoEntity), HttpStatus.OK);
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
        return new ResponseEntity<>(estabelecimentoMapper.convertListEstabecimentoEntityParaListEstabelecimentoDTO(listaEstabelecimentos), HttpStatus.OK);
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
