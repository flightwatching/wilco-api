package com.fw.wilco.api.examples;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import com.fw.wilco.api.InputSampleV3IO;
import com.fw.wilco.api.access.ApiBridge;
import com.fw.wilco.api.access.ApiException;
import com.fw.wilco.api.access.Logger;
import com.fw.wilco.api.constants.FlightStatus;
import com.fw.wilco.api.constants.SMI;
import com.fw.wilco.api.constants.Severity;
import com.fw.wilco.api.EventV3IO;
import com.fw.wilco.api.InputMessageV3IO;

/**
 * this class is an example of the use of the WILCO desktop API to insert a message into it
 * @author dao
 *
 */
public class InsertMessage {

	public static void main(String[] args) throws IOException, ApiException {
		Map<String, String> opts = Utils.parseArgs(args);
		ApiBridge.connect(opts.get("-apiUrl"), opts.get("-user"), opts.get("-password"));
		InputMessageV3IO msg = new InputMessageV3IO();
		msg.setComputedDate(InputMessageV3IO.ISO8601DF.format(new Date()));
		msg.setReg("FW-DAO");
		msg.setSumup("This is a boilerplate test message");
		msg.setSeverity(Severity.INFO);
		msg.setStatus(FlightStatus.GATEIN);
		msg.setTags(new HashSet<String>(Arrays.asList("TAG1", "TAG2")));
		msg.setSmi(SMI.FW);
		msg.setSamples(new HashSet<InputSampleV3IO>());
		msg.getSamples().add(new InputSampleV3IO("OIT_1", "12", InputMessageV3IO.ISO8601DF.format(new Date())));
		msg.getSamples().add(new InputSampleV3IO("OIT_1", "12", InputMessageV3IO.ISO8601DF.format(new Date(System.currentTimeMillis()-20000))));
		EventV3IO postMessage = ApiBridge.postMessage(msg);
		Logger.info("event #%s created", postMessage.getId());
	}

}
