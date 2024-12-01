import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlUpdater {
    public static void main(String[] args) {
        String xmlFilePath = "pom.xml"; // Replace with your actual file path

        try {
            // Load the XML file
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Find the node to update (e.g., <suiteXmlFile>)
            NodeList nodeList = doc.getElementsByTagName("suiteXmlFile");
            Node node = nodeList.item(0); // Assuming there's only one such node

            // Get user input for the new file path
            System.out.print("Enter the new file path: ");
            String newContent = "testxml/"+args[0]+".xml";

            // Update the node content
            node.setTextContent(newContent);

            // Write the modified content back to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            System.out.println("XML file updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

