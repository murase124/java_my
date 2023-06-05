package calen01;

import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.plaf.TextUI;

public class JTextPaneMy extends JTextPane{
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
}
