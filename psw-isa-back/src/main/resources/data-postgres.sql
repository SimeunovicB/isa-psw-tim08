insert into clinic(name,address,description) values('clinic1','dr klinike 1','very clininac clinic hoho');

--passwordi su 123
insert into person(type,first_name,last_name,username,password,address,status,jmbg,enabled)
values('P','Nikola','Roncevic','nikola.roncevic123@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr ribara 1','PENDING','0203998800062',true);

insert into person(type,first_name,last_name,username,password,address,status,worktime_start,worktime_end,clinic_id,enabled)
values('MS','Doktor','Dokotorovic','doktor.doktorovic@gmail.com','$2y$10$6/ExZPzokaxKh91XVMiSNOm/gsVJe8YkkXL.Kj3jivRJpGzagVnia','dr sibara 1','ACTIVE','8am','4pm',1,true);

insert into clinic_patient(clinic_id,patient_id) values (1,1);

INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);


