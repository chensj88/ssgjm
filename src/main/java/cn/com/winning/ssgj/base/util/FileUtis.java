package cn.com.winning.ssgj.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class FileUtis {

	private final static String _ENCODING = "UTF-8";
	private final static String _REG = "<col name=\"([^\"]*)\" [^>]*>([^<]*)</col>";
	private final static String _REG_EASY_UI = "<th data-options=\"field:'([^\"]*)',[^>]*>([^<]*)</th>";

	public static HashSet<String> readTplFile(String filePath, boolean isEasyUITpl) {
		HashSet<String> excludes = new HashSet<String>();
		if (StringUtils.isBlank(filePath))
			return excludes;
		try {
			Pattern p = (isEasyUITpl) ? Pattern.compile(_REG_EASY_UI) : Pattern.compile(_REG);
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), _ENCODING);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					Matcher m = p.matcher(lineTxt);
					if (m.find())
						excludes.add(m.group(1));
				}
				read.close();
			}
			if (excludes.size() > 0 && isEasyUITpl) {
				excludes.add("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excludes;

	}

	/**
	 * 获取时间戳文件名称
	 * @param filename 文件名，主要用于获取后缀
	 * @return
	 */
	public static String generateFileName(String filename){
		String suffix = filename.substring( filename.lastIndexOf("."));
		return  System.currentTimeMillis() + suffix;
	}

	/**
	 * 获取时间戳文件名
	 * @return
	 */
	public static String generateFileName(){
		return  System.currentTimeMillis() + "";
	}
}
