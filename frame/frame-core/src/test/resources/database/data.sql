insert into frame_module(id, parent_id, code, name) values('1', null, 'SYSTEM', 'System');
insert into frame_module(id, parent_id, code, name) values('2', '1', 'SECURITY', 'Security');
insert into frame_menu(id, parent_id, module_id, code, name, url) values('1', null, '2', 'USER', 'User', null);
insert into frame_menu(id, parent_id, module_id, code, name, url) values('2', null, '2', 'ADD_USER', 'Add User', '/system/security/user/addUser.jsp');