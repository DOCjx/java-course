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
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
	 * @throws Exception 
     */
    public static String sendGet(String url, String param) throws Exception {
        String result = "";
        BufferedReader in = null;
        JTextPane jtp=gui.CreatGui.txtpnprogrammers;
        if(url.length()==0) {
        	return "������URL��";
        }
        else if (!url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")){
        	return "URL�Ƿ�";
        }

        //ƥ�����ֵ��
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
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));   
            String line;  
            StringBuilder sb = new StringBuilder(); // �����洢��Ӧ����  
              
            // ѭ����ȡ��,��������β��  
            while ((line = bf.readLine()) != null) {  
             //sb.append(bf.readLine());  
             sb.append(line).append(System.getProperty("line.separator"));  
            }  
            bf.close();
            String res = Json.isJson(sb.toString()) ? sb.toString() : "�������";
            return res;

        } catch (Exception e) {
        		throw new Exception("senterror");
        }
        // ʹ��finally�����ر�������
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
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return ������Զ����Դ����Ӧ���
     * @throws Exception 
     */
    public static String sendPost(String url, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        if(url.length()==0) {
        	return "������URL��";
        }
        else if (!url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")){
        	return "URL�Ƿ�";
        }

        //ƥ�����ֵ��
        Matcher m = Pattern.compile("[-a-zA-Z0-9]{1,}=[-a-zA-Z0-9]{1,}").matcher(param);
        String paramDeal = "";
        if(param.length() != 0) while(m.find()) paramDeal += "&" + m.group();
        try {
            URL realUrl = new URL(url);
            // �򿪺�URL֮�������
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"); 
            conn.addRequestProperty("accept-charset", "UTF-8");
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));   
            String line;  
            StringBuilder sb = new StringBuilder(); // �����洢��Ӧ����  
              
            // ѭ����ȡ��,��������β��  
            while ((line = bf.readLine()) != null) {  
             //sb.append(bf.readLine());  
             sb.append(line).append(System.getProperty("line.separator"));  
            }  
            bf.close();
            String res = Json.isJson(sb.toString()) ? sb.toString() : "�������";
            return res;
        } catch (Exception e) {
        	throw new Exception("senterror");
        }
        //ʹ��finally�����ر��������������
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
