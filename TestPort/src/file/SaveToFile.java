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
		//利用时间函数生成唯一的时间字符串，用作保存文件的文件名
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
		Date now = new Date(); 
		String filename = dateFormat.format( now ); 
		File savefile=new File(filepath,filename+".json");
		//将字符串写入文件中
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
