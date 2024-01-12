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
public class VehicleAccidentServiceSOAP {

    private Map<String, AccidentDetails> vehicleAccidentDetailsMap;

    public VehicleAccidentServiceSOAP() {
        vehicleAccidentDetailsMap = new HashMap<>();

        addVehicleAccidentDetails("المترو", 1, 0, 1);
        addVehicleAccidentDetails("الدراجة العادية", 136, 22, 121);
        addVehicleAccidentDetails("سقوط راكب", 37, 3, 34);
        addVehicleAccidentDetails("إنقلاب وسيلة", 361, 150, 609);
        addVehicleAccidentDetails("سيارة أجرة", 520, 124, 1032);
        addVehicleAccidentDetails("الدراجة النارية", 2151, 384, 2529);
        addVehicleAccidentDetails("الشاحنة الخفيفة", 1284, 371, 1886);
        addVehicleAccidentDetails("إصطدام بحاجز", 254, 115, 439);
        addVehicleAccidentDetails("المترجل", 1718, 302, 1691);
        addVehicleAccidentDetails("آلة الأشغال", 11, 2, 15);
        addVehicleAccidentDetails("السيارة الخفيفة", 2962, 502, 4350);
        addVehicleAccidentDetails("الشاحنة الثقيلة", 216, 120, 318);
        addVehicleAccidentDetails("الجرار", 36, 13, 62);

        // Add totals
        addTotalCounts(9804, 2146, 13341);
    }

    private void addVehicleAccidentDetails(String vehicleType, int accidents, int tues, int blesses) {
        vehicleAccidentDetailsMap.put(vehicleType, new AccidentDetails(accidents, tues, blesses));
    }

    private void addTotalCounts(int totalAccidents, int totalTues, int totalBlesses) {
        vehicleAccidentDetailsMap.put("Total", new AccidentDetails(totalAccidents, totalTues, totalBlesses));
    }

    @WebMethod
    public AccidentDetails getVehicleAccidentData(@WebParam(name = "vehicleType") String vehicleType) {
        return vehicleAccidentDetailsMap.getOrDefault(vehicleType, new AccidentDetails(0, 0, 0));
    }

    @WebMethod
    public AccidentDetails getTotalCounts() {
        return vehicleAccidentDetailsMap.get("Total");
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
        VehicleAccidentServiceSOAP service = new VehicleAccidentServiceSOAP();

        // Publish the service on a specific URL
        String address = "http://localhost:8080/vehicleAccidentService";

        Endpoint endpoint = Endpoint.publish(address, service);

        // Get the WSDL URL
        String wsdlURL = address + "?wsdl";

        // Display the WSDL URL
        System.out.println("WSDL for VehicleAccidentServiceSOAP: " + wsdlURL);

        // To stop the service (optional)
        // endpoint.stop();
    }
}

//http://localhost:8080/vehicleAccidentService?wsdl
