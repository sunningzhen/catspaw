insert into frame_module(id, parent_id, code, name) values('1', null, 'test', 'test');
insert into frame_menu(id, parent_id, module_id, code, name, url) values('1', null, '1', 'system', 'system', null);
insert into frame_menu(id, parent_id, module_id, code, name, url) values('11', '1', '1', 'user', 'user', null);