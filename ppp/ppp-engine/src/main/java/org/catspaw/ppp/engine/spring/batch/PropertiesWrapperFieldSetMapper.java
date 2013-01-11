package org.catspaw.ppp.engine.spring.batch;

import java.util.Properties;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PropertiesWrapperFieldSetMapper implements FieldSetMapper<Properties> {

	@Override
	public Properties mapFieldSet(FieldSet fieldSet) throws BindException {
		return fieldSet.getProperties();
	}
}
