# Documentação Geral do Backend

## Visão Geral

O monorepo `backend` concentra múltiplos microsserviços Spring Boot responsáveis por domínios específicos do sistema Fiscos-MS. Cada microsserviço possui uma pasta `docs/` com informações sobre responsabilidades, rotas previstas, integrações e requisitos de configuração. Use esta pasta como ponto central para navegar pela documentação individual.

| Microsserviço | Foco principal                                     | Documento                                             |
| ------------- | -------------------------------------------------- | ----------------------------------------------------- |
| Auth          | Autenticação, autorização e emissão de tokens      | [auth/docs/README.md](../auth/docs/README.md)         |
| Category      | Gestão de categorias fiscais e contábeis           | [category/docs/README.md](../category/docs/README.md) |
| Invoice       | Emissão, conciliação e controle de notas fiscais   | [invoice/docs/README.md](../invoice/docs/README.md)   |
| Product       | Catálogo e atributos de produtos                   | [product/docs/README.md](../product/docs/README.md)   |
| Saga          | Orquestração de fluxos distribuídos e compensações | [saga/docs/README.md](../saga/docs/README.md)         |
| User          | Cadastro, perfis e preferências de usuários        | [user/docs/README.md](../user/docs/README.md)         |

## Convenções de Documentação

- **Idioma**: português brasileiro.
- **Formato**: Markdown, organizado por seções (responsabilidades, rotas, integrações, configuração e próximos passos).
- **Atualização contínua**: cada time deve atualizar a documentação ao introduzir novas rotas ou dependências.
- **Histórico**: use o controle de versão para registrar mudanças relevantes.

## Swagger / OpenAPI

- Planeja-se utilizar **springdoc-openapi** para gerar a documentação automática das rotas de cada microsserviço.
- Endpoints sugeridos (padrões do springdoc):
  - JSON: `GET /v3/api-docs`
  - UI: `GET /swagger-ui.html`
- Recomenda-se definir `springdoc.api-docs.path` e `springdoc.swagger-ui.path` conforme necessidade de cada microsserviço.
- Para uso local, exponha o swagger em cada serviço e considere agregar tudo futuramente em um API Gateway.

## Próximos Passos Recomendados

1. Configurar dependências `springdoc-openapi-starter-webmvc-ui` (ou WebFlux) em cada `pom.xml` relevante.
2. Expor rotas reais de cada microsserviço e atualizar tabelas de rotas nos documentos individuais.
3. Automatizar a publicação da documentação (CI/CD) para facilitar consulta da equipe.
4. Avaliar a consolidação de uma documentação centralizada (por exemplo, via API Gateway) apontando para os `api-docs` de cada serviço.
