# Auth Service

> **Nota**: As responsabilidades e rotas descritas aqui representam o planejamento inicial do serviço. Atualize este documento conforme as implementações forem evoluindo.

## Responsabilidades Principais

- Centralizar autenticação e autorização da plataforma.
- Gerar e validar tokens JWT para acesso às APIs internas.
- Integrar com o microsserviço `user` para validação de credenciais e perfil.
- Expor endpoints públicos para login, registro e recuperação de credenciais.
- Publicar eventos de segurança (ex.: login, bloqueios) para outros serviços consumirem.

## Stack e Dependências Esperadas

- Spring Boot 3.x
- Spring Security 6
- Biblioteca `springdoc-openapi-starter-webmvc-ui` para Swagger UI
- Persistência de usuários/autorização (MongoDB ou PostgreSQL — definir na implementação)
- Cache e controle de sessão (Redis) opcional para revogação de tokens
- Mensageria via RabbitMQ para notificar o `saga` service sobre eventos críticos

## Rotas REST Planejadas

| Método | Caminho                     | Descrição                                     | Autenticação                | Observações                             |
| ------ | --------------------------- | --------------------------------------------- | --------------------------- | --------------------------------------- |
| `POST` | `/api/auth/login`           | Autentica usuário e devolve JWT/refresh token | Pública                     | Valida credenciais com `user` service   |
| `POST` | `/api/auth/register`        | Cria novo usuário com dados mínimos           | Pública                     | Pode delegar criação a `user` service   |
| `POST` | `/api/auth/refresh`         | Troca refresh token por novo JWT              | Requer refresh token válido | Armazena refresh tokens revogados       |
| `POST` | `/api/auth/logout`          | Revoga tokens ativos                          | Requer JWT válido           | Opcionalmente publica evento de logout  |
| `POST` | `/api/auth/forgot-password` | Solicita fluxo de recuperação de senha        | Pública                     | Dispara e-mail/OTP via provider externo |

> **Swagger planejado**: `/swagger-ui.html` e `/v3/api-docs`.

## Fluxos de Dados e Integrações

- **`User Service`**: consulta dados cadastrais e sincroniza perfis.
- **`Saga Service`**: publica eventos de auditoria (`auth.login.succeeded`, `auth.login.failed`) para compor trilhas de auditoria.
- **Provedores externos**: SMTP/SMS para recuperação de senha (configurar adaptadores).
- **Banco de dados**: tabela/coleção `users` (minimal) e `revoked_tokens`.

## Segurança e Política de Senhas

- Definir regras de complexidade de senhas e tentativas máximas.
- Considerar 2FA opcional (via e-mail/SMS/app autenticador).
- Garantir armazenamento seguro (hash BCrypt/Argon2).

## Configurações de Ambiente

| Variável                      | Descrição                                 |
| ----------------------------- | ----------------------------------------- |
| `JWT_SECRET`                  | Chave usada para assinar tokens JWT       |
| `JWT_EXPIRATION_MINUTES`      | Tempo de expiração do token de acesso     |
| `JWT_REFRESH_EXPIRATION_DAYS` | Validade do refresh token                 |
| `AUTH_RABBITMQ_EXCHANGE`      | Exchange para eventos de autenticação     |
| `SPRING_SECURITY_OAUTH2_*`    | Configurações opcionais para login social |

## Observabilidade

- Registrar tentativas de login e falha em logs estruturados.
- Expor métricas no formato Prometheus (`/actuator/prometheus`).
- Configurar alertas para picos de falhas de login e bloqueios.

## Próximos Passos

1. Adicionar dependências de segurança e openapi ao `pom.xml`.
2. Implementar controllers e services para cada rota listada.
3. Criar testes de integração cobrindo fluxos de autenticação e refresh.
4. Atualizar documentação ao liberar novas rotas (ex.: 2FA, gerenciamento de roles).
