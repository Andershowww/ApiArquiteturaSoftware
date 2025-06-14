Tecnologia
Java Puro (JDK 21.0.7)

IDE Recomendada: Visual Studio Code

Plugins necessários:

- Java Extension Pack

- Debugger for Java

Sem uso de frameworks externos (como Spring)

Arquitetura utilizada:
MVC (Model - View - Controller) com separação de responsabilidades:

*****************************************************************
controller - Recebe requisições HTTP e responde à API REST	
service - Contém a lógica de negócio e integração com APIs externas	
repository - Simula persistência de dados (atualmente em memória)	
model -	Define as entidades do domínio da aplicação	
dto	- Define objetos para entrada de dados (input JSON)
util - Utilitários auxiliares (ex: conversão JSON, resposta)