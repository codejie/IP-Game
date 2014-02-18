package jie.android.ip.executor;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Script {
	public class BlockData {
		public int row;
		public int col;
		public int value;
		public int style;		
		
		public BlockData(int row, int col, int value, int style) {
			this.row = row;
			this.col = col;
			this.value = value;
			this.style = style;			
		}
	}
	
	public class TrayData {
		public int col;
		public int style;		
		
		public TrayData(int col, int style) {
			this.col = col;
			this.style = style;			
		}
	}
	
	private final int id;
	
//	private int status;
//	private int base_score;
	
	private String author;
	private String title;
	private String comment;
	
	private ArrayList<BlockData> source;
	private ArrayList<BlockData> target;
	private TrayData tray;
	
	public Script(int id) {
		this.id = id;
	}
	
	public final int getId() {
		return id;
	}
	
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}
//
//	public int getBaseScore() {
//		return base_score;
//	}
//
//	public void setBaseScore(int score) {
//		this.base_score = score;
//	}

	public boolean loadFile(final String file) {
		
		try {
			return load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean loadString(final String str) {
		return load(new ByteArrayInputStream(str.getBytes()));
	}
	
	private boolean load(final InputStream is) {
		
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			
			NodeList tl = doc.getElementsByTagName("Title");
			if (tl != null && tl.getLength() > 0) {
				this.title = tl.item(0).getNodeValue();
			} else {
				this.title = "No Title";
			}
			NodeList au = doc.getElementsByTagName("Author");
			if (au != null && au.getLength() > 0) {
				this.author = au.item(0).getNodeValue();
			} else {
				this.author = "Unknown";
			}
			NodeList ct = doc.getElementsByTagName("Comment");
			if (ct != null && ct.getLength() > 0) {
				this.comment = ct.item(0).getNodeValue();
			} else {
				this.comment = "";
			}
			
			NodeList src = doc.getElementsByTagName("Source").item(0).getChildNodes();
			source = new ArrayList<BlockData>();
			loadBlockData(source, src);
			
			NodeList tgt = doc.getElementsByTagName("Target").item(0).getChildNodes();
			target = new ArrayList<BlockData>();
			loadBlockData(target, tgt);
			
			NodeList tray = doc.getElementsByTagName("Tray");
			NamedNodeMap attr = tray.item(0).getAttributes();
			this.tray = new TrayData(Integer.valueOf(attr.getNamedItem("col").getNodeValue()).intValue()
					, Integer.valueOf(attr.getNamedItem("style").getNodeValue()).intValue());
			
			return checkBlockData();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;		
	}

	private void loadBlockData(ArrayList<BlockData> block, final NodeList nl) throws ScriptException {
		for (int i = 0; i < nl.getLength(); ++ i) {
			Node item = nl.item(i);
			int type = item.getNodeType();
			if (type != Node.ELEMENT_NODE) {
				continue;
			}
			NamedNodeMap attr = item.getAttributes();
			String v = attr.getNamedItem("row").getNodeValue();
			BlockData data = new BlockData(Integer.valueOf(attr.getNamedItem("row").getNodeValue()).intValue()
					, Integer.valueOf(attr.getNamedItem("col").getNodeValue()).intValue()
					, Integer.valueOf(attr.getNamedItem("value").getNodeValue()).intValue()
					, Integer.valueOf(attr.getNamedItem("style").getNodeValue()).intValue());
			block.add(data);
		}
	}

	private boolean checkBlockData() {
		return source.size() == target.size();
	}	
	
	public final ArrayList<BlockData> getSource() {
		return source;
	}
	
	public final ArrayList<BlockData> getTarget() {
		return target;
	}

	public final TrayData getTray() {
		return tray;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getComment() {
		return comment;
	}


}
