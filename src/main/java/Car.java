

class Car {


    private int id;
    private int carVin;
    private String carBrand;
    private String carModel;
    private String carDateOfProduction;
    private String carColour;


    Car(int carVin, String carBrand, String carModel, String carDateOfProduction, String carColour){

        this.carVin = carVin;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carDateOfProduction = carDateOfProduction;
        this.carColour = carColour;

    }

    Car(int id, int carVin, String carBrand, String carModel, String carDateOfProduction, String carColour){

        this.id = id;
        this.carVin = carVin;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carDateOfProduction = carDateOfProduction;
        this.carColour = carColour;

    }

    int getId(){
        return id;
    }

    int getCarVin(){
        return carVin;
    }

    void setCarVin(int carVin){
        this.carVin = carVin;
    }

    String getCarBrand(){
        return carBrand;
    }
    void setCarBrand(String carBrand){
        this.carBrand = carBrand;
    }

    String getCarModel(){
        return carModel;
    }
    void setCarModel(String carModel){
        this.carModel = carModel;
    }

    String getCarDateOfProduction(){
        return carDateOfProduction;
    }

    void setCarDateOfProduction(String carDateOfProduction){
        this.carDateOfProduction = carDateOfProduction;
    }

    String getCarColour(){
        return carColour;
    }

    void setCarColour(String carColour){
        this.carColour = carColour;
    }

    String getCarInfo() {
        return String.format("%d: numer VIN: %d, %s %s, data produkcji: %s, %s",
              id, carVin, carBrand, carModel, carDateOfProduction, carColour);
    }


}
