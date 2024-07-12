package com.imara.shipping.utility;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.*;

@Component
public class EncryptionUtility {

  private String key = "U2lsYXBwYXRoaWhhYXJhbQ==";
  private String iv = "U2lsYXBwYXRoaWhhYXJhbQ==";

  private Cipher encryptCipher;
  private Cipher decryptCipher;


  public String encrypt(String value) throws Exception {
    Cipher cipher = getEncryptCipher();
    byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8")); //value.getBytes()
    return Base64.encodeBase64String(encrypted);
  }

  public String decrypt(String encrypted) throws Exception {
    Cipher cipher = getDecryptCipher();
    byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
    return new String(original);
  }

  private Cipher getEncryptCipher() throws Exception {
    if (encryptCipher == null) {
      synchronized (EncryptionUtility.class) {
        if (encryptCipher == null) {
          IvParameterSpec vector = new IvParameterSpec(Base64.decodeBase64(iv));
          SecretKeySpec skeySpec = new SecretKeySpec(Base64.decodeBase64(key), "AES");
          encryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
          encryptCipher.init(Cipher.ENCRYPT_MODE, skeySpec, vector);
        }
      }
    }
    return encryptCipher;
  }

  private Cipher getDecryptCipher() throws Exception {
    if (decryptCipher == null) {
      synchronized (EncryptionUtility.class) {
        if (decryptCipher == null) {
          IvParameterSpec vector = new IvParameterSpec(Base64.decodeBase64(iv));
          SecretKeySpec skeySpec = new SecretKeySpec(Base64.decodeBase64(key), "AES");
          decryptCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
          decryptCipher.init(Cipher.DECRYPT_MODE, skeySpec, vector);
        }
      }
    }
    return decryptCipher;
  }

}
