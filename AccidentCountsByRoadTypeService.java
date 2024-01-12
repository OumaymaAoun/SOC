package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/accidentCountsByRoadType")
public class AccidentCountsByRoadTypeService {

    private Map<String, Map<String, Integer>> accidentCountsByRoadType = new HashMap<>();

    public AccidentCountsByRoadTypeService() {
        // Ajout des données pour chaque type de route
        addRoadTypeData("شارع", 1581, 144, 1950);
        addRoadTypeData("نهج", 442, 28, 501);
        addRoadTypeData("مفترق", 307, 37, 423);
        addRoadTypeData("جهوية", 418, 174, 687);
        addRoadTypeData("سيارة", 111, 51, 236);
        addRoadTypeData("داخل تجمع سكني", 1078, 167, 1399);
        addRoadTypeData("سريعة", 50, 11, 64);
        addRoadTypeData("وطنية", 536, 281, 978);
        addRoadTypeData("محلية", 479, 176, 696);
        addRoadTypeData("حزامية", 65, 23, 83);


        // Calcul des totaux
        calculateTotals(5067, 1092, 7017);
    }

    private void addRoadTypeData(String roadType, int accidents, int tues, int blesses) {
        Map<String, Integer> roadTypeData = new HashMap<>();
        roadTypeData.put("Accidents", accidents);
        roadTypeData.put("Tues", tues);
        roadTypeData.put("Blesses", blesses);
        accidentCountsByRoadType.put(roadType, roadTypeData);
    }

    private void calculateTotals(int totalAccidents, int totalTues, int totalBlesses) {
        Map<String, Integer> totals = new HashMap<>();
        totals.put("Total Accidents", totalAccidents);
        totals.put("Total Tues", totalTues);
        totals.put("Total Blesses", totalBlesses);
        accidentCountsByRoadType.put("Total", totals);
    }

    @GetMapping("/getCounts")
    public Object getAccidentCountsByRoadType(@RequestParam(required = false) String roadType) {
        if (roadType != null && !roadType.isEmpty()) {
            Map<String, Integer> count = accidentCountsByRoadType.get(roadType);
            if (count != null) {
                return "Accidents pour " + roadType + " : " + count.get("Accidents") +
                        ", Blesses : " + count.get("Blesses") +
                        ", Tues : " + count.get("Tues");
            } else {
                return "Type de route non trouvé";
            }
        } else {
            return accidentCountsByRoadType;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AccidentCountsByRoadTypeService.class, args);
    }
}


//http://localhost:8080/accidentCountsByRoadType/getCounts
//http://localhost:8080/accidentCountsByRoadType/getCounts?roadType=شارع