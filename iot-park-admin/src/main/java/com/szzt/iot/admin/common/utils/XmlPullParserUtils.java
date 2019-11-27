package com.szzt.iot.admin.common.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class XmlPullParserUtils {

	/**
	 * 返回的是一个Map，当然也可以使用一个Domain来接收返回的内容
	 * 
	 * @param reader
	 * @return
	 * @throws @throws
	 *             XmlPullParserException
	 */
	public static Map<String, String> xmlPullParser(Reader reader) {

		Map<String, String> map = new HashMap<>();
		try {
			//通过工厂得到一个对象
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			// 把参数设置到parser
			parser.setInput(reader);

			/**
			 * START_DOCUMENT = 0;==>文档开始 <xml> END_DOCUMENT = 1;==》文档结束 </xml>
			 * START_TAG = 2;==》标签的开始 <ToUserName> END_TAG = 3;==》标签的结束
			 * </ToUserName>
			 */

			//事件类型
			int eventType = parser.getEventType();
		   //不是文档结束：</xml>
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// 不是文档的结束
				String name = parser.getName();// 节点的值===》就是map的key
				if (!"xml".equals(name) && eventType == XmlPullParser.START_TAG) {
					// 当前的name不是xml(就是文档的开始)并且是标签的开始：<CreateTime>1523072470</CreateTime>
					//<MsgType><![CDATA[voice]]></MsgType>
					//进来：得到value
					String text = parser.nextText();// value  标签开始的下一个文本值
					map.put(name, text);
				}
				// 得到下一个节点
				eventType = parser.next();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}
	
	/**
	 * map的解析
	 * @param map
	 */
	public static void parseMap(Map<String, String> map) {
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			String key = next.getKey();
			String value = next.getValue();
			System.out.println("=====================");
			System.out.println("key:" + key);
			System.out.println("value:" + value);
		}
		
	}
	  

	public static void main(String[] args) {
		String str = "<xml><ToUserName><![CDATA[gh_55e547acb71e]]></ToUserName><FromUserName><![CDATA[o9cEf0iZn8aKK4-MKWmqO2fs36xE]]></FromUserName><CreateTime>1523072399</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[大家好]]></Content><MsgId>6541546143579028417</MsgId></xml>";
		StringReader sr = new StringReader(str);
		Map<String, String> map = XmlPullParserUtils.xmlPullParser(sr);
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			String key = next.getKey();
			String value = next.getValue();
			System.out.println("=====================");
			System.out.println("key:" + key);
			System.out.println("value:" + value);
		}

	}

}
