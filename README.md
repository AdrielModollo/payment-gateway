# payment-gateway

Este é o projeto payment-gateway, responsável pela comunicação com o RabbitMQ para envio e processamento de mensagens relacionadas a pagamentos. 
A aplicação é desenvolvida com Spring Boot e utiliza o RabbitMQ para enviar e receber mensagens, simulando um gateway de pagamentos. 
Para testar falhas, estipulei que pagamentos com valor superior a 500 serão recusados, enquanto pagamentos abaixo de 500 serão aprovados.
Ou seja, esse MS apenas precisa estar em execução, e ele fará todo trabalho, sem
interferência do usuário!

## Pré-requisitos

Antes de executar o projeto, certifique-se de que os seguintes pré-requisitos estão instalados:

- **Java 17** ou superior.
- **Maven** para gerenciamento de dependências e execução do build.
- **RabbitMQ** em funcionamento (local ou em contêiner Docker).



### Instalação do RabbitMQ (Opcional)
### Importante
Caso ainda não tenha instado o RabbitMQ e Docker consulte a documentação do MS de checkout!

Caso já tenha o RabbitMQ instalado localmente, você pode rodá-lo usando o Docker com o seguinte comando:

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
