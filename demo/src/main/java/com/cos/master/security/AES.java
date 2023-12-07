package com.cos.master.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
@Component
public class AES {
	private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "1234567891234567".getBytes();
//    public static void main(String args[]) throws Exception {
//        Key key = generateKey();
//       String encriptValue = encrypt("Poorna@123",key);
//       decrypt(encriptValue,key);
//
//   }
 
//private static Key generateKey() throws Exception {
//	Key key = new SecretKeySpec(keyValue, ALGORITHM);
//        return key;
//    }
    
    
public static String encrypt(String value) throws Exception {
	try {
	
	Key key = new SecretKeySpec(keyValue, ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key);

    byte[] encValue = cipher.doFinal(value.getBytes());
    byte[] encryptedByteValue = new Base64().encode(encValue);
    if (encryptedByteValue != null && encryptedByteValue.length > 0) {
        String encryptedText = new String(encryptedByteValue);
        System.out.println("Encrypted Value :: " + encryptedText);
        return encryptedText;
    } else {
        System.err.println("Encryption failed or produced empty result.");
        return null; // Or any appropriate value indicating failure
    }
} catch (Exception e) {
    e.printStackTrace();
    // Handle the exception appropriately (e.g., log the error, throw it, etc.)
    return null; // Or any appropriate value indicating failure
}
}

public static String decrypt(String encryptedText) throws Exception {
	Key key = new SecretKeySpec(keyValue, ALGORITHM);
     Cipher cipher = Cipher.getInstance(ALGORITHM);
     cipher.init(Cipher.DECRYPT_MODE, key);
      
     byte[] decodedBytes = new Base64().decode(encryptedText.getBytes());

     byte[] enctVal = cipher.doFinal(decodedBytes);
     
     System.out.println("Decrypted Value :: " + new String(enctVal));
     return new String(enctVal);
 }

}
