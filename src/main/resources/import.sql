INSERT INTO member (age, id, email, name, phone_number, rank, member_status) select 30,  NEXTVAL('member_seq'), 'wshan@win.co.kr', '한우석', '010-7742-1893', '100', '1' from dual;
