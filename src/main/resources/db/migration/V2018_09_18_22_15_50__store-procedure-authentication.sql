CREATE OR REPLACE FUNCTION auth.authentication(uname character varying(100))
    RETURNS TABLE
            (
                username character varying(100),
                password character varying(255),
                enabled  boolean
            )
AS
$$
DECLARE
    data record;
BEGIN
    FOR data IN (select u.i_peg_nrk as uid, u.i_sandi as passwd, u.c_aktif as is_enabled
                  from trsiappengguna u
                  where u.i_peg_nrk = uname
                     and c_lock = false)
        LOOP
            username := data.uid;
            password := data.passwd;
            enabled := data.is_enabled;
            RETURN NEXT;
        END LOOP;
END;
$$
    LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION auth.authorization(uname character varying(100))
    RETURNS TABLE
            (
                username  character varying(100),
                authority character varying(100)
            )
AS
$$
DECLARE
    data record;
BEGIN
    FOR data IN (select distinct
                     pgun.i_peg_nrk  as user_id,
                     otor.c_otoritas as role_name
                  from trsiappengguna pgun
                     left join trsiappenggunaotoritas pgunotor on pgun.i_id = pgunotor.i_idpengguna
                     left join trsiapotoritas otor on pgunotor.i_idotoritas = otor.i_id
                  where otor.c_aktif = true and pgun.i_peg_nrk = uname)
        LOOP
            username := uname;
            authority := data.role_name;
            RETURN NEXT;
        END LOOP;
END;
$$
    LANGUAGE 'plpgsql';