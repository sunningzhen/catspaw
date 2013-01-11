create table frame_module (
	id varchar(36) primary key,
	parent_id varchar(36),
	code varchar(50),
	name varchar(50)
);
create table frame_menu (
	id varchar(36) primary key,
	parent_id varchar(36),
	module_id varchar(36),
	code varchar(50),
	name varchar(50),
	url varchar(100)
);