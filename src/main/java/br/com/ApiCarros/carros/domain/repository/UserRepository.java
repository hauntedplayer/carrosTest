package br.com.ApiCarros.carros.domain.repository;

import br.com.ApiCarros.carros.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
