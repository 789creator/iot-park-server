package com.szzt.iot.sms;

import com.szzt.iot.common.utils.Result;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

class SmsTestDemo {

	/*public static String httpPostWithJSON(String url, String json) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
		
        httpPost.setEntity(new StringEntity(json, "utf-8"));
		httpPost.addHeader("Content-type","application/json; charset=utf-8");
		httpPost.setHeader("Connection", "Close");
		
        HttpResponse response = httpClient.execute(httpPost);
		
		if(response.getStatusLine().getStatusCode() == 200) {
			respContent = EntityUtils.toString(response.getEntity(),"UTF-8");
			System.out.println(respContent);
			if(respContent=="OK"){
				System.out.println("发送成功！");
			}
			return respContent;
		}
		System.out.println("发送失败！");
		return null;
    }*/

    public static void main(String[] args) {
        String url = "http://192.168.0.100/cgi-bin/NoticePush";
        try {
            JSONObject json = new JSONObject();
            String[] phoneArr = {"18520148903"};
            json.put("To", "18520148903");
            json.put("Type", "Call");
            json.put("Encoding", "UTF-8");
            json.put("Text", "温馨提示：这是一条网页测试通知，温度：23.5℃，时间：2019年10月8日16:02:16，测试完毕。");
            System.out.println(json.toString());
            Result result = sendNotePost(url, json.toString());
            System.out.println(result.getMsg());
            //TestDemo.httpPostWithJSON(url, json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Result sendNotePost(String url, String srecsNote) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(srecsNote);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return new Result().ok(result);
    }
}
