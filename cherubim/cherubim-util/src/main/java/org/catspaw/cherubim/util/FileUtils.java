package org.catspaw.cherubim.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具
 */
public final class FileUtils {

	// 默认的读入块大小
	private static final int	DEFAULT_READ_BLOCK_SIZE	= 4096;

	private FileUtils() {
	}

	/**
	 * 文件copy.
	 * @param src 源文件
	 * @param dest 目标文件
	 * @param cover 是否覆盖写入
	 * @throws IOException
	 */
	public static void copy(File src, File dest, boolean cover) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		byte[] buf = new byte[DEFAULT_READ_BLOCK_SIZE];
		int i;
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dest);
			while ((i = fis.read(buf)) > 0) {
				fos.write(buf, 0, i);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}

	/**
	 * 文件copy.
	 * @param src源文件
	 * @param dest目标文件
	 * @param cover是否覆盖写入
	 * @throws IOException
	 */
	public static void copy(String src, String dest, boolean cover) {
		File fsrc = new File(src);
		File fdest = new File(dest);
		copy(fsrc, fdest, cover);
	}

	/**
	 * 文件COPY包含子目录.
	 * @param src 源目录
	 * @param dest 目标目录
	 * @param cover 是否覆盖写入
	 * @throws IOException
	 */
	public static void copyTree(File src, File dest, boolean cover) {
		if (src.isFile()) {
			copy(src, dest, cover);
		} else {
			File[] children = src.listFiles();
			for (int i = 0; i < children.length; i++) {
				File f = new File(dest, children[i].getName());
				if (children[i].isDirectory()) {
					f.mkdirs();
					copyTree(children[i], f, cover);
				} else {
					copy(children[i], f, cover);
				}
			}
		}
	}

	/**
	 * 文件COPY包含子目录.
	 * @param src 源目录
	 * @param dest 目标目录
	 * @param cover 是否覆盖写入
	 * @throws IOException
	 */
	public static void copyTree(String src, String dest, boolean cover) throws IOException {
		File fsrc = new File(src);
		File fdest = new File(dest);
		copyTree(fsrc, fdest, cover);
	}

	/**
	 * 文件MOVE（包含子目录）.
	 * @param src源路径
	 * @param dest目标路径
	 * @param cover是否覆盖写入
	 * @throws IOException
	 */
	public static void moveTree(File src, File dest, boolean cover) throws IOException {
		copyTree(src, dest, cover);
		deleteTree(src);
	}

	/**
	 * 删除文件、目录包括子目录.
	 * @param f FILE对象
	 */
	public static void deleteTree(File f) {
		File[] children = f.listFiles();
		if (children != null && children.length != 0) {
			for (int i = 0; i < children.length; i++) {
				deleteTree(children[i]);
			}
		}
		f.delete();
	}

	/**
	 * 删除文件、目录（包含子目录）.
	 * @param path路径
	 */
	public static void deleteTree(String path) {
		File f = new File(path);
		deleteTree(f);
	}

	/**
	 * 文件MOVE.
	 * @param src源路径
	 * @param dest目录路径
	 * @param cover是否覆盖写入
	 * @throws IOException
	 */
	public static void move(String src, String destFolder, boolean cover) throws IOException {
		File fsrc = new File(src);
		String destFile = assembleFilePath(destFolder, getFileName(src));
		File fdest = new File(destFile);
		copy(fsrc, fdest, cover);
		fsrc.delete();
	}

	/**
	 * 文件查找.
	 * @param root
	 * @param filter
	 * @return
	 */
	public static boolean find(File root, FileFilter filter) {
		if (filter.accept(root)) {
			return true;
		}
		File[] children = root.listFiles();
		if (children == null || children.length == 0) {
			return false;
		}
		for (int i = 0; i < children.length; i++) {
			if (find(children[i], filter)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得文件的后缀名.
	 * @param fileName
	 * @return
	 */
	public static String getFileExtName(String fileName) {
		String ext = "";
		int dot = fileName.lastIndexOf(".");
		if (dot != -1) {
			ext = fileName.substring(dot + 1);
		}
		return ext;
	}

	/**
	 * 获得文件不包括后缀名.
	 * @param fileName
	 * @return
	 */
	public static String getFileNoExtName(String fileName) {
		String ext = "";
		int dot = fileName.lastIndexOf(".");
		if (dot != -1) {
			ext = fileName.substring(0, dot);
		}
		return ext;
	}

	/**
	 * 通过文件全路径获得文件名.
	 * @param strFilePath
	 * @return
	 */
	public static String getFileName(String strFilePath) {
		String fileName = strFilePath;
		int i = fileName.lastIndexOf(File.separator);
		if (i != -1) {
			fileName = fileName.substring(i + 1);
		}
		return fileName;
	}

	/**
	 * 根据目录名,文件名生成完整路径.
	 * @param strPath
	 * @param strFileName
	 * @return 完整路径
	 */
	public static String assembleFilePath(String strPath, String strFileName) {
		String path = strPath;
		if (path.charAt(path.length() - 1) != File.separatorChar) {
			path = path + File.separator;
		}
		return path + strFileName;
	}

	/**
	 * 通过文件全路径获得所在目录.
	 * @param strFilePath 文件名（包含路径）
	 * @return 所在目录
	 */
	public static String getFilePath(String fullFilePath) {
		String filePath = fullFilePath;
		String path = "";
		int i = filePath.lastIndexOf(File.separator);
		if (i != -1) {
			path = filePath.substring(0, i);
		}
		return path;
	}

	/**
	 * 获得目录下的文件列表.
	 * <p>
	 * <b>Example:</b> <blockquote>
	 * 
	 * <pre>
	 * String[] fileList = UitlFile.getFileList(&quot;c:/oracle/admin/kmis&quot;);
	 * for (int i = 0; i &lt; fileList.length; i++) {
	 * 	System.out.println(fileList[i]);
	 * }
	 * </pre>
	 * 
	 * </blockquote>
	 * @param directory 目录名,如果参数非目录则返回NULL
	 * @return String[] 文件列表（全路径）,参数错误或不存在文件时返回null
	 * @throws IOException
	 * @since BASE 0.1
	 */
	public static String[] getFileList(String directory) throws IOException {
		if (directory.charAt((directory.length() - 1)) == File.separatorChar) {
			directory = directory.substring(0, directory.length() - 1);
		}
		File path = new File(directory);
		if (!path.isDirectory())
			return null;
		List<String> rc = new ArrayList<String>();
		String[] list = path.list();
		for (int i = 0; i < list.length; i++) {
			list[i] = directory + File.separator + list[i];
			// 是目录则递归调用
			File item = new File(list[i]);
			if (item.isDirectory()) {
				String[] tmp = getFileList(list[i]);
				for (int j = 0; j < tmp.length; j++) {
					rc.add(tmp[j]);
				}
			} else {
				// 是文件则记录
				rc.add(list[i]);
			}
		}
		return rc.toArray(new String[rc.size()]);
	}

	/**
	 * 往文件中追加字符串.
	 * @param path
	 * @param info
	 * @return
	 */
	public static boolean appendString(String path, String info) {
		File file = new File(path);
		BufferedWriter writer = null;
		try {
			if (file.exists()) {
				writer = new BufferedWriter(new FileWriter(file, true));
			} else {
				writer = new BufferedWriter(new FileWriter(file));
			}
			writer.newLine();
			writer.write(info);
			return true;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}

	/**
	 * 文件读入字符串.
	 * @param srcfile File对象
	 * @return
	 */
	public static String readText(File file) {
		BufferedReader reader = null;
		try {
			StringBuilder builder = new StringBuilder();
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}

	/**
	 * 文件读入字符串.
	 * @param fpath 文件路径
	 * @return
	 */
	public static String readText(String path) {
		File file = new File(path);
		return readText(file);
	}

	/**
	 * 累加目录.
	 * @param sourceDir String
	 * @param appendDir String
	 * @return String
	 */
	public static String appendDirectory(String sourceDir, String appendDir) {
		if (appendDir.startsWith(File.separator)) {
			appendDir = appendDir.substring(1);
		}
		String rc = null;
		if (sourceDir.endsWith(File.separator)) {
			rc = sourceDir + appendDir;
		} else {
			rc = sourceDir + File.separator + appendDir;
		}
		return rc;
	}

	/**
	 * 将String写入文件
	 * @param data
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeText(String data, String filePath) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(data);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}

	/**
	 * 格式化文件路径
	 * @param path
	 * @return
	 */
	public static String formatPath(String path) {
		if (!path.endsWith(File.separator))
			path += File.separator;
		return path;
	}

	/**
	 * 新建文件
	 * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent String 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePath, String text, String charset) {
		File file = new File(filePath);
		if (!file.exists()) {
			File parentFile = file.getParentFile();
			parentFile.mkdirs();
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			text = new String(text.getBytes(), charset);
			writer.write(text);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}

	/**
	 * 新建目录
	 * @param folderPath String 如 c:/fqf
	 * @return boolean
	 */
	public void newFolder(String folderPath) {
		String filePath = folderPath;
		filePath = filePath.toString();
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.mkdir();
		}
	}
}
