package com.tabeldata.sipening.auth.usermanage.controller;

import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasAplikasiDto;
import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasMenuDto;
import com.tabeldata.sipening.auth.usermanage.error.NotAllowedException;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import com.tabeldata.sipening.auth.usermanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/um/otoritas-kelola")
public class UmOtoritasKelolaController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private UmOtoritasService umOtoritasService;

    @Autowired
    private UmAplikasiService aplikasiService;

    @Autowired
    private UmOtoritasAplikasiService umOtoritasAplikasiService;

    @Autowired
    private UmOtoritasMenuService umOtoritasMenuService;

    private final static String KODE_MENU = "UM-OTOR";

    @PostMapping("/aplikasi/save")
    public ResponseEntity<?> otoritasAplikasiSave(@Valid @RequestBody UmOtoritasAplikasiDto.Kelola value, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        umOtoritasService.findById(value.getIdOtoritas());
        umOtoritasAplikasiService.save(value, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/aplikasi/list-by-id-otoritas/{idOtoritas}")
    public ResponseEntity<UmOtoritasAplikasiDto.ListApp> otoritasAplikasiFindById(@PathVariable Integer idOtoritas, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        umOtoritasService.findById(idOtoritas);
        return ResponseEntity.ok(umOtoritasAplikasiService.listApp(idOtoritas));
    }

    @PostMapping("/menu/save")
    public ResponseEntity<?> otoritasMenuSave(@Valid @RequestBody UmOtoritasMenuDto.Kelola value, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        umOtoritasService.findById(value.getIdOtoritas());
        aplikasiService.findById(value.getIdAplikasi());
        umOtoritasMenuService.save(value, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/menu/list/{idOtoritas}/{idAplikasi}")
    public ResponseEntity<UmOtoritasMenuDto.ListMenu> otoritasMenuFindById(
            @PathVariable Integer idOtoritas,
            @PathVariable Integer idAplikasi,
            Principal principal
    ) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        umOtoritasService.findById(idOtoritas);
        aplikasiService.findById(idAplikasi);
        return ResponseEntity.ok(umOtoritasMenuService.listMenu(idOtoritas, idAplikasi));
    }
}
