
public class aestester {
	public static void main (String args []) throws Exception{
		encryptionAES enc1 = new encryptionAES();
		encryptionAES enc2 = new encryptionAES();
		
		String start = "John er noob";
		String dec1 = enc1.encrypt(start);
		System.out.println(dec1);
		String dec2 = enc2.decrypt(dec1);
		System.out.println(dec2);
		
		
		
	}
}
