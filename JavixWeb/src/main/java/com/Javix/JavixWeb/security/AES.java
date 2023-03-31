package com.Javix.JavixWeb.security;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/*
 * @author h0riz4n m1d1
 */
@Service
public class AES
{
    private static SecretKeySpec key;
    private static Cipher encryptCipher;
    private static Cipher decryptCipher;

    static
    {
        try
        {
            encryptCipher = Cipher.getInstance("AES");
            decryptCipher = Cipher.getInstance("AES");

            key = new SecretKeySpec(System.getenv("KEY").getBytes(), "AES");

            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
        catch (NoSuchPaddingException e)
        {
            throw new RuntimeException(e);
        }
        catch (InvalidKeyException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encrypt(String value) throws IllegalBlockSizeException, BadPaddingException
    {
        return encryptCipher.doFinal(value.getBytes());
    }

    public static String decrypt(byte[] value) throws IllegalBlockSizeException, BadPaddingException
    {
        byte[] decryptedBytes = decryptCipher.doFinal(value);
        String decryptedValue = "";

        for (byte code : decryptedBytes)
        {
            decryptedValue += (char) code;
        }

        return decryptedValue;
    }
}
