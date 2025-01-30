package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String county;
    private String postcode;
    private Boolean furnished;
    private Boolean liveInLandlord;
    private int sharedWith;
    private Boolean billsIncluded;
    private Boolean bathroomShared;
    private int pricePerMonthShared;
    private Date availabilityDate;
    @ElementCollection
    private List<String> amenities;
    @ElementCollection
    private List<String> spokenLanguages;
    private Double distanceFromNtu;
    @ElementCollection
    private List<String> images;

    public Room(String name, String city, String county, String postcode,Double distanceFromNtu ,Boolean furnished, Boolean liveInLandlord,
                int sharedWith, Boolean billsIncluded, Boolean bathroomShared, int pricePerMonthShared, Date availabilityDate,
                List<String> amenities, List<String> spokenLanguages, List<String> images) {
        this.name = name;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
        this.distanceFromNtu = distanceFromNtu;
        this.furnished = furnished;
        this.liveInLandlord = liveInLandlord;
        this.sharedWith = sharedWith;
        this.billsIncluded = billsIncluded;
        this.bathroomShared = bathroomShared;
        this.pricePerMonthShared = pricePerMonthShared;
        this.availabilityDate = availabilityDate;
        this.amenities = amenities;
        this.spokenLanguages = spokenLanguages;
        this.images = images;
    }

    public void setDistanceFromNTU(double distance) {
        this.distanceFromNtu = distance;
    }
}
