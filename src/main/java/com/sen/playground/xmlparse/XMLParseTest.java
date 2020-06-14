package com.sen.playground.xmlparse;

import com.sun.org.apache.xml.internal.resolver.readers.SAXParserHandler;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.ArrayList;


public class XMLParseTest {
    private static String path = XMLParseTest.class.getResource("").getPath() + "\\books.xml";

    public static void main(String[] args) {
		//processByDom();
        processBySax();
	}

	public static void processByDom() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try{
            DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(path);
			//Get all sub nodes of root node 'bookstore'
			NodeList bookList = document.getElementsByTagName("book");
			System.out.println(" Total " + bookList.getLength() + " books.");

			//go through each book
			for(int i=0; i<bookList.getLength(); i++) {
				System.out.println("====== Start of reading " + (i+1) + "th book ======");
				//get single book node
				Node book = bookList.item(i);
				//get all attributes of a single book node
				NamedNodeMap attrs = book.getAttributes();
				System.out.println(i + "th book has " + attrs.getLength() + " attributes");

				// go through attributes of book node
				for(int j=0; j<attrs.getLength(); j++) {
					Node attr = attrs.item(j);
					System.out.println(" attr"+ (j+1) + ": "+ attr.getNodeName()
							+ ", value:" + attr.getNodeValue());
				}

				// get childnodes of book
				NodeList childNodes = book.getChildNodes();
				System.out.print( i + "th book has " + childNodes.getLength() + " child nodes");

				//重要事项：文本永远存储在文本节点中。在 DOM 处理过程中的一个常见的错误是，
				// 导航到元素节点，并认为此节点含有文本。不过，即使最简单的元素节点之下也拥有文本节点。
				// 举例，在 <year>2005</year> 中，有一个元素节点（year），同时此节点之下存在一个文本节点，
				// 其中含有文本（2005）。

				for(int k=0; k<childNodes.getLength(); k++) {
					if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
						System.out.println(k + "th child node's name :" + childNodes.item(k).getNodeName());
					    String nodeValue = childNodes.item(k).getFirstChild().getNodeValue();
						System.out.println(" value is:" + nodeValue);
					}
				}

				System.out.println("===== end of " + i + "th book");
			}
		}catch (ParserConfigurationException e) {
			             e.printStackTrace();
			        } catch (SAXException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
		             e.printStackTrace();
			        }
    }

	public static void processBySax() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            ContentHandler ch = new XMLParseTest().new BookContentHandler();
            SAXParserHandler handler = new SAXParserHandler();
            handler.setContentHandler(ch);
            parser.parse(path, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class BookContentHandler implements ContentHandler {
        String value = null;
        Book book = null;
        private ArrayList<Book> bookList = new ArrayList<Book>();
        public ArrayList<Book> getBookList() {
            return bookList;
        }
        int bookIndex = 0;
        @Override
        public void setDocumentLocator(Locator locator) {

        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("SAX解析开始");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("SAX解析开始结束");
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {

        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("book")) {
                bookIndex++;
                //创建一个book对象
                book = new Book();
                //开始解析book元素的属性
                System.out.println("======================开始遍历某一本书的内容=================");
                //不知道book元素下属性的名称以及个数，如何获取属性名以及属性值
                int num = attributes.getLength();
                for(int i = 0; i < num; i++){
                    System.out.print("book元素的第" + (i + 1) +  "个属性名是："
                            + attributes.getQName(i));
                    System.out.println("---属性值是：" + attributes.getValue(i));
                    if (attributes.getQName(i).equals("category")) {
                        book.setCategory(attributes.getValue(i));
                    }
                }
            }
            else if (!qName.equals("name") && !qName.equals("bookstore")) {
                System.out.print("节点名是：" + qName + "---");
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            //判断是否针对一本书已经遍历结束
            if (qName.equals("book")) {
                bookList.add(book);
                book = null;
                System.out.println("======================结束遍历某一本书的内容=================");
            }
            else if (qName.equals("category")) {
                book.setCategory(value);
            }
            else if (qName.equals("author")) {
                book.setAuthor(value);
            }
            else if (qName.equals("year")) {
                book.setYear(value);
            }
            else if (qName.equals("price")) {
                book.setPrice(value);
            }
            else if (qName.equals("lang")) {
                book.setLang(value);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            value = new String(ch, start, length);
            if (!value.trim().equals("")) {
                System.out.println("节点值是：" + value);
            }
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {

        }

        @Override
        public void skippedEntity(String name) throws SAXException {

        }
    }
}
