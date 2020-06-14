package com.sen.playground.xmlparse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.List;

public class XMLParseDom4jTest {
    private static String path = XMLParseTest.class.getResource("").getPath() + "\\books.xml";

    public static void main(String[] args) {
        SAXReader reader = new SAXReader();

        try {
            Document document = reader.read(path);

            Element bookstore = document.getRootElement();
            Iterator<Element> itr = bookstore.elementIterator();
            while(itr.hasNext()) {
                Element book = itr.next();
                System.out.println("=== Start reading a book ====");
                List<Attribute> attrList = book.attributes();
                for(Attribute attr : attrList) {
                    System.out.print( "attr name:" + attr.getName() + " attr value:" + attr.getValue());
                }

                Iterator<Element> bookItr = book.elementIterator();
                while(bookItr.hasNext()) {
                    Element bookChild = bookItr.next();
                    System.out.println(" name: " + bookChild.getName() + " value:" + bookChild.getStringValue());
                }
                System.out.println("=== End reading a book ===");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
