package translation;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URLEncoder;
public class Translation {
	
	public static String get(String urlAddr, String words, String from, String to, String id, String priKey){	
		String webMessage = "";
		try {
			//临时储存信息
			String tempMessage = null;
			//随机生成数
			String random = new String((int)(Math.random() * 15555)+"");
			//百度的appid
			String appid = URLEncoder.encode(id, "UTF-8");
			//盐
			String salt  = URLEncoder.encode(random, "UTF-8");
			//密钥
			String key   = URLEncoder.encode(priKey, "UTF-8");
			//编码后的单词
			String encWords  = URLEncoder.encode(words, "UTF-8"); 
			//md5
			String md5 = WebOperation.md5(appid+words+salt+key);
			URL url = new URL(urlAddr + "?from=" + 
			                     from + "&to="   + 
			                       to + "&sign=" + 
			                      md5 + "&appid=20161213000033961&salt=" + 
					           random + "&q=" + encWords);
			
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			while((tempMessage = input.readLine()) != null){
				webMessage += tempMessage ;
			}
		} catch (Exception e) {
			System.out.println("connection is fail");
			System.exit(0);
			
		}
		return webMessage;
	}

	
	public static boolean paraJudge(String str){
		return !str.matches("-t|-r|-f");
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException{
		String path = System.getProperty("java.class.path");
		path = path.substring(0, path.lastIndexOf("/"));
		/*软件的help界面*/
		if(args.length == 0){
			File file = new File(path + "/introduction");
			if(file.exists()){
				Scanner input = new Scanner(file);
				while(input.hasNextLine()){
					System.out.println(input.nextLine());
				}
				input.close();
			}
			return;
		}
		//word
		String words = "";	
		//source language
		String from = "en";	
		//destination language
		String to = "zh";
		//baidu appid
		String id = "";
		//baidu private key
		String prikey = "";

		/*read config file, load words from to id*/
		File file = new File(path + "/init.conf");
		if(file.exists()){
			Scanner input = new Scanner(file);
			
			while(input.hasNextLine()){
				String data = input.nextLine();
				data = data.replaceAll("\\s", "");
				
				String[] datas = data.split("=");
				if(datas.length == 2){
					if(datas[0].equals("from"))
						from = datas[1];
					else if(datas[0].equals("to"))
						to = datas[1];
					else if(datas[0].equals("appid"))
						id = datas[1];
					else if(datas[0].equals("prikey"))
						prikey = datas[1];
				}
			}
			input.close();
		}

		for(int i = 0 ; i < args.length ; ++i){
			
			if(args[i].equals("-f")){	//指定源语言
				if(i + 1 < args.length && paraJudge(args[i + 1])){
					from = args[++i];
				}
			} else if(args[i].equals("-t")){	//指定目的语言
				if(i + 1 < args.length && paraJudge(args[i + 1])){
					to = args[++i];
				}
			}else if(args[i].equals("-r") ){	//翻译互换
				String temp = from;
				from = to;
				to = temp;
				break;
			}else{
				words += args[i] + " ";
			}
		}
		
		//��Ӧ�ı�
		String response = get("http://fanyi-api.baidu.com/api/trans/vip/translate", words, from, to, id, prikey);
		
		String end = WebOperation.getJSON(response).get("dst");
		
		System.out.println(WebOperation.unicodeToString(end));
	}
}
