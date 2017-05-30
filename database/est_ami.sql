-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 26 Mai 2017 à 09:13
-- Version du serveur :  5.7.17-log
-- Version de PHP :  7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `test`
--

-- --------------------------------------------------------

--
-- Structure de la table `est_ami`
--

CREATE TABLE `est_ami` (
  `id_user` int(11) NOT NULL,
  `id_user_Utilisateur` int(11) NOT NULL,
  `id_message` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `est_ami`
--

INSERT INTO `est_ami` (`id_user`, `id_user_Utilisateur`, `id_message`) VALUES
(1, 33, 13),
(1, 48, 28),
(1, 78, 58),
(1, 94, 74),
(1, 113, 93),
(2, 23, 3),
(2, 50, 30),
(2, 74, 54),
(2, 77, 57),
(2, 79, 59),
(2, 101, 81),
(2, 118, 98),
(3, 22, 2),
(3, 26, 6),
(3, 36, 16),
(3, 82, 62),
(4, 30, 10),
(4, 73, 53),
(4, 84, 64),
(4, 103, 83),
(4, 109, 89),
(4, 119, 99),
(5, 25, 5),
(5, 40, 20),
(5, 43, 23),
(5, 58, 38),
(6, 71, 51),
(6, 102, 82),
(6, 108, 88),
(6, 114, 94),
(7, 32, 12),
(7, 35, 15),
(7, 57, 37),
(8, 29, 9),
(8, 49, 29),
(8, 53, 33),
(8, 67, 47),
(8, 76, 56),
(8, 85, 65),
(8, 99, 79),
(9, 72, 52),
(9, 80, 60),
(9, 83, 63),
(9, 96, 76),
(9, 104, 84),
(9, 111, 91),
(9, 120, 100),
(10, 46, 26),
(10, 52, 32),
(10, 63, 43),
(10, 66, 46),
(10, 81, 61),
(10, 97, 77),
(10, 100, 80),
(11, 42, 22),
(11, 51, 31),
(11, 105, 85),
(12, 44, 24),
(12, 89, 69),
(12, 91, 71),
(12, 92, 72),
(12, 106, 86),
(13, 21, 1),
(13, 34, 14),
(13, 41, 21),
(13, 55, 35),
(13, 70, 50),
(13, 86, 66),
(13, 115, 95),
(14, 31, 11),
(14, 37, 17),
(14, 60, 40),
(14, 75, 55),
(14, 87, 67),
(14, 88, 68),
(14, 93, 73),
(15, 45, 25),
(15, 59, 39),
(15, 68, 48),
(15, 98, 78),
(15, 110, 90),
(16, 27, 7),
(16, 39, 19),
(16, 61, 41),
(16, 62, 42),
(16, 65, 45),
(17, 28, 8),
(17, 47, 27),
(17, 64, 44),
(17, 107, 87),
(18, 69, 49),
(18, 112, 92),
(19, 24, 4),
(19, 38, 18),
(19, 90, 70),
(19, 95, 75),
(19, 116, 96),
(20, 54, 34),
(20, 56, 36),
(20, 117, 97);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `est_ami`
--
ALTER TABLE `est_ami`
  ADD PRIMARY KEY (`id_user`,`id_user_Utilisateur`,`id_message`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
