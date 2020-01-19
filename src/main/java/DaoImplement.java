import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import static java.sql.DriverManager.getConnection;


public class DaoImplement implements Dao {

    private Connection connection;
    private Statement statement;
    private String tableName = "samochody";

    private void initConnection() throws SQLException {
        final String HOST = "localhost";
        final int PORT = 3306;
        final String DB_NAME = "baza_samochodow";
        final String USER_NAME = "root";
        final String PASSWORD = "root";
        final String QUERY_STRING = "serverTimezone=UTC";
        String dburl = String.format("jdbc:mysql://%s:%d/%s?%s", HOST, PORT,
                DB_NAME, QUERY_STRING);
//        String dburl =("jdbc:mysql://localhost:3306/baza_samochodow?serverTimezone=UTC");
        connection = getConnection(dburl, USER_NAME, PASSWORD);
        statement = connection.createStatement();
    }

// Tworzenie tabeli
    private void createTable() throws SQLException{
        final String SQL_TABLE = "CREATE TABLE " + tableName + " ("
                + "id INT AUTO_INCREMENT, "
                + "carVin VARCHAR(17), "
                + "carBrand VARCHAR(30), "
                + "carModel VARCHAR(30), "
                + "carDateOfProduction VARCHAR(10), "
                + "carColour VARCHAR(20), "
                + "PRIMARY KEY (id))";

        final String DROP_IF_EXISTS = "DROP TABLE IF EXISTS " + tableName;

        statement.executeUpdate(DROP_IF_EXISTS);
        statement.executeUpdate(SQL_TABLE);
        }

    @Override
    public void connect() throws SQLException {
        initConnection();
        createTable();
    }
// Funkcja dodawania samochodu
    @Override
    public void saveCar(Car car) {
        final String SAVE_CAR = String.format("INSERT INTO %s (id, carVin, carBrand, carModel, carDateOfProduction,"
                        + " carColour)" +"VALUES (null, %d, '%s', '%s', '%s', '%s');",
                tableName,
                car.getCarVin(),
                car.getCarBrand(),
                car.getCarModel(),
                car.getCarDateOfProduction(),
                car.getCarColour()
        );
        try{
            this.statement.executeUpdate(SAVE_CAR);
            System.out.printf("Dodano samochód %s %s do bazy danych\n", car.getCarBrand(), car.getCarModel());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

//Wyszukiwanie samochodu do edycji

    Car searchForCar(int carId) throws SQLException{
        final String SEARCH_FOR_CAR_TO_EDIT = String.format("SELECT * FROM %s WHERE id=%d", tableName, carId);
            ResultSet resultSet = statement.executeQuery(SEARCH_FOR_CAR_TO_EDIT);
            Car searchedCar = null;
                while (resultSet.next()){
                int id = resultSet.getInt("id");
                int carVin = resultSet.getInt("carVin");
                String carBrand = resultSet.getString("carBrand");
                String carModel = resultSet.getString("carModel");
                String carDateOfProduction = resultSet.getString("carDateOfProduction");
                String carColour = resultSet.getString("carColour");
                searchedCar = new Car(id, carVin, carBrand, carModel, carDateOfProduction, carColour);
        }
        return searchedCar;
    }

    @Override
    public void editCar(Car editedCar) throws SQLException {
        final String EDIT_CAR = String.format("UPDATE %s SET " +
                "carVin = %d ," +
                "carBrand = '%s' ," +
                "carModel = '%s' ," +
                "carDateOfProduction = '%s' ," +
                "carColour = '%s' " +
                "WHERE id = %d",
                tableName,
                editedCar.getCarVin(),
                editedCar.getCarBrand(),
                editedCar.getCarModel(),
                editedCar.getCarDateOfProduction(),
                editedCar.getCarColour(),
                editedCar.getId());
        statement.executeUpdate(EDIT_CAR);
        System.out.println("Samochód został nadpisany\n");
    }


// Usuwanie samochodu
    boolean foundId(int id) throws SQLException{
        boolean value = false;
        List<Car> carList = createCarList();
        for (Car car : carList){
            if (car.getId() == id) {
                value = true;
                break;
            }
        }
        if (value == false){
            System.out.println("Nie ma takiego samochodu\n");
        }
        return value;
    }

    @Override
    public void removeCar(int id) throws SQLException {
        final String REMOVE_CAR = String.format("DELETE FROM %s WHERE id=%d",
                tableName, id);
        if (foundId(id)){
            statement.executeUpdate(REMOVE_CAR);
            System.out.printf("Usunięto samochód o ID: %d\n", id);
        }
        else {
            System.out.println("Nie ma takiego samochodu w bazie\n");
        }
    }


// Pokazanie całej listy samochodów

private List<Car> createCarList() throws SQLException{
    List<Car> carList = new ArrayList<>();
    final String FIND_ALL = String.format("SELECT * from %s", tableName);
    ResultSet resultSet = statement.executeQuery(FIND_ALL);

    while(resultSet.next()){
        int id = resultSet.getInt("id");
        int carVin = resultSet.getInt("carVin");
        String carBrand = resultSet.getString("carBrand");
        String carModel = resultSet.getString("carModel");
        String carDateOfProduction = resultSet.getString("carDateOfProduction");
        String carColour = resultSet.getString("carColour");

        Car car = new Car(id, carVin, carBrand, carModel, carDateOfProduction, carColour);
        carList.add(car);
    }
    return carList;
}

    @Override
    public List<Car> getCars() throws SQLException {
        List<Car> carList = createCarList();
        if (carList.isEmpty()){
            System.out.println("Nie ma samochodów w bazie danych");
        }
        else{
            for (Car car : carList){
                System.out.println(car.getCarInfo());
            }
        }
        return carList;
    }

}

