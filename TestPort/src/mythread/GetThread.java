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
				if(str.equals("������URL��") || str.equals("URL�Ƿ�")) {
					a.lose++;
					jtp.setText(jtp.getText()+Thread.currentThread().getName()+"ʧ�ܣ�\n");
				}else {
					a.count++;
					jtp.setText(jtp.getText()+Thread.currentThread().getName()+"�ɹ���\n");
				}
			} catch(Exception e) {
				a.lose++;
				jtp.setText(jtp.getText()+Thread.currentThread().getName()+"ʧ�ܣ�\n");
//	            e.printStackTrace();
			}
			finally {
				if((a.count + a.lose) == num) {
					jtp.setText(jtp.getText()+"�ɹ�"+a.count+"����ʧ��"+a.lose+"��");
				}
				gui.CreatGui.textPane_1.setText(Json.outLineNum(DocAttribute.getLines(jtp)));	
				try{sleep(1);} catch(InterruptedException e) {e.printStackTrace();;}	
			}
	
		}
		
		
	}
}
