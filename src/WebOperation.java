package translation;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class WebOperation {
	/**
	 *	@function : md5 encode for text
	 *	@return   : String 
	 **/
	public static String md5(String text) throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] mdBytes = md.digest(text.getBytes());
		StringBuffer md5 = new StringBuffer();
		
		for(int i = 0 ; i < mdBytes.length ; ++i){
			int t = mdBytes[i];
			if(t < 0)
				t += 256;
			if(t < 16)
				md5.append("0");
			
			md5.append(Integer.toHexString(t));
		}
		
		return md5.toString();
	}
	/**
	 * @function : only transfrom one word form unicode to string
	 * @return   : String
	 **/
	public static String unicodeToChar(String text){
		text = text.replaceAll("\\\\u", "");
		
		return (char)Integer.parseInt(text, 16) + "";
	}
	
	/**
	 * @function : transfrom all words form unicode to string
	 * @return   : String
	 **/
	public static String unicodeToString(String text){
		Pattern pattern = Pattern.compile("\\\\u[0-9|a-f|A-F]{4}");
		Matcher match = pattern.matcher(text);
		StringBuffer buf = new StringBuffer();
		boolean includeUnicode = false;
		
		while(match.find()){
			includeUnicode = true;
			match.appendReplacement(buf, unicodeToChar(match.group()));
		}
		
		return includeUnicode ? buf.toString() : text;
	}
	
	/**
	 * @function : get the JSON 
	 * @return   : Map<String, String>
	 **/
	public static Map<String, String> getJSON(String response){
		//filter the father element, find the sub element
		Pattern patternF = Pattern.compile("\"([^\"]*?)\":\\[\\{(.*?)\\}\\]");
		Matcher matchF = patternF.matcher(response);
		StringBuffer bufF = new StringBuffer();
		boolean hasChild = false;
		while(matchF.find()){
			hasChild = true;
			matchF.appendReplacement(bufF, matchF.group(2).replaceAll("\\\\", "\\\\\\\\"));
			
			
		}
		
		//add the property to the map
		Pattern pattern = Pattern.compile("\"(.*?)\":\"(.*?)\",*?");
		Matcher match = pattern.matcher(hasChild ? bufF.toString() : response);
		
		Map<String, String> json = new HashMap<String, String>();
		while(match.find()){
			json.put(match.group(1), match.group(2));
		}
		
		return json;
	}
}
