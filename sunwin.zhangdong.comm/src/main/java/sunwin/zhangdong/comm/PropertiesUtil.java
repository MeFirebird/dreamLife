package sunwin.zhangdong.comm;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Component
public class PropertiesUtil {

	private static Properties prop;

	private static Logger log = Logger.getLogger(PropertiesUtil.class);


	/**
	 *  初始化Properties对象
	 */
	private static void init() {

		prop = new Properties();
		try {
			// 取得该Class对象的类装载器  类装载器负责从Java字符文件将字符流读入内存，并构造Class类对象
			prop.load(new InputStreamReader(PropertiesUtil.class.getClassLoader()
					// getResourceAsStream:将properties文件转换为指定格式编码的流
					.getResourceAsStream("config.properties"), "UTF-8"));
		} catch (IOException e) {
			log.error("Error loading file!-->config.properites");
			e.printStackTrace();
		}
	}

	/**
	 * 根据key获取value    Properties继承自Hashtable：方法继承
	 * @param key
	 * @return
     */
	public static String getProperty(String key) {
		if (prop == null) {
			init();
		}
		String value = prop.get(key).toString();
		return value;
	}
}
