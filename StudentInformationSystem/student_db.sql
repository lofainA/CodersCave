-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2024 at 03:08 PM
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
-- Database: `student_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `academic_info`
--

CREATE TABLE `academic_info` (
  `ID` int(4) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Register_no` varchar(20) NOT NULL,
  `sem1_gpa` float NOT NULL,
  `sem2_gpa` float NOT NULL,
  `sem3_gpa` float NOT NULL,
  `sem4_gpa` float NOT NULL,
  `sem5_gpa` float NOT NULL,
  `sem6_gpa` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `academic_info`
--

INSERT INTO `academic_info` (`ID`, `Name`, `Register_no`, `sem1_gpa`, `sem2_gpa`, `sem3_gpa`, `sem4_gpa`, `sem5_gpa`, `sem6_gpa`) VALUES
(1, 'Alan Bates', '211422104001', 7.2, 6.2, 6.2, 7.1, 6.4, 7.8),
(2, 'Alec Simmons', '211422104002', 8.5, 7.4, 7.3, 6.7, 7.6, 6.2),
(3, 'Brandon Chapman', '211422104003', 9.1, 8.5, 8.1, 6.8, 8.2, 8.5),
(4, 'Brock Talley', '211422104004', 8.8, 6.6, 6.8, 8.2, 6.8, 6.9),
(5, 'Brock Warren', '211422104005', 6.7, 7.7, 7.6, 5.9, 7.4, 7.7),
(6, 'Burke Humphrey', '211422104006', 5.5, 8.8, 8.7, 8.5, 8.3, 8.1),
(7, 'Chava Holloway', '211422104007', 9.7, 6.9, 6.9, 9.5, 6.9, 6.4),
(8, 'Damon Fernandez', '211422104008', 6.8, 7.1, 7.2, 7.9, 7.1, 7.2),
(9, 'Daria Christian', '211422104009', 8.2, 8.2, 8.3, 6.4, 8.6, 8.3),
(10, 'Donovan Norris', '211422104010', 9.4, 7.3, 7.4, 8.1, 7.3, 7.4),
(11, 'Guinevere Mack', '211422104011', 7.5, 6.4, 6.5, 5.5, 6.6, 6.8),
(12, 'Hakeem Talley', '211422104012', 5.8, 8.6, 8.6, 9.2, 8.7, 8.6),
(13, 'Idola Fischer', '211422104013', 8.9, 9.1, 9, 6.6, 9, 9),
(14, 'Idona Mercado', '211422104014', 9.3, 7.5, 7.5, 7.6, 7.9, 7.5),
(15, 'India Acevedo', '211422104015', 7.9, 8.3, 8.2, 9.1, 8.4, 8.2),
(16, 'Jana Talley', '211422104016', 8.1, 9.2, 9.2, 5.2, 9.2, 9.2),
(17, 'Jaquelyn Richmond', '211422104017', 6.4, 7.6, 7.7, 8.9, 7.8, 7.6),
(18, 'Jayme Mcleod', '211422104018', 7.8, 6.7, 6.7, 7.3, 6.7, 6.7),
(19, 'Kaitlin Shepherd', '211422104019', 8.7, 9.4, 9.4, 8.7, 9.3, 9.4),
(20, 'Kirk Fischer', '211422104020', 9.2, 9.7, 9.7, 5.7, 9.7, 9.7),
(21, 'Kitra Horne', '211422104021', 5.9, 5.8, 5.8, 9.7, 5.5, 5.8),
(22, 'Lyle Bowers', '211422104022', 6.6, 7.5, 7.1, 8, 7.8, 6.4),
(23, 'Madeson Stokes', '211422104023', 6.2, 6.5, 6.6, 6.1, 6.3, 6.5),
(24, 'Marshall Whitley', '211422104024', 8.3, 6.1, 6.3, 7.5, 6.1, 6.1),
(25, 'Octavius Lindsey', '211422104025', 8.6, 9, 9.1, 9.3, 9.1, 9.1),
(26, 'Oleg Mclean', '211422104026', 9.6, 9.5, 9.5, 5.3, 9.6, 9.5),
(27, 'Orla Fields', '211422104027', 9, 7.8, 7.8, 6.9, 7.5, 7.3),
(28, 'Perry Durham', '211422104028', 7.4, 9.1, 7.5, 8.8, 6.3, 8),
(29, 'Priscilla Harmon', '211422104029', 7.1, 8.4, 8.4, 7.8, 8.5, 8.4),
(30, 'Rachel George', '211422104030', 9.5, 5.6, 5.9, 6.2, 5.9, 5.9),
(31, 'Rahim Alston', '211422104031', 5.7, 5.1, 5.5, 5.6, 5.3, 5.5),
(32, 'Roanna Pollard', '211422104032', 7, 5.5, 5.3, 7.4, 5.7, 5.3),
(33, 'Sade Gonzalez', '211422104033', 8.4, 5.3, 5.7, 9.6, 5.1, 5.7),
(34, 'Serina Mayer', '211422104034', 6.3, 9.3, 9.3, 8.4, 9.4, 9.3),
(35, 'Xandra Mccullough', '211422104035', 6.9, 6.3, 6.4, 6.3, 6.5, 6.6),
(36, 'Yeo Melton', '211422104036', 5.6, 5.7, 5.6, 5.8, 5.6, 6.1),
(37, 'Yoko Hinton', '211422104037', 5.4, 5.2, 5.2, 5.4, 5.2, 7),
(38, 'Yvonne Rowland', '211422104038', 4.7, 8.9, 5.4, 5.1, 5.4, 7.5),
(39, 'Zane Chaney', '211422104039', 7.5, 5.9, 5.1, 9, 5.8, 8.2),
(40, 'Zoe Harrell', '211422104040', 4.2, 5, 7.8, 8.4, 9.3, 9.4);

-- --------------------------------------------------------

--
-- Table structure for table `stpersonal_info`
--

CREATE TABLE `stpersonal_info` (
  `ID` int(10) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Register_no` varchar(20) NOT NULL,
  `DOB` date NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `E-mail` varchar(50) NOT NULL,
  `Sex` varchar(1) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Quota` varchar(20) NOT NULL,
  `CGPA` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stpersonal_info`
--

INSERT INTO `stpersonal_info` (`ID`, `Name`, `Register_no`, `DOB`, `Phone`, `E-mail`, `Sex`, `Address`, `Quota`, `CGPA`) VALUES
(1, 'Alan Bates', '211422104001', '2004-10-12', '779-1877', 'adipiscing.enim@gmail.com', 'M', '123 Maple St', 'Counselling', 7.3),
(2, 'Alec Simmons', '211422104002', '2004-01-01', '553-6832', 'donec@gmail.com', 'M', '456 Oak Ave', 'Counselling', 6.7),
(3, 'Brandon Chapman', '211422104003', '2003-06-20', '814-4868', 'libero.proin@gmail.com', 'M', '789 Pine Ln', 'Management', 6.5),
(4, 'Brock Talley', '211422104004', '2004-12-20', '752-3696', 'at.sem.molestie@outlook.com', 'M', '101 Elm St', 'Counselling', 7.2),
(5, 'Brock Warren', '211422104005', '2004-07-13', '483-6289', 'mattis.velit.justo@gmail.com', 'M', '202 Birch Rd', 'Management', 8.1),
(6, 'Burke Humphrey', '211422104006', '2004-09-23', '181-0887', 'duis@outlook.com', 'M', '303 Cedar Ave', 'Management', 7.4),
(7, 'Chava Holloway', '211422104007', '2003-11-09', '612-3326', 'mi.felis@yahoo.com', 'F', '404 Spruce Ln', 'Counselling', 7.6),
(8, 'Damon Fernandez', '211422104008', '2004-02-29', '224-1694', 'morbi.accumsan@yahoo.com', 'M', '505 Willow Rd', 'Management', 8.5),
(9, 'Daria Christian', '211422104009', '2002-12-19', '728-4261', 'dignissim@outlook.com', 'F', '606 Ash St', 'Counselling', 7.9),
(10, 'Donovan Norris', '211422104010', '2003-08-24', '471-4496', 'nascetur.ridiculus@outlook.com', 'M', '707 Poplar Ave', 'Counselling', 7.3),
(11, 'Guinevere Mack', '211422104011', '2004-06-18', '168-1573', 'magna.a@outlook.com', 'F', '808 Chestnut Ln', 'Management', 7),
(12, 'Hakeem Talley', '211422104012', '2004-08-27', '368-5343', 'tincidunt@yahoo.com', 'M', '909 Walnut St', 'Management', 8),
(13, 'Idola Fischer', '211422104013', '2003-03-18', '867-5663', 'cursus@gmail.com', 'F', '111 Pinecone Ave', 'Counselling', 7.2),
(14, 'Idona Mercado', '211422104014', '2004-06-08', '264-5210', 'semper.nam@outlook.com', 'F', '222 Maplewood Rd', 'Counselling', 8),
(15, 'India Acevedo', '211422104015', '2003-05-22', '743-9703', 'dictum@yahoo.com', 'F', '333 Cedarwood Ln', 'Management', 7.8),
(16, 'Jana Talley', '211422104016', '2004-08-29', '666-0152', 'malesuada.id@gmail.com', 'F', '444 Elmwood St', 'Counselling', 6.8),
(17, 'Jaquelyn Richmond', '211422104017', '2003-11-03', '223-1295', 'nunc.laoreet.lectus@outlook.com', 'F', '555 Birchwood Ave', 'Management', 8.1),
(18, 'Jayme Mcleod', '211422104018', '2004-04-07', '936-0165', 'feugiat@outlook.com', 'M', '666 Oakwood Rd', 'Counselling', 6.7),
(19, 'Kaitlin Shepherd', '211422104019', '2003-03-21', '440-8638', 'vel.arcu.eu@yahoo.com', 'F', '777 Willowwood Ln', 'Management', 7.9),
(20, 'Kirk Fischer', '211422104020', '2004-12-12', '514-1927', 'neque.tellus@yahoo.com', 'M', '888 Sprucewood St', 'Management', 7.6),
(21, 'Kitra Horne', '211422104021', '2004-04-03', '983-5040', 'facilisis.vitae@gmail.com', 'F', '999 Ashwood Ave', 'Counselling', 7.1),
(22, 'Lyle Bowers', '211422104022', '2002-12-12', '895-9516', 'interdum.nunc@gmail.com', 'M', '1212 Redwood Rd', 'Counselling', 6.8),
(23, 'Madeson Stokes', '211422104023', '2004-11-15', '194-8263', 'enim.etiam@outlook.com', 'F', '1313 Birchhill Ln', 'Management', 8.6),
(24, 'Marshall Whitley', '211422104024', '2005-01-03', '162-3221', 'dui.cras.pellentesque@gmail.com', 'M', '1414 Pinewood St', 'Management', 7.6),
(25, 'Octavius Lindsey', '211422104025', '2004-06-07', '648-3102', 'ac.feugiat@gmail.com', 'M', '1515 Cedarhill Ave', 'Counselling', 7.4),
(26, 'Oleg Mclean', '211422104026', '2003-03-22', '135-1543', 'egestas@outlook.com', 'M', '1616 Oakhill Rd', 'Management', 7.5),
(27, 'Orla Fields', '211422104027', '2004-12-14', '187-7392', 'ligula@yahoo.com', 'F', '1717 Maplehill Ln', 'Management', 7.3),
(28, 'Perry Durham', '211422104028', '2003-06-23', '873-0885', 'scelerisque@gmail.com', 'M', '1818 Willowhill St', 'Counselling', 8),
(29, 'Priscilla Harmon', '211422104029', '2003-07-06', '645-2753', 'semper.pretium@outlook.com', 'F', '1919 Chestnuthill Ave', 'Management', 7.9),
(30, 'Rachel George', '211422104030', '2004-01-22', '423-5764', 'sem.consequat@gmail.com', 'F', '2020 Walnutridge Rd', 'Counselling', 7.5),
(31, 'Rahim Alston', '211422104031', '2003-12-27', '828-1374', 'at.libero.morbi@gmail.com', 'M', '2121 Pinegrove Ln', 'Management', 7.5),
(32, 'Roanna Pollard', '211422104032', '2004-11-17', '575-8186', 'nam@gmail.com', 'F', '2222 Cedarlane St', 'Counselling', 8.2),
(33, 'Sade Gonzalez', '211422104033', '2003-02-23', '229-2688', 'est.vitae@gmail.com', 'F', '2323 Elmridge Ave', 'Counselling', 8.4),
(34, 'Serina Mayer', '211422104034', '2003-08-07', '180-6586', 'nascetur@gmail.com', 'F', '2424 Birchtree Rd', 'Counselling', 7.1),
(35, 'Xandra Mccullough', '211422104035', '2004-02-27', '847-4414', 'nonummy.ut@yahoo.com', 'F', '2525 Oakridge Ln', 'Management', 7.2),
(36, 'Yeo Melton', '211422104036', '2003-03-10', '386-5212', 'diam.eu@gmail.com', 'M', '2626 Mapleleaf St', 'Counselling', 7.6),
(37, 'Yoko Hinton', '211422104037', '2004-01-21', '815-4559', 'dui@yahoo.com', 'M', '2727 Willowridge Ave', 'Management', 7.8),
(38, 'Yvonne Rowland', '211422104038', '2003-12-31', '786-3549', 'mauris.erat@gmail.com', 'F', '2828 Sprucetree Rd', 'Management', 8.3),
(39, 'Zane Chaney', '211422104039', '2004-10-12', '323-1146', 'tempus.lorem@gmail.com', 'M', '2929 Ashlane Ln', 'Counselling', 7.3),
(40, 'Zoe Harrell', '211422104040', '2004-11-24', '437-4206', 'enim.mauris@gmail.com', 'F', '3030 Redwoodridge St', 'Management', 8.1);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `ID` int(10) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Register_no` varchar(20) NOT NULL,
  `Dept` varchar(5) NOT NULL,
  `Batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`ID`, `Name`, `Register_no`, `Dept`, `Batch`) VALUES
(1, 'Alan Bates', '211422104001', 'CSE', 2021),
(2, 'Alec Simmons', '211422104002', 'CSE', 2021),
(3, 'Brandon Chapman', '211422104003', 'CSE', 2021),
(4, 'Brock Talley', '211422104004', 'CSE', 2021),
(5, 'Brock Warren', '211422104005', 'CSE', 2021),
(6, 'Burke Humphrey', '211422104006', 'CSE', 2021),
(7, 'Chava Holloway', '211422104007', 'CSE', 2021),
(8, 'Damon Fernandez', '211422104008', 'CSE', 2021),
(9, 'Daria Christian', '211422104009', 'CSE', 2021),
(10, 'Donovan Norris', '211422104010', 'CSE', 2021),
(11, 'Guinevere Mack', '211422104011', 'IT', 2021),
(12, 'Hakeem Talley', '211422104012', 'IT', 2021),
(13, 'Idola Fischer', '211422104013', 'IT', 2021),
(14, 'Idona Mercado', '211422104014', 'IT', 2021),
(15, 'India Acevedo', '211422104015', 'IT', 2021),
(16, 'Jana Talley', '211422104016', 'IT', 2021),
(17, 'Jaquelyn Richmond', '211422104017', 'IT', 2021),
(18, 'Jayme Mcleod', '211422104018', 'IT', 2021),
(19, 'Kaitlin Shepherd', '211422104019', 'IT', 2021),
(20, 'Kirk Fischer', '211422104020', 'IT', 2021),
(21, 'Kitra Horne', '211422104021', 'CSE', 2022),
(22, 'Lyle Bowers', '211422104022', 'CSE', 2022),
(23, 'Madeson Stokes', '211422104023', 'CSE', 2022),
(24, 'Marshall Whitley', '211422104024', 'CSE', 2022),
(25, 'Octavius Lindsey', '211422104025', 'CSE', 2022),
(26, 'Oleg Mclean', '211422104026', 'CSE', 2022),
(27, 'Orla Fields', '211422104027', 'CSE', 2022),
(28, 'Perry Durham', '211422104028', 'CSE', 2022),
(29, 'Priscilla Harmon', '211422104029', 'CSE', 2022),
(30, 'Rachel George', '211422104030', 'CSE', 2022),
(31, 'Rahim Alston', '211422104031', 'IT', 2022),
(32, 'Roanna Pollard', '211422104032', 'IT', 2022),
(33, 'Sade Gonzalez', '211422104033', 'IT', 2022),
(34, 'Serina Mayer', '211422104034', 'IT', 2022),
(35, 'Xandra Mccullough', '211422104035', 'IT', 2022),
(36, 'Yeo Melton', '211422104036', 'IT', 2022),
(37, 'Yoko Hinton', '211422104037', 'IT', 2022),
(38, 'Yvonne Rowland', '211422104038', 'IT', 2022),
(39, 'Zane Chaney', '211422104039', 'IT', 2022),
(40, 'Zoe Harrell', '211422104040', 'IT', 2022);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stpersonal_info`
--
ALTER TABLE `stpersonal_info`
  ADD PRIMARY KEY (`Register_no`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`Register_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
