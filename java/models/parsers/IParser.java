package models.parsers;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import models.io.InputMessageV3IO;


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
