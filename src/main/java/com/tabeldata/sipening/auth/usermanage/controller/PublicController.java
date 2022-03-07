package com.tabeldata.sipening.auth.usermanage.controller;

import com.tabeldata.sipening.auth.usermanage.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private PublicService service;

    @GetMapping("/tahun-anggaran")
    public ResponseEntity<List<String>> getTahunAnggaran() {
        List<String> list = service.getTahunAnggaran();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
