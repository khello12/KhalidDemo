package com.khalid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khalid.model.Address;
import com.khalid.model.AirportDetails;
import com.khalid.model.FlightDetails;
import com.khalid.util.KhalidUtil;

@SpringBootApplication
@RestController
public class DemoApplication<flightDetails> {
	@Autowired(required = true)
	private AppProperties appProperties;

	private static HashMap<String, AirportDetails> AIRPORT_LIST = null;

	private static Logger logger = Logger.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/giveMeSalam")
	public String greetingMethod(@RequestParam(value = "name", defaultValue = "World") String name) {
		logger.info("/giveMeSalam: " + name);
		return "Salam " + name;
	}

	@RequestMapping(value = "/lookUp", method = RequestMethod.GET)
	public AirportDetails lookUpMethod(@RequestParam(value = "code", defaultValue = "MCO") String code) {
		HashMap<String, AirportDetails> cache = getAirportList();
		AirportDetails airportDetails = cache.get(code.toUpperCase());

		logger.info("/lookUp: " + code);
		// airportDetails = new AirportDetails(code, "?? International Airport",
		// "???");
		return airportDetails;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ArrayList<AirportDetails> getAllMethod() {
		HashMap<String, AirportDetails> cache = getAirportList();

		ArrayList<AirportDetails> result = new ArrayList<AirportDetails>();

		logger.info("/getAll");

		for (AirportDetails airportDetails : cache.values()) {
			result.add(airportDetails);
		}

		return result;

	}
	
	@RequestMapping(value = "/deleteAirport", method = RequestMethod.DELETE)
	public ArrayList<AirportDetails> deleteAirportMethod(@RequestParam(value = "code") String code) {
		
		HashMap<String, AirportDetails> cache = getAirportList();

		cache.remove(code);
		
		ArrayList<AirportDetails> result = new ArrayList<AirportDetails>();

		logger.info("/deleteAirport" + code);

		for (AirportDetails airportDetails : cache.values()) {
			result.add(airportDetails);
		}

		return result;

	}

	@RequestMapping(value = "/lookUpByDesc", method = RequestMethod.GET)
	public ArrayList<AirportDetails> lookUpByDescMethod(@RequestParam(value = "keyword") String keyword) {
		HashMap<String, AirportDetails> cache = getAirportList();
		logger.info("/lookUpByDesc: " + keyword);

		ArrayList<AirportDetails> result = new ArrayList<AirportDetails>();
		String keywordUpper = keyword.toUpperCase();

		for (AirportDetails airportDetails : cache.values()) {
			// ...
			String descUpper = airportDetails.getName().toUpperCase();

			if (descUpper.indexOf(keywordUpper) >= 0) {
				result.add(airportDetails);
			}
		}

		logger.info("Lookup Desc Result Size: " + result.size());

		return result;
	}

	private HashMap<String, AirportDetails> getAirportList() {
		if (AIRPORT_LIST != null) {
			return AIRPORT_LIST;
		}

		logger.info("Building cache from file......");

		BufferedReader br = null;
		try {
			String filePath = appProperties.getProperty("airportlist.file.path");
			br = new BufferedReader(new FileReader(filePath));

			String line = br.readLine();

			AIRPORT_LIST = new HashMap<String, AirportDetails>();

			while (line != null) {
				line = br.readLine();

				if (line != null && line.indexOf("\t") > 2) {
					String values[] = line.split("\t");
					if (values != null && values.length == 2) {
						String airportCode = values[1];
						String airportName = values[0];
						if (!KhalidUtil.isNullOrEmpty(airportCode)) {
							Address address = new Address("Squirrel Bend Dr.", "Central Ave.", "Toledo", "OH", "43617");
							String carriersFromPropFile = appProperties.getCarriers(airportCode);
							Integer outboundsFromPropFile = appProperties.getOutboundOrInbound(airportCode, true);
							Integer inboundsFromPropFile = appProperties.getOutboundOrInbound(airportCode, false);
							ArrayList<String> carriersList = KhalidUtil.parseCSV(carriersFromPropFile);
							FlightDetails fDetails = new FlightDetails(carriersList, inboundsFromPropFile, outboundsFromPropFile);
							AIRPORT_LIST.put(airportCode, new AirportDetails(airportCode, airportName, null, address, fDetails));
						}
					}
				}
			}

			return AIRPORT_LIST;

		} catch (Exception e) {
			logger.error("Failed to load cache from file", e);
			AIRPORT_LIST = null;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new HashMap<String, AirportDetails>();

	}

	// @RequestMapping(value="/enterPhone", method = RequestMethod.POST)

	// We cannot do "POST" from the browser, we only can do "POST" from the REST
	// tool on the example Firefox

	@RequestMapping(value = "/enterInfo", method = RequestMethod.POST)
	public String enterPhoneMethod(@RequestParam(value = "phone") String phoneNo, @RequestParam(value = "code") String code) {

		logger.info("/enterPhone: " + phoneNo);

		HashMap<String, AirportDetails> cache = getAirportList();
		AirportDetails airportDetails = cache.get(code.toUpperCase());

		if (airportDetails != null) {
			airportDetails.setPhoneNumber(phoneNo);
			return "{'status' : 'ok'}";
		} else {
			return "{'status' : 'failed'}";
		}

	}

	@RequestMapping("/add")
	public int addMethod(@RequestParam(value = "x") Integer a, @RequestParam(value = "y") Integer b) {
		return (a + b);
	}

}