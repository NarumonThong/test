-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2017 at 12:38 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cm_workshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(5) NOT NULL,
  `id_product` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `detail` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `price` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `img_product` varchar(500) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `id_product`, `name`, `detail`, `price`, `img_product`) VALUES
(1, 'cm-001', 'TW64 TW64S Bluetooth fitband sport Fitness Activity Tracker Smart Band Wristband Pulsera Inteligente Smart Bracelet Not Fitbit', 'TW64 Bluetooth 4.0 Fitness Activity Tracker Smart Band Wristband Pulsera Inteligente Smart Bracelet Not Fitbit Flex Fit Bit ios', '12,000', 'https://www.dhresource.com/260x260s/f2-albu-g5-M01-BC-58-rBVaI1jeC7KAZzrFAAHqRpWaa4Q372.jpg/tw64s-tw64-fitbit-flex-smartband-charge-hr.jpg'),
(2, 'cm-002', 'Top Quality Cooling Fan Charging Station For Play Station Playstation 4 Pro Console', 'for play station 4 pro console Game accessories for sony ps4 pro console vertical stand dock For playstation 4 pro game console', '25,000', 'https://images-na.ssl-images-amazon.com/images/I/61jhO0owsUL._SL1500_.jpg'),
(3, 'cm-003', '2017 High Quality sport backpack bag/outdoor bag/sport bag waterproof', '2017 new design fashion pattern girls backpack school bag packing details Inner Poly Bag+carton', '18,000', 'https://s-media-cache-ak0.pinimg.com/originals/d5/a4/98/d5a498f315f0e2dff26d6954139a04cc.jpg'),
(4, 'cm-004', 'headphone factory wireless headphone new stereo Fashion wireless earphone', 'retail package', '22,000', 'https://sc02.alicdn.com/kf/HTB1AukGc3vD8KJjy0Flq6ygBFXan/headphone-factory-wireless-headphone-new-stereo-Fashion.jpg_220x220.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
