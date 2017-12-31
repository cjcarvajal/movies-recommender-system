package co.leantech.movies.recommender.model;

/**
 * 
 * @author carlosj
 *
 */
public class Item {

	private Long id;

	private String name;

	public Item(Long itemId, String itemName) {
		super();
		this.id = itemId;
		this.name = itemName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long itemId) {
		this.id = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String itemName) {
		this.name = itemName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
