drop table if exists inventory_item CASCADE;
create table inventory_item (
item_id bigint AUTO_INCREMENT,
item_name varchar(255),
item_desc varchar(255),
item_type varchar(255),
inv_slots integer not null,
upgradable boolean,
primary key (item_id)
);
