package Entity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;

import java_cup.internal_error;
import net.sf.json.JSONArray;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import BasicOps.FileOps;

public class Annotation {
	public Logger LOG = Logger.getLogger(this.getClass());
	private String inputPath;
	private String outputPath;
	private HttpClient client;
	private int serviceNum;
	private String serviceURI;

	public Annotation(){
		client = new HttpClient();
		serviceNum = AnnotationConst.serviceList.length;
	}
	
	public Annotation(String in, String out, int n) {
		client = new HttpClient();
		inputPath = in;
		outputPath = out;
		serviceNum = n;
		if (n < AnnotationConst.serviceList.length) {
			serviceURI = AnnotationConst.serviceList[n];
		} else {
			serviceNum = 0;
			serviceURI = AnnotationConst.serviceList[0];
		}

	}

	private String queryBuilder(String base_uri, String text, String paras) {
		StringBuilder sb = new StringBuilder();
		sb.append(base_uri);

		// add text
		if (serviceNum == 4) {
			sb.append("?source=");
		} else {
			sb.append("?text=");
		}

		try {
			sb.append(URLEncoder.encode(text, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			LOG.debug("URLEncoder!");
			e.printStackTrace();
		}

		if (paras.length() > 0) {
			sb.append(paras);
		}

		return sb.toString();
	}

	private String queryBuilder(String text) {
		return queryBuilder(serviceURI, text,
				AnnotationConst.servicetParas[serviceNum]);
	}
	
	public String queryResult(String query) {

		String responseBody = null;
		GetMethod getMethod = new GetMethod(query.toString());
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		if (serviceNum < 4) {
			getMethod.addRequestHeader(new Header("Accept", "application/json"));
		}
		int statusCode;
		try {
			statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}

			// queryResult
			responseBody = getMethod.getResponseBodyAsString();

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		// LOG.info(responseBody);
		return responseBody;
	}
	
//	public String spotlightSpot(String response) throws JSONException{
//		JSONObject resJson = new JSONObject(response);
//		JSONArray 
//	}
	
	public void annotation(){
		LinkedList<String> texts = FileOps.LoadFilebyLine(inputPath);
		
		JsonFormatter formatter = new JsonFormatter();
		int count = 0;
		for(String text : texts){
			++count;
//			if(count == 135 || count == 140){
//				continue;
//			}
//			System.out.println(queryResult(queryBuilder(text)));
//			break;
//			String response = queryResult(queryBuilder(text));
			String response = formatter.format(queryResult(queryBuilder(text)));
//			System.out.println(formatter.format(response));
//			System.out.println(response);
			
			String filename = outputPath + count + ".txt";
			FileOps.SaveFile(filename, response);
//			break;
		}
	}
	
}
