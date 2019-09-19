package com.epam.reportportal.migration.steps.bts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author <a href="mailto:pavel_bortnik@epam.com">Pavel Bortnik</a>
 */
@Component
public class BtsSqlParameterSourceProvider extends BeanPropertyItemSqlParameterSourceProvider<DBObject> {

	@Override
	public SqlParameterSource createSqlParameterSource(DBObject item) {
		String params;
		try {
			params = new ObjectMapper().writeValueAsString(item.get("params"));
			params = params.replaceAll("isRequired", "required");
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
		MapSqlParameterSource res = new MapSqlParameterSource();
		res.addValue("pr", item.get("projectId"));
		res.addValue("tp", item.get("integrationId"));
		res.addValue("en", false);
		res.addValue("params", params);
		res.addValue("cr", Optional.ofNullable(item.get("username")).orElse("mongodb"));
		res.addValue("nm", item.get("project"));
		return res;
	}
}