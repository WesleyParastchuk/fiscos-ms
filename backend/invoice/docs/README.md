# Invoice Service

> **Nota**: Documento orientativo para o desenho inicial do microsserviço de notas fiscais. Ajuste conforme funcionalidades forem desenvolvidas.

## Responsabilidades Principais

- Registrar, emitir e acompanhar notas fiscais eletrônicas.
- Integrar com provedores externos (SEFAZ) para autorização/consulta.
- Garantir consistência com dados de `product`, `category` e `user` (emitente/destinatário).
- Disponibilizar trilhas de auditoria e status de conciliação.
- Expor APIs para busca, emissão e cancelamento de notas.

## Stack e Dependências Esperadas

- Spring Boot 3.x + Spring Data (JPA) + Bean Validation.
- Integração com serviços externos via REST/SOAP (adapters).
- Mensageria via RabbitMQ para disparo de eventos de faturamento (`invoice.emitted`).
- Armazenamento em banco relacional (PostgreSQL) com controle de transações.
- `springdoc-openapi` para documentação da API.

## Rotas REST Planejadas

| Método | Caminho                       | Descrição                                          | Autenticação          | Observações                               |
| ------ | ----------------------------- | -------------------------------------------------- | --------------------- | ----------------------------------------- |
| `GET`  | `/api/invoices`               | Lista notas com filtros (status, período, cliente) | Requer JWT            | Paginação / export CSV                    |
| `GET`  | `/api/invoices/{id}`          | Detalhes completos da NF                           | Requer JWT            | Incluir XML autorizado                    |
| `POST` | `/api/invoices`               | Solicita emissão de nova NF                        | Requer role `billing` | Valida dados fiscais antes da autorização |
| `POST` | `/api/invoices/{id}/transmit` | Reenvia NF para SEFAZ                              | Requer role `billing` | Idempotente                               |
| `POST` | `/api/invoices/{id}/cancel`   | Envia pedido de cancelamento                       | Requer role `billing` | Regras de prazo                           |
| `GET`  | `/api/invoices/{id}/events`   | Histórico de eventos/sefaz                         | Requer JWT            | Usa fonte audit trail                     |

## Integrações e Eventos

- **Product Service**: consulta dados de produtos e impostos.
- **Category Service**: valida códigos fiscais e alíquotas.
- **User Service**: obtém dados do cliente/fornecedor.
- **Saga Service**: coordena fluxos complexos (ex.: cancelamento com estorno em módulos financeiros).
- **Mensageria**: publica `invoice.emitted`, `invoice.canceled`, `invoice.failed`.

## Dados e Persistência

- Tabelas principais: `invoice`, `invoice_item`, `invoice_event`.
- Armazenar XML autorizado (campo CLOB ou storage externo S3).
- Logar divergências de validação em tabela dedicada.

## Configurações de Ambiente

| Variável                     | Descrição                                 |
| ---------------------------- | ----------------------------------------- |
| `INVOICE_DB_URL`             | String de conexão com base relacional     |
| `INVOICE_SEFAZ_ENDPOINT`     | URL do provedor SEFAZ ou mock             |
| `INVOICE_EVENT_EXCHANGE`     | Exchange para eventos do faturamento      |
| `INVOICE_XML_STORAGE_BUCKET` | Bucket/Path para armazenar XML autorizado |

## Swagger / OpenAPI

- Documentar schemas `InvoiceRequest`, `InvoiceResponse`, `InvoiceEvent`.
- Integrar respostas de erro padronizadas (HTTP 4xx/5xx) com exemplos.
- Endpoint padrão: `/v3/api-docs`; UI: `/swagger-ui.html`.

## Observabilidade

- Expor métricas de tempo de emissão, taxa de falhas, fila de reprocesso.
- Configurar tracing distribuído (OpenTelemetry) para integração com SEFAZ.

## Próximos Passos

1. Definir contrato de dados da nota fiscal e criar migrations.
2. Implementar adapters para comunicação com SEFAZ ou mocks.
3. Construir testes de integração com cenários de sucesso e falha.
4. Atualizar documentação com rotas implementadas e exemplos reais.
