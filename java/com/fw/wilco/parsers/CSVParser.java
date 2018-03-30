package com.fw.wilco.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.time.DateUtils;

import com.fw.wilco.api.InputMessageV3IO;

public class CSVParser implements IParser {
	
	enum MANDATORY_FIELDS {LAYOUT, FWOT, DATE, TIME};
	private static final int PARAMS_OFFSET = MANDATORY_FIELDS.values().length;
	
	@Override
	public List<InputMessageV3IO> parse(InputStream is) throws ParseException {
		List<InputMessageV3IO> ret = new ArrayList<InputMessageV3IO>();
		InputMessageV3IO m = new InputMessageV3IO();
		try {
			org.apache.commons.csv.CSVParser parser = CSVFormat.EXCEL.withDelimiter(';').parse(new InputStreamReader(is));
			Iterator<CSVRecord> it = parser.iterator();
			if (!it.hasNext()) {
				throw new ParseException("header", "the format must at least have a header (see https://github.com/flightwatching/wilco-api/tree/master/docs/csvImputFormat)");
			}
			CSVRecord header = it.next();
			List<String> params = new ArrayList<String>();
			if (header.size()<PARAMS_OFFSET) {
				logException(header, "header", "the header must at least have "+MANDATORY_FIELDS.values()+". it is currently \""+header+"\" (see WILCO CSV format)");
			}
			for (int i = PARAMS_OFFSET; i < header.size(); i++) {
				params.add(header.get(i));
			}
			while (it.hasNext()) {
				CSVRecord csvRecord = (CSVRecord) it.next();
				
				String fwot = null;
				String layout = null;									
				if (csvRecord.size()>2) {
					fwot = csvRecord.get(MANDATORY_FIELDS.FWOT.ordinal()).trim();
					layout = csvRecord.get(MANDATORY_FIELDS.LAYOUT.ordinal()).trim();					
				}
				if (!matchesMessage(m, fwot, layout)) {
					//the fwot changed. push the message and create a new one
					ret.add(m);
					m = new InputMessageV3IO();
				}
				if (fwot==null || fwot.length()==0) {
					continue;
				} else {
					m.reg = fwot;
					m.namedLayout = layout;
					m.sumup = layout;
				}
				try {
					if (csvRecord.size()<PARAMS_OFFSET) {
						logException(csvRecord, "line "+csvRecord.getRecordNumber(), "the line must at least have COL1->FWOT, COL2->DATE. it is currently \""+csvRecord+"\" (see WILCO CSV format)");					
					}
					Date d = DateUtils.parseDateStrictly(csvRecord.get(MANDATORY_FIELDS.DATE.ordinal())+"T"+csvRecord.get(MANDATORY_FIELDS.TIME.ordinal()), 
							"yyyy/MM/dd'T'HH:mm",
							"yyyy/MM/dd'T'HH:mm:ss",
							"dd/MM/yyyy'T'HH:mm:ss",
							"yyyy-MM-dd'T'HH:mm:ss.SSS",
							"yyyy-MM-dd'T'HH:mm:ss");
					m.computedDate = InputMessageV3IO.ISO8601DF.format(d);
					for (int i = PARAMS_OFFSET; i < csvRecord.size(); i++) {
						if (header.get(i).trim().length()>0 && csvRecord.get(i).trim().length()>0) {
							m.insertSample(header.get(i), csvRecord.get(i), InputMessageV3IO.ISO8601DF.format(d));							
						}
					}
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//to end the loop, store the message
			if (m.reg!=null) {
				//the fwot changed. push the message and create a new one
				ret.add(m);
			}

		} catch (Exception e) {
			throw new ParseException("inputstream", "cannot process data with CSV parser", e);
		}
		return ret;
	}

	private boolean matchesMessage(InputMessageV3IO m, String fwot, String layout) {
		return (m.reg==null || m.reg.equals(fwot))
				&& 
				(m.namedLayout==null || m.namedLayout.equals(layout));
	}

	private void logException(CSVRecord record, String field, String msg) throws ParseException {
		throw new ParseException(field, msg);
	}


	@Override
	public String getName() {
		return "CSVParser";
	}

}
