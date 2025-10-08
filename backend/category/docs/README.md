# Category Service

> **Nota**: Documento inicial para orientar a modelagem do microsserviço de categorias. Revise constantemente conforme as implementações forem chegando.

## Responsabilidades Principais

- Gerenciar o catálogo de categorias fiscais e contábeis.
- Disponibilizar taxonomias padronizadas para outros microsserviços (ex.: `product`, `invoice`).
- Manter atributos como alíquotas, códigos fiscais (NCM, CFOP) e regras regionais.
- Permitir versionamento/validação de categorias para auditoria.

## Stack e Dependências Esperadas

- Spring Boot 3.x + Spring Data (JPA ou MongoDB a definir).
- Integração com `springdoc-openapi` para documentação.
- Banco relacional sugerido (PostgreSQL) para integridade referencial.
- Mensageria RabbitMQ para disparar eventos de atualização de categoria.

## Rotas REST Planejadas

| Método   | Caminho                | Descrição                                         | Autenticação                | Observações                           |
| -------- | ---------------------- | ------------------------------------------------- | --------------------------- | ------------------------------------- |
| `GET`    | `/api/categories`      | Lista categorias com filtros (nome, status, tipo) | Requer JWT                  | Suporta paginação e ordenação         |
| `GET`    | `/api/categories/{id}` | Retorna detalhes completos de uma categoria       | Requer JWT                  | Incluir atributos fiscais             |
| `POST`   | `/api/categories`      | Cria nova categoria                               | Requer role `catalog-admin` | Valida duplicidade de códigos fiscais |
| `PUT`    | `/api/categories/{id}` | Atualiza uma categoria inteira                    | Requer role `catalog-admin` | Recalcula metadados                   |
| `PATCH`  | `/api/categories/{id}` | Atualização parcial (status, descrição)           | Requer role `catalog-admin` | Usar JSON Merge Patch                 |
| `DELETE` | `/api/categories/{id}` | Arquiva categoria (soft delete)                   | Requer role `catalog-admin` | Manter histórico                      |

## Integrações

- **Product Service**: consome categorias para validação de produtos.
- **Invoice Service**: utiliza códigos fiscais vinculados a categorias.
- **Saga Service**: orquestra atualizações massivas (ex.: alteração de alíquota).
- **Cache**: considerar uso de Redis para caching de listas públicas.

## Regras de Negócio Consideradas

- Cada categoria deve possuir identificação única (`category_code`).
- Permitir hierarquia (categoria pai/filho) para agrupar produtos.
- Controlar datas de vigor de regras fiscais.

## Configurações de Ambiente

| Variável                     | Descrição                                |
| ---------------------------- | ---------------------------------------- |
| `CATEGORY_DB_URL`            | URL da base utilizada pelo serviço       |
| `CATEGORY_DEFAULT_PAGE_SIZE` | Página padrão para listagens             |
| `CATEGORY_EVENT_EXCHANGE`    | Exchange para eventos `category.updated` |

## Swagger / OpenAPI

- Endpoints padrão: `/swagger-ui.html` e `/v3/api-docs`.
- Tag sugerida: `Category`.
- Descrever schemas `Category`, `CategoryAudit`, `CategoryUpdatedEvent`.

## Próximos Passos

1. Definir modelo de dados e migrations (Flyway/Liquibase) para categorias.
2. Implementar serviços e repositórios, incluindo validações fiscais.
3. Registrar eventos de criação/atualização no RabbitMQ.
4. Atualizar documentação conforme endpoints e eventos forem implementados.
