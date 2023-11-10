package calen05.mainFrame.scheduleDisplayPanel.panels.List;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class JTextPaneMy extends JTextPane{
	
	private DefaultStyledDocument doc;
	private StyleContext sc;
	private  MyMouseListener action = new MyMouseListener();
	private List<ListPanelListener> listeners = new ArrayList<ListPanelListener>();
	public void addListPanelListener(ListPanelListener listener) {
		listeners.add(listener);
	}
	
	public void changeChoiceLine(int lineNum) {
		listeners.forEach(listener -> listener.changePerformed(lineNum));
	}
	
	@Override
	public boolean getScrollableTracksViewportWidth() {
		Object parent = getParent();
		if (parent instanceof JViewport) {
			JViewport port = (JViewport) parent;
			int w = port.getWidth();	// 表示できる範囲(上限)

			TextUI ui = getUI();
			Dimension sz = ui.getPreferredSize(this); // 実際の文字列サイズ
			if (sz.width < w) {
				return true;
			}
		}
		return false;
	}
	
	public  void append(String text) {
		initDocument(text);		
		int prevIndex= -1;
		int index = 0;
		while (prevIndex != doc.getParagraphElement(index).getEndOffset()) {
			prevIndex = doc.getParagraphElement(index).getEndOffset();
			index = doc.getParagraphElement(index).getEndOffset();
		}

	}
	protected void initDocument(String sb){
		int prevIndex= -1;
		int index = 0;
		while (prevIndex != doc.getParagraphElement(index).getEndOffset()) {
			prevIndex = doc.getParagraphElement(index).getEndOffset();
			index = doc.getParagraphElement(index).getEndOffset();
		}

		try{
			/* 文書を挿入する */
			doc.insertString(index-1 
			, sb
			, sc.getStyle(StyleContext.DEFAULT_STYLE));
		}catch (BadLocationException ble){
			System.err.println("初期文書の読み込みに失敗しました。");
		}
	}
	
	public  void clearText() {
		super.setText("");
		action.clearChoice();
	}

    public JTextPaneMy() {
		// TODO 自動生成されたコンストラクター・スタブ
    	sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);

        this.setDocument(doc);
        addMouseListener(action);
	}

	
	protected void changeStyle(int start, int len){
		MutableAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setBold(attr, true);
		
		/* 4文字目から8文字分だけスタイルを変更する */
		doc.setCharacterAttributes(start, len, attr, false);
	}
	protected void nochangeStyle(int start, int len){
		MutableAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setBold(attr, false);
		
		/* 4文字目から8文字分だけスタイルを変更する */
		doc.setCharacterAttributes(start, len, attr, false);
	}
	
	class MyMouseListener implements MouseListener{
		private int start = -1;
		private int end = -1;
		private int choiceLine = -1;
		public void setChoiceLine(int choiceLine) {
			this.choiceLine = choiceLine;
			changeChoiceLine(choiceLine);
		}
		public int getChoiceLine() {
			return choiceLine;
		}
		public void clearChoice() {
			if(this.start != this.end) nochangeStyle(this.start, this.end-this.start);
			start = -1;
			end = -1; 
			setChoiceLine(-1);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			int startNext = doc.getParagraphElement(getCaretPosition()).getStartOffset();
			int endNext = doc.getParagraphElement(getCaretPosition()).getEndOffset();
			int prevIndex= -1;
			int index = 0;
			int i =0;
			while (prevIndex != doc.getParagraphElement(index).getEndOffset()) {
				i +=1;
				prevIndex = doc.getParagraphElement(index).getEndOffset();
				index = doc.getParagraphElement(index).getEndOffset();
				if(endNext == index) {
					break;
				}
			}
			if(this.start != this.end) nochangeStyle(this.start, this.end-this.start);
			changeStyle(startNext, endNext-startNext);
			this.start = startNext;
			this.end = endNext;
			setChoiceLine(i);
		}
		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
}


