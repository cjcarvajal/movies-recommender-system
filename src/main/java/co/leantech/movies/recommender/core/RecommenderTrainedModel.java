package co.leantech.movies.recommender.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author carlosj
 *
 */
@Service
@Scope("singleton")
public class RecommenderTrainedModel {

	private DataModel model;

	private static final int RECOMMENDATIONS_NUMBER = 10;

	public RecommenderTrainedModel() throws IOException {
		File file = File.createTempFile("temp", "csv");
		FileUtils.copyInputStreamToFile(new ClassPathResource("ratings.csv").getInputStream(), file);
		model = new FileDataModel(file, ",");
	}

	public List<RecommendedItem> getRecommendationsBycosineSimilarity(int neighbors, Long userId)
			throws TasteException {

		UserSimilarity userSimilarity = new UncenteredCosineSimilarity(model);

		UserNeighborhood neighborhood = new NearestNUserNeighborhood(neighbors, userSimilarity, model);

		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, userSimilarity);

		return recommender.recommend(userId, RECOMMENDATIONS_NUMBER);
	}

}
