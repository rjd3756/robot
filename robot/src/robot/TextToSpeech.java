package robot;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.*;

/**
 * Takes a command line argument and speaks it using Google Translate
 * 
 * @author Kevin Hannigan
 */
public class TextToSpeech {

	public static void main(String args[]) {
		String url = makeURL(args);
		System.out.println(url);
		
		try {
			URL ttsURL = new URL(url);
            URLConnection con = ttsURL.openConnection();
			System.out.println("Opened connection");
			DataInputStream in = new DataInputStream(con.getInputStream());
			System.out.println("Made DataInputStream");
			
			int length = in.available();
			byte[] byteArray = new byte[length];
			for(int i = 0; i < length; i++) {
				byteArray[i] = in.readByte();
			}
			in.close();
			
			System.out.println("Downloaded bytes to array");
			
			FileOutputStream out = new FileOutputStream(new File("tts.mp3"));
			out.write(byteArray);
			out.close();
			System.out.println("Success!");
		} catch(IOException e) {
			System.out.println("IO Exception!");
		}
	}
	
	/**
	 * Takes the words we want to speak and appends them to the tts url with 
	 *  '+' characters in between
	 * @param words
	 * @return
	 */
	private static String makeURL(String[] words) {
		String url = "http://translate.google.com/translate_tts?ie=UTF-8&tl=en&q=";
		
		for(int i = 0; i < words.length; i++) {
			url = url + words[i];
			if(i != words.length-1) {
				url = url + "+";
			}
		}
		
		return url;
	}
}
