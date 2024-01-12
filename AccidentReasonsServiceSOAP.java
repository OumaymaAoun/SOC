package org.example;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService
public class AccidentReasonsServiceSOAP {

    private Map<String, AccidentDetails> accidentDetailsMap;

    public AccidentReasonsServiceSOAP() {
        accidentDetailsMap = new HashMap<>();

        // Ajout des données d'accident avec leurs libellés
        addAccidentDetails("عبور السكة", 10, 6, 8);
        addAccidentDetails("اهمال حدث", 12, 4, 8);
        addAccidentDetails("اختراق الضوء الاحمر", 28, 5, 44);
        addAccidentDetails("محاولة صعود او نزول", 28, 2, 26);
        addAccidentDetails("انفلاق عجلة", 31, 10, 63);
        addAccidentDetails("السياقة بدون رخصة", 36, 10, 48);
        addAccidentDetails("السير ليلا بدون انارة", 39, 15, 42);
        addAccidentDetails("فتح باب", 42, 3, 49);
        addAccidentDetails("السير في اتجاه محجر", 52, 9, 90);
        addAccidentDetails("السياقة في حالة سكر", 77, 16, 133);
        addAccidentDetails("السير الى الوراء", 86, 7, 108);
        addAccidentDetails("المجاوزة الممنوعة", 91, 33, 167);
        addAccidentDetails("عدم احترام علامة قف", 136, 12, 197);
        addAccidentDetails("تغيير اتجاه", 201, 30, 321);
        addAccidentDetails("عدم ملازمة اليمين", 282, 137, 548);
        addAccidentDetails("المداهمة", 294, 58, 522);
        addAccidentDetails("شق الطريق", 338, 76, 293);
        addAccidentDetails("عدم احترام الاولوية", 409, 42, 558);
        addAccidentDetails("السرعة", 820, 354, 1352);
        addAccidentDetails("السهو وعدم الانتباه", 2053, 263, 2438);

        // Ajouter les données totales
        addTotalCounts(5065, 1092, 7015);
    }

    private void addAccidentDetails(String labelle, int accidents, int tues, int blesses) {
        accidentDetailsMap.put(labelle, new AccidentDetails(accidents, tues, blesses));
    }

    private void addTotalCounts(int totalAccidents, int totalTues, int totalBlesses) {
        accidentDetailsMap.put("Total", new AccidentDetails(totalAccidents, totalTues, totalBlesses));
    }

    @WebMethod
    public AccidentDetails getAccidentData(@WebParam(name = "labelle") String labelle) {
        return accidentDetailsMap.getOrDefault(labelle, new AccidentDetails(0, 0, 0));
    }

    @WebMethod
    public AccidentDetails getTotalCounts() {
        return accidentDetailsMap.get("Total");
    }

    // Classe pour représenter les détails d'un accident
    private static class AccidentDetails {
        private int accidents;
        private int tues;
        private int blesses;

        public AccidentDetails(int accidents, int tues, int blesses) {
            this.accidents = accidents;
            this.tues = tues;
            this.blesses = blesses;
        }

        public int getAccidents() {
            return accidents;
        }

        public void setAccidents(int accidents) {
            this.accidents = accidents;
        }

        public int getTues() {
            return tues;
        }

        public void setTues(int tues) {
            this.tues = tues;
        }

        public int getBlesses() {
            return blesses;
        }

        public void setBlesses(int blesses) {
            this.blesses = blesses;
        }
    }

}
//http://localhost:8080/accidentReasonsService?wsdl