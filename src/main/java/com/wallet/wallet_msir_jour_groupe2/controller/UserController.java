package com.wallet.wallet_msir_jour_groupe2.controller;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.model.TypeUser;
import com.wallet.wallet_msir_jour_groupe2.model.UserDTO;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.service.UserService;
import com.wallet.wallet_msir_jour_groupe2.util.CustomCollectors;
import com.wallet.wallet_msir_jour_groupe2.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CompteRepository compteRepository;

    public UserController(final UserService userService, final CompteRepository compteRepository) {
        this.userService = userService;
        this.compteRepository = compteRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("typeUserValues", TypeUser.values());
        model.addAttribute("compteValues", compteRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Compte::getId, Compte::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("user") final UserDTO userDTO) {
        return "user/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("user") @Valid final UserDTO userDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("compte") && userDTO.getCompte() != null && userService.compteExists(userDTO.getCompte())) {
            bindingResult.rejectValue("compte", "Exists.user.compte");
        }
        if (bindingResult.hasErrors()) {
            return "user/add";
        }
        userService.create(userDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("user.create.success"));
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("user", userService.get(id));
        return "user/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("user") @Valid final UserDTO userDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        final UserDTO currentUserDTO = userService.get(id);
        if (!bindingResult.hasFieldErrors("compte") && userDTO.getCompte() != null &&
                !userDTO.getCompte().equals(currentUserDTO.getCompte()) &&
                userService.compteExists(userDTO.getCompte())) {
            bindingResult.rejectValue("compte", "Exists.user.compte");
        }
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        userService.update(id, userDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("user.update.success"));
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = userService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            userService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("user.delete.success"));
        }
        return "redirect:/users";
    }

}
