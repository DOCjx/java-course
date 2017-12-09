package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import event.DealEvent;
import tool.DocAttribute;
import tool.Json;
import web.WebRequest;

class tu extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image image;
	public tu() {
		//super();
	      setOpaque(true);
	 
	      image = Toolkit.getDefaultToolkit().getImage( "images/4.jpg"); ;
	   }
	 
	   public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      setBackground(Color.WHITE);
	      if (image != null) {
	         int height = image.getHeight(this);
	         int width = image.getWidth(this);
	 
	         if (height != -1 && height > getHeight())
	            height = getHeight();
	 
	         if (width != -1 && width > getWidth())
	            width = getWidth();
	 
	         int x = (int) (((double) (getWidth() - width)) / 2.0);
	         int y = (int) (((double) (getHeight() - height)) / 2.0);
	         g.drawImage(image, x, y, width, height, this);
	      }
	}
}


public class CreatGui extends JFrame{
	public static JLabel way,conc,url,logFile,result,kong,parameter;        //标签
	public static JTextField Tconc,Turl,Tparameter;							//普通文本框
	public static JButton go,refresh,look,delete,save;						//按键
	public static JComboBox wayB,concB;										//下拉框
	public static String wayName[]= {"GET","POST"};							//方法数组
	public static String concName[]= {"不并发","并发"};						
	public static JPanel contentPane;
	public static JTextPane txtpnprogrammers;
	public static JTextPane textPane_1;
	
	private DealEvent d;
	public void setDeal(DealEvent d) {
		this.d=d;
	    save.addActionListener(d);
	    go.addActionListener(d);
	    wayB.addActionListener(d);
	    concB.addActionListener(d);
	}


	public CreatGui() {
		super("前后端接口测压");
		ImageIcon icon=new ImageIcon("images/logo1.jpg");
	    setIconImage(icon.getImage());
		setSize(805,485);
		setBounds(100, 100, 762, 556);
		setResizable(false);
		Font f1=new Font("宋体",Font.BOLD,20);
		//setBackground(Color.lightGray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	//设置点击关闭按钮
		
		JPanel p1=new JPanel();
		
		way=new JLabel("方式:");way.setFont(f1);
		wayB = new JComboBox(wayName);wayB.setFont(f1);
		concB=new JComboBox(concName);concB.setFont(f1);
		conc=new JLabel("并发数:");conc.setFont(f1);
		Tconc=new JTextField("",5);Tconc.setFont(f1);Tconc.setEditable(false);
		url=new JLabel("URL:");url.setFont(f1);
		Turl=new JTextField("",20);Turl.setFont(f1);
		go=new JButton("发送");go.setFont(f1);
		p1.add(way);p1.add(wayB);p1.add(concB);p1.add(conc);p1.add(Tconc);p1.add(url);p1.add(Turl);p1.add(go);
		
		JPanel p7=new JPanel();
		parameter=new JLabel("参数:");parameter.setFont(f1);
		Tparameter=new JTextField("",60);Tparameter.setFont(f1);
		p7.add(parameter);p7.add(Tparameter);
		//add(p7);
		
		JPanel p6=new JPanel();
		p6.setLayout(new BorderLayout());
		p6.add(p1,BorderLayout.NORTH);
		p6.add(p7,BorderLayout.SOUTH);
		add(p6,BorderLayout.NORTH);
		
		tu p2=new tu();
		p2.setPreferredSize(new Dimension(200, 200)); 
		p2.setBackground(Color.blue);
		//logFile=new JLabel("日志文件");logFile.setFont(f1);  
		//p2.add(logFile);
		add(p2,BorderLayout.WEST);
		
		JTextArea ta=new JTextArea();
		JPanel p5=new JPanel ();
		p5.setLayout(new BorderLayout());
		JScrollPane jsp=new JScrollPane(ta);
		p5.setPreferredSize(new Dimension(200, 20)); 
		p5.add(jsp);
		add(p5);
		
		txtpnprogrammers = new JTextPane();
		txtpnprogrammers.setFont(new Font("Courier New", Font.PLAIN, 15));
		//向面板输出未格式化json
//		WebRequest wr=new WebRequest(); 
//		String ss=wr.sendGet("http://www.pm25.in/api/querys/pm2_5.json", "city=beijing&token=5j1znBVAsnSf5xQyNQyq");
//		String ss=wr.sendGet("http://localhost:3000/posts", "id=1");
		
//		txtpnprogrammers.setText("{ \"programmers\": [\r\n\r\n{ \"firstName\": \"Brett\", \"lastName\":\"McLaughlin\", \"email\": \"aaaa\" },\r\n\r\n{ \"firstName\": \"Jason\", \"lastName\":\"Hunter\", \"email\": \"bbbb\" },\r\n\r\n{ \"firstName\": \"Elliotte\", \"lastName\":\"Harold\", \"email\": \"cccc\" }\r\n\r\n],\r\n\r\n\"authors\": [\r\n\r\n{ \"firstName\": \"Isaac\", \"lastName\": \"Asimov\", \"genre\": \"science fiction\" },\r\n\r\n{ \"firstName\": \"Tad\", \"lastName\": \"Williams\", \"genre\": \"fantasy\" },\r\n\r\n{ \"firstName\": \"Frank\", \"lastName\": \"Peretti\", \"genre\": \"christian fiction\" }\r\n\r\n],\r\n\r\n\"musicians\": [\r\n\r\n{ \"firstName\": \"Eric\", \"lastName\": \"Clapton\", \"instrument\": \"guitar\" },\r\n\r\n{ \"firstName\": \"Sergei\", \"lastName\": \"Rachmaninoff\", \"instrument\": \"piano\" }\r\n\r\n] }");
//		txtpnprogrammers.setToolTipText("\u7F16\u8F91\u6587\u672C");
		jsp.setViewportView(txtpnprogrammers);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Courier New", Font.PLAIN, 15));
		textPane_1.setEditable(false);
		textPane_1.setEnabled(false);
		jsp.setRowHeaderView(textPane_1);
		//调用formatJson將返回的json格式化并向文本面板输出
//		txtpnprogrammers.setText(Json.formatJson(ss));
		txtpnprogrammers.setEditable(false);
		//System.out.println(ss);
		//调用getLines得到行数向左侧面板输出
//		textPane_1.setText(Json.outLineNum(DocAttribute.getLines(txtpnprogrammers)));
		
		JPanel p3=new JPanel();
		p3.setLayout(new BorderLayout());
		//p3.setBackground(Color.red);
		result=new JLabel("结果输出");result.setFont(f1);
		save=new JButton("保存");save.setFont(f1);
		p3.add(save,BorderLayout.SOUTH);
		p3.add(result,BorderLayout.NORTH);
		p3.add(p5,BorderLayout.CENTER);
		add(p3,BorderLayout.CENTER);
		
		
		setVisible(true);
		
	}
		
}