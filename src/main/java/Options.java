import java.sql.SQLException;
import java.util.Scanner;


public class Options {

    Scanner scan = new Scanner(System.in);


    public Car addCar(){
        System.out.println("Wprowadź VIN: ");
        int carVin = scan.nextInt();
        System.out.println("Wprowadź markę: ");
        String carBrand = scan.next();
        System.out.println("Wprowadź model: ");
        String carModel = scan.next();
        System.out.println("Wprowadź datę produkcji dd/mm/yyy");
        String carDateOfProduction = scan.next();
        System.out.println("Wprowadź kolor: ");
        String carColour = scan.next();

        Car car = new Car(carVin, carBrand, carModel, carDateOfProduction, carColour);

        System.out.println("Samochód został dodany do bazy\n");
        return car;
    }

    private String setNewString(){
        String newString = scan.next();
        return newString;
    }
    private int setNewInt(){
        int newInt = scan.nextInt();
        return newInt;
    }

    public Car editCar() throws SQLException{
        int id;
        do {
            System.out.println("Wprowadź ID samochodu");
            id = scan.nextInt();
        }
        while (!Main.daoImplement.foundId(id));

        Car editedCar = Main.daoImplement.searchForCar(id);

        System.out.printf("Podaj nowy VIN, stary to: %d\n", editedCar.getCarVin());
        editedCar.setCarVin(setNewInt());
        System.out.printf("Podaj nową markę, stara to: %s\n", editedCar.getCarBrand());
        editedCar.setCarBrand(setNewString());
        System.out.printf("Podaj nowy model, stary to: %s\n", editedCar.getCarModel());
        editedCar.setCarModel(setNewString());
        System.out.printf("Podaj nową datę produkcji w formacie dd/mm/yyy, stara to: %s\n", editedCar.getCarDateOfProduction());
        editedCar.setCarDateOfProduction(setNewString());
        System.out.printf("Podaj kolor, stary to: %s\n", editedCar.getCarColour());
        editedCar.setCarColour(setNewString());

        return editedCar;

    }

    public int carIdToDelete(){
        System.out.println("Podaj ID samochodu do usunięcia: ");
        int carId = scan.nextInt();
        return carId;
    }


}
