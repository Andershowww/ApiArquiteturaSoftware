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
  logradouro NVARCHAR(100),
  numero NVARCHAR(10),
  bairro NVARCHAR(50),
  municipio NVARCHAR(50),
  complemento NVARCHAR(50),
  cep NVARCHAR(8)
);
