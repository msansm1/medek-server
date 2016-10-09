DELETE FROM `FRIEND` WHERE 1;
DELETE FROM `MOVIESUBTITLE` WHERE 1;
DELETE FROM `MOVIELANG` WHERE 1;
DELETE FROM `USERALBUM` WHERE 1;
DELETE FROM `USERBOOK` WHERE 1;
DELETE FROM `USERMOVIE` WHERE 1;
DELETE FROM `USERTV` WHERE 1;
DELETE FROM `ALBUMARTIST` WHERE 1;
DELETE FROM `TRACKARTIST` WHERE 1;
DELETE FROM `BOOKARTIST` WHERE 1;
DELETE FROM `MOVIEARTIST` WHERE 1;
DELETE FROM `USER` WHERE 1;
DELETE FROM `TRACK` WHERE 1;
DELETE FROM `ALBUM` WHERE 1;
DELETE FROM `BOOK` WHERE 1;
DELETE FROM `MOVIE` WHERE 1;
DELETE FROM `TVSHOW` WHERE 1;
DELETE FROM `ARTIST` WHERE 1;
DELETE FROM `ARTISTTYPE` WHERE 1;
DELETE FROM `LANG` WHERE 1;
DELETE FROM `STORYGENRE` WHERE 1;
DELETE FROM `SUPPORT` WHERE 1;
DELETE FROM `EDITOR` WHERE 1;
DELETE FROM `COLLECTION` WHERE 1;
DELETE FROM `BOOKTYPE` WHERE 1;
DELETE FROM `GENRE` WHERE 1;
DELETE FROM `CONFIGURATION` WHERE 1;
DELETE FROM `DATABASE` WHERE 1;

ALTER TABLE `ARTIST` AUTO_INCREMENT = 1;
ALTER TABLE `TVSHOW` AUTO_INCREMENT = 1;
ALTER TABLE `USER` AUTO_INCREMENT = 1;

INSERT INTO `DATABASE` (`ID`, `VERSION`) VALUES (NULL, '0.8');

INSERT INTO `CONFIGURATION` (`PARAMETER`, `VALUE`) VALUES ('filesystem', '/opt/medek/fs/');

INSERT INTO `USER` (`ID`, `LOGIN`, `PASSWORD`, `EMAIL`, `TOKEN`, `MOBILE_TOKEN`) VALUES (1, 'msansm1', '7161759d184494851cc10d02a8037f2f9e98953329e3e30976e8a44be4c80cc9', 'msansm1@test.bzh', '0277d058-f407-47d0-9e1c-797376e7da1a', null), (2, 'testarq', '6fbd2e3d96a64f4e28faba23a7860234ba842d84', 'test@arq.bzh', '0277d058-f407-47d0-9e1c-797376e7da1a34', null);

INSERT INTO `STORYGENRE` (`ID`, `NAME`) VALUES (1, 'Aventure'), (2, 'Action');

INSERT INTO `SUPPORT` (`ID`, `NAME`) VALUES (1, 'DVD'), (2, 'Blu-ray'), (3, 'CD'), (4, 'Dématérialisé');

INSERT INTO `LANG` (`ID`, `NAME`) VALUES (1, 'Français'), (2, 'Anglais'), (3, 'Allemand');

INSERT INTO `EDITOR` (`ID`, `NAME`) VALUES (1, 'Dupuis'), (2, 'Livre de Poche'), (3, 'Dargaud');

INSERT INTO `COLLECTION` (`ID`, `NAME`) VALUES (1, 'Yoko Tsuno'), (2, 'Le Disque Monde');

INSERT INTO `BOOKTYPE` (`ID`, `NAME`) VALUES (1, 'Roman'), (2, 'B.D.');

INSERT INTO `GENRE` (`ID`, `NAME`) VALUES (1, 'Metal'), (2, 'Rock'), (3, 'Pop');

INSERT INTO `ARTISTTYPE` (`ID`, `NAME`) VALUES (1, 'Groupe de musique'), (2, 'Auteur'), (3, 'Producteur'), (4, 'Réalisateur'), (5, 'Scénariste');

INSERT INTO `ARTIST` (`ID`, `NAME`, `FIRSTNAME`, `TYPE`, `NATIONALITY`, `PICTURE`, `BIOLINK`) VALUES (1, 'Metallica', NULL, '1', 'Americain', NULL, NULL), (2, 'Tolkien', 'JRR', '2', 'Anglais', NULL, NULL), (3, 'Miyasaki', 'Hayao', '3', 'Japonais', NULL, NULL), (4, 'Matmatah', NULL, '1', 'Breton', NULL, NULL), (5, 'Nirvana', NULL, '1', 'Americain', NULL, NULL), (6, 'Rowling', 'JK', '2', 'Anglaise', NULL, NULL), (7, 'Weber', 'Franck', '2', 'Français', NULL, NULL);

INSERT INTO `ALBUM` (`ID`, `TITLE`, `COVER`, `RELEASEDATE`, `GENRE`, `NBTRACKS`, `SUPPORT`) VALUES (1, 'And justice for all', NULL, '1984-12-01 00:00:00', 1, 8, 3), (2, 'La ouache', NULL, '1999-12-01 00:00:00', 2, 11, 3), (3, 'Master of puppets', NULL, '1986-12-01 00:00:00', 1, 9, 3), (4, 'In utero', NULL, '1990-12-01 00:00:00', 2, 13, 3), (5, 'Nevermind', NULL, '1992-12-01 00:00:00', 2, 12, 3), (6, 'Best of', NULL, '2005-12-01 00:00:00', 2, 15, 3), (7, 'Kill them all', NULL, '1981-12-01 00:00:00', 1, 8, 3);

INSERT INTO `TRACK` (`ID`, `ALBUM`, `TITLE`, `NUMBER`, `LENGTH`) VALUES (1, '1', 'Master of Puppets', '3', '7:50'), (2, '1', 'Orion', '8', '9:57');

INSERT INTO `ALBUMARTIST` (`ALBUM`, `ARTIST`, `TYPE`) VALUES ('1', '1', NULL), ('2', '4', NULL), ('3', '1', NULL), ('4', '5', NULL), ('5', '5', NULL), ('6', '5', NULL), ('7', '1', NULL);

INSERT INTO `TRACKARTIST` (`TRACK`, `ARTIST`, `TYPE`) VALUES ('1', '1', NULL), ('2', '1', NULL);

INSERT INTO `BOOK` (`ID`, `TITLE`, `EDITOR`, `COLLECTION`, `COVER`, `DESCRIPTION`, `PUBLICATIONDATE`, `GENRE`, `TYPE`, `LANG`, `SERIES`, `BOOKNB`, `ISSERIEDONE`) VALUES (1, 'The Hobbit', 2, NULL, NULL, 'In a hole there was a Hobbit', '1937-05-01 00:00:00', 1, 1, 2, NULL, 1, 1), (2, 'The Lord of the Rings', 2, NULL, NULL, 'The herbs of hobbits', '1939-05-01 00:00:00', 1, 1, 2, NULL, 1, 1), (3, 'Harry Potter', 2, NULL, NULL, 'Youp', '1984-05-01 00:00:00', 1, 1, 2, NULL, 1, 1), (4, 'The Cup of Fire', 2, NULL, NULL, 'Fwouh', '1990-05-01 00:00:00', 1, 1, 2, NULL, 1, 1), (5, 'Vacancies', 2, NULL, NULL, 'Youhou', '2013-05-01 00:00:00', 1, 1, 2, NULL, 1, 1), (6, 'Les Fourmis', 2, NULL, NULL, 'Tchtch', '1998-05-01 00:00:00', 1, 1, 2, NULL, 1, 1), (7, 'Les papillons', 2, NULL, NULL, 'Fwfwfw', '2007-05-01 00:00:00', 1, 1, 2, NULL, 1, 1);

INSERT INTO `BOOKARTIST` (`BOOK`, `ARTIST`, `TYPE`) VALUES ('1', '2', NULL), ('2', '2', NULL), ('3', '6', NULL), ('4', '6', NULL), ('5', '6', NULL), ('6', '7', NULL), ('7', '7', NULL);

INSERT INTO `MOVIE` (`ID`, `TITLE`, `DESCRIPTION`, `RELEASEDATE`, `COVER`, `SUPPORT`, `GENRE`, `LENGTH`, `ISCOLLECTOR`) VALUES (1, 'Princesse Mononoké', NULL, '1999-12-02 00:00:00', NULL, 1, 1, NULL, 0), (2, 'Le chateau dans le ciel', NULL, '1988-12-02 00:00:00', NULL, 1, 1, NULL, 0), (3, 'Mémoires de nos pères', NULL, '1999-12-02 00:00:00', NULL, 1, 1, NULL, 0), (4, 'Gran Torino', NULL, '2010-12-02 00:00:00', NULL, 1, 1, NULL, 0), (5, 'Invictus', NULL, '2012-12-02 00:00:00', NULL, 1, 1, NULL, 0), (6, 'Inception', NULL, '2013-12-02 00:00:00', NULL, 1, 1, NULL, 0), (7, 'The Dark Knight', NULL, '2012-12-02 00:00:00', NULL, 1, 1, NULL, 0);

INSERT INTO `MOVIEARTIST` (`MOVIE`, `ARTIST`, `TYPE`) VALUES ('1', '3', NULL);

INSERT INTO `MOVIELANG` (`MOVIE`, `LANG`) VALUES ('1', '1'), ('1', '2');

INSERT INTO `MOVIESUBTITLE` (`MOVIE`, `SUBTITLE`) VALUES ('1', '1'), ('1', '2');

INSERT INTO `TVSHOW` (`ID`, `TITLE`, `DESCRIPTION`, `RELEASEDATE`, `COVER`, `SUPPORT`, `GENRE`, `LENGTH`, `season`, `SERIES`, `ISSERIEDONE`) VALUES (1, 'Justified s01', NULL, '2010-12-02 00:00:00', NULL, 1, 1, NULL, 1, 'Justified', 0), (2, 'Justified s02', NULL, '2011-12-02 00:00:00', NULL, NULL, NULL, NULL, 2, 'Justified', 0), (3, 'Once upon a time s01', NULL, '2010-12-02 00:00:00', NULL, NULL, NULL, NULL, 1, 'Once upon a time', 0), (4, 'Once upon a time s02', NULL, '2011-12-02 00:00:00', NULL, NULL, NULL, NULL, 2, 'Once upon a time', 0), (5, 'Hell on Wheels s01', NULL, '2010-12-02 00:00:00', NULL, NULL, NULL, NULL, 1, 'Hell on Wheels', 0), (6, 'Hell on Wheels s02', NULL, '2011-12-02 00:00:00', NULL, NULL, NULL, NULL, 2, 'Hell on Wheels', 0), (7, 'NCIS s09', NULL, '2010-12-02 00:00:00', NULL, NULL, NULL, NULL, 9, 'NCIS', 0);

INSERT INTO `USERALBUM` (`USER`, `ALBUM`, `ISSIGNED`, `RATING`, `COMMENT`) VALUES ('1', '1', 0, 5, NULL);

INSERT INTO `USERBOOK` (`USER`, `BOOK`, `ISSIGNED`, `RATING`, `COMMENT`) VALUES (1, 1, 0, 4, NULL), (1, 2, 0, 4, NULL);

INSERT INTO `USERMOVIE` (`USER`, `MOVIE`, `RATING`, `COMMENT`) VALUES ('1', '1', 5, NULL);

INSERT INTO `USERTV` (`USER`, `TVSHOW`, `RATING`, `COMMENT`) VALUES ('1', '1', 4, NULL);

INSERT INTO `FRIEND` (`USER`, `FRIEND`, `ISACCEPTED`, `ISSHAREDCOLLECTION`) VALUES ('1', '2', 1, 0);
