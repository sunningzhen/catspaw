create table frame_module (
	id varchar(36) primary key,
	parent_id varchar(36),
	code varchar(20),
	name varchar(20)
);
create table frame_menu (
	id varchar(36) primary key,
	parent_id varchar(36),
	module_id varchar(36),
	code varchar(20),
	name varchar(20),
	url varchar(100)
);