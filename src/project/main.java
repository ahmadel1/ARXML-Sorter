package project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class main {


	public static void main(String[] args) {
		
        if(args.length == 0) return;
        String InputFileName = GetParentFolder() + args[0]; 
        System.out.println("file Location:  " + InputFileName);
		String userInput = InputFileName;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			String arxmlFile = InputOutputHandling.input(userInput);
			
			File file = new File(arxmlFile);
		    if (!file.exists()) {
		        throw new FileNotFound("File does not Exist");
		    }
		    if (file.length() == 0) {
		        throw new EmptyAutosarFileException("File is empty.");
		    }
		    
			Document doc =builder.parse(arxmlFile);
			int isEmpty = doc.getDocumentElement().getChildNodes().getLength();
			if(isEmpty == 0) throw new EmptyAutosarFileException("Not valid arxml file");
			NodeList containers = doc.getElementsByTagName("CONTAINER");
			
	     
			ArrayList<Element>nodeArrayList = new ArrayList<Element>();
			for(int i = 0; i<containers.getLength(); i++) {
				nodeArrayList.add((Element)(containers.item(i)));
			}
			
			Collections.sort(nodeArrayList, new NodeComparator());	
			
			String newName = InputOutputHandling.generateNewName(arxmlFile);
			InputOutputHandling.newFile(nodeArrayList, doc, newName);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(NotVaildAutosarFileException e) {
			System.out.println(e.getMessage());
		}catch(EmptyAutosarFileException e){
			System.out.println(e.getMessage());
		}catch(FileNotFound e) {
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
	
	static String GetParentFolder() {
		String currentDir = System.getProperty("user.dir");
		File currentDirFile = new File(currentDir);
		String parentFolder = currentDirFile.getParent() + "\\";
		return parentFolder;
	}
	
}
