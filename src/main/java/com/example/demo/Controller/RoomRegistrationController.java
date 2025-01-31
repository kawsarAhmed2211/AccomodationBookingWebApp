package com.example.demo.Controller;

import com.example.demo.Model.Room;
import com.example.demo.Service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/api/rooms")
public class RoomRegistrationController {

    private final RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        // Validate Room fields if necessary
        return roomService.addRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
