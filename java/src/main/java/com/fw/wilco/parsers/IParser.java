package com.fw.wilco.parsers;

import java.io.InputStream;
import java.util.List;

import com.fw.wilco.api.InputMessageV3IO;


public interface IParser {
	
	/**
	 * if the parser returns null, empty or an exception, the parser is considered to be not applicable and the other parser will be tried
	 * @param file
	 * @return
	 * @throws ParseException
	 */
	List<InputMessageV3IO> parse(InputStream is) throws ParseException;

	String getName();

}
