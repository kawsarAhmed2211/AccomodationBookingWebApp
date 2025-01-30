package com.example.demo;

import com.example.demo.Model.Room;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.Service.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomService roomService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		// Create a new room
		Room room = new Room(
				"Cozy Room in Shared House",
				"Nottingham",
				"Nottinghamshire",
				"NG11 8NS", // Postcode
				null, // Distance will be calculated later
				true, // Furnished
				true, // Live-in landlord
				3,    // Shared with 3 others
				true, // Bills included
				true, // Bathroom shared
				500,  // Price per month
				new Date(2024 - 1900, 7 - 1, 1), // Availability date
				Arrays.asList("WiFi", "Heating", "Kitchen"), // Amenities
				Arrays.asList("English", "Spanish"),
				Arrays.asList("image1.webp", "image2.webp", "image3.webp", "image4.webp", "image5.webp",
						"image6.webp", "image7.webp", "image8.webp", "image9.webp", "image10.webp", "image11.webp", "image12.webp"
				,"image13.webp","image14.webp","image15.webp","image16.webp", "image17.webp", "image18.webp", "image19.webp")// Spoken languages
		);

		Room savedRoom = roomRepository.save(room);
		try {
			// Calculate and update the distance from NTU
			roomService.calculateAndSaveDistanceForRoom(savedRoom.getId());
			roomService.calculateAndSaveDistanceForRoom(savedRoom.getId());
			System.out.println("Room saved with ID: " + savedRoom.getId());
			System.out.println("Distance from NTU: " + savedRoom.getDistanceFromNtu() + " km");
		} catch (Exception e) {
			System.err.println("Error calculating distance: " + e.getMessage());
		}
//		Room room2 = new Room(
//				"Room in shared apartment in Nottingham",
//				"Nottingham",
//				"Nottinghamshire",
//				"NG1 4AA", // Postcode
//				null, // Distance will be calculated later
//				true, // Furnished
//				true, // Live-in landlord
//				3,    // Shared with 3 others
//				true, // Bills included
//				true, // Bathroom shared
//				500,  // Price per month
//			  	new Date(2024 - 1900, 7 - 1, 1), // Availability date
//				Arrays.asList("WiFi", "Heating", "Kitchen"), // Amenities
//				Arrays.asList("English", "Spanish"),
//				Arrays.asList("image20.webp", "image21.webp", "image22.webp", "image23.webp", "image24.webp",
//						"image25.webp", "image26.webp", "image27.webp", "image28.webp", "image29.webp", "image30.webp")// Spoken languages
//		);
//
//		Room room3 = new Room(
//				"Room in shared apartment in Nottingham",
//				"Nottingham",
//				"Nottinghamshire",
//				"NG1 1FS", // Postcode
//				null, // Distance will be calculated later
//				true, // Furnished
//				true, // Live-in landlord
//				3,    // Shared with 3 others
//				true, // Bills included
//				true, // Bathroom shared
//				500,  // Price per month
//				new Date(2024 - 1900, 5 - 1, 1), // Availability date
//				Arrays.asList("WiFi", "Heating", "Kitchen"), // Amenities
//				Arrays.asList("English", "Spanish"),
//				Arrays.asList("image31.webp", "image32.webp", "image33.webp", "image34.webp", "image35.webp",
//						"image36.webp", "image37.webp", "image38.webp", "image39.webp", "image40.webp")// Spoken languages
//		);
//		// Save the room into the database
//		Room savedRoom = roomRepository.save(room);
//		try {
//			// Calculate and update the distance from NTU
//			roomService.calculateAndSaveDistanceForRoom(savedRoom.getId());
//			roomService.calculateAndSaveDistanceForRoom(savedRoom.getId());
//			System.out.println("Room saved with ID: " + savedRoom.getId());
//			System.out.println("Distance from NTU: " + savedRoom.getDistanceFromNtu() + " km");
//		} catch (Exception e) {
//			System.err.println("Error calculating distance: " + e.getMessage());
//		}
//		Room savedRoom2 = roomRepository.save(room2);
//		try {
//			// Calculate and update the distance from NTU
//			roomService.calculateAndSaveDistanceForRoom(savedRoom2.getId());
//			roomService.calculateAndSaveDistanceForRoom(savedRoom2.getId());
//			System.out.println("Room saved with ID: " + savedRoom2.getId());
//			System.out.println("Distance from NTU: " + savedRoom2.getDistanceFromNtu() + " km");
//		} catch (Exception e) {
//			System.err.println("Error calculating distance: " + e.getMessage());
//		}
//
//		Room savedRoom3 = roomRepository.save(room3);
//		try {
//			// Calculate and update the distance from NTU
//			roomService.calculateAndSaveDistanceForRoom(savedRoom3.getId());
//			roomService.calculateAndSaveDistanceForRoom(savedRoom3.getId());
//			System.out.println("Room saved with ID: " + savedRoom3.getId());
//			System.out.println("Distance from NTU: " + savedRoom3.getDistanceFromNtu() + " km");
//		} catch (Exception e) {
//			System.err.println("Error calculating distance: " + e.getMessage());
//		}
//
//		Room room4 = new Room(
//				"Rooms for rent in 2-bedroom apartment in St Ann's",
//				"Nottingham",
//				"Nottinghamshire",
//				"NG7 1GX", // Postcode
//				null, // Distance will be calculated later
//				true, // Furnished
//				true, // Live-in landlord
//				3,    // Shared with 3 others
//				true, // Bills included
//				true, // Bathroom shared
//				500,  // Price per month
//				new Date(2024 - 1900, 5 - 1, 1), // Availability date
//				Arrays.asList("WiFi", "Heating", "Kitchen"), // Amenities
//				Arrays.asList("English", "Spanish"),
//				Arrays.asList("image41.webp", "image42.webp", "image43.webp", "image44.webp", "image45.webp",
//						"image46.webp", "image47.webp", "image48.webp")// Spoken languages
//		);
//		Room savedRoom4 = roomRepository.save(room4);
//		try {
//			// Calculate and update the distance from NTU
//			roomService.calculateAndSaveDistanceForRoom(savedRoom4.getId());
//			roomService.calculateAndSaveDistanceForRoom(savedRoom4.getId());
//			System.out.println("Room saved with ID: " + savedRoom4.getId());
//			System.out.println("Distance from NTU: " + savedRoom4.getDistanceFromNtu() + " km");
//		} catch (Exception e) {
//			System.err.println("Error calculating distance: " + e.getMessage());
//		}
//		Room room5 = new Room(
//				"Rooms for rent in 2-bedroom apartment in St Ann's",
//				"Nottingham",
//				"Nottinghamshire",
//				"NG7 1GX", // Postcode
//				null, // Distance will be calculated later
//				true, // Furnished
//				true, // Live-in landlord
//				3,    // Shared with 3 others
//				true, // Bills included
//				true, // Bathroom shared
//				500,  // Price per month
//				new Date(2024 - 1900, 5 - 1, 1), // Availability date
//				Arrays.asList("WiFi", "Heating", "Kitchen"), // Amenities
//				Arrays.asList("English", "Spanish"),
//				Arrays.asList("image49.webp", "image50.webp", "image51.webp")// Spoken languages
//		);
//		Room savedRoom5 = roomRepository.save(room5);
//		try {
//			// Calculate and update the distance from NTU
//			roomService.calculateAndSaveDistanceForRoom(savedRoom5.getId());
//			roomService.calculateAndSaveDistanceForRoom(savedRoom5.getId());
//			System.out.println("Room saved with ID: " + savedRoom5.getId());
//			System.out.println("Distance from NTU: " + savedRoom5.getDistanceFromNtu() + " km");
//		} catch (Exception e) {
//			System.err.println("Error calculating distance: " + e.getMessage());
//		}

	}
}
