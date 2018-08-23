Desafio Kernel

Amazon S3 <> API (Spring Boot) <> Web Interface <> Usuário

Para Executar esse projeto é necessário ter instalado as seguintes ferramentas:

    Java 8 JDK
    Maven 3
    NodeJS 8

O arquivo de configuração está em:

  - src/
    |- main/
    |  |- resources
    |  |  |- application.xml

Depois que instalar as ferramentas execute na pasta raiz do projeto:

$ mvn package

Execute ”$ mvn package” para verificar, testar e empacotar o codigo em um JAR. Além do JAR é feito o build da imagem no docker

O projeto possui dois Dockerfile. Após rodar o maven, é necessário entrar no diretório do portal-app e gerar outra imagem no docker
para o frontend em Angular.

Por último, quando tiver as duas imagens, a partir da raiz do projeto basta rodar docker-compose up
