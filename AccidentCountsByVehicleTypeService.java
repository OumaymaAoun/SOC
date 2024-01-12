package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/accidentCountsByVehicleType")
public class AccidentCountsByVehicleTypeService {

    private static final Map<String, Map<String, Integer>> accidentsByVehicleType = new HashMap<>();
    private static int totalAccidents = 0;
    private static int totalTues = 0;
    private static int totalBlesses = 0;

    static {
        Map<String, Integer> train = new HashMap<>();
        train.put("Accidents", 18);
        train.put("Tues", 8);
        train.put("Blesses", 20);

        Map<String, Integer> bus = new HashMap<>();
        bus.put("Accidents", 99);
        bus.put("Tues", 30);
        bus.put("Blesses", 234);

        Map<String, Integer> metro = new HashMap<>();
        metro.put("Accidents", 1);
        metro.put("Tues", 0);
        metro.put("Blesses", 1);

        Map<String, Integer> regularBike = new HashMap<>();
        regularBike.put("Accidents", 136);
        regularBike.put("Tues", 22);
        regularBike.put("Blesses", 121);

        Map<String, Integer> passengerFall = new HashMap<>();
        passengerFall.put("Accidents", 37);
        passengerFall.put("Tues", 3);
        passengerFall.put("Blesses", 34);

        Map<String, Integer> vehicleOverturn = new HashMap<>();
        vehicleOverturn.put("Accidents", 361);
        vehicleOverturn.put("Tues", 150);
        vehicleOverturn.put("Blesses", 609);

        Map<String, Integer> taxi = new HashMap<>();
        taxi.put("Accidents", 520);
        taxi.put("Tues", 124);
        taxi.put("Blesses", 1032);

        Map<String, Integer> motorcycle = new HashMap<>();
        motorcycle.put("Accidents", 2151);
        motorcycle.put("Tues", 384);
        motorcycle.put("Blesses", 2529);

        Map<String, Integer> lightTruck = new HashMap<>();
        lightTruck.put("Accidents", 1284);
        lightTruck.put("Tues", 371);
        lightTruck.put("Blesses", 1886);

        Map<String, Integer> barrierCollision = new HashMap<>();
        barrierCollision.put("Accidents", 254);
        barrierCollision.put("Tues", 115);
        barrierCollision.put("Blesses", 439);

        Map<String, Integer> pedestrian = new HashMap<>();
        pedestrian.put("Accidents", 1718);
        pedestrian.put("Tues", 302);
        pedestrian.put("Blesses", 1691);

        Map<String, Integer> constructionMachine = new HashMap<>();
        constructionMachine.put("Accidents", 11);
        constructionMachine.put("Tues", 2);
        constructionMachine.put("Blesses", 15);

        Map<String, Integer> lightCar = new HashMap<>();
        lightCar.put("Accidents", 2962);
        lightCar.put("Tues", 502);
        lightCar.put("Blesses", 4350);

        Map<String, Integer> heavyTruck = new HashMap<>();
        heavyTruck.put("Accidents", 216);
        heavyTruck.put("Tues", 120);
        heavyTruck.put("Blesses", 318);

        Map<String, Integer> tractor = new HashMap<>();
        tractor.put("Accidents", 36);
        tractor.put("Tues", 13);
        tractor.put("Blesses", 62);

        accidentsByVehicleType.put("القطار", train);
        accidentsByVehicleType.put("حافلة النقل", bus);
        accidentsByVehicleType.put("المترو", metro);
        accidentsByVehicleType.put("الدراجة العادية", regularBike);
        accidentsByVehicleType.put("سقوط راكب", passengerFall);
        accidentsByVehicleType.put("إنقلاب وسيلة", vehicleOverturn);
        accidentsByVehicleType.put("سيارة أجرة", taxi);
        accidentsByVehicleType.put("الدراجة النارية", motorcycle);
        accidentsByVehicleType.put("الشاحنة الخفيفة", lightTruck);
        accidentsByVehicleType.put("إصطدام بحاجز", barrierCollision);
        accidentsByVehicleType.put("المترجل", pedestrian);
        accidentsByVehicleType.put("آلة الأشغال", constructionMachine);
        accidentsByVehicleType.put("السيارة الخفيفة", lightCar);
        accidentsByVehicleType.put("الشاحنة الثقيلة", heavyTruck);
        accidentsByVehicleType.put("الجرار", tractor);


        // Calculez les totaux pour tous les types de véhicules
        for (Map<String, Integer> vehicleData : accidentsByVehicleType.values()) {
            totalAccidents += vehicleData.getOrDefault("Accidents", 0);
            totalTues += vehicleData.getOrDefault("Tues", 0);
            totalBlesses += vehicleData.getOrDefault("Blesses", 0);
        }
    }

    @GetMapping("/getCounts")
    public Map<String, Map<String, Integer>> getAccidentCountsByVehicleType() {
        return accidentsByVehicleType;
    }

    @GetMapping("/getCounts/{vehicleType}")
    public Map<String, Integer> getAccidentCountsForVehicleType(@PathVariable(required = false) String vehicleType) {
        if (vehicleType != null) {
            return accidentsByVehicleType.getOrDefault(vehicleType, new HashMap<>());
        } else {
            return new HashMap<>();
        }
    }

    @GetMapping("/getTotal")
    public Map<String, Integer> getTotalAccidentCounts() {
        Map<String, Integer> totals = new HashMap<>();
        totals.put("TotalAccidents", totalAccidents);
        totals.put("TotalTues", totalTues);
        totals.put("TotalBlesses", totalBlesses);
        return totals;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccidentCountsByVehicleTypeService.class, args);
    }
}

//http://localhost:8080/accidentCountsByVehicleType/getTotal
//http://localhost:8080/accidentCountsByVehicleType/getCounts/المتروالقطار
