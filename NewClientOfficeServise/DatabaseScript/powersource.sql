-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2022 at 10:26 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `powersource`
--

CREATE TABLE `powersource` (
  `ID` int(11) NOT NULL,
  `Name` varchar(300) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `Province` varchar(300) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `PowerGenerated` int(4) NOT NULL,
  `Maintenance_Day` varchar(20) NOT NULL,
  `Head_Engineer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `powersource`
--

INSERT INTO `powersource` (`ID`, `Name`, `Address`, `Province`, `Type`, `PowerGenerated`, `Maintenance_Day`, `Head_Engineer`) VALUES
(18, 'Randenigala', 'Kandy Road, Badulla', 'Uva', 'Renewable', 450, '25-MAY-2022', 8),
(19, 'Victoria ', 'Kandy Road, Theldeniya', 'Central', 'Renewable', 400, '04-MAY-2022', 11),
(20, 'Lakvijaya', 'Puttalam Road,Norocholai', 'North Western', 'Non-Renewable', 600, '02-MAY-2022', 6),
(21, 'Kothmale', 'Nuwaraeliya Road,Kothamale', 'Central', 'Renewable', 450, '29-APRIL-2022', 8),
(22, 'Kelanitissa', 'Colombo Road,Kelaniya', 'Western', 'Non-Renewable', 550, '03-MAY-2022', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `powersource`
--
ALTER TABLE `powersource`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `powersource`
--
ALTER TABLE `powersource`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
