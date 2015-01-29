//package com.nd.rock.common.json;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//
//import com.fasterxml.jackson.core.JsonEncoding;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class FastJsonTest {
//	
//	private static String tempStr = null;
//	
//	@Test
//	public void fromBean2Json() {
//		BeanClass beanClass = new BeanClass();
//		beanClass.setIntValue(100);
//		beanClass.setString("string_value");
//		List<String> list = new ArrayList<>();
//		list.add("a");
//		list.add("b");
//		Map<String, String> map = new HashMap<>();
//		map.put("1", "aa");
//		map.put("2", "b");
//		beanClass.setList(list);
//		beanClass.setMap(map);
//		ObjectMapper mapper = new ObjectMapper();
//        
//		try {
//			JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
//			jsonGenerator.writeObject(beanClass);
//			System.out.println("int");
////			jsonGenerator.close();
////			System.out.println(jsonGenerator.toString());
//			
////			tempStr = jsonGenerator.toString();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	public void fromJson2Bean() {
//		String str = "{\"string\":\"string_value\",\"intValue\":100,\"list\":[\"a\",\"b\"],\"map\":{\"2\":\"b\",\"1\":\"aa\"}}";
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			BeanClass beanClass = mapper.readValue(str, BeanClass.class);
//			System.out.println(beanClass);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public class BeanClass {
//		private String string;
//	    private int intValue;
//	    private List<String> list;
//	    private Map<String,String> map;
//		public String getString() {
//			return string;
//		}
//		public void setString(String string) {
//			this.string = string;
//		}
//		public int getIntValue() {
//			return intValue;
//		}
//		public void setIntValue(int intValue) {
//			this.intValue = intValue;
//		}
//		public List<String> getList() {
//			return list;
//		}
//		public void setList(List<String> list) {
//			this.list = list;
//		}
//		public Map<String, String> getMap() {
//			return map;
//		}
//		public void setMap(Map<String, String> map) {
//			this.map = map;
//		}
//	}
//
//}
