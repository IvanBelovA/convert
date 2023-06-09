package ru.belov.currency_converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.belov.currency_converter.entity.Users;

import java.util.Optional;

import static ru.belov.currency_converter.util.SecurityUtil.currentUser;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    @Query("FROM Users u WHERE u.id = ?1")
    Optional<Users> findById(Long id);

    default Optional<Users> current() {
        return findById(currentUser().getId());
    }
}
