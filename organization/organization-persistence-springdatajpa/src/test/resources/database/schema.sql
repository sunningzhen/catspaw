-- ----------------------------
-- Table structure for frame_module
-- ----------------------------
CREATE TABLE frame_module (
  id varchar(255) NOT NULL,
  code varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  parent_id varchar(255) DEFAULT NULL,
  url varchar(255) DEFAULT NULL,
  frame varchar(2000) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for frame_menu
-- ----------------------------
CREATE TABLE frame_menu (
  id varchar(255) NOT NULL,
  code varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  url varchar(255) DEFAULT NULL,
  module_id varchar(255) DEFAULT NULL,
  parent_id varchar(255) DEFAULT NULL,
  frame varchar(2000) DEFAULT NULL,
  PRIMARY KEY (id)
);
-- ----------------------------
-- Table structure for org_branch
-- ----------------------------
CREATE TABLE org_branch (
  id varchar(36) NOT NULL DEFAULT '',
  parent_id varchar(36) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  code varchar(20) DEFAULT NULL,
  frame varchar(200) DEFAULT NULL,
  level int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for org_department
-- ----------------------------
CREATE TABLE org_department (
  id varchar(36) NOT NULL DEFAULT '',
  parent_id varchar(36) DEFAULT NULL,
  branch_id varchar(36) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  code varchar(20) DEFAULT NULL,
  frame varchar(200) DEFAULT NULL,
  level int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for org_post
-- ----------------------------
CREATE TABLE org_post (
  id varchar(36) NOT NULL DEFAULT '',
  department_id varchar(36) DEFAULT NULL,
  name varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for org_staff
-- ----------------------------
CREATE TABLE org_staff (
  id varchar(36) NOT NULL DEFAULT '',
  department_id varchar(36) DEFAULT NULL,
  no varchar(20) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  sex varchar(20) DEFAULT NULL,
  birthday date DEFAULT NULL,
  identity_no varchar(50) DEFAULT NULL,
  entry_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for org_staff_post
-- ----------------------------
CREATE TABLE org_staff_post (
  staff_id varchar(36) NOT NULL DEFAULT '',
  post_id varchar(36) NOT NULL DEFAULT '',
  PRIMARY KEY (staff_id,post_id)
);

-- ----------------------------
-- Table structure for rbac_resource
-- ----------------------------
CREATE TABLE rbac_resource (
  id varchar(36) NOT NULL,
  symbol varchar(200) DEFAULT NULL,
  resource_string varchar(200) DEFAULT NULL,
  type varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for rbac_operation
-- ----------------------------
CREATE TABLE rbac_operation (
  id varchar(36) NOT NULL,
  symbol varchar(20) DEFAULT NULL,
  operation_string varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for rbac_permission
-- ----------------------------
CREATE TABLE rbac_permission (
  id varchar(36) NOT NULL,
  permit varchar(50) DEFAULT NULL,
  resource_id varchar(36) DEFAULT NULL,
  resource_symbol varchar(20) DEFAULT NULL,
  operation_id varchar(36) DEFAULT NULL,
  operation_symbol varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
CREATE TABLE rbac_role (
  id varchar(36) NOT NULL,
  code varchar(50) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for rbac_role_permission
-- ----------------------------
CREATE TABLE rbac_role_permission (
  role_id varchar(36) NOT NULL DEFAULT '',
  permission_id varchar(36) NOT NULL DEFAULT '',
  PRIMARY KEY (role_id,permission_id)
);

-- ----------------------------
-- Table structure for rbac_user
-- ----------------------------
CREATE TABLE rbac_user (
  id varchar(36) NOT NULL,
  username varchar(50) DEFAULT NULL,
  password varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for rbac_user_role
-- ----------------------------
CREATE TABLE rbac_user_role (
  user_id varchar(36) NOT NULL DEFAULT '',
  role_id varchar(36) NOT NULL DEFAULT '',
  PRIMARY KEY (user_id,role_id)
);

-- ----------------------------
-- Table structure for rbac_user_staff
-- ----------------------------
CREATE TABLE rbac_user_staff (
  user_id varchar(36) NOT NULL DEFAULT '',
  staff_id varchar(36) NOT NULL DEFAULT '',
  PRIMARY KEY (user_id,staff_id)
);