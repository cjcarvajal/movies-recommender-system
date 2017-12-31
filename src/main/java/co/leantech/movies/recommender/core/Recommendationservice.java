package co.leantech.movies.recommender.core;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.leantech.movies.recommender.model.Item;
import co.leantech.movies.recommender.model.User;
import co.leantech.movies.recommender.persistence.PersistenceManager;

/**
 * 
 * @author carlosj
 *
 */
@Service
public class Recommendationservice {

	@Autowired
	PersistenceManager persistenceManager;

	@Autowired
	RecommenderTrainedModel model;

	public User getUserRecommendations(Long userId, int neighbors) throws TasteException {
		List<RecommendedItem> items = model.getRecommendationsBycosineSimilarity(neighbors, userId);

		User user = new User(userId);

		for (RecommendedItem item : items) {
			Item movie = new Item(item.getItemID(), persistenceManager.getItemName(item.getItemID()));
			user.getRecommendedItems().add(movie);
		}

		return user;
	}
}
