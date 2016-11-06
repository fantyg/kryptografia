package crypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		/*if(args.length == 2 && args[0].equals("crt")) {
			try {
				createKey(args[1]);
			} catch (KeyStoreException | NoSuchAlgorithmException
					| CertificateException | IOException e) {
				e.printStackTrace();
				System.exit(90);
			}
		}*/
		
		if (args.length < 6) {
			System.err.println("Wrong syntax:"
					+ " FileCrypt path/to/file crypt_scheme crypt_mode"
					+ " path/to/keystore key_id encrypt|decrypt");
			System.exit(1);
		}
		
		Path file = Paths.get(args[0]);
		if (!args[1].equals("AES")) {
			System.err.println("Wrong crypt schame.");
			System.exit(3);
		}
		if (!args[2].equals("CBC") && !args[2].equals("CTR") && !args[2].equals("GCM")) {
			System.err.println("Wrong crypt mode.");
			System.exit(4);
		}
		FileInputStream keyfile = null;
		try {
			keyfile = new FileInputStream (args[3]);
		} catch (FileNotFoundException e) {
			System.err.println("Keystore not found.");
			System.exit(5);
		}
		if (!args[5].equals("encrypt") && !args[5].equals("decrypt")) {
			System.err.println("Wrong parametr: no value "+args[5]);
			System.exit(6);
		}
		//if (args[5].equals("decrypt")) {
			try {
				DecryptFile df = new DecryptFile(file, keyfile, args[1], args[2]);
				df.setKey(args[4]);
				df.init(args[5].equals("decrypt"));
				byte[] res = df.doIt();
				FileOutputStream fosFile = new FileOutputStream(args[0]);
				if (args[5].equals("encrypt")) {
					fosFile.write(df.iv);
				}
				fosFile.write(res);
				fosFile.close();
			} catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
				e.printStackTrace();
				System.exit(7);
			}
		//}
	}

	private static void createKey(String path) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		FileOutputStream fos = new FileOutputStream(path);
		KeyStore ks = KeyStore.getInstance("JCEKS");
		ks.load(null);
		byte[] k1 = new byte[32];
		new Random().nextBytes(k1);
		SecretKeySpec key = new SecretKeySpec(k1, "AES");
		KeyStore.ProtectionParameter param1 = new KeyStore.PasswordProtection("pass1".toCharArray());
		ks.setEntry("1", new KeyStore.SecretKeyEntry(key), param1);
		byte[] k2 = new byte[32];
		new Random().nextBytes(k2);
		SecretKeySpec key2 = new SecretKeySpec(k2, "AES");
		KeyStore.ProtectionParameter param2 = new KeyStore.PasswordProtection("pass2".toCharArray());
		ks.setEntry("2", new KeyStore.SecretKeyEntry(key2), param2);
		byte[] k3 = new byte[32];
		new Random().nextBytes(k3);
		SecretKeySpec key3 = new SecretKeySpec(k3, "AES");
		KeyStore.ProtectionParameter param3 = new KeyStore.PasswordProtection("pass3".toCharArray());
		ks.setEntry("3", new KeyStore.SecretKeyEntry(key3), param3);
		ks.store(fos, "".toCharArray());
	}

}
