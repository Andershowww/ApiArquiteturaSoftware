INSERT INTO categoria (nome) VALUES
('Águas e Refrigerantes'),
('Sucos e Néctares'),
('Cervejas'),
('Bebida Mista Alcoólica'),
('Energéticos e Isotônicos');

INSERT INTO produto (codigo, nome, descricao, preco, estoqueAtual, estoqueMinimo, estoqueMaximo, categoria_id) VALUES
-- categoria_id = 1: Águas e Refrigerantes
('BEB-001', 'Água Mineral 500ml', 'Garrafa de água sem gás', 2.5, 5, 10, 100, 1),
('BEB-002', 'Água Mineral com Gás 1L', 'Garrafa de água com gás', 4.0, 30, 10, 100, 1),
('BEB-003', 'Refrigerante Cola 2L', 'Refrigerante sabor cola', 9.9, 120, 10, 100, 1),
('BEB-004', 'Refrigerante Guaraná 2L', 'Refrigerante sabor guaraná', 9.5, 8, 20, 80, 1),
('BEB-005', 'Refrigerante Limão 1,5L', 'Refrigerante sabor limão', 8.9, 40, 20, 80, 1),
('BEB-006', 'Refrigerante Laranja 2L', 'Refrigerante sabor laranja', 9.2, 90, 20, 80, 1),

-- categoria_id = 2: Sucos e Néctares
('BEB-007', 'Suco de Laranja 1L', 'Suco integral de laranja', 12.9, 5, 15, 60, 2),
('BEB-008', 'Suco de Uva 1L', 'Suco integral de uva', 13.5, 20, 15, 60, 2),
('BEB-009', 'Suco de Maçã 1L', 'Suco integral de maçã', 12.0, 70, 15, 60, 2),
('BEB-010', 'Néctar de Pêssego 1L', 'Néctar pronto sabor pêssego', 7.5, 12, 20, 80, 2),
('BEB-011', 'Néctar de Manga 1L', 'Néctar pronto sabor manga', 7.8, 50, 20, 80, 2),
('BEB-012', 'Néctar de Goiaba 1L', 'Néctar pronto sabor goiaba', 7.9, 100, 20, 80, 2),

-- categoria_id = 3: Cervejas
('BEB-013', 'Cerveja Pilsen Lata 350ml', 'Cerveja leve tipo pilsen', 3.9, 10, 30, 200, 3),
('BEB-014', 'Cerveja Puro Malte 600ml', 'Garrafa de cerveja puro malte', 7.5, 60, 30, 200, 3),
('BEB-015', 'Cerveja IPA 350ml', 'Lata de cerveja artesanal IPA', 8.9, 250, 30, 200, 3),
('BEB-016', 'Cerveja Sem Álcool 350ml', 'Cerveja sem teor alcoólico', 4.2, 20, 30, 100, 3),
('BEB-017', 'Cerveja de Trigo 500ml', 'Garrafa de cerveja artesanal de trigo', 9.9, 45, 20, 100, 3),
('BEB-018', 'Chopp Pilsen 1L', 'Chopp artesanal pilsen', 12.5, 150, 20, 100, 3),

-- categoria_id = 4: Bebida Mista Alcoólica
('BEB-019', 'Skol Beats Senses 313ml', 'Bebida alcoólica pronta, sabor cítrico', 6.9, 15, 10, 50, 4),
('BEB-020', 'Skol Beats GT 269ml', 'Bebida alcoólica pronta, sabor gin-tônica', 7.2, 30, 10, 50, 4),
('BEB-021', 'Smirnoff Ice 275ml', 'Bebida alcoólica mista sabor limão', 9.5, 60, 10, 50, 4),
('BEB-022', 'Ice Mix Frutas Vermelhas 275ml', 'Bebida alcoólica mista sabor frutas', 8.9, 8, 15, 40, 4),
('BEB-023', 'Ice Mix Citrus 275ml', 'Bebida alcoólica mista sabor citrus', 8.9, 20, 15, 40, 4),
('BEB-024', 'Beats Tropical 269ml', 'Bebida alcoólica mista sabor tropical', 7.8, 50, 15, 40, 4),

-- categoria_id = 5: Energéticos e Isotônicos
('BEB-025', 'Energético 250ml', 'Lata de energético sabor tradicional', 8.9, 5, 20, 100, 5),
('BEB-026', 'Energético Zero Açúcar 250ml', 'Lata de energético sem açúcar', 9.2, 40, 20, 100, 5),
('BEB-027', 'Isotônico Limão 500ml', 'Bebida esportiva sabor limão', 6.2, 120, 20, 100, 5),
('BEB-028', 'Isotônico Laranja 500ml', 'Bebida esportiva sabor laranja', 6.5, 15, 20, 80, 5),
('BEB-029', 'Água de Coco 1L', 'Água de coco natural', 11.9, 30, 20, 80, 5),
('BEB-030', 'Achocolatado Pronto 200ml', 'Bebida láctea sabor chocolate', 3.5, 50, 20, 80, 5);


-- Categorias

INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (2, N'AC');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (3, N'AL');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (4, N'AP');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (5, N'AM');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (6, N'BA');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (7, N'CE');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (8, N'DF');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (9, N'ES');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (10, N'GO');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (11, N'MA');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (12, N'MT');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (13, N'MS');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (14, N'MG');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (15, N'PA');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (16, N'PB');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (17, N'PR');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (18, N'PE');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (19, N'PI');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (20, N'RJ');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (21, N'RN');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (22, N'RS');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (23, N'RO');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (24, N'RR');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (25, N'SC');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (26, N'SP');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (27, N'SE');
INSERT [dbo].[UF] ([id_uf], [uf]) VALUES (28, N'TO');