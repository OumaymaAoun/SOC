package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/accidentCountsByMonth")
public class AccidentCountsByMonthService {

    private static final Map<String, Map<String, Integer>> accidentsByMonth = new HashMap<>();
    private static int totalAccidents = 0;
    private static int totalTues = 0;
    private static int totalBlesses = 0;

    static {
        Map<String, Integer> may = new HashMap<>();
        may.put("Accidents", 459);
        may.put("Tues", 79);
        may.put("Blesses", 643);

        Map<String, Integer> october = new HashMap<>();
        october.put("Accidents", 457);
        october.put("Tues", 113);
        october.put("Blesses", 587);

        Map<String, Integer> april = new HashMap<>();
        april.put("Accidents", 526);
        april.put("Tues", 102);
        april.put("Blesses", 732);

        Map<String, Integer> february = new HashMap<>();
        february.put("Accidents", 444);
        february.put("Tues", 69);
        february.put("Blesses", 642);

        Map<String, Integer> march = new HashMap<>();
        march.put("Accidents", 507);
        march.put("Tues", 77);
        march.put("Blesses", 720);

        Map<String, Integer> july = new HashMap<>();
        july.put("Accidents", 482);
        july.put("Tues", 116);
        july.put("Blesses", 695);

        Map<String, Integer> november = new HashMap<>();
        november.put("Accidents", 255);
        november.put("Tues", 90);
        november.put("Blesses", 348);

        Map<String, Integer> september = new HashMap<>();
        september.put("Accidents", 390);
        september.put("Tues", 103);
        september.put("Blesses", 586);

        Map<String, Integer> january = new HashMap<>();
        january.put("Accidents", 597);
        january.put("Tues", 130);
        january.put("Blesses", 741);

        Map<String, Integer> june = new HashMap<>();
        june.put("Accidents", 487);
        june.put("Tues", 117);
        june.put("Blesses", 632);

        Map<String, Integer> august = new HashMap<>();
        august.put("Accidents", 463);
        august.put("Tues", 96);
        august.put("Blesses", 691);

// Initialisez les autres mois de la même manière...

        accidentsByMonth.put("ماي", may);
        accidentsByMonth.put("اكتوبر", october);
        accidentsByMonth.put("افريل", april);
        accidentsByMonth.put("فيفري", february);
        accidentsByMonth.put("مارس", march);
        accidentsByMonth.put("جويلية", july);
        accidentsByMonth.put("نوفمبر", november);
        accidentsByMonth.put("سبتمبر", september);
        accidentsByMonth.put("جانفي", january);
        accidentsByMonth.put("جوان", june);
        accidentsByMonth.put("اوت", august);


        for (Map<String, Integer> monthData : accidentsByMonth.values()) {
            totalAccidents += monthData.getOrDefault("Accidents", 0);
            totalTues += monthData.getOrDefault("Tues", 0);
            totalBlesses += monthData.getOrDefault("Blesses", 0);
        }
    }

    @GetMapping("/getCounts")
    public Map<String, Map<String, Integer>> getAccidentCountsByMonth() {
        return accidentsByMonth;
    }

    @GetMapping("/getCounts/{month}")
    public Map<String, Integer> getAccidentCountsForMonth(@PathVariable(required = false) String month) {
        if (month != null) {
            return accidentsByMonth.getOrDefault(month, new HashMap<>());
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
        SpringApplication.run(AccidentCountsByMonthService.class, args);
    }
}


//http://localhost:8080/accidentCountsByMonth/getTotal
//http://localhost:8080/accidentCountsByMonth/getCounts/{month}
//http://localhost:8080//accidentCountsByMonth/getCounts/جوان