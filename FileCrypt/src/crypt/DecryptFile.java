package crypt;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DecryptFile {
	
	private SecretKeySpec key = null;
	private KeyStore ks = null;
	private byte[] cFile;
	private Console con;
	private String scheme;
	private Cipher cip = null;
	private boolean decrypt;
	
	public byte[] iv;
	
	
	public DecryptFile(Path file, InputStream keyfile, String scheme, String mode) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, NoSuchPaddingException {
		cFile = Files.readAllBytes(file);
		ks = KeyStore.getInstance("JCEKS");
		ks.load(keyfile, "".toCharArray());
		this.scheme = scheme;
		con = System.console();
		cip = Cipher.getInstance(scheme+"/"+mode+"/PKCS5Padding");
	}

	public void setKey(String alias) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		System.out.println("Password to key: ");
		char[] password = new Scanner(System.in).nextLine().toCharArray();
		key = new SecretKeySpec(ks.getKey(alias, password).getEncoded(), scheme);
	}
	
	public void init(boolean decrypt) throws InvalidKeyException, InvalidAlgorithmParameterException {
		this.decrypt = decrypt;
		if (decrypt) {
			iv = new byte[16];
			for (int i = 0; i < 16; i++) {
				iv[i] = cFile[i];
			}
			byte[] temp = cFile;
			cFile = new byte[cFile.length - 16];
			for (int i = 16; i < temp.length; i++) {
				cFile[i-16] = temp[i];
			}
			IvParameterSpec ips = new IvParameterSpec(iv);
			cip.init(cip.DECRYPT_MODE, key, ips);
		} else {
			cip.init(cip.ENCRYPT_MODE, key);
			iv = cip.getIV();
			System.out.println(iv.length);
		}
	}

	public byte[] doIt() throws IllegalBlockSizeException, BadPaddingException {
		return cip.doFinal(cFile);
	}
	
}
