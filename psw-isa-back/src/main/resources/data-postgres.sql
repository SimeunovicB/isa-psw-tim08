insert into clinic(name,address,description) values('clinic1','dr klinike 1','very clininac clinic hoho');

insert into person(type,first_name,last_name,email,password,address,status,jmbg)
values('P','Nikola','Roncevic','nikola.roncevic123@gmail.com','password','dr ribara 1','ACTIVE','0203998800062');

insert into person(type,first_name,last_name,email,password,address,status,worktime_start,worktime_end,clinic_id)
values('MS','Doktor','Dokotorovic','doktor.doktorovic@gmail.com','password','dr sibara 1','ACTIVE','8am','4pm',1);

insert into clinic_patient(clinic_id,patient_id) values (1,1)


