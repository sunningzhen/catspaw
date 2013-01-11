
-- ----------------------------
-- Records of frame_module
-- ----------------------------
INSERT INTO frame_module VALUES ('93694808-9738-4c27-970b-7573eb0756b4', 'SYSTEM', 'System', null, null, 'SYSTEM');

-- ----------------------------
-- Records of frame_menu
-- ----------------------------
INSERT INTO frame_menu VALUES ('000f21e3-4976-404e-ad32-d3c03388f2e8', 'MENU', 'Menu', null, '93694808-9738-4c27-970b-7573eb0756b4', '02bb71d3-2a02-465e-b66f-6d89eca33423', 'FRAME|MENU');
INSERT INTO frame_menu VALUES ('0240f835-04ce-4a00-937d-97ffc3799246', 'USER', 'User', null, '93694808-9738-4c27-970b-7573eb0756b4', '30d584c7-7b91-46eb-a116-b79524f6cf8e', 'RBAC|USER');
INSERT INTO frame_menu VALUES ('02bb71d3-2a02-465e-b66f-6d89eca33423', 'FRAME', 'Frame', null, '93694808-9738-4c27-970b-7573eb0756b4', null, 'FRAME');
INSERT INTO frame_menu VALUES ('17b0c1fb-57ac-4f78-bcd1-8b9d87ab419e', 'ORGANIZATION', 'Organization', null, '93694808-9738-4c27-970b-7573eb0756b4', null, 'ORGANIZATION');
INSERT INTO frame_menu VALUES ('2e7fd80a-a141-4562-87ce-c3b0f80d01c6', 'DEPARTMENT', 'Department', null, '93694808-9738-4c27-970b-7573eb0756b4', '17b0c1fb-57ac-4f78-bcd1-8b9d87ab419e', 'ORGANIZATION|DEPARTMENT');
INSERT INTO frame_menu VALUES ('30d584c7-7b91-46eb-a116-b79524f6cf8e', 'RBAC', 'RBAC', null, '93694808-9738-4c27-970b-7573eb0756b4', null, 'RBAC');
INSERT INTO frame_menu VALUES ('44ce3c77-fc71-44ca-a8a6-7ab52f373a0c', 'OPERATION', 'Operation', null, '93694808-9738-4c27-970b-7573eb0756b4', '30d584c7-7b91-46eb-a116-b79524f6cf8e', 'RBAC|OPERATION');
INSERT INTO frame_menu VALUES ('521fddb6-02ac-4f25-86c1-d5b03fe79405', 'MODULE', 'Module', null, '93694808-9738-4c27-970b-7573eb0756b4', '02bb71d3-2a02-465e-b66f-6d89eca33423', 'FRAME|MODULE');
INSERT INTO frame_menu VALUES ('52a941bc-c557-4d25-9662-fad9b6148f7a', 'RESOURCE', 'Resource', null, '93694808-9738-4c27-970b-7573eb0756b4', '30d584c7-7b91-46eb-a116-b79524f6cf8e', 'RBAC|RESOURCE');
INSERT INTO frame_menu VALUES ('83bad7c0-7d03-46d2-9646-4cd3446aa676', 'POST', 'Post', null, '93694808-9738-4c27-970b-7573eb0756b4', '17b0c1fb-57ac-4f78-bcd1-8b9d87ab419e', 'ORGANIZATION|POST');
INSERT INTO frame_menu VALUES ('87c8e86f-6679-40bf-8bde-a8f8db186eed', 'BRANCH', 'Branch', null, '93694808-9738-4c27-970b-7573eb0756b4', '17b0c1fb-57ac-4f78-bcd1-8b9d87ab419e', 'ORGANIZATION|BRANCH');
INSERT INTO frame_menu VALUES ('b108a590-31c4-4a2f-9189-8166253f60f8', 'ROLE', 'Role', null, '93694808-9738-4c27-970b-7573eb0756b4', '30d584c7-7b91-46eb-a116-b79524f6cf8e', 'RBAC|ROLE');
INSERT INTO frame_menu VALUES ('caa6cdea-3184-4a47-b128-e477f58262dd', 'SECURITY', 'Security', null, '93694808-9738-4c27-970b-7573eb0756b4', null, 'SECURITY');
INSERT INTO frame_menu VALUES ('e548b1b2-503f-4563-ba32-d503f417806a', 'PERMISSION', 'Permission', null, '93694808-9738-4c27-970b-7573eb0756b4', '30d584c7-7b91-46eb-a116-b79524f6cf8e', 'RBAC|PERMISSION');

-- ----------------------------
-- Records of rbac_resource
-- ----------------------------
INSERT INTO rbac_resource VALUES ('242b4970-5bb8-4e4b-991c-4bffec281c90', 'ORGANIZATION', '/organization/**', 'MENU');
INSERT INTO rbac_resource VALUES ('73529fde-1f2b-47b9-be4c-993787901831', 'ORGANIZATION_BRANCH', '/organization/branch.html', 'MENU');
INSERT INTO rbac_resource VALUES ('b2b9f239-404e-4832-870b-da4a40d6e5b0', 'RBAC', '/rbac/**', 'MENU');
INSERT INTO rbac_resource VALUES ('fc1a7f83-9490-4d43-b9e3-650cd76b8325', 'FRAME', '/frame/**', 'MENU');

-- ----------------------------
-- Records of rbac_operation
-- ----------------------------
INSERT INTO rbac_operation VALUES ('144a8a0b-6558-478c-88ac-5579bc0efb54', 'PRINT', 'PRINT');
INSERT INTO rbac_operation VALUES ('d2ee031e-acef-4ea1-bf3e-ea86d562f277', 'WRITE', 'WRITE');
INSERT INTO rbac_operation VALUES ('e3d8075c-fe68-4e0f-be20-5bc103c3d181', 'READ', 'READ');
INSERT INTO rbac_operation VALUES ('0642dbb4-d0cb-46eb-a963-1f5cf3ec587e', 'ALL', 'ALL');

-- ----------------------------
-- Records of rbac_permission
-- ----------------------------
INSERT INTO `rbac_permission` VALUES ('462db8da-f65f-429c-9646-9e15950e4b49', 'AUTH_RBAC:*', 'b2b9f239-404e-4832-870b-da4a40d6e5b0', 'RBAC', '0642dbb4-d0cb-46eb-a963-1f5cf3ec587e', 'ALL');
INSERT INTO `rbac_permission` VALUES ('50e46356-781d-4d91-9ace-a3b0defb2ea8', 'AUTH_ORGANIZATION:*', '242b4970-5bb8-4e4b-991c-4bffec281c90', 'ORGANIZATION', '0642dbb4-d0cb-46eb-a963-1f5cf3ec587e', 'ALL');
INSERT INTO `rbac_permission` VALUES ('a4f32b50-728a-4de6-aefc-a1dc2bc03d6a', 'AUTH_ORGANIZATION_BRANCH:*', '73529fde-1f2b-47b9-be4c-993787901831', 'ORGANIZATION_BRANCH', '0642dbb4-d0cb-46eb-a963-1f5cf3ec587e', 'ALL');
INSERT INTO `rbac_permission` VALUES ('b3d68588-09ef-4341-a108-345da298445c', 'AUTH_FRAME:WRITE', 'fc1a7f83-9490-4d43-b9e3-650cd76b8325', 'FRAME', 'd2ee031e-acef-4ea1-bf3e-ea86d562f277', 'WRITE');
INSERT INTO `rbac_permission` VALUES ('c4a8dd27-fcf1-4a0d-b564-0450da3db2df', 'AUTH_FRAME:*', 'fc1a7f83-9490-4d43-b9e3-650cd76b8325', 'FRAME', '0642dbb4-d0cb-46eb-a963-1f5cf3ec587e', 'ALL');
INSERT INTO `rbac_permission` VALUES ('ca76045e-e827-48ec-a22b-79ac915b0405', 'AUTH_ORGANIZATION:WRITE', '242b4970-5bb8-4e4b-991c-4bffec281c90', 'ORGANIZATION', 'd2ee031e-acef-4ea1-bf3e-ea86d562f277', 'WRITE');
INSERT INTO `rbac_permission` VALUES ('cdc447f5-e1eb-42bd-a548-49a6eac31c18', 'AUTH_RBAC:WRITE', 'b2b9f239-404e-4832-870b-da4a40d6e5b0', 'RBAC', 'd2ee031e-acef-4ea1-bf3e-ea86d562f277', 'WRITE');

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
INSERT INTO rbac_role VALUES ('5db56504-ce03-4614-85f4-015e73f49456', 'USER', 'User');
INSERT INTO rbac_role VALUES ('6722992d-d096-49d1-915b-727b336c2951', 'SYSTEM_ADMIN', 'System Administrator');
INSERT INTO rbac_role VALUES ('74b19076-c7e7-4f8d-8191-a0b31333a76e', 'ORGANIZATION_ADMIN', 'Organization Administrator');
INSERT INTO rbac_role VALUES ('dbc90f3c-66db-4233-97d0-8238a94bfb66', 'SECURITY_ADMIN', 'Security Administrator');

-- ----------------------------
-- Records of rbac_role_permission
-- ----------------------------
INSERT INTO rbac_role_permission VALUES ('6722992d-d096-49d1-915b-727b336c2951', 'b3d68588-09ef-4341-a108-345da298445c');
INSERT INTO rbac_role_permission VALUES ('6722992d-d096-49d1-915b-727b336c2951', 'c4a8dd27-fcf1-4a0d-b564-0450da3db2df');
INSERT INTO rbac_role_permission VALUES ('74b19076-c7e7-4f8d-8191-a0b31333a76e', '50e46356-781d-4d91-9ace-a3b0defb2ea8');
INSERT INTO rbac_role_permission VALUES ('74b19076-c7e7-4f8d-8191-a0b31333a76e', 'ca76045e-e827-48ec-a22b-79ac915b0405');
INSERT INTO rbac_role_permission VALUES ('dbc90f3c-66db-4233-97d0-8238a94bfb66', '462db8da-f65f-429c-9646-9e15950e4b49');
INSERT INTO rbac_role_permission VALUES ('dbc90f3c-66db-4233-97d0-8238a94bfb66', 'cdc447f5-e1eb-42bd-a548-49a6eac31c18');

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
INSERT INTO rbac_user VALUES ('5e959a92-c489-4a63-8ed2-d3e3c35b3aa0', 'secadmin', '123');
INSERT INTO rbac_user VALUES ('7ce0f770-9d87-4996-ac26-c9358703d2a1', 'sysadmin', '123');
INSERT INTO rbac_user VALUES ('c1c36f17-828f-4ac5-b73a-d3b5728ccaea', 'orgadmin', '123');
INSERT INTO rbac_user VALUES ('eeb76fb7-e8d1-4aa1-9f44-8f4693659d2d', 'user', '123');

-- ----------------------------
-- Records of rbac_user_role
-- ----------------------------
INSERT INTO rbac_user_role VALUES ('eeb76fb7-e8d1-4aa1-9f44-8f4693659d2d', '5db56504-ce03-4614-85f4-015e73f49456');
INSERT INTO rbac_user_role VALUES ('7ce0f770-9d87-4996-ac26-c9358703d2a1', '6722992d-d096-49d1-915b-727b336c2951');
INSERT INTO rbac_user_role VALUES ('c1c36f17-828f-4ac5-b73a-d3b5728ccaea', '74b19076-c7e7-4f8d-8191-a0b31333a76e');
INSERT INTO rbac_user_role VALUES ('5e959a92-c489-4a63-8ed2-d3e3c35b3aa0', 'dbc90f3c-66db-4233-97d0-8238a94bfb66');
