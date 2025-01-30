//package com.example.demo.Controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//public class DistanceController {
//
//    private final String OSRM_BASE_URL = "http://router.project-osrm.org";
//    private final String POSTCODES_IO_URL = "https://api.postcodes.io/postcodes/";
//
//    // Base coordinates (NTU Clifton Campus)
//    private final double baseLatitude = 52.9124;
//    private final double baseLongitude = -1.1814;
//
//    /**
//     * API Endpoint to calculate distance between NTU Clifton and a given postcode.
//     * Example: /calculate-distance?postcode=NG11+8NS
//     *
//     * @param postcode UK postcode for which to calculate the distance.
//     * @return Distance information from OSRM.
//     */
//    @GetMapping("/calculate-distance")
//    public String calculateDistance(@RequestParam String postcode) {
//        try {
//            // Step 1: Fetch latitude and longitude of the postcode
//            String postcodeUrl = POSTCODES_IO_URL + postcode;
//            RestTemplate restTemplate = new RestTemplate();
//            String postcodeResponse = restTemplate.getForObject(postcodeUrl, String.class);
//
//            // Parse latitude and longitude from the response
//            double targetLatitude = extractLatitude(postcodeResponse);
//            double targetLongitude = extractLongitude(postcodeResponse);
//
//            // Step 2: Build coordinates string for OSRM API
//            String coordinates = baseLongitude + "," + baseLatitude + ";" + targetLongitude + "," + targetLatitude;
//            String osrmUrl = String.format("%s/route/v1/driving/%s?overview=false", OSRM_BASE_URL, coordinates);
//
//            // Step 3: Call OSRM API to calculate the distance
//            String osrmResponse = restTemplate.getForObject(osrmUrl, String.class);
//            return osrmResponse; // Return OSRM response
//
//        } catch (Exception e) {
//            return String.format("Error: %s", e.getMessage());
//        }
//    }
//
//    // Helper method to extract latitude from Postcodes.io response
//    private double extractLatitude(String jsonResponse) {
//        return Double.parseDouble(jsonResponse.split("\"latitude\":")[1].split(",")[0]);
//    }
//
//    // Helper method to extract longitude from Postcodes.io response
//    private double extractLongitude(String jsonResponse) {
//        return Double.parseDouble(jsonResponse.split("\"longitude\":")[1].split(",")[0]);
//    }
//}
//*