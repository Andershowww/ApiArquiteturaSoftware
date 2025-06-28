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

Este projeto tem como objetivo fornecer uma API para consulta e cadastro de fornecedores, além de integração com a API do YouTube. Ele segue as boas práticas de design de software, com separação de camadas (Controller, Service, Repository, Model, DTO, Util) e persistência de dados local por meio do banco SQLite.

---

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3+**
- **Maven**
- **SQLite** (via JDBC)
- **HTTPServer (Sun)** para endpoints legados

---

## Estrutura do Projeto

```
ApiArquiteturaSoftware/
├── legacy/ # Projeto antigo (referência)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/consultasapibr/apiarquiteturasoftware/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       ├── util/
│   │   │       └── ApiArquiteturaSoftwareApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application.local.properties
│   └── test/
│       └── java/
│           └── br/com/consultasapibr/apiarquiteturasoftware/
├── pom.xml
├── .gitignore
└── README.md
```

- **controller/**: Endpoints/rest controllers.
- **service/**: Lógica de negócio.
- **repository/**: Persistência de dados.
- **model/**: Entidades do sistema.
- **dto/**: Objetos de transferência de dados.
- **util/**: Classes utilitárias.
- **legacy/**: Estrutura antiga mantida para referências.

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

3. Banco de dados: 
    Se usar banco de dados, configurar a conexao no arquivo application.properties na pasta resources.
    Se não for utilizar banco de dados, adicione a seguinte anotação na classe ApiArquiteturaSoftwareApplication.java (classe principal), substituindo a anotação existente:
    @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
    adicione o import : import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
    Isso impede que o Spring Boot tente configurar uma conexão com o banco de dados ao iniciar a aplicação.
    
4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```
   Ou execute a classe `ApiArquiteturaSoftwareApplication` pelo seu IDE.

5. Acesse a API:
    - Endpoints disponíveis em: `http://localhost:8080`
    - Endpoint legado: `http://localhost:8080/fornecedores`

---

## Exemplos de Uso

- **Consultar cnpj:**
    ```
    GET http://localhost:8080/fornecedores/consulta-cnpj
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
- **Código legado mantido em `/legacy` para consulta e refatoração futura**

Para mais detalhes, consulte:
- [Java Code Conventions (Oracle)](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
- [Spring Boot Reference - Structuring Your Code](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code)

---
