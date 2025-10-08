# Shared Module

Este módulo contém componentes Java reutilizáveis pelos microserviços do Fiscos.

## Visão geral

- **Artefato Maven**: `com.fiscos:shared`
- **Empacotamento**: `jar`
- **Java**: 17

O projeto é intencionalmente leve para conter DTOs, contratos, exceções e utilitários que devam ser compartilhados entre os serviços.

## Artefatos disponíveis

- `com.fiscos.shared.dto.error.ErrorDTO`: estrutura padrão para retornar erros, com `message`, `status`, `code`, `path` e `timestamp` (UTC via `Instant`). Imutável (`@Value`), expõe `builder()` (Lombok) e o atalho `ErrorDTO.of(...)`. As mensagens e comentários públicos são mantidos em inglês para padronização entre times.
- `com.fiscos.shared.dto.saga.SagaResponseDTO<T>`: envelope genérico para respostas de sagas, com `success`, `error` (`ErrorDTO`) e `data`. Inclui fábricas estáticas `SagaResponseDTO.success(data)` e `SagaResponseDTO.error(error)` para facilitar a criação controlada.

## Como usar localmente

1. Compile e publique o artefato no repositório Maven local:

   ```powershell
   ./mvnw clean install
   ```

   > Execute o comando dentro da pasta `backend/shared`.

2. Adicione a dependência no `pom.xml` do microserviço que precisa compartilhar os artefatos:

   ```xml
   <dependency>
       <groupId>com.fiscos</groupId>
       <artifactId>shared</artifactId>
       <version>0.0.1-SNAPSHOT</version>
   </dependency>
   ```

3. Importe as classes necessárias (`com.fiscos.shared.*`).

## Próximos passos sugeridos

- Migrar DTOs comuns existentes dos microserviços para este módulo.
- Publicar o artefato em um repositório Maven remoto (por ex. GitHub Packages) para facilitar a integração com pipelines CI/CD.
- Configurar um módulo pai (`backend/pom.xml`) para permitir `mvn install` em todos os serviços de uma só vez.
