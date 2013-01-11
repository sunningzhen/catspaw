package org.catspaw.ppp.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

public class AttentionWriter implements ItemWriter<Map<String, Object>> {

	private static final Logger	logger	= LoggerFactory.getLogger(AttentionWriter.class);
	private JdbcTemplate		jdbcTemplate;

	@Override
	public void write(List<? extends Map<String, Object>> items) throws Exception {
		String sql = "insert into smp_attention(trading_date, secucode, stall, busi_code, volume, amount, cust_num) values(?,?,?,?,?,?,?)";
		List<Object[]> params = new ArrayList<Object[]>();
		for (Map<String, Object> item : items) {
			Object[] param = new Object[7];
			param[0] = item.get("TRADING_DATE");
			param[1] = item.get("SECUCODE");
			param[2] = item.get("STALL");
			param[3] = item.get("BUSI_CODE");
			param[4] = item.get("VOLUME");
			param[5] = item.get("AMOUNT");
			param[6] = item.get("CUST_NUM");
			params.add(param);
		}
		jdbcTemplate.batchUpdate(sql, params);
		logger.debug("insert attentions: " + items.size());
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
