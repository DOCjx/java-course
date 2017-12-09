package file;

import java.io.BufferedWriter;
import static main.MainApp.filepath;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveToFile {
	private String data;
	public SaveToFile(String s) {this.data=s;}
	public void save(String filepath) {
		//����ʱ�亯������Ψһ��ʱ���ַ��������������ļ����ļ���
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
		Date now = new Date(); 
		String filename = dateFormat.format( now ); 
		File savefile=new File(filepath,filename+".json");
		//���ַ���д���ļ���
		try {
				FileWriter fw=new FileWriter(savefile);
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write(data);
				bw.close();fw.close();			
		}
		catch(Exception e) {
			 //e.printStackTrace(); 
		}
		
	}
}
