package com.epam.liavitskaya.main.parser.stax;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.epam.liavitskaya.main.parser.Parser;

public class StAXCommandParser implements Parser {

	static final String COMMAND_XML_PATH = "src/command.xml";

	@Override
	public Map<String, String> parseXML() {

		return parseXMLStAX();
	}

	public static Map<String, String> parseXMLStAX() {

		XMLInputFactory factory = XMLInputFactory.newInstance();
		Map<String, String> commandMap = new HashMap<>();

		try {
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(COMMAND_XML_PATH));

			while (reader.hasNext()) {

				String commandName = null;
				String commandPath = null;

				int type = reader.next();

				switch (type) {
				case XMLStreamConstants.START_ELEMENT:
					commandName = reader.getAttributeValue(0).trim();
					System.out.println("START_ELEMENT " + commandName);
					reader.next();
					commandPath = reader.getText().trim();
					if (commandPath.equals("") || commandName.equals("")) {
						continue;
					}
					break;
				default:
					break;
				}
				if (commandName != null && commandPath != null) {
					commandMap.put(commandName, commandPath);
				}
			}

		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
		return commandMap;
	}

}