# Janban Real-Time API

Este projeto foi desenvolvido como parte da disciplina de **Programação Orientada a Objetos** na faculdade. Utilizando **Java** com **Spring Boot** no backend e **React** no frontend, o objetivo foi criar uma aplicação inspirada no **Trello**, onde múltiplos usuários podem colaborar em tempo real em uma estrutura de Kanban.

## Contexto e Motivação

Inicialmente, o projeto seria um simples **CRUD de Usuários** e **Anotações**, o clássico **To-Do List**. No entanto, para me desafiar, decidi ampliar a ideia e criar algo mais robusto: um sistema de **Kanban com suporte a múltiplos usuários em tempo real**.

A ideia surgiu durante as aulas de **Engenharia de Software III**, onde utilizamos o Trello. Inspirado por isso, quis implementar um sistema onde:

- Múltiplos usuários pudessem colaborar simultaneamente.
- Tarefas pudessem ser movidas entre colunas.
- Todas as interações fossem sincronizadas em **tempo real** utilizando **WebSocket**.

Embora já tivesse trabalhado com **Node.js** e **WebSocket** em projetos anteriores, implementar isso com **Java** foi um desafio emocionante.

## Funcionalidades

- **CRUD de Usuários**.
- **CRUD de Anotações**.
- **CRUD de Salas**.
- **Sistema de Kanban**:
  - Colaboração em tempo real entre usuários.
  - Movimentação de tarefas entre colunas.
  - Sincronização de eventos de criação, atualização e exclusão de tarefas.

## Tecnologias Utilizadas

### Backend:

- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Security
  - WebSocket

### Frontend:

- **React**
- **TypeScript**
- **Axios** (para chamadas HTTP)
- **Socket.IO** (para WebSocket)
- **STOMPjs** (para facilitar a comunicação WebSocket)

## Estrutura do Projeto

### Backend (Spring Boot)

O projeto segue uma estrutura organizada por domínios, com camadas separadas para facilitar manutenção e escalabilidade:

    src/
    ├── main/
    │ ├── java/
    │ │ ├── com.example.kanban/
    │ │ │ ├── domain/
    │ │ │ │ ├── user
    │ │ │ │ ├── note
    │ │ │ │ |── room
    │ │ │ | | |── repository/
    │ │ │ | | |── service/
    │ │ │ | | |── controller/
    │ │ │ | | |── dto/
    │ │ │ | | |── entity/
    │ ├── resources/
    │ ├── application.properties

### Frontend (React)

O frontend foi projetado com **React** e **TypeScript**, utilizando uma interface simples e funcional. As interações com o backend são realizadas via **Axios** e **WebSocket**.

## WebSocket

A implementação do **WebSocket** foi essencial para garantir a atualização em tempo real. Ele foi usado para:

- **Created**: Notificar os usuários quando uma nova tarefa é criada.
- **Updated**: Sincronizar alterações em tarefas existentes.
- **Deleted**: Atualizar a interface ao remover tarefas.

Exemplo de payload enviado pelo WebSocket:

```json
{
  "type": "created",
  "data": {
    "id": "c172f68c-601e-4ca2-80c9-58c44b2d7001",
    "title": "Nova Tarefa",
    "column": "done",
    "authorName": "pedro",
    "position": 1
  }
}
```

## Demonstração do Projeto

Aqui está um vídeo de demonstração do projeto:

[🔗 Assista ao vídeo](./project-video.mp4)

## Futuras Melhorias e Implementações

Como o projeto foi desenvolvido com prazos curtos, algumas escolhas foram feitas para simplificar a entrega. No entanto, há diversas melhorias e funcionalidades que podem ser implementadas no futuro para tornar o sistema mais completo e robusto:

### 1. **Persistência de Dados**

Atualmente, o projeto utiliza uma **implementação em memória** para simular um banco de dados. Todos os dados são armazenados temporariamente e se perdem ao reiniciar a aplicação. No entanto, a arquitetura do projeto foi pensada para facilitar a troca da camada de persistência:

- Os repositórios foram abstraídos em interfaces, o que permite trocar o método de persistência facilmente.
- Futuramente, será possível integrar com bancos de dados como **PostgreSQL**, **MySQL**, ou até mesmo **MongoDB**, apenas implementando as interfaces já definidas.

### 2. **Melhorias no CRUD de Salas**

No momento, o CRUD de salas está limitado à criação de novas salas. Algumas melhorias planejadas incluem:

- **Listar Salas Criadas**: Permitir que os usuários visualizem todas as salas existentes.
- **Listar Salas em que o Usuário Participa**: Mostrar as salas que o usuário já acessou ou foi convidado.
- **Excluir e Editar Salas**: Adicionar funcionalidades para exclusão e edição de salas.

### 3. **Gerenciamento de Colunas**

Uma das principais funcionalidades de um sistema Kanban é a flexibilidade de personalizar o quadro de tarefas. As melhorias planejadas incluem:

- **Adicionar Novas Colunas**: Permitir que os usuários criem novas colunas para organizar suas tarefas.
- **Excluir Colunas**: Opção para remover colunas desnecessárias.
- **Mover Colunas**: Implementar a funcionalidade de reordenar colunas.

### 4. **Sistema de Convites**

Implementar um sistema de convites para colaborar em salas:

- **Enviar Convites para Outros Usuários**: O criador da sala ou membros com permissão poderão convidar novos participantes.
- **Aceitar/Recusar Convites**: Os usuários poderão aceitar ou recusar convites enviados.
- **Notificação em Tempo Real de Convites**: Quando um convite for enviado, o usuário receberá uma notificação em tempo real via WebSocket.

### 5. **Autenticação e Controle de Acesso**

Embora o sistema atual seja simples, a introdução de autenticação pode melhorar a segurança e personalização:

- **Login e Registro de Usuários**: Implementar autenticação usando JWT ou OAuth2.
- **Permissões por Sala**: Controlar quais usuários podem editar, excluir ou convidar outros participantes para uma sala.

---

Essas melhorias e novas funcionalidades tornarão o projeto mais robusto e oferecerão uma experiência de usuário mais rica e completa. A modularidade e organização do código atual facilitarão a implementação dessas mudanças no futuro.
