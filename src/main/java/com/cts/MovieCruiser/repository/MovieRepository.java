package com.cts.MovieCruiser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.MovieCruiser.Movie;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, Integer>{

@Query(value="FROM Movie")
List<Movie> getAllMovies();

List<Movie> findByActive(String string);

Movie findByTitle(String title);
}
