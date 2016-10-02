package com.khalid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.khalid.util.KhalidUtil;

@Component
public class AppProperties {
	@Autowired(required = true)
	private Environment env; //Auto-Wired which automatically reads application.properties and store in "env"

	public String getProperty(String propName) {
		return env.getProperty(propName);
	}
	
	public String getCarriers(String airportCode) {
		String value = env.getProperty(airportCode.toLowerCase() + ".carriers");
		return value;
	}

	public Integer getOutboundOrInbound(String airportCode, boolean isOutbound) {
		String key = isOutbound?"outbound":"inbound";
		String value = env.getProperty(airportCode.toLowerCase() + "." + key);
		if (!KhalidUtil.isNullOrEmpty(value)) {
			int result = Integer.parseInt(value);
			return result;
		}
		return null;
	}

}
