package encryption;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

import sun.misc.*;


public class encryptionAES {
	
	public encryptionAES(){
		try {
			setKey(generateKey());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// Her defineres hvilken type algoritme der skal benyttes, samt byte[] til nøglen der genrreres i generateKey() 	
		private static String algorithm = "AES";
		private static byte[] keyValue=new byte[] 
		{ 'D', 'I', 'S', 'T', '@', 'D', 'O', 'E', 'K', '4', 'E', 'V', 'A', 'H', '!', '!' };
		private Key key;


				// Performs Encryption
		        @SuppressWarnings("restriction")
				public String encrypt(String plainText) throws Exception 
		        {
		                Cipher chiper = Cipher.getInstance(algorithm);
		                chiper.init(Cipher.ENCRYPT_MODE, key);
		                byte[] encVal = chiper.doFinal(plainText.getBytes());
		                String encryptedValue = new BASE64Encoder().encode(encVal);
		                return encryptedValue;
		        }

		        // Performs decryption
		        @SuppressWarnings("restriction")
				public String decrypt(String encryptedText) throws Exception 
		        {
		        		System.out.println("Decrypting...");
		                Cipher chiper = Cipher.getInstance(algorithm);
		                chiper.init(Cipher.DECRYPT_MODE, key);
		                byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
		                byte[] decValue = chiper.doFinal(decordedValue);
		                String decryptedValue = new String(decValue);
		                return decryptedValue;
		        }

		//generateKey() is used to generate a secret key for AES algorithm
		        private static Key generateKey() throws Exception 
		        {
		                Key key = new SecretKeySpec(keyValue, algorithm);
		                return key;
		        }
		        public Key getKey() {
		        		return key;
		}

		        public void setKey(Key key) {
		        		this.key = key;
		        		System.out.println(key.toString());
		}

}


