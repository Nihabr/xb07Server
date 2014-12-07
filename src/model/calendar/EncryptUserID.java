package model.calendar;

import java.security.MessageDigest;

public class EncryptUserID {

    private static final String HASHKEY = "v.eRyzeKretW0r_t";
    private static String email = "";
    private static String key;
    private static MessageDigest digester;

    static {
        try {
        	
            digester = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  

	// Enkryptere en tekst streng som bliver parset til funktionen
    public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Error");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

    //
    public static void main(String[] args) {

        System.out.print("Secret key: " + crypt(email + HASHKEY));
        key = crypt(email+ HASHKEY);

    }

	public String getKey() {
		
		return crypt(email+ HASHKEY); 
		
	}
	  public void setEmail(String email) {
			EncryptUserID.email = email;
		}

	public String getEmail() {
		return email;
	}

}

