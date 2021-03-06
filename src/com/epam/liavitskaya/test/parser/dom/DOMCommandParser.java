package com.epam.liavitskaya.test.parser.dom;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.liavitskaya.test.parser.Parser;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class DOMCommandParser implements Parser {

	static final Logger logger = Logger.getLogger(DOMCommandParser.class);

	static final String COMMAND_XML_PATH = "resources/command.xml";

	@Override
	public Map<String, String> parseXML() {

		return parseXMLDom();
	}

	public static Map<String, String> parseXMLDom() {

		DOMParser domParser = new DOMParser();

		try {
			domParser.parse(COMMAND_XML_PATH);
		} catch (SAXException | IOException e) {
			logger.error("error during DOM parsing", e);
		}

		Document document = domParser.getDocument();
		Element root = document.getDocumentElement();

		Map<String, String> commandMap = new HashMap<>();
		NodeList commandNodes = root.getElementsByTagName("command");

		String commandName = null;
		String commandUrl = null;

		for (int i = 0; i < commandNodes.getLength(); i++) {

			Element commandElement = (Element) commandNodes.item(i);

			commandName = commandElement.getAttribute("name").trim();
			commandUrl = commandElement.getTextContent().trim();
			commandMap.put(commandName, commandUrl);
		}
		return commandMap;
	}

}
