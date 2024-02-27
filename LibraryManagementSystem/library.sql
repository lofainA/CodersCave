-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 27, 2024 at 05:00 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `admin_id` int(10) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`admin_id`, `Name`, `Username`, `Password`) VALUES
(1, 'Steve William', 'steven023', '1234'),
(2, 'Ross Lynn', 'rossix12', '1234'),
(3, 'tester', 'test', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `Book_ID` int(10) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Author` varchar(50) NOT NULL,
  `Genre` varchar(20) NOT NULL,
  `Stock` int(4) NOT NULL,
  `Status` varchar(100) NOT NULL DEFAULT '"Available"'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`Book_ID`, `Name`, `Author`, `Genre`, `Stock`, `Status`) VALUES
(1, 'The Pilgrim’s Progress', 'John Bunyan', 'Religion', 9, 'available'),
(2, 'Robinson Crusoe', ' Daniel Defoe', 'Adventure', 22, 'available'),
(3, 'Gulliver’s Travels', 'Jonathan Swift', 'Adventure', 56, 'available'),
(4, 'Clarissa', 'Samuel Richardson', 'Psychological', 14, 'available'),
(5, 'Tom Jones', 'Henry Fielding', 'Biography', 33, 'available'),
(6, 'The Life and Opinions of Tristram Shandy, Gentleman', 'Laurence Sterne', 'Romance', 10, 'available'),
(7, 'Emma', 'Jane Austen', 'Thriller', 54, 'available'),
(8, 'Frankenstein ', 'Mary Shelley', 'Thriller', 5, 'available'),
(9, 'Nightmare Abbey', 'Thomas Love Peacock', 'Horror', 12, 'available'),
(10, 'The Narrative of Arthur Gordon Pym of Nantucket', 'Edgar Allan Poe', 'Adventure', 29, 'available'),
(11, 'Sybil', 'Benjamin Disraeli', 'Politics', 1, 'available');

--
-- Triggers `books`
--
DELIMITER $$
CREATE TRIGGER `update_status` BEFORE UPDATE ON `books` FOR EACH ROW BEGIN
    IF NEW.stock = 0 THEN
        SET NEW.status = 'out of stock';
    ElSEIF NEW.stock > 0 THEN
    	SET NEW.status = 'available';
    ELSE
    	SET NEW.stock = 0;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `borrowedbooks`
--

CREATE TABLE `borrowedbooks` (
  `Book_ID` int(10) NOT NULL,
  `Book_name` varchar(100) NOT NULL,
  `Borrower_phone` varchar(10) NOT NULL,
  `Borrower_name` varchar(50) NOT NULL,
  `Return_date` date NOT NULL,
  `Status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrowedbooks`
--

INSERT INTO `borrowedbooks` (`Book_ID`, `Book_name`, `Borrower_phone`, `Borrower_name`, `Return_date`, `Status`) VALUES
(4, 'Clarissa', '1234', 'test', '2024-03-03', NULL),
(7, 'Emma', '6473881293', 'Margot Robbie', '2024-03-04', NULL),
(11, 'Sybil', '1234', 'test', '2024-03-05', NULL),
(2, 'Robinson Crusoe', '1234', 'test', '2024-03-05', NULL);

--
-- Triggers `borrowedbooks`
--
DELIMITER $$
CREATE TRIGGER `due_status` BEFORE UPDATE ON `borrowedbooks` FOR EACH ROW BEGIN
	IF NEW.return_date > CURRENT_DATE THEN
    	SET NEW.status = 'overdue';
    ELSEIF NEW.return_date = CURRENT_DATE THEN
     	SET NEW.status = 'due';
        
     END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `User_ID` int(10) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Borrowed books` int(2) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User_ID`, `Name`, `Phone`, `Address`, `Borrowed books`) VALUES
(1, 'Mathew Jobs', '237890375', '777 Brockton Avenue', 0),
(2, 'Rita Johnson', '657483923', '30 Memorial Drive', 0),
(3, 'Clement Ruford', '256753626', '250 Hartford Avenue', 0),
(4, 'Mary Wilsburg', '287674532', '700 Oak Street', 0),
(5, 'Hamsford Smith', '7264288764', '66-4 Parkhurst Rd', 0),
(6, 'Bailey Galloper', '364278649', '591 Memorial Dr', 0),
(7, 'Jamie Anderson', '6823822398', '55 Brooksby Village Way', 0),
(8, 'test', '1234', 'somethingg', 0),
(9, 'Margot Robbie', '6473881293', '137 Teaticket Hwy', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`User_ID`),
  ADD UNIQUE KEY `Phone` (`Phone`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
