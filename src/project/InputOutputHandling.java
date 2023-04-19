package project;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class InputOutputHandling {
	public static String input(String in) throws NotVaildAutosarFileException{
		//Scanner sc = new Scanner(System.in);
		//System.out.print("Enter arxml file Name: ");
		String str = in;
		if(!str.endsWith(".arxml"))
			throw new NotVaildAutosarFileException("Not valid arxml file");
		return str;
	}
	
	public static String generateNewName(String oldName) {
		int pos = oldName.indexOf(".arxml");
		return oldName.substring(0,pos) + "_mod.arxml";
	}
	
	public static void newFile(ArrayList<Element> nodes, Document doc, String newName){
		
		Element rootElement = doc.getDocumentElement();
        for (Element container : nodes) {
            rootElement.appendChild((Element)container);
        }

         
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File(newName));
	        transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
         
         System.out.println("ARXML file generated successfully.");
	}
}
