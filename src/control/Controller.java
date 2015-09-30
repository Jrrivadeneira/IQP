package control;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import ui.UI;

public interface Controller extends KeyListener,MouseListener,ActionListener{
	public void say(String s);
	public void addUI(UI u);
}