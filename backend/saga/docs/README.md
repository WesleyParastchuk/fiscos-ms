# Saga Service

> **Nota**: Documento introdutório do serviço de orquestração (saga). Sirva-se como base para planejar compensações e fluxos distribuídos.

## Responsabilidades Principais

- Coordenar transações distribuídas entre múltiplos microsserviços (ex.: `invoice`, `product`, `user`).
- Implementar padrões de Saga (choreography/orchestration) para garantir consistência.
- Monitorar estados de execução e disparar ações compensatórias quando necessário.
- Expor APIs para acompanhamento/manual override de sagas críticas.

## Stack e Dependências Esperadas

- Spring Boot 3.x
- Spring State Machine ou frameworks de orquestração (ex.: Camunda, Temporal) — avaliar durante o design.
- Mensageria RabbitMQ/Kafka para coordenar passos da saga.
- Persistência para estados de saga (PostgreSQL ou MongoDB).
- `springdoc-openapi` para documentar APIs administrativas.

## Rotas REST Planejadas

| Método | Caminho                      | Descrição                               | Autenticação      | Observações                |
| ------ | ---------------------------- | --------------------------------------- | ----------------- | -------------------------- |
| `GET`  | `/api/sagas`                 | Lista instâncias de saga e status atual | Requer role `ops` | Filtros por tipo/status    |
| `GET`  | `/api/sagas/{id}`            | Detalhes de uma saga específica         | Requer role `ops` | Retorna timeline e eventos |
| `POST` | `/api/sagas/{type}`          | Dispara saga manualmente                | Requer role `ops` | Útil para reprocessos      |
| `POST` | `/api/sagas/{id}/compensate` | Executa passo compensatório manual      | Requer role `ops` | Registrar justificativa    |
| `POST` | `/api/sagas/{id}/retry`      | Reagenda execução pendente              | Requer role `ops` | Idempotente                |

## Fluxos e Eventos Esperados

- Recebe eventos dos serviços de domínio (`invoice.emitted`, `product.updated`, `auth.login.failed` etc.).
- Publica eventos de confirmação ou compensação (`saga.invoice.compensated`).
- Mantém máquina de estados com passos definidos (ex.: `RESERVE_STOCK`, `EMIT_INVOICE`, `NOTIFY_CUSTOMER`).

## Monitoramento e Alertas

- Expor métricas: quantidade de sagas ativas, taxa de falhas, tempo médio de conclusão.
- Integrar com alertas (PagerDuty, Slack) para sagas travadas.
- Armazenar trilhas de auditoria acessíveis pelo time de operações.

## Configurações de Ambiente

| Variável                 | Descrição                                        |
| ------------------------ | ------------------------------------------------ |
| `SAGA_DB_URL`            | Banco para armazenar estados                     |
| `SAGA_EVENT_EXCHANGE`    | Exchange/Topic de eventos principais             |
| `SAGA_DEAD_LETTER_QUEUE` | Fila de mensagens não processadas                |
| `SAGA_MAX_RETRIES`       | Número padrão de tentativas antes da compensação |

## Swagger / OpenAPI

- A API exposta é principalmente operacional/administrativa.
- Padronizar responses com status da saga (`PENDING`, `IN_PROGRESS`, `COMPLETED`, `FAILED`, `COMPENSATED`).
- Documentar payloads dos comandos e respostas de status.

## Próximos Passos

1. Escolher estratégia de saga (coreografia vs orquestração centralizada).
2. Definir contratos de eventos e schemas compartilhados.
3. Implementar storage para estados e histórico de passos.
4. Expandir documentação com diagramas de fluxo e exemplos reais.
