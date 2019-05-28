[![Build Status](https://travis-ci.com/weslyvinicius/xy-inc.svg?branch=master)](https://travis-ci.com/weslyvinicius/xy-inc)
[![Coverage Status](https://coveralls.io/repos/github/weslyvinicius/xy-inc/badge.svg?branch=master)](https://coveralls.io/github/weslyvinicius/xy-inc?branch=master)
[![Known Vulnerabilities](https://snyk.io/test/github/weslyvinicius/xy-inc/badge.svg)](https://snyk.io/test/github/weslyvinicius/xy-inc)
[![GitHub version](https://badge.fury.io/gh/weslyvinicius%2Fxy-inc.svg)](https://badge.fury.io/gh/weslyvinicius%2Fxy-inc)

# XY Inc | Pontos de Interesse (POIs) #

Plataforma para disponibilização de um dispositivo inovador que auxilia pessoas para encontrar pontos de interesse (POIs).
A mesma foi desenvolvida em uma arquitetura de serviços utilizando os principios básicos do [REST](https://pt.wikipedia.org/wiki/REST).

O Serviço de backend é composto de web services baseados na implementação do [Spring RESTful](https://spring.io/guides/gs/rest-service/) utilizando o JSON como trafego de informações.

### Dependências ###    
As seguintes dependências são necessárias:

- [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (versão 8)
- [Maven](https://maven.apache.org/) (versão 3.3 ou maior)
- [MySQL](https://www.mysql.com/) - servidor de banco de dados;
- [H2](http://www.h2database.com/html/main.html) - servidor de banco de dados de teste;
- [Docker](https://www.docker.com/) - container
- [Swagger](https://swagger.io/) - Gerador da documentação da API 
- [Flyway](https://flywaydb.org/) - Contole de versão do banco de dados 
- [Lombok](https://projectlombok.org/) - Framework para diminuir a verbosida das classes java
- [Spring Boot](https://spring.io/projects/spring-boot) - Framework Spring

### Integração Continua ###
- [TravesCI](https://travis-ci.com/weslyvinicius/xy-inc) 


### Instalando a aplicação ###
Execute o comando para clonar (copiar) o projeto:

    git clone https://github.com/weslyvinicius/xy-inc.git

### Configurando o banco de dados ###
Para configurar os banco de dados na aplicação, o local 
onde se encontra o projeto e digite o comando:

    docker-compose up -d
    
### Inicializando a aplicação ###

Acesse a pasta do projeto copiado por linha de comando;

Inicie a aplicação com o comando (nesse momento não executa testes):

    mvn spring-boot:run

Após a inicialização do servidor, a aplicação será acessível na URL:

    http://localhost:8080/
    
#### API REST ####

A API REST do servidor expõe o seguinte serviço abaixo e também 
pode ser vistos pela documentação Swagger.iu pelo navegador web 
utilizando a URL: 

    http://localhost:8080/swagger-ui.html 
    
##### Pontos de Interesse #####

Url           |Verb          |Request Body  | Description
--------------|------------- |------------- | -------------
/v1/estabelecimentos      |GET          |void|lista todos os pontos de interesse cadastrado na aplicação.
/v1/estabelecimentos|POST|JSON| cria um novo ponto de interesse.
/v1/estabelecimentos?{coordenadaX}/{coordenadaY}/{distancia}|GET|void| busca pontos de interesse baseado em uma localização `x`, `y` e uma distância máxima (`dMax`).

##### Tipos de Respostas ##### 

| Código | Nome                   | Descrição                                                            |
|--------|------------------------|----------------------------------------------------------------------| 
|200     | OK                     | Indica que a operação foi realizada com sucesso.                     |
|400     | Bad Request            | Indica que os parâmetros fornecidos estão incorretos.                |
|404     | Not Found              | Indica que o recurso solicitado não foi localizado.                  |
|500     | Internal Server Error  | Indica que ocorreu algum erro interno no processamento da requisição |
