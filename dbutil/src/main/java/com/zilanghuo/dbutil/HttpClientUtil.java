package com.zilanghuo.dbutil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;

public class HttpClientUtil {


	private static CloseableHttpClient httpClient;
	private HttpUriRequest currRequest;
	private static PoolingHttpClientConnectionManager cm = null;
	private static HttpRequestRetryHandler httpRequestRetryHandler = null;
	private static SocketConfig socketConfig = null;

	/**
	 * HttpClient 4.5 版本之后，超时时间超过20s是无效的，最大为20s 可通过增加重试次数来弥补
	 */
	private static final int CONN_TIMEOUT = 10000;
	private static final int SOCKET_TIMEOUT = 10000;
	private static final int RETRY_TIMES = 1;

	/**
	 * 初始化http连接池
	 */
	static {
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(300);
		cm.setDefaultMaxPerRoute(100);

		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount > RETRY_TIMES) {
					return false;
				}

				if (exception instanceof NoHttpResponseException) {
					return true;
				}
				if (exception instanceof SocketTimeoutException)
					return true;
				if (exception instanceof SocketException)
					return true;

				if (exception instanceof SSLHandshakeException)// 不重试SSL握手异常
					return false;
				if (exception instanceof InterruptedIOException)// 超时
					return false;
				if (exception instanceof UnknownHostException)// 目标服务器不可达
					return false;
				if (exception instanceof SSLException)// SSL握手异常
					return false;

				if (exception instanceof ConnectTimeoutException)
					return false;

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就重试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};

		socketConfig = SocketConfig.custom().setSoKeepAlive(true).setSoLinger(SOCKET_TIMEOUT)
				.setSoTimeout(SOCKET_TIMEOUT).setTcpNoDelay(true).build();

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONN_TIMEOUT)
				.setConnectTimeout(CONN_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setCookieSpec(CookieSpecs.DEFAULT)
				.build();
		httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(httpRequestRetryHandler)
				.setDefaultRequestConfig(requestConfig).setDefaultSocketConfig(socketConfig).build();
	}

	public static String get(String url, Map<String, Object> headers, String method) {

		Request request = null;
		if (method.toLowerCase().equals("put")) {
			request = Request.Put(url);
		}else if (method.toLowerCase().equals("get")) {
			request = Request.Get(url);
		}
		request.socketTimeout(5000);
		request.connectTimeout(5000);
		if (headers != null && headers.size() > 0) {
			for (String headerKey : headers.keySet()) {
				request.addHeader(headerKey, String.valueOf(headers.get(headerKey)));
			}
		}

		try {
			return request.execute().returnContent().asString(Charset.forName("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String postJson(String url, Map<String, Object> headers, String postjson) {

		CloseableHttpResponse response = null;
		HttpUriRequest httpUriRequest = null;
		try {
			Builder builder = RequestConfig.copy(((Configurable) httpClient).getConfig());
			RequestConfig requestConfig = builder.build();
			httpUriRequest = new HttpPost(url);
			((HttpPost) httpUriRequest).setConfig(requestConfig);
			httpUriRequest.setHeader("Content-Type", "application/json");
			if (headers != null && headers.size() > 0) {
				for (String name : headers.keySet()) {
					httpUriRequest.setHeader(name.toString(), headers.get(name).toString());
				}
			}
			((HttpPost) httpUriRequest).setEntity(new StringEntity(postjson, Charset.forName("utf-8")));

			response = httpClient.execute(httpUriRequest);

			int code = response.getStatusLine().getStatusCode();
			if (code != 200) {
				httpUriRequest.abort();
				return null;
			}

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				byte[] bytes = EntityUtils.toByteArray(entity);
				return new String(bytes, "utf-8");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}


	
	/*public static void main(String [] args) throws ClientProtocolException, IOException, InterruptedException {
		*//*for (int i = 0; i < 1; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(HttpClientUtil.postJson("http://10.67.84.189:9090/silkworm/worker/start", null, "{\"retryType\":0,\"taskRecordId\":304605}"));
				}
			}).start();
		}*//*

		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184569,\"jobInstId\": 132031,\"jobId\": 787}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184171,\"jobInstId\": 131633,\"jobId\": 1001}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184217,\"jobInstId\": 131679,\"jobId\": 1003}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184366,\"jobInstId\": 131828,\"jobId\": 853}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184371,\"jobInstId\": 131833,\"jobId\": 856}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184571,\"jobInstId\": 132033,\"jobId\": 788}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184206,\"jobInstId\": 131668,\"jobId\": 1012}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184540,\"jobInstId\": 132002,\"jobId\": 1082}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184567,\"jobInstId\": 132029,\"jobId\": 786}"));
		System.out.println(HttpClientUtil.postJson("http://10.210.14.61:8230/worker/job/executors/stopJob", null, "{\"jobSheetId\": 184149,\"jobInstId\": 131611,\"jobId\": 1106}"));

	}*/
}
