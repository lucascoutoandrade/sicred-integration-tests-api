package br.com.sicred.utils;


import com.google.common.collect.ImmutableMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class AllureEnvironmentWriter {
    static Description description;
    static Story story;
    static Epic epic;
    static Severity severity;
    static DisplayName displayName;


    public AllureEnvironmentWriter() {
    }

    public static void allureEnvironmentWriter(ImmutableMap<String, String> environmentValuesSet) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element environment = doc.createElement("environment");
            doc.appendChild(environment);
            environmentValuesSet.forEach((k, v) -> {
                Element parameter = doc.createElement("parameter");
                Element key = doc.createElement("key");
                Element value = doc.createElement("value");
                key.appendChild(doc.createTextNode(k));
                value.appendChild(doc.createTextNode(v));
                parameter.appendChild(key);
                parameter.appendChild(value);
                environment.appendChild(parameter);
            });
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File allureResultsDir = new File(System.getProperty("user.dir") + "/allure-results");
            if (!allureResultsDir.exists()) {
                allureResultsDir.mkdirs();
            }

            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + "/allure-results/environment.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException var10) {
            var10.printStackTrace();
        }

    }

    public static void allureEnvironmentWriter(ImmutableMap<String, String> environmentValuesSet, String customResultsPath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element environment = doc.createElement("environment");
            doc.appendChild(environment);
            environmentValuesSet.forEach((k, v) -> {
                Element parameter = doc.createElement("parameter");
                Element key = doc.createElement("key");
                Element value = doc.createElement("value");
                key.appendChild(doc.createTextNode(k));
                value.appendChild(doc.createTextNode(v));
                parameter.appendChild(key);
                parameter.appendChild(value);
                environment.appendChild(parameter);
            });
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File allureResultsDir = new File(customResultsPath);
            if (!allureResultsDir.exists()) {
                allureResultsDir.mkdirs();
            }

            StreamResult result = new StreamResult(new File(customResultsPath + "environment.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException var11) {
            var11.printStackTrace();
        }

    }
 


    @BeforeEach
    public void setupScenario() {
        Method[] methods = getClass().getMethods();
        Method method = methods[0];
        description = method.getAnnotation(Description.class);
        story = method.getAnnotation(Story.class);
        epic = method.getAnnotation(Epic.class);
        severity = method.getAnnotation(Severity.class);
        displayName = method.getAnnotation(DisplayName.class);


    }

    public static String descriptionTest(Annotations tag) {
        String value = "";

        try {
            switch (tag) {
                case DESCRIPTION:
                    value = description.value();
                    break;
                case STORY:
                    value = story.value();
                    break;
                case EPIC:
                    value = epic.value();
                    break;
                case DISPLAY_NAME:
                    value = displayName.value();
                    break;
                case SEVERITY:
                    value = String.valueOf(severity.value());
                    break;
                default:
                    value = "Tag não encontrada";
                    break;
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return value;
    }

}

}
