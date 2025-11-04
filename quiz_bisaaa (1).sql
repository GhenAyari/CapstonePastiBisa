-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 04, 2025 at 01:45 PM
-- Server version: 8.4.3
-- PHP Version: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quiz_bisaaa`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id_feedback` int NOT NULL,
  `message` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id_feedback`, `message`) VALUES
(1, '[From: gurukeren → To: siswakeren (bagas) @2025-11-02 21:46:54]\r\nmantap kamu bagas'),
(2, 'T:1|S:1|B64:a2VyZW4gYmFuZ2V0IGthbXUgYmFnYXM='),
(3, 'T:1|S:1|B64:a2VyZW4ga2FtdSBiYWdhcyBkYXBhdCAxMDA='),
(4, 'T:13|S:2|B64:a2FtdSBoZWJhdA=='),
(5, 'T:17|S:1|B64:a2FtdSBoZWJhdCBiYWdhcw=='),
(6, 'T:16|S:1|B64:a2FtdSBwYXN0aSBiaXNhIGJhZ2Fz'),
(7, 'T:16|S:2|B64:SGFsbG8gQnJvdG8=');

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `question_id` int NOT NULL,
  `teacher_id` int NOT NULL,
  `quiz_title` varchar(100) NOT NULL,
  `description` text,
  `question_text` text,
  `soal` text NOT NULL,
  `correct_answer` char(1) NOT NULL,
  `question_number` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`question_id`, `teacher_id`, `quiz_title`, `description`, `question_text`, `soal`, `correct_answer`, `question_number`) VALUES
(16, 2, 'fikih', NULL, NULL, 'rukun islam ada :  A. 1, B. 3, C. 4, D. 5', 'D', 1),
(17, 1, 'PU', NULL, NULL, '1. Ibukota negara Indonesia adalah... a) Bali b) Bandung c) Jakarta d) Surabaya', 'C', 1),
(18, 1, 'PU', NULL, NULL, '2. Hewan nasional Australia adalah... a) Koala b) Kanguru c) Emu d) Platipus', 'B', 2),
(19, 1, 'PU', NULL, NULL, '3. Planet terbesar di tata surya kita adalah... a) Mars b) Bumi c) Jupiter d) Venus', 'C', 3),
(23, 10, 'Penalaran', NULL, NULL, 'Kaltim adalah: A) Kalimantan Timur, B) Kalimantan Selatan C) Kalimantan Barat D) Kalimantan Tengah', 'A', 1),
(24, 10, 'Penalaran', NULL, NULL, 'Ada berapa presiden di Indonesia A) 5 B) 6 C) 7 D) 8', 'D', 2),
(25, 1, 'Soal Umum', NULL, NULL, '1. Siapakah Penemu bola lampu A) Thomas Alva Edison B) Satria C) Dono D) Rusdi', 'A', 1),
(26, 1, 'Soal Umum', NULL, NULL, '2. Hemat pangkal: A) Kaya, B) Miskin C) Pas Pasan D) Sederhana', 'A', 2),
(27, 1, 'Soal Umum', NULL, NULL, '3.  Adik sakit. Adik harus berobat ke A) Dokter, B) Apotek C) Salon D) Dukun', 'A', 3),
(28, 1, 'Soal Umum', NULL, NULL, '4. Ibu berbelanja ke A) Sekolah B) Pasar C) Kebun D) Sawah', 'B', 4),
(29, 1, 'Soal Umum', NULL, NULL, '5. Harimau termasuk hewan: A) Bertelur B) Membelah diri C) Mamalia D) Reptil', 'C', 5),
(30, 13, 'Soal Dasar', NULL, NULL, '1. Siapakah Penemu bola lampu A) Thomas Alva Edison B) Satria C) Bowo D) Rahmad', 'A', 1),
(31, 13, 'Soal Dasar', NULL, NULL, '2. Hemat pangkal: A) Kaya, B) Miskin C) Pas Pasan D) Sederhana', 'A', 2),
(32, 13, 'Soal Dasar', NULL, NULL, '3.  Adik sakit. Adik harus berobat ke A) Dokter, B) Apotek C) Salon D) Dukun', 'A', 3),
(33, 17, 'Testing', NULL, NULL, '1. Siapakah Penemu bola lampu A) Thomas Alva Edison B) Satria C) Bowo D) Rusdi', 'A', 1),
(44, 16, 'Pengetahuan Umum', NULL, NULL, '1. Ibu berbelanja ke A) Sekolah B) Pasar C) Kebun D) Rumah', 'B', 1),
(45, 16, 'Pengetahuan Umum', NULL, NULL, '2. Harimau termasuk hewan: A) Bertelur B) Membelah diri C) Mamalia D) Reptil', 'C', 2),
(46, 17, 'Ayooo', NULL, NULL, 'Unmul adalah A) Universitas Mulawarman B) Universitas Muhammadiyah C) Universitas Pelita D) Tidak benar semua', 'A', 1),
(47, 17, 'Ayooo', NULL, NULL, '1. Siapakah Penemu bola lampu A) Thomas Alva Edison B) Satria C) Bowo D) Rusdi', 'A', 2),
(48, 17, 'Ayooo', NULL, NULL, '4. Ibu berbelanja ke A) Sekolah B) Pasar C) Kebun D) Sawah', 'B', 3);

-- --------------------------------------------------------

--
-- Table structure for table `rapor`
--

CREATE TABLE `rapor` (
  `id_rapor` int NOT NULL,
  `student_id` int NOT NULL,
  `quiz_id` int NOT NULL,
  `is_incorrect` int NOT NULL DEFAULT '0',
  `is_correct` int NOT NULL DEFAULT '0',
  `total_scores` int NOT NULL DEFAULT '0',
  `attempt_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rapor`
--

INSERT INTO `rapor` (`id_rapor`, `student_id`, `quiz_id`, `is_incorrect`, `is_correct`, `total_scores`, `attempt_date`) VALUES
(3, 1, 17, 1, 2, 2, '2025-11-02 10:48:21'),
(4, 1, 17, 1, 2, 67, '2025-11-02 11:19:33'),
(7, 2, 17, 1, 2, 67, '2025-11-02 15:17:28'),
(10, 1, 30, 0, 3, 100, '2025-11-03 06:39:11'),
(11, 2, 30, 2, 1, 33, '2025-11-03 06:40:51'),
(12, 2, 33, 0, 1, 100, '2025-11-03 10:18:18'),
(13, 2, 25, 3, 2, 40, '2025-11-03 10:20:08'),
(14, 1, 25, 5, 0, 0, '2025-11-03 10:21:00'),
(17, 1, 44, 0, 2, 100, '2025-11-04 07:10:58'),
(18, 2, 44, 1, 1, 50, '2025-11-04 07:15:43'),
(19, 1, 25, 2, 3, 60, '2025-11-04 12:14:39'),
(20, 2, 46, 0, 3, 100, '2025-11-04 12:17:25');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `users_id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_verified` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`users_id`, `username`, `name`, `password`, `is_verified`) VALUES
(1, 'siswakeren', 'bagas', '123', 1),
(2, 'siswamantap', 'broto', '123', 1),
(3, 'siswabagus', 'alden', '123', 1),
(4, 'siswahebat', 'putra', '123', 1);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `users_id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_verified` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`users_id`, `username`, `name`, `password`, `is_verified`) VALUES
(1, 'gurukeren', 'prakasa', '123', 1),
(2, 'gurumtk', 'satria', '123', 1),
(10, 'guruips', 'galpati', '123', 1),
(12, 'gurubio', 'Darel', '123', 1),
(13, 'guruipa', 'Hendra', '123', 1),
(16, 'gurugeo', 'pras', '123', 1),
(17, 'guruindo', 'ramadhan', '123', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id_feedback`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`question_id`),
  ADD KEY `fk_quiz_teacher` (`teacher_id`);

--
-- Indexes for table `rapor`
--
ALTER TABLE `rapor`
  ADD PRIMARY KEY (`id_rapor`),
  ADD KEY `fk_rapor_student` (`student_id`),
  ADD KEY `fk_rapor_quiz` (`quiz_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`users_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`users_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id_feedback` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `question_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `rapor`
--
ALTER TABLE `rapor`
  MODIFY `id_rapor` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `users_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `users_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `fk_quiz_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`users_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rapor`
--
ALTER TABLE `rapor`
  ADD CONSTRAINT `fk_rapor_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`users_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
