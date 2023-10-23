package calen05.schedulePanel.Panels.List;

import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class JTextPaneMy extends JTextPane{
	
	DefaultStyledDocument doc;
	StyleContext sc;
	
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
		int backIndex= -1;
		int index = 0;
		while (backIndex != doc.getParagraphElement(index).getEndOffset()) {
			backIndex = doc.getParagraphElement(index).getEndOffset();
			index = doc.getParagraphElement(index).getEndOffset();
		}

	}
	protected void initDocument(String sb){
		int backIndex= -1;
		int index = 0;
		while (backIndex != doc.getParagraphElement(index).getEndOffset()) {
			backIndex = doc.getParagraphElement(index).getEndOffset();
			index = doc.getParagraphElement(index).getEndOffset();
		}

		try{
			/* 文書を挿入する */
			doc.insertString(index-1 //doc.getParagraphElement(0).getEndOffset()-1
			, sb
			, sc.getStyle(StyleContext.DEFAULT_STYLE));
		}catch (BadLocationException ble){
			System.err.println("初期文書の読み込みに失敗しました。");
		}
	}
	
	public  void clearText() {
		super.setText("");
	}
	
	

    public JTextPaneMy() {
		// TODO 自動生成されたコンストラクター・スタブ
    	sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);

        this.setDocument(doc);
        addCaretListener(new action(this));

        /* 初期文書の読み込み */
        //initDocument(doc, sc);
	}

	
	protected void changeStyle(){
		MutableAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setBold(attr, true);
		
		
		/* 4文字目から8文字分だけスタイルを変更する */
		doc.setCharacterAttributes(0, 29, attr, false);
		int num = doc.getParagraphElement(1).getEndOffset();
		doc.setParagraphAttributes(StyleConstants.ALIGN_JUSTIFIED, 10, attr, false);
	}
	
	class action implements CaretListener{
		JTextPaneMy jTextPaneMy;
		public action(JTextPaneMy jTextPaneMy) {
			this.jTextPaneMy = jTextPaneMy;
			
		}
		@Override
		public void caretUpdate(CaretEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
			System.out.println(e.getSource());
			System.out.println(e.getSource().getClass());
			System.out.println(jTextPaneMy.getCaretPosition());
			System.out.println(jTextPaneMy.getParagraphAttributes());
			System.out.println(jTextPaneMy.getParagraphAttributes());
			//System.out.println(jTextPaneMy.getText(ABORT, WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
		}
		
	}
}


