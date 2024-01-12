package org.example;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Traitement5 {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Oumayma Aoun\\Desktop\\SOC\\mois2023.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            Map<String, Map<String, Integer>> accidentsByMonth = new HashMap<>();
            int totalAccidents = 0;
            int totalTues = 0;
            int totalBlesses = 0;

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    String month = element.getElementsByTagName("labelle").item(0).getTextContent();
                    String accidents = element.getElementsByTagName("accidents").item(0).getTextContent();
                    String tues = element.getElementsByTagName("tues").item(0).getTextContent();
                    String blesses = element.getElementsByTagName("blesses").item(0).getTextContent();

                    int accidentsCount = Integer.parseInt(accidents);
                    int tuesCount = Integer.parseInt(tues);
                    int blessesCount = Integer.parseInt(blesses);

                    totalAccidents += accidentsCount;
                    totalTues += tuesCount;
                    totalBlesses += blessesCount;

                    Map<String, Integer> data = new HashMap<>();
                    data.put("Accidents", accidentsCount);
                    data.put("Tues", tuesCount);
                    data.put("Blesses", blessesCount);

                    accidentsByMonth.put(month, data);
                }
            }

            // Display aggregated accident counts by month
            for (Map.Entry<String, Map<String, Integer>> entry : accidentsByMonth.entrySet()) {
                System.out.println("Month: " + entry.getKey());
                Map<String, Integer> data = entry.getValue();
                for (Map.Entry<String, Integer> innerEntry : data.entrySet()) {
                    System.out.println(innerEntry.getKey() + ": " + innerEntry.getValue());
                }
                System.out.println("---------------");
            }

            // Display total accidents, tues, and blesses
            System.out.println("Total Accidents: " + totalAccidents);
            System.out.println("Total Tues: " + totalTues);
            System.out.println("Total Blesses: " + totalBlesses);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
