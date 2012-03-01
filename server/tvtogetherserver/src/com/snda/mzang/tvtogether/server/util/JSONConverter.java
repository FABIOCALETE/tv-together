package com.snda.mzang.tvtogether.server.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.snda.mzang.tvtogether.server.entry.IFieldMapper;
import com.snda.mzang.tvtogether.server.log.L;

public class JSONConverter {
	private final static Map<String, String> EMPTY_MAP = Collections.emptyMap();

	private final static Set<String> EMPTY_SET = Collections.emptySet();

	public static JSONObject convertBeanToJSON(Object bean, JSONObject json, Set<String> ignoredFields) throws Exception {

		if (json == null) {
			json = new JSONObject();
		}

		Map<String, String> fieldNameMap = null;

		if (bean instanceof IFieldMapper) {
			fieldNameMap = ((IFieldMapper) bean).getFieldMap();
		} else {
			fieldNameMap = EMPTY_MAP;
		}

		if (ignoredFields == null) {
			ignoredFields = EMPTY_SET;
		}

		Class<?> beanClz = bean.getClass();
		Field[] fields = beanClz.getDeclaredFields();

		for (Field field : fields) {
			String fieldName = field.getName();

			if (ignoredFields.contains(fieldName)) {
				continue;
			}

			Method getMethod = beanClz.getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
			if (getMethod.isAccessible() == false) {
				getMethod.setAccessible(true);
			}

			String key = fieldName;
			if (fieldNameMap.containsKey(key)) {
				key = fieldNameMap.get(key);
			}
			Object value = getMethod.invoke(bean);

			Object oldValue = json.get(key);
			if (oldValue != null) {
				L.warning("Value already exists for key " + key + ". Value is:" + oldValue);
			}
			json.put(key, value);
		}
		return json;
	}

	public static JSONObject convertListToJSON(List<Object> beans, String arrayName, JSONObject json, Map<String, String> fieldNameMap) throws Exception {
		if (json == null) {
			json = new JSONObject();
		}

		if (fieldNameMap == null) {
			fieldNameMap = EMPTY_MAP;
		}

		Object oldValue = json.get(arrayName);
		if (oldValue != null) {
			L.warning("Value already exists for array key " + arrayName + ". Value is:" + oldValue);
		}

		JSONArray array = new JSONArray();

		for (Object bean : beans) {
			JSONObject element = convertBeanToJSON(bean, null, null);
			array.put(element);
		}
		json.put(arrayName, array);
		return json;

	}
}