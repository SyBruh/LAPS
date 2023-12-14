package team8.laps.javaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team8.laps.javaca.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
