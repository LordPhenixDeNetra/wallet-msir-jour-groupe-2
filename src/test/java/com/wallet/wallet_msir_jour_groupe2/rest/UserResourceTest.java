package com.wallet.wallet_msir_jour_groupe2.rest;

import com.wallet.wallet_msir_jour_groupe2.model.UserDTO;
import com.wallet.wallet_msir_jour_groupe2.service.UserService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserResourceTest {
    /*
    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO());
        users.add(new UserDTO());

        when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<UserDTO>> responseEntity = userResource.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
    }

    @Test
    void getUser() {
        Long id = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);

        when(userService.get(id)).thenReturn(userDTO);

        ResponseEntity<UserDTO> responseEntity = userResource.getUser(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());
    }

    @Test
    void createUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNomUser("john_doe");

        when(userService.create(userDTO)).thenReturn(1L);

        ResponseEntity<Long> responseEntity = userResource.createUser(userDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(1L, responseEntity.getBody());
    }

    @Test
    void createUser_ValidationException() {
        UserDTO userDTO = new UserDTO();
        // Invalid data, should throw ValidationException

        when(userService.create(userDTO)).thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> userResource.createUser(userDTO));
    }

    @Test
    void updateUser() {
        Long id = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setNomUser("updated_username");

        ResponseEntity<Long> responseEntity = userResource.updateUser(id, userDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(id, responseEntity.getBody());
    }

    @Test
    void deleteUser() {
        Long id = 1L;

        ResponseEntity<Void> responseEntity = userResource.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(userService, times(1)).delete(id);
    }


     */
}
