package com.wallet.wallet_msir_jour_groupe2.controller;

import com.wallet.wallet_msir_jour_groupe2.domain.Compte;
import com.wallet.wallet_msir_jour_groupe2.domain.User;
import com.wallet.wallet_msir_jour_groupe2.model.TransactionDTO;
import com.wallet.wallet_msir_jour_groupe2.model.TypeStatutTransaction;
import com.wallet.wallet_msir_jour_groupe2.model.TypeTransaction;
import com.wallet.wallet_msir_jour_groupe2.repos.CompteRepository;
import com.wallet.wallet_msir_jour_groupe2.repos.UserRepository;
import com.wallet.wallet_msir_jour_groupe2.service.TransactionService;
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
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserRepository userRepository;
    private final CompteRepository compteRepository;

    public TransactionController(final TransactionService transactionService,
            final UserRepository userRepository, final CompteRepository compteRepository) {
        this.transactionService = transactionService;
        this.userRepository = userRepository;
        this.compteRepository = compteRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("typeTransactionValues", TypeTransaction.values());
        model.addAttribute("statutTransactionValues", TypeStatutTransaction.values());
        model.addAttribute("userValues", userRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(User::getId, User::getNomUser)));
        model.addAttribute("compteValues", compteRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Compte::getId, Compte::getId)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        return "transaction/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("transaction") final TransactionDTO transactionDTO) {
        return "transaction/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("transaction") @Valid final TransactionDTO transactionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "transaction/add";
        }
        transactionService.create(transactionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("transaction.create.success"));
        return "redirect:/transactions";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("transaction", transactionService.get(id));
        return "transaction/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("transaction") @Valid final TransactionDTO transactionDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "transaction/edit";
        }
        transactionService.update(id, transactionDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("transaction.update.success"));
        return "redirect:/transactions";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        transactionService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("transaction.delete.success"));
        return "redirect:/transactions";
    }

}
