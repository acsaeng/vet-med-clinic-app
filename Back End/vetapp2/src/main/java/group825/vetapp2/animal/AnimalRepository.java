package group825.vetapp2.animal;

import group825.vetapp2.database.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Repository that stores Animal information
 *
 * @author Aron Saengchan, Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Repository("animalRepo")
public class AnimalRepository {

	/**
	 * Connector to the database
	 */
	Connection dao;

//	/**
//	 * Search object with methods to perform animal searches
//	 */
//	Search search;
//
//	/**
//	 * Desired number of search results
//	 */
//	int desiredNumberOfResults = 4;

	/**
     * Constructor that initializes the AnimalRepository
     */
	public AnimalRepository() {
		dao = DatabaseConnection.getConnection();
//		search = new Search(dao, tableName, desiredNumberOfResults);
	}

	/**
	 * Searches for an animal by ID number in the database
	 * @param animalID = animal's ID number
	 * @return animal if found, 'null' otherwise
	 */
	public Animal searchAnimalById(int animalID) {
		Animal animal = null;

		try {
			// Execute SQL query to retrieve specified animal
			PreparedStatement statement = this.dao.prepareStatement("SELECT * FROM ANIMAL WHERE Animal_ID = ?");

			statement.setInt(1, animalID);
			ResultSet results = statement.executeQuery();

			// Extract the animal's information
			while (results.next()) {
				animal = new Animal(results.getInt("Animal_ID"), results.getString("Animal_Name"),
						results.getString("Species"), results.getString("Breed"), results.getInt("Tattoo_Num"),
						results.getString("City_Tattoo"), LocalDate.parse(results.getString("Birth_Date")),
						results.getString("Sex").charAt(0), results.getString("RFID"), results.getString("Microchip"),
						results.getString("Health_Status"), results.getBoolean("Availability_Status"),
						results.getString("Colour"), results.getString("Additional_Info"));
			}

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return animal;
	}

    /**
     * Adds an animal to the database
     * @param animal animal to be added
     */
    public void addAnimal(Animal animal) {
		try {
			PreparedStatement statement = this.dao.prepareStatement("SELECT * FROM ANIMAL;");

			ResultSet result = statement.executeQuery();
			int rows = 0;

			while (result.next()) {
				rows++;
			}

			animal.setAnimalID(rows);

			// Execute SQL query to add new animal
			statement = this.dao.prepareStatement("INSERT INTO ANIMAL (Animal_ID, Animal_Name, " +
					"Species, Breed, Tattoo_Num, City_Tattoo, Birth_Date, Sex, RFID, Microchip, Health_Status, " +
					"Availability_Status, Colour, Additional_Info) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			statement.setInt(1, animal.getAnimalID());
			statement.setString(2, animal.getName());
			statement.setString(3, animal.getSpecies());
			statement.setString(4, animal.getBreed());
			statement.setInt(5, animal.getTattoo());
			statement.setString(6, animal.getCityTattoo());
			statement.setString(7, animal.getDob().toString());
			statement.setString(8, "" + animal.getSex());
			statement.setString(9, animal.getRfid());
			statement.setString(10, animal.getMicrochip());
			statement.setString(11, animal.getHealthStatus());
			statement.setBoolean(12, animal.isAvailabilityStatus());
			statement.setString(13, animal.getColour());
			statement.setString(14, animal.getAdditionalInfo());

			statement.executeUpdate();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    /**
     * Updates an animal's information
	 * @param animalID animal's ID number
	 * @param updatedInfo animal's updated information
     */

    public void updateAnimal(int animalID, Animal updatedInfo) {
		try {
			// Execute SQL query to update the user's information
			PreparedStatement statement = this.dao.prepareStatement("UPDATE ANIMAL SET Animal_ID = ?, Animal_Name = ?, " +
					" Species = ?, Breed = ?, Tattoo_Num = ?, City_Tattoo = ?, Birth_Date = ?, Sex = ?, RFID = ?, Microchip = ?, " +
					"Health_Status = ?, Availability_Status = ?, Colour = ?, Additional_Info = ? WHERE ANIMAL_ID = ?");

			statement.setInt(1, updatedInfo.getAnimalID());
			statement.setString(2, updatedInfo.getName());
			statement.setString(3, updatedInfo.getSpecies());
			statement.setString(4, updatedInfo.getBreed());
			statement.setInt(5, updatedInfo.getTattoo());
			statement.setString(6, updatedInfo.getCityTattoo());
			statement.setString(7, updatedInfo.getDob().toString());
			statement.setString(8, "" + updatedInfo.getSex());
			statement.setString(9, updatedInfo.getRfid());
			statement.setString(10, updatedInfo.getMicrochip());
			statement.setString(11, updatedInfo.getHealthStatus());
			statement.setBoolean(12, updatedInfo.isAvailabilityStatus());
			statement.setString(13, updatedInfo.getColour());
			statement.setString(14, updatedInfo.getAdditionalInfo());
			statement.setInt(15, animalID);

			statement.executeUpdate();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//    /**
//     * Searches for an animal by name in the database
//     * @param name = animal's name
//     * @param species =  animal's species
//     * @param onlyAvailableAnimals = boolean deciding whether to return all animals or only available animals
//     * @return specified animal if found, null otherwise
//     */
//    public ArrayList<String> searchAnimalByName(String name, String species, boolean onlyAvailableAnimals) throws Exception{
//    	ArrayList<String> foundResults = search.searchForName(name, species, onlyAvailableAnimals);
//    	return foundResults;
//    }
}