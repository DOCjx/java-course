package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import file.SaveToFile;
import gui.CreatGui;
import mythread.Counter;
import mythread.GetThread;
import mythread.PostThread;
import tool.DocAttribute;
import tool.Json;
import web.WebRequest;

public class DealEvent implements ActionListener{
	private CreatGui gg;
	private SaveToFile stf;
	public void setGui(CreatGui gg) {this.gg=gg;}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("保存")) {
			String filepath=file.GetFilePath.fun();
			stf=new SaveToFile(gg.txtpnprogrammers.getText());
			stf.save(filepath);
		}
		
		if (e.getSource()==gg.concB) {
			if(gg.concB.getSelectedItem().equals("并发")) {
				gg.Tconc.setEditable(true);
				gg.setVisible(true);
			}
			if(gg.concB.getSelectedItem().equals("不并发")) {
				gg.Tconc.setEditable(false);
				gg.Tconc.setText("");
				gg.setVisible(true);
			}
			
			
		}
		
		if(e.getSource()==gg.go) {
			gui.CreatGui.txtpnprogrammers.setText("");
			mythread.Counter.count=0;
			String url=gg.Turl.getText();
			String param=gg.Tparameter.getText();
			//不并发程序
			if(gg.concB.getSelectedItem().equals("不并发")) {
				if(gg.wayB.getSelectedItem().equals("GET")) {
					
					//发送get网络请求
					try {
						CreatGui.txtpnprogrammers.setText(Json.formatJson(WebRequest.sendGet(url,param)));
					} catch (Exception e1) {
						CreatGui.txtpnprogrammers.setText("请求错误！");
						// TODO 自动生成的 catch 块
//						e1.printStackTrace();
					}
					gg.textPane_1.setText(Json.outLineNum(DocAttribute.getLines(gg.txtpnprogrammers)));
					
					
				}
				if(gg.wayB.getSelectedItem().equals("POST")) {
					//发送post网络请求
					try {
						gg.txtpnprogrammers.setText(Json.formatJson(WebRequest.sendPost(url,param)));
					} catch (Exception e1) {
						CreatGui.txtpnprogrammers.setText("请求错误！");
						// TODO 自动生成的 catch 块
//						e1.printStackTrace();
					}
					gg.textPane_1.setText(Json.outLineNum(DocAttribute.getLines(gg.txtpnprogrammers)));
					
				}
			}
			//并发程序
			if(gg.concB.getSelectedItem().equals("并发")) {
				int num;
				mythread.Counter.count=0;
				mythread.Counter.lose=0;
				try {
					num=Integer.parseInt(gg.Tconc.getText());
				}
				catch(NumberFormatException ne) {
					num=1;
				}
				if(gg.wayB.getSelectedItem().equals("GET")) {
					Counter a=new Counter();
					for(int i=0;i<num;i++) {
						new GetThread(i,gg.Turl.getText(),gg.Tparameter.getText(),a,num).start();
					}

				}
				if(gg.wayB.getSelectedItem().equals("POST")) {
					Counter a=new Counter();
					for(int i=0;i<num;i++) {
						new PostThread(i,gg.Turl.getText(),gg.Tparameter.getText(),a,num).start();					
					}
				
				}			
		}
	}
	

}
}
