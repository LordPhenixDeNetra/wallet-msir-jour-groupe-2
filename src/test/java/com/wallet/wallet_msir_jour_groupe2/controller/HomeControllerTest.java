package com.wallet.wallet_msir_jour_groupe2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @Test
    void index() {
        HomeController homeController = new HomeController();

        String viewName = homeController.index();

        // Verify that the view name is "home/index"
        assertEquals("home/index", viewName);
    }
}