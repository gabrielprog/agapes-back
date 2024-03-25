package br.com.agapesistemas.demo.database.repositories;

import br.com.agapesistemas.demo.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}
