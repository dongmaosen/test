package org.rookie.test.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * http客户端工具类
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class HttpClient {
	String serverUrl = "http://47.93.244.13:9090/dfs/ajaxUpload";

	public void updateFile(File file) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpResponse httpResponse = null;
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000)
				.build();
		HttpPost httpPost = new HttpPost(serverUrl);
		httpPost.setConfig(requestConfig);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

		// multipartEntityBuilder.addBinaryBody("file",
		// file,ContentType.create("image/png"),"abc.pdf");
		// 当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
		// multipartEntityBuilder.addBinaryBody("file",file,ContentType.create("application/octet-stream"),"abd.pdf");
		multipartEntityBuilder.addBinaryBody("file", file);
		// multipartEntityBuilder.addPart("comment", new StringBody("This is comment",
		// ContentType.TEXT_PLAIN));
		HttpEntity httpEntity = multipartEntityBuilder.build();
		httpPost.setEntity(httpEntity);

		httpResponse = httpClient.execute(httpPost);
		HttpEntity responseEntity = httpResponse.getEntity();
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
			StringBuffer buffer = new StringBuffer();
			String str = "";
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}

			System.out.println(buffer.toString());
		}

		httpClient.close();
		if (httpResponse != null) {
			httpResponse.close();
		}
	}


	public static void main(String[] args) throws Exception {
		File file = new File("d:/logo.png");
		HttpClient cli = new HttpClient();
		cli.updateFile(file);

	}
}
