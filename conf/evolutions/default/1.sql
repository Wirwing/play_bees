# --- First database schema

# --- !Ups

set ignorecase true;

create table event (
  id                        bigint not null AUTO_INCREMENT,
  name                      varchar(255) not null,
  event_date              	timestamp,
  event_hour              	timestamp,
  description               varchar(500),
  constraint pk_event primary key (id))
;

# --- !Downs

drop table if exists event;