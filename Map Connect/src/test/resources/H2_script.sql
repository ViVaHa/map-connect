CREATE TABLE `contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `contact` VALUES (1,'acco','abc@def.gh','Raheja','1234567890');
INSERT INTO `contact` VALUES (2,'sony','xyz@abc.de','Cessna','3214560987');