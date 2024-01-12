package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/accidentCountsByHour")
public class AccidentCountsByHourService {

    private static final Map<String, Map<String, Integer>> accidentsByHour = new HashMap<>();
    private static int totalAccidents = 0;
    private static int totalTues = 0;
    private static int totalBlesses = 0;

    static {
        Map<String, Integer> hour0200 = new HashMap<>();
        hour0200.put("Accidents", 158);
        hour0200.put("Tues", 55);
        hour0200.put("Blesses", 248);

        Map<String, Integer> hour1614 = new HashMap<>();
        hour1614.put("Accidents", 547);
        hour1614.put("Tues", 116);
        hour1614.put("Blesses", 785);

        Map<String, Integer> hour1412 = new HashMap<>();
        hour1412.put("Accidents", 607);
        hour1412.put("Tues", 102);
        hour1412.put("Blesses", 853);

        Map<String, Integer> hour2018 = new HashMap<>();
        hour2018.put("Accidents", 578);
        hour2018.put("Tues", 127);
        hour2018.put("Blesses", 838);

        Map<String, Integer> hour1210 = new HashMap<>();
        hour1210.put("Accidents", 588);
        hour1210.put("Tues", 96);
        hour1210.put("Blesses", 787);

        Map<String, Integer> hour2220 = new HashMap<>();
        hour2220.put("Accidents", 485);
        hour2220.put("Tues", 114);
        hour2220.put("Blesses", 661);

        Map<String, Integer> hour2422 = new HashMap<>();
        hour2422.put("Accidents", 238);
        hour2422.put("Tues", 52);
        hour2422.put("Blesses", 353);

        Map<String, Integer> hour0806 = new HashMap<>();
        hour0806.put("Accidents", 383);
        hour0806.put("Tues", 105);
        hour0806.put("Blesses", 512);

        Map<String, Integer> hour0604 = new HashMap<>();
        hour0604.put("Accidents", 168);
        hour0604.put("Tues", 75);
        hour0604.put("Blesses", 260);

        Map<String, Integer> hour0418 = new HashMap<>();
        hour0418.put("Accidents", 114);
        hour0418.put("Tues", 58);
        hour0418.put("Blesses", 158);

        Map<String, Integer> hour1816 = new HashMap<>();
        hour1816.put("Accidents", 687);
        hour1816.put("Tues", 118);
        hour1816.put("Blesses", 902);

        Map<String, Integer> hour1008 = new HashMap<>();
        hour1008.put("Accidents", 514);
        hour1008.put("Tues", 74);
        hour1008.put("Blesses", 660);



        accidentsByHour.put("02-00", hour0200);
        accidentsByHour.put("16-14", hour1614);
        accidentsByHour.put("14-12", hour1412);
        accidentsByHour.put("20-18", hour2018);
        accidentsByHour.put("12-10", hour1210);
        accidentsByHour.put("22-20", hour2220);
        accidentsByHour.put("24-22", hour2422);
        accidentsByHour.put("08-06", hour0806);
        accidentsByHour.put("06-04", hour0604);
        accidentsByHour.put("04-02", hour0418);
        accidentsByHour.put("18-16", hour1816);
        accidentsByHour.put("10-08", hour1008);


        // Calculate totals
        for (Map<String, Integer> hourData : accidentsByHour.values()) {
            totalAccidents += hourData.getOrDefault("Accidents", 0);
            totalTues += hourData.getOrDefault("Tues", 0);
            totalBlesses += hourData.getOrDefault("Blesses", 0);
        }
    }

    @GetMapping("/getCounts")
    public Map<String, Map<String, Integer>> getAccidentCountsByHour() {
        return accidentsByHour;
    }

    @GetMapping("/getCounts/{hour}")
    public Map<String, Integer> getAccidentCountsForHour(@PathVariable(required = false) String hour) {
        if (hour != null) {
            return accidentsByHour.getOrDefault(hour, new HashMap<>());
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
        SpringApplication.run(AccidentCountsByHourService.class, args);
    }
}

//http://localhost:8080/accidentCountsByHour/getTotal
//http://localhost:8080/accidentCountsByHour/getCounts/02-00
//http://localhost:8080/accidentCountsByHour/getCounts