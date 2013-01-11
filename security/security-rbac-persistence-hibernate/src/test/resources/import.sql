
insert into rbac_resource(id, code, resource_string) values('7ec90200-8ad1-4e7c-8bd3-e4732f9fc919', 'SYSTEM', '/system.cmd')
insert into rbac_resource(id, code, resource_string) values('2539b84c-4067-49aa-ac77-4089c9d05f80', 'PROTECTED', '/protected.html')
insert into rbac_resource(id, code, resource_string) values('ffeafa9b-43f7-46b9-8d41-5fd31db28f6c', 'FREE', '/free.html')
insert into rbac_operation(id, code, action_string) values('e4162391-e940-4bed-8194-0c4da0e43df2', 'ACCESS', 'ACCESS')
insert into rbac_permission(id, code, domain_id, domain_code, action_id, action_code) values('a125c3e5-4c9e-4da7-afaf-555ced36cd67', 'ACCESS_SYSTEM', '7ec90200-8ad1-4e7c-8bd3-e4732f9fc919', 'SYSTEM', 'e4162391-e940-4bed-8194-0c4da0e43df2', 'ACCESS')
insert into rbac_permission(id, code, domain_id, domain_code, action_id, action_code) values('22fcbad5-37d2-4d1c-9d8c-9bfdc33899d3', 'ACCESS_PROTECTED', '2539b84c-4067-49aa-ac77-4089c9d05f80', 'PROTECTED', 'e4162391-e940-4bed-8194-0c4da0e43df2', 'ACCESS')
insert into rbac_permission(id, code, domain_id, domain_code, action_id, action_code) values('748e5cda-0e10-476a-8acb-42acb9472683', 'ACCESS_FREE', 'ffeafa9b-43f7-46b9-8d41-5fd31db28f6c', 'FREE', 'e4162391-e940-4bed-8194-0c4da0e43df2', 'ACCESS')

insert into rbac_resource(id, code, resource_string) values('79a1c111-32fc-42a1-bb8f-aa1bb577077b', 'AREA_531', '531')
insert into rbac_permission(id, code, domain_id, domain_code) values('bb1dea36-cd07-48bf-ac1c-08da95146882', '531', '79a1c111-32fc-42a1-bb8f-aa1bb577077b', '531')

insert into rbac_role(id, name, code) values('4a53e8bf-8c06-4f4e-b032-12e6d1a0eedd', 'Administrator', 'ADMIN')
insert into rbac_role(id, name, code) values('0a5143d6-dc88-484b-8abf-26e60be31c26', 'Anonymous', 'ANONYMOUS')
insert into rbac_role(id, name, code) values('80cc80f6-2ddf-46f6-a1c9-d4a4193de667', 'CEO', 'CEO')
insert into rbac_role(id, name, code) values('363ddb02-b0c1-4cf7-b417-8e491a3a8094', 'Manager', 'MANAGER')
insert into rbac_role(id, name, code) values('03556825-aa85-47f7-8ece-51a1b7120c9d', 'Normal', 'NORMAL')

insert into rbac_role_permission(role_id, permission_id) values('03556825-aa85-47f7-8ece-51a1b7120c9d', '748e5cda-0e10-476a-8acb-42acb9472683')
insert into rbac_role_permission(role_id, permission_id) values('03556825-aa85-47f7-8ece-51a1b7120c9d', 'a125c3e5-4c9e-4da7-afaf-555ced36cd67')
insert into rbac_role_permission(role_id, permission_id) values('363ddb02-b0c1-4cf7-b417-8e491a3a8094', '748e5cda-0e10-476a-8acb-42acb9472683')
insert into rbac_role_permission(role_id, permission_id) values('363ddb02-b0c1-4cf7-b417-8e491a3a8094', '22fcbad5-37d2-4d1c-9d8c-9bfdc33899d3')
insert into rbac_role_permission(role_id, permission_id) values('363ddb02-b0c1-4cf7-b417-8e491a3a8094', 'a125c3e5-4c9e-4da7-afaf-555ced36cd67')
insert into rbac_role_permission(role_id, permission_id) values('03556825-aa85-47f7-8ece-51a1b7120c9d', 'bb1dea36-cd07-48bf-ac1c-08da95146882')

insert into rbac_user(id, username, password) values('72b3610b-994c-4960-a98a-2ad1909e0515', 'admin', 'admin')
insert into rbac_user(id, username, password) values('a8289c1d-2955-4c59-90b0-53830600e720', 'ceo', 'ceo')
insert into rbac_user(id, username, password) values('7ce30c8e-7539-4271-bb9a-44b4cc4b23e3', 'manager', 'manager')
insert into rbac_user(id, username, password) values('62c2debf-f7ae-4869-8bdf-b528782082e8', 'user1', 'user1')

insert into rbac_user_role(user_id, role_id) values('7ce30c8e-7539-4271-bb9a-44b4cc4b23e3', '363ddb02-b0c1-4cf7-b417-8e491a3a8094')
insert into rbac_user_role(user_id, role_id) values('62c2debf-f7ae-4869-8bdf-b528782082e8', '03556825-aa85-47f7-8ece-51a1b7120c9d')

