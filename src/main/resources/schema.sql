drop table if exists COURSE;
drop table if exists CATEGORY;

create table CATEGORY (
    code serial unique primary key,
    description varchar (255) not null
);

create table COURSE (
        id serial unique primary key,
        description varchar (255) not null,
        start_date timestamp not null,
        end_date timestamp not null,
        students_qnt integer,
        category int not null,
        constraint category_fk foreign key (category) references CATEGORY (code) match simple
);