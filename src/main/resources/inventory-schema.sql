drop table if exists inventory_item CASCADE;
create table inventory_item (
item_id bigint AUTO_INCREMENT,
inv_slots integer not null,
item_desc varchar(255),
item_name varchar(255),
item_type varchar(255),
upgradable boolean,
primary key (item_id)
);
