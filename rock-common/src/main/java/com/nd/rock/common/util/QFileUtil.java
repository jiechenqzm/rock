package com.nd.rock.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QFileUtil {

	public static String readFile2String(String path) throws IOException {

		File file = checkFile(path);
		return getFileContent(file);
	}
	
	public static byte[] readFile2Bytes(String path) throws IOException {
		File file = checkFile(path);
		FileInputStream fis = null;
		byte[] buf = null;
		try {
			fis = new FileInputStream(file);
			buf = new byte[(int)file.length()];
			fis.read(buf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return buf;
	}
	
	public static  List<File> getListFiles(String path) throws IOException {
		File folder = checkFolder(path);
		return getListFilesFromFolder(folder);
	}

	public static List<File> getListFilesFromFolder(File folder) {
		File[] files = folder.listFiles();

		List<File> result = new ArrayList<File>();
		for(File file : files) {
			if(file.isDirectory())
				result.addAll(getListFilesFromFolder(file));
			else
				result.add(file);
		}
		return result;
	}
	
	public static String getFileContent(File file) throws IOException {
		
		FileInputStream fis = null;
		StringBuffer result = new StringBuffer();

		try {
			fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			
			while (fis.read(buf) != -1) {
				result.append(new String(buf));
				buf = new byte[1024];
			}
			return result.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return result.toString();

	}
	
	public static byte[] getFileByteContent(File file) throws IOException {
	        byte[] buf = new byte[(int)file.length()];
	        FileInputStream in = new FileInputStream(file);
	        int len = in.read(buf);
	        in.close();
	        if (len <= 0) {
	            return null;
	        }
	        return buf;
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
	
	private static File checkFolder(String path) throws FileNotFoundException {
		File file = new File(path);
		if (!file.exists() || !file.isDirectory())
			throw new FileNotFoundException("path : " + path);
		return file;
	}
	
	private static File checkFile(String path) throws FileNotFoundException {
		File file = new File(path);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException("path : " + path);
		return file;
	}

}
