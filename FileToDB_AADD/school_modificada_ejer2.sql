-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 27, 2021 at 09:28 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `school`
--

-- --------------------------------------------------------

--
-- Table structure for table `alumno`
--

CREATE TABLE `alumno` (
  `nummatricula` int(11) NOT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `fechanacimiento` date DEFAULT NULL,
  `telefono` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `alumno`
--

INSERT INTO `alumno` (`nummatricula`, `nombre`, `fechanacimiento`, `telefono`) VALUES
(1, 'Jesus', '2001-08-28', '+34631269272'),
(2, 'Lidia', '2000-09-29', '666555444'),
(3, 'Carlos', '1990-12-12', '+1555999666');

-- --------------------------------------------------------

--
-- Table structure for table `asignatura`
--

CREATE TABLE `asignatura` (
  `codasignatura` varchar(50) NOT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `idprofesor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `asignatura`
--

INSERT INTO `asignatura` (`codasignatura`, `nombre`, `idprofesor`) VALUES
('1DAM_ED', 'Entornos de Desarrollo', 36),
('1DAM_LM', 'Lenguajes de Marcas', 36),
('1DAM_PR', 'Programación', 37),
('1DAM_SI', 'Sistemas Informáticos', 38),
('2DAM_AD', 'Acceso a Datos', 36),
('2DAM_DI', 'Diseño de Interfaces', 37),
('2DAM_PDM', 'Programación y Dispositivos Móviles', 38),
('2DAM_PSP', 'Programación de Servicios y Procesos', 37),
('2DAM_SGE', 'Sistemas de Gestión Empresarial', 36);

-- --------------------------------------------------------

--
-- Table structure for table `profesor`
--

CREATE TABLE `profesor` (
  `idprofesor` int(11) NOT NULL,
  `nif_p` varchar(9) DEFAULT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `especialidad` varchar(40) DEFAULT NULL,
  `telefono` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `profesor`
--

INSERT INTO `profesor` (`idprofesor`, `nif_p`, `nombre`, `especialidad`, `telefono`) VALUES
(36, '12345178N', 'Nacho Lorenzo', 'Informática', '666123456'),
(37, '12345676J', 'José de la Torre', 'Informática', '661113456'),
(38, '44445676F', 'Francisco Fernández', 'Informática', '661187656');

-- --------------------------------------------------------

--
-- Table structure for table `recibe`
--

CREATE TABLE `recibe` (
  `nummatricula` int(11) NOT NULL,
  `codasignatura` varchar(50) CHARACTER SET utf8 NOT NULL,
  `cursoescolar` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`nummatricula`);

--
-- Indexes for table `asignatura`
--
ALTER TABLE `asignatura`
  ADD PRIMARY KEY (`codasignatura`),
  ADD KEY `idprofesor` (`idprofesor`);

--
-- Indexes for table `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`idprofesor`),
  ADD UNIQUE KEY `nif_p` (`nif_p`);

--
-- Indexes for table `recibe`
--
ALTER TABLE `recibe`
  ADD PRIMARY KEY (`nummatricula`,`codasignatura`),
  ADD KEY `codasignatura` (`codasignatura`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `profesor`
--
ALTER TABLE `profesor`
  MODIFY `idprofesor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `asignatura`
--
ALTER TABLE `asignatura`
  ADD CONSTRAINT `asignatura_ibfk_1` FOREIGN KEY (`idprofesor`) REFERENCES `profesor` (`idprofesor`);

--
-- Constraints for table `recibe`
--
ALTER TABLE `recibe`
  ADD CONSTRAINT `recibe_ibfk_1` FOREIGN KEY (`nummatricula`) REFERENCES `alumno` (`nummatricula`),
  ADD CONSTRAINT `recibe_ibfk_2` FOREIGN KEY (`codasignatura`) REFERENCES `asignatura` (`codasignatura`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
