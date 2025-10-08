# Product Service

> **Nota**: Guia inicial para estruturação do microsserviço de produtos. Atualize este documento constantemente ao evoluir o domínio.

## Responsabilidades Principais

- Manter cadastro completo de produtos, incluindo atributos fiscais e comerciais.
- Relacionar produtos a categorias, regras de tributação e fornecedores.
- Disponibilizar APIs para consulta, criação e atualização de produtos.
- Sincronizar dados com outros módulos (ex.: faturamento, estoque externo).

## Stack e Dependências Esperadas

- Spring Boot 3.x + Spring Data JPA.
- Banco relacional recomendado: PostgreSQL (apoio a relacionamentos complexos).
- `springdoc-openapi` para documentação automática.
- Integração com mensageria (RabbitMQ) para notificar alterações (`product.updated`).
- Cache (Redis) opcional para catálogos de alta leitura.

## Rotas REST Planejadas

| Método   | Caminho              | Descrição                                           | Autenticação                 | Observações                          |
| -------- | -------------------- | --------------------------------------------------- | ---------------------------- | ------------------------------------ |
| `GET`    | `/api/products`      | Lista produtos com filtros (categoria, status, SKU) | Requer JWT                   | Paginação + suporte a busca textual  |
| `GET`    | `/api/products/{id}` | Detalhes do produto                                 | Requer JWT                   | Inclui atributos fiscais e imagens   |
| `POST`   | `/api/products`      | Cria novo produto                                   | Requer role `catalog-editor` | Valida vínculo com categorias ativas |
| `PUT`    | `/api/products/{id}` | Atualiza produto completo                           | Requer role `catalog-editor` | Controla versionamento               |
| `PATCH`  | `/api/products/{id}` | Atualização parcial (preço, status)                 | Requer role `catalog-editor` | Idempotente                          |
| `DELETE` | `/api/products/{id}` | Arquiva produto (soft delete)                       | Requer role `catalog-admin`  | Não remover registros de histórico   |

## Integrações

- **Category Service**: valida categorias e atributos fiscais vinculados.
- **Invoice Service**: fornece dados fiscais para emissão.
- **Saga Service**: orquestra atualizações em massa e sincronização.
- **Armazenamento de mídia**: bucket S3/MinIO para imagens (opcional).

## Modelo de Dados Sugerido

- Tabelas: `product`, `product_attribute`, `product_price_history`.
- Campos chave: `sku`, `barcode`, `category_id`.
- Suporte a atributos dinâmicos via tabela chave-valor.

## Configurações de Ambiente

| Variável                 | Descrição                        |
| ------------------------ | -------------------------------- |
| `PRODUCT_DB_URL`         | String de conexão do banco       |
| `PRODUCT_EVENT_EXCHANGE` | Exchange para eventos de produto |
| `PRODUCT_IMAGE_BUCKET`   | Local para armazenar imagens     |

## Swagger / OpenAPI

- Tag sugerida: `Product`.
- Documentar schemas `ProductRequest`, `ProductResponse`, `ProductListResponse`.
- Configurar exemplos de cargas com atributos fiscais.

## Observabilidade

- KPIs: tempo de cadastro, volume de atualizações, cache hit ratio.
- Ativar logs estruturados com correlação de requisições.

## Próximos Passos

1. Modelar entidades e migrations de produto.
2. Implementar controllers/serviços e camadas de validação.
3. Configurar publicação de eventos `product.created` / `product.updated`.
4. Atualizar este documento com rotas definitivas e links para Swagger.
