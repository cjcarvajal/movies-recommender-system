package co.leantech.movies.recommender.controller;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.leantech.movies.recommender.core.Recommendationservice;
import co.leantech.movies.recommender.model.User;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author carlosj
 *
 */
@RestController
public class RecommenderController {

	@Autowired
	Recommendationservice recommendationService;

	@ApiOperation(value = "Gets recommendations for the user")
	@RequestMapping(value = "/user/{userId}/recommendation", method = RequestMethod.GET)
	public User getUserRecommendations(@PathVariable Long userId) throws TasteException {
		return recommendationService.getUserRecommendations(userId, 100);
	}
}
