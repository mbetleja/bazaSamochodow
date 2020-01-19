import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static DaoImplement daoImplement = new DaoImplement();

    public static void main(String[] args) throws SQLException {

        Options options = new Options();

        daoImplement.connect();

        System.out.println("Samochody testowe");
        Car car1 = new Car(12345,"Opel","Astra","11/12/2005","czarny");
        Car car2 = new Car(12345,"Renault","Laguna","05/03/2003","czerwony");
        Car car3 = new Car(12345,"Audi","Quatro","01/04/2001","żółty");
        daoImplement.saveCar(car1);
        daoImplement.saveCar(car2);
        daoImplement.saveCar(car3);
        System.out.println("-------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        top: while(true){
            System.out.println("DOSTĘPNE OPCJE:");
            System.out.println("1 - Dodaj samochów");
            System.out.println("2 - Edytuj samochów");
            System.out.println("3 - Usuń samochód");
            System.out.println("4 - Pokaż samochody");
            System.out.println("5 - Wyjdź");
            System.out.print("Wybierz opcję: ");
            int choice;
            if (scanner.hasNextInt()){
                choice = scanner.nextInt();
            }
            else{
                scanner.next();
                continue;
            }
            switch (choice){

                case 1:
                    daoImplement.saveCar(options.addCar());
                    break;

                case 2:
                    daoImplement.editCar(options.editCar());
                    break ;

                case 3:
                    daoImplement.removeCar(options.carIdToDelete());
                    break;

                case 4:
                    daoImplement.getCars();
                    break;

                case 5:
                    break top;
                default:
                    System.out.println("Brak polecenia");
                    break;

            }

        }
        scanner.close();
    }
}
