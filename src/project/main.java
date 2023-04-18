package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class main {


	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			String arxmlFile = input();
			Document doc =builder.parse(arxmlFile);
			NodeList containers = doc.getElementsByTagName("CONTAINER");
	     
			ArrayList<Node>nodeArrayList = new ArrayList<Node>();
			for(int i = 0; i<containers.getLength(); i++) {
				nodeArrayList.add(containers.item(i));
			}
			
			Collections.sort(nodeArrayList, new NodeComparator());	
			for (int i = 0; i < nodeArrayList.size(); i++) {
                System.out.println(nodeArrayList.get(i).getTextContent());
            }

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(NotVaildAutosarFileException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static class NodeComparator implements Comparator<Node> {
	        
	        @Override
	        public int compare(Node node1, Node node2) {
	            String title1 = getNodeValue(node1);
	            String title2 = getNodeValue(node2);
	            return title1.compareTo(title2);
	        }
	        
	        private String getNodeValue(Node node) {
	            NodeList nodeList = node.getChildNodes();
	            for (int i = 0; i < nodeList.getLength(); i++) {
	                Node childNode = nodeList.item(i);
	                if (childNode.getNodeName().equals("SHORT-NAME")) {
	                    return childNode.getTextContent();
	                }
	            }
	            return "";
	        }
	}
	
	public static String input() throws NotVaildAutosarFileException{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter arxml file Name: ");
		String str = sc.nextLine();
		if(!str.endsWith(".arxml"))
			throw new NotVaildAutosarFileException("Not valid arxml file");
		return str;
	}
	
	

}


class NotVaildAutosarFileException extends Exception {
    public NotVaildAutosarFileException(String s)
    {
        super(s);
    }
}




