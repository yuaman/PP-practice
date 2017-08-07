package com.etc.pp.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;

public class Common {

	public static int BASE_PATH;
	Logger logger = Logger.getAnonymousLogger();

	/**
	 * 静态代码段，一般来说用来做一些初始化工作
	 */
	static {

	}

	/**
	 * 非空验证
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		if (str == null) {
			return true;
		}

		if ("".equals(str.trim())) {
			return true;
		}

		return false;
	}

	/**
	 * 判断不是一个数字
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isNotANumber(String number) {

		if (isEmpty(number)) {
			return true;
		}

		for (int i = 0; i < number.length(); i++) {
			if (Contact.STANDARD_NUMBER.indexOf(number.substring(i, i + 1)) == -1) {
				return true;
			}
		}

		return false;

	}

	/**
	 * 创建一个文件夹
	 * 
	 * @param path
	 * @return true:创建成功/false:已经存在
	 * @throws IOException
	 */
	public static boolean createDirectory(String path) throws IOException {

		File file = new File(path);

		if (!file.exists()) {
			file.mkdir();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 读内容
	 * @param userInfoPath
	 * @return
	 * @throws IOException
	 */
	public static String readBuffer(String Path) throws IOException {

		Reader reader = new FileReader(Path);

		BufferedReader br = new BufferedReader(reader);

		StringBuffer sb = new StringBuffer();

		String str = null;

		str = br.readLine();

		while (str != null) {
			sb.append(str);
			str = br.readLine();
		}

		br.close();
		reader.close();

		return sb.toString();

	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isFileExist(String path) {
		File file = new File(path);

		if (file.exists()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 创建一个文件
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean createFile(String path) throws IOException {

		File file = new File(path);

		if (!file.exists()) {
			file.createNewFile();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 往文件里写内容
	 * 
	 * @param str
	 *            要写的内容
	 * @param path
	 *            文件的路径
	 * @param b 
	 * @throws IOException
	 */
	public static void writeZifu(String str, String path, boolean append) throws IOException {

		FileWriter fileWriter = new FileWriter(path,append);

		fileWriter.write(str);
		fileWriter.flush();
		fileWriter.close();
	}

	public void showFileInfo(File file) {

		logger.info("文件大小:" + file.length());
		logger.info("文件位置:" + file.getPath());
	}

	public void deleteFile(File file) {
		if (file.exists()) {
			file.delete();
			logger.info("文件已经被删除");
		}
	}
}
