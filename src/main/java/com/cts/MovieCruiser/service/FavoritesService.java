package com.cts.MovieCruiser.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cts.MovieCruiser.Favorites;
import com.cts.MovieCruiser.Movie;
import com.cts.MovieCruiser.repository.FavoritesRepository;

@Service
public class FavoritesService {

	@Autowired
	FavoritesRepository favoritesRepository;

	@Transactional
	public List<Favorites> getFavorites(int id) {

		return favoritesRepository.findByFavUsId(id);

	}

	@Transactional
	public void addToFavorites(Favorites fav) {

		favoritesRepository.save(fav);

	}

	@Transactional
	public void deleteFavorites(int id) {

		favoritesRepository.deleteById(id);

	}
}
