package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/accidentCountsByLocation")
public class AccidentCountsByLocationService {

    private static final Map<String, Integer> accidentCountsByLocation = new HashMap<>();
    private static final int TOTAL_DEATHS = 1092;
    private static final int TOTAL_INJURIES = 7017;

    // Initialisation des données factices pour chaque emplacement
    static {
        accidentCountsByLocation.put("قابس", 199);
        accidentCountsByLocation.put("سوسة", 213);
        accidentCountsByLocation.put("منوبة", 121);
        accidentCountsByLocation.put("قفصة", 278);
        accidentCountsByLocation.put("الكاف", 132);
        accidentCountsByLocation.put("زغوان", 89);
        accidentCountsByLocation.put("سيدي بوزيد", 233);
        accidentCountsByLocation.put("سليانة", 144);
        accidentCountsByLocation.put("بنعروس", 340);
        accidentCountsByLocation.put("صفاقس", 260);
        accidentCountsByLocation.put("جندوبة", 146);
        accidentCountsByLocation.put("نابل", 398);
        accidentCountsByLocation.put("تونس", 800);
        accidentCountsByLocation.put("القصرين", 206);
        accidentCountsByLocation.put("المهدية", 408);
        accidentCountsByLocation.put("المنستير", 157);
        accidentCountsByLocation.put("توزر", 39);
        accidentCountsByLocation.put("باجة", 146);
        accidentCountsByLocation.put("القيروان", 131);
        accidentCountsByLocation.put("اريانة", 189);
        accidentCountsByLocation.put("مدنين", 201);
        accidentCountsByLocation.put("قبلي", 35);
        accidentCountsByLocation.put("تطاوين", 42);
        accidentCountsByLocation.put("بنزرت", 160);

        // Totals
        accidentCountsByLocation.put("Total Tues", TOTAL_DEATHS);
        accidentCountsByLocation.put("Total Blesses", TOTAL_INJURIES);
    }

    // Endpoint pour obtenir les données d'accidents par emplacement ou les totaux
    @GetMapping("/getAccidentCounts")
    public Object getAccidentCountsByLocation(@RequestParam(required = false) String location) {
        if (location != null && !location.isEmpty()) {
            Integer count = accidentCountsByLocation.get(location);
            if (count != null) {
                return "Accidents pour " + location + " : " + count;
            } else {
                return "Emplacement non trouvé";
            }
        } else {
            return accidentCountsByLocation;
        }
    }

    @GetMapping("/getTotalDeaths")
    public Integer getTotalDeaths() {
        // Renvoyer le total des décès
        return TOTAL_DEATHS; // Remplacez totalDeaths par la variable contenant le total des décès
    }

    @GetMapping("/getTotalInjuries")
    public Integer getTotalInjuries() {
        // Renvoyer le total des blessures
        return TOTAL_INJURIES; // Remplacez totalInjuries par la variable contenant le total des blessures
    }

    public static void main(String[] args) {
        SpringApplication.run(AccidentCountsByLocationService.class, args);
    }}


//http://localhost:8080/accidentCountsByLocation/getAccidentCounts?location=اريانة
//http://localhost:8080/accidentCountsByLocation/getAccidentCounts
//http://localhost:8080/accidentCountsByLocation/getTotalDeaths
//http://localhost:8080/accidentCountsByLocation/getTotalInjuries

