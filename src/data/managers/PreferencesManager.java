package data.managers;

import utils.PreferencesManagerConstants;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.Properties;

public class PreferencesManager {

    private static PreferencesManager instance;
    public static final String XML_PATH = "C:\\Users\\Agerest\\Desktop\\examTask\\src\\resources\\configuration\\appconfig.xml";
    private Document document;
    private XPath path;
    private Properties properties;

    private PreferencesManager() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.parse(XML_PATH);
            XPathFactory xpathfactory = XPathFactory.newInstance();
            this.path = xpathfactory.newXPath();
            this.properties = new Properties();
            readProperties();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static PreferencesManager getInstance() {
        if (instance == null) {
            instance = new PreferencesManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    private void readProperties() {
        String[] keys = {PreferencesManagerConstants.classname, PreferencesManagerConstants.DBName,
                PreferencesManagerConstants.drivertype, PreferencesManagerConstants.pass,
                PreferencesManagerConstants.hostName, PreferencesManagerConstants.port,
                PreferencesManagerConstants.user};
        for(String key : keys){
            try {
                properties.setProperty(key,path.evaluate(key,document));
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        }
    }

}
