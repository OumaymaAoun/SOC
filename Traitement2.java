package org.example;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Traitement2 {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Oumayma Aoun\\Desktop\\SOC\\gouvernorat2023.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            Map<String, Integer> accidentsByLocation = new HashMap<>();
            int totalTues = 0;
            int totalBlesses = 0;

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    String labelle = element.getElementsByTagName("labelle").item(0).getTextContent();
                    String accidents = element.getElementsByTagName("accidents").item(0).getTextContent();
                    String tues = element.getElementsByTagName("tues").item(0).getTextContent();
                    String blesses = element.getElementsByTagName("blesses").item(0).getTextContent();

                    // Update accidents count by location
                    accidentsByLocation.put(labelle, Integer.parseInt(accidents) + accidentsByLocation.getOrDefault(labelle, 0));

                    // Total Tue's and Blesses count
                    totalTues += Integer.parseInt(tues);
                    totalBlesses += Integer.parseInt(blesses);
                }
            }

            // Display aggregated accident counts by location
            for (Map.Entry<String, Integer> entry : accidentsByLocation.entrySet()) {
                System.out.println("Location: " + entry.getKey() + ", Accidents: " + entry.getValue());
            }

            // Display total Tue's and Blesses count
            System.out.println("Total Tues: " + totalTues);
            System.out.println("Total Blesses: " + totalBlesses);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
