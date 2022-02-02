insert into resource.client_details(id, name, password, is_auto_approve, token_expired_in_second, created_by,
                                    created_date, last_update_by, last_update_date)
values ('sipening-auth', 'sipening-auth',
        '$2a$11$52ykJMuT3eooYzfYkZYIF.kZaStN4AGBCMI43aFoBC/Br5QcqHp3G', true, 43200, 'migration', now(), null, null),
       ('sipening-api', 'sipening-api',
        '$2a$11$52ykJMuT3eooYzfYkZYIF.kZaStN4AGBCMI43aFoBC/Br5QcqHp3G', true, 43200, 'migration', now(), null, null),
       ('sipening-webapp', 'sipening-webapp',
        '$2a$11$52ykJMuT3eooYzfYkZYIF.kZaStN4AGBCMI43aFoBC/Br5QcqHp3G', true, 43200, 'migration', now(), null, null),
       ('sipening-integration', 'sipening-integration',
        '$2a$11$52ykJMuT3eooYzfYkZYIF.kZaStN4AGBCMI43aFoBC/Br5QcqHp3G', true, 43200, 'migration', now(), null, null),
       ('sipening-report', 'sipening-report',
        '$2a$11$52ykJMuT3eooYzfYkZYIF.kZaStN4AGBCMI43aFoBC/Br5QcqHp3G', true, 43200, 'migration', now(), null, null);

insert into resource.applications (id, name, created_by, created_date, last_update_by, last_update_date)
values ('sipening', 'Sistem Pengelolaan Rekening', 'migration', now(), null, null);

insert into resource.client_detail_applications(id, client_detail_id, app_id, created_by, created_date, last_update_by,
                                                last_update_date)
values (uuid_generate_v4(), 'sipening-auth', 'sipening', 'migration', now(), null, null),
       (uuid_generate_v4(), 'sipening-api', 'sipening', 'migration', now(), null, null),
       (uuid_generate_v4(), 'sipening-webapp', 'sipening', 'migration', now(), null, null),
       (uuid_generate_v4(), 'sipening-report', 'sipening', 'migration', now(), null, null),
       (uuid_generate_v4(), 'sipening-integration', 'sipening', 'migration', now(), null, null);

insert into oauth.client_scopes(id, name, created_by, created_date, last_update_by, last_update_date)
values (2, 'write', 'migration', now(), null, null),
       (3, 'update', 'migration', now(), null, null),
       (4, 'delete', 'migration', now(), null, null);
