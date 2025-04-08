-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : mar. 08 avr. 2025 à 11:20
-- Version du serveur : 11.3.2-MariaDB
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_spotifish`
--

-- --------------------------------------------------------

--
-- Structure de la table `album`
--

DROP TABLE IF EXISTS `album`;
CREATE TABLE IF NOT EXISTS `album` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `created_at` date NOT NULL,
  `description` text NOT NULL,
  `artist_uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKa19bvf0yg4fu0lbc6dpt71od3` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `album`
--

INSERT INTO `album` (`uuid`, `name`, `slug`, `created_at`, `description`, `artist_uuid`) VALUES
('02045da0-2a4a-4712-817c-99deed86659c', 'Dr Louise Moulin', 'dr-louise-moulin-02045da0', '2020-11-24', 'Lorsqu\'on le tient par la partie sporadique, ou boulière, le fenouil est un objet redondant.', '863ff2a1-e4b7-4460-a0c9-d98bcb818e0f'),
('72fa0fd9-594d-4bae-9918-e8bd66732f07', 'Pierre Louise', 'pierre-louise-72fa0fd9', '2023-05-23', 'JE NE MANGE PAS DE GRAINES !', '863ff2a1-e4b7-4460-a0c9-d98bcb818e0f'),
('8888261f-d55e-402f-b982-0bf083a592c7', 'M Lola Robin', 'm-lola-robin-8888261f', '2023-07-07', 'Du passé faisons table en marbre.', '0d5074eb-aa56-4054-a9d6-c525cae23602'),
('d68853cb-a6a0-4112-92c9-16aeb15d6a67', 'Bourgeois Julie', 'bourgeois-julie-d68853cb', '2015-08-28', 'Ça y est... je vois trouble. C\'est le manque de gras, je me dessèche.', '0d5074eb-aa56-4054-a9d6-c525cae23602'),
('e324e0ed-ee12-4432-9de5-7db2520773c9', 'Pierre Lemoine', 'pierre-lemoine-e324e0ed', '2016-05-02', 'C\'est pas faux.', '51869558-4b8c-4c5d-98e7-1127295b15b1');

-- --------------------------------------------------------

--
-- Structure de la table `artist`
--

DROP TABLE IF EXISTS `artist`;
CREATE TABLE IF NOT EXISTS `artist` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `birth_at` date NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKa19bvf0yg4fu0lbc6dpt71od3` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `artist`
--

INSERT INTO `artist` (`uuid`, `name`, `slug`, `birth_at`, `created_at`, `email`, `first_name`, `last_name`, `password`) VALUES
('76022886-e9ab-450c-9b11-ba43cb0beef9', 'Prof Arthur Gaillard', '76022886-e9ab-450c-9b11-ba43cb0beef9', '1969-11-28', '2025-04-08 11:14:21.892045', 'hugo.legrand@gmail.com', 'Noémie', 'Aubert', '$2a$10$.I4UPdSaQp/cUXzu6DGuCeR16OYo30GXD6onGfc71xX.ghdxI/c/6'),
('863ff2a1-e4b7-4460-a0c9-d98bcb818e0f', 'Dumont Louna', '863ff2a1-e4b7-4460-a0c9-d98bcb818e0f', '1959-10-08', '2025-04-08 11:14:22.004138', 'evan.menard@gmail.com', 'Valentin', 'Morin', '$2a$10$./sdbezhZKvz9fkpQkF8buCl7MSiAzHZxNVHdzGb5GYxs3SpgWa7K'),
('9782504f-8c8c-4cff-8a54-4490108d6689', 'M Mathéo Martin', '9782504f-8c8c-4cff-8a54-4490108d6689', '1997-08-19', '2025-04-08 11:14:21.540976', 'gabriel.roy@hotmail.fr', 'Maëlle', 'Chevalier', '$2a$10$O8t6/LwaX.rI8WaBPtGC8u1D2WSUDTpkuG8GJpzBA2ZaN81vUszSG'),
('cc259778-8019-424a-8eca-316006f7e666', 'Clémence Colin', 'cc259778-8019-424a-8eca-316006f7e666', '1966-12-19', '2025-04-08 11:14:22.112907', 'ines.muller@yahoo.fr', 'Mathis', 'Thomas', '$2a$10$34vP2DV8KU2aReTmvbtfguwDmD6zQtaXspqehQKySarKEZzO5sjjW'),
('ce6d3b5c-dbf2-45c8-b9ee-c3eba7106318', 'Pierre Lucie', 'ce6d3b5c-dbf2-45c8-b9ee-c3eba7106318', '1983-04-12', '2025-04-08 11:14:21.800219', 'eva.petit@yahoo.fr', 'Lilou', 'Dvnis', '$2a$10$OuNg3WwF3l/j/Y9.SSW/u.zI/HS2abLI1LDsvxnZuzMNQcJGY3Pba');

-- --------------------------------------------------------

--
-- Structure de la table `contributor`
--

DROP TABLE IF EXISTS `contributor`;
CREATE TABLE IF NOT EXISTS `contributor` (
  `is_owner` bit(1) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `still_contributing` bit(1) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `user_uuid` varchar(255) NOT NULL,
  `playlist_uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`playlist_uuid`,`user_uuid`),
  UNIQUE KEY `UKei2pgepyoi6wltbtn73ptnhna` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `contributor`
--

INSERT INTO `contributor` (`is_owner`, `slug`, `still_contributing`, `uuid`, `user_uuid`, `playlist_uuid`) VALUES
(b'1', 'dubois-noah-16655144_0d5074eb-aa56-4054-a9d6-c525cae23602', b'1', 'd12a6aa0-50b2-47ed-8304-5c0eef2a1955', '0d5074eb-aa56-4054-a9d6-c525cae23602', '16655144-cf0a-4c94-85bf-98bddfe1dbe8'),
(b'1', 'alice-lemoine-4f3bc134_76022886-e9ab-450c-9b11-ba43cb0beef9', b'1', 'bee0009c-7d06-4450-9d63-6dfd4c062bbf', '76022886-e9ab-450c-9b11-ba43cb0beef9', '4f3bc134-5676-4071-92f7-2f24ecf775c4'),
(b'1', 'dubois-thomas-549e0c31_51869558-4b8c-4c5d-98e7-1127295b15b1', b'1', '41fe7964-ae4a-4ec7-a4f8-a326ad621153', '51869558-4b8c-4c5d-98e7-1127295b15b1', '549e0c31-b402-4ff0-913b-49be7cd358f6'),
(b'1', 'm-jade-thomas-70d5518c_0d5074eb-aa56-4054-a9d6-c525cae23602', b'1', '3f6e6e23-c0fd-4f3b-8b3f-178ed8ac072c', '0d5074eb-aa56-4054-a9d6-c525cae23602', '70d5518c-5f36-43b4-b303-7aa51f7d907a'),
(b'1', 'anais-durand-d35d1d1b_91c3baff-f582-4eb9-839b-cfa39898c5e2', b'1', '0528b608-714e-4192-8ba6-35800c87e357', '91c3baff-f582-4eb9-839b-cfa39898c5e2', 'd35d1d1b-c011-4963-a05b-9dd717974f38');

-- --------------------------------------------------------

--
-- Structure de la table `historical`
--

DROP TABLE IF EXISTS `historical`;
CREATE TABLE IF NOT EXISTS `historical` (
  `uuid` varchar(255) NOT NULL,
  `listen_at` datetime(6) NOT NULL,
  `number_oflisten` bigint(20) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `song_uuid` varchar(255) NOT NULL,
  `user_uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKemjrul3beyv6p7rpyxmrshyno` (`slug`),
  KEY `FKm6b5s4d07ro0nfbhh9paewqem` (`song_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `historical`
--

INSERT INTO `historical` (`uuid`, `listen_at`, `number_oflisten`, `slug`, `song_uuid`, `user_uuid`) VALUES
('52b54a7b-d3fa-4ded-b640-a425b6da2c7c', '2021-01-17 00:00:00.000000', 1, '52b54a7b-d3fa-4ded-b640-a425b6da2c7c', '14c1593e-2605-4ec2-94ce-fa90e4ae1503', '91c3baff-f582-4eb9-839b-cfa39898c5e2'),
('62acad52-f7ac-4d7b-ae1b-e4d775e5fa7e', '2017-11-28 00:00:00.000000', 1, '62acad52-f7ac-4d7b-ae1b-e4d775e5fa7e', '1e1c43b7-24dc-45c4-8830-4cadb328f16d', 'f1872f27-6e9b-4c02-9388-5a2a7abc9568'),
('7157440e-1e90-4643-b33c-08b74a13a4f6', '2026-09-26 00:00:00.000000', 1, '7157440e-1e90-4643-b33c-08b74a13a4f6', '21483d2f-ac83-4348-b88e-45076734d824', '3a4a0222-1d57-4d31-ba74-c16154f9843c'),
('a92ba15f-855a-403b-863d-41092c4afbec', '2025-08-30 00:00:00.000000', 1, 'a92ba15f-855a-403b-863d-41092c4afbec', '21483d2f-ac83-4348-b88e-45076734d824', '57d3a6fc-8b03-471e-a2cf-60b59c6ce68e'),
('d1342417-5d21-4be3-9131-c7d80001843d', '2026-07-06 00:00:00.000000', 1, 'd1342417-5d21-4be3-9131-c7d80001843d', '68c0bbdc-baea-4a7b-8920-b3379ea0bcb8', '51869558-4b8c-4c5d-98e7-1127295b15b1');

-- --------------------------------------------------------

--
-- Structure de la table `moderator`
--

DROP TABLE IF EXISTS `moderator`;
CREATE TABLE IF NOT EXISTS `moderator` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `birth_at` date NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKa19bvf0yg4fu0lbc6dpt71od3` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `moderator`
--

INSERT INTO `moderator` (`uuid`, `name`, `slug`, `birth_at`, `created_at`, `email`, `first_name`, `last_name`, `password`) VALUES
('0d5074eb-aa56-4054-a9d6-c525cae23602', 'Colin Kylian', '0d5074eb-aa56-4054-a9d6-c525cae23602', '1969-11-05', '2025-04-08 11:14:22.543947', 'mohamed.aubry@hotmail.fr', 'Axel', 'Ménard', '$2a$10$JAIm3O0aLD9ToEn/BlvGvO6If2eVhUMnCMrrBeiKu7kdBhndFq0IC'),
('3a4a0222-1d57-4d31-ba74-c16154f9843c', 'Lucas Noémie', '3a4a0222-1d57-4d31-ba74-c16154f9843c', '1978-09-02', '2025-04-08 11:14:22.379677', 'sacha.nguyen@yahoo.fr', 'Noa', 'Perrot', '$2a$10$4Bjoi2gctYZbm2gmX4.dVezbkxVgTYroOUhOB8MVnXL6.TF86HERa'),
('51869558-4b8c-4c5d-98e7-1127295b15b1', 'Mlle Lena Thomas', '51869558-4b8c-4c5d-98e7-1127295b15b1', '2002-04-30', '2025-04-08 11:14:22.459616', 'alice.colin@hotmail.fr', 'Romane', 'Dumas', '$2a$10$zZcMrtITKOm1aY88z4yYJe1p0ozY4AONoAxRaSnSvjHeA93K3CPyW'),
('91c3baff-f582-4eb9-839b-cfa39898c5e2', 'Prof Evan Lambert', '91c3baff-f582-4eb9-839b-cfa39898c5e2', '1968-08-01', '2025-04-08 11:14:22.290803', 'elisa.schneider@hotmail.fr', 'Romane', 'Rey', '$2a$10$be.aTIxOMdctPaDMG26E0OrW2LmYP2Xy/eShuPPaEqHVTvk9SaCGm'),
('f1872f27-6e9b-4c02-9388-5a2a7abc9568', 'Romain Fabre', 'f1872f27-6e9b-4c02-9388-5a2a7abc9568', '1980-12-31', '2025-04-08 11:14:22.216539', 'romain.leroux@gmail.com', 'Carla', 'Bonnet', '$2a$10$aDd46bMrjXnAcN7djfZFo.bg8F3gTvGNFyUZ0v.N4x/cFNqNLi6Dq');

-- --------------------------------------------------------

--
-- Structure de la table `musical_genre`
--

DROP TABLE IF EXISTS `musical_genre`;
CREATE TABLE IF NOT EXISTS `musical_genre` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKa19bvf0yg4fu0lbc6dpt71od3` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `musical_genre`
--

INSERT INTO `musical_genre` (`uuid`, `name`, `slug`, `description`, `image`) VALUES
('21100f72-8f7e-4d49-94b5-10f990b656e2', 'Enzo Guyot', 'enzo-guyot-21100f72', 'Maybe nothing in this world happens by accident. As everything happens for a reason, our destiny slowly takes form.', 'http://www.thomas-roy.name:49571/#ea'),
('47fe2123-5fa2-49ea-b91f-c57ccb681557', 'André Victor', 'andre-victor-47fe2123', 'If I can\'t even protect my captain\'s dream, then whatever ambition I have is nothing but talk! Luffy must be the man who becomes the Pirate King!', 'https://www.xn--la-brunet-b4a.net/ea/voluptatumquia?quia=et&esse=magnam'),
('5a3fa0f6-66c0-4eab-8d52-42053d3eccb6', 'Mlle Maxime Mathieu', 'mlle-maxime-mathieu-5a3fa0f6', 'I want to live!', 'http://www.ines-xn--grard-bsa.name/asperiores/beatae?optio=autem&repudiandae=dolor'),
('5f822d28-1fae-4b78-9eee-1bb4c68d17fd', 'Charpentier Sarah', 'charpentier-sarah-5f822d28', 'I want to live!', 'http://www.romain-carpentier.org/?blanditiis=velit&sequi=distinctio'),
('c009130f-b3de-4daa-bd42-e6b54124d2a5', 'Léa Perrot', 'lea-perrot-c009130f', 'The government says your existence is a crime, but no matter what kind of weapons you may hold, just being alive isn\'t a sin! There\'s no crime in living!', 'http://www.baptiste-xn--prvost-cva.name:43900/modi/et');

-- --------------------------------------------------------

--
-- Structure de la table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
CREATE TABLE IF NOT EXISTS `playlist` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text NOT NULL,
  `is_private` bit(1) NOT NULL,
  `shared_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKa19bvf0yg4fu0lbc6dpt71od3` (`slug`),
  UNIQUE KEY `UK3un3x4tnolvs0ro8eswyt0w8d` (`shared_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `playlist`
--

INSERT INTO `playlist` (`uuid`, `name`, `slug`, `created_at`, `description`, `is_private`, `shared_uuid`) VALUES
('16655144-cf0a-4c94-85bf-98bddfe1dbe8', 'Dubois Noah', 'dubois-noah-16655144', '2025-04-08 11:14:23.333061', 'I g-guess you guys aren\'t ready for that yet. But your kids are gonna love it.', b'0', NULL),
('4f3bc134-5676-4071-92f7-2f24ecf775c4', 'Alice Lemoine', 'alice-lemoine-4f3bc134', '2025-04-08 11:14:23.201265', 'Oh, honey, he\'s teasing you. Nobody has two television sets.', b'0', NULL),
('549e0c31-b402-4ff0-913b-49be7cd358f6', 'Dubois Thomas', 'dubois-thomas-549e0c31', '2025-04-08 11:14:23.141362', 'Roads? Where we\'re going, we don\'t need roads.', b'0', NULL),
('70d5518c-5f36-43b4-b303-7aa51f7d907a', 'M Jade Thomas', 'm-jade-thomas-70d5518c', '2025-04-08 11:14:23.286396', 'Wait a minute. Wait a minute, Doc. Ah... Are you telling me that you built a time machine... out of a DeLorean?', b'0', NULL),
('d35d1d1b-c011-4963-a05b-9dd717974f38', 'Anaïs Durand', 'anais-durand-d35d1d1b', '2025-04-08 11:14:23.244115', 'Let\'s see if you bastards can do 90.', b'0', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `shared`
--

DROP TABLE IF EXISTS `shared`;
CREATE TABLE IF NOT EXISTS `shared` (
  `uuid` varchar(255) NOT NULL,
  `expire_at` datetime(6) NOT NULL,
  `remaining_invitation` int(11) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `playlist_uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKorjxw722amul9weuvvyx81sqc` (`slug`),
  UNIQUE KEY `UKir9yassb2hhl1x8j0geoia4rp` (`playlist_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `song`
--

DROP TABLE IF EXISTS `song`;
CREATE TABLE IF NOT EXISTS `song` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `created_at` date NOT NULL,
  `duration` bigint(20) NOT NULL,
  `number_of_listen` bigint(20) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKa19bvf0yg4fu0lbc6dpt71od3` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `song`
--

INSERT INTO `song` (`uuid`, `name`, `slug`, `created_at`, `duration`, `number_of_listen`) VALUES
('14c1593e-2605-4ec2-94ce-fa90e4ae1503', 'Prof Gabriel Lambert', 'prof-gabriel-lambert-14c1593e', '2017-10-30', 0, 0),
('1e1c43b7-24dc-45c4-8830-4cadb328f16d', 'Noah Nicolas', 'noah-nicolas-1e1c43b7', '2018-12-17', 0, 0),
('21483d2f-ac83-4348-b88e-45076734d824', 'Girard Maxence', 'girard-maxence-21483d2f', '2019-09-07', 0, 0),
('68c0bbdc-baea-4a7b-8920-b3379ea0bcb8', 'Thomas Pierre', 'thomas-pierre-68c0bbdc', '2021-11-09', 0, 0),
('e7bb3a28-4b1d-40a2-8692-810dd2ba1bf0', 'Chevalier Pauline', 'chevalier-pauline-e7bb3a28', '2021-03-23', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `song_album`
--

DROP TABLE IF EXISTS `song_album`;
CREATE TABLE IF NOT EXISTS `song_album` (
  `position` int(11) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `album_uuid` varchar(255) NOT NULL,
  `song_uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`album_uuid`,`song_uuid`),
  UNIQUE KEY `UK2j5eqtuk47qo688ydafsa74h2` (`slug`),
  KEY `FKjs0hbnhiq3i61oru0ett7wqfd` (`song_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `song_album`
--

INSERT INTO `song_album` (`position`, `slug`, `uuid`, `album_uuid`, `song_uuid`) VALUES
(1, 'prof-gabriel-lambert-14c1593e_dr-louise-moulin-02045da0', '02914397-8862-4d09-9635-d58e5986504b', '02045da0-2a4a-4712-817c-99deed86659c', '14c1593e-2605-4ec2-94ce-fa90e4ae1503'),
(1, 'thomas-pierre-68c0bbdc_pierre-louise-72fa0fd9', 'db5d3fdb-e0fd-424e-a9cb-36fd144a8ace', '72fa0fd9-594d-4bae-9918-e8bd66732f07', '68c0bbdc-baea-4a7b-8920-b3379ea0bcb8'),
(1, 'girard-maxence-21483d2f_m-lola-robin-8888261f', '2fd722f0-4c95-4e8b-a123-2bb93a0d76d0', '8888261f-d55e-402f-b982-0bf083a592c7', '21483d2f-ac83-4348-b88e-45076734d824'),
(1, 'girard-maxence-21483d2f_bourgeois-julie-d68853cb', 'd6746bbe-9678-49ab-8a6c-63d97608fb80', 'd68853cb-a6a0-4112-92c9-16aeb15d6a67', '21483d2f-ac83-4348-b88e-45076734d824'),
(1, 'chevalier-pauline-e7bb3a28_pierre-lemoine-e324e0ed', '14679dd5-903a-421c-bd64-7ae1b5e81199', 'e324e0ed-ee12-4432-9de5-7db2520773c9', 'e7bb3a28-4b1d-40a2-8692-810dd2ba1bf0');

-- --------------------------------------------------------

--
-- Structure de la table `song_artist`
--

DROP TABLE IF EXISTS `song_artist`;
CREATE TABLE IF NOT EXISTS `song_artist` (
  `is_principal_artist` bit(1) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `artist_uuid` varchar(255) NOT NULL,
  `song_uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`artist_uuid`,`song_uuid`),
  UNIQUE KEY `UKk5lwdvivtlal95747yg64ijua` (`slug`),
  KEY `FKcemtkbcmb4bntyrhhhnosyut` (`song_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `song_artist`
--

INSERT INTO `song_artist` (`is_principal_artist`, `slug`, `uuid`, `artist_uuid`, `song_uuid`) VALUES
(b'1', 'noah-nicolas-1e1c43b7_76022886-e9ab-450c-9b11-ba43cb0beef9', '479c572f-01b7-4044-ab96-c0b2d0921f7b', '76022886-e9ab-450c-9b11-ba43cb0beef9', '1e1c43b7-24dc-45c4-8830-4cadb328f16d'),
(b'1', 'prof-gabriel-lambert-14c1593e_863ff2a1-e4b7-4460-a0c9-d98bcb818e0f', 'b925a697-7440-48ea-b89c-568f19a38f7e', '863ff2a1-e4b7-4460-a0c9-d98bcb818e0f', '14c1593e-2605-4ec2-94ce-fa90e4ae1503'),
(b'1', 'prof-gabriel-lambert-14c1593e_9782504f-8c8c-4cff-8a54-4490108d6689', '398bd5a5-1373-411c-9e05-1576d20c008d', '9782504f-8c8c-4cff-8a54-4490108d6689', '14c1593e-2605-4ec2-94ce-fa90e4ae1503'),
(b'1', 'prof-gabriel-lambert-14c1593e_cc259778-8019-424a-8eca-316006f7e666', '52770ac1-7a7e-4417-8ebc-585cac7793eb', 'cc259778-8019-424a-8eca-316006f7e666', '14c1593e-2605-4ec2-94ce-fa90e4ae1503'),
(b'1', 'thomas-pierre-68c0bbdc_ce6d3b5c-dbf2-45c8-b9ee-c3eba7106318', 'ae69e5f7-90d5-44a3-bbb8-25151ff02477', 'ce6d3b5c-dbf2-45c8-b9ee-c3eba7106318', '68c0bbdc-baea-4a7b-8920-b3379ea0bcb8');

-- --------------------------------------------------------

--
-- Structure de la table `song_musical_genre`
--

DROP TABLE IF EXISTS `song_musical_genre`;
CREATE TABLE IF NOT EXISTS `song_musical_genre` (
  `song_uuid` varchar(255) NOT NULL,
  `musical_genre_uuid` varchar(255) NOT NULL,
  KEY `FK8cubs8pxf6519xn44xeuuuo0m` (`musical_genre_uuid`),
  KEY `FK9ht3padpsffkw3epw5yu1931f` (`song_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `song_playlist`
--

DROP TABLE IF EXISTS `song_playlist`;
CREATE TABLE IF NOT EXISTS `song_playlist` (
  `uuid` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `position` int(11) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `contributor_playlist_uuid` varchar(255) DEFAULT NULL,
  `contributor_user_uuid` varchar(255) DEFAULT NULL,
  `playlist_uuid` varchar(255) NOT NULL,
  `song_uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKago4eatwn1vwij1mikuntysik` (`slug`),
  KEY `FK6pfyf3itv8vjxcodmhpkut4ym` (`contributor_playlist_uuid`,`contributor_user_uuid`),
  KEY `FKob04uo0xeoymvsaasfu6rlex8` (`playlist_uuid`),
  KEY `FKlaijk5mb0583kujfqeaaw21ym` (`song_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `song_playlist`
--

INSERT INTO `song_playlist` (`uuid`, `created_at`, `position`, `slug`, `contributor_playlist_uuid`, `contributor_user_uuid`, `playlist_uuid`, `song_uuid`) VALUES
('03a33869-1f8a-4376-a698-eb546f8aa94c', '2025-04-08 11:14:23.236823', 0, 'thomas-pierre-68c0bbdcalice-lemoine-4f3bc1340', '4f3bc134-5676-4071-92f7-2f24ecf775c4', '76022886-e9ab-450c-9b11-ba43cb0beef9', '4f3bc134-5676-4071-92f7-2f24ecf775c4', '68c0bbdc-baea-4a7b-8920-b3379ea0bcb8'),
('21e7c301-1670-47cb-89af-72cbb127366b', '2025-04-08 11:14:23.324945', 0, 'prof-gabriel-lambert-14c1593em-jade-thomas-70d5518c0', '70d5518c-5f36-43b4-b303-7aa51f7d907a', '0d5074eb-aa56-4054-a9d6-c525cae23602', '70d5518c-5f36-43b4-b303-7aa51f7d907a', '14c1593e-2605-4ec2-94ce-fa90e4ae1503'),
('38739988-9486-48e0-9170-79bbeb18c7b8', '2025-04-08 11:14:23.278960', 0, 'chevalier-pauline-e7bb3a28anais-durand-d35d1d1b0', 'd35d1d1b-c011-4963-a05b-9dd717974f38', '91c3baff-f582-4eb9-839b-cfa39898c5e2', 'd35d1d1b-c011-4963-a05b-9dd717974f38', 'e7bb3a28-4b1d-40a2-8692-810dd2ba1bf0'),
('43d09f07-0ead-42eb-ae12-3780af4a3b3f', '2025-04-08 11:14:23.194297', 0, 'girard-maxence-21483d2fdubois-thomas-549e0c310', '549e0c31-b402-4ff0-913b-49be7cd358f6', '51869558-4b8c-4c5d-98e7-1127295b15b1', '549e0c31-b402-4ff0-913b-49be7cd358f6', '21483d2f-ac83-4348-b88e-45076734d824'),
('f93c4f17-a381-4008-92e3-6ebea5424dff', '2025-04-08 11:14:23.375353', 0, 'noah-nicolas-1e1c43b7dubois-noah-166551440', '16655144-cf0a-4c94-85bf-98bddfe1dbe8', '0d5074eb-aa56-4054-a9d6-c525cae23602', '16655144-cf0a-4c94-85bf-98bddfe1dbe8', '1e1c43b7-24dc-45c4-8830-4cadb328f16d');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `uuid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `birth_at` date NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `UKa19bvf0yg4fu0lbc6dpt71od3` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`uuid`, `name`, `slug`, `birth_at`, `created_at`, `email`, `first_name`, `last_name`, `password`) VALUES
('57d3a6fc-8b03-471e-a2cf-60b59c6ce68e', 'Prof Nicolas Pierre', 'prof-nicolas-pierre-57d3a6fc', '1960-12-31', '2025-04-08 11:14:21.229337', 'mathis.laurent@yahoo.fr', 'Zoe', 'Marty', '$2a$10$xrDC5sYU3U3EF0ALfy7QDOj7OZ.ZqFkL/yUW0xKJKgok9IjwC6Chy'),
('76373348-8c0e-4ccc-8468-81478657d162', 'Brun Sacha', 'brun-sacha-76373348', '1994-07-27', '2025-04-08 11:14:21.145238', 'lou.duval@gmail.com', 'Baptiste', 'Francois', '$2a$10$N/AqhC7XrZtffnOgQ50BjupXtokmUytkU5s8RS0sLE6CfjlRSIRpm'),
('885e197a-6909-4868-9f6a-5f0caece2001', 'Mlle Arthur Muller', 'mlle-arthur-muller-885e197a', '2002-03-04', '2025-04-08 11:14:21.305153', 'evan.perrot@yahoo.fr', 'Lola', 'Leroux', '$2a$10$1CUHksDlVCOnAQJLhPNP8.vHrb.zXntOAK4nwY5z.6bGe4hrnSQq.'),
('e8e6ddd1-2794-46cd-bf53-7bf0c5a1dbdd', 'Mme Julien André', 'mme-julien-andre-e8e6ddd1', '1965-11-15', '2025-04-08 11:14:21.459140', 'kylian.joly@yahoo.fr', 'Kylian', 'Méunier', '$2a$10$/E2UMATXLcF4Cf7KAiuFo.nSPhngQob.y2m8kfVESG/4TFsuQT/yG'),
('efcae0ab-9890-48dd-b330-25d0fd470182', 'Mattéo Le roux', 'matteo-le-roux-efcae0ab', '1970-10-28', '2025-04-08 11:14:21.380554', 'ambre.simon@yahoo.fr', 'Elisa', 'Baron', '$2a$10$ytZT4B3xTK.qUV/2xrGbY.SUGSuSTVmMbfi1Bl4aASuTM8sIb7GTq');

-- --------------------------------------------------------

--
-- Structure de la table `user_likeable_item`
--

DROP TABLE IF EXISTS `user_likeable_item`;
CREATE TABLE IF NOT EXISTS `user_likeable_item` (
  `add_at` datetime(6) NOT NULL,
  `user_uuid` varchar(255) NOT NULL,
  `likeable_item_uuid` varchar(255) NOT NULL,
  PRIMARY KEY (`likeable_item_uuid`,`user_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user_likeable_item`
--

INSERT INTO `user_likeable_item` (`add_at`, `user_uuid`, `likeable_item_uuid`) VALUES
('2025-04-08 11:14:22.769777', '0d5074eb-aa56-4054-a9d6-c525cae23602', '0d5074eb-aa56-4054-a9d6-c525cae23602'),
('2025-04-08 11:14:22.658578', '3a4a0222-1d57-4d31-ba74-c16154f9843c', '91c3baff-f582-4eb9-839b-cfa39898c5e2'),
('2025-04-08 11:14:22.714792', '863ff2a1-e4b7-4460-a0c9-d98bcb818e0f', '9782504f-8c8c-4cff-8a54-4490108d6689'),
('2025-04-08 11:14:22.881766', 'efcae0ab-9890-48dd-b330-25d0fd470182', 'ce6d3b5c-dbf2-45c8-b9ee-c3eba7106318'),
('2025-04-08 11:14:22.825944', 'ce6d3b5c-dbf2-45c8-b9ee-c3eba7106318', 'e8e6ddd1-2794-46cd-bf53-7bf0c5a1dbdd');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `contributor`
--
ALTER TABLE `contributor`
  ADD CONSTRAINT `FK8w2ktglqps6ghfnwlqkasy28p` FOREIGN KEY (`playlist_uuid`) REFERENCES `playlist` (`uuid`);

--
-- Contraintes pour la table `historical`
--
ALTER TABLE `historical`
  ADD CONSTRAINT `FKm6b5s4d07ro0nfbhh9paewqem` FOREIGN KEY (`song_uuid`) REFERENCES `song` (`uuid`);

--
-- Contraintes pour la table `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `FKjj8a7um5s0o5kh1h30uimycn5` FOREIGN KEY (`shared_uuid`) REFERENCES `shared` (`uuid`);

--
-- Contraintes pour la table `shared`
--
ALTER TABLE `shared`
  ADD CONSTRAINT `FK4fqdfseb2aouy79ef7iursfvw` FOREIGN KEY (`playlist_uuid`) REFERENCES `playlist` (`uuid`);

--
-- Contraintes pour la table `song_album`
--
ALTER TABLE `song_album`
  ADD CONSTRAINT `FKjs0hbnhiq3i61oru0ett7wqfd` FOREIGN KEY (`song_uuid`) REFERENCES `song` (`uuid`),
  ADD CONSTRAINT `FKrnwl5eyuobtvekqv1us90i1kn` FOREIGN KEY (`album_uuid`) REFERENCES `album` (`uuid`);

--
-- Contraintes pour la table `song_artist`
--
ALTER TABLE `song_artist`
  ADD CONSTRAINT `FKcemtkbcmb4bntyrhhhnosyut` FOREIGN KEY (`song_uuid`) REFERENCES `song` (`uuid`);

--
-- Contraintes pour la table `song_musical_genre`
--
ALTER TABLE `song_musical_genre`
  ADD CONSTRAINT `FK8cubs8pxf6519xn44xeuuuo0m` FOREIGN KEY (`musical_genre_uuid`) REFERENCES `musical_genre` (`uuid`),
  ADD CONSTRAINT `FK9ht3padpsffkw3epw5yu1931f` FOREIGN KEY (`song_uuid`) REFERENCES `song` (`uuid`);

--
-- Contraintes pour la table `song_playlist`
--
ALTER TABLE `song_playlist`
  ADD CONSTRAINT `FK6pfyf3itv8vjxcodmhpkut4ym` FOREIGN KEY (`contributor_playlist_uuid`,`contributor_user_uuid`) REFERENCES `contributor` (`playlist_uuid`, `user_uuid`),
  ADD CONSTRAINT `FKlaijk5mb0583kujfqeaaw21ym` FOREIGN KEY (`song_uuid`) REFERENCES `song` (`uuid`),
  ADD CONSTRAINT `FKob04uo0xeoymvsaasfu6rlex8` FOREIGN KEY (`playlist_uuid`) REFERENCES `playlist` (`uuid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
