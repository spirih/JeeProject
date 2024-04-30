-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 30 avr. 2024 à 11:07
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `payaya`
--

-- --------------------------------------------------------

--
-- Structure de la table `activities`
--

DROP TABLE IF EXISTS `activities`;
CREATE TABLE IF NOT EXISTS `activities` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `discipline` text NOT NULL,
  `pathologie` int(11) NOT NULL,
  `url` int(11) NOT NULL,
  `lat` float NOT NULL,
  `lng` float NOT NULL,
  `address` text NOT NULL,
  `note` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupactivities`
--

DROP TABLE IF EXISTS `groupactivities`;
CREATE TABLE IF NOT EXISTS `groupactivities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(256) NOT NULL,
  `name` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_group_user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `groupandactivities`
--

DROP TABLE IF EXISTS `groupandactivities`;
CREATE TABLE IF NOT EXISTS `groupandactivities` (
  `idGroup` int(11) NOT NULL,
  `idActivity` int(11) NOT NULL,
  KEY `fk_group_n` (`idGroup`),
  KEY `fk_activity_n` (`idActivity`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `nickname` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `groupactivities`
--
ALTER TABLE `groupactivities`
  ADD CONSTRAINT `fk_group_user` FOREIGN KEY (`user`) REFERENCES `users` (`nickname`);

--
-- Contraintes pour la table `groupandactivities`
--
ALTER TABLE `groupandactivities`
  ADD CONSTRAINT `fk_activity_n` FOREIGN KEY (`idActivity`) REFERENCES `activities` (`id`),
  ADD CONSTRAINT `fk_group_n` FOREIGN KEY (`idGroup`) REFERENCES `groupactivities` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
