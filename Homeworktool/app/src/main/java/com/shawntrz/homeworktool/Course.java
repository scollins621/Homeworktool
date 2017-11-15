package com.shawntrz.homeworktool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Course extends AppCompatActivity {

    public EditText name;
    public EditText professor;
    public EditText number;
    public EditText section;

    Course course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        name = (EditText) findViewById(R.id.editText);
        professor = (EditText) findViewById(R.id.editText4);
        number = (EditText) findViewById(R.id.editText6);
        section = (EditText) findViewById(R.id.editText7);
    }

    public void appendCourse(Context context) {

    }

    public void createCourse(Context context) {

        Intent data = new Intent();
//        data.putExtra("ID", name.getText().toString());
        data.putExtra("name", name.getText().toString());
        data.putExtra("professor", professor.getText().toString());
        data.putExtra("number", number.getText().toString());
        data.putExtra("section", section.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
//    public void createCourse(View view) throws IOException, SAXException, ParserConfigurationException {
//        Intent intent = new Intent(this, AddCourses.class);
//        EditText name, professor, number, section;
//
//        name = (EditText) findViewById(R.id.editText);
//        professor = (EditText) findViewById(R.id.editText7);
//        number = (EditText) findViewById(R.id.editText4);
//        section = (EditText) findViewById(R.id.editText6);
//
//        this.course = new com.shawntrz.hwtObjects.Course(name.getText().toString(),
//                professor.getText().toString(),
//                number.getText().toString(),
//                section.getText().toString());
//
//        exportCourseXML((com.shawntrz.hwtObjects.Course) this.course);
//
//        startActivity(intent);
//
//
//    }

    public void exportCourseXML(com.shawntrz.hwtObjects.Course course) throws ParserConfigurationException, IOException, SAXException {

        try {
            InputStream courseFile = getAssets().open("courses.xml");

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(courseFile);
            Node root = document.getFirstChild();

            Element courseElement = document.createElement("courseID--SUCCESS");
            //age.
//            Element age = doc.createElement("age");
//            age.appendChild(doc.createTextNode("28"));
//            staff.appendChild(age);




        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }

    }
}
