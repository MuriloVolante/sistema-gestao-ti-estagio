# Gestão TI API

API REST simples para gerenciamento de ativos de TI usando Spring Boot, Spring Data JPA, H2 Database e Lombok.

## Requisitos

- Java 17 ou superior
- Maven 3.6+

## Como Executar

### 1. Clonar ou descarregar o projeto
```bash
cd gestao-ti-api
```

### 2. Compilar o projeto
```bash
mvn clean install
```

### 3. Executar a aplicação
```bash
mvn spring-boot:run
```

A aplicação iniciará em `http://localhost:8080`

## Endpoints Disponíveis

### Ativos
- `GET /api/ativos` - Listar todos os ativos
- `GET /api/ativos/{id}` - Obter ativo por ID
- `GET /api/ativos/status/{status}` - Filtrar por status (ATIVO, MANUTENCAO, ESTOQUE, DESCARTADO)
- `POST /api/ativos` - Criar novo ativo
- `PUT /api/ativos/{id}` - Atualizar ativo
- `DELETE /api/ativos/{id}` - Deletar ativo

### Chamados
- `GET /api/chamados` - Listar todos os chamados
- `GET /api/chamados/{id}` - Obter chamado por ID
- `GET /api/chamados/status/{status}` - Filtrar por status (ABERTO, EM_ANDAMENTO, FECHADO, REABERTO)
- `GET /api/chamados/prioridade/{prioridade}` - Filtrar por prioridade (BAIXA, MEDIA, ALTA, CRITICA)
- `GET /api/chamados/solicitante/{solicitanteId}` - Filtrar por solicitante
- `POST /api/chamados` - Criar novo chamado
- `PUT /api/chamados/{id}` - Atualizar chamado
- `DELETE /api/chamados/{id}` - Deletar chamado

### Movimentações
- `GET /api/movimentacoes` - Listar todas as movimentações
- `GET /api/movimentacoes/{id}` - Obter movimentação por ID
- `GET /api/movimentacoes/ativo/{ativoId}` - Obter movimentações de um ativo
- `POST /api/movimentacoes` - Criar nova movimentação
- `PUT /api/movimentacoes/{id}` - Atualizar movimentação
- `DELETE /api/movimentacoes/{id}` - Deletar movimentação

### Manutenções
- `GET /api/manutencoes` - Listar todas as manutenções
- `GET /api/manutencoes/{id}` - Obter manutenção por ID
- `GET /api/manutencoes/ativo/{ativoId}` - Obter manutenções de um ativo
- `GET /api/manutencoes/tipo/{tipo}` - Filtrar por tipo (PREVENTIVA, CORRETIVA)
- `POST /api/manutencoes` - Criar nova manutenção
- `PUT /api/manutencoes/{id}` - Atualizar manutenção
- `DELETE /api/manutencoes/{id}` - Deletar manutenção

### Comentários
- `GET /api/comentarios` - Listar todos os comentários
- `GET /api/comentarios/{id}` - Obter comentário por ID
- `GET /api/comentarios/chamado/{chamadoId}` - Obter comentários de um chamado
- `POST /api/comentarios` - Criar novo comentário
- `PUT /api/comentarios/{id}` - Atualizar comentário
- `DELETE /api/comentarios/{id}` - Deletar comentário

## Exemplos de Requisições no Postman

### Criar um Ativo
```
POST http://localhost:8080/api/ativos
Content-Type: application/json

{
  "patrimonio": "PAT001",
  "tipo": "Notebook",
  "marca": "Dell",
  "modelo": "Inspiron 15",
  "serie": "ABC123456",
  "ip": "192.168.1.100",
  "mac": "00:1A:2B:3C:4D:5E",
  "status": "ATIVO",
  "valor": 3500.00,
  "responsavelId": 1
}
```

### Criar um Chamado
```
POST http://localhost:8080/api/chamados
Content-Type: application/json

{
  "titulo": "Notebook não liga",
  "descricao": "O notebook do usuário não está ligando",
  "prioridade": "ALTA",
  "status": "ABERTO",
  "solicitanteId": 1,
  "tecnicoId": 2
}
```

### Criar uma Movimentação
```
POST http://localhost:8080/api/movimentacoes
Content-Type: application/json

{
  "ativoId": 1,
  "responsavelAntigoId": 1,
  "responsavelNovoId": 2,
  "centroCusto": "TI-001",
  "motivo": "Realocação de equipamento"
}
```

### Criar uma Manutenção
```
POST http://localhost:8080/api/manutencoes
Content-Type: application/json

{
  "ativoId": 1,
  "tipo": "PREVENTIVA",
  "descricao": "Limpeza e atualização de software",
  "custo": 150.00,
  "tecnicoId": 2,
  "garantia": true
}
```

### Criar um Comentário
```
POST http://localhost:8080/api/comentarios
Content-Type: application/json

{
  "chamadoId": 1,
  "usuarioId": 1,
  "conteudo": "Problema resolvido com sucesso"
}
```

## Banco de Dados H2

O H2 é um banco de dados em memória que é criado automaticamente ao iniciar a aplicação.

Para acessar o console do H2:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: (deixar em branco)

## Estrutura do Projeto

```
gestao-ti-api/
├── src/
│   ├── main/
│   │   ├── java/com/gestao/
│   │   │   ├── GestaoTiApiApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── AtivoController.java
│   │   │   │   ├── ChamadoController.java
│   │   │   │   ├── MovimentacaoController.java
│   │   │   │   ├── ManutencaoController.java
│   │   │   │   └── ComentarioController.java
│   │   │   ├── model/
│   │   │   │   ├── Ativo.java
│   │   │   │   ├── Chamado.java
│   │   │   │   ├── Movimentacao.java
│   │   │   │   ├── Manutencao.java
│   │   │   │   └── Comentario.java
│   │   │   └── repository/
│   │   │       ├── AtivoRepository.java
│   │   │       ├── ChamadoRepository.java
│   │   │       ├── MovimentacaoRepository.java
│   │   │       ├── ManutencaoRepository.java
│   │   │       └── ComentarioRepository.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Notas

- O banco de dados é em memória (H2), então os dados são perdidos quando a aplicação é reiniciada
- Para usar um banco de dados persistente, altere a configuração em `application.properties`
- Todas as datas são armazenadas em UTC (LocalDateTime)
