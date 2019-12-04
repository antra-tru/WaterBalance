import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WaterBalance {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BeverageReader beverageReader = new BeverageReader();
		Map<Integer, BeverageData> database = beverageReader.readDatabase();

		System.out.println("Type Your weight: ");
		double weight = sc.nextDouble();
		System.out.println("Type Your age: ");
		int age = sc.nextInt();

		double waterBalance = Math.ceil(((((weight / 0.45359) / 2.2) * age) / 28.3) / 0.03520);

		System.out.println("Your everyday water intake should be: " + waterBalance + " ml");
		System.out.println("\n");

		boolean userExited = false;
		while (!userExited) {

			System.out.println(
					"What did You drink? (1-Water; 2-Coffee; 3-Tea; 4-SoftDrink; 5-Wine; 6-Beer; 7-StrongAlcohol; 0-EXIT PROGRAM!): ");
			int beverageType = sc.nextInt();

			if (beverageType == 0) {
				userExited = true;
				System.out.println("Program EXITED! But You still have to drink " + waterBalance + " ml!");
				break;
			}

			if (beverageType < 0 || beverageType > 7) {
				System.out.println("Invalid input, try again!");
			} else {

				System.out.println("How much did You drink (in ml)?");
				double beverageAmount = sc.nextDouble();

				BeverageData beverageData = database.get(beverageType);
				double userIntake = beverageAmount * beverageData.getKoeff();
				double stillToDrink = waterBalance - userIntake;

				if (userIntake < waterBalance) {
					System.out.println("Today You still have to drink " + stillToDrink + " ml. Keep going!");
					waterBalance = stillToDrink;

				} else {
					/* if (stillToDrink > waterBalance) { */
				//	double overTake = stillToDrink - waterBalance;
					double overTake = userIntake - waterBalance;
					double extraWine = Math.ceil((overTake / 0.7) - overTake);
					System.out.println("Congratulations! You reached Your daily GOAL! You can still drink " + extraWine + " ml of wine. CHEERS!");
					/* } else { */
					userExited = true;
					//System.out.println("Congratulations! You reached Your daily GOAL!");
				}
			}
		}
	}
}