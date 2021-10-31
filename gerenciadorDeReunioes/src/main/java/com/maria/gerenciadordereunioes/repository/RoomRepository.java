package com.maria.gerenciadordereunioes.repository;
import com.maria.gerenciadordereunioes.model.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
}
