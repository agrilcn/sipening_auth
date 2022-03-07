package com.tabeldata.sipening.auth.usermanage.service;

import com.tabeldata.sipening.auth.usermanage.dao.PublicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PublicService {

    @Autowired
    private PublicDao dao;

    public List<String> getTahunAnggaran() {
        List<String> listTahunAnggaran = dao.getTahunAnggaran();
        return listTahunAnggaran;
    }
}
