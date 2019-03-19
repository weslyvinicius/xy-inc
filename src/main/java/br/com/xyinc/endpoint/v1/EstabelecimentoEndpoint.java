package br.com.xyinc.endpoint.v1;

import br.com.xyinc.endpoint.v1.mapper.EstabelecimentoMapper;
import br.com.xyinc.service.IEstabelecimentoService;
import br.com.xyinc.service.impl.EstabelecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "Retorna a lista de todos os estabelecimentos cadastrados")
    @GetMapping(path = "estabelecimentos")
    public ResponseEntity<?> listAll() {
        log.info("Inicio do metodo de lista de Estabelecimentos");
        return new ResponseEntity<>(estabelecimentoMapper.convertListEstabecimentoEntityParaListEstabelecimentoDTO(estabelecimentoService.listAll()), HttpStatus.OK);
    }
}
