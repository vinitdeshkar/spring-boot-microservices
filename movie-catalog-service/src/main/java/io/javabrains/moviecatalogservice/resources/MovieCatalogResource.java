package io.javabrains.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	
	@Autowired
    private RestTemplate restTemplate;


	@GetMapping("getcatalog/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		
		//get all rated movie ids

        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);

        return userRating.getUserRating().stream()
                .map(rating -> {
                	//For each movie id, call the movie info service and get details
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/getmovieinfo/" + rating.getMovieId(), Movie.class);
                    
                  //Put them all together
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList());

		
//		return Collections.singletonList(new CatalogItem("Avengers", "Avengers Movie", 5));
	
	}
	

}
