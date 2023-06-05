-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2023 at 11:38 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tictactoe`
--
CREATE DATABASE IF NOT EXISTS `tictactoe` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tictactoe`;

-- --------------------------------------------------------

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
CREATE TABLE IF NOT EXISTS `board` (
  `board_id` int(11) NOT NULL,
  `board_size` int(11) NOT NULL,
  `board_size_name` varchar(255) NOT NULL,
  PRIMARY KEY (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `board`
--

INSERT INTO `board` (`board_id`, `board_size`, `board_size_name`) VALUES
(0, 3, 'Default (3X3)'),
(1, 5, '4X4'),
(2, 5, '5X5');

-- --------------------------------------------------------

--
-- Table structure for table `difficulty`
--

DROP TABLE IF EXISTS `difficulty`;
CREATE TABLE IF NOT EXISTS `difficulty` (
  `difficulty_id` int(11) NOT NULL,
  `difficult_mode` varchar(255) NOT NULL,
  PRIMARY KEY (`difficulty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `difficulty`
--

INSERT INTO `difficulty` (`difficulty_id`, `difficult_mode`) VALUES
(0, 'easy'),
(1, 'hard');

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
  `player_id` int(11) NOT NULL,
  `player_username` varchar(255) NOT NULL,
  `player_win_count` int(11) NOT NULL,
  `player_lose_count` int(11) NOT NULL,
  `player_play_time` time NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`player_id`, `player_username`, `player_win_count`, `player_lose_count`, `player_play_time`) VALUES
(1, 'Sun', 0, 0, '00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `player_match`
--

DROP TABLE IF EXISTS `player_match`;
CREATE TABLE IF NOT EXISTS `player_match` (
  `match_id` int(11) NOT NULL AUTO_INCREMENT,
  `board_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `match_win_count` int(11) NOT NULL,
  `match_lose_count` int(11) NOT NULL,
  PRIMARY KEY (`match_id`),
  KEY `player_id` (`player_id`),
  KEY `board_id` (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
CREATE TABLE IF NOT EXISTS `setting` (
  `setting_id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `board_id` int(11) NOT NULL,
  `difficulty_id` int(11) NOT NULL,
  `setting_show_timer` tinyint(1) NOT NULL,
  `setting_show_board_info` tinyint(1) NOT NULL,
  `setting_show_player_win_count` tinyint(1) NOT NULL,
  `gamemode` int(1) NOT NULL,
  `setting_background_music` tinyint(1) NOT NULL,
  PRIMARY KEY (`setting_id`),
  KEY `player_id` (`player_id`),
  KEY `board_id` (`board_id`),
  KEY `difficulty_id` (`difficulty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `setting`
--

INSERT INTO `setting` (`setting_id`, `player_id`, `board_id`, `difficulty_id`, `setting_show_timer`, `setting_show_board_info`, `setting_show_player_win_count`, `gamemode`, `setting_background_music`) VALUES
(1, 1, 0, 0, 1, 1, 1, 0, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `player_match`
--
ALTER TABLE `player_match`
  ADD CONSTRAINT `player_match_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`),
  ADD CONSTRAINT `player_match_ibfk_2` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`);

--
-- Constraints for table `setting`
--
ALTER TABLE `setting`
  ADD CONSTRAINT `setting_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`),
  ADD CONSTRAINT `setting_ibfk_2` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`),
  ADD CONSTRAINT `setting_ibfk_3` FOREIGN KEY (`difficulty_id`) REFERENCES `difficulty` (`difficulty_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
