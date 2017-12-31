package co.leantech.movies.recommender.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author carlosj
 *
 */
public class User {

	Long id;

	Set<Item> favouriteItems;

	Set<Item> recommendedItems;

	public User(Long id) {
		this.id = id;
		favouriteItems = new HashSet<>();
		recommendedItems = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Item> getFavouriteItems() {
		return favouriteItems;
	}

	public Set<Item> getRecommendedItems() {
		return recommendedItems;
	}
}
