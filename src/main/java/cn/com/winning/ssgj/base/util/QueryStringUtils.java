package cn.com.winning.ssgj.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("rawtypes") 
public class QueryStringUtils {
	public static final String serialize(HttpServletRequest request, String[] filterParameterNames) {
		Enumeration e = request.getParameterNames();

		List<String> queryStrings = new ArrayList<String>();
		String filterParameterNamesString = "," + StringUtils.join(filterParameterNames, ",") + ",";
		String ne = "";
		while (e.hasMoreElements()) {
			ne = (String) e.nextElement();
			if (((filterParameterNamesString == null) || (filterParameterNamesString.indexOf("," + ne + ",") == -1))
					&& (StringUtils.isNotBlank(request.getParameter(ne)))) {
				queryStrings.add(ne + "=" + request.getParameter(ne));
			}
		}
		return StringUtils.join(queryStrings.toArray(), "&");
	}

	public static final String encodeSerializedQueryString(String serializedQueryString, String encoding) throws UnsupportedEncodingException {
		String[] kvs = StringUtils.split(serializedQueryString, "&");
		List<String> encodedKVs = new ArrayList<String>();

		for (String kv : kvs) {
			String[] k_v = StringUtils.split(kv, "=");
			encodedKVs.add(k_v[0] + "=" + URLEncoder.encode(k_v[1], "UTF-8"));
		}
		return StringUtils.join(encodedKVs.toArray(), "&");
	}

	public static final String encodeSerializedQueryString(String serializedQueryString) throws UnsupportedEncodingException {
		return encodeSerializedQueryString(serializedQueryString, "UTF-8");
	}

	public static final String toQueryString(Map<String, String> map, String prefix) throws UnsupportedEncodingException {
		if (prefix == null) {
			prefix = "?";
		}
		Set<String> keySet = map.keySet();
		String[] kvs = new String[map.size()];

		int i = 0;
		for (String key : keySet) {
			kvs[i] = (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));
			i++;
		}
		return prefix + StringUtils.join(kvs, "&");
	}
}