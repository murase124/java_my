package time;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;

public class Test {

    public static void main(String args[]) {
    	Test a =new Test();
    	a.new test01();
	}
    
    public class test04 extends JPanel{

    	  public static void main(String[] args){
    	    JFrame frame = new JFrame();
    	    Test a =new Test();
    	    
    	    frame.getContentPane().add(a.new test04());

    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    frame.setBounds(10, 10, 300, 200);
    	    frame.setTitle("タイトル");
    	    frame.setVisible(true);
    	  }

    	  public void paintComponent(Graphics g){
    	    g.setColor(Color.red);
    	    g.fillRect(40, 20, 200, 120);

    	    g.setColor(Color.blue);
    	    g.drawString("Hello Java2D", 10, 50);
    	  }
    	}
    
    public class test03 {
    	test03(){
    		
    		JPanel textField01 = new JPanel() {
    			  @Override protected void paintComponent(Graphics g) {
    			    if (!isOpaque()) {
    			      int w = getWidth() - 1;
    			      int h = getHeight() - 1;
    			      Graphics2D g2 = (Graphics2D) g.create();
    			      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    			                          RenderingHints.VALUE_ANTIALIAS_ON);
    			      g2.setPaint(UIManager.getColor("TextField.background"));
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
    			    setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
    			  }
    			};
    			
    			JFrame win = new JFrame();
        		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		win.setBounds(10 , 10 , 400 , 300);
        		win.getContentPane().add(textField01);
        		win.setVisible(true);
    	}
    }
	public class test02 extends JFrame{

    	test02(){
    	JLayeredPane pane = new JLayeredPane();

		int layer = (int)(Math.random() * (400));
		JButton button = new JButton("レイヤ : " + layer);
		button.setBounds(20 , 40 , 200 , 50);
		pane.add(button);
		pane.setLayer(button , 1);
	
	
		JFrame win = new JFrame();
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setBounds(10 , 10 , 400 , 300);
		win.getContentPane().add(pane);
		win.setVisible(true);
    	}
    }
	
	public class test01 {
	
	    /**
	     * NTPサーバー
	     * <p>
	     * 独立行政法人情報通信研究機構 (NICT)<br>
	     * http://jjy.nict.go.jp/tsp/PubNtp/index.html
	     * </p>
	     */
	    private static final String NTP_SERVER = "ntp.nict.jp";
	
	    test01() {
	        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss.SSS");
	        NTPUDPClient client = new NTPUDPClient();
	        try {
	            client.open();
	            InetAddress host = InetAddress.getByName(NTP_SERVER);
	            TimeInfo info = client.getTime(host);
	
	            info.computeDetails();
	            Date exactTime = new Date(System.currentTimeMillis() + info.getOffset());
	            System.out.println("正しい時刻\n" + formater.format(exactTime) + "\n");
	
	            NtpV3Packet packet = info.getMessage();
	
	            System.out.println("[t1] クライアントがパケットを送信した時刻（Originate TimeStamp）\n"
	                    + formater.format(packet.getOriginateTimeStamp().getDate()) + "\n");
	
	            System.out.println("[t2] NTPサーバーがパケットを受信した時刻（Receive TimeStamp）\n"
	                    + formater.format(packet.getReceiveTimeStamp().getDate()) + "\n");
	
	            System.out.println("[t3] NTPサーバーがパケットを送信した時刻（Transmit TimeStamp）\n"
	                    + formater.format(packet.getTransmitTimeStamp().getDate()) + "\n");
	
	            System.out.println("[t4] クライアントがパケットを受信した時刻（Return TimeStamp）\n"
	                    + formater.format(new Date(info.getReturnTime())) + "\n");
	
	            System.out.println("往復にかかった時間（Roundtrip Time）\n" + info.getDelay() + "ms\n");
	            System.out.println("クライアントの時刻差（Offset）\n" + info.getOffset() + "ms\n");
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            client.close();
	        }
	    }
	}
}