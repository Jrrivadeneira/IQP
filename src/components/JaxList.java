package components;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

public class JaxList extends JPanel {

	/**
	 * Written by Jack Rivadeneira
	 */
	private static final long serialVersionUID = -1862712982356137942L;

	public static void main(String[] args) {
		System.out.println("Program Start!");
		JaxList jl = new JaxList();
		jl.setListFont(new Font("Helvetica", Font.BOLD, 122));
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setSize(500, 500);
		jf.add(jl, BorderLayout.CENTER);
		jf.setVisible(true);
		int i = 0;
		while (i < 10) {
			jl.addToList("" + i);
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
			}
			i++;
		}
	}

	private DefaultListModel<String> lm;
	private JScrollPane listScroller;
	private JList<String> list;

	// private Font f;

	public JaxList() {
		setLayout(new BorderLayout());
		lm = new DefaultListModel<String>();
		list = new JList<String>(lm);
		listScroller = new JScrollPane(list);
		add(listScroller, BorderLayout.CENTER);
		// f = new Font("Helvetica", Font.BOLD, 12);
	}

	private void refresh() {
		add(listScroller, BorderLayout.CENTER);
		validate();
	}

	public void setListFont(Font f) {
		this.list.setFont(f);
	}

	public void addToList(String element) {
		lm.addElement(element);
		refresh();
	}

	public void addToList(String[] elements) {
		int i = 0;
		while (i < elements.length) {
			lm.addElement(elements[i]);
			i++;
		}
		refresh();
	}

	public void removeFromList(int i) {
		lm.removeElementAt(i);
		refresh();
	}

	public void removeFromList(String s) {
		lm.removeElement(s);
		refresh();
	}

	public String getSelectedValue() {
		return this.list.getSelectedValue();
	}

	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}

	public int[] getSelectedIndcies() {
		return this.list.getSelectedIndices();
	}

	public void removeSelectedIndices(int[] index) {
		int i = 0;
		while (i < index.length) {
			list.remove(index[i]);
			i++;
		}
	}
}
