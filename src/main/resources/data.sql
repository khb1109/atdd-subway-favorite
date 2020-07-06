INSERT INTO MEMBER(id, email, name, password) VALUES ( 1, 'a@a.com', 'a', '1');

insert into LINE(id, name, start_time, end_time, interval_time) values (1, '1호선', CURRENT_TIME(), CURRENT_TIME(), 8);
insert into LINE(id, name, start_time, end_time, interval_time) values (2, '2호선', CURRENT_TIME(), CURRENT_TIME(), 8);

insert into STATION(id, name) values (1, '신당');
insert into STATION(id, name)  values (2, '상왕십리');
insert into STATION(id, name)  values (3, '왕십리');
insert into STATION(id, name)  values (4, '한양대');
insert into STATION(id, name)  values (5, '뚝섬');

insert into LINE_STATION(id, pre_station_id,station_id,distance,duration,line) values (1, null, 1, 0, 10,1);
insert into LINE_STATION(id, pre_station_id,station_id,distance,duration,line) values (2, 1, 2, 5, 10,1);
insert into LINE_STATION(id, pre_station_id,station_id,distance,duration,line) values (3, 2, 3, 5, 10,1);
insert into LINE_STATION(id, pre_station_id,station_id,distance,duration,line) values (4, 3, 4, 3, 10,1);

insert into LINE_STATION(id, pre_station_id,station_id,distance,duration,line) values (5, null, 4, 0, 10,2);
insert into LINE_STATION(id, pre_station_id,station_id,distance,duration,line) values (6, 4, 2, 9, 10 ,2);
insert into LINE_STATION(id, pre_station_id,station_id,distance,duration,line) values (7, 2, 5, 3, 10 ,2);

insert into FAVORITE(id, member_id,source_station_id, target_station_id ) values (1,1, 1, 5);
