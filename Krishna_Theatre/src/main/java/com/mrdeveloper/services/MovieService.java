package com.mrdeveloper.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mrdeveloper.entities.MovieEntity;
import com.mrdeveloper.repositories.MovieEntityRepository;
import com.mrdeveloper.util.MovieInfo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class MovieService {

	private final MovieEntityRepository movieEntityRepository;

	@Autowired
	public MovieService(MovieEntityRepository movieEntityRepository, MovieEntityRepository movieEntityRepository2) {
		this.movieEntityRepository = movieEntityRepository2;
	}

	// Save the movie into the database
	public void saveMovie(List<MultipartFile> files, String title, String description) {
		if (files.isEmpty()) {
			throw new RuntimeException(" File is Empty ");
		}
		int fileSize = files.size();
		System.out.println(" THIS IS THE FILE SIZE IN SERVICE " + fileSize);
		// get file one by one
		for (MultipartFile file : files) {
			String originalFilename = file.getOriginalFilename();
			String movieTitle = title;
			String uploadDirectory = new File("src/main/resources/static/MyMovies/").getAbsolutePath();// Path goes here

			// save the file to specified directory
			try {
				 byte[] movieFile=file.getBytes();
				File destinationFile = new File(uploadDirectory , originalFilename);
				file.transferTo(destinationFile);

				// Create Entity and save each files to Movie Entity
				MovieEntity movie = new MovieEntity();
				movie.setMovieTitle(movieTitle);
				movie.setDescription(description);
				movie.setFilePath(destinationFile.getAbsolutePath());
				// movie.setFilePath("/MoMovies/"+originalFilename);
				movie.setMovieFile(movieFile);
				movie.setUploadTime(LocalDateTime.now());

				// call save method to save above details to entity
				movieEntityRepository.save(movie);
			} catch (IOException ioe) {
				throw new RuntimeException(" Failed to store file " + originalFilename, ioe);
			}
		}
	}

	// TO get all the movies from database
	public List<MovieInfo> getAllMovies() {

		List<MovieInfo> listOfAllMovies = new ArrayList<>();

		List<MovieEntity> moviesFromEntity = movieEntityRepository.findAll();

		for (MovieEntity eachMovie : moviesFromEntity) {
			if (eachMovie != null) {

				// create a movie Object
				MovieInfo movies = new MovieInfo();

				// set the data into movie object
				movies.setMovieId(eachMovie.getMovieId());
				movies.setMovieName(eachMovie.getMovieName());
				movies.setMovieTitle(eachMovie.getMovieTitle());
				movies.setDescription(eachMovie.getDescription());
				movies.setFilePath("/MyMovies/" + eachMovie.getMovieName());
			//	movies.setFilePath(eachMovie.getFilePath());
				movies.setMovieUrl(eachMovie.getMovieUrl());
				movies.setUploadTime(eachMovie.getUploadTime());
				movies.setMovieFile(eachMovie.getMovieFile());

				// add the movie object into list
				listOfAllMovies.add(movies);
			}
		}
		return listOfAllMovies;

	}
	
	
	//Download the movie by ID
	public ResponseEntity<InputStreamResource> downloadMovie(Long movieId) {
		
		//get the movie by id
		MovieEntity movie=movieEntityRepository.findById(movieId).orElseThrow(()->new RuntimeException(" File not found on this Id"+movieId));
		
		//fetch the data
		byte[] fileData=movie.getMovieFile();
		String fileName=movie.getMovieName()+".mp4";
		
		//create an input stream resource to send file as tream
		InputStreamResource inputStreamResource=new InputStreamResource(new ByteArrayInputStream(fileData));
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filenme=\""+fileName+"\"")
				.contentType(MediaType.parseMediaType("video/mp4"))
				.contentLength(fileData.length).body(inputStreamResource);
		
	}
	
	
	
	

}
