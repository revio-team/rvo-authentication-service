# Serviço de Autenticação e Autorização

> Responsável por lidar com criação de usuarios, autenticação e autorização do cliente

## Como rodar
1. baixar gradle
2. baixar java
3. baixar docker
   1. comandos docker:
   ```shell
   $ docker system prune
   $ docker build --no-cache -t revio/authentication-v1 .
   $ docker run -e MONGO_URI=$MONGO_URI -e JWT_SECRET=$JWT_SECRET -p 8080:8080 revio/authentication-v1
    ```
   
Disclamer:
esse passo a passo não está elúcido suficiente para você baixar e sair rodando.
serão necessárias outras possíveis configurações mas que, no momento, não consigo adicionar.


qualquer dúvida, só abrir um issue :) 