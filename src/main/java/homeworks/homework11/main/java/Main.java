import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car("a123me", "Mercedes", "White", 0, 8300000),
                new Car("b873of", "Volga", "Black", 0, 673000),
                new Car("w487mn", "Lexus", "Grey", 76000, 76000),
                new Car("p987hj", "Volga", "Red", 610, 704340),
                new Car("c987ss", "Toyota", "White", 254000, 761000),
                new Car("o983op", "Toyota", "Black", 698000, 740000),
                new Car("p146op", "BMW", "White", 271000, 850000),
                new Car("u893ii", "Toyota", "Purple", 210900, 440000),
                new Car("l097df", "Toyota", "Black", 108000, 780000),
                new Car("y876wd", "Toyota", "Black", 160000, 1000000)
        );

        String colorToFind = "Black";
        int mileageToFind = 0;
        String modelToFind = "Toyota";
        double priceFrom = 700000;
        double priceTo = 800000;

        System.out.println("Автомобили с цветом " + colorToFind + " или пробегом " + mileageToFind + ":");
        cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .forEach(System.out::println);

        long uniqueModels = cars.stream()
                .filter(car -> car.getPrice() >= priceFrom && car.getPrice() <= priceTo)
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("\nКоличество уникальных моделей в ценовом диапазоне от " +
                priceFrom + " до " + priceTo + ": " + uniqueModels);

        System.out.print("\nЦвет автомобиля с минимальной ценой: ");
        cars.stream()
                .min(Comparator.comparingDouble(Car::getPrice))
                .ifPresent(car -> System.out.println(car.getColor()));

        OptionalDouble avgToyota = cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase("Toyota"))
                .mapToDouble(Car::getPrice)
                .average();

        System.out.printf("\nСредняя стоимость модели Toyota: %.2f\n",
                avgToyota.orElse(0));

        OptionalDouble avgVolvo = cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase("Volvo"))
                .mapToDouble(Car::getPrice)
                .average();

        System.out.printf("Средняя стоимость модели Volvo: %.2f\n",
                avgVolvo.orElse(0));
    }
}