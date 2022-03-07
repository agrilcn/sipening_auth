package com.tabeldata.sipening.auth.usermanage.controller;

import com.tabeldata.sipening.auth.usermanage.dto.UmPenggunaOtoritasDto;
import com.tabeldata.sipening.auth.usermanage.error.NotAllowedException;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import com.tabeldata.sipening.auth.usermanage.service.PenggunaService;
import com.tabeldata.sipening.auth.usermanage.service.UmPenggunaOtoritasService;
import com.tabeldata.sipening.auth.usermanage.service.UmPenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/um/pengguna-kelola")
public class UmPenggunaKelolaController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private UmPenggunaService umPenggunaService;

    @Autowired
    private UmPenggunaOtoritasService umPenggunaOtoritasService;

    private final static String KODE_MENU = "UM-PGUN";

    @PostMapping("/otoritas/save")
    public ResponseEntity<?> penggunaOtoritasSave(@Valid @RequestBody UmPenggunaOtoritasDto.Kelola value, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        umPenggunaService.findById(value.getIdPengguna());
        umPenggunaOtoritasService.save(value, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/otoritas/list-by-id-pengguna/{idPengguna}")
    public ResponseEntity<UmPenggunaOtoritasDto.ListOtor> penggunaOtoritasFindById(@PathVariable Integer idPengguna, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        umPenggunaService.findById(idPengguna);
        return ResponseEntity.ok(umPenggunaOtoritasService.listOtor(idPengguna));
    }

    @PostMapping("/sekolah/save")
    public ResponseEntity<?> penggunaSekolahSave() {
        return null;
    }

    @GetMapping("/sekolah/list-by-id-pengguna/{idPengguna}")
    public ResponseEntity<?> penggunaSekolahFindById(@PathVariable Integer idPengguna) {
        return null;
    }


    @PostMapping("/opd/save")
    public ResponseEntity<?> penggunaOpdSave() {
        return null;
    }

    @GetMapping("/opd/list-by-id-pengguna/{idPengguna}")
    public ResponseEntity<?> penggunaOpdFindById(@PathVariable Integer idPengguna) {
        return null;
    }
}
