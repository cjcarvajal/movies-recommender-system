package co.leantech.movies.recommender.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import co.leantech.movies.recommender.model.Item;

/**
 * 
 * @author carlosj
 *
 */
@Service
@Scope("singleton")
public class PersistenceManager {

	private Map<Long, Item> itemsMap;

	public PersistenceManager() throws IOException {
		loadItemsData();
	}

	private void loadItemsData() throws IOException {
		itemsMap = new HashMap<>();
		File file = File.createTempFile("temp", "csv");
		FileUtils.copyInputStreamToFile(new ClassPathResource("movies-data.csv").getInputStream(), file);
		final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
		final CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(parser).build();

		String[] line;
		while ((line = reader.readNext()) != null) {
			Item item = new Item(Long.parseLong(line[0]), line[1]);
			itemsMap.put(item.getId(), item);
		}
	}

	public String getItemName(Long id) {
		Item item = itemsMap.get(id);

		if (item == null)
			return StringUtils.EMPTY;

		return item.getName();

	}

}
