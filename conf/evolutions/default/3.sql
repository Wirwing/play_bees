# --- First database schema

# --- !Ups

set ignorecase true;

create table running (
  id                    bigint not null AUTO_INCREMENT,
  name                  varchar(255) not null,
  length              	int,
  price              	float,
  constraint pk_running primary key (id))
;

# --- !Downs

drop table if exists running;


# --- Sample dataset

# --- !Ups

insert into running (id,name, length, price) values (  1,'aniversario FMAT', 10, 100.00);
insert into running (id,name, length, price) values (  2,'10 km televisa', 10, 200.00);
insert into running (id,name, length, price) values (  3,'maraton', 10, 300.00);
insert into running (id,name, length, price) values (  4,'aniversario merida', 10, 400.00);

# --- !Downs
