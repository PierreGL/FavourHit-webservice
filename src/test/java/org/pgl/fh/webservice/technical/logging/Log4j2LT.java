package org.pgl.fh.webservice.technical.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Learning Tests about Log4j2.
 * */
public class Log4j2LT {
	
	private static final Logger LOGGER = LogManager.getRootLogger();
	
	@Test
	public void testBasicsLog() {
		LOGGER.debug("debug message");
		LOGGER.warn("warn message");
		LOGGER.error("error message");
	}
}
