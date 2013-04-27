package models;

import java.io.IOException;
import java.net.URL;

import lib.APIException;
import lib.Api;
import models.api.responses.DateHistogramResponse;
import models.api.results.DateHistogramResult;

public class MessageCountHistogram {

	private final String interval;
	private final int timerange;
	
	public MessageCountHistogram(String interval, int timerange) {
		this.interval = interval;
		this.timerange = timerange;
	}
	
	public DateHistogramResult histogram() throws IOException, APIException {
		String i = Api.urlEncode(interval);
		URL url = Api.buildTarget("count/histogram?interval=" + i + "&timerange=" + timerange);
		
		DateHistogramResponse response = Api.get(url, new DateHistogramResponse());
		return new DateHistogramResult("match_all", response.time, response.interval, response.results);
	}
	
}
