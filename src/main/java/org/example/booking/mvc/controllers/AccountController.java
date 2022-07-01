package org.example.booking.mvc.controllers;

import org.example.booking.mvc.facade.impl.BookingWebFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("accounts")
public class AccountController {
    @Autowired
    private BookingWebFacadeImpl bookingWebAppFacade2;

    @GetMapping("checkForm")
    public String checkAccountForm() {
        return "accounts/checkAccountForm.html";
    }

    @GetMapping(value = "/", params = "id")
    public String checkAccount(@RequestParam("id") long id, Model model) {
        BigDecimal amount = bookingWebAppFacade2.checkAccount(id);
        String msg = "Account of user: " + id + " has: " + amount + " money amount";
        model.addAttribute("msg", msg);
        return "inform";
    }

    @GetMapping("refillForm")
    public String refillAccountForm() {
        return "accounts/refillAccountForm.html";
    }

    @PostMapping(value = "/", params = {"id", "amount"})
    public String refillAccount(@RequestParam("id") long id, @RequestParam("amount") BigDecimal amount,
                                RedirectAttributes redirectAttributes) {
        BigDecimal newAmount = bookingWebAppFacade2.refillAccount(id, amount);
        redirectAttributes.addFlashAttribute("msg", "Account: " + id + " refilled. Balance: " + newAmount + ".");
        return "redirect:/success";
    }
}
