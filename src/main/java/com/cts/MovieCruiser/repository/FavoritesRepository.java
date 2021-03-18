package com.cts.MovieCruiser.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.MovieCruiser.Favorites;


@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Integer>{
	
	List<Favorites> findByFavUsId(int id); 
	
	void deleteById(int mov_id);
	

}
