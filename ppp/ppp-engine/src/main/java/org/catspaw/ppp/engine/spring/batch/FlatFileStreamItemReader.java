package org.catspaw.ppp.engine.spring.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.core.io.Resource;

public class FlatFileStreamItemReader<T> extends AbstractItemStreamItemReader<T> {

	private Resource		resource;
	private String			encoding	= "UTF-8";
	private LineMapper<T>	lineMapper;
	private BufferedReader	reader;

	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		String line = reader.readLine();
		if (line == null) {
			return null;
		}
		try {
			return lineMapper.mapLine(line, -1);
		} catch (Exception e) {
			throw new ParseException(e.getMessage(), e);
		}
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		InputStream is;
		try {
			is = resource.getInputStream();
		} catch (IOException e) {
			throw new ItemStreamException(e.getMessage(), e);
		}
		try {
			reader = new BufferedReader(new InputStreamReader(is, encoding));
		} catch (UnsupportedEncodingException e) {
			throw new ItemStreamException(e.getMessage(), e);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		try {
			reader.close();
		} catch (IOException e) {
			throw new ItemStreamException(e.getMessage(), e);
		}
	}

	public Resource getResource() {
		return resource;
	}

	public String getEncoding() {
		return encoding;
	}

	public LineMapper<T> getLineMapper() {
		return lineMapper;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setLineMapper(LineMapper<T> lineMapper) {
		this.lineMapper = lineMapper;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
}
