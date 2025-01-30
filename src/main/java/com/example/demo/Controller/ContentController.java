package com.example.demo.Controller;

import com.example.demo.Model.MyAppUser;
import com.example.demo.Model.Room;
import com.example.demo.Repository.MyAppUserRepository;

import com.example.demo.Repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class ContentController {

    private final MyAppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        List<Room> room = roomRepository.findAll();
        model.addAttribute("rooms", room);
        return "rooms"; // This corresponds to the HTML file name (rooms.html)
    }

    @GetMapping("/rooms/search")
    public String searchRooms(@RequestParam String query, Model model) {
        List<Room> rooms = roomRepository.searchRooms(query);
        model.addAttribute("rooms", rooms);
        return "rooms"; // Reuse the existing rooms.html to display results
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String getReg(Model model) {
        model.addAttribute("user", new MyAppUser());
        return "signup";
    }
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute MyAppUser user, Model model) {
        System.out.println(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        model.addAttribute("success", "Account created successfully. Please log in.");
        return "redirect:/req/login?success"; // Redirect to the login page with a success query parameter.
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        Optional<MyAppUser> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            // If login is successful, redirect to rooms.html
            return "redirect:/index";
        } else {
            // Login failed, show an error message
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }


}
