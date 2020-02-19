package com.rental.uigastore.repository;

import com.rental.uigastore.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<Film> getById(Long id);

    List<Film> getAllByAvailable(Boolean availability);
}
