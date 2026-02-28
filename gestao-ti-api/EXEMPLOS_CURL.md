# Exemplos de Requisições CURL

## Ativos

### Listar todos os ativos
```bash
curl -X GET http://localhost:8080/api/ativos
```

### Criar um novo ativo
```bash
curl -X POST http://localhost:8080/api/ativos \
  -H "Content-Type: application/json" \
  -d '{
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
  }'
```

### Obter ativo por ID
```bash
curl -X GET http://localhost:8080/api/ativos/1
```

### Filtrar ativos por status
```bash
curl -X GET http://localhost:8080/api/ativos/status/ATIVO
```

### Atualizar ativo
```bash
curl -X PUT http://localhost:8080/api/ativos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "patrimonio": "PAT001",
    "tipo": "Notebook",
    "marca": "Dell",
    "modelo": "Inspiron 15",
    "serie": "ABC123456",
    "ip": "192.168.1.101",
    "mac": "00:1A:2B:3C:4D:5E",
    "status": "MANUTENCAO",
    "valor": 3500.00,
    "responsavelId": 2
  }'
```

### Deletar ativo
```bash
curl -X DELETE http://localhost:8080/api/ativos/1
```

## Chamados

### Listar todos os chamados
```bash
curl -X GET http://localhost:8080/api/chamados
```

### Criar um novo chamado
```bash
curl -X POST http://localhost:8080/api/chamados \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Notebook não liga",
    "descricao": "O notebook do usuário não está ligando",
    "prioridade": "ALTA",
    "status": "ABERTO",
    "solicitanteId": 1,
    "tecnicoId": 2
  }'
```

### Obter chamado por ID
```bash
curl -X GET http://localhost:8080/api/chamados/1
```

### Filtrar chamados por status
```bash
curl -X GET http://localhost:8080/api/chamados/status/ABERTO
```

### Filtrar chamados por prioridade
```bash
curl -X GET http://localhost:8080/api/chamados/prioridade/CRITICA
```

### Atualizar chamado
```bash
curl -X PUT http://localhost:8080/api/chamados/1 \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Notebook não liga",
    "descricao": "O notebook do usuário não está ligando",
    "prioridade": "ALTA",
    "status": "EM_ANDAMENTO",
    "solicitanteId": 1,
    "tecnicoId": 2
  }'
```

### Deletar chamado
```bash
curl -X DELETE http://localhost:8080/api/chamados/1
```

## Movimentações

### Listar todas as movimentações
```bash
curl -X GET http://localhost:8080/api/movimentacoes
```

### Criar uma nova movimentação
```bash
curl -X POST http://localhost:8080/api/movimentacoes \
  -H "Content-Type: application/json" \
  -d '{
    "ativoId": 1,
    "responsavelAntigoId": 1,
    "responsavelNovoId": 2,
    "centroCusto": "TI-001",
    "motivo": "Realocação de equipamento"
  }'
```

### Obter movimentações de um ativo
```bash
curl -X GET http://localhost:8080/api/movimentacoes/ativo/1
```

### Atualizar movimentação
```bash
curl -X PUT http://localhost:8080/api/movimentacoes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "ativoId": 1,
    "responsavelAntigoId": 1,
    "responsavelNovoId": 3,
    "centroCusto": "TI-002",
    "motivo": "Realocação para outro departamento"
  }'
```

### Deletar movimentação
```bash
curl -X DELETE http://localhost:8080/api/movimentacoes/1
```

## Manutenções

### Listar todas as manutenções
```bash
curl -X GET http://localhost:8080/api/manutencoes
```

### Criar uma nova manutenção
```bash
curl -X POST http://localhost:8080/api/manutencoes \
  -H "Content-Type: application/json" \
  -d '{
    "ativoId": 1,
    "tipo": "PREVENTIVA",
    "descricao": "Limpeza e atualização de software",
    "custo": 150.00,
    "tecnicoId": 2,
    "garantia": true
  }'
```

### Obter manutenções de um ativo
```bash
curl -X GET http://localhost:8080/api/manutencoes/ativo/1
```

### Filtrar manutenções por tipo
```bash
curl -X GET http://localhost:8080/api/manutencoes/tipo/PREVENTIVA
```

### Atualizar manutenção
```bash
curl -X PUT http://localhost:8080/api/manutencoes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "ativoId": 1,
    "tipo": "CORRETIVA",
    "descricao": "Reparo de teclado",
    "custo": 200.00,
    "tecnicoId": 2,
    "garantia": false
  }'
```

### Deletar manutenção
```bash
curl -X DELETE http://localhost:8080/api/manutencoes/1
```

## Comentários

### Listar todos os comentários
```bash
curl -X GET http://localhost:8080/api/comentarios
```

### Criar um novo comentário
```bash
curl -X POST http://localhost:8080/api/comentarios \
  -H "Content-Type: application/json" \
  -d '{
    "chamadoId": 1,
    "usuarioId": 1,
    "conteudo": "Problema resolvido com sucesso"
  }'
```

### Obter comentários de um chamado
```bash
curl -X GET http://localhost:8080/api/comentarios/chamado/1
```

### Atualizar comentário
```bash
curl -X PUT http://localhost:8080/api/comentarios/1 \
  -H "Content-Type: application/json" \
  -d '{
    "chamadoId": 1,
    "usuarioId": 1,
    "conteudo": "Problema resolvido com sucesso - Atualizado"
  }'
```

### Deletar comentário
```bash
curl -X DELETE http://localhost:8080/api/comentarios/1
```
