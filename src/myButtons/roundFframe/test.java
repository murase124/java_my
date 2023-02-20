package myButtons.roundFframe;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.AbstractBorder;

public final class test extends JPanel {
private static final TexturePaint TEXTURE = makeCheckerTexture();

private test() {
 super(new BorderLayout());

 JPanel textField01 = new JPanel() {
   // Unleash Your Creativity with Swing and the Java 2D API!
   // http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
   // http://web.archive.org/web/20091205092230/http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
   @Override protected void paintComponent(Graphics g) {
     if (!isOpaque()) {
       int w = getWidth() - 1;
       int h = getHeight() - 1;
       Graphics2D g2 = (Graphics2D) g.create();
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       g2.setPaint(Color.red);
       g2.fillRoundRect(0, 0, w, h, h, h);
       g2.setPaint(Color.GRAY);
       g2.drawRoundRect(0, 0, w, h, h, h);
       g2.dispose();
     }
     super.paintComponent(g);
   }

   @Override public void updateUI() {
     super.updateUI();
     setOpaque(false);
     // setBackground(new Color(0x0, true));
     setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
   }
 };
 JTextField a = new JTextField(20);
 a.setText("aaaaaaaaaaa");
 textField01.add(a);



 
 JRadioButton r1 = new JRadioButton("default", true);
 JRadioButton r2 = new JRadioButton("setOpaque(false) + TexturePaint");
 ActionListener l = e -> {
   setOpaque(e.getSource() == r1);
   //repaint();
 };
 ButtonGroup bg = new ButtonGroup();
 Box box = Box.createHorizontalBox();
 box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
 Stream.of(r1, r2).forEach(b -> {
   b.addActionListener(l);
   b.setOpaque(false);
   bg.add(b);
   box.add(b);
 });
 JPanel p = new JPanel(new GridLayout(2, 1, 5, 5));
 p.setOpaque(false);
 p.add(makeTitledPanel("Override: JTextField#paintComponent(...)", textField01));
 add(p);
 add(box, BorderLayout.NORTH);
 setBorder(BorderFactory.createEmptyBorder(2, 20, 20, 20));
 setPreferredSize(new Dimension(320, 240));
}

private static Component makeTitledPanel(String title, Component cmp) {
 JPanel p = new JPanel(new GridBagLayout());
 p.setBorder(BorderFactory.createTitledBorder(title));
 p.setOpaque(false);
 GridBagConstraints c = new GridBagConstraints();
 c.weightx = 1d;
 c.fill = GridBagConstraints.HORIZONTAL;
 c.insets = new Insets(5, 5, 5, 5);
 p.add(cmp, c);
 return p;
}

@Override protected void paintComponent(Graphics g) {
 super.paintComponent(g);
 if (!isOpaque()) {
   Graphics2D g2 = (Graphics2D) g.create();
   g2.setPaint(TEXTURE);
   g2.fillRect(0, 0, getWidth(), getHeight());
   g2.dispose();
 }
}

private static TexturePaint makeCheckerTexture() {
 int cs = 6;
 int sz = cs * cs;
 BufferedImage img = new BufferedImage(sz, sz, BufferedImage.TYPE_INT_ARGB);
 Graphics2D g2 = img.createGraphics();
 g2.setPaint(new Color(100, 100, 100, 50));
 g2.fillRect(0, 0, sz, sz);
 for (int i = 0; i * cs < sz; i++) {
   for (int j = 0; j * cs < sz; j++) {
     if ((i + j) % 2 == 0) {
       g2.fillRect(i * cs, j * cs, cs, cs);
     }
   }
 }
 g2.dispose();
 return new TexturePaint(img, new Rectangle(sz, sz));
}

public static void main(String... args) {
 EventQueue.invokeLater(new Runnable() {
   @Override public void run() {
     createAndShowGui();
   }
 });
}

public static void createAndShowGui() {
 try {
   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
 } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
   ex.printStackTrace();
 }
 JFrame frame = new JFrame("RoundedTextField");
 frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 frame.getContentPane().add(new test());
 frame.pack();
 frame.setLocationRelativeTo(null);
 frame.setVisible(true);
}
}

class RoundedCornerBorder extends AbstractBorder {
private static final Color ALPHA_ZERO = new Color(0x0, true);

@Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
 Graphics2D g2 = (Graphics2D) g.create();
 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 Shape border = getBorderShape(x, y, width - 1, height - 1);

 // Container parent = c.getParent();
 // if (Objects.nonNull(parent)) {
 //   // g2.setPaint(parent.getBackground());
 //   g2.setPaint(new Color(0x0, true));
 //   Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
 //   corner.subtract(new Area(border));
 //   g2.fill(corner);
 // }

 g2.setPaint(ALPHA_ZERO);
 // Area corner = new Area(border.getBounds2D());
 Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
 corner.subtract(new Area(border));
 g2.fill(corner);

 g2.setPaint(Color.GRAY);
 g2.draw(border);
 g2.dispose();
}

public Shape getBorderShape(int x, int y, int w, int h) {
 int r = h; // h / 2;
 return new RoundRectangle2D.Double(x, y, w, h, r, r);
}

@Override public Insets getBorderInsets(Component c) {
 return new Insets(4, 8, 4, 8);
}

@Override public Insets getBorderInsets(Component c, Insets insets) {
 insets.set(4, 8, 4, 8);
 return insets;
}
}

