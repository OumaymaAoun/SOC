package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/accidentReasons")
public class AccidentReasonsService {

    private static final Map<String, Map<String, Integer>> accidentReasons = new HashMap<>();

    // Initialisation des données pour chaque raison d'accident
    static {
        Map<String, Integer> accident1 = new HashMap<>();
        accident1.put("Accidents", 10);
        accident1.put("Tues", 6);
        accident1.put("Blesses", 8);
        accidentReasons.put("عبور السكة", accident1);

        Map<String, Integer> accident2 = new HashMap<>();
        accident2.put("Accidents", 12);
        accident2.put("Tues", 4);
        accident2.put("Blesses", 8);
        accidentReasons.put("اهمال حدث", accident2);

        Map<String, Integer> accident3 = new HashMap<>();
        accident3.put("Accidents", 28);
        accident3.put("Tues", 5);
        accident3.put("Blesses", 44);
        accidentReasons.put("اختراق الضوء الاحمر", accident3);
        // Raison 4
        Map<String, Integer> accident4 = new HashMap<>();
        accident4.put("Accidents", 28);
        accident4.put("Tues", 2);
        accident4.put("Blesses", 26);
        accidentReasons.put("محاولة صعود او نزول", accident4);

// Raison 5
        Map<String, Integer> accident5 = new HashMap<>();
        accident5.put("Accidents", 31);
        accident5.put("Tues", 10);
        accident5.put("Blesses", 63);
        accidentReasons.put("انفلاق عجلة", accident5);

// Raison 6
        Map<String, Integer> accident6 = new HashMap<>();
        accident6.put("Accidents", 36);
        accident6.put("Tues", 10);
        accident6.put("Blesses", 48);
        accidentReasons.put("السياقة بدون رخصة", accident6);

// Raison 7
        Map<String, Integer> accident7 = new HashMap<>();
        accident7.put("Accidents", 39);
        accident7.put("Tues", 15);
        accident7.put("Blesses", 42);
        accidentReasons.put("السير ليلا بدون انارة", accident7);

// Raison 8
        Map<String, Integer> accident8 = new HashMap<>();
        accident8.put("Accidents", 42);
        accident8.put("Tues", 3);
        accident8.put("Blesses", 49);
        accidentReasons.put("فتح باب", accident8);

// Raison 9
        Map<String, Integer> accident9 = new HashMap<>();
        accident9.put("Accidents", 52);
        accident9.put("Tues", 9);
        accident9.put("Blesses", 90);
        accidentReasons.put("السير في اتجاه محجر", accident9);

// Raison 10
        Map<String, Integer> accident10 = new HashMap<>();
        accident10.put("Accidents", 77);
        accident10.put("Tues", 16);
        accident10.put("Blesses", 133);
        accidentReasons.put("السياقة في حالة سكر", accident10);
        // Raison 11
        Map<String, Integer> accident11 = new HashMap<>();
        accident11.put("Accidents", 86);
        accident11.put("Tues", 7);
        accident11.put("Blesses", 108);
        accidentReasons.put("السير الى الوراء", accident11);

// Raison 12
        Map<String, Integer> accident12 = new HashMap<>();
        accident12.put("Accidents", 91);
        accident12.put("Tues", 33);
        accident12.put("Blesses", 167);
        accidentReasons.put("المجاوزة الممنوعة", accident12);

// Raison 13
        Map<String, Integer> accident13 = new HashMap<>();
        accident13.put("Accidents", 136);
        accident13.put("Tues", 12);
        accident13.put("Blesses", 197);
        accidentReasons.put("عدم احترام علامة قف", accident13);

// Raison 14
        Map<String, Integer> accident14 = new HashMap<>();
        accident14.put("Accidents", 201);
        accident14.put("Tues", 30);
        accident14.put("Blesses", 321);
        accidentReasons.put("تغيير اتجاه", accident14);

// Raison 15
        Map<String, Integer> accident15 = new HashMap<>();
        accident15.put("Accidents", 282);
        accident15.put("Tues", 137);
        accident15.put("Blesses", 548);
        accidentReasons.put("عدم ملازمة اليمين", accident15);

// Raison 16
        Map<String, Integer> accident16 = new HashMap<>();
        accident16.put("Accidents", 294);
        accident16.put("Tues", 58);
        accident16.put("Blesses", 522);
        accidentReasons.put("المداهمة", accident16);

// Raison 17
        Map<String, Integer> accident17 = new HashMap<>();
        accident17.put("Accidents", 338);
        accident17.put("Tues", 76);
        accident17.put("Blesses", 293);
        accidentReasons.put("شق الطريق", accident17);

// Raison 18
        Map<String, Integer> accident18 = new HashMap<>();
        accident18.put("Accidents", 409);
        accident18.put("Tues", 42);
        accident18.put("Blesses", 558);
        accidentReasons.put("عدم احترام الاولوية", accident18);

// Raison 19
        Map<String, Integer> accident19 = new HashMap<>();
        accident19.put("Accidents", 820);
        accident19.put("Tues", 354);
        accident19.put("Blesses", 1352);
        accidentReasons.put("السرعة", accident19);



        Map<String, Integer> total = new HashMap<>();
        total.put("Total Accidents", 5065);
        total.put("Total Tues", 1092);
        total.put("Total Blesses", 7015);
        accidentReasons.put("Total", total);
    }

    // Endpoint pour obtenir les détails spécifiques d'une raison d'accident
    @GetMapping("/getAccidentReason")
    public Object getAccidentReason(@RequestParam(required = false) String reason) {
        if (reason != null && !reason.isEmpty()) {
            Map<String, Integer> specificReason = accidentReasons.get(reason);
            if (specificReason != null) {
                Map<String, Object> reasonDetails = new HashMap<>();
                reasonDetails.put("Raison", reason);
                reasonDetails.putAll(specificReason);
                return reasonDetails;
            } else {
                return "Raison d'accident non trouvée";
            }
        } else {
            return accidentReasons.get("Total");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AccidentReasonsService.class, args);
    }
}
//http://localhost:8080/accidentReasons/getAccidentReason
//http://localhost:8080/accidentReasons/getAccidentReason?reason=عبور السكة