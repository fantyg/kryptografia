import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Decryption {
	
	private String keyP = "2c2a8c20";
	private String keyS = "bd72343b94aafb0c6c8dd7e60b7915594d75273633386e8409d95fcc";
	private String iv = "f93a961d574b9efc4c21d234bdb5da17";
	private String encrypted = "b+VjT7S/wU8MYTRa6fKxQ7VfKSkpPf2z5EvT964Ah2/It9sEL0Flgudp0C4nOWIHFsrNTVN5jOQF3SzcHYBUS8nMI0Ps19ZMoV6pvvAAg9jvzg+po6YG3IFDR+KwwVPj/DEk3SRDwG0Thp8oP3sW2YbuS/KX7eD4np7igFmE5c4=";
	private String keyChars = "0123456789abcdef";
	private boolean finded = false;
	private String resChars = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM[];\',./{}:\"<>?\\|!@#$%^&*()_+-=";
	
	public String decrypt(String keyP){
		String result = "";
		try {
			System.err.println("keypre: "+keyP);
			String ex = "openssl enc -d -A -aes-256-cbc -base64 -K ";
			ex += keyP+keyS;
			ex += " -iv ";
			ex += iv;
			
			Process proc =  Runtime.getRuntime().exec(ex);
			BufferedWriter write = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
			write.write(encrypted);
			write.newLine();
			write.close();
			BufferedReader read=new BufferedReader(new InputStreamReader(proc.getInputStream()));
			proc.waitFor();
			while(read.ready()) {
				result += read.readLine();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void bf(){
		String localPrefix = "";
		do{
			synchronized(keyP){
				localPrefix = keyP;
				keyP = nextKey(keyP);
			}
			String res = decrypt(localPrefix);
			System.err.println(res);
			if(check(res)){
				System.err.println(res);
				synchronized(keyS){
					finded = true;
				}
				break;
			}
			synchronized(keyS){
				if(finded){
					break;
				}
			}
		}while(!localPrefix.equals("0000000"));
		synchronized(keyS){
			finded = true;
		}
	}

	private boolean check(String res) {
		if(res.equals("")){
			return false;
		}
		for(int i = 0; i < res.length(); i++){
			if(resChars.indexOf(res.charAt(i)) < 0){
				return false;
			}
		}
		return true;
	}

	private String nextKey(String k) {
		int i = 0;
		while(i < k.length()){
			int nC = keyChars.indexOf(k.charAt(i));
			if(nC < keyChars.length()-1){
				k = k.substring(0, i)+keyChars.charAt(nC+1)+k.substring(i+1);
				break;
			}else{
				k = k.substring(0, i)+keyChars.charAt(0)+k.substring(i+1);
				i++;
			}
		}
		return k;
	}
}
