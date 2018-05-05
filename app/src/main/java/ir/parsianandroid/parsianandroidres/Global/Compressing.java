package ir.parsianandroid.parsianandroidres.Global;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import android.util.Base64;
import android.util.Log;




public class Compressing {
	public static String compress(String str) throws IOException {

		byte[] data=str.getBytes("UTF-8");
		 byte[] blockcopy = ByteBuffer
		            .allocate(4)
		            .order(java.nio.ByteOrder.LITTLE_ENDIAN)
		            .putInt(data.length)
		            .array();
		    ByteArrayOutputStream os = new ByteArrayOutputStream(data.length);
		    GZIPOutputStream gos = new GZIPOutputStream(os);
		    gos.write(data);
		    gos.close();
		    os.close();
		    byte[] compressed = new byte[4 + os.toByteArray().length];
		    System.arraycopy(blockcopy, 0, compressed, 0, 4);
		    System.arraycopy(os.toByteArray(), 0, compressed, 4,
		            os.toByteArray().length);
		    String s = new String(Base64.encode(compressed,Base64.DEFAULT));

		    return s;




	}

	public static String decompress(String zipText) throws IOException {
		byte[] compressed = Base64.decode(zipText,Base64.DEFAULT);
		if (compressed.length > 4)
		{
			GZIPInputStream gzipInputStream = new GZIPInputStream(
					new ByteArrayInputStream(compressed, 4,
							compressed.length - 4));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int value = 0; value != -1;) {
				value = gzipInputStream.read();
				if (value != -1) {
					baos.write(value);
				}
			}
			gzipInputStream.close();
			baos.close();
			String sReturn = new String(baos.toByteArray(), "UTF-8");
			return sReturn;
		}
		else
		{
			return "";
		}
	}
	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		reader.close();
		return sb.toString();
	}

	public static String getStringFromFile (String filePath) throws Exception {

		File fl = new File(filePath);
		FileInputStream fin = new FileInputStream(fl);
		String ret = convertStreamToString(fin);
		//Make sure you close all streams.
		fin.close();        
		return ret;
	}

}
