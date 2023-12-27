//package com.cos.master.security;
//
//import java.nio.ByteBuffer;
//import java.nio.charset.Charset;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchProviderException;
//import java.security.SecureRandom;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.KeySpec;
//import java.util.Base64;
//import java.util.Base64.Decoder;
//import java.util.Base64.Encoder;
//import java.util.List;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.GCMParameterSpec;
//import javax.crypto.spec.PBEKeySpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.hibernate.cfg.Environment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import java.util.Base64.Encoder;
//
//import com.cos.master.config.PropertiesConfig;
//
//import io.micrometer.common.util.StringUtils;
//import lombok.SneakyThrows;
//
//@Component
//public class Security {
//	private static final int IV_LENGTH = 16;
//	private static final int SALT_LENGTH = 16;
//	public static final int ITERATION_COUNT = 5000;
//	public static final int KEY_LENGTH = 256;
//	public static final int AES_SIZE = 128;
//	public static final String TRANSFORMATION = "AES/GCM/NoPadding";
//	public static final String KEY_SPEC_ALGORITHM = "AES";
//	public static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
//	public static final List<String> salts = List.of("5s4da9ZN", "B4syN9HP", "sWpcA9dV", "86BU7eFg", "BdhdTQj6");
//
//	@Autowired
//	private List<String> words;
//	
//	@SneakyThrows
//	public String encryptString(String text) {
//		try {
//			String key = "cosglobalprivatelimited2021";
//			byte[] iv = getNonce(IV_LENGTH);
//			byte[] salt = getNonce(SALT_LENGTH);
//			SecretKey secretKey = generateSecretKey(key, salt);
//			Cipher aesCipher = Cipher.getInstance(TRANSFORMATION);
//			aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(AES_SIZE, iv));
//			byte[] bytes = aesCipher.doFinal(text.getBytes(Charset.defaultCharset()));
//			byte[] encryptedBytes = ByteBuffer.allocate(IV_LENGTH + SALT_LENGTH + bytes.length).put(iv).put(salt).put(bytes).array();
//			return Base64.getEncoder().encodeToString(encryptedBytes);
//		} catch (Exception e) {
//			return null;
//		}
//		
//	}
//
//	@SneakyThrows
//	public String decryptString(String text) {
//		try {
//			String key = "cosglobalprivatelimited2021";
//			if (StringUtils.isEmpty(text)) {
//				return text;
//			}
//			byte[] decode = Base64.getDecoder().decode(text.getBytes(Charset.defaultCharset()));
//			ByteBuffer byteBuffer = ByteBuffer.wrap(decode);
//			byte[] iv = new byte[IV_LENGTH];
//			byteBuffer.get(iv);
//			byte[] salt = new byte[SALT_LENGTH];
//			byteBuffer.get(salt);
//			byte[] cipherText = new byte[byteBuffer.remaining()];
//			byteBuffer.get(cipherText);
//			SecretKey secretkey = generateSecretKey(key, salt);
//			Cipher aesCipher = Cipher.getInstance(TRANSFORMATION);
//			aesCipher.init(Cipher.DECRYPT_MODE, secretkey, new GCMParameterSpec(AES_SIZE, iv));
//			byte[] decrypted = aesCipher.doFinal(cipherText);
//			return new String(decrypted, Charset.defaultCharset());
//		} catch (Exception e) {
//			return null;
//		}
//		
//	}
//
//	@SneakyThrows
//	private SecretKey generateSecretKey(String key, byte[] salt) {
//		SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
//		KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
//		return new SecretKeySpec(factory.generateSecret(keySpec).getEncoded(), key);
//	}
//
//	private byte[] getNonce(int size) {
//		byte[] nonce = new byte[size];
//		new SecureRandom().nextBytes(nonce);
//		return nonce;
//	}
//
//}
