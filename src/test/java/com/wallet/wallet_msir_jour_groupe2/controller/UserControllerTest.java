package com.wallet.wallet_msir_jour_groupe2.controller;
import com.wallet.wallet_msir_jour_groupe2.model.UserDTO;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {



    @Mock
    private UserService userService;

    @Mock
    private CompteRepository compteRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void prepareContext() {
        Model model = mock(Model.class);

        userController.prepareContext(model);

        // Verify that the model attributes "typeUserValues" and "compteValues" are set
        verify(model, times(1)).addAttribute(eq("typeUserValues"), any());
        verify(model, times(1)).addAttribute(eq("compteValues"), any());
    }

    @Test
    void list() {
        /*Model model = mock(Model.class);

        String viewName = userController.list(model);

        // Verify that the "users" attribute is set and the view name is "user/list"
        verify(model, times(1)).addAttribute(eq("users"), any());
        assertEquals("user/list", viewName);*/
        Model model= Mockito.mock(Model.class);
        List<UserDTO> simulateUserControllerList=new ArrayList<>();
        when(userService.findAll()).thenReturn(simulateUserControllerList);
        String view_page=userController.list(model);
        assertEquals("user/list",view_page);

    }

    @Test
    void add() {
        UserDTO userDTO =Mockito.mock(UserDTO.class);
        String pageName = userController.add(userDTO);
        assertEquals("user/add", pageName);
    }


    @Test
    void testAdd() {
        UserDTO userDTO = Mockito.mock(UserDTO.class);
        BindingResult bindingResult = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(bindingResult.hasFieldErrors("compte")).thenReturn(false);
        when(userDTO.getCompte()).thenReturn(null);
        when(userService.compteExists(null)).thenReturn(false);
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = userController.add(userDTO, bindingResult, redirectAttributes);

        // Verify that create method is called and view name is "redirect:/users"
        verify(userService, times(1)).create(userDTO);
        assertEquals("redirect:/users", viewName);
    }



    @Test
    void edit() {
        Long id = 1L;
        Model model = mock(Model.class);
        String viewName = userController.edit(id, model);
        verify(model, times(1)).addAttribute(eq("user"), any());
        assertEquals("user/edit", viewName);
    }


    @Test
    void testEdit() {
        Long id = 1L;
        UserDTO userDTO =Mockito.mock(UserDTO.class);
        BindingResult bindingResult = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(bindingResult.hasFieldErrors("compte")).thenReturn(false);
        when(userDTO.getCompte()).thenReturn(null);
        when(userService.compteExists(null)).thenReturn(false);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.get(id)).thenReturn(userDTO);

        String viewName = userController.edit(id, userDTO, bindingResult, redirectAttributes);

        // Verify that update method is called and view name is "redirect:/users"
        verify(userService, times(1)).update(id, userDTO);
        assertEquals("redirect:/users", viewName);
    }



    @Test
    void delete() {
        Long id = 1L;
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(userService.getReferencedWarning(id)).thenReturn(null);

        String viewName = userController.delete(id, redirectAttributes);

        // Verify that delete method is called and view name is "redirect:/users"
        verify(userService, times(1)).delete(id);
        assertEquals("redirect:/users", viewName);
    }


}