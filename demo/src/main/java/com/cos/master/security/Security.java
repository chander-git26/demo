package com.cos.master.security;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.Base64.Encoder;

import com.cos.master.config.PropertiesConfig;

@Component
public class Security {
//	@Autowired
//	private Environment env;
//	 
//	String SecretKey = env.getProperty("cos_secret_key");
//	@Value("${cos_secret_key}")
	
	public static void main(String[] args) {
	 
    	
        Encoder encoder = Base64.getEncoder();
        String originalString = "[B@2ac1fdc4";
       String encodedString = encoder.encodeToString(originalString.getBytes());
       System.out.println("Encrypted Value :: " +encodedString);
        Decoder decoder = Base64.getDecoder();
	        byte[] bytes = decoder.decode(encodedString);
	                 
      System.out.println("Decrypted Value :: " +new String(bytes));
    }

	public String encrypt(String password) {
		// TODO Auto-generated method stub
		return null;
	}
        
	}

	
	





	//	private static final String ALGORITHM = "AES";
//	private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
////	private static final int ITERATIONS = 10000;
//	//private static final int KEY_LENGTH = 256;
//	
//	@Value("${cos_secret_key}")
//	private static byte[] secretKey;
//	byte[] salt = new byte[16];
//	
//	//System.out.println("secret key :"+secretKey);
//
//    public static String encryptString(String secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
//        
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//      String password = null;
//      byte[] encryptedBytes = cipher.doFinal(password.getBytes());
//       return Base64.getEncoder().encodeToString(encryptedBytes);
//   }
//
//   public static String decrypt(String secretKey, String encryptedPassword) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//   	SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
//       Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
//       return new String(decryptedBytes);
//    }
	
	
    
	




