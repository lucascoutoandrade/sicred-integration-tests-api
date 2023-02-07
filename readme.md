# Teste Técnico Sicred Sobre Testes de API

Este projeto foi elaborado pensando em garantir uma cobertura de testes para a API (SIMULAÇÃO DE CRÉDITO).

##  Requisitos
* Java 11+ JDK deve estar instalado
* Maven deve estar instalado e configurado no path da aplicação
* Instalar o plugin do lombok na IDE
* Ter Aplicação local na maquina

##  Pre-requisitos

Levantar a aplicação local antes de executar os testes <br>

Comandos para subir aplicação local: <br>
```bash
mvn clean spring-boot:run
```
ou se optar por outra porta <br>
```bash
mvn clean spring-boot:run -Dserver.port=8888
```

## Documentacão técnica da aplicação

A documentação técnica da API está disponível através do OpenAPI/Swagger em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## :open_file_foder: Estrutura do projeto

````text

|__src (java root path projeto)
    |__main
    |  |__java
    |  |  |__sicred
    |  |    |__client
    |  |    |__core
    |  |    |__dto
    |  |___resources (contém arquivos de configuração caso haja)
    | 
    |__test
    |    |__sicred
    |      |__stub
    |      |__tests
    |__pom.xml (Contém as dependências relacionada ao projeto)
    |__.gitignore (Contém o diretório de arquivos que devem ser ignorados no momento do commit)   
````

## Como executar os testes
É Possivel executar pela propria IDE, selecionado qual Teste deseja executar e simplismente dar play no método <br>
do teste ou podera executar pela linha de comando atraves do comando:<br>

Rodar todos os testes<br>
```bash
mvn test 
```
Rodar teste especifico utilizando o nome da Tag<br>
```bash
mvn test -Dgroups="cadastrar_com_email_invalido"
```
Rodas diversas Tags
```bash
mvn test  -Dgroups="add-cliente, excluir-cliente"
```
Não excutar alguma Tag
```bash
mvn test -DexcludedGroups="bug"
```
