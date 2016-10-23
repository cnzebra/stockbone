package com.stockbone.utils.compress;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*  
 * 功能：压缩文件成tar.gz格式  
 */
public class TarUtils {

	private static Logger logger = LoggerFactory.getLogger(TarUtils.class);
	private static int BUFFER = 1024 * 4; // 缓冲大小

	public byte[] getTarBytes(byte[] bytes, String fileName) {
		byte[] result = null;
		InputStream in = null;
		ByteArrayOutputStream byteOutStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		TarOutputStream out = null;
		try {
			in = new ByteArrayInputStream(bytes);
			byteOutStream = new ByteArrayOutputStream();
			bufferedOutputStream = new BufferedOutputStream(byteOutStream);
			out = new TarOutputStream(bufferedOutputStream);

			// 如果不加下面这段，当压缩包中的路径字节数超过100 byte时，就会报错
			out.setLongFileMode(TarOutputStream.LONGFILE_GNU);

			TarEntry tarEntry = new TarEntry(fileName);
			// 设置打包文件的大小，如果不设置，打包有内容的文件时，会报错
			tarEntry.setSize(bytes.length);
			out.putNextEntry(tarEntry);
			int b = 0;
			byte[] B_ARRAY = new byte[BUFFER];
			while ((b = in.read(B_ARRAY, 0, BUFFER)) != -1) {
				out.write(B_ARRAY, 0, b);
			}
			out.flush();
			out.closeEntry();
			out.close();
			result = byteOutStream.toByteArray();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				in.close();
				byteOutStream.close();
				bufferedOutputStream.close();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
	}

	public byte[] compress(byte[] srcBytes) {
		byte[] bytes = null;
		InputStream in = null;
		ByteArrayOutputStream byteOut = null;
		GZIPOutputStream out = null;
		try {
			in = new ByteArrayInputStream(srcBytes);
			byteOut = new ByteArrayOutputStream();
			out = new GZIPOutputStream(byteOut);
			int number = 0;
			byte[] B_ARRAY = new byte[BUFFER];
			while ((number = in.read(B_ARRAY, 0, BUFFER)) != -1) {
				out.write(B_ARRAY, 0, number);
			}
			out.close();
			bytes = byteOut.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return bytes;
	}

}