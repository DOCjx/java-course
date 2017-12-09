package file;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class GetFilePath {
	private static Component chatFrame;

	public static String fun() {
		int result = 0;
		File file = null;
		String path = "";
		try {
		JFileChooser fileChooser = new JFileChooser();
		FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
		//System.out.println(fsv.getDefaultDirectory());                //得到桌面路径
		fileChooser.setCurrentDirectory(fsv.getDefaultDirectory());
		fileChooser.setDialogTitle("请选择文件的保存路径...");
		fileChooser.setApproveButtonText("确定");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		result = fileChooser.showOpenDialog(chatFrame);
		if (JFileChooser.APPROVE_OPTION == result) {
		    	   path=fileChooser.getSelectedFile().getPath();
		   }
		}
		catch(Exception e) {;}
		return path.toString();
	}

}
