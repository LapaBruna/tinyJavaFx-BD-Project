-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29-Jun-2021 às 23:36
-- Versão do servidor: 10.4.19-MariaDB
-- versão do PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `cacupe`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `cpfFunc` int(11) NOT NULL,
  `nomeFunc` varchar(255) NOT NULL,
  `nasciFunc` date NOT NULL,
  `enderecoFunc` varchar(255) NOT NULL,
  `senhaFunc` varchar(255) NOT NULL,
  `emailFunc` varchar(255) NOT NULL,
  `foneFunc` varchar(255) NOT NULL,
  `funcaoFunc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`cpfFunc`, `nomeFunc`, `nasciFunc`, `enderecoFunc`, `senhaFunc`, `emailFunc`, `foneFunc`, `funcaoFunc`) VALUES
(111222333, 'Bruna Lapa Valgas', '1989-09-18', 'Praia Comprida', '369', 'bruna@usj.com', '48999979999', 'Marketing'),
(444555666, 'Lucas Hames', '1993-01-02', 'Barreiros', '333', 'lucas@usj.com', '4899888899', 'Vendas'),
(777888999, 'Rafael Kbrau', '1999-03-01', 'Campinas', '999', 'Rafa@usj.com', '4899889988', 'Médico');

-- --------------------------------------------------------

--
-- Estrutura da tabela `medico`
--

CREATE TABLE `medico` (
  `crmMed` int(11) NOT NULL,
  `nomeMed` varchar(255) NOT NULL,
  `senhaMed` varchar(255) NOT NULL,
  `emailMed` varchar(255) NOT NULL,
  `foneMed` varchar(255) NOT NULL,
  `espeMed` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `medico`
--

INSERT INTO `medico` (`crmMed`, `nomeMed`, `senhaMed`, `emailMed`, `foneMed`, `espeMed`) VALUES
(111, 'Bruna Lapa Valgas', '666', 'bruna@usj.com', '4899996666', 'Nariz'),
(444, 'Lucas Hames', '0101', 'lucas@usj.com', '4855552222', 'Ouvido'),
(777, 'Rafael Kabrau', '999', 'Rafael@usj.com', '4877776666', 'Garganta');

-- --------------------------------------------------------

--
-- Estrutura da tabela `paciente`
--

CREATE TABLE `paciente` (
  `cpfPac` int(11) NOT NULL,
  `nomePac` varchar(255) NOT NULL,
  `nasciPac` date NOT NULL,
  `enderecoPac` varchar(255) NOT NULL,
  `emailPac` varchar(255) NOT NULL,
  `fonePac` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `paciente`
--

INSERT INTO `paciente` (`cpfPac`, `nomePac`, `nasciPac`, `enderecoPac`, `emailPac`, `fonePac`) VALUES
(111222333, 'Bruna Lapa Valgas', '1989-09-18', 'Praia comprida', 'Bruna@usj.com', '48444499999'),
(444555666, 'Lucas Hames', '1995-01-01', 'Campinas', 'Lucas@usj.com', '4877775555'),
(777888999, 'Rafael Kabrau', '1999-02-01', 'Kobrasol', 'Rafael@usj.com', '48999668877');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`cpfFunc`);

--
-- Índices para tabela `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`crmMed`);

--
-- Índices para tabela `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`cpfPac`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `cpfFunc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=888554668;

--
-- AUTO_INCREMENT de tabela `medico`
--
ALTER TABLE `medico`
  MODIFY `crmMed` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=778;

--
-- AUTO_INCREMENT de tabela `paciente`
--
ALTER TABLE `paciente`
  MODIFY `cpfPac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=999666334;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
