INSERT INTO location(address)
values('Ha Noi');
INSERT INTO location(address)
values('Hue');
INSERT INTO location(address)
values('Da Nang');

INSERT INTO department(name, location_id)
values('S1',1);
INSERT INTO department(name, location_id)
values('S2',1);
INSERT INTO department(name, location_id)
values('S3',1);
INSERT INTO department(name, location_id)
values('SHue1',2);
INSERT INTO department(name, location_id)
values('SDN1',3);
INSERT INTO department(name, location_id)
values('SDN2',3);

INSERT INTO POSITION (name)
VALUES('Admin');
INSERT INTO POSITION (name)
VALUES('HR');
INSERT INTO POSITION (name)
VALUES('Intern Java');
INSERT INTO POSITION (name)
VALUES('ReactJS');
INSERT INTO POSITION (name)
VALUES('Java Develop');
INSERT INTO POSITION (name)
VALUES('ReactJS Develop');
INSERT INTO POSITION (name)
VALUES('Tester');

INSERT INTO nationality(name) VALUES('Vietnam');
INSERT INTO nationality(name) VALUES('Japan');
INSERT INTO nationality(name) VALUES('Canada');

INSERT INTO leave_type(name)
VALUES ('Nghỉ Không Lương');
INSERT INTO leave_type(name)
VALUES ('Nghỉ Vợ Ốm');
INSERT INTO leave_type(name)
VALUES ('Nghỉ Lễ');

INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USERHR');
INSERT INTO roles(name) VALUES('ROLE_USER');

INSERT INTO users(created_at, updated_at, email, name, password, username, position_id, department_id, manager_id, hire_date)
VALUES('2019-08-09 15:27:54','2019-08-09 15:27:54','admin@admin.com','admin','$2a$10$Sp.lZfnwAyLVr9io1ilk2O47paAPvMDbNMeHpBlWVSBDEZjH5jTfm', 'admin', 1, 1, 1, '2019-08-09 15:27:54');
INSERT INTO users(created_at, updated_at, email, name, password, username, position_id, department_id, manager_id, hire_date)
VALUES('2019-08-09 15:27:54','2019-08-09 15:27:54','userhr@userhr.com','userhr','$2a$10$tEE31VA6wAsQ5ejCvrIw..0t5EhH578O/O0yrGuLR3EP6vE4FVx2W', 'userhr', 2, 1, 1, '2019-08-09 15:27:54');
INSERT INTO users(created_at, updated_at, email, name, password, username, position_id, department_id, manager_id, hire_date)
VALUES('2019-08-09 15:27:54','2019-08-09 15:27:54','user@user.com','Nguyễn Anh Tú','$2a$10$eNaHi4bhk3aKO48lEa.Y3..ZiAmoi5FKXE.0bhVSgjNnswq9D39yi', 'user', 3, 1, 5, '2019-08-09 15:27:54');

INSERT INTO users(created_at, updated_at, email, name, password, username, position_id, department_id, manager_id, hire_date)
VALUES('2019-08-09 15:27:54','2019-08-09 15:27:54','anhnm@user.com','Nguyễn Mai Anh','$2a$10$eNaHi4bhk3aKO48lEa.Y3..ZiAmoi5FKXE.0bhVSgjNnswq9D39yi', 'anhnm', 4, 1, 5,'2019-08-09 15:27:54');
INSERT INTO users(created_at, updated_at, email, name, password, username, position_id, department_id, manager_id, hire_date)
VALUES('2019-08-09 15:27:54','2019-08-09 15:27:54','lenh@user.com','Hoàng Nhật Lệ','$2a$10$eNaHi4bhk3aKO48lEa.Y3..ZiAmoi5FKXE.0bhVSgjNnswq9D39yi', 'lehn', 5, 1, 1,'2019-08-09 15:27:54');
INSERT INTO users(created_at, updated_at, email, name, password, username, position_id, department_id, manager_id, hire_date)
VALUES('2019-08-09 15:27:54','2019-08-09 15:27:54','huyendt@user.com','Đường Thị Huyền','$2a$10$eNaHi4bhk3aKO48lEa.Y3..ZiAmoi5FKXE.0bhVSgjNnswq9D39yi', 'huyendt', 6, 1, 5,'2019-08-09 15:27:54');
INSERT INTO users(created_at, updated_at, email, name, password, username, position_id, department_id, manager_id, hire_date)
VALUES('2019-08-09 15:27:54','2019-08-09 15:27:54','minhnv@user.com','Nguyễn Văn Minh','$2a$10$eNaHi4bhk3aKO48lEa.Y3..ZiAmoi5FKXE.0bhVSgjNnswq9D39yi', 'minhnv', 7, 1, 5, '2019-08-09 15:27:54');

INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);
INSERT INTO user_roles(user_id, role_id) VALUES(3, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(4, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(5, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(6, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(7, 3);

INSERT INTO leave(comment, start_date, end_date, is_delete, status, leave_type_id, user_id)
VALUES ('Thich thi nghi', '2019-08-13', '2019-08-14', false, 0, 2, 1);
INSERT INTO leave(comment, start_date, end_date, is_delete, status, leave_type_id, user_id)
VALUES ('comment', '2019-08-26', '2019-08-28', true, 0, 2, 2);
INSERT INTO leave(comment, start_date, end_date, is_delete, status, leave_type_id, user_id)
VALUES ('Ban', '2019-08-26', '2019-08-30', false, 2, 2, 3);
INSERT INTO leave(comment, start_date, end_date, is_delete, status, leave_type_id, user_id)
VALUES ('Ban', '2019-08-26', '2019-08-26', false, 2, 2, 3);

INSERT INTO candidate(name,interview_date,position_id, user_id) values('Nguyễn Văn Hùng','2019-07-15',1,1);
INSERT INTO candidate(name,interview_date,position_id, user_id) values('Hoàng Thu Thảo','2019-08-25',2,1);
INSERT INTO candidate(name,interview_date,position_id, user_id) values('Nguyễn Văn Hùng','2019-03-30',1,3);
INSERT INTO candidate(name,interview_date,position_id, user_id) values('Nguen Van hung','2019-02-13',1,1);
INSERT INTO candidate(name,interview_date,position_id, user_id) values('Nguen Van hung','2019-02-13',2,1);
INSERT INTO candidate(name,interview_date,position_id, user_id) values('Nguen Van hung','2019-02-13',1,3);
INSERT INTO candidate(name,interview_date,position_id, user_id) values('Nguen Van hung','2019-02-13',3,2);
