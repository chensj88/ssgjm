package cn.com.winning.ssgj.web.controller.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.winning.ssgj.base.util.QueryStringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 
 * @author Wu,ShangLong
 * @version 2016年4月1日 上午10:14:15
 */
public abstract class BaseSpringMvcMybatisController implements MessageSourceAware {
	private MessageSourceAccessor messageSource;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected static final String SYS_ENCODING = "UTF-8";

	static {
		registConverter();
	}

	public static void registConverter() {
		ConvertUtils.register(new BooleanConverter(), Boolean.class);
		ConvertUtils.register(new StringConverter(), String.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
	}

	protected void copyProperties(Object dest, Object orig) {
		if (orig != null)
			try {
				BeanUtils.copyProperties(dest, orig);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
	}

	protected String serialize(HttpServletRequest request, String[] ignoredParameterNames) throws UnsupportedEncodingException {
		return QueryStringUtils.serialize(request, ignoredParameterNames);
	}

	protected String encodeSerializedQueryString(String serializedQueryString, String encoding) throws UnsupportedEncodingException {
		return QueryStringUtils.encodeSerializedQueryString(serializedQueryString, encoding);
	}

	protected String encodeSerializedQueryString(String serializedQueryString) throws UnsupportedEncodingException {
		return encodeSerializedQueryString(serializedQueryString, "UTF-8");
	}

	protected void render(HttpServletResponse response, String text, String contentType) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			this.logger.error(e.getMessage(), e);
		}
	}

	protected void renderJson(HttpServletResponse response, String text) {
		render(response, text, "application/json;charset=".concat("UTF-8"));
	}

	protected void renderText(HttpServletResponse response, String text) {
		render(response, text, "text/plain;charset=".concat("UTF-8"));
	}

	protected void renderXml(HttpServletResponse response, String text) {
		render(response, text, "text/xml;charset=".concat("UTF-8"));
	}

	protected void renderJavaScript(HttpServletResponse response, String text) {
		String prefixJavaScript = "<script type=\"text/javascript\">";
		String suffixJavaScript = "</script>";
		StringBuffer sb = new StringBuffer();
		sb.append(prefixJavaScript).append(text).append(suffixJavaScript);
		render(response, sb.toString(), "text/html;charset=".concat("UTF-8"));
	}

	protected String getMessage(HttpServletRequest request, String code) {
		return getMessage(request, code, new Object[0]);
	}

	protected String getMessage(HttpServletRequest request, String code, Object[] args) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale = localeResolver.resolveLocale(request);
		if (this.messageSource == null)
			return "null";
		try {
			return this.messageSource.getMessage(code, args, locale);
		} catch (NoSuchMessageException e) {
		}
		return code;
	}

	@Override
	public void setMessageSource(MessageSource paramMessageSource) {
		this.messageSource = new MessageSourceAccessor(paramMessageSource);
	}
}