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

// Room 1
		Room room1 = new Room(
				"Cozy Studio in the Heart of Nottingham",
				"Nottingham",
				"Nottinghamshire",
				"NG1 1AA", // Postcode
				null, // Distance will be calculated later
				true, // Furnished
				true, // Live-in landlord
				2,    // Shared with 2 others
				true, // Bills included
				true, // Bathroom shared
				450,  // Price per month
				new Date(2024 - 1900, 6 - 1, 1), // Availability date
				Arrays.asList("WiFi", "Heating", "Kitchen", "Laundry"), // Amenities
				Arrays.asList("English", "French"),
				Arrays.asList("image1.webp", "image2.webp", "image3.webp", "image4.webp", "image5.webp",
						"image6.webp", "image7.webp", "image8.webp", "image9.webp", "image10.webp") // Images
		);

// Save Room 1
		Room savedRoom1 = roomRepository.save(room1);
		try {
			roomService.calculateAndSaveDistanceForRoom(savedRoom1.getId());
			System.out.println("Room 1 saved with ID: " + savedRoom1.getId());
			System.out.println("Distance from NTU: " + savedRoom1.getDistanceFromNtu() + " km");
		} catch (Exception e) {
			System.err.println("Error calculating distance for Room 1: " + e.getMessage());
		}

// Room 2
		Room room2 = new Room(
				"Modern 2-Bedroom Apartment in City Centre",
				"Nottingham",
				"Nottinghamshire",
				"NG1 3EN", // Postcode
				null, // Distance will be calculated later
				true, // Furnished
				false, // No live-in landlord
				2,    // Shared with 2 others
				true, // Bills included
				true, // Bathroom shared
				550,  // Price per month
				new Date(2024 - 1900, 7 - 1, 1), // Availability date
				Arrays.asList("WiFi", "Heating", "Dishwasher", "Balcony"), // Amenities
				Arrays.asList("English", "German"),
				Arrays.asList("image11.webp", "image12.webp", "image13.webp", "image14.webp", "image15.webp",
						"image16.webp", "image17.webp", "image18.webp", "image19.webp", "image20.webp") // Images
		);

// Save Room 2
		Room savedRoom2 = roomRepository.save(room2);
		try {
			roomService.calculateAndSaveDistanceForRoom(savedRoom2.getId());
			System.out.println("Room 2 saved with ID: " + savedRoom2.getId());
			System.out.println("Distance from NTU: " + savedRoom2.getDistanceFromNtu() + " km");
		} catch (Exception e) {
			System.err.println("Error calculating distance for Room 2: " + e.getMessage());
		}

// Room 3
		Room room3 = new Room(
				"Affordable Room with Great Amenities",
				"Nottingham",
				"Nottinghamshire",
				"NG3 4BR", // Postcode
				null, // Distance will be calculated later
				true, // Furnished
				true, // Live-in landlord
				3,    // Shared with 3 others
				false, // Bills not included
				false, // Bathroom not shared
				400,  // Price per month
				new Date(2024 - 1900, 8 - 1, 1), // Availability date
				Arrays.asList("WiFi", "Heating", "Parking"), // Amenities
				Arrays.asList("English"),
				Arrays.asList("image21.webp", "image22.webp", "image23.webp", "image24.webp", "image25.webp",
						"image26.webp", "image27.webp", "image28.webp", "image29.webp", "image30.webp") // Images
		);

// Save Room 3
		Room savedRoom3 = roomRepository.save(room3);
		try {
			roomService.calculateAndSaveDistanceForRoom(savedRoom3.getId());
			System.out.println("Room 3 saved with ID: " + savedRoom3.getId());
			System.out.println("Distance from NTU: " + savedRoom3.getDistanceFromNtu() + " km");
		} catch (Exception e) {
			System.err.println("Error calculating distance for Room 3: " + e.getMessage());
		}

// Room 4
		Room room4 = new Room(
				"Spacious Room in Quiet Neighborhood",
				"Nottingham",
				"Nottinghamshire",
				"NG5 5JD", // Postcode
				null, // Distance will be calculated later
				true, // Furnished
				false, // No live-in landlord
				1,    // Shared with 1 other
				true, // Bills included
				true, // Bathroom shared
				480,  // Price per month
				new Date(2024 - 1900, 9 - 1, 1), // Availability date
				Arrays.asList("WiFi", "Heating", "Balcony", "Gym"), // Amenities
				Arrays.asList("English", "Italian"),
				Arrays.asList("image31.webp", "image32.webp", "image33.webp", "image34.webp", "image35.webp",
						"image36.webp", "image37.webp", "image38.webp", "image39.webp", "image40.webp") // Images
		);

// Save Room 4
		Room savedRoom4 = roomRepository.save(room4);
		try {
			roomService.calculateAndSaveDistanceForRoom(savedRoom4.getId());
			System.out.println("Room 4 saved with ID: " + savedRoom4.getId());
			System.out.println("Distance from NTU: " + savedRoom4.getDistanceFromNtu() + " km");
		} catch (Exception e) {
			System.err.println("Error calculating distance for Room 4: " + e.getMessage());
		}

// Room 5
		Room room5 = new Room(
				"Stylish Room in Historic Building",
				"Nottingham",
				"Nottinghamshire",
				"NG7 3AA", // Postcode
				null, // Distance will be calculated later
				true, // Furnished
				true, // Live-in landlord
				4,    // Shared with 4 others
				false, // Bills not included
				true, // Bathroom shared
				600,  // Price per month
				new Date(2024 - 1900, 10 - 1, 1), // Availability date
				Arrays.asList("WiFi", "Heating", "Smart TV", "Washing Machine"), // Amenities
				Arrays.asList("English", "Portuguese"),
				Arrays.asList("image41.webp", "image42.webp", "image43.webp", "image44.webp", "image45.webp",
						"image46.webp", "image47.webp", "image48.webp", "image49.webp", "image50.webp") // Images
		);

// Save Room 5
		Room savedRoom5 = roomRepository.save(room5);
		try {
			roomService.calculateAndSaveDistanceForRoom(savedRoom5.getId());
			System.out.println("Room 5 saved with ID: " + savedRoom5.getId());
			System.out.println("Distance from NTU: " + savedRoom5.getDistanceFromNtu() + " km");
		} catch (Exception e) {
			System.err.println("Error calculating distance for Room 5: " + e.getMessage());
		}


	}
}
