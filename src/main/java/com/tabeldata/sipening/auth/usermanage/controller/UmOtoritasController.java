package com.tabeldata.sipening.auth.usermanage.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasDto;
import com.tabeldata.sipening.auth.usermanage.error.NotAllowedException;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import com.tabeldata.sipening.auth.usermanage.service.PenggunaService;
import com.tabeldata.sipening.auth.usermanage.service.UmOtoritasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/um/otoritas")
public class UmOtoritasController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private UmOtoritasService service;

    private final static String KODE_MENU = "UM-OTOR";

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody UmOtoritasDto.Kelola value, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        service.save(value, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/set-aktif")
    public ResponseEntity<?> setAktif(@Valid @RequestBody UmOtoritasDto.SetAktif value, Principal principal) throws NotFoundException, NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        service.setAktif(value, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UmOtoritasDto.Kelola> findById(@PathVariable Integer id, Principal principal) throws NotFoundException, NotAllowedException {
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
    public ResponseEntity<DataTablesResponse<UmOtoritasDto.DtRes>> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0][dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) UmOtoritasDto.DtReq param,
            Principal principal
    ) throws NotAllowedException {
        penggunaService.isAllowed(KODE_MENU, principal.getName());
        if(param == null) param = new UmOtoritasDto.DtReq();
        DataTablesResponse<UmOtoritasDto.DtRes> data = service.datatables(new DataTablesRequest<>(draw, length, start, sSortDir0, iSortCol0, param));
        return ResponseEntity.ok(data);
    }
}
