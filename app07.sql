DROP SEQUENCE upload_seq;
DROP SEQUENCE file_seq;
CREATE SEQUENCE upload_seq;
CREATE SEQUENCE file_seq;

DROP TABLE file_t;
DROP TABLE upload_t;

CREATE TABLE upload_t (
  upload_no NUMBER NOT NULL,
  uploader  VARCHAR2(100 BYTE),
  ip        VARCHAR2(50 BYTE),
  upload_dt DATE, 
  CONSTRAINT pk_upload PRIMARY KEY(upload_no)
);
CREATE TABLE file_t (
  file_no           NUMBER NOT NULL,
  upload_path       VARCHAR2(500 BYTE),
  original_filename VARCHAR2(300 BYTE),
  filesystem_name   VARCHAR2(40 BYTE),
  upload_no         NUMBER,
  CONSTRAINT pk_file PRIMARY KEY(file_no),
  CONSTRAINT fk_file_upload FOREIGN KEY(upload_no)
    REFERENCES upload_t(upload_no)
      ON DELETE CASCADE
);

select u.upload_no, u.uploader, u.ip, u.upload_dt, f.file_no, f.original_filename
  from upload_t u left join file_t f
    on u.upload_no = f.upload_no;

-- inner join : upload_t 과 file_t 에 모두 존재해야만 조회된다.
-- left join  : upload_t 에 존재하면 file_t 에 없어도 조회된다.
--              왼쪽에 있는 정보는 항상 조회된다.

select u.upload_no, u.uploader, f.file_no
  from upload_t u left join file_t f
    on u.upload_no = f.upload_no
 group by u.upload_no, u.uploader;
 








