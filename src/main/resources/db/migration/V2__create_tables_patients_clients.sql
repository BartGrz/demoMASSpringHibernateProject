CREATE table patients (

    id integer primary key auto_increment,
    patient_name varchar(100),
    id_client integer,
    id_card integer



);

CREATE table clients (

        id integer primary key auto_increment,
        client_name varchar(100)


);