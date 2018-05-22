package org.mail.util;

import com.google.common.collect.Lists;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import java.util.Date;
import java.util.List;

/**
 * 日志工具类
 * 
 * @author VIC
 *
 */
public class LoggerUtil {

	private static final String FQCN = LoggerUtil.class.getName();

	private Logger logger;

	public <T> LoggerUtil(Class<T> t) {
		logger = Logger.getLogger(t);
	}

	public static <T> LoggerUtil getLogger(Class<T> t) {
		return new LoggerUtil(t);
	}

	public boolean isEnabledFor(Priority level) {
		return logger.isEnabledFor(level);
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public void trace(String message, Object... args) {
		_Log(Level.TRACE, null, message, args);
	}

	public void trace(Throwable t, String message, Object... args) {
		_Log(Level.TRACE, t, message, args);
	}

	public void debug(String message, Object... args) {
		_Log(Level.DEBUG, null, message, args);
	}

	public void debug(Throwable t, String message, Object... args) {
		_Log(Level.DEBUG, t, message, args);
	}

	public void info(String message, Object... args) {
		_Log(Level.INFO, null, message, args);
	}

	public void info(Throwable t, String message, Object... args) {
		_Log(Level.INFO, t, message, args);
	}

	public void warn(Throwable t, String message, Object... args) {
		_Log(Level.WARN, t, message, args);
	}

	public void warn(String message, Object... args) {
		_Log(Level.WARN, null, message, args);
	}

	public void error(String message, Object... args) {
		_Log(Level.ERROR, null, message, args);
	}

	public void fatal(Throwable throwable, String message, Object... args) {
		_Log(Level.FATAL, throwable, message, args);
	}

	public void fatal(String message, Object... args) {
		_Log(Level.FATAL, null, message, args);
	}

	public void error(Throwable throwable, String message, Object... args) {
		_Log(Level.ERROR, throwable, message, args);
	}

	private void _Log(Level level, Throwable throwable, String message, Object... args) {
		logger.log(FQCN, level, _BuildMessage(message, args), throwable);
	}

	private final static String _BuildMessage(String message, Object... args) {
		List<String> _args = Lists.newArrayList();
		for (Object arg : args) {
			if (null == arg) {
				_args.add("null");
			} else if (_IsPrimitive(arg.getClass())) {
				_args.add(arg.toString());
			} else {
				_args.add(JsonUtil.toJson(arg));
			}
		}
		if (args == null || args.length == 0) {
			return message;
		}
		return String.format(message, _args.toArray());
	}

	private final static boolean _IsPrimitive(Class<?> cls) {
		return cls.isPrimitive() || PRIMITIVE_CLASSES.contains(cls);
	}

	private final static List PRIMITIVE_CLASSES = Lists.newArrayList(Long.class, Integer.class, String.class,
			Date.class, java.sql.Date.class, java.sql.Timestamp.class);

}
