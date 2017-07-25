package com.fw.wilco.parsers.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fw.wilco.api.InputMessageV3IO;
import com.fw.wilco.parsers.CSVParser;
import com.fw.wilco.parsers.ParseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CSVTest {
	
	@Test
	public void ontonome() throws ParseException, FileNotFoundException {
		CSVParser p = new CSVParser();
		List<InputMessageV3IO> msgs = p.parse(new FileInputStream(new File("java/tests/resources/otono-me.csv")));
		Assert.assertEquals(1, msgs.size());
		Assert.assertEquals("2014-10-02T16:25:00.000", msgs.get(0).computedDate);
	}

	@Test
	public void simple() throws ParseException, FileNotFoundException {
		CSVParser p = new CSVParser();
		List<InputMessageV3IO> msgs = p.parse(new FileInputStream(new File("java/tests/resources/simple.csv")));
		Assert.assertEquals(2, msgs.size());
		Assert.assertEquals(6, msgs.get(0).samples.size());
		Assert.assertEquals(8, msgs.get(1).samples.size());
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(msgs.get(0)));
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(msgs.get(1)));
	}
	

	@Test
	public void splitMessage() throws ParseException, FileNotFoundException {
		CSVParser p = new CSVParser();
		List<InputMessageV3IO> msgs = p.parse(new FileInputStream(new File("java/tests/resources/splitMessage.csv")));
		Assert.assertEquals(3, msgs.size());
		Assert.assertEquals(6, msgs.get(0).samples.size());
		Assert.assertEquals(4, msgs.get(1).samples.size());
		Assert.assertEquals(3, msgs.get(2).samples.size());
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(msgs.get(0)));
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(msgs.get(1)));
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(msgs.get(2)));
	}

}
