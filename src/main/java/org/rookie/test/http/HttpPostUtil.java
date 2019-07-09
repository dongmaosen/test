package org.rookie.test.http;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class HttpPostUtil {

	private final static char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();

	private URL url;
	private HttpURLConnection conn;
	private String boundary = null;
	private Map<String, String> textParams = new HashMap<String, String>();
	private Map<String, File> fileparams = new HashMap<String, File>();

	public HttpPostUtil(String url) throws Exception {
		this.url = new URL(url);
	}

	// 重新设置要请求的服务器地址，即上传文件的地址。
	public void setUrl(String url) throws Exception {
		this.url = new URL(url);
	}

	// 增加一个普通字符串数据到form表单数据中
	public void addTextParameter(String name, String value) {
		textParams.put(name, value);
	}

	// 增加一个文件到form表单数据中
	public void addFileParameter(String name, File value) {
		fileparams.put(name, value);
	}

	// 清空所有已添加的form表单数据
	public void clearAllParameters() {
		textParams.clear();
		fileparams.clear();
	}

	/**
	 * 发送数据到服务器
	 * 
	 * @return 一个字节包含服务器的返回结果的数组
	 * @throws Exception
	 */
	public byte[] send() throws Exception {
		initConnection();
		try {
			conn.connect();
		} catch (SocketTimeoutException e) {
			throw new Exception(e);
		}

		OutputStream connOutStream = new DataOutputStream(conn.getOutputStream());

		writeFileParams(connOutStream);
		writeStringParams(connOutStream);
		writesEnd(connOutStream);

		InputStream responseInStream = conn.getInputStream();
		ByteArrayOutputStream responseOutStream = new ByteArrayOutputStream();
		int len;
		byte[] bufferByte = new byte[1024];
		while ((len = responseInStream.read(bufferByte)) != -1) {
			responseOutStream.write(bufferByte, 0, len);
		}
		responseInStream.close();
		connOutStream.close();

		conn.disconnect();
		byte[] resultByte = responseOutStream.toByteArray();
		responseOutStream.close();
		return resultByte;
	}

	// 文件上传的connection的一些必须设置
	private void initConnection() throws Exception {
		StringBuffer buf = new StringBuffer("----");
		Random rand = new Random();
		for (int i = 0; i < 15; i++) {
			buf.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
		}
		this.boundary = buf.toString();

		conn = (HttpURLConnection) this.url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(3 * 60 * 1000); // 连接超时为10秒
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	}

	// 普通字符串数据
	private void writeStringParams(OutputStream out) throws Exception {
		Set<String> keySet = textParams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			String value = textParams.get(name);

			out.write(("--" + boundary + "\r\n").getBytes());
			out.write(("Content-Disposition: form-data; name=\"" + name + "\"\r\n").getBytes());
			out.write(("\r\n").getBytes());
			out.write((encode(value) + "\r\n").getBytes());
		}
	}

	// 文件数据
	private void writeFileParams(OutputStream out) throws Exception {
		Set<String> keySet = fileparams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			File value = fileparams.get(name);

			out.write(("--" + boundary + "\r\n").getBytes());
			out.write(("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + encode(value.getName())
					+ "\"\r\n").getBytes());
			out.write(("Content-Type: " + getContentType(value) + "\r\n").getBytes());
			out.write(("Content-Transfer-Encoding: " + "binary" + "\r\n").getBytes());

			out.write(("\r\n").getBytes());

			FileInputStream inStream = new FileInputStream(value);
			int bytes = 0;
			byte[] bufferByte = new byte[1024];
			while ((bytes = inStream.read(bufferByte)) != -1) {
				out.write(bufferByte, 0, bytes);
			}
			inStream.close();

			out.write(("\r\n").getBytes());
		}
	}

	// 添加结尾数据
	private void writesEnd(OutputStream out) throws Exception {
		out.write(("--" + boundary + "--" + "\r\n").getBytes());
		out.write(("\r\n").getBytes());
	}

	// 获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
	private String getContentType(File f) throws Exception {
		String fileName = f.getName();
		if (fileName.endsWith(".jpg")) {
			return "image/jpeg";
		} else if (fileName.endsWith(".png")) {
			return "image/png";
		}
		return "application/octet-stream";
	}

	// 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
	private String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}

	public static void main(String[] args) throws Exception {
		HttpPostUtil hp = new HttpPostUtil("http://47.93.244.13:9090/dfs/ajaxUpload");
		hp.addFileParameter("logo", new File("D://logo.png"));
		hp.send();
	}
}


