package time;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;

public class test {

    /**
     * NTPサーバー
     * <p>
     * 独立行政法人情報通信研究機構 (NICT)<br>
     * http://jjy.nict.go.jp/tsp/PubNtp/index.html
     * </p>
     */
    private static final String NTP_SERVER = "ntp.nict.jp";

    public static void main(String[] args) {
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