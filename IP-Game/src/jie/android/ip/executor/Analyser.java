package jie.android.ip.executor;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jie.android.ip.executor.CommandConsts.ActType;
import jie.android.ip.executor.CommandConsts.CommandType;
import jie.android.ip.utils.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Analyser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Analyser.makeCommandSet(".\\doc\\cmd_test.xml");		
	}
	
	public static CommandSet makeCommandSet(final String file) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new File(file));
			
			NodeList func = doc.getElementsByTagName("Function");
			if (func == null || func.getLength() == 0) {
				return null;
			}
			
			CommandSet cmdset = new CommandSet();
			
			for (int i = 0; i < func.getLength(); ++ i) {
				addFunction(cmdset, func.item(i));
			}
			return cmdset;
			
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
					cmdq.push(CommandSet.makeCommand(CommandType.ACT, p1, p2));
				} else if (p1 == ActType.ACTION.getId()) { // action
					cmdq.push(CommandSet.makeCommand(CommandType.ACT, p1));
				}
			} else if (type.equals(CommandType.CHECK.getTitle())) {
				int p1 = Integer.valueOf(attr.getNamedItem("p1").getNodeValue()).intValue();
				int p2 = Integer.valueOf(attr.getNamedItem("p2").getNodeValue()).intValue();
				cmdq.push(CommandSet.makeCommand(CommandType.CHECK, p1, p2));
			} else if (type.equals(CommandType.CALL.getTitle())) {
				int p1 = Integer.valueOf(attr.getNamedItem("p1").getNodeValue()).intValue();
				cmdq.push(CommandSet.makeCommand(CommandType.CALL, p1));
			} else {
				//
			}
		}
		if (cmdq.size() > 0) {
			cmdset.put(id, cmdq);
		}
	}
	
}
