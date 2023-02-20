package vertical_text_labal;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicLabelUI;
import javax.swing.text.View;

/**
 * @author murase
 *
 */
 class Dot extends BasicLabelUI {
    private static Rectangle paintIconR = new Rectangle();
    private static Rectangle paintTextR = new Rectangle();
    private static Rectangle paintViewR = new Rectangle();
    private int width_adjustment = 0;
    private int height_adjustment = 10;
    public Dot() {
        super();
    }
    
    /*
     * 読点「、」を横書きから縦書きに位置を変える
     *  width_adjustment 　　横幅調節 
     * 　height_adjustment　　縦幅調節 10　少し縦幅を少なくしている
     * 
     */
    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);
        return new Dimension(d.width - width_adjustment, d.height - height_adjustment);  
    }

    public void paint(Graphics g, JComponent c) {
        JLabel label = (JLabel) c;
        String text = label.getText();
        Icon icon = null;

        FontMetrics fm = g.getFontMetrics();
        Insets insets = c.getInsets(new Insets(0, 0, 0, 0));

        paintViewR.y = insets.left;
        paintViewR.x = insets.top;
    	paintViewR.height = c.getHeight() - (insets.left + insets.right);
        paintViewR.width = c.getWidth() - (insets.top + insets.bottom);
        
        paintIconR.x = paintIconR.y = paintIconR.width = paintIconR.height = 0;
        paintTextR.x = paintTextR.y = paintTextR.width = paintTextR.height = 0;

        String clippedText = layoutCL(label, fm, text, icon, paintViewR, paintIconR, paintTextR);

        if (text != null) {
            View v = (View) c.getClientProperty(BasicHTML.propertyKey);

            if (v != null) {
                v.paint(g, paintTextR);
            } else {
                int textX;
                int textY;
                textX = paintTextR.x + fm.getMaxAdvance()/4;
                textY = +paintTextR.y+fm.getAscent()/4 + height_adjustment/2;
               
                if (label.isEnabled()) {
                    paintEnabledText(label, g, clippedText, textX, textY);
                } else {
                    paintDisabledText(label, g, clippedText, textX, textY);
                }
            }
        }
    }
}