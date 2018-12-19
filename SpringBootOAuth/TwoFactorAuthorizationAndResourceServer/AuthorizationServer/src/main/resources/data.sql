insert into permission(permission_name) values ('view_permission');

insert into permission(permission_name) values ('create_role');
insert into permission(permission_name) values ('edit_role');
insert into permission(permission_name) values ('view_role');
insert into permission(permission_name) values ('delete_role');

insert into permission(permission_name) values ('assign_permissions_to_role');
insert into permission(permission_name) values ('view_permissions_by_role');


insert into permission(permission_name) values ('create_users');
insert into permission(permission_name) values ('edit_users');
insert into permission(permission_name) values ('view_users');
insert into permission(permission_name) values ('delete_users');


insert into permission(permission_name) values ('assign_users_to_roles');
insert into permission(permission_name) values ('view_users_by_role');

insert into role (role_name) values('Administrator');

insert into role_permission (role_id, permission_id) values(1,1);
insert into role_permission (role_id, permission_id) values(1,2);
insert into role_permission (role_id, permission_id) values(1,3);
insert into role_permission (role_id, permission_id) values(1,4);

insert into role_permission (role_id, permission_id) values(1,5);
insert into role_permission (role_id, permission_id) values(1,6);
insert into role_permission (role_id, permission_id) values(1,7);
insert into role_permission (role_id, permission_id) values(1,8);

insert into role_permission (role_id, permission_id) values(1,9);
insert into role_permission (role_id, permission_id) values(1,10);
insert into role_permission (role_id, permission_id) values(1,11);
insert into role_permission (role_id, permission_id) values(1,12);

insert into role_permission (role_id, permission_id) values(1,13);

insert into users(first_name, last_name, email_id, password, mobile, country, user_type, is_tfa_enabled, tfa_code, tfa_expire_time, tfa_default_type) values('Kite', 'Danie', 'kite_d@gmail.com','$2a$10$4zvDxdC3Y8KcCXCikw9fbuKwTesHxHh.bih7J6OFn22BioKQLdOTC','9902583940','USA','super_admin','Y','','','SMS');
insert into users(first_name, last_name, email_id, password, mobile, country, user_type, is_tfa_enabled, tfa_code, tfa_expire_time, tfa_default_type) values('William', 'John', 'william_j@gmail.com','$2a$10$96W88y2OvetXaAL2KZpJBe9xq.9ZzWW5DMtSYVNKYm/kyUjnLYKn2','8192508792','UK','admin', 'Y','','','EMAIL');

insert into role_users(role_id, user_Id) values(1,2);
