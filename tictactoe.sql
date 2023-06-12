-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2023 at 08:24 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `board`
--

CREATE TABLE `board` (
  `board_id` int(11) NOT NULL,
  `board_size` int(11) NOT NULL,
  `board_size_name` varchar(255) NOT NULL
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

CREATE TABLE `difficulty` (
  `difficulty_id` int(11) NOT NULL,
  `difficult_mode` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `difficulty`
--

INSERT INTO `difficulty` (`difficulty_id`, `difficult_mode`) VALUES
(0, 'easy'),
(1, 'hard');

-- --------------------------------------------------------

--
-- Table structure for table `lastplayer`
--

CREATE TABLE `lastplayer` (
  `lastRecord` int(11) NOT NULL,
  `player_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lastplayer`
--

INSERT INTO `lastplayer` (`lastRecord`, `player_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `player_id` int(11) NOT NULL,
  `player_username` varchar(255) NOT NULL,
  `player_play_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`player_id`, `player_username`, `player_play_time`) VALUES
(1, 'Sun', '00:05:07'),
(2, 'See', '00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `player_match`
--

CREATE TABLE `player_match` (
  `match_id` int(11) NOT NULL,
  `board_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `difficulty_id` int(11) NOT NULL,
  `match_win_count` int(11) NOT NULL,
  `match_lose_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `player_match`
--

INSERT INTO `player_match` (`match_id`, `board_id`, `player_id`, `difficulty_id`, `match_win_count`, `match_lose_count`) VALUES
(1, 0, 1, 0, 29, 3),
(2, 0, 1, 1, 1, 0),
(3, 1, 1, 1, 1, 0),
(4, 1, 1, 0, 7, 0);

-- --------------------------------------------------------

--
-- Table structure for table `setting`
--

CREATE TABLE `setting` (
  `setting_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `board_id` int(11) NOT NULL,
  `difficulty_id` int(11) NOT NULL,
  `setting_show_timer` tinyint(1) NOT NULL,
  `setting_show_board_info` tinyint(1) NOT NULL,
  `setting_show_player_win_count` tinyint(1) NOT NULL,
  `gamemode` int(1) NOT NULL,
  `setting_background_music` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `setting`
--

INSERT INTO `setting` (`setting_id`, `player_id`, `board_id`, `difficulty_id`, `setting_show_timer`, `setting_show_board_info`, `setting_show_player_win_count`, `gamemode`, `setting_background_music`) VALUES
(1, 1, 1, 0, 1, 1, 1, 0, 0),
(3, 2, 0, 0, 1, 0, 1, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `board`
--
ALTER TABLE `board`
  ADD PRIMARY KEY (`board_id`);

--
-- Indexes for table `difficulty`
--
ALTER TABLE `difficulty`
  ADD PRIMARY KEY (`difficulty_id`);

--
-- Indexes for table `lastplayer`
--
ALTER TABLE `lastplayer`
  ADD PRIMARY KEY (`lastRecord`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`player_id`);

--
-- Indexes for table `player_match`
--
ALTER TABLE `player_match`
  ADD PRIMARY KEY (`match_id`),
  ADD KEY `player_id` (`player_id`),
  ADD KEY `board_id` (`board_id`),
  ADD KEY `difficulty_id` (`difficulty_id`);

--
-- Indexes for table `setting`
--
ALTER TABLE `setting`
  ADD PRIMARY KEY (`setting_id`),
  ADD KEY `player_id` (`player_id`),
  ADD KEY `board_id` (`board_id`),
  ADD KEY `difficulty_id` (`difficulty_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `player_match`
--
ALTER TABLE `player_match`
  MODIFY `match_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `setting`
--
ALTER TABLE `setting`
  MODIFY `setting_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `player_match`
--
ALTER TABLE `player_match`
  ADD CONSTRAINT `player_match_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`),
  ADD CONSTRAINT `player_match_ibfk_2` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`),
  ADD CONSTRAINT `player_match_ibfk_3` FOREIGN KEY (`difficulty_id`) REFERENCES `difficulty` (`difficulty_id`);

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
