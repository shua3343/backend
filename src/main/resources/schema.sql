create table if not exists CATEGORY (
    code serial unique primary key,
    description varchar (255) not null
);

create table if not exists COURSE (
        id serial unique primary key,
        description varchar (255) not null,
        start_date timestamp not null,
        end_date timestamp not null,
        students_qnt integer,
        category int not null,
        constraint category_fk foreign key (category) references CATEGORY (code) match simple
);

create table if not exists USERS (
    id serial primary key,
    email varchar(40) not null,
    "name" varchar(40) not null,
    "password" varchar(255) not null,
    username varchar(20) not null
)
