package org.pgl.fh.webservice.dao;

import java.util.HashMap;
import java.util.Map;

import org.pgl.fh.webservice.data.Folder;

/**
 * Use for simulation.
 * */
public class DataFolderByAccountMap {
	
	private DataFolderByAccountMap(){};
	
	public static final Map<String, Map<String, Folder>> data = new HashMap<>();
}
