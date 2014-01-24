package jie.android.ip.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AssetsHelper {

	private static final String Tag = AssetsHelper.class.getSimpleName();
	
	public interface OnUnzipListener {
		public void onComplete();
//		public void onFail();
	}
	
	public static int UnzipTo(final InputStream input, final String outputPath, OnUnzipListener listener) {
		
		File file = new File(outputPath);
		if(!file.exists()) {
			file.mkdir();
		}
		
		ZipInputStream zipStream = new ZipInputStream(input);

		try {
			byte[] buf = new byte[64 * 1024];
			
			ZipEntry zipEntry = null;
			while((zipEntry = zipStream.getNextEntry())!= null) {
				file = new File(outputPath + File.separator + zipEntry.getName());
				if(!zipEntry.isDirectory()) {
					if(!file.createNewFile())
						return -1;
					FileOutputStream output = new FileOutputStream(file);
					int size = -1;
					while((size = zipStream.read(buf)) > 0) {
						output.write(buf, 0, size);
					}
					output.close();
				} else {
					file.mkdir();
				}
			}
			
			zipStream.close();
			
		} catch (IOException e) {
			return -1;
		}
		
		if (listener != null) {
			listener.onComplete();
		}
				
		return 0;
	}
	
}
