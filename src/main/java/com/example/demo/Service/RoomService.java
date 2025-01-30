package com.example.demo.Service;

import com.example.demo.Model.Room;
import com.example.demo.Repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    // Latitude and Longitude for NTU Clifton Campus
    private static final double NTU_LAT = 52.9124;
    private static final double NTU_LON = -1.1760;

    private static final String POSTCODE_API_URL = "https://api.postcodes.io/postcodes/%s"; // API for postcode lookup
    private final RoomRepository roomRepository;

    /**
     * Fetch latitude and longitude for a given postcode using the Postcodes.io API.
     *
     * @param postcode The postcode to look up.
     * @return An array containing latitude and longitude.
     * @throws Exception If the API call fails or the response is invalid.
     */
    private double[] getLatLonForPostcode(String postcode) throws Exception {
        String url = String.format(POSTCODE_API_URL, postcode);
        RestTemplate restTemplate = new RestTemplate();

        try {
            PostcodeResponse response = restTemplate.getForObject(url, PostcodeResponse.class);

            // Check if response or result is null
            if (response != null && response.getStatus() == 200 && response.getResult() != null) {
                PostcodeResponse.Result result = response.getResult();
                return new double[]{result.getLatitude(), result.getLongitude()};
            } else {
                throw new Exception("Invalid or null response for postcode: " + postcode);
            }
        } catch (Exception e) {
            throw new Exception("Error fetching lat/lon for postcode " + postcode + ": " + e.getMessage());
        }
    }


    /**
     * Calculate the distance in kilometers between two geographical points using the Haversine formula.
     *
     * @param lat1 Latitude of the first point.
     * @param lon1 Longitude of the first point.
     * @param lat2 Latitude of the second point.
     * @param lon2 Longitude of the second point.
     * @return The distance in kilometers.
     */
    private double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Radius of the Earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance= EARTH_RADIUS * c;
        return Math.round(distance * 100.0) / 100.0;
    }

    /**
     * Get the distance from NTU Clifton Campus to a given postcode.
     *
     * @param postcode The postcode to calculate the distance to.
     * @return The distance in kilometers.
     * @throws Exception If latitude/longitude for the postcode cannot be fetched.
     */
    public double getDistanceFromNTU(String postcode) throws Exception {
        double[] latLon = getLatLonForPostcode(postcode);
        return calculateHaversineDistance(NTU_LAT, NTU_LON, latLon[0], latLon[1]);
    }

    /**
     * Calculate and save the distance from NTU for a single room.
     *
     * @param roomId The ID of the room to update.
     */
    public void calculateAndSaveDistanceForRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));

        try {
            String postcode = room.getPostcode();
            double distance = getDistanceFromNTU(postcode);

            // Save the distance in the room entity
            room.setDistanceFromNTU(distance);
            roomRepository.save(room);

            // Log the result
            logger.info("Calculated distance for room {}: {} km", room.getId(), distance);
        } catch (Exception e) {
            logger.error("Error calculating distance for room {}: {}", room.getId(), e.getMessage());
        }
    }

    /**
     * Calculate and save the distance for all rooms in the database.
     */
    public void calculateAndSaveDistanceForAllRooms() {
        List<Room> rooms = roomRepository.findAll();

        for (Room room : rooms) {
            try {
                String postcode = room.getPostcode();
                double distance = getDistanceFromNTU(postcode);

                // Save the distance in the room entity
                room.setDistanceFromNTU(distance);
                roomRepository.save(room);

                // Log the result
                logger.info("Calculated distance for room {}: {} km", room.getId(), distance);
            } catch (Exception e) {
                logger.error("Error calculating distance for room {}: {}", room.getId(), e.getMessage());
            }
        }
    }

    // Other methods for room management remain the same
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public Room addRoom(Room room) {
        Room savedRoom = roomRepository.save(room);
        try {
            calculateAndSaveDistanceForRoom(savedRoom.getId());
        } catch (Exception e) {
            logger.error("Error calculating distance for room {}: {}", savedRoom.getId(), e.getMessage());
        }
        return savedRoom;
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> searchRooms(String query) {
        return roomRepository.searchRooms(query);
    }
}
