package com.shawntrz.homeworktool;

/**
 * Created by strz on 10/18/17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.NonReadableChannelException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class AddCourses extends AppCompatActivity {
    public ListView list;
    private File file;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classes);



        file = new File(this.getFilesDir(), "courses ");

        initFromFileOrCreateOne(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Course.RESULT_OK){
                String result=data.getStringExtra("result");
            }
            if (resultCode == Course.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private void initFromFileOrCreateOne(Context context) {
        try {
            readFromFileAndPopList();
        }
        catch(NonReadableChannelException e){
            initFile(file);
            Log.v("courses", "NonReadableChannelException");
        } catch (ParserConfigurationException e) {
            initFile(file);
            Log.v("courses", "ParserConfigException");
        } catch (IOException e) {
            Log.v("courses", "IO Exception");
            initFile(file);
        } catch (SAXException e) {
            Log.v("courses", "SAXException");
            initFile(file);
        }
    }

    private void appendToFile(Context context) {
        Log.v("courses", "Create CLICKED");
        try {
            parseToXMLFile(context);
        }catch (ParserConfigurationException pce){
            Log.v("courses", "parser configuration error..");
        }
        catch (TransformerConfigurationException tce){
            Log.v("courses", "TransformerConfiguration Exception");

        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private void parseToXMLFile(Context context) throws ParserConfigurationException, TransformerException {
        String ret = "";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // root
        Element root = document.createElement("courses");
        document.appendChild(root);

        //courseID
        Element courseID = document.createElement("courseID");
        courseID.appendChild(document.createTextNode("courseID"));
        root.appendChild(courseID);

            //name
        Element courseName = document.createElement("name");
        courseName.appendChild(document.createTextNode("Software Engineering"));
        courseID.appendChild(courseName);

            //professor
        Element professorName = document.createElement(("professor"));
        professorName.appendChild(document.createTextNode("NICK"));
        courseID.appendChild(professorName);

            //number
        Element courseNumber = document.createElement(("number"));
        courseNumber.appendChild(document.createTextNode("350"));
        courseID.appendChild(courseNumber);

            //section
        Element sectionNumber = document.createElement(("section"));
        sectionNumber.appendChild(document.createTextNode("02"));
        courseID.appendChild(sectionNumber);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource Dsource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        Log.v("courses", "xmlwrite: " + Dsource.toString());
        transformer.transform(Dsource, streamResult);
    }

    private void readFromFileAndPopList() throws ParserConfigurationException, IOException, SAXException, NonReadableChannelException {
        if(!file.canRead()){
            Log.v("courses", "CANNOTREAD!");
            //throw new NonReadableChannelException();
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while((line = bufferedReader.readLine()) != null){
            Log.v("courses-XMLOUTPUT", line);
        }
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        Log.v("courses", "xml output: " + document.getChildNodes().toString());
        document.getDocumentElement().normalize();
        String id = document.getElementsByTagName("Sms").item(0).getTextContent();
        String name = document.getElementsByTagName("Push").item(0).getTextContent();
        String prof = document.getElementsByTagName("Email").item(0).getTextContent();
        String number = document.getElementsByTagName("Email").item(0).getTextContent();
        String section = document.getElementsByTagName("Email").item(0).getTextContent();

        Element element = document.getDocumentElement();
        element.normalize();

        NodeList courseNodes = document.getElementsByTagName("courseID");
        for (int i=0; i<courseNodes.getLength(); i++) {

            Node node = courseNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element2 = (Element) node;
            }
        }

            Log.v("courses", "state from File: ");
//
//        Switch smsButton = (Switch) findViewById(R.id.sms);
//        Switch pushButton = (Switch) findViewById(R.id.push);
//        Switch emailButton = (Switch) findViewById(R.id.email);

    }

    private void initFile(File file) {
        String name = "Software Engineering";
        String professor = "NICK";
        String number = "350";
        String section = "02";
        appendToFile(this);
    }


    public void loadCourseCreator(View view) {
        Intent intent = new Intent(this, Course.class);
11        startActivityForResult(intent, 0);
    }


}

