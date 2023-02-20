package time;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import opaqueChange.OpaqueChange;

/*
 *  setCreateEmptyBorderAppearance arrange use
 *	setCreateEmptyBorderは見た目を整えるために使用
 */

public class Clock extends JFrame{

	private JLabel Timestamp_yMd;//名前
	private JLabel Timestamp_Hms;//名前
	private JLabel update;//更新
    private Timer timer;
    private TimerTask task;
    private Long clockOffset = (long) 0;

    final public static String cardPanelName_Clock = "Clock";
	
	Clock(JPanel cardPanel){
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

		//年月日
		JPanel p1 = new JPanel();
		p1.setLayout(new  BoxLayout(p1, BoxLayout.X_AXIS));
		p1.setBorder(BorderFactory.createEmptyBorder(20, 10, 5, 10));//ラベル

		Timestamp_yMd = new JLabel(Timestamp_yMd());
		Timestamp_yMd.setFont(new Font("",Font.PLAIN ,30));
		p1.add(Timestamp_yMd);
		p.add(p1);
		
		//時間
		JPanel p2 = new JPanel();
		p2.setLayout(new  BoxLayout(p2, BoxLayout.X_AXIS));
		p2.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));//ラベル

		Timestamp_Hms = new JLabel(Timestamp_Hms());
		Timestamp_Hms.setFont(new Font("",Font.PLAIN ,30));
		p2.add(Timestamp_Hms);
		p.add(p2);
		
		update = new JLabel("↻");
		update.setFont(new Font("",Font.PLAIN ,30));
		update.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));//ラベル
		update.setForeground(new Color(0,255,0));
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				setOffset();
				clockprocess();
			}
		});
		p2.add(update);

		setOffset();
		clockprocess();
		
		new OpaqueChange(p);
		cardPanel.add(p, cardPanelName_Clock);
	}
	
	//更新を止める
	public void cancel() {
		if(timer != null) timer.cancel();
	}
	//更新再開
	public void clockprocess() {
		if(timer != null) timer.cancel();
		task = new TimerTask() {
			public void run() {
				Timestamp_yMd.setText(Timestamp_yMd());
				Timestamp_Hms.setText(Timestamp_Hms());
			}
		};
		timer = new Timer();
		/*
		 * time update interval
		 * stand-by 0  interval 90 (properness)
		 * タイマー更新間隔
		 * 待機　０　間隔　90 (適当)
		 */
		timer.scheduleAtFixedRate(task,0, 91);
	}
	
    //NTPサーバーとの時差取得
    private static final String NTP_SERVER = "ntp.nict.jp";
	public void setOffset() {
		NTPUDPClient client = new NTPUDPClient();
        try {
            client.open();
            InetAddress host = InetAddress.getByName(NTP_SERVER);
            TimeInfo info = client.getTime(host);
            info.computeDetails();

            clockOffset = info.getOffset();
    		update.setForeground(new Color(160,180,160));
           
        } catch (Exception e) {
            clockOffset = (long) 0;//NTPサーバーとの接続不可
    		update.setForeground(new Color(200,70,70));
        } finally {
            client.close();
        }
	}
	
	//年月日取得
	public String Timestamp_yMd() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() + clockOffset);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(timestamp);
	}
	//時分秒取得
	public String Timestamp_Hms() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() + clockOffset);
        SimpleDateFormat sdf = new SimpleDateFormat("HH時mm分ss秒");
        return sdf.format(timestamp);
	}
}
