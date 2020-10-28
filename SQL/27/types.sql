-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 28, 2020 at 04:47 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `product_history`
--

-- --------------------------------------------------------

--
-- Table structure for table `types`
--

CREATE TABLE `types` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `types`
--

INSERT INTO `types` (`id`, `name`, `image`) VALUES
('11312', 'Voucher - Dịch Vụ - Thẻ Cào ', ''),
('1520', 'Làm Đẹp - Sức Khỏe', ''),
('17166', 'Hàng quốc tế ', ''),
('1789', 'Điện Thoại - Máy Tính Bảng', ''),
('1801', 'Máy Ảnh - Quay Phim', ''),
('1815', 'Phụ Kiện - Thiết Bị Số', ''),
('1846', 'Laptop - Thiết bị IT', ''),
('1882', 'Điện Gia Dụng', ''),
('1883', 'Nhà Cửa Đời Sống', ''),
('1975', 'Thể Thao - Dã Ngoại', ''),
('2549', 'Đồ chơi, Mẹ & Bé', ''),
('4221', 'Điện Tử - Điện Lạnh', ''),
('4384', 'Hàng Tiêu Dùng - Thực Phẩm', ''),
('8322', 'Sách, VPP & Quà Tặng', ''),
('8594', 'Xe Máy, Ô tô, Xe Đạp', ''),
('914', 'Thời trang - Phụ kiện', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `types`
--
ALTER TABLE `types`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
