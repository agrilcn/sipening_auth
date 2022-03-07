insert into trtahunangg(c_tahun_angg, c_aktif)
values
('2020', true),
('2021', true),
('2022', true),
('2023', true);

insert into trsiappengguna(i_peg_nrk, i_sandi, n_peg, c_lock, c_aktif) values
('ADM01DINAS', '$2a$11$giyC4om5HWwRZsaYGUvTNOCpq3QAjLaEL9941Xaec0EzN758VLvcm', 'Admin Dinas', false, true);

insert into trsiapotoritas(c_otoritas, n_otoritas, e_otoritas, c_aktif) values
('ADMIN-DINAS', 'Admin Dinas', 'Administrator Dinas', true);

insert into trsiappenggunaotoritas(i_idpengguna, i_idotoritas) values
((select i_id from trsiappengguna where i_peg_nrk = 'ADM01DINAS'), (select i_id from trsiapotoritas where c_otoritas = 'ADMIN-DINAS'));

insert into trsiapaplikasi(no_urut, c_aplikasi, n_aplikasi, e_aplikasi, n_link, c_aktif) values
(1, 'UM', 'User Management', 'aplikasi untuk kelola pengguna, otoritas, aplikasi dan menu', 'user-management', true);

insert into trsiapotoritasaplikasi(i_idotoritas, i_idaplikasi) values
((select i_id from trsiapotoritas where c_otoritas = 'ADMIN-DINAS'), (select i_id from trsiapaplikasi where c_aplikasi = 'UM'));

insert into trsiapmenu(i_idaplikasi, i_idinduk, no_urut, c_menu , n_menu, e_menu, n_link, icon, c_aktif) values
((select i_id from trsiapaplikasi where c_aplikasi = 'UM'), null, 1, 'UM-PGUN', 'Pengguna', 'Pengguna Deskripsi', 'pengguna', 'file-text', true),
((select i_id from trsiapaplikasi where c_aplikasi = 'UM'), null, 2, 'UM-OTOR', 'Otoritas', 'Otoritas Deskripsi', 'otoritas', 'file-text', true),
((select i_id from trsiapaplikasi where c_aplikasi = 'UM'), null, 3, 'UM-APLI', 'Aplikasi', 'Aplikasi Deskripsi', 'aplikasi', 'file-text', true),
((select i_id from trsiapaplikasi where c_aplikasi = 'UM'), null, 4, 'UM-MENU', 'Menu', 'Menu Deskripsi', 'menu', 'file-text', true);

insert into trsiapotoritasmenu(i_idotoritas, i_idmenu) values
((select i_id from trsiapotoritas where c_otoritas = 'ADMIN-DINAS'), (select i_id from trsiapmenu where c_menu = 'UM-PGUN')),
((select i_id from trsiapotoritas where c_otoritas = 'ADMIN-DINAS'), (select i_id from trsiapmenu where c_menu = 'UM-OTOR')),
((select i_id from trsiapotoritas where c_otoritas = 'ADMIN-DINAS'), (select i_id from trsiapmenu where c_menu = 'UM-APLI')),
((select i_id from trsiapotoritas where c_otoritas = 'ADMIN-DINAS'), (select i_id from trsiapmenu where c_menu = 'UM-MENU'));