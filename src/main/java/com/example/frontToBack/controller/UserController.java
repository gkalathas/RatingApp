package com.example.frontToBack.controller;

//@Controller
public class UserController {

//    UserService userService;
//
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    //@GetMapping("/signup")
//    public String showSignUpForm(User user) {
//        return "add-user";
//    }
//
//    //@PostMapping("/adduser")
//    public String AddUser(User user, BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()){
//            return "add-user";
//        }
//
//        userService.save(user);
//        return "redirect:/index";
//
//    }
//
//    //@GetMapping("/index")
//    public String showUserList(Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }
//
//    //@GetMapping("/edit/{id}")
//    public String ShowUpdateForm(@PathVariable("id") Long id, Model model) {
//        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id: " +id));
//
//        model.addAttribute("user", user);
//        return "update-user";
//    }
//
//    //@PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") Long id, User user, BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()) {
//            user.setId(id);
//            return "update-user";
//        }
//
//        userService.save(user);
//        return "redirect:/index";
//    }
//
//    //@GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id, Model model) {
//        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id: " +id));
//
//        userService.delete(user);
//        return "redirect:/index";
//    }


}
