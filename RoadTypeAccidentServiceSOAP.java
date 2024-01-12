package org.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

@WebService
public class RoadTypeAccidentServiceSOAP {

    private Map<String, AccidentDetails> roadTypeAccidentDetailsMap;

    public RoadTypeAccidentServiceSOAP() {
        roadTypeAccidentDetailsMap = new HashMap<>();

        addRoadTypeAccidentDetails("مفترق", 307, 37, 423);
        addRoadTypeAccidentDetails("جهوية", 418, 174, 687);
        addRoadTypeAccidentDetails("سيارة", 111, 51, 236);
        addRoadTypeAccidentDetails("داخل تجمع سكني", 1078, 167, 1399);
        addRoadTypeAccidentDetails("سريعة", 50, 11, 64);
        addRoadTypeAccidentDetails("وطنية", 536, 281, 978);
        addRoadTypeAccidentDetails("محلية", 479, 176, 696);
        addRoadTypeAccidentDetails("حزامية", 65, 23, 83);

        // Add totals
        addTotalCounts(5067, 1092, 7017);
    }

    private void addRoadTypeAccidentDetails(String roadType, int accidents, int tues, int blesses) {
        roadTypeAccidentDetailsMap.put(roadType, new AccidentDetails(accidents, tues, blesses));
    }

    private void addTotalCounts(int totalAccidents, int totalTues, int totalBlesses) {
        roadTypeAccidentDetailsMap.put("Total", new AccidentDetails(totalAccidents, totalTues, totalBlesses));
    }

    @WebMethod
    public AccidentDetails getRoadTypeAccidentData(@WebParam(name = "roadType") String roadType) {
        return roadTypeAccidentDetailsMap.getOrDefault(roadType, new AccidentDetails(0, 0, 0));
    }

    @WebMethod
    public AccidentDetails getTotalCounts() {
        return roadTypeAccidentDetailsMap.get("Total");
    }

    // AccidentDetails class
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AccidentDetails {
        private int accidents;
        private int tues;
        private int blesses;

        public AccidentDetails() {
            // Default constructor needed for JAXB
        }

        public AccidentDetails(int accidents, int tues, int blesses) {
            this.accidents = accidents;
            this.tues = tues;
            this.blesses = blesses;
        }

        // Getters and Setters
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

    public static void main(String[] args) {
        RoadTypeAccidentServiceSOAP service = new RoadTypeAccidentServiceSOAP();

        // Publish the service on a specific URL
        String address = "http://localhost:8080/roadTypeAccidentService";

        Endpoint endpoint = Endpoint.publish(address, service);

        // Get the WSDL URL
        String wsdlURL = address + "?wsdl";

        // Display the WSDL URL
        System.out.println("WSDL for RoadTypeAccidentServiceSOAP: " + wsdlURL);

        // To stop the service (optional)
        // endpoint.stop();
    }
}

