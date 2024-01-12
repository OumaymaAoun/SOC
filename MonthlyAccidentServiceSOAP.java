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
public class MonthlyAccidentServiceSOAP {

    private Map<String, AccidentDetails> monthlyAccidentDetailsMap;

    public MonthlyAccidentServiceSOAP() {
        monthlyAccidentDetailsMap = new HashMap<>();

        addMonthlyAccidentDetails("افريل", 526, 102, 732);
        addMonthlyAccidentDetails("فيفري", 444, 69, 642);
        addMonthlyAccidentDetails("مارس", 507, 77, 720);
        addMonthlyAccidentDetails("جويلية", 482, 116, 695);
        addMonthlyAccidentDetails("نوفمبر", 255, 90, 348);
        addMonthlyAccidentDetails("سبتمبر", 390, 103, 586);
        addMonthlyAccidentDetails("جانفي", 597, 130, 741);
        addMonthlyAccidentDetails("جوان", 487, 117, 632);
        addMonthlyAccidentDetails("اوت", 463, 96, 691);

        // Add totals
        addTotalCounts(5067, 1092, 7017);
    }

    private void addMonthlyAccidentDetails(String month, int accidents, int tues, int blesses) {
        monthlyAccidentDetailsMap.put(month, new AccidentDetails(accidents, tues, blesses));
    }

    private void addTotalCounts(int totalAccidents, int totalTues, int totalBlesses) {
        monthlyAccidentDetailsMap.put("Total", new AccidentDetails(totalAccidents, totalTues, totalBlesses));
    }

    @WebMethod
    public AccidentDetails getMonthlyAccidentData(@WebParam(name = "month") String month) {
        return monthlyAccidentDetailsMap.getOrDefault(month, new AccidentDetails(0, 0, 0));
    }

    @WebMethod
    public AccidentDetails getTotalCounts() {
        return monthlyAccidentDetailsMap.get("Total");
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
        MonthlyAccidentServiceSOAP service = new MonthlyAccidentServiceSOAP();

        // Publish the service on a specific URL
        String address = "http://localhost:8080/monthlyAccidentService";

        Endpoint endpoint = Endpoint.publish(address, service);

        // Get the WSDL URL
        String wsdlURL = address + "?wsdl";

        // Display the WSDL URL
        System.out.println("WSDL for MonthlyAccidentServiceSOAP: " + wsdlURL);

        // To stop the service (optional)
        // endpoint.stop();
    }
}


//http://localhost:8080/monthlyAccidentService?wsdl

