package jie.android.ip.executor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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

import jie.android.ip.executor.CommandConsts.CommandType;

public class CommandSet {

	public static class Command {
		private CommandType type;
		private Object param1;
		private Object param2;

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
				if (param1 == null) {
					return defaultValue;
				}
				return ((Integer)param1).intValue();				
			}
		}
		
		public Object getParam(int index) {
			return (index == 0) ? param1 : param2;
		}
		
		public CommandType getType() {
			return type;
		}
		
	}
	
	public static class CommandQueue extends LinkedList<Command> {
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
	
	public boolean save(final String file) {		
		
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
				for (int i = que.size() - 1; i >= 0; -- i) {
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
			
			//output
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			trans.transform(new DOMSource(doc), new StreamResult(new File(file)));

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
			return false;
		} catch (TransformerFactoryConfigurationError e1) {
			e1.printStackTrace();
			return false;
		} catch (TransformerException e1) {
			e1.printStackTrace();
			return false;
		}
		
		return true;	
	}
	
}
