Projeto API - Engenharia de Softare
*****************************************************************
controller - Recebe requisições HTTP e responde à API REST	
service - Contém a lógica de negócio e integração com APIs externas	
repository - Simula persistência de dados (atualmente em memória)	
model -	Define as entidades do domínio da aplicação	
dto	- Define objetos para entrada de dados (input JSON)
util - Utilitários auxiliares (ex: conversão JSON, resposta)

# ApiArquiteturaSoftware

Projeto backend em Java + Spring Boot, com integração à [API do Brasil](https://brasilapi.com.br/)

---

## Índice

- [Descrição](#descrição)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Exemplos de Uso](#exemplos-de-uso)
- [Padrões de Código e Organização](#padrões-de-código-e-organização)

---

## Descrição

Este projeto tem como objetivo fornecer uma API para consulta e cadastro de fornecedores.
A API para gestão de fornecedores que segue boas práticas de arquitetura em camadas:

Controller: recebe requisições HTTP e responde com dados via API REST

Service: contém a lógica de negócio e integração com APIs externas

Repository: manipula persistência dos dados, atualmente via banco (ex: SQL Server, H2)

Model: define as entidades do domínio da aplicação

DTO: define objetos para entrada e saída de dados JSON

Util: utilitários auxiliares (ex: conversão, respostas padronizadas)

---

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3+**
- **Maven**
- **SQLSERVER** (via JDBC)
- **HTTPServer (Sun)** para endpoints legados

---

## Estrutura do Projeto

```
ApiArquiteturaSoftware/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/consultasapibr/apiarquiteturasoftware/
│   │   │       ├── controller/       # Endpoints REST
│   │   │       ├── dto/              # Data Transfer Objects (entrada/saída JSON)
│   │   │       ├── model/            # Entidades JPA / domínio
│   │   │       ├── repository/       # Interfaces JPA para acesso a dados
│   │   │       ├── service/          # Lógica de negócio
│   │   │       ├── util/             # Classes utilitárias
│   │   │       └── ApiArquiteturaSoftwareApplication.java  # Classe principal
│   │   └── resources/
│   │       ├── application.properties       # Configurações principais
│   │       ├── application-dev.properties   # Configurações para perfil dev
│   │       └── application-prod.properties  # Configurações para perfil produção
│   └── test/
│       └── java/
│           └── br/com/consultasapibr/apiarquiteturasoftware/  # Testes automatizados
├── pom.xml
└── README.md
```

- **controller/**: Endpoints/rest controllers.
- **service/**: Lógica de negócio.
- **repository/**: Persistência de dados.
- **model/**: Entidades do sistema.
- **dto/**: Objetos de transferência de dados.
- **util/**: Classes utilitárias.
---

## Como Rodar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- (Opcional) IntelliJ IDEA - VScode com Extensões para compilar o projeto

### Passos

1. Clone o repositório:
    ```bash
    git clone https://github.com/Andershowww/ApiArquiteturaSoftware.git
    cd ApiApiArquiteturaSoftware/apiarquiteturasoftware/
    ```

2. Compile o projeto:
    ```bash
    mvn clean install
    ```

3.Configure o banco de dados no arquivo src/main/resources/application.properties, o projeto utiliza arquivos de configuração no formato .properties para definir parâmetros essenciais, como conexão com banco de dados, portas, perfis de execução, entre outros.

Você encontrará os seguintes arquivos na pasta src/main/resources/:

application.properties
Arquivo principal de configuração, que serve como base para todos os perfis. Aqui você pode definir configurações comuns que serão usadas em todos os ambientes (dev, prod, etc), use dev para manipular dados em memória e prod para manipular com banco de dados.

application-dev.properties
Arquivo específico para o perfil de desenvolvimento ("dev"). Geralmente contém configurações para facilitar o desenvolvimento, como banco de dados em memória H2, logs mais detalhados, entre outros.

application-prod.properties
Arquivo para o perfil de produção ("prod"). Deve conter as configurações reais, como conexão com o banco SQL Server, segurança, níveis de log mais restritos, etc.
    
4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```
   Ou execute a classe `ApiArquiteturaSoftwareApplication` pelo seu IDE.

4. Acesse a API:
    - Endpoints disponíveis em: `http://localhost:8080`
    - Endpoint legado: `http://localhost:8080/fornecedores`

---

## Exemplos de Uso

- **Consultar fornecedores:**
    ```
    GET http://localhost:8080/fornecedores/lista-fornecedores
    ```
- **Consultar cnpj:**
    ```
    GET http://localhost:8080/fornecedores/consulta-cnpj?cnpj=19131243000197
    ```
- **Cadastrar fornecedor:**
    ```
    POST http://localhost:8080/fornecedores
    Content-Type: application/json

    {
        "cnpj": "12345678000100",
        "razaoSocial": "Empresa Exemplo LTDA",
        "nomeFantasia": "Exemplo",
        "logradouro": "Rua das Flores, 123",
        "municipio": "São Paulo",
        "uf": "SP"
    }
    ```

---

## Padrões de Código e Organização

- **Pacotes organizados por camada:**  
  Exemplo: `controller`, `service`, `repository`, `model`, `dto`, `util`
- **Nomes de classes em CamelCase** (ex: FornecedorController, FornecedorService)
- **Packages em minúsculo, seguindo domínio invertido**  
  Exemplo: `br.com.consultasapibr.apiarquiteturasoftware.model`
- **Configurações em `application.properties`**


Para mais detalhes, consulte:
- [Java Code Conventions (Oracle)](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
- [Spring Boot Reference - Structuring Your Code](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code)

---
