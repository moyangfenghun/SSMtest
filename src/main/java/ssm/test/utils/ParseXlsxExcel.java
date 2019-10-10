package ssm.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
 
public class ParseXlsxExcel extends DefaultHandler {
	private OPCPackage opcPackage = null;
	private XSSFReader xssfReader = null;
	private boolean nextIsStr;
	private String cellContent; //单元格的内容
	private int rowNumber=0;//第一行的标题的列数
	private boolean isfirstnull;//第一个空值单元格
	private ArrayList<String> sheetList; // sheet的所有内容
	private ArrayList<String> excelList; // Excel的所有内容
	private SharedStringsTable sst;
	private int currentColumn = 0;
	private XMLReader reader;
	
	/**
	 * @param path .xlxs后缀的Excel文件
	 * @param sheetId 
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @throws SAXException
	 */
	public ParseXlsxExcel(File path,int sheetId) throws IOException, OpenXML4JException, SAXException{
		FileInputStream inputStream=new FileInputStream(path);
		init( inputStream );
		parseSheet(sheetId);
	}
	
	public ParseXlsxExcel(InputStream file,int sheetId) throws IOException, OpenXML4JException, SAXException{
		init(file);
		parseSheet(sheetId);
	}
	
	/**
	 * @param file	.xlxs 后缀的Excel文件
	 * @param flag	true 表示查询所有 ，false 表示只获取sheetId为1的内容
	 * @throws IOException
	 * @throws OpenXML4JException
	 * @throws SAXException
	 */
	public ParseXlsxExcel(InputStream file,boolean flag) throws IOException, OpenXML4JException, SAXException{
		initAll(file, flag);
	}
	
	public ParseXlsxExcel(File path,boolean flag) throws IOException, OpenXML4JException, SAXException{
		FileInputStream inputStream=new FileInputStream(path);
		initAll( inputStream , flag);
	}
 
	private void initAll(InputStream file, boolean flag)throws InvalidFormatException, IOException, OpenXML4JException,SAXException {
		init(file);
		if(flag){
			parseAllSheet();
		}else {
			parseSheet(1);
		}
	}
	
	private void parseAllSheet() {
		int x = 0;
		int y = 0;
		while(x==y){
			try {
				parseSheet(++x);
				excelList.addAll(sheetList);
				y=x;
				currentColumn=0;
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}
 
	private void init(InputStream file) throws IOException, OpenXML4JException, SAXException{
		opcPackage = OPCPackage.open(file);
		sheetList = new ArrayList<String>();
		excelList = new ArrayList<String>();
		xssfReader = new XSSFReader(opcPackage);
		sst = xssfReader.getSharedStringsTable();
		reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		reader.setContentHandler(this);
	}
	
	
	public void close() throws IOException{
		if(opcPackage!=null) opcPackage.close();
	}
	
	private void parseSheet(int sheetId) throws IOException, OpenXML4JException, SAXException{
		sheetList.clear();
		InputStream inStream = xssfReader.getSheet("rId"+sheetId);
		InputSource inputSource = new InputSource(inStream);
		reader.parse(inputSource);
	}
 
	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		if(qName.equals("c")){ // c为单元格，v为值，row为行。
			String type = attributes.getValue("t");
			if(type!=null && type.equals("s")){
				nextIsStr = true;
			}else {
				nextIsStr = false;
			} 
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		cellContent = new String(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(nextIsStr&&cellContent!=null&&cellContent!=""){
			try {
				int idx = Integer.parseInt(cellContent);
				cellContent = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
			} catch (Exception e) {
//				 e.printStackTrace();
			}
		}
		if(qName.equals("v")){
			sheetList.add(currentColumn++, cellContent);
			cellContent="";
		}
		
	}
	
	/** sheet的所有内容 */
	public ArrayList<String> getSheetList() {
		return sheetList;
	}
	
	/** Excel的所有内容 */
	public ArrayList<String> getExcelList() {
		return excelList;
	}
	
	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
		//"C:/Users/admin/Desktop/大师傅家的感觉.xlsx"
		InputStream inputStream=new FileInputStream("C:\\eclipse4.2_workspace\\1568970438545.xlsx");
		ParseXlsxExcel excel = new ParseXlsxExcel(inputStream,false);
		excel.close();
		ArrayList<String> list = excel.getSheetList();
		for (int i = 0; i < list.size(); i++) {
			String string = list.get(i);
			System.out.println(i+"|"+string);
			if(i==10)break;
//			System.out.println(i+"|"+string);
		}
		long end = System.currentTimeMillis();
		
		System.out.println(list.size());//19677984
		System.out.println(end-start); //16389ms
	}
}
