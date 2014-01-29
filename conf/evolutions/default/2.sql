# --- Sample dataset

# --- !Ups

insert into event (id,name, event_date, event_hour, description) values (  1,'Apple Inc.', '2001-01-01', '2001-01-01', 'descripcionesss');
insert into event (id,name, event_date, event_hour, description) values (  2,'Thinking Machines', '2001-01-01', '2001-01-01', 'descripcionesss');
insert into event (id,name, event_date, event_hour, description) values (  3,'RCA', '2001-01-01', '2001-01-01', 'descripcionesss');
insert into event (id,name, event_date, event_hour, description) values (  4,'Netronics', '2001-01-01', '2001-01-01', 'descripcionesss');

# --- !Downs

delete from event;
