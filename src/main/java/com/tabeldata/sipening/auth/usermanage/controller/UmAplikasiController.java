package com.tabeldata.sipening.auth.usermanage.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.tabeldata.sipening.auth.usermanage.dto.UmAplikasiDto;
import com.tabeldata.sipening.auth.usermanage.error.NotAllowedException;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import com.tabeldata.sipening.auth.usermanage.service.PenggunaService;
import com.tabeldata.sipening.auth.usermanage.service.UmAplikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/um/aplikasi")
public class UmAplikasiController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private UmAplikasiService service;

    private final static String KODE_MENU = "UM-APLI";

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody UmAplikasiDto.Kelola value, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        service.save(value, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/set-aktif")
    public ResponseEntity<?> setAktif(@Valid @RequestBody UmAplikasiDto.SetAktif value, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        service.setAktif(value, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UmAplikasiDto.Kelola> findById(@PathVariable Integer id, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/datatables")
    public ResponseEntity<DataTablesResponse<UmAplikasiDto.DtRes>> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0][dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) UmAplikasiDto.DtReq param,
            Principal principal
    ) throws NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        if(param == null) param = new UmAplikasiDto.DtReq();
        DataTablesResponse<UmAplikasiDto.DtRes> data = service.datatables(new DataTablesRequest<>(draw, length, start, sSortDir0, iSortCol0, param));
        return ResponseEntity.ok(data);
    }
}
