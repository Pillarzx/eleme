CREATE TABLE users(
u_no NUMBER(4) PRIMARY KEY,
u_name VARCHAR2(30) NOT NULL,
u_pwd Varchar2(16) CONSTRAINT u_pwd_check CHECK (length(u_pwd)>=8 AND length(u_pwd)<=16),
u_tell NUMBER(11),
u_role NUMBER(1) CONSTRAINT user_role_check CHECK (u_role IN(0,1)),
u_money NUMBER(10,2) NOT NULL
);

INSERT INTO users VALUES (u_seq.NEXTVAL,'b','123456789',13800000000,1,30);
-----------------------------
CREATE TABLE dishes(
g_no number(4) PRIMARY KEY,
g_name Varchar2(30) ,
g_price NUMBER(4,2) ,
g_like_num NUMBER(6),
g_state NUMBER(1) CONSTRAINT g_state_check CHECK (g_state IN(0,1))
);
ALTER TABLE dishes ADD CONSTRAINT g_name_uq UNIQUE(g_name);
ALTER TABLE dishes ADD CONSTRAINT g_price_check CHECK(g_price<100);

INSERT INTO dishes VALUE(g_seq.NEXTVAL,'ÓãÏãÈâË¿',25.00,0,0);
----------------------------------------
CREATE TABLE orders(
o_no NUMBER(4) PRIMARY KEY,
u_no NUMBER(4),
g_no NUMBER(4),
o_num NUMBER(4),
o_time Varchar2(20) NOT NULL,
o_address varchar2(40) NOT NULL,
o_state NUMBER(1) CONSTRAINT o_state_check CHECK (o_state IN(0,1,2))
);
ALTER TABLE orders ADD CONSTRAINT u_no_fk FOREIGN KEY (u_no) REFERENCES users(u_no) ON DELETE SET NULL;
ALTER TABLE orders ADD CONSTRAINT g_no_fk FOREIGN KEY (g_no) REFERENCES dishes(g_no) ON DELETE SET NULL;

INSERT INTO dishes VALUES(o_seq.NEXTVAL,'yutou',25.00,0,1);

-----------------------
CREATE SEQUENCE u_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE g_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE o_seq START WITH 1 INCREMENT BY 1;