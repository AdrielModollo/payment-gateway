# payment-gateway

Este é o projeto **payment-gateway**, que implementa a comunicação com RabbitMQ para enviar e processar mensagens relacionadas a pagamentos. A aplicação é construída com **Spring Boot** e utiliza **RabbitMQ** para o envio e recebimento de mensagens.

## Pré-requisitos

Antes de executar o projeto, certifique-se de que os seguintes pré-requisitos estão instalados:

- **Java 17** ou superior.
- **Maven** para gerenciamento de dependências e execução do build.
- **RabbitMQ** em funcionamento (local ou em contêiner Docker).

## Importante
Caso já tenha instalado e configurado o rabbitMq com Docker, você pode ignorar estes passos!

### Instalação do RabbitMQ (Opcional)
Caso não tenha o RabbitMQ instalado localmente, você pode rodá-lo usando o Docker com o seguinte comando:

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
```

### Como executar o projeto
1. Construir o projeto
O comando a seguir limpa e instala as dependências do projeto, garantindo que tudo esteja preparado para execução:

```
bash
mvn clean install
```

Se você já tiver as dependências instaladas, pode usar:

```
bash
mvn install
```

### 2. Executar o projeto
Após a instalação das dependências, você pode executar o projeto através da classe principal PaymentGatewayApplication:

Classe principal: PaymentGatewayApplication
Comando para executar (a partir da raiz do projeto):

```
bash
mvn spring-boot:run
```

Ou, se preferir, execute diretamente pela IDE (IntelliJ, Eclipse, etc.):

Abra o projeto na IDE.
Localize a classe PaymentGatewayApplication no pacote com.develcode.payment_gateway.
Execute a classe como uma aplicação Java (com o método main).

### 3. Verificar se o RabbitMQ está configurado corretamente
A aplicação envia e consome mensagens de RabbitMQ nas filas checkout e payment. Certifique-se de que o RabbitMQ está em funcionamento e que as filas estão criadas corretamente.

Você pode acessar o painel de administração do RabbitMQ no seguinte endereço:

http://localhost:15672

O usuário e senha padrão são guest e guest.

### 4. Testes Unitários
Para rodar os testes unitários, utilize o seguinte comando:

```
bash
mvn test
```

Gerar testes com coverage
```
bash
mvn verify
```
Acessar através do browser pasta target/site/index.html 

### 5. Logs e Monitoramento
Os logs de execução da aplicação serão exibidos no terminal. Para monitorar as mensagens enviadas e recebidas nas filas, acesse o painel de administração do RabbitMQ.

### Estrutura do Projeto
/src/main/java/com/develcode/payment_gateway: Contém as classes principais do projeto, incluindo o produtor e o consumidor de mensagens RabbitMQ.
/src/test/java/com/develcode/payment_gateway: Contém os testes unitários e de integração para garantir o correto funcionamento da aplicação.

### Funcionalidade
A aplicação realiza o envio de mensagens de status de pagamento através de RabbitMQ para a fila payment. Quando uma mensagem é recebida na fila checkout, a aplicação verifica o status do pagamento e envia uma resposta para a fila payment com o status de "payment_fail" ou "payment_success", dependendo do resultado.

### Fluxo da Aplicação
A fila checkout recebe mensagens com informações sobre o pagamento.
O PaymentListener consome as mensagens da fila checkout, verifica o status do pagamento e envia a resposta para a fila payment.
O PaymentProducer envia a mensagem para a fila payment com o status atualizado.
