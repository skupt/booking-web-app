package org.example.booking.mvc.controllers;

import org.example.booking.core.model.UserImpl;
import org.example.booking.intro.facade.BookingFacade;
import org.example.booking.intro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("users")
public class UsersController {
    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("getUserByIdForm")
    public String getUsersByIdForm() {
        return "users/getUserByIdForm.html";
    }

    @GetMapping(value = "/", params = "id")
    public String getUserById(@RequestParam("id") long id, Model model) {
        User user = bookingFacade.getUserById(id);
        model.addAttribute("user", user);

        return "users/getUser.html";
    }

    @GetMapping("getUserByEmailForm")
    public String getUserByEmailForm() {
        return "users/getUserByEmailForm.html";
    }

    @GetMapping(value = "/", params = "email")
    public String getUserByEmail(@RequestParam("email") String email, Model model) {
        User user = bookingFacade.getUserByEmail(email);
        model.addAttribute("user", user);
        return "users/getUser.html";
    }

    @GetMapping("getUsersByNameForm")
    public String getUsersByNameForm() {
        return "users/getUsersByNameForm.html";
    }

    @GetMapping(value = "/", params = {"name", "pageSize", "pageNumber"})
    public String getUsersByName(@RequestParam("name") String name, @RequestParam("pageSize") int paggeSize,
                                 @RequestParam("pageNumber") int pageNumber, Model model) {
        List<User> userList = bookingFacade.getUsersByName(name, paggeSize, pageNumber);
        model.addAttribute("users", userList);

        return "users/getUsers.html";
    }

    @GetMapping("createUserForm")
    public String createUserForm() {
        return "users/createUserForm.html";
    }

    @PostMapping(value = "/", params = {"name", "email"})
    public String createUser(@RequestParam("name") String name, @RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        User user = new UserImpl();
        user.setName(name);
        user.setEmail(email);
        User created = bookingFacade.createUser(user);
        redirectAttributes.addFlashAttribute("msg", created != null ? "User was created." : "User was not created.");

        return "redirect:/success";
    }

    @GetMapping("updateUserForm")
    public String updateUserForm() {
        return "users/updateUserForm.html";
    }

    @PutMapping(value = "/", params = {"id", "name", "email"})
    public String updateUser(@RequestParam("id") long id, @RequestParam("name") String name,
                             @RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        User user = new UserImpl();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        User updated = bookingFacade.updateUser(user);
        redirectAttributes.addFlashAttribute("msg", updated != null ? "User was updated" : "User was not updated");

        return "redirect:/success";
    }

    @GetMapping("deleteUserForm")
    public String deleteUserForm() {
        return "users/deleteUserForm.html";
    }

    @DeleteMapping("/")
    public String deleteUser(@RequestParam("id") long id, RedirectAttributes redirectAttributes) {
        boolean result = bookingFacade.deleteUser(id);
        redirectAttributes.addFlashAttribute("msg", result ? "User was deleted" : "User was not deleted");

        return "redirect:/success";
    }


}
