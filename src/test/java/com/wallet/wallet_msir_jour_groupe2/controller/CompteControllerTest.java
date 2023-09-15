package com.wallet.wallet_msir_jour_groupe2.controller;

import com.wallet.wallet_msir_jour_groupe2.model.CompteDTO;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.service.CompteService;
import com.wallet.wallet_msir_jour_groupe2.util.WebUtils;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompteControllerTest {
    /*
    @Mock
    private CompteService compteService;

    @InjectMocks
    private CompteController compteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void prepareContext() {
        Model model = mock(Model.class);

        compteController.prepareContext(model);

        // Verify that the model attribute "statutCompteValues" is set
        verify(model, times(1)).addAttribute(eq("statutCompteValues"), any());
    }
    */

    /*
    @Test
    void list() {
        Model model = mock(Model.class);

        String viewName = compteController.list(model);

        // Verify that the "comptes" attribute is set and the view name is "compte/list"
        verify(model, times(1)).addAttribute(eq("comptes"), any());
        assertEquals("compte/list", viewName);
    }

    */

    /*
    @Test
    void add() {
        CompteDTO compteDTO = new CompteDTO();

        String viewName = compteController.add(compteDTO);

        // Verify that the view name is "compte/add"
        assertEquals("compte/add", viewName);
    }

    *

    /*
    @Test
    void testAdd() {
        CompteDTO compteDTO = new CompteDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(compteService.create(compteDTO)).thenReturn(1L);

        String viewName = compteController.add(compteDTO, bindingResult, redirectAttributes);

        // Verify that create method is called and view name is "redirect:/comptes"
        verify(compteService, times(1)).create(compteDTO);
        assertEquals("redirect:/comptes", viewName);
    }
    */

    /*
    @Test
    void edit() {
        Long id = 1L;
        Model model = mock(Model.class);

        String viewName = compteController.edit(id, model);

        // Verify that the "compte" attribute is set and the view name is "compte/edit"
        verify(model, times(1)).addAttribute(eq("compte"), any());
        assertEquals("compte/edit", viewName);
    }
    */
    /*
    @Test
    void testEdit() {
        Long id = 1L;
        CompteDTO compteDTO = new CompteDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = compteController.edit(id, compteDTO, bindingResult, redirectAttributes);

        // Verify that update method is called and view name is "redirect:/comptes"
        verify(compteService, times(1)).update(id, compteDTO);
        assertEquals("redirect:/comptes", viewName);
    }
    */
    /*
    @Test
    void delete() {
        Long id = 1L;
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(compteService.getReferencedWarning(id)).thenReturn(null);

        String viewName = compteController.delete(id,redirectAttributes);

        // Verify that delete method is called and view name is "redirect:/comptes"
        verify(compteService, times(1)).delete(id);
        assertEquals("redirect:/comptes", viewName);
    }
    */
}
