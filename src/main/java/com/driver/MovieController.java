package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<Movie> addMovie(@RequestBody() Movie movie){

        boolean temp = movieService.addMovieByBody(movie);
        if(temp){
            return new ResponseEntity<>(movie,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director){
        boolean temp = movieService.addDirectorByBody(director);
        if(temp){
            return new ResponseEntity<>("success",HttpStatus.OK);
        }

        return new ResponseEntity<>("director already exists",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String>  addMovieDirectorPair(@RequestParam String movieName, String directorName){
        boolean temp = movieService.addMovieDirectorByNames(movieName,directorName);
        if(temp==true){
            return new ResponseEntity<>("success",HttpStatus.OK);
        }

        return new ResponseEntity<>("Director or Movie Doesn't Exist",HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        Movie movie = movieService.getMovieFromPathName(movieName);
        if(movie==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        Director director = movieService.getDirectorFromPathName(directorName);
        if(director==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(director,HttpStatus.OK);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<Movie>> getMoviesByDirectorName(@PathVariable("director") String directorName){
        Director director = movieService.getDirectorFromPathName(directorName);
        if(director==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        List list = movieService.getListFromPathName(directorName);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<Movie>>  findAllMovies(){
        List list = movieService.getAllMovies();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
        boolean temp = movieService.deleteDirector(directorName);
        if(temp==false){
            return new ResponseEntity<>("director not found",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("director is deleted",HttpStatus.OK);

    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String>  deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("all directors and movies by them are deleted",HttpStatus.OK);
    }


}
