package com.cts.MovieCruiser.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.MovieCruiser.Favorites;
import com.cts.MovieCruiser.Movie;
import com.cts.MovieCruiser.service.FavoritesService;
import com.cts.MovieCruiser.service.MovieService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*")
public class MovieCruiserController {

	@Autowired
	MovieService movieService;

	@Autowired
	FavoritesService favoritesService;

	@GetMapping("/authenticate")
	@ResponseBody
	private Map<String, String> getToken(@RequestHeader("Authorization") String authHeader) {

		Map<String, String> m = new HashMap<>();
		String user = getUser(authHeader);
		String token = generateJwt(user);
		m.put("token", token);
		return m;

	}

	private String getUser(String authHeader) {

		String baseStr = authHeader.substring(authHeader.indexOf("Basic ") + 6);
		byte[] encCred = Base64.getDecoder().decode(baseStr);
		String encStr = new String(encCred);
		// log.debug("EncStr " +encStr);
		return encStr.substring(0, encStr.indexOf(":"));

	}

	private String generateJwt(String user) {

		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);

		// Set the token issue time as current time
		builder.setIssuedAt(new Date());

		// Set the token expiry as 20 minutes from now
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "ABC123");
		String token = builder.compact();
		return token;

	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public List<Movie> customerMovieList() {
		List<Movie> mList = movieService.getAllMovies();
		// System.out.println(mList);
		return mList;
	}

	@RequestMapping("/customer")
	public List<Movie> viewMoviesCustomer(ModelMap model) {

		List<Movie> customerMovieList = new ArrayList<>();
		customerMovieList = movieService.findAllMoviesCustomer();
		return customerMovieList;

	}

	@RequestMapping(value = "/favorites", method = RequestMethod.GET)
	@ResponseBody
	public List<Favorites> favorites() {
		return favoritesService.getFavorites(2);
	}

	@DeleteMapping("/fav-delete/{id}")
	public List<Favorites> deleteFavorite(@PathVariable int id) {
		favoritesService.deleteFavorites(id);
		return favoritesService.getFavorites(2);
	}

//	@PostMapping("/newFavorite")
//	public String addFavorite(@RequestBody Movie movie) {
//		Favorites fav = new Favorites();
//		fav.setFavUsId(2);
//		fav.setMovies(movie);
//		favoritesService.addToFavorites(fav);
//		return "added successfully";
//	}

	@PostMapping(value = "/newFavorite/{id}")
	public ResponseEntity<Movie> addToFav(@PathVariable int id) {
		Movie movie = movieService.findById(id);
		Favorites fav = new Favorites();
		fav.setFavUsId(2);
		fav.setMovies(movie);
		favoritesService.addToFavorites(fav);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	@GetMapping("/edit/{id}")
	public Movie editMovieList(@PathVariable int id) {
		return movieService.findById(id);
	}

	@PutMapping("/edit-success")
	public String editMovie(@RequestBody Movie movie) {
		Movie m = movieService.findById(movie.getMovie_id());
		m.setTitle(movie.getTitle());
		m.setBox_office(movie.getBox_office());
		// m.setGenre(movie.getGenre());
		movieService.saveMovie(m);
		return "edit-successful";
	}

	@GetMapping("/logout")
	public void logout(HttpSession session) {

		session.invalidate();
		System.out.println("logged out");

	}

}
