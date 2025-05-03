public class AppCar {
    public static void main(String[] args) {
        Car perfCar = new PerformanceCar("BMW", "M3", 2019, 300, 5, 100, 200);
        Car showCar = new ShowCar("Audi", "TT", 2020, 250, 6, 90, 180);

        Race dragRace = new DragRace(400, "Downtown", 10000);
        dragRace.addParticipant(perfCar);

        Garage garage = new Garage();
        garage.parkCar(showCar);
        System.out.println(perfCar);
        System.out.println(showCar);
    }
}
