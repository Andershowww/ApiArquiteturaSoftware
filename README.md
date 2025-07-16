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

- [ApiArquiteturaSoftware](#apiarquiteturasoftware)
  - [Índice](#índice)
  - [Descrição](#descrição)
  - [Tecnologias Utilizadas](#tecnologias-utilizadas)
  - [Estrutura do Projeto](#estrutura-do-projeto)
  - [Como Rodar o Projeto](#como-rodar-o-projeto)
    - [Pré-requisitos](#pré-requisitos)
    - [Script de criação tabelas SQL Server](#script-de-criação-tabelas-sql-server)
    - [Script Insert UF](#script-insert-uf)
    - [Passos](#passos)
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
│   │       └── application.properties       # Configurações principais       
│   │       
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
### Script de criação tabelas SQL Server

```sql
CREATE TABLE UF (
  id_uf INT IDENTITY(1,1) PRIMARY KEY,
  uf NVARCHAR(2) NOT NULL
);

CREATE TABLE Fornecedor (
  id_fornecedor INT IDENTITY(1,1) PRIMARY KEY,
  cnpj VARCHAR(14) NOT NULL,
  razao_social NVARCHAR(100),
  nome_fantasia NVARCHAR(100),
  cnae NVARCHAR(20)
);

CREATE TABLE EnderecoFornecedor (
  id_endereco INT IDENTITY(1,1) PRIMARY KEY,
  id_fornecedor INT FOREIGN KEY REFERENCES Fornecedor(id_fornecedor),
  id_uf INT FOREIGN KEY REFERENCES UF(id_uf),
  logradouro NVARCHAR(50),
  numero NVARCHAR(10),
  bairro NVARCHAR(50),
  municipio NVARCHAR(50),
  complemento NVARCHAR(50),
  cep NVARCHAR(50)
);
```
### Script Insert UF
```
SET IDENTITY_INSERT [dbo].[UF] ON 
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (2, N'AC')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (3, N'AL')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (4, N'AP')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (5, N'AM')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (6, N'BA')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (7, N'CE')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (8, N'DF')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (9, N'ES')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (10, N'GO')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (11, N'MA')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (12, N'MT')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (13, N'MS')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (14, N'MG')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (15, N'PA')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (16, N'PB')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (17, N'PR')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (18, N'PE')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (19, N'PI')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (20, N'RJ')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (21, N'RN')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (22, N'RS')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (23, N'RO')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (24, N'RR')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (25, N'SC')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (26, N'SP')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (27, N'SE')
GO
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (28, N'TO')
GO
SET IDENTITY_INSERT [dbo].[UF] OFF

```

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
O projeto utiliza banco de dados **SQL Server**, você pode usar a estrutura disponibilizada na seção: [Script de criação tabelas SQL Server](#script-de-criacao-tabelas-sql-server).
Após criar seu banco de dados, preencha a tabela `UF` com todas as unidades federativas disponíveis na seção: [Script Insert UF](#script-insert-uf).

Procedimento para liberar o TCP/IP no configuration manager (caso não seja realizado, o servidor não será encontrado.):
    ```bash
          1. Abrir o Configuration Manager.
          2. Em Configuração de Rede do SQL Server, clique em Protocolos para SQLEXPRESS ou MSSQLSERVER.
          3. Clique com o botão direito do mouse em TCP/IP, em seguida, escolha Ativar no menu suspenso.
     ```
     Configure o banco de dados no arquivo src/main/resources/application.properties, o projeto utiliza arquivos de configuração no formato .properties para definir parâmetros essenciais, como conexão com banco de dados, portas, entre outros. Nele você deve substituir os dados exemplos pela sua conexão com o banco SQL Server criado.    
 
4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```
   Ou execute a classe `ApiArquiteturaSoftwareApplication` pelo seu IDE.

5. Acesse a API:
    - Endpoints disponíveis em: `http://localhost:8080` (valide se a porta que está rodando é a 8080)
    

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
    "cnpj": "19131243000196",
    "razaoSocial": "OPEN KNOWLEDGE BRASIL",
    "nomeFantasia": "REDE PELO CONHECIMENTO LIVRE",
    "cnae": "",
    "endereco": {
        "logradouro": "PAULISTA 37",
        "numero": "37",
        "complemento": "ANDAR 4",
        "bairro": "BELA VISTA",
        "municipio": "SAO PAULO",
        "uf": "SP",
        "cep": "01311902"
    }
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
