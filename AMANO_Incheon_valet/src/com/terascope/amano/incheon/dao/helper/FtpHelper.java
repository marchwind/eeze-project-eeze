package com.terascope.amano.incheon.dao.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import android.util.Log;
//import org.apache.commons.net.ftp.*;

public class FtpHelper
{
	private final String 	TAG = "FtpClient";
	private FTPClient mFTPClient = null;
	
	public boolean ftpConnect(String host, String username, String password, int port)
	{
		try 
		{
	        mFTPClient = new FTPClient();

	        // connecting to the host
	        mFTPClient.connect(host, port);

	        // now check the reply code, if positive mean connection success
	        if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
	            // login using username & password
	            boolean status = mFTPClient.login(username, password);
	            
	            Log.d(TAG, "Connect[status=] " + status );
	            
	            /* Set File Transfer Mode
	             *
	             * To avoid corruption issue you must specified a correct
	             * transfer mode, such as ASCII_FILE_TYPE, BINARY_FILE_TYPE,
	             * EBCDIC_FILE_TYPE .etc. Here, I use BINARY_FILE_TYPE
	             * for transferring text, image, and compressed files.
	             */
	            mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
	            mFTPClient.enterLocalPassiveMode();

	            return status;
	        }
	    } catch(Exception e) {
	        Log.d(TAG, "Error: could not connect to host " + host );
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean ftpDisconnect()
	{
	    try {
	        mFTPClient.logout();
	        mFTPClient.disconnect();
	        return true;
	    } catch (Exception e) {
	        Log.d(TAG, "Error occurred while disconnecting from ftp server.");
	    }

	    return false;
	}
	
	public String ftpGetCurrentWorkingDirectory()
	{
	    try {
	        String workingDir = mFTPClient.printWorkingDirectory();
	        return workingDir;
	    } catch(Exception e) {
	        Log.d(TAG, "Error: could not get current working directory.");
	    }

	    return null;
	}
	
	public boolean ftpChangeDirectory(String directory_path)
	{
	    try {
	    	boolean result = mFTPClient.changeWorkingDirectory(directory_path);
	    	Log.d(TAG, "ftpChangeDirectory reply => " + mFTPClient.getReplyString());
	        return result;
	    } catch(Exception e) {
	        Log.d(TAG, "Error: could not change directory to " + directory_path);
	    }

	    return false;
	}
	
	public List<String> ftpGetFilesList(String dir_path)
	{
		List<String> files = new ArrayList<String>();
	    try {
	        FTPFile[] ftpFiles = mFTPClient.listFiles(dir_path);
	        
	        int length = ftpFiles.length;

	        for (int i = 0; i < length; i++) {
	            //String name = new String (ftpFiles[i].getName().getBytes("8859_1"), "euc-kr");
	        	String name = ftpFiles[i].getName();
	            if (ftpFiles[i].isFile()) {
	            	//files.add(name);
	            	files.add(name);
	                Log.i(TAG, "File : " + name);
	            } else {
	                Log.i(TAG, "Directory : " + name);
	            }
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	    return files;
	}
	
	public List<String> ftpGetFilesListWithCompare(String dir_path, File localFiles)
	{
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = mFTPClient.listFiles(dir_path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultOfCompare(localFiles.listFiles(), ftpFiles);
	}
	 
	private List<String> resultOfCompare(File[] localFiles, FTPFile[] remoteFiles) {
		Map<String, Long> localList = new HashMap<String, Long>();
		List<String> resultList = new ArrayList<String>();
		
		if (remoteFiles != null) {
			
			for(File file: localFiles) {
				localList.put(file.getName(), file.lastModified());
			}
			String remoteFileName;
			for(FTPFile file: remoteFiles) {
//				try {
//					remoteFileName = new String (file.getName().getBytes("8859_1"), "euc-kr");
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//					remoteFileName = "";
//				}
				
				remoteFileName = file.getName();
				
				if ( localList.get(remoteFileName)!=null ) {
					if ( file.getTimestamp().getTimeInMillis() > localList.get(remoteFileName) ){
						// remote file add into List if remote file is newer then local 
						resultList.add(remoteFileName);
					}
				} else {
					// remote file add into List if remote file dosen't exist in local 
					resultList.add(file.getName());
				}
			}
		}
		
		return resultList;
	}
	
	public boolean ftpMakeDirectory(String new_dir_path)
	{
	    try {
	        boolean status = mFTPClient.makeDirectory(new_dir_path);
	        Log.d(TAG, "ftpMakeDirectory reply => " + mFTPClient.getReplyString());
	        return status;
	    } catch(Exception e) {
	        Log.d(TAG, "Error: could not create new directory named " + new_dir_path);
	    }

	    return false;
	}
	
	public boolean ftpRemoveDirectory(String dir_path)
	{
	    try {
	        boolean status = mFTPClient.removeDirectory(dir_path);
	        return status;
	    } catch(Exception e) {
	        Log.d(TAG, "Error: could not remove directory named " + dir_path);
	    }

	    return false;
	}
	
	public boolean ftpRemoveFile(String filePath)
	{
	    try {
	        boolean status = mFTPClient.deleteFile(filePath);
	        return status;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean ftpRenameFile(String from, String to)
	{
	    try {
	        boolean status = mFTPClient.rename(from, to);
	        return status;
	    } catch (Exception e) {
	        Log.d(TAG, "Could not rename file: " + from + " to: " + to);
	    }

	    return false;
	}
	
	/**
	 * mFTPClient: FTP client connection object (see FTP connection example)
	 * srcFilePath: path to the source file in FTP server
	 * desFilePath: path to the destination file to be saved in sdcard
	 */
	public boolean ftpDownload(String srcFilePath, String desFilePath)
	{
	    boolean status = false;
	    try {
	        FileOutputStream desFileStream = new FileOutputStream(desFilePath);;
	        status = mFTPClient.retrieveFile(srcFilePath, desFileStream);
	        desFileStream.close();

	        return status;
	    } catch (Exception e) {
	        Log.d(TAG, "ftpDownload download failed " + e);
	    }

	    return status;
	}

	
	/**
	 * mFTPClient: FTP client connection object (see FTP connection example)
	 * srcFilePath: path to the source file in FTP server
	 */
	public String ftpReadString(String srcFilePath)
	{
		String result="";
		InputStream in = null;
	    try {
	    	in = mFTPClient.retrieveFileStream(srcFilePath);
	        StringBuffer sb = new StringBuffer();
	    	byte[] b = new byte[1024];
	        int n;
	        while ((n=in.read(b)) != -1) {
	        	sb.append(new String(b, 0, n));
	        }
	        result = sb.toString();
	    } catch (Exception e) {
	        Log.d(TAG, "ftpReadString download failed");
	    } finally {
	    	if (in != null) {
	    		 try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }

	    return result;
	}
	
	/**
	 * mFTPClient: FTP client connection object (see FTP connection example)
	 * srcFilePath: source file path in sdcard
	 * desFileName: file name to be stored in FTP server
	 * desDirectory: directory path where the file should be upload to
	 */
	public boolean ftpUpload(String srcFilePath, String desFileName,
	                         String desDirectory)
	{
	    boolean status = false;
	    try {
	        FileInputStream srcFileStream = new FileInputStream(srcFilePath);

	        // change working directory to the destination directory
	        if (ftpChangeDirectory(desDirectory)) {
	            status = mFTPClient.storeFile(desFileName, srcFileStream);
	        }
	        Log.d(TAG, "ftpUpload reply => " + mFTPClient.getReplyString());
	        srcFileStream.close();
	        return status;
	    } catch (Exception e) {
	        Log.d(TAG, "upload failed");
	    }

	    return status;
	}
}
