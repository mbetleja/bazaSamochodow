import java.sql.SQLException;
import java.util.List;

public interface Dao {

    void connect() throws SQLException;

    void saveCar(Car car);

    void editCar(Car car) throws SQLException;

    void removeCar(int carId) throws SQLException;

    List<Car> getCars() throws SQLException;


}
