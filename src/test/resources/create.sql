CREATE SCHEMA IF NOT EXISTS `meddb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `meddb` ;

-- -----------------------------------------------------
-- Table `meddb`.`EDITOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`EDITOR` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`COLLECTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`COLLECTION` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`BOOKTYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`BOOKTYPE` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`LANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`LANG` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`STORYGENRE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`STORYGENRE` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`BOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`BOOK` (  `ID` INT NOT NULL AUTO_INCREMENT,  `TITLE` VARCHAR(45) NOT NULL,  `EDITOR` INT NULL,  `COLLECTION` INT NULL,  `COVER` VARCHAR(45) NULL,  `DESCRIPTION` VARCHAR(150) NULL,  `PUBLICATIONDATE` DATETIME NULL,  `GENRE` INT NULL,  `TYPE` INT NULL,  `LANG` INT NULL,  `SERIES` VARCHAR(45) NULL,  `BOOKNB` INT(11) NULL,  `ISSERIEDONE` TINYINT(1) NULL,  PRIMARY KEY (`ID`),  CONSTRAINT `FK_BK_EDITOR_EDITOR`    FOREIGN KEY (`EDITOR`)    REFERENCES `meddb`.`EDITOR` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_BK_COLLEC_COLLEC`    FOREIGN KEY (`COLLECTION`)    REFERENCES `meddb`.`COLLECTION` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_BK_TYPE_BKTYPE`    FOREIGN KEY (`TYPE`)    REFERENCES `meddb`.`BOOKTYPE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_BKLG_LANG`    FOREIGN KEY (`LANG`)    REFERENCES `meddb`.`LANG` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_BK_GR_GENRE`    FOREIGN KEY (`GENRE`)    REFERENCES `meddb`.`STORYGENRE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_EDITOR_EDITOR_idx` ON `meddb`.`BOOK` (`EDITOR` ASC);

CREATE INDEX `FK_COLLEC_COLLEC_idx` ON `meddb`.`BOOK` (`COLLECTION` ASC);

CREATE INDEX `FK_BK_TYPE_TYPE_idx` ON `meddb`.`BOOK` (`TYPE` ASC);

CREATE INDEX `FK_BKLG_LANG_idx` ON `meddb`.`BOOK` (`LANG` ASC);

CREATE INDEX `FK_BK_GR_GENRE_idx` ON `meddb`.`BOOK` (`GENRE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`ARTIST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`ARTIST` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  `FIRSTNAME` VARCHAR(45) NULL,  `TYPE` INT NOT NULL,  `NATIONALITY` VARCHAR(45) NULL,  `PICTURE` VARCHAR(45) NULL,  `BIOLINK` VARCHAR(45) NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`SUPPORT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`SUPPORT` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`MOVIE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`MOVIE` (  `ID` INT NOT NULL AUTO_INCREMENT,  `TITLE` VARCHAR(45) NOT NULL,  `DESCRIPTION` VARCHAR(150) NULL,  `RELEASEDATE` DATETIME NULL,  `COVER` VARCHAR(45) NULL,  `SUPPORT` INT NULL,  `GENRE` INT NULL,  `LENGTH` VARCHAR(5) NULL,  `ISCOLLECTOR` TINYINT(1) NOT NULL DEFAULT 0,  PRIMARY KEY (`ID`),  CONSTRAINT `FK_MV_SUP_SUPPORT`    FOREIGN KEY (`SUPPORT`)    REFERENCES `meddb`.`SUPPORT` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_MV_TYPE_MVTYPE`    FOREIGN KEY (`GENRE`)    REFERENCES `meddb`.`STORYGENRE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_MV_REAL_ARTIST_idx` ON `meddb`.`MOVIE` (`SUPPORT` ASC);

CREATE INDEX `FK_MV_TYPE_MVTYPE_idx` ON `meddb`.`MOVIE` (`GENRE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`GENRE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`GENRE` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`ALBUM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`ALBUM` (  `ID` INT NOT NULL AUTO_INCREMENT,  `TITLE` VARCHAR(45) NOT NULL,  `COVER` VARCHAR(45) NULL,  `RELEASEDATE` DATETIME NULL,  `GENRE` INT NULL,  `NBTRACKS` INT NULL,  `SUPPORT` INT NULL,  PRIMARY KEY (`ID`),  CONSTRAINT `FK_AL_SUP_SUPPORT`    FOREIGN KEY (`SUPPORT`)    REFERENCES `meddb`.`SUPPORT` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_AL_GENRE_GENRE`    FOREIGN KEY (`GENRE`)    REFERENCES `meddb`.`GENRE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_AL_ARTIST_ARTIST_idx` ON `meddb`.`ALBUM` (`SUPPORT` ASC);

CREATE INDEX `FK_AL_GENRE_GENRE_idx` ON `meddb`.`ALBUM` (`GENRE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`TRACK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`TRACK` (  `ID` INT NOT NULL AUTO_INCREMENT,  `ALBUM` INT NOT NULL,  `TITLE` VARCHAR(45) NOT NULL,  `NUMBER` INT NULL,  `LENGTH` VARCHAR(45) NULL,  PRIMARY KEY (`ID`),  CONSTRAINT `FK_TR_ALBUM_ALBUM`    FOREIGN KEY (`ALBUM`)    REFERENCES `meddb`.`ALBUM` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_TR_ALBUM_ALBUM_idx` ON `meddb`.`TRACK` (`ALBUM` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`TVSHOW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`TVSHOW` (  `ID` INT NOT NULL AUTO_INCREMENT,  `TITLE` VARCHAR(45) NOT NULL,  `DESCRIPTION` VARCHAR(150) NULL,  `RELEASEDATE` DATETIME NULL,  `COVER` VARCHAR(45) NULL,  `SUPPORT` INT NULL,  `GENRE` INT NULL,  `LENGTH` VARCHAR(5) NULL,  `SEASON` INT NULL,  `SERIES` VARCHAR(45) NULL,  `ISSERIEDONE` TINYINT(1) NULL,  PRIMARY KEY (`ID`),  CONSTRAINT `FK_TV_SUP_SUPPORT`    FOREIGN KEY (`SUPPORT`)    REFERENCES `meddb`.`SUPPORT` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_TV_TYPE_MVTYPE`    FOREIGN KEY (`GENRE`)    REFERENCES `meddb`.`STORYGENRE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_TV_REAL_ARTIST_idx` ON `meddb`.`TVSHOW` (`SUPPORT` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`DATABASE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`DATABASE` (  `ID` INT NOT NULL AUTO_INCREMENT,  `VERSION` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`MOVIELANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`MOVIELANG` (  `MOVIE` INT NOT NULL,  `LANG` INT NOT NULL,  PRIMARY KEY (`MOVIE`, `LANG`),  CONSTRAINT `FK_MVLG_MOVIE`    FOREIGN KEY (`MOVIE`)    REFERENCES `meddb`.`MOVIE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_MVLG_LANG`    FOREIGN KEY (`LANG`)    REFERENCES `meddb`.`LANG` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_MVLG_MOVIE_idx` ON `meddb`.`MOVIELANG` (`MOVIE` ASC);

CREATE INDEX `FK_MVLG_LANG_idx` ON `meddb`.`MOVIELANG` (`LANG` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`MOVIESUBTITLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`MOVIESUBTITLE` (  `MOVIE` INT NOT NULL,  `SUBTITLE` INT NOT NULL,  PRIMARY KEY (`MOVIE`, `SUBTITLE`),  CONSTRAINT `FK_MVSUB_MOVIE`    FOREIGN KEY (`MOVIE`)    REFERENCES `meddb`.`MOVIE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_MVSUB_SUBTITLE`    FOREIGN KEY (`SUBTITLE`)    REFERENCES `meddb`.`LANG` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_MVSUB_MOVIE_idx` ON `meddb`.`MOVIESUBTITLE` (`MOVIE` ASC);

CREATE INDEX `FK_MVSUB_SUBTITLE_idx` ON `meddb`.`MOVIESUBTITLE` (`SUBTITLE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`TVLANG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`TVLANG` (  `TVSHOW` INT NOT NULL,  `LANG` INT NOT NULL,  PRIMARY KEY (`TVSHOW`, `LANG`),  CONSTRAINT `FK_TVLG_TV`    FOREIGN KEY (`TVSHOW`)    REFERENCES `meddb`.`TVSHOW` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_TVLG_LANG`    FOREIGN KEY (`LANG`)    REFERENCES `meddb`.`LANG` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_TVLG_TV_idx` ON `meddb`.`TVLANG` (`TVSHOW` ASC);

CREATE INDEX `FK_TVLG_LANG_idx` ON `meddb`.`TVLANG` (`LANG` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`TVSUBTITLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`TVSUBTITLE` (  `TVSHOW` INT NOT NULL,  `SUBTITLE` INT NOT NULL,  PRIMARY KEY (`TVSHOW`, `SUBTITLE`),  CONSTRAINT `FK_TVSUB_TV`    FOREIGN KEY (`TVSHOW`)    REFERENCES `meddb`.`TVSHOW` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_TVSUB_SUBTITLE`    FOREIGN KEY (`SUBTITLE`)    REFERENCES `meddb`.`LANG` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_TVSUB_TV_idx` ON `meddb`.`TVSUBTITLE` (`TVSHOW` ASC);

CREATE INDEX `FK_TVSUB_SUBTITLE_idx` ON `meddb`.`TVSUBTITLE` (`SUBTITLE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`USER` (  `ID` INT NOT NULL AUTO_INCREMENT,  `LOGIN` VARCHAR(20) NOT NULL,  `PASSWORD` VARCHAR(60) NOT NULL,  `EMAIL` VARCHAR(75) NOT NULL,  `TOKEN` VARCHAR(60) NULL,  `MOBILE_TOKEN` VARCHAR(60) NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;

CREATE UNIQUE INDEX `login_UNIQUE` ON `meddb`.`USER` (`LOGIN` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`USERBOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`USERBOOK` (  `USER` INT NOT NULL,  `BOOK` INT NOT NULL,  `ISSIGNED` TINYINT(1) NOT NULL DEFAULT 0,  `RATING` INT NULL,  `COMMENT` VARCHAR(100) NULL,  PRIMARY KEY (`USER`, `BOOK`),  CONSTRAINT `FK_USBK_USER`    FOREIGN KEY (`USER`)    REFERENCES `meddb`.`USER` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_USBK_BOOK`    FOREIGN KEY (`BOOK`)    REFERENCES `meddb`.`BOOK` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_USBK_USER_idx` ON `meddb`.`USERBOOK` (`USER` ASC);

CREATE INDEX `FK_USBK_BOOK_idx` ON `meddb`.`USERBOOK` (`BOOK` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`USERMOVIE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`USERMOVIE` (  `USER` INT NOT NULL, `MOVIE` INT NOT NULL,  `RATING` INT NULL,  `COMMENT` VARCHAR(100) NULL,  PRIMARY KEY (`USER`, `MOVIE`),  CONSTRAINT `FK_USMV_USER`    FOREIGN KEY (`USER`)    REFERENCES `meddb`.`USER` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_USMV_MOVIE`    FOREIGN KEY (`MOVIE`)   REFERENCES `meddb`.`MOVIE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_USMV_USER_idx` ON `meddb`.`USERMOVIE` (`USER` ASC);

CREATE INDEX `FK_USMV_MOVIE_idx` ON `meddb`.`USERMOVIE` (`MOVIE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`USERTV`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`USERTV` (  `USER` INT NOT NULL, `TVSHOW` INT NOT NULL,  `RATING` INT NULL,  `COMMENT` VARCHAR(100) NULL,  PRIMARY KEY (`USER`, `TVSHOW`),  CONSTRAINT `FK_USTV_USER`    FOREIGN KEY (`USER`)    REFERENCES `meddb`.`USER` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_USTV_TVSHOW`    FOREIGN KEY (`TVSHOW`)    REFERENCES `meddb`.`TVSHOW` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_USTV_USER_idx` ON `meddb`.`USERTV` (`USER` ASC);

CREATE INDEX `FK_USTV_TVSHOW_idx` ON `meddb`.`USERTV` (`TVSHOW` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`USERALBUM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`USERALBUM` (  `USER` INT NOT NULL,  `ALBUM` INT NOT NULL,  `ISSIGNED` TINYINT(1) NOT NULL DEFAULT 0,  `RATING` INT NULL,  `COMMENT` VARCHAR(100) NULL,  PRIMARY KEY (`USER`, `ALBUM`),  CONSTRAINT `FK_USAL_USER`    FOREIGN KEY (`USER`)    REFERENCES `meddb`.`USER` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_USAL_ALBUM`    FOREIGN KEY (`ALBUM`)    REFERENCES `meddb`.`ALBUM` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_USAL_USER_idx` ON `meddb`.`USERALBUM` (`USER` ASC);

CREATE INDEX `FK_USAL_ALBUM_idx` ON `meddb`.`USERALBUM` (`ALBUM` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`ARTISTTYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`ARTISTTYPE` (  `ID` INT NOT NULL AUTO_INCREMENT,  `NAME` VARCHAR(45) NOT NULL,  PRIMARY KEY (`ID`)) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meddb`.`BOOKARTIST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`BOOKARTIST` (  `BOOK` INT NOT NULL,  `ARTIST` INT NOT NULL,  `TYPE` INT NULL,  PRIMARY KEY (`BOOK`, `ARTIST`),  CONSTRAINT `FK_BKAR_BOOK`   FOREIGN KEY (`BOOK`)    REFERENCES `meddb`.`BOOK` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_BKAR_ARTIST`    FOREIGN KEY (`ARTIST`)    REFERENCES `meddb`.`ARTIST` (`ID`)    ON DELETE NO ACTION   ON UPDATE NO ACTION,  CONSTRAINT `FK_BKAR_TYPE`    FOREIGN KEY (`TYPE`)    REFERENCES `meddb`.`ARTISTTYPE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_BKAR_BOOK_idx` ON `meddb`.`BOOKARTIST` (`BOOK` ASC);

CREATE INDEX `FK_BKAR_ARTIST_idx` ON `meddb`.`BOOKARTIST` (`ARTIST` ASC);

CREATE INDEX `FK_BKAR_TYPE_idx` ON `meddb`.`BOOKARTIST` (`TYPE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`MOVIEARTIST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`MOVIEARTIST` (  `MOVIE` INT NOT NULL,  `ARTIST` INT NOT NULL,  `TYPE` INT NULL,  PRIMARY KEY (`MOVIE`, `ARTIST`),  CONSTRAINT `FK_MVAR_ARTIST`    FOREIGN KEY (`ARTIST`)    REFERENCES `meddb`.`ARTIST` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_MVAR_MOVIE`    FOREIGN KEY (`MOVIE`)    REFERENCES `meddb`.`MOVIE` (`ID`)   ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_MVAR_TYPE`    FOREIGN KEY (`TYPE`)    REFERENCES `meddb`.`ARTISTTYPE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_MVAR_ARTIST_idx` ON `meddb`.`MOVIEARTIST` (`ARTIST` ASC);

CREATE INDEX `FK_MVAR_MOVIE_idx` ON `meddb`.`MOVIEARTIST` (`MOVIE` ASC);

CREATE INDEX `FK_MVAR_TYPE_idx` ON `meddb`.`MOVIEARTIST` (`TYPE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`ALBUMARTIST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`ALBUMARTIST` (  `ALBUM` INT NOT NULL,  `ARTIST` INT NOT NULL,  `TYPE` INT NULL,  PRIMARY KEY (`ALBUM`, `ARTIST`),  CONSTRAINT `FK_ALAR_ALBUM`    FOREIGN KEY (`ALBUM`)    REFERENCES `meddb`.`ALBUM` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_ALAR_ARTIST`    FOREIGN KEY (`ARTIST`)    REFERENCES `meddb`.`ARTIST` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_ALAR_TYPE`    FOREIGN KEY (`TYPE`)    REFERENCES `meddb`.`ARTISTTYPE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_ALAR_ALBUM_idx` ON `meddb`.`ALBUMARTIST` (`ALBUM` ASC);

CREATE INDEX `FK_ALAR_ARTIST_idx` ON `meddb`.`ALBUMARTIST` (`ARTIST` ASC);

CREATE INDEX `FK_ALAR_TYPE_idx` ON `meddb`.`ALBUMARTIST` (`TYPE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`TVARTIST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`TVARTIST` (  `TVSHOW` INT NOT NULL,  `ARTIST` INT NOT NULL,  `TYPE` INT NULL,  PRIMARY KEY (`TVSHOW`, `ARTIST`),  CONSTRAINT `FK_TVAR_TVSHOW`    FOREIGN KEY (`TVSHOW`)    REFERENCES `meddb`.`TVSHOW` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_TVAR_ARTIST`    FOREIGN KEY (`ARTIST`)    REFERENCES `meddb`.`ARTIST` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_TVAR_TYPE`    FOREIGN KEY (`TYPE`)    REFERENCES `meddb`.`ARTISTTYPE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_TVAR_TVSHOW_idx` ON `meddb`.`TVARTIST` (`TVSHOW` ASC);

CREATE INDEX `FK_TVAR_ARTIST_idx` ON `meddb`.`TVARTIST` (`ARTIST` ASC);

CREATE INDEX `FK_TVAR_TYPE_idx` ON `meddb`.`TVARTIST` (`TYPE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`TRACKARTIST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`TRACKARTIST` (  `TRACK` INT NOT NULL,  `ARTIST` INT NOT NULL,  `TYPE` INT NULL,  PRIMARY KEY (`TRACK`, `ARTIST`),  CONSTRAINT `FK_TRAR_TRACK`    FOREIGN KEY (`TRACK`)    REFERENCES `meddb`.`TRACK` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_TRAR_ARTIST`    FOREIGN KEY (`ARTIST`)    REFERENCES `meddb`.`ARTIST` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_TRAR_TYPE`    FOREIGN KEY (`TYPE`)    REFERENCES `meddb`.`ARTISTTYPE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_TRAR_TRACK_idx` ON `meddb`.`TRACKARTIST` (`TRACK` ASC);

CREATE INDEX `FK_TRAR_ARTIST_idx` ON `meddb`.`TRACKARTIST` (`ARTIST` ASC);

CREATE INDEX `FK_TRAR_TYPE_idx` ON `meddb`.`TRACKARTIST` (`TYPE` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`FRIEND`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`FRIEND` (  `USER` INT NOT NULL,  `FRIEND` INT NOT NULL,  `ISACCEPTED` TINYINT(1) NOT NULL DEFAULT 0,  `ISSHAREDCOLLECTION` TINYINT(1) NOT NULL DEFAULT 0,  PRIMARY KEY (`USER`, `FRIEND`),  CONSTRAINT `FK_USFD_USER`    FOREIGN KEY (`USER`)    REFERENCES `meddb`.`USER` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_USFD_FRIEND`    FOREIGN KEY (`FRIEND`)    REFERENCES `meddb`.`USER` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_USFD_USER_idx` ON `meddb`.`FRIEND` (`USER` ASC);

CREATE INDEX `FK_USFD_FRIEND_idx` ON `meddb`.`FRIEND` (`FRIEND` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`LOAN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`LOAN` (  `ID` INT NOT NULL AUTO_INCREMENT,  `USER` INT NOT NULL,  `FRIEND` INT NOT NULL,  `NAME` VARCHAR(45) NULL,  `BOOK` INT NULL,  `ALBUM` INT NULL,  `MOVIE` INT NULL,  `TVSHOW` INT NULL,  `STARTDATE` DATETIME NOT NULL,  `ENDDATE` DATETIME NULL,  PRIMARY KEY (`ID`),  CONSTRAINT `FK_LOANUSER_USER`    FOREIGN KEY (`USER`)    REFERENCES `meddb`.`USER` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_LOANFRIEND_FRIEND`    FOREIGN KEY (`FRIEND`)    REFERENCES `meddb`.`USER` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_LOANBOOK_BOOK`    FOREIGN KEY (`BOOK`)    REFERENCES `meddb`.`BOOK` (`ID`)   ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_LOANMOVIE_MOVIE`    FOREIGN KEY (`MOVIE`)    REFERENCES `meddb`.`MOVIE` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_LOANALBUM_ALBUM`    FOREIGN KEY (`ALBUM`)    REFERENCES `meddb`.`ALBUM` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT `FK_LOANTV_TVSHOW`    FOREIGN KEY (`TVSHOW`)    REFERENCES `meddb`.`TVSHOW` (`ID`)    ON DELETE NO ACTION    ON UPDATE NO ACTION) ENGINE = InnoDB;

CREATE INDEX `FK_LOANUSER_USER_idx` ON `meddb`.`LOAN` (`USER` ASC);

CREATE INDEX `FK_LOANBOOK_BOOK_idx` ON `meddb`.`LOAN` (`BOOK` ASC);

CREATE INDEX `FK_LOANMOVIE_MOVIE_idx` ON `meddb`.`LOAN` (`MOVIE` ASC);

CREATE INDEX `FK_LOANALBUM_ALBUM_idx` ON `meddb`.`LOAN` (`ALBUM` ASC);

CREATE INDEX `FK_LOANTV_TVSHOW_idx` ON `meddb`.`LOAN` (`TVSHOW` ASC);

CREATE INDEX `FK_LOANFRIEND_FRIEND_idx` ON `meddb`.`LOAN` (`FRIEND` ASC);


-- -----------------------------------------------------
-- Table `meddb`.`CONFIGURATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meddb`.`CONFIGURATION` (  `PARAMETER` VARCHAR(20) NOT NULL,  `VALUE` VARCHAR(60) NOT NULL,  PRIMARY KEY (`PARAMETER`)) ENGINE = InnoDB;

CREATE UNIQUE INDEX `parameter_UNIQUE` ON `meddb`.`CONFIGURATION` (`PARAMETER` ASC);
