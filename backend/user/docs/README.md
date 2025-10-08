# User Service

> **Nota**: Documento base para orientar a implementação do microsserviço de usuários. Atualize com as decisões finais à medida que o desenvolvimento avança.

## Responsabilidades Principais

- Manter cadastro completo de usuários, perfis e preferências.
- Fornecer dados para autenticação (em conjunto com `auth` service).
- Gerenciar papéis (roles) e permissões agregadas.
- Expor APIs para autoatendimento (atualização de perfil, troca de senha) e administração.
- Registrar histórico de alterações para auditoria e LGPD.

## Stack e Dependências Esperadas

- Spring Boot 3.x + Spring Data (JPA ou MongoDB).
- Integração com `auth` service para sincronização de credenciais.
- `springdoc-openapi` para documentação automática.
- Mensageria (RabbitMQ) para propagar eventos de usuário.
- Encrypt/Hash (BCrypt/Argon2) para dados sensíveis.

## Rotas REST Planejadas

| Método   | Caminho                  | Descrição                         | Autenticação             | Observações                   |
| -------- | ------------------------ | --------------------------------- | ------------------------ | ----------------------------- |
| `GET`    | `/api/users/me`          | Retorna perfil do usuário logado  | Requer JWT               | Usa claims do token           |
| `PUT`    | `/api/users/me`          | Atualiza dados pessoais           | Requer JWT               | Valida campos obrigatórios    |
| `PATCH`  | `/api/users/me/password` | Atualiza senha                    | Requer JWT               | Exige senha atual             |
| `GET`    | `/api/users`             | Lista usuários para administração | Requer role `user-admin` | Paginação                     |
| `POST`   | `/api/users`             | Cria usuário administrativo       | Requer role `user-admin` | Dispara evento `user.created` |
| `PUT`    | `/api/users/{id}`        | Atualiza usuário existente        | Requer role `user-admin` | Administra roles              |
| `DELETE` | `/api/users/{id}`        | Desativa usuário (soft delete)    | Requer role `user-admin` | Respeitar LGPD                |

## Integrações

- **Auth Service**: sincroniza status de conta (bloqueios, resets).
- **Saga Service**: participa de fluxos que exigem criação/remoção coordenada.
- **Email/SMS Provider**: envio de notificações (boas-vindas, reset de senha).
- **Invoice/Product**: fornece dados de contato, aprovações ou limites.

## Dados Sensíveis e LGPD

- Armazenar consentimentos e histórico de alterações.
- Permitir anonimização/remoção quando solicitado.
- Rastrear quem atualizou informações administrativas.

## Configurações de Ambiente

| Variável               | Descrição                         |
| ---------------------- | --------------------------------- |
| `USER_DB_URL`          | Conexão com banco de usuários     |
| `USER_EVENT_EXCHANGE`  | Exchange para eventos de usuário  |
| `USER_PASSWORD_POLICY` | Configuração para regras de senha |

## Swagger / OpenAPI

- Tag principal: `User`.
- Documentar schemas `UserProfile`, `UserAdminRequest`, `PasswordChangeRequest`.
- Fornecer exemplos de respostas e mensagens de erro.

## Observabilidade

- Métricas: criação vs desativação, tentativas de atualização de senha, usuários ativos.
- Logs: mudanças de role, exportações de dados.

## Próximos Passos

1. Definir modelo de dados completo e migrations iniciais.
2. Implementar controllers e services, alinhados com `auth` service.
3. Configurar eventos de auditoria e notificações.
4. Atualizar documentação com endpoints reais e schema JSON derivado do Swagger.
