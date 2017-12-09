package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JTextPane;

import tool.DocAttribute;
import tool.Json;

public class WebRequest {
	 /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
	 * @throws Exception 
     */
    public static String sendGet(String url, String param) throws Exception {
        String result = "";
        BufferedReader in = null;
        JTextPane jtp=gui.CreatGui.txtpnprogrammers;
        if(url.length()==0) {
        	return "请输入URL。";
        }
        else if (!url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")){
        	return "URL非法";
        }

        //匹配出键值对
        Matcher m = Pattern.compile("[-a-zA-Z0-9]{1,}=[[%]-a-zA-Z0-9\\u4e00-\\u9fa5]{1,}").matcher(param);
        String paramDeal = "";
        if(param.length() != 0) {
        	while(m.find()) {
        		String[] strs = m.group().split("=");
        		System.out.println(strs[0]);
        		System.out.println(strs[1]);
	        	paramDeal += strs[0] + "=" + URLEncoder.encode(strs[1], "utf8") + "&";
	        }
        }
        System.out.println(paramDeal);
        try {
            String urlNameatcheString = url + "?" + paramDeal;
            URL realUrl = new URL(urlNameatcheString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));   
            String line;  
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据  
              
            // 循环读取流,若不到结尾处  
            while ((line = bf.readLine()) != null) {  
             //sb.append(bf.readLine());  
             sb.append(line).append(System.getProperty("line.separator"));  
            }  
            bf.close();
            String res = Json.isJson(sb.toString()) ? sb.toString() : "请求错误！";
            return res;

        } catch (Exception e) {
        		throw new Exception("senterror");
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    


    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws Exception 
     */
    public static String sendPost(String url, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        if(url.length()==0) {
        	return "请输入URL。";
        }
        else if (!url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")){
        	return "URL非法";
        }

        //匹配出键值对
        Matcher m = Pattern.compile("[-a-zA-Z0-9]{1,}=[-a-zA-Z0-9]{1,}").matcher(param);
        String paramDeal = "";
        if(param.length() != 0) while(m.find()) paramDeal += "&" + m.group();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"); 
            conn.addRequestProperty("accept-charset", "UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));   
            String line;  
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据  
              
            // 循环读取流,若不到结尾处  
            while ((line = bf.readLine()) != null) {  
             //sb.append(bf.readLine());  
             sb.append(line).append(System.getProperty("line.separator"));  
            }  
            bf.close();
            String res = Json.isJson(sb.toString()) ? sb.toString() : "请求错误！";
            return res;
        } catch (Exception e) {
        	throw new Exception("senterror");
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }    

}
