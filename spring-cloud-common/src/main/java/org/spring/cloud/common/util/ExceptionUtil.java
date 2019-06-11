package org.spring.cloud.common.util;

import org.springframework.util.StringUtils;

public class ExceptionUtil {
	public static String errorToCodeEN(Enum<?> error) {
		String errorName = error.name().toLowerCase();
		String[] rule = errorName.split("_");
		StringBuffer code = new StringBuffer();
		for (String str : rule) {
			code.append(StringUtils.capitalize(str));
		}
		return code.toString();
	}
}
