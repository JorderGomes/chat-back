# Real-Time Chat Engine 🚀

Este é o motor de backend para o sistema de chat privado, focado em alta disponibilidade, segurança rigorosa e persistência eficiente.

## 🏗️ Arquitetura e Decisões Técnicas

A estrutura foi desenhada seguindo os princípios de **Clean Architecture** e **S.O.L.I.D.**, dividida em camadas de Controller, Service e Repository.

### 1. Mensageria com STOMP e WebSockets

Diferente de requisições HTTP comuns, o backend implementa um **Message Broker** interno:

* **Protocolo STOMP:** Utilizado para fornecer uma semântica de "Publish/Subscribe". Isso permite que o backend não apenas receba mensagens, mas as direcione para tópicos específicos (`/topic/room/{id}`).
* **SimpMessagingTemplate:** Em vez de depender apenas de anotações `@SendTo`, utilizamos o template para ter controle granular sobre o destino das mensagens após o processamento no Service.

### 2. Segurança Stateless com Spring Security + JWT

Para garantir escalabilidade (facilidade de rodar múltiplas instâncias no futuro):

* **Autenticação Stateless:** O servidor não armazena sessões. Cada requisição REST ou Handshake de WebSocket deve conter um JWT válido.
* **Security Filter:** Um filtro customizado intercepta as requisições, valida o token via `TokenService` e injeta o contexto de autenticação no Spring Security.
* **Criptografia:** Senhas são armazenadas utilizando **BCrypt**, garantindo proteção contra ataques de dicionário.

### 3. Lógica de Salas Privadas (Room Management)

A inteligência do chat privado reside no `ChatRoomService`:

* **Find or Create:** O sistema evita a criação de salas duplicadas. Ele busca uma combinação única entre `User A` e `User B` para gerar um `roomId` consistente, permitindo que o histórico seja compartilhado entre ambos de forma persistente.

## 🚀 Boas Práticas Utilizadas

* **Java Records (DTOs):** Uso extensivo de `record` para DTOs (Data Transfer Objects), garantindo imutabilidade e reduzindo o código "boilerplate".
* **Paginação de Histórico:** A API de mensagens (`/api/messages/{roomId}`) utiliza `Pageable` do Spring Data, evitando sobrecarga de memória ao carregar milhares de mensagens de uma vez.
* **Tratamento de Exceções:** Implementação de respostas estruturadas para erros de autenticação e busca de recursos.
* **Injeção de Dependência:** Uso rigoroso de injeção via `@Autowired` em serviços desacoplados, facilitando testes unitários.

## 🛠️ Tecnologias

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Segurança:** Spring Security + JWT (auth0)
* **Persistência:** Spring Data JPA + Hibernate
* **Mensageria:** Spring WebSocket (STOMP)

## 📡 Fluxo de Dados

1. **Auth:** O cliente envia credenciais para `/auth/login` e recebe um JWT + dados do usuário.
2. **Handshake:** O cliente solicita upgrade de protocolo (HTTP para WS). O backend valida o token no header.
3. **Chat Init:** O cliente chama o endpoint REST `/api/rooms/find-or-create` para saber em qual tópico se inscrever.
4. **Processing:**
* O `@MessageMapping` recebe o `MsgInputDTO`.
* O `MsgService` converte o DTO para a Entidade `Msg`, associa ao usuário e sala, e persiste no banco.
* O `messagingTemplate` despacha a mensagem salva (com ID e Timestamp) para os inscritos no tópico da sala.
