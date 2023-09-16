package com.wallet.wallet_msir_jour_groupe2.rest;

import com.wallet.wallet_msir_jour_groupe2.model.CompteDTO;
import com.wallet.wallet_msir_jour_groupe2.service.CompteService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CompteResourceTest {

    @Mock
    private CompteService compteService;

    @InjectMocks
    private CompteResource compteResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllComptes() {
        List<CompteDTO> comptes = new ArrayList<>();
        comptes.add(new CompteDTO());
        comptes.add(new CompteDTO());

        when(compteService.findAll()).thenReturn(comptes);

        ResponseEntity<List<CompteDTO>> responseEntity = compteResource.getAllComptes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comptes, responseEntity.getBody());
    }


    @Test
    void getCompte() {
        Long id = 1L;
        CompteDTO compteDTO = new CompteDTO();
        compteDTO.setId(id);

        when(compteService.get(id)).thenReturn(compteDTO);

        ResponseEntity<CompteDTO> responseEntity = compteResource.getCompte(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(compteDTO, responseEntity.getBody());
    }


    @Test
    void createCompte() {
        CompteDTO compteDTO = new CompteDTO();
        compteDTO.setSoldeCompte(1000.0);

        when(compteService.create(compteDTO)).thenReturn(1L);

        ResponseEntity<Long> responseEntity = compteResource.createCompte(compteDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody());
    }


    @Test
    void createCompte_ValidationException() {
        CompteDTO compteDTO = new CompteDTO();
        // Invalid data, should throw ValidationException

        when(compteService.create(compteDTO)).thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> compteResource.createCompte(compteDTO));
    }

    @Test
    void updateCompte() {
        Long id = 1L;
        CompteDTO compteDTO = new CompteDTO();
        compteDTO.setSoldeCompte(2000.0);

        ResponseEntity<Long> responseEntity = compteResource.updateCompte(id, compteDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(id, responseEntity.getBody());
    }

    @Test
    void deleteCompte() {
        Long id = 1L;

        ResponseEntity<Void> responseEntity = compteResource.deleteCompte(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(compteService, times(1)).delete(id);
    }


}
