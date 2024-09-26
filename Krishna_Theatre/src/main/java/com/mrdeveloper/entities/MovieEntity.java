package com.mrdeveloper.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "KRISHNA_THEATRE_MOVIE")
public class MovieEntity {

	@Id
	@Column(name = "MOVIE_ID")
	// Below annotation will create the id of the movies sequentialy inascending
	// order
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KRISHNA_THEATRE_MOVIE_SEQ")
	@SequenceGenerator(name = "KRISHNA_THEATRE_MOVIE_SEQ", sequenceName = "KRISHNA_THEATRE_MOVIE_SEQ", allocationSize = 1)
	private long movieId;

	@Column(name = "MOVIE_NAME")
	private String movieName;

	@Column(name = "MOVIE_TITLE")
	private String movieTitle;
	
	@Column(name="MOVIE_FILE")
	@Lob
    private byte[] movieFile;
	
	@Column(name = "UPLOAD_TIME")
	private LocalDateTime uploadTime;

	@Column(name = "MOV_FILE_PATH")
	private String filePath;

	@Column(name = "MOVIE_DESCRIPTION")
	private String description;

	@Column(name = "MOVIE_URL")
	private String movieUrl;

	// SETTERS AND GETTERS FOR THE VARIABLES

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public byte[] getMovieFile() {
		return movieFile;
	}

	public void setMovieFile(byte[] movieFile) {
		this.movieFile = movieFile;
	}

	public LocalDateTime getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(LocalDateTime uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	
	// toString FOR ABOVE VARIABLES

	@Override
	public String toString() {
		return "MovieEntity [movieId=" + movieId + ", movieName=" + movieName + ", movieTitle=" + movieTitle
				+ ", uploadTime=" + uploadTime + ", filePath=" + filePath + ", description=" + description
				+ ", movieUrl=" + movieUrl + "]";
	}

}
