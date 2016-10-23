package com.stockbone.utils.reflect;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stockbone.utils.valid.Preconditions;

public class BeanWrapper {

	private static Logger logger = LoggerFactory.getLogger(BeanWrapper.class);

	public static <T> T wrapper(Class<T> clazz, Map<String, Object> map) {
		Preconditions.checkNotNull(clazz);
		Preconditions.checkNotNull(map);
		T result = null;
		try {
			result = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Class fieldClass = getPackageType(field.getType());
				String fieldName = field.getName().toLowerCase();
				Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, Object> entry = iter.next();
					String key = entry.getKey().toLowerCase().replaceAll("_", "");
					Object value = entry.getValue();
					if (fieldName.equals(key) && value.getClass() == fieldClass) {
						field.setAccessible(true);
						field.set(result, value);
						field.setAccessible(false);
						break;
					}
				}
			}
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 如果是基本类型，获取封装类型
	 * @param clazz
	 * @return
	 */
	private static Class getPackageType(Class clazz){
		if(!clazz.isPrimitive()){
			return clazz;
		}
		Class result = null;
		String className = clazz.getName();
		if(className.equals("boolean")){
			result = Boolean.class;
		}
		if(className.equals("char")){
			result = Character.class;
		}
		if(className.equals("byte")){
			result = Byte.class;
		}
		if(className.equals("short")){
			result = Short.class;
		}
		if(className.equals("int")){
			result = Integer.class;
		}
		if(className.equals("long")){
			result = Long.class;
		}
		if(className.equals("float")){
			result = Float.class;
		}
		if(className.equals("double")){
			result = Double.class;
		}
		return result;
	}

}
