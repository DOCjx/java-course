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
		FileSystemView fsv = FileSystemView.getFileSystemView();  //ע���ˣ�������Ҫ��һ��
		//System.out.println(fsv.getDefaultDirectory());                //�õ�����·��
		fileChooser.setCurrentDirectory(fsv.getDefaultDirectory());
		fileChooser.setDialogTitle("��ѡ���ļ��ı���·��...");
		fileChooser.setApproveButtonText("ȷ��");
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
