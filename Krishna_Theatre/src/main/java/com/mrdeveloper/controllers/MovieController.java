package com.mrdeveloper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mrdeveloper.services.MovieService;
import com.mrdeveloper.util.MovieInfo;

@RestController
public class MovieController {

	private MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping(value = "/upload", produces = "multipart/form-data", consumes = "multipart/form-data")
	public ResponseEntity<String> uploadMovies(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("title") String titles, @RequestParam("Description") String description) {
		int fileSize = files.size();
		System.out.println(" THIS IS THE FILE SIZE IN CONTROLLER " + fileSize);

		movieService.saveMovie(files, titles, description);

		return ResponseEntity.ok(" File Uploaded Successfully");

	}

   // GET ALL THE MOVIES 	
	
	@GetMapping(value = "/getAllMovies")
	public ResponseEntity<List<MovieInfo>> allMovies() {
		List<MovieInfo> allMovies = movieService.getAllMovies();
		System.out.println(" All Movies " + allMovies);

		return new ResponseEntity<>(allMovies, HttpStatus.OK);
	}

	// DOWNLOAD MOVIE BY ID
	@GetMapping(value="/downloadMovie/{movieId}")
	public ResponseEntity<InputStreamResource> downloadMovieById(@PathVariable Long movieId) {

		return movieService.downloadMovie(movieId);

	}

}
