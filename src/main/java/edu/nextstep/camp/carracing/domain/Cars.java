package edu.nextstep.camp.carracing.domain;

import edu.nextstep.camp.carracing.util.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> values;
    private final NumberGenerator generator;

    public Cars(List<Car> cars, NumberGenerator generator) {
        this.values = cars;
        this.generator = generator;
    }

    public static Cars fromNames(List<String> carNames, NumberGenerator generator) {
        List<Car> cars = new ArrayList<>();
        for (String carName : carNames) {
            cars.add(new Car(carName));
        }
        return new Cars(cars, generator);
    }

    public void moveCars() {
        for (Car car : this.values) {
            car.move(generator.generateNumber());
        }
    }

    private int getMaxPosition() {
        int maxPosition = 0;
        for (Car car : this.values) {
            maxPosition = car.getMaxValue(maxPosition);
        }
        return maxPosition;
    }

    public List<String> getWinners() {
        int winnerPosition = getMaxPosition();
        return this.values.stream()
                .filter(car -> car.isMaxPosition(winnerPosition))
                .map(Car::getNameValue)
                .collect(Collectors.toList());
    }

    public List<Car> getValues() {
        return this.values;
    }
}
