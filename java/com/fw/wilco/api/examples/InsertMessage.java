package com.fw.wilco.api.examples;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import com.fw.wilco.api.EventV3IO;
import com.fw.wilco.api.InputMessageV3IO;
import com.fw.wilco.api.InputSampleV3IO;
import com.fw.wilco.api.access.ApiBridge;
import com.fw.wilco.api.access.ApiException;
import com.fw.wilco.api.access.Logger;
import com.fw.wilco.api.constants.FlightStatus;
import com.fw.wilco.api.constants.SMI;
import com.fw.wilco.api.constants.Severity;

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
		msg.computedDate = InputMessageV3IO.ISO8601DF.format(new Date());
		msg.reg="FW-DAO";
		msg.sumup="This is a boilerplate test message";
		msg.severity=Severity.INFO;
		msg.status = FlightStatus.GATEIN;
		msg.tags = new HashSet<String>(Arrays.asList("TAG1", "TAG2"));
		msg.smi = SMI.FW;
		msg.samples = new HashSet<InputSampleV3IO>();
		msg.samples.add(new InputSampleV3IO("OIT_1", "12", InputMessageV3IO.ISO8601DF.format(new Date())));
		msg.samples.add(new InputSampleV3IO("OIT_1", "12", InputMessageV3IO.ISO8601DF.format(new Date(System.currentTimeMillis()-20000))));
		EventV3IO postMessage = ApiBridge.postMessage(msg);
		Logger.info("event #%s created", postMessage.id);
	}

}
