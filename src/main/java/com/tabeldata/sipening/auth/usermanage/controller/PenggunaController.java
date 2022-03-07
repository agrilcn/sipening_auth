package com.tabeldata.sipening.auth.usermanage.controller;

import com.tabeldata.sipening.auth.usermanage.dto.PenggunaDto;
import com.tabeldata.sipening.auth.usermanage.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/pengguna")
public class PenggunaController {

    @Autowired
    private PenggunaService service;

    @GetMapping("/find-info")
    public ResponseEntity<PenggunaDto.Info> findInfo(Principal principal) {
        return ResponseEntity.ok(service.findByNrk(principal.getName()));
    }

    @GetMapping("/list-otoritas")
    public ResponseEntity<List<String>> listOtoritas(Principal principal) {
        return ResponseEntity.ok(service.listOtoritas(principal.getName()));
    }

    @GetMapping("/list-aplikasi")
    public ResponseEntity<List<PenggunaDto.Aplikasi>> listAplikasi(Principal principal) {
        return ResponseEntity.ok(service.listAplikasi(principal.getName()));
    }

    @GetMapping("/list-aplikasi-with-menu")
    public ResponseEntity<List<PenggunaDto.AplikasiWithMenu>> listAplikasiWithMenu(Principal principal) {
        return ResponseEntity.ok(service.listMenu(principal.getName()));
    }

    @GetMapping("/list-menu-by-id-aplikasi/{id}")
    public ResponseEntity<List<PenggunaDto.Menu>> listAplikasiWithMenu(@PathVariable Integer id, Principal principal) {
        return ResponseEntity.ok(service.listMenuByAplikasi(principal.getName(), id));
    }
}
