package mythread;

import javax.swing.JTextPane;

import tool.DocAttribute;
import tool.Json;

public class GetThread extends Thread {
	private Counter a;
	private String url,param;
	private int num;
	public GetThread(int i,String url,String param,Counter a,int num) {super(i+"");this.url=url;this.param=param;this.a=a;this.num=num;}
	public void run() {
		synchronized (a) {
			JTextPane jtp=gui.CreatGui.txtpnprogrammers;

			try{
				String str = web.WebRequest.sendGet(url, param);
				if(str.equals("请输入URL。") || str.equals("URL非法")) {
					a.lose++;
					jtp.setText(jtp.getText()+Thread.currentThread().getName()+"失败！\n");
				}else {
					a.count++;
					jtp.setText(jtp.getText()+Thread.currentThread().getName()+"成功！\n");
				}
			} catch(Exception e) {
				a.lose++;
				jtp.setText(jtp.getText()+Thread.currentThread().getName()+"失败！\n");
//	            e.printStackTrace();
			}
			finally {
				if((a.count + a.lose) == num) {
					jtp.setText(jtp.getText()+"成功"+a.count+"个，失败"+a.lose+"个");
				}
				gui.CreatGui.textPane_1.setText(Json.outLineNum(DocAttribute.getLines(jtp)));	
				try{sleep(1);} catch(InterruptedException e) {e.printStackTrace();;}	
			}
	
		}
		
		
	}
}
