package project;

import java.io.IOException;
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
			
			NodeList personList = doc.getElementsByTagName("SHORT-NAME");
			for(int i = 0; i<personList.getLength(); i++) {
				Node p = personList.item(i);
				System.out.println(p.getTextContent());
				/*if(p.getNodeType()==Node.ELEMENT_NODE) {
					Element person = (Element)p;
					String id = person.getAttribute("id");
					
				}*/
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
        // Call constructor of parent Exception
        super(s);
    }
}
