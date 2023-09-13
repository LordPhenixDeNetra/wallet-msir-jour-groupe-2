package com.wallet.wallet_msir_jour_groupe2.rest;

import com.wallet.wallet_msir_jour_groupe2.model.CompteDTO;
import com.wallet.wallet_msir_jour_groupe2.service.CompteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/comptes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompteResource {

    private final CompteService compteService;

    public CompteResource(final CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    public ResponseEntity<List<CompteDTO>> getAllComptes() {
        return ResponseEntity.ok(compteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteDTO> getCompte(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(compteService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCompte(@RequestBody @Valid final CompteDTO compteDTO) {
        final Long createdId = compteService.create(compteDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCompte(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final CompteDTO compteDTO) {
        compteService.update(id, compteDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCompte(@PathVariable(name = "id") final Long id) {
        compteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
