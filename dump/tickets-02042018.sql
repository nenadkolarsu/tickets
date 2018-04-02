-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2018 at 10:13 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tickets`
--
CREATE DATABASE IF NOT EXISTS `tickets` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `tickets`;

-- --------------------------------------------------------

--
-- Table structure for table `cinemas`
--

DROP TABLE IF EXISTS `cinemas`;
CREATE TABLE `cinemas` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserve` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `timestamp` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `cinemas`
--

INSERT INTO `cinemas` (`id`, `action`, `code`, `name`, `remark`, `reserve`, `status`, `timestamp`) VALUES
(1, NULL, '1', 'First cinema', '', NULL, b'1111111111111111111111111111111', '2018-04-01 07:59:57'),
(2, NULL, '2', 'Second cinema', 'rem', NULL, b'1111111111111111111111111111111', '2018-04-01 08:00:14');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(11),
(11),
(11);

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserve` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `timestamp` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `action`, `code`, `name`, `remark`, `reserve`, `status`, `timestamp`) VALUES
(5, NULL, '', 'Harry Potter', '', NULL, b'1111111111111111111111111111111', '2018-04-01 11:05:29'),
(6, NULL, '', 'Once Upon a Time in America', '', NULL, b'1111111111111111111111111111111', '2018-04-01 11:06:14');

-- --------------------------------------------------------

--
-- Table structure for table `projections`
--

DROP TABLE IF EXISTS `projections`;
CREATE TABLE `projections` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `movie` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `projection_date` date DEFAULT NULL,
  `projection_datetime` datetime DEFAULT NULL,
  `projection_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserve` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `id_movie` bigint(20) NOT NULL,
  `id_theatre` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `projections`
--

INSERT INTO `projections` (`id`, `action`, `code`, `movie`, `projection_date`, `projection_datetime`, `projection_time`, `remark`, `reserve`, `status`, `timestamp`, `id_movie`, `id_theatre`) VALUES
(1, NULL, '', NULL, '2018-04-01', NULL, '19:25', '', NULL, b'1111111111111111111111111111111', '2018-04-02 16:53:39', 6, 2),
(2, NULL, '3', NULL, '2018-04-01', NULL, '02:02', '', NULL, b'1111111111111111111111111111111', '2018-04-01 11:22:52', 5, 2),
(3, NULL, '4', NULL, '2018-04-02', NULL, '16:00', '', NULL, b'1111111111111111111111111111111', '2018-04-02 16:50:36', 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE `reservations` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserve` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `row` int(11) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `id_projection` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `rows` bigint(20) NOT NULL,
  `seats` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`id`, `action`, `remark`, `reserve`, `row`, `seat`, `status`, `timestamp`, `id_projection`, `name`, `rows`, `seats`) VALUES
(8, NULL, '', NULL, 1, 3, b'1111111111111111111111111111111', '2018-04-01 13:49:14', 1, 'Marko', 0, 0),
(9, NULL, '', NULL, 10, 4, b'1111111111111111111111111111111', '2018-04-02 16:52:09', 1, 'Joe', 0, 0),
(10, NULL, '', NULL, 10, 3, b'1111111111111111111111111111111', '2018-04-02 18:46:49', 1, 'Bob', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `theatres`
--

DROP TABLE IF EXISTS `theatres`;
CREATE TABLE `theatres` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserve` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `id_cinema` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `theatres`
--

INSERT INTO `theatres` (`id`, `action`, `code`, `name`, `remark`, `reserve`, `status`, `timestamp`, `id_cinema`) VALUES
(1, NULL, '1', 'First theatre', 'rem', NULL, b'1111111111111111111111111111111', '2018-04-01 08:02:00', 2),
(2, NULL, '1', 'Second theatre -', 'rem', NULL, b'1111111111111111111111111111111', '2018-04-01 11:15:37', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cinemas`
--
ALTER TABLE `cinemas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projections`
--
ALTER TABLE `projections`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkch74bc0bxbjaiv2tgl04o7wk` (`id_movie`),
  ADD KEY `FKoh0h3cmqjk5cxf5o5dv3p37n3` (`id_theatre`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs66dl6bs8dc2gbstv51kfdnh1` (`id_projection`);

--
-- Indexes for table `theatres`
--
ALTER TABLE `theatres`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtosel06pvwnkwcike0cw2yxwb` (`id_cinema`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `projections`
--
ALTER TABLE `projections`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `theatres`
--
ALTER TABLE `theatres`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
