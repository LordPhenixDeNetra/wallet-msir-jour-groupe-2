package com.wallet.wallet_msir_jour_groupe2.controller;

import com.wallet.wallet_msir_jour_groupe2.model.CompteDTO;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutCompte;
import com.wallet.wallet_msir_jour_groupe2.service.CompteService;
import com.wallet.wallet_msir_jour_groupe2.util.WebUtils;
import jakarta.validation.Valid;
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
@RequestMapping("/comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(final CompteService compteService) {
        this.compteService = compteService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("statutCompteValues", TypeStatutCompte.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("comptes", compteService.findAll());
        return "compte/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("compte") final CompteDTO compteDTO) {
        return "compte/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("compte") @Valid final CompteDTO compteDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "compte/add";
        }
        compteService.create(compteDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("compte.create.success"));
        return "redirect:/comptes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("compte", compteService.get(id));
        return "compte/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("compte") @Valid final CompteDTO compteDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "compte/edit";
        }
        compteService.update(id, compteDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("compte.update.success"));
        return "redirect:/comptes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = compteService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            compteService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("compte.delete.success"));
        }
        return "redirect:/comptes";
    }

}
