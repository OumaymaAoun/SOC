package org.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

@WebService
public class AccidentLocationServiceSOAP {

    private Map<String, Integer> accidentsByLocation;

    public AccidentLocationServiceSOAP() {
        accidentsByLocation = new HashMap<>();
        addAccidentsByLocation("قابس", 199);
        addAccidentsByLocation("سوسة", 213);
        addAccidentsByLocation("منوبة", 121);
        addAccidentsByLocation("قفصة", 278);
        addAccidentsByLocation("الكاف", 132);
        addAccidentsByLocation("زغوان", 89);
        addAccidentsByLocation("سيدي بوزيد", 233);
        addAccidentsByLocation("سليانة", 144);
        addAccidentsByLocation("بنعروس", 340);
        addAccidentsByLocation("صفاقس", 260);
        addAccidentsByLocation("جندوبة", 146);
        addAccidentsByLocation("نابل", 398);
        addAccidentsByLocation("تونس", 800);
        addAccidentsByLocation("القصرين", 206);
        addAccidentsByLocation("المهدية", 408);
        addAccidentsByLocation("المنستير", 157);
        addAccidentsByLocation("توزر", 39);
        addAccidentsByLocation("باجة", 146);
        addAccidentsByLocation("القيروان", 131);
        addAccidentsByLocation("اريانة", 189);
        addAccidentsByLocation("مدنين", 201);
        addAccidentsByLocation("قبلي", 35);
        addAccidentsByLocation("تطاوين", 42);
        addAccidentsByLocation("بنزرت", 160);
    }

    private void addAccidentsByLocation(String location, int accidents) {
        accidentsByLocation.put(location, accidents);
    }

    @WebMethod
    public int getAccidentsByLocation(@WebParam(name = "location") String location) {
        return accidentsByLocation.getOrDefault(location, 0);
    }

    @WebMethod
    public int getTotalTues() {
        return 1092;
    }

    @WebMethod
    public int getTotalBlesses() {
        return 7017;
    }
    public static void main(String[] args) {
        // Créer l'instance de votre service SOAP
        AccidentLocationServiceSOAP service = new AccidentLocationServiceSOAP();

        // Publier le service sur une URL spécifique
        String address = "http://localhost:8080/accidentLocationService";
        Endpoint.publish(address, service);

        // Afficher un message pour indiquer que le service a été publié avec succès
        System.out.println("Service published at: " + address + "?wsdl");
    }
}
//http://localhost:8080/accidentLocationService?wsdl
