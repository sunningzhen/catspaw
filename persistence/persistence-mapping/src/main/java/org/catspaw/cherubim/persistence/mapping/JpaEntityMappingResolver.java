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

public class JpaEntityMappingResolver extends DefaultEntityMappingResolver {

	public JpaEntityMappingResolver(Class<?> entityClass, String configPath) {
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
		List<Element> entityElems = root.elements("entity");
		for (Element entityElem : entityElems) {
			Element tableElem = entityElem.element("table");
			String table = tableElem.attributeValue("name");
			setTable(table);
			Element attributesElem = tableElem.element("attributes");
			Element idElem = attributesElem.element("id");
			String idProperty = idElem.attributeValue("name");
			setIdProperty(idProperty);
			String idColumn = extractColumn(idElem, idProperty);
			setIdColumn(idColumn);
			List<Element> basicElems = attributesElem.elements("basic");
			String[] properties = new String[basicElems.size()];
			setProperties(properties);
			String[] columns = new String[basicElems.size()];
			setColumns(columns);
			Map<String, String> propertyColumnMapping = new HashMap<String, String>();
			Map<String, String> columnPropertyMapping = new HashMap<String, String>();
			int idx = 0;
			for (Element basicElem : basicElems) {
				properties[idx] = basicElem.attributeValue("name");
				columns[idx] = extractColumn(basicElem, properties[idx]);
				propertyColumnMapping.put(properties[idx], columns[idx]);
				columnPropertyMapping.put(columns[idx], properties[idx]);
				idx++;
			}
			setPropertyColumnMapping(propertyColumnMapping);
			setColumnPropertyMapping(columnPropertyMapping);
		}
	}

	private String extractColumn(Element e, String name) {
		Element columnElem = e.element("column");
		if (columnElem != null) {
			String column = columnElem.attributeValue("name");
			if (column == null || column.length() == 0) {
				throw new IllegalArgumentException(
						"column's name must not be empty");
			}
			return column;
		}
		return name;
	}
}
