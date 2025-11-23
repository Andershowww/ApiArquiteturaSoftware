# backend-tarefa1-projeto-desenvolvimento-webapp

Projeto backend em Java + Spring Boot

---

## Índice

- [backend-tarefa1-projeto-desenvolvimento-webapp](#backend-tarefa1-projeto-desenvolvimento-webapp)
  - [Índice](#índice)
  - [Descrição](#descrição)
  - [Tecnologias Utilizadas](#tecnologias-utilizadas)
  - [Estrutura do Projeto](#estrutura-do-projeto)
  - [Como Rodar o Projeto](#como-rodar-o-projeto)
    - [Pré-requisitos](#pré-requisitos)
    - [Passos](#passos)
  - [Exemplos de Uso](#exemplos-de-uso)
  - [Padrões de Código e Organização](#padrões-de-código-e-organização)

---

## Descrição

Este projeto tem como objetivo fornecer uma API para nosso projeto que consiste em um software com dashboards, 
cadastro de produtos e cadastro de ordem de produção:

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
│   │   │   └── br/com/consultasapibr/gestaoproducao/
│   │   │       ├── controller/          # Endpoints REST
│   │   │       ├── dto/                 # Data Transfer Objects (entrada/saída JSON)
│   │   │       ├── model/               # Entidades JPA / domínio
│   │   │       ├── repository/          # Interfaces JPA para acesso a dados
│   │   │       ├── service/             # Lógica de negócio
│   │   │       ├── util/                # Classes utilitárias
│   │   │       └── gestaoproducao.java  # Classe principal
│   │   └── resources/
│   │       └── application.properties   # Configurações principais       
│   │       
│   └── test/
│       └── java/
│           └── br/com/gestaoproducao/   # Testes automatizados
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
    git clone <LINK_DO_PROJETO>
    ```

2. Banco de dados:
   
   - O projeto utiliza banco de dados **SQL Server**, para desenvolvimento foi utilizado o SQL Server Management Studio, você pode usar a estrutura disponibilizada na seção: [Script de criação tabelas SQL Server](#script-de-criação-tabelas-sql-server).

   - Procedimento para liberar o TCP/IP no configuration manager (caso não seja realizado, o servidor não será encontrado):
      - Abrir o Configuration Manager.
      - Em Configuração de Rede do SQL Server, clique em Protocolos para SQLEXPRESS ou MSSQLSERVER.
      - Clique com o botão direito do mouse em TCP/IP, em seguida, escolha Ativar no menu suspenso.
     
   - Configure o banco de dados no arquivo src/main/resources/application.properties, o projeto utiliza arquivos de configuração no formato .properties para definir parâmetros essenciais, como conexão com banco de dados, portas, entre outros. Nele você deve substituir os dados exemplos que estão dentro do arquivo pela sua conexão com o banco SQL Server criado.

   Observação: A flag de atualização do banco de dados está como `spring.jpa.hibernate.ddl-auto=update`, isso significa que o Hibernate irá atualizar o esquema do banco de dados conforme as entidades definidas no código, não sendo necessário a criação das DDLs.

3. Compile o projeto:
    ```bash
    mvn clean install
    ```

4. Execute o Script query.sql encontrado na raiz do projeto no SQL Server Management Studio para popular as tabelas.

5. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

6. Acesse a API:
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
    
- **Cadastrar ordem de produção:**
    ```
      POST http://localhost:8080/OrdemProducao
      Content-Type: application/json
       {
            "numeroOrdem": "OP-2024-001",
            "produto": "Smartphone XYZ",
            "quantidade": 500,
            "dataInicio": "2024-01-15",
            "dataPrevisaoConclusao": "2024-01-30",
            "status": "PLANEJADA",
            "materiais": [
                {
                    "material": "Tela 6.5\"",
                    "quantidade": 500,
                    "unidade": "UN"
                },
                {
                    "material": "Processador Snapdragon",
                    "quantidade": 500,
                    "unidade": "UN"
                }
            ],
            "observacoes": "Ordem de produção para lote inicial"
        }
    ```
---

## Padrões de Código e Organização

- **Pacotes organizados por camada:**  
  Exemplo: `controller`, `service`, `repository`, `model`, `dto`, `util`
- **Nomes de classes em CamelCase** (ex: FornecedorController, FornecedorService)
- **Packages em minúsculo, seguindo domínio invertido**  
  Exemplo: `br.com.consultasapibr.gestaoproducao.model`
- **Configurações em `application.properties`**


Para mais detalhes, consulte:
- [Java Code Conventions (Oracle)](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
- [Spring Boot Reference - Structuring Your Code](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code)

---
