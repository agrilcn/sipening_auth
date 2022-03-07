create sequence trtahunangg_id_seq;
create table trtahunangg(
    i_id            integer         not null    primary key     default nextval('trtahunangg_id_seq'),
    c_tahun_angg    varchar(4)      not null    unique,
    c_aktif         boolean         not null                    default false
);
comment on table trtahunangg                is 'Tabel referensi tahun anggaran';
comment on column trtahunangg.i_id          is 'id';
comment on column trtahunangg.c_tahun_angg  is 'tahun anggaran';
comment on column trtahunangg.c_aktif       is 'Kondisi aktif';

create sequence trsiappengguna_id_seq;
create table trsiappengguna(
    i_id            integer         not null    primary key     default nextval('trsiappengguna_id_seq'),
    i_peg_nrk       varchar(10)     not null    unique,
    i_sandi         varchar(64)     not null,
    n_email         varchar(100),
    n_hpno          varchar(20),
    i_peg_nip       varchar(18),
    n_peg           varchar(50),
    n_peg_jabatan   varchar(50),
    d_sandi_expired timestamp,
    c_lock          boolean         not null                    default false,
    c_aktif         boolean         not null                    default true,
    i_pgun_rekam    integer,
    d_pgun_rekam    timestamp       not null             		default now(),
    i_pgun_ubah     integer,
    d_pgun_ubah     timestamp       not null             		default now()
);
comment on table trsiappengguna                     is 'Tabel referensi pengguna';
comment on column trsiappengguna.i_id               is 'id';
comment on column trsiappengguna.i_peg_nrk          is 'NRK untuk usernam login';
comment on column trsiappengguna.i_sandi            is 'password login';
comment on column trsiappengguna.n_email            is 'email';
comment on column trsiappengguna.n_hpno             is 'no hp';
comment on column trsiappengguna.i_peg_nip          is 'nip pengguna';
comment on column trsiappengguna.n_peg              is 'nama pengguna';
comment on column trsiappengguna.n_peg_jabatan      is 'jabatan pengguna';
comment on column trsiappengguna.d_sandi_expired    is 'Tanggal sandi kadaluarsa';
comment on column trsiappengguna.c_lock             is 'Kondisi lock';
comment on column trsiappengguna.c_aktif            is 'Kondisi Aktif';
comment on column trsiappengguna.i_pgun_rekam       is 'id Pengguna yang menyimpan';
comment on column trsiappengguna.d_pgun_rekam       is 'Tanggal Simpan';
comment on column trsiappengguna.i_pgun_ubah        is 'id Pengguna yang mengubah';
comment on column trsiappengguna.d_pgun_ubah        is 'Tanggal Ubah';

create sequence trsiapotoritas_id_seq;
create table trsiapotoritas(
	i_id            integer         not null    primary key     default nextval('trsiapotoritas_id_seq'),
	c_otoritas      varchar(15)     not null    unique,
	n_otoritas      varchar(255)    not null,
	e_otoritas      varchar(400),
    c_aktif         boolean         not null                    default true,
    i_pgun_rekam    integer,
    d_pgun_rekam    timestamp       not null             		default now(),
    i_pgun_ubah     integer,
    d_pgun_ubah     timestamp       not null             		default now()
);
comment on table trsiapotoritas                 is 'Tabel referensi otoritas';
comment on column trsiapotoritas.i_id           is 'id';
comment on column trsiapotoritas.c_otoritas     is 'Kode Otoritas';
comment on column trsiapotoritas.n_otoritas     is 'Nama Otoritas';
comment on column trsiapotoritas.e_otoritas     is 'Keterangan Otoritas';
comment on column trsiapotoritas.c_aktif        is 'Kondisi Aktif';
comment on column trsiapotoritas.i_pgun_rekam   is 'id Pengguna yang menyimpan';
comment on column trsiapotoritas.d_pgun_rekam   is 'Tanggal Simpan';
comment on column trsiapotoritas.i_pgun_ubah    is 'id Pengguna yang mengubah';
comment on column trsiapotoritas.d_pgun_ubah    is 'Tanggal Ubah';

create sequence trsiappenggunaotoritas_id_seq;
create table trsiappenggunaotoritas(
	i_id            integer         not null    primary key     default nextval('trsiappenggunaotoritas_id_seq'),
	i_idpengguna    integer         not null,
	i_idotoritas    integer         not null,
    i_pgun_rekam    integer,
    d_pgun_rekam    timestamp       not null             		default now(),
    constraint fk_trsiappengguna foreign key(i_idpengguna) references trsiappengguna(i_id) on delete cascade,
    constraint fk_trsiapotoritas foreign key(i_idotoritas) references trsiapotoritas(i_id) on delete cascade
);
alter table trsiappenggunaotoritas add constraint uq_otoritaspengguna unique (i_idpengguna, i_idotoritas);
comment on table trsiappenggunaotoritas                 is 'Tabel referensi : pengguna -> otoritas';
comment on column trsiappenggunaotoritas.i_id           is 'id';
comment on column trsiappenggunaotoritas.i_idpengguna   is 'id tabel trsiappengguna';
comment on column trsiappenggunaotoritas.i_idotoritas   is 'id tabel trsiapotoritas';
comment on column trsiappenggunaotoritas.i_pgun_rekam   is 'id Pengguna yang menyimpan';
comment on column trsiappenggunaotoritas.d_pgun_rekam   is 'Tanggal Simpan';

create sequence trsiapaplikasi_id_seq;
create table trsiapaplikasi (
	i_id            integer         not null    primary key     default nextval('trsiapaplikasi_id_seq'),
	no_urut         integer,
	c_aplikasi      varchar(15)     not null    unique,
	n_aplikasi      varchar(255)    not null,
	e_aplikasi      varchar(400),
	n_link          varchar(255)    not null,
    c_aktif         boolean         not null                    default true,
    i_pgun_rekam    integer,
    d_pgun_rekam    timestamp       not null             		default now(),
    i_pgun_ubah     integer,
    d_pgun_ubah     timestamp       not null             		default now()
);
comment on table trsiapaplikasi                 is 'Tabel referensi aplikasi';
comment on column trsiapaplikasi.i_id           is 'id';
comment on column trsiapaplikasi.c_aplikasi     is 'Kode Aplikasi';
comment on column trsiapaplikasi.n_aplikasi     is 'Nama Aplikasi';
comment on column trsiapaplikasi.e_aplikasi     is 'Keterangan Aplikasi';
comment on column trsiapaplikasi.n_link         is 'Link Aplikasi';
comment on column trsiapaplikasi.c_aktif        is 'Kondisi Aktif';
comment on column trsiapaplikasi.i_pgun_rekam   is 'ID Pengguna yang menyimpan';
comment on column trsiapaplikasi.d_pgun_rekam   is 'Tanggal Simpan';
comment on column trsiapaplikasi.i_pgun_ubah    is 'ID Pengguna yang mengubah';
comment on column trsiapaplikasi.d_pgun_ubah    is 'Tanggal Ubah';

create sequence trsiapotoritasaplikasi_id_seq;
create table trsiapotoritasaplikasi (
	i_id            integer         not null    primary key     default nextval('trsiapotoritasaplikasi_id_seq'),
	i_idotoritas    integer         not null,
	i_idaplikasi    integer         not null,
    i_pgun_rekam    integer,
    d_pgun_rekam    timestamp       not null             		default now(),
    constraint fk_trsiapotoritas foreign key(i_idotoritas) references trsiapotoritas(i_id) on delete cascade,
    constraint fk_trsiapaplikasi foreign key(i_idaplikasi) references trsiapaplikasi(i_id) on delete cascade
);
alter table trsiapotoritasaplikasi add constraint uq_otoritasaplikasi unique (i_idotoritas, i_idaplikasi);
comment on table trsiapotoritasaplikasi                 is 'Tabel referensi : otoritas -> aplikasi';
comment on column trsiapotoritasaplikasi.i_id           is 'id';
comment on column trsiapotoritasaplikasi.i_idotoritas   is 'id tabel trsiapotoritas';
comment on column trsiapotoritasaplikasi.i_idaplikasi   is 'id tabel trsiapaplikasi';
comment on column trsiapotoritasaplikasi.i_pgun_rekam   is 'id Pengguna yang menyimpan';
comment on column trsiapotoritasaplikasi.d_pgun_rekam   is 'Tanggal Simpan';

create sequence trsiapmenu_id_seq;
create table trsiapmenu (
	i_id            integer         not null    primary key     default nextval('trsiapmenu_id_seq'),
	i_idaplikasi    integer         not null,
	i_idinduk       integer,
	no_urut         integer,
	c_menu          varchar(25)     not null    unique,
	n_menu          varchar(255)    not null,
	e_menu          varchar(400),
	n_link          varchar(255)    not null,
	icon            varchar(255)    not null,
    c_aktif         boolean         not null                    default true,
    i_pgun_rekam    integer,
    d_pgun_rekam    timestamp       not null             		default now(),
    i_pgun_ubah     integer,
    d_pgun_ubah     timestamp       not null             		default now(),
    constraint fk_trsiapaplikasi foreign key(i_idaplikasi) references trsiapaplikasi(i_id) on delete cascade
);
comment on table trsiapmenu                     is 'Tabel referensi menu ';
comment on column trsiapmenu.i_id               is 'id';
comment on column trsiapmenu.i_idaplikasi       is 'id tabel traplikasi';
comment on column trsiapmenu.i_idinduk          is 'id menu induk (null = menu, not null = submenu)';
comment on column trsiapmenu.no_urut            is 'no urut menu';
comment on column trsiapmenu.c_menu             is 'Kode Menu';
comment on column trsiapmenu.n_menu             is 'Nama Menu';
comment on column trsiapmenu.e_menu             is 'Keterangan Menu';
comment on column trsiapmenu.n_link             is 'Link Menu';
comment on column trsiapmenu.icon               is 'icon menu (ex: file-text)';
comment on column trsiapmenu.c_aktif            is 'Kondisi Aktif';
comment on column trsiapmenu.i_pgun_rekam       is 'id Pengguna yang menyimpan';
comment on column trsiapmenu.d_pgun_rekam       is 'Tanggal Simpan';
comment on column trsiapmenu.i_pgun_ubah        is 'id Pengguna yang mengubah';
comment on column trsiapmenu.d_pgun_ubah        is 'Tanggal Ubah';

create sequence trsiapotoritasmenu_id_seq;
create table trsiapotoritasmenu (
	i_id            integer         not null    primary key     default nextval('trsiapmenu_id_seq'),
	i_idotoritas    integer         not null,
	i_idmenu        integer         not null,
    i_pgun_rekam    integer,
    d_pgun_rekam    timestamp       not null             		default now(),
    constraint fk_trsiapotoritas foreign key(i_idotoritas) references trsiapotoritas(i_id) on delete cascade,
    constraint fk_trsiapmenu foreign key(i_idmenu) references trsiapmenu(i_id) on delete cascade
);
alter table trsiapotoritasmenu add constraint uq_otoritasmenu unique (i_idotoritas, i_idmenu);
comment on table trsiapotoritasmenu                     is 'Tabel referensi : otoritas -> menu';
comment on column trsiapotoritasmenu.i_id               is 'id';
comment on column trsiapotoritasmenu.i_idmenu           is 'id tabel trsiapmenu';
comment on column trsiapotoritasmenu.i_idotoritas       is 'id tabel trsiapaplikasi';
comment on column trsiapotoritasmenu.i_pgun_rekam       is 'id Pengguna yang menyimpan';
comment on column trsiapotoritasmenu.d_pgun_rekam       is 'Tanggal Simpan';