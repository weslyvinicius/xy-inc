CREATE TABLE `estabelecimento` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `posicao_x` INT NOT NULL,
  `posicao_y` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `estabelecimento`
--
ALTER TABLE `estabelecimento`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for table `estabelecimento`
--
ALTER TABLE `estabelecimento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;


--
-- INSERT INIT for table `estabelecimento`
--
INSERT INTO estabelecimento VALUES(1,'Lanchonete', 27, 12);
INSERT INTO estabelecimento VALUES(2,'Posto', 31, 18);
INSERT INTO estabelecimento VALUES(3,'Joalheria', 15, 12);
INSERT INTO estabelecimento VALUES(4,'Floricultura', 19, 21);
INSERT INTO estabelecimento VALUES(5,'Pub', 12, 8);
INSERT INTO estabelecimento VALUES(6,'Supermercado', 23, 6);
INSERT INTO estabelecimento VALUES(7,'Churrascaria', 28, 2);