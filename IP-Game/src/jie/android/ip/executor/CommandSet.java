package jie.android.ip.executor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jie.android.ip.executor.CommandConsts.ActType;
import jie.android.ip.executor.CommandConsts.CommandType;

public class CommandSet {

	public static class Command {
		private final CommandType type;
		private final Object param1;
		private final Object param2;

		public Command(CommandType type, Object param1, Object param2) {
			this.type = type;
			this.param1 = param1;
			this.param2 = param2;
		}
		
		public String getParamAsString(int index) {
			if (index == 0) {
				if (param1 == null) {
					return null;
				}
				return param1.toString();
			} else {
				if (param2 == null) {
					return null;
				}
				return param2.toString();				
			}
		}
		
		public int getParamAsInt(int index, int defaultValue) {
			if (index == 0) {
				if (param1 == null) {
					return defaultValue;
				}
				return ((Integer)param1).intValue();
			} else {
				if (param2 == null) {
					return defaultValue;
				}
				return ((Integer)param2).intValue();				
			}
		}
		
		public Object getParam(int index) {
			return (index == 0) ? param1 : param2;
		}
		
		public CommandType getType() {
			return type;
		}
		
	}
	
//	public static class CommandQueue extends LinkedList<Command> {
//	}
	
	public static class CommandQueue extends ArrayList<Command> {
	}
	

	private HashMap<Integer, CommandQueue> functionSet = new HashMap<Integer, CommandQueue>();
	
	public static CommandQueue makeCommandQueue() {
		return new CommandSet.CommandQueue();
	}
	
	public static Command makeCommand(CommandType type) {
		return new CommandSet.Command(type, null, null);
	}
	
	public static Command makeCommand(CommandType type, Object param1) {
		return new CommandSet.Command(type, param1, null);
	}
	
	public static Command makeCommand(CommandType type, Object param1, Object param2) {
		return new CommandSet.Command(type, param1, param2);
	}
	
	public void put(int func, CommandQueue cmds) {
		functionSet.put(func, cmds);
	}
	
	public final CommandQueue get(int func) {
		return functionSet.get(func);
	}

	private final Document transToXmlDocument() {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.newDocument();
						
			//comment
			//CommandSet
			Element cmdset = doc.createElement("CommandSet");
			for (Entry<Integer, CommandQueue> e : functionSet.entrySet()) {
				Element func = doc.createElement("Function");
				func.setAttribute("id", e.getKey().toString());
				CommandQueue que = e.getValue();
				//for (int i = que.size() - 1; i >= 0; -- i) {
				for (int i = 0; i < que.size(); ++ i) {
					Command cmd = que.get(i);
					Element c = doc.createElement("Command");					
					c.setAttribute("type", cmd.getType().getTitle());
					if (cmd.getParam(0) != null) {
						c.setAttribute("p1", cmd.getParamAsString(0));
					}
					if (cmd.getParam(1) != null) {
						c.setAttribute("p2", cmd.getParamAsString(1));
					}
					func.appendChild(c);
				}
				cmdset.appendChild(func);
			}
			
			Element root = doc.createElement("IP-Command-Format");			
			root.appendChild(cmdset);
			doc.appendChild(root);
			
			return doc;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public boolean saveToFile(final String file) {		
		
		final Document doc = transToXmlDocument();
		if (doc != null) {
			try {
				Transformer trans = TransformerFactory.newInstance().newTransformer();
				trans.transform(new DOMSource(doc), new StreamResult(new File(file)));
				
				return true;				
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;

	}
	
	public final String saveToString() {		
		
		final Document doc = transToXmlDocument();
		if (doc != null) {
			try {
				Transformer trans = TransformerFactory.newInstance().newTransformer();
				final StringWriter sw = new StringWriter();
				trans.transform(new DOMSource(doc), new StreamResult(sw));
				return sw.toString();				
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static final CommandSet loadFromFile(final String file) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new File(file));
			
			if (doc != null) {
				return loadFromXmlDocument(doc);
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static final CommandSet loadFromString(final String xml) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
			Document doc = builder.parse(is);
			
			if (doc != null) {
				return loadFromXmlDocument(doc);
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
	
	private static final CommandSet loadFromXmlDocument(final Document doc) {
			
		NodeList func = doc.getElementsByTagName("Function");
		if (func == null || func.getLength() == 0) {
			return null;
		}
		
		final CommandSet cmdset = new CommandSet();
		
		for (int i = 0; i < func.getLength(); ++ i) {
			addFunction(cmdset, func.item(i));
		}
		return cmdset;
	}

	private static void addFunction(CommandSet cmdset, Node func) {
		int id = Integer.valueOf(func.getAttributes().getNamedItem("id").getNodeValue()).intValue();
				
		NodeList cmd = ((Element)func).getElementsByTagName("Command");// .getChildNodes();
		if (cmd == null || cmd.getLength() == 0) {
			return;
		}
		
		CommandSet.CommandQueue cmdq = CommandSet.makeCommandQueue();
		
		for (int i = 0; i < cmd.getLength(); ++ i) {
			NamedNodeMap attr = cmd.item(i).getAttributes();
			final String type = attr.getNamedItem("type").getNodeValue();
			if (type.equals(CommandType.ACT.getTitle())) {
				int p1 = Integer.valueOf(attr.getNamedItem("p1").getNodeValue()).intValue();
				if (p1 == ActType.MOVE_LEFT.getId() || p1 == ActType.MOVE_RIGHT.getId()) { //move
					int p2 = Integer.valueOf(attr.getNamedItem("p2").getNodeValue()).intValue();
					cmdq.add(CommandSet.makeCommand(CommandType.ACT, p1, p2));
				} else if (p1 == ActType.ACTION.getId()) { // action
					cmdq.add(CommandSet.makeCommand(CommandType.ACT, p1));
				}
			} else if (type.equals(CommandType.CHECK.getTitle())) {
				int p1 = Integer.valueOf(attr.getNamedItem("p1").getNodeValue()).intValue();
				int p2 = Integer.valueOf(attr.getNamedItem("p2").getNodeValue()).intValue();
				cmdq.add(CommandSet.makeCommand(CommandType.CHECK, p1, p2));
			} else if (type.equals(CommandType.CALL.getTitle())) {
				int p1 = Integer.valueOf(attr.getNamedItem("p1").getNodeValue()).intValue();
				cmdq.add(CommandSet.makeCommand(CommandType.CALL, p1));
			} else if (type.equals(CommandType.EMPTY.getTitle())) {
				int p1 = Integer.valueOf(attr.getNamedItem("p1").getNodeValue()).intValue();
				cmdq.add(CommandSet.makeCommand(CommandType.EMPTY, p1));
			} else {
				//
			}
		}
		if (cmdq.size() > 0) {
			cmdset.put(id, cmdq);
		}
	}
	
	
}