DROP SEQUENCE UPLOAD_SEQ;
DROP SEQUENCE FILE_SEQ;
CREATE SEQUENCE UPLOAD_SEQ;
CREATE SEQUENCE FILE_SEQ;

DROP TABLE FILE_T;
DROP TABLE UPLOAD_T;

CREATE TABLE UPLOAD_T (
  UPLOAD_NO NUMBER NOT NULL,
  UPLOADER  VARCHAR2(100 BYTE),
  IP        VARCHAR2(50 BYTE),
  UPLOAD_DT DATE, 
  CONSTRAINT PK_UPLOAD PRIMARY KEY(UPLOAD_NO)
);
CREATE TABLE FILE_T (
  FILE_NO           NUMBER NOT NULL,
  UPLOAD_PATH       VARCHAR2(500 BYTE),
  ORIGINAL_FILENAME VARCHAR2(300 BYTE),
  FILESYSTEM_NAME   VARCHAR2(40 BYTE),
  UPLOAD_NO         NUMBER,
  CONSTRAINT PK_FILE PRIMARY KEY(FILE_NO),
  CONSTRAINT FK_FILE_UPLOAD FOREIGN KEY(UPLOAD_NO)
    REFERENCES UPLOAD_T(UPLOAD_NO)
      ON DELETE CASCADE
);

SELECT U.upload_no, U.uploader, U.ip, U.upload_dt, F.file_no, F.original_filename
  FROM upload_t U LEFT JOIN file_t F
    ON U.upload_no = F.upload_no;

-- inner join : upload_t 과 file_t 에 모두 존재해야만 조회된다.
-- left join  : upload_t 에 존재하면 file_t 에 없어도 조회된다.
--              왼쪽에 있는 정보는 항상 조회된다.

SELECT U.upload_no, U.uploader, COUNT(F.file_no) AS file_cnt
  FROM upload_t U LEFT JOIN file_t F
    ON U.upload_no = F.upload_no
 GROUP BY U.upload_no, U.uploader;  -- group by 절에 명시된 칼럼만 select 절에서 조회할 수 있다.