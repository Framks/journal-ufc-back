# 📰 Jornal da UFC – Backend API

## 📌 Visão Geral do Projeto

O **Jornal da UFC - Backend** é a API REST do portal digital de notícias acadêmicas, desenvolvido como parte do **Trabalho Prático 2 – Gerenciamento Ágil com Scrum**.  
Este serviço é responsável por gerenciar toda a lógica de negócio, persistência de dados e exposição de endpoints para o frontend.

O sistema visa centralizar informações relevantes da **Universidade Federal do Ceará**, permitindo que professores, servidores e alunos bolsistas publiquem notícias, eventos e avisos institucionais para toda a comunidade acadêmica e externa.

---

## 🛠 Tecnologias Utilizadas

### Backend

| Tecnologia        | Descrição |
|------------------|-----------|
| Kotlin           | Linguagem de programação moderna e concisa para JVM |
| Spring Boot      | Framework para criação de aplicações Java/Kotlin robustas |
| Spring Data JPA  | Abstração para persistência de dados com ORM |
| Gradle           | Ferramenta de automação de build |

### Banco de Dados

| Tecnologia   | Descrição |
|-------------|-----------|
| PostgreSQL  | Banco de dados relacional para produção |
| H2          | Banco de dados em memória para desenvolvimento/testes |
| Liquibase  | Versionamento e migração de banco de dados |

### DevOps & Infraestrutura

| Tecnologia | Descrição |
|-----------|-----------|
| Git       | Sistema de controle de versão distribuído |
| GitHub   | Plataforma de hospedagem de código e colaboração |
| Java 21  | JDK LTS para execução da aplicação |

---

## 📁 Estrutura do Projeto

```
journal-ufc-back/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew / gradlew.bat
├── src/
│   ├── main/
│   │   ├── kotlin/com/ufc/jornal/
│   │   │   └── JornalApplication.kt
│   │   └── resources/
│   │       └── application.yaml
│   └── test/
│       └── kotlin/com/ufc/jornal/
│           └── JornalApplicationTests.kt
```

---

## 🚀 Como Executar

### Pré-requisitos

- JDK 21+
- Gradle 8+ (ou usar o wrapper incluído)
- PostgreSQL (produção) ou H2 (desenvolvimento)

### Instalação

```bash
git clone https://github.com/seu-usuario/journal-ufc-back.git
cd journal-ufc-back
chmod +x gradlew
```

### Executando em Desenvolvimento

```bash
./gradlew bootRun
```

Windows:
```bat
gradlew.bat bootRun
```

A API estará disponível em:  
👉 http://localhost:8080

### Executando os Testes

```bash
./gradlew test
```

### Gerando o Build

```bash
./gradlew build
```

O arquivo JAR será gerado em `build/libs/`.

---

## 🔧 Configuração

### Variáveis de Ambiente

| Variável | Descrição | Padrão |
|--------|-----------|--------|
| SPRING_DATASOURCE_URL | URL de conexão com o banco | H2 em memória |
| SPRING_DATASOURCE_USERNAME | Usuário do banco | sa |
| SPRING_DATASOURCE_PASSWORD | Senha do banco | - |

### Exemplo de Configuração PostgreSQL

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/jornal_ufc
    username: seu_usuario
    password: sua_senha
  jpa:
    hibernate:
      ddl-auto: validate
```

---

## 📚 Saiba Mais

- Spring Boot – Documentação oficial
- Kotlin – Referência da linguagem
- Spring Data JPA – Guia oficial
- Liquibase – Documentação oficial

---

## ☁ Deploy

O deploy pode ser realizado em qualquer plataforma que suporte aplicações Java/Spring Boot:

- Docker
- Railway / Render
- AWS / GCP / Azure

### Executando com JAR

```bash
java -jar build/libs/jornal-0.0.1-SNAPSHOT.jar
```

---

## 👥 Equipe

Desenvolvido como parte do **Trabalho Prático 2 – Gerenciamento Ágil com Scrum** da **Universidade Federal do Ceará**.

---

## 📄 Licença

Este projeto é desenvolvido exclusivamente para fins acadêmicos.