package org.catspaw.cherubim.persistence.mapping;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import org.catspaw.cherubim.util.ClassPathUtils;

public class MybatisJpaEntityMappingResolver extends
		DefaultEntityMappingResolver {

	public MybatisJpaEntityMappingResolver(Class<?> entityClass,
			String configPath) {
		super(entityClass);
		URL url = ClassPathUtils.loadResource(configPath);
		SAXReader reader = new SAXReader();
		try {
			reader
					.setFeature(
								"http://apache.org/xml/features/nonvalidating/load-external-dtd",
								false);
		} catch (SAXException e) {
			throw new IllegalStateException(e);
		}
		Document doc;
		try {
			doc = reader.read(url);
		} catch (DocumentException e) {
			throw new IllegalStateException(e);
		}
		init(doc);
	}

	private void init(Document doc) {
		Element root = doc.getRootElement();
		List<Element> resultMapElems = root.elements("resultMap");
		for (Element resultMapElem : resultMapElems) {
			String table = resultMapElem.attributeValue("id");
			setTable(table);
			Element idElem = resultMapElem.element("id");
			String idProperty = idElem.attributeValue("property");
			setIdProperty(idProperty);
			String idColumn = idElem.attributeValue("column");
			setIdColumn(idColumn);
			List<Element> resultElems = resultMapElem.elements("result");
			String[] properties = new String[resultElems.size()];
			setProperties(properties);
			String[] columns = new String[resultElems.size()];
			setColumns(columns);
			Map<String, String> propertyColumnMapping = new HashMap<String, String>();
			Map<String, String> columnPropertyMapping = new HashMap<String, String>();
			int idx = 0;
			for (Element resultElem : resultElems) {
				properties[idx] = resultElem.attributeValue("property");
				columns[idx] = resultElem.attributeValue("column");
				propertyColumnMapping.put(properties[idx], columns[idx]);
				columnPropertyMapping.put(columns[idx], properties[idx]);
				idx++;
			}
			setPropertyColumnMapping(propertyColumnMapping);
			setColumnPropertyMapping(columnPropertyMapping);
		}
	}
}
