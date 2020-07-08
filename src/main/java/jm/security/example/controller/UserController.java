package jm.security.example.controller;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import jm.security.example.service.RoleService;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("userList", userService.getAllUser());
        model.addAttribute("roleList", roleService.getAllRole());
        return "readUser";
    }

    @GetMapping("/user")
    public String infoUserPage() {
        return "infoUser";
    }

    @GetMapping("/admin/updateUser")
    public String updateUserPage(ModelMap model, Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roleList", roleService.getAllRole());
        return "updateUser";
    }

    @PostMapping("/admin/updateUser")
    public String updateUser(User user, String[] posRoles) {
        userService.updateUser(findUserRole(user, posRoles));
        return "redirect:/admin";
    }

    @GetMapping("/admin/addUser")
    public String addUserPage(ModelMap model) {
        model.addAttribute("roleList", roleService.getAllRole());
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(User user, String[] posRoles) {
        userService.addUser(findUserRole(user, posRoles));
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteUser")
    public String deleteUser(Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    private User findUserRole(User user, String[] posRoles) {
        Set<Role> userRoles = new HashSet<>();
        for (String role: posRoles) {
            userRoles.add(roleService.getRoleByName(role));
        }
        user.setRoles(userRoles);
        return user;
    }
}
