package com.example.demo.Repository;

import com.example.demo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE LOWER(r.city) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(r.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Room> searchRooms(@Param("query") String query);
}
