CREATE TABLE teacherlist_houdai_rank (
  `ID`   SMALLINT(6) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `rank` VARCHAR(4)  NOT NULL,
  PRIMARY KEY (`ID`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

drop table teacherlist_schedule_houdai_favoriteTeacherID;
CREATE TABLE teacherlist_schedule_houdai_favoriteTeacherID (
  `dateid`          BIGINT NOT NULL,
  `timeid`          BIGINT  NOT NULL,
  favoriteTeacherID TINYINT(3)  NOT NULL DEFAULT '0',
  PRIMARY KEY (dateid, timeid)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;


CREATE TABLE teacherlist_houdai (
  `ID`            SMALLINT(6) NOT NULL,
  `name`          VARCHAR(50) NOT NULL,
  `japLevel`      TINYINT(3)  NOT NULL,
  `japJessLevel`  TINYINT(3)  NOT NULL,
  `studyMajor`    VARCHAR(60) NOT NULL,
  `university`    VARCHAR(60) NOT NULL,
  `occupation`    VARCHAR(50) NOT NULL,
  `teachExpLevel` TINYINT(3)  NOT NULL,
  PRIMARY KEY (`ID`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;


CREATE TABLE studentFavorite
(
  studentID         INT(11)     NOT NULL,
  favoriteTeacherID SMALLINT(6) NOT NULL,
  PRIMARY KEY (`studentID`),
  FOREIGN KEY (`favoriteTeacherID`) REFERENCES teacherlist_houdai (ID)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

drop table teacher_evaluate;
CREATE TABLE teacher_evaluate (
  `teacherID` SMALLINT(6) NOT NULL,
  `studentID` SMALLINT(6) NOT NULL,
  `classDate` BIGINT NOT NULL,
  `classTime` BIGINT  NOT NULL,
  `manzokudo` TINYINT(4)  NOT NULL DEFAULT '0',
  PRIMARY KEY (`teacherID`, `classDate`, `classTime`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;

CREATE TABLE apply_student_list (
  `ID`                    INT(11)     NOT NULL,
  `student_name_kanji`    VARCHAR(30) NOT NULL,
  `student_name_hiragana` VARCHAR(50) NOT NULL,
  `student_email`         VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ID`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8;
