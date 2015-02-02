package com.nd.rock.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class QFileUtil {
	
	public static boolean createOrUpdate(String filePath, String content, String character) throws UnsupportedEncodingException, FileNotFoundException, IOException{
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();

		try (OutputStreamWriter osw = new OutputStreamWriter(
				new FileOutputStream(file), character)) {
			osw.write(content);
			osw.flush();
		}
		return true;
	}
	
	/**
	 * 一次性读取一个文件
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] getFileContent(String filePath) throws FileNotFoundException, IOException {
		File file = new File(filePath);
		if (!file.exists())
			return null;

        Long filelength = file.length();  
        byte[] result = new byte[filelength.intValue()]; 
        
        try(FileInputStream in = new FileInputStream(file)) {
        	in.read(result);	
        }
        return result;
	}
	
	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		return file.exists() ? file.delete() : true;
	}
	
	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     */
    public static boolean deleteDir(File dir) {
        return delete(dir, true);
    }
    
    /**
     * 递归删除目录下的所有文件及子目录下所有文件(不删除目录结构)
     * @param dir			将要删除的文件目录
     * @param deleteFolder	是否删除目录结构
     * @return
     */
    public static boolean delete(File dir, boolean deleteFolder) {
    	//是目录的情况下执行递归遍历
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = delete(new File(dir, children[i]), deleteFolder);
                if (!success) {
                    return false;
                }
            }
        }
        
        // 执行删除
        if(deleteFolder)
        	return dir.delete();
        else if(dir.isFile())
        	return dir.delete();
        return true;
    }

}
