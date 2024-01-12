package org.example;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Traitement1 {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Oumayma Aoun\\Desktop\\SOC\\cause2023.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            Set<String> labellesUniques = new HashSet<>(); // For storing unique labelles
            int totalAccidents = 0;
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

                    // Remove duplicates (store only unique labelles)
                    if (!labellesUniques.contains(labelle)) {
                        labellesUniques.add(labelle);

                        // Normalize format (example: convert accidents to integer)
                        int accidentsValue = Integer.parseInt(accidents);

                        // Handle missing data (example: mark missing data)
                        if (tues.isEmpty()) {
                            tues = "Missing data";
                        }

                        // Filter out irrelevant data (example: ignore data with accidents < 5)
                        if (accidentsValue >= 5) {
                            // Additional aggregation
                            totalAccidents += accidentsValue;
                            totalTues += Integer.parseInt(tues);
                            totalBlesses += Integer.parseInt(blesses);

                            // Print cleaned data
                            System.out.println("Labelle: " + labelle);
                            System.out.println("Accidents: " + accidentsValue);
                            System.out.println("Tues: " + tues);
                            System.out.println("Blesses: " + blesses);
                            System.out.println("------------------------------");
                        }
                    }
                }
            }

            // Additional data analysis
            System.out.println("Total Accidents: " + totalAccidents);
            System.out.println("Total Tues: " + totalTues);
            System.out.println("Total Blesses: " + totalBlesses);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
