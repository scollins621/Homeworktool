package com.shawntrz.homeworktool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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


public class Settings extends AppCompatActivity {
    public boolean smsNotifications;
    public boolean pushNotifications;
    public boolean emailNotifications;
    private File file;
    private Switch smsButton;
    private Switch pushButton;
    private Switch emailButton;

    Settings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        file = new File(this.getFilesDir(), "settings");
        smsButton = (Switch) findViewById(R.id.sms);
        pushButton = (Switch) findViewById(R.id.push);
        emailButton = (Switch) findViewById(R.id.email);
        initFromFileOrCreateOne(this);
    }

    private void initFromFileOrCreateOne(Context context) {
        try {
            readFromFileAndSetVarsFromFile();
        }
        catch(NonReadableChannelException e){
            initializeFileAndSetVarsFromDefaults(file);
            Log.v("settings", "NonReadableChannelException");
        } catch (ParserConfigurationException e) {
            initializeFileAndSetVarsFromDefaults(file);
            Log.v("settings", "ParserConfigException");
        } catch (IOException e) {
            Log.v("settings", "IO Exception");
            initializeFileAndSetVarsFromDefaults(file);
        } catch (SAXException e) {
            Log.v("settings", "SAXException");
            initializeFileAndSetVarsFromDefaults(file);
        }
    }

    public void updateVariables(View view){

        smsNotifications = smsButton.isChecked();
        pushNotifications = pushButton.isChecked();
        emailNotifications = emailButton.isChecked();

        Log.v("settings", "state: " + smsNotifications + pushNotifications + emailNotifications);

        saveToFile(this);
    }

    private void saveToFile(Context context) {
        Log.v("settings", "SAVE clicked!!!");
        try {
            parseToXMLFile();
        }
        catch (ParserConfigurationException pce){
            Log.v("settings", "parser configuration error..");
        }
        catch (TransformerConfigurationException tce){
            Log.v("settings", "TransformerConfiguration Exception");

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void parseToXMLFile() throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        String ret = "";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // root
        Element root = document.createElement("Settings");
        document.appendChild(root);

        //sms notifications
        Element smsnote = document.createElement("Sms");
        smsnote.appendChild(document.createTextNode((smsNotifications) ? "True" : "False"));
        root.appendChild(smsnote);

        //push notifications
        Element pushnote = document.createElement("Push");
        pushnote.appendChild(document.createTextNode((pushNotifications) ? "True" : "False"));
        root.appendChild(pushnote);

        //email notifications
        Element emailnote = document.createElement(("Email"));
        emailnote.appendChild(document.createTextNode((emailNotifications) ? "True" : "False"));
        root.appendChild(emailnote);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource Dsource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        Log.v("settings", "xmlwrite: " + Dsource.toString());
        transformer.transform(Dsource, streamResult);
    }

    private void readFromFileAndSetVarsFromFile() throws ParserConfigurationException, IOException, SAXException, NonReadableChannelException {
        if(!file.canRead()){
            Log.v("settings", "CANNOTREAD!");
            //throw new NonReadableChannelException();
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while((line = bufferedReader.readLine()) != null){
            Log.v("settings-XMLOUTPUT", line);
        }
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        Log.v("settings", "xml output: " + document.getChildNodes().toString());
        document.getDocumentElement().normalize();
        String sms = document.getElementsByTagName("Sms").item(0).getTextContent();
        String push = document.getElementsByTagName("Push").item(0).getTextContent();
        String email = document.getElementsByTagName("Email").item(0).getTextContent();
        smsNotifications = sms.equals("True");
        pushNotifications = push.equals("True");
        emailNotifications = email.equals("True");

        Log.v("settings", "state from File: " + sms + push + email);
//
//        Switch smsButton = (Switch) findViewById(R.id.sms);
//        Switch pushButton = (Switch) findViewById(R.id.push);
//        Switch emailButton = (Switch) findViewById(R.id.email);

        smsButton.setChecked(smsNotifications);
        pushButton.setChecked(pushNotifications);
        emailButton.setChecked(emailNotifications);

    }

    private void initializeFileAndSetVarsFromDefaults(File file){
        smsNotifications = false;
        emailNotifications = false;
        smsNotifications = false;
        saveToFile(this);
    }
}
