CREATE TABLE `book` (
  `Book_ID` int NOT NULL,
  `Title` char(20) DEFAULT NULL,
  `Author` char(20) DEFAULT NULL,
  `Publisher` char(20) DEFAULT NULL,
  `Publication_Year` int DEFAULT NULL,
  `Thickness` double DEFAULT NULL,
  `Location` char(20) DEFAULT NULL,
  PRIMARY KEY (`Book_ID`)
);