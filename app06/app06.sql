DROP TABLE contact_t;
CREATE TABLE contact_t (
  contact_no NUMBER NOT NULL PRIMARY KEY,
  name       VARCHAR2(100 BYTE),
  email      VARCHAR2(100 BYTE),
  mobile     VARCHAR2(100 BYTE),
  created_at DATE
);

DROP SEQUENCE contact_seq;
CREATE SEQUENCE contact_seq;