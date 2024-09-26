package com.mrdeveloper.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mrdeveloper.entities.MovieEntity;

public interface MovieEntityRepository extends JpaRepository<MovieEntity, Serializable> {

	@Query("SELECT entity.movieName from MovieEntity entity WHERE entity.movieName=:movieName")
	public String findByMovieName(@Param("movieName") String movieName);
}
