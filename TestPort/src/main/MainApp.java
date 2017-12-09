package main;

import java.io.IOException;

import file.GetFilePath;
import gui.CreatGui;
import web.WebRequest;
import javax.swing.JScrollPane;

import event.DealEvent;

import java.awt.BorderLayout;


public class MainApp {
	public static final String filepath="C:/javaµÄ²âÊÔÊý¾Ý/";
	public static void main(String[] args) throws IOException {
			CreatGui gg=new CreatGui();
			DealEvent dd=new DealEvent();
			gg.setDeal(dd);
			dd.setGui(gg);
	}
}
