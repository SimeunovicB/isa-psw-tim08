-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

insert into clinic(name,address,description) values('clinic1','45.2471-19.8035','clinic desc');

--passwordi su 123
insert into person(type,first_name,last_name,username,password,address,status,jmbg,enabled,version)
values('P','Pera','Peric','pera.peric@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE','0203998800062',true,0);

insert into person(type,first_name,last_name,username,password,address,status,worktime_start,worktime_end,clinic_id,enabled)
values('D','Marko','Markovic','marko.markovic@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE','8am','4pm',1,true);

insert into person(type,first_name,last_name,username,password,address,status,jmbg,enabled)
values('P','Jovan','Peric','j.peric@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE','0203998800022',true);

insert into person(type,first_name,last_name,username,password,address,status,jmbg,enabled)
values('P','test','test','nikola.roncevic123@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE','02039988000223',true);

insert into person(type,first_name,last_name,username,password,address,status,worktime_start,worktime_end,clinic_id,enabled)
values('N','Stefan','Stefanovic','stefan.stefanovic@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE','8am','4pm',1,true);

insert into person(type,first_name,last_name,username,password,address,status,enabled,clinic_id)
values('A','Dragan','Draganovic','dragan.draganovic@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE',true,1);

insert into person(type,first_name,last_name,username,password,address,status,enabled)
values('CCA','Nikola','Nikolic','nikola.nikolic@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','ACTIVE',true);

insert into clinic_patient(clinic_id,patient_id) values (1,1);
insert into clinic_patient(clinic_id,patient_id) values (1,3);
insert into clinic_patient(clinic_id,patient_id) values (1,4);

insert into medical_record(height,weight,diopter,alergies,blood_type,patient_id) values (176,77,3.2,'polen mleko','O',1);

insert into vacation(start_date,end_date,status,staff_id) values ('2020-06-01','2020-06-10','PENDING',2);
insert into vacation(start_date,end_date,status,staff_id) values ('2020-07-01','2020-07-10','PENDING',3);

insert into room (name,number, clinic_id) values ('Sala za pregled' ,1, 1);
insert into room (name,number, clinic_id) values ('Sala za pregled', 2, 1);


insert into appointment(date,discount,price,status,type,doctor_id,room_id,patient_id) values ('2020-02-01 12:00:00.000',1,0,'','Hirurg',2,1,1);
insert into appointment(date,discount,price,status,type,doctor_id,room_id,patient_id) values ('2020-02-02 13:00:00.000',5,0,'','Hirurg',2,1,1);
insert into appointment(date,discount,price,status,type,doctor_id,room_id,patient_id) values ('2020-02-02 14:00:00.000',12,0,'','Pedijatar',2,1,1);

--predefinisani
insert into appointment(date,discount,price,status,type,doctor_id,room_id) values ('2020-03-02 15:00:00.000',12,123123,'','Pedijatar',2,1);
insert into appointment(date,discount,price,status,type,doctor_id,room_id) values ('2020-02-02 16:00:00.000',12,123123,'','Pedijatar',2,1);
insert into appointment(date,discount,price,status,type,doctor_id,room_id) values ('2020-02-02 19:00:00.000',12,123123,'','Pedijatar',2,1);


INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

insert into appointment_type (name) VALUES ('Rutinski pregled');
insert into appointment_type (name) VALUES ('Operacija');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 2);


insert into diagnosis (name) values ('prehlada');
insert into diagnosis (name) values ('kasalj');
insert into diagnosis (name) values ('viroza');
insert into diagnosis (name) values ('izmorenost');

insert into medicine (name) values ('penicilin');
insert into medicine (name) values ('brufen');
insert into medicine (name) values ('antibiotik');
insert into medicine (name) values ('vinjak');

insert into recipe(medicine,patient_id,nurse_id) values ('penicilin',1,5);
insert into recipe(medicine,patient_id,nurse_id) values ('penicilin',1,5);
insert into recipe(medicine,patient_id) values ('penicilin',1);
insert into recipe(medicine,patient_id) values ('penicilin',1);

insert into appointment_request(doctor_id,patient_id,date,appointment_type) values (2,1,'2020-07-01','pregled');
insert into appointment_request(doctor_id,patient_id,date,appointment_type) values (2,4,'2020-07-01','operacija');
insert into appointment_request(doctor_id,patient_id,date,appointment_type) values (2,3,'2020-07-01','operacija');
insert into appointment_request(doctor_id,patient_id,date,appointment_type) values (2,4,'2020-07-01','pregled');

insert into patient_clinic_grades(patient_id, clinic_id,grade) values (1,1,5);
insert into patient_clinic_grades(patient_id, clinic_id,grade) values (3,1,3);
insert into patient_clinic_grades(patient_id, clinic_id,grade) values (4,1,4);

insert into patient_doctor_grades(patient_id, doctor_id,grade) values (1,2,5);
insert into patient_doctor_grades(patient_id, doctor_id,grade) values (3,2,2);
insert into patient_doctor_grades(patient_id, doctor_id,grade) values (4,2,3);