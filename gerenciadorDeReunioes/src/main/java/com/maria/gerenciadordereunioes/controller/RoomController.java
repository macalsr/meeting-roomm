package com.maria.gerenciadordereunioes.controller;
import com.maria.gerenciadordereunioes.exceptions.ResourceNotFoundException;
import com.maria.gerenciadordereunioes.model.Room;
import com.maria.gerenciadordereunioes.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
@RestController @CrossOrigin(origins = "http://localhost:42 00")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRoms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/id")
    public ResponseEntity<Room> getRoomById(@PathVariable(value="id") long roomId)
        throws ResourceNotFoundException{
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new ResourceNotFoundException("Room not found:" + roomId));
            return ResponseEntity.ok().body(room);
        }

        @PostMapping("/rooms")
        public Room createRoom (@Valid @RequestBody Room room){
        return roomRepository.save(room);
        }


        @PutMapping("/rooms/{id}")
        public  ResponseEntity<Room> updateRoom (@PathVariable( value = "id") Long roomId,
                                                 @Valid @RequestBody Room roomDetails) throws  ResourceNotFoundException{
        Room room = roomRepository.findById( roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id::" + roomId));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok (updateRoom);
        }


        public Map<String,Boolean> deleteRoom (@PathVariable(value = "id") Long roomId)
            throws  ResourceNotFoundException{
                    Room room = roomRepository.findById(roomId)
                            .orElseThrow(() -> new ResourceNotFoundException("Room not found for this::" + roomId));
                    roomRepository.delete(room);
                    Map<String,Boolean> response = new HashMap<>();
                    response.put ("deleted", Boolean.TRUE);
                    return response;

    }
}

