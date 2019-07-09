package org.rookie.test.http;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.UUID;

/**
 *
 *
 * Author: 不二
 *
 * Copyright @ 2019
 * 
 */
public class Test {
	public static void main(String[] args) throws Exception {
		String postTarget = "http://47.93.244.13:9090/dfs/ajaxUpload";
		URL url = new URL(postTarget);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		String boundary = UUID.randomUUID().toString();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

		DataOutputStream request = new DataOutputStream(connection.getOutputStream());

		request.writeBytes("--" + boundary + "\r\n");
		request.writeBytes("Content-Disposition: form-data; name=\"description\"\r\n\r\n");
		request.writeBytes("abc" + "\r\n");

		request.writeBytes("--" + boundary + "\r\n");
		request.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"logo.png\"\r\n\r\n");
		DataInputStream ds = new DataInputStream(new FileInputStream(new File("D://logo.png")));
		byte[] buf = new byte[1024];
		while (ds.read(buf) != -1) {			
			request.write(buf);
		}
		ds.close();
		request.writeBytes("\r\n");

		request.writeBytes("--" + boundary + "--\r\n");
		request.flush();
		int respCode = connection.getResponseCode();
		System.out.println(respCode);
		BufferedReader br = new BufferedReader( new InputStreamReader( connection.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.print(line);
		}
	}

}
