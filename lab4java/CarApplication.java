public class CarApplication {

    public static void main(String[] args){
        CarModel carModel = new CarModel();
        CarView carView = new CarView("CarSim 1.0", carModel);
        carModel.addListener(carView);
        CarController carController = new CarController(carModel, carView);
    }
}
