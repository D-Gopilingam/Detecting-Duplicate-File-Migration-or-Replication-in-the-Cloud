package CDC;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class Checksum {
	public MessageDigest md;
	private FileInputStream fis;

	public Checksum() {
		try {
			md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String generateChecksum(String filepath, String filename) {
		try {
			fis = new FileInputStream(filepath);

			byte[] dataBytes = new byte[1024];

			int nread = 0;

			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] mdbytes = md.digest();

		String result = byteToHex(mdbytes);
		
		return result;
	}

	public String chunking(byte[] dataBytes){
		md.update(dataBytes);
		byte[] mdbytes = md.digest();
		String result = byteToHex(mdbytes);
		return result;
	}
	public String naechunking(byte[] dataBytes,long length,int window){
		md.update(dataBytes);
		byte[] mdbytes = md.digest();
		byte[] chbytes=null;
		byte m_value=max(dataBytes);
		int i=0;
		String result = null;
		while(i<mdbytes.length)
		{
			if(mdbytes[i]>m_value)
			{
				
				if(i>window)
				{
					chbytes=Arrays.copyOfRange(mdbytes, 0,i);
					result= byteToHex(chbytes);
					
				}
				m_value=mdbytes[i];
				
			}
			i++;
			
		}
		return result;
	}
	public static byte max(byte[] array) {
	      // Validates input
	      if (array == null) {
	          throw new IllegalArgumentException("The Array must not be null");
	      } else if (array.length == 0) {
	          throw new IllegalArgumentException("Array cannot be empty.");
	      }
	  
	      // Finds and returns max
	      byte max = array[0];
	      for (int i = 1; i < array.length; i++) {
	          if (array[i] > max) {
	              max = array[i];
	          }
	      }
	  
	      return max;
	  }
	
	
	// convert the byte to hex format
	public String byteToHex(byte[] mdbytes) {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}

}
