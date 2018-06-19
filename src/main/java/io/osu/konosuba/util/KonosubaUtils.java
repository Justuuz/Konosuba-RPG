package io.osu.konosuba.util;

public class KonosubaUtils {
	/**
	 * Upper cases every word in a sentence
	 * @param sentence 
	 * @return sentence full of upper cases
	 */
	public static String UpperCaseSentence(String sentence){
		char[] chars = sentence.toCharArray();
		 
	      // all ways make first char a cap
	      chars[0] = Character.toUpperCase(chars[0]);
	 
	      // then capitalize if space on left.
	      for(int x=1; x<chars.length; x++) {
	         if(chars[x-1] == ' '){
	            chars[x] = Character.toUpperCase(chars[x]);
	         } 
	      }
	 
	      String str2 = new String(chars);
	      return str2;
	}

}
