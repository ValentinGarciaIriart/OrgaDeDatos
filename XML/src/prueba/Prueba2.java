package prueba;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Prueba2 {

	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		
		
		Document documento = implementation.createDocument(null,"Toma_de_Temperaturas2", null);
		documento.setXmlVersion("1.0");
		
		Element tomaDeTemperaturas = documento.createElement("Toma_de_Temperaturas2");
		
		
		
		Element t1 = documento.createElement("Toma_de_Temperatura");
		Element f1 = documento.createElement("Fecha");
		Text textf1 = documento.createTextNode("14-12-2020");
		f1.appendChild(textf1);
		t1.appendChild(f1);
		
		Element lugar1 = documento.createElement("Lugar_1");
		Text textLugar1 = documento.createTextNode("15");
		lugar1.appendChild(textLugar1);
	
		
		Element lugar2 = documento.createElement("Lugar_2");
		Text textLugar2 = documento.createTextNode("20");
		lugar2.appendChild(textLugar2);
		
		t1.appendChild(lugar1);
		t1.appendChild(lugar2);

		
		

		
		Element t2 = documento.createElement("Toma_de_Temperatura");
		Element f2 = documento.createElement("Fecha");
		Text textf2 = documento.createTextNode("15-12-2020");
		f2.appendChild(textf2);
		t2.appendChild(f2);
		
		Element lugar12 = documento.createElement("Lugar_1");
		Text textLugar12 = documento.createTextNode("19");
		lugar12.appendChild(textLugar12);
	
		
		Element lugar22 = documento.createElement("Lugar_2");
		Text textLugar22 = documento.createTextNode("20");
		lugar22.appendChild(textLugar22);
		
	
		
		t2.appendChild(lugar12);
		t2.appendChild(lugar22);
		
		Element t3 = documento.createElement("Toma_de_Temperatura");
		Element f3 = documento.createElement("Fecha");
		Text textf3 = documento.createTextNode("16-12-2020");
		f3.appendChild(textf3);
		t3.appendChild(f3);
		
		Element lugar13 = documento.createElement("Lugar_1");
		Text textLugar13 = documento.createTextNode("18");
		lugar13.appendChild(textLugar13);
	
		
		Element lugar23 = documento.createElement("Lugar_2");
		Text textLugar23 = documento.createTextNode("22");
		lugar23.appendChild(textLugar23);
		
	
		
		t3.appendChild(lugar13);
		t3.appendChild(lugar23);
	
		
		
		Element t4 = documento.createElement("Toma_de_Temperatura");
		Element f4 = documento.createElement("Fecha");
		Text textf4 = documento.createTextNode("17-12-2020");
		f4.appendChild(textf4);
		t4.appendChild(f4);
		
		Element lugar14 = documento.createElement("Lugar_1");
		Text textLugar14 = documento.createTextNode("19");
		lugar14.appendChild(textLugar14);
	
		
		Element lugar24 = documento.createElement("Lugar_2");
		Text textLugar24 = documento.createTextNode("22");
		lugar24.appendChild(textLugar24);
		
	
		
		t4.appendChild(lugar14);
		t4.appendChild(lugar24);
	
	
		
		
		
		documento.getDocumentElement().appendChild(t1);
		documento.getDocumentElement().appendChild(t2);
		documento.getDocumentElement().appendChild(t3);
		documento.getDocumentElement().appendChild(t4);
		
		Source source = new DOMSource(documento);
		Result result = new StreamResult(new File("Toma_de_Temperaturas2.xml"));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result);

	}

}
