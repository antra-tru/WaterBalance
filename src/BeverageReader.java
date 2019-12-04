import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeverageReader {

	public Map<Integer, BeverageData> readDatabase() {
		Map<Integer, BeverageData> result = new LinkedHashMap<>();
		try {
			Path path = Paths.get("Beverages.txt");
			List<String> lines = Files.readAllLines(path);
			int i = 1;
			for (String beverage : lines) {
				// System.out.println(beverage);
				String[] elems = beverage.split(" "); // sadala tekstu nosaukumâ un koeficientâ
				String beverageName = elems[0]; // nodefinç
				Double koefficient = Double.parseDouble(elems[1]); // nodefinç
				result.put(i, new BeverageData(beverageName, koefficient));
				i++;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
