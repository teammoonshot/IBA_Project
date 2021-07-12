CREATE TABLE `map` (
  `Node1` int NOT NULL,
  `Node2` int NOT NULL,
  `Edge` double DEFAULT NULL,
  PRIMARY KEY (`Node1`,`Node2`)
);