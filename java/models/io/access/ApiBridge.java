package models.io.access;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import models.io.EventV3IO;
import models.io.InputMessageV3IO;
import com.google.gson.Gson;

public class ApiBridge {

	private static String rootApiUrl;
	private static String cookies;

	public static void connect(String rootApiUrl, String user, String password) throws IOException, ApiException {
		if (!rootApiUrl.endsWith("/")) {
			rootApiUrl+="/";
		}
		ApiBridge.rootApiUrl = rootApiUrl;
		String response = call("login?username="+user+"&tnc=true&password="+password, "GET", null);
		Logger.info("connected as %s",user);
	}
	
	public static String get(String url) throws IOException, ApiException {
		return call(url, "GET", null);
	}

	public static String post(String url, String body) throws IOException, ApiException {
		return call(url, "POST", body);
	}

	public static String put(String url) throws IOException, ApiException {
		return call(url, "PUT", null);
	}
	
	
	private static String call(String urlFragment, String method, String body) throws IOException, ApiException {
		URL url = new URL(rootApiUrl+urlFragment);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Accept", "application/json");
		if (cookies!=null && cookies.length()>0) {
			conn.setRequestProperty("Cookie", cookies);
		}
		
		if (body!=null) {
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", "" + Integer.toString(body.getBytes().length));
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream ());
			wr.writeBytes(body);
			wr.flush();
			wr.close();			
		}

		if (conn.getResponseCode() != 200) {
			throw new ApiException(conn.getResponseCode(), readResponseAsString(conn));
		}
		
		cookies = conn.getHeaderField("Set-Cookie");

		String response = readResponseAsString(conn);
		
		conn.disconnect();

		return response;
	}
	
	private static Reader readResponseAsReader(HttpURLConnection conn) throws IOException {
		InputStream is;
		if (conn.getResponseCode() >= 400) {
		    is = conn.getErrorStream();
		} else {
		    is = conn.getInputStream();
		}
		if (is==null) {
			return null;
		} else {
			return new InputStreamReader(is);
		}	
	}

	private static String readResponseAsString(HttpURLConnection conn)
			throws IOException {
		
		Reader reader = readResponseAsReader(conn);
		if (reader==null) {
			return "";
		}
		BufferedReader br = new BufferedReader(reader);
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = br.readLine()) != null) {
		    builder.append(aux);
		}
		return builder.toString();
	}

	public static EventV3IO postMessage(InputMessageV3IO msg) throws IOException, ApiException {
		String json = new Gson().toJson(msg);
		String response = post("insertMessage", json);
		EventV3IO ret = new Gson().fromJson(response, EventV3IO.class);
		return ret;
	}

}
