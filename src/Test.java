
import Utils.OtherUtils.Serialization;
import Utils.ServerPart.DataBaseManager;
import javafx.scene.paint.Color;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Test {
    private static boolean UserLog = false;

    private static String username;
    private static String password;

    private final DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private static int port;
    static DataBaseManager dataBaseManager = new DataBaseManager();
    static Scanner scanner = new Scanner(System.in);

    public Test(DatagramChannel datagramChannel) throws IOException {
        this.datagramChannel = datagramChannel;
        datagramChannel.configureBlocking(false);
    }

    public static void main(String[] args) throws SQLException, IOException {

        DataBaseManager dataBaseManager = new DataBaseManager();
        dataBaseManager.updateCollectionFromDataBase();
        LinkedHashMap linkedHashMap;

        ByteBuffer byteBuffer = ByteBuffer.wrap(Objects.requireNonNull(Serialization.SerializeObject(dataBaseManager.getCollection())));

        linkedHashMap = (LinkedHashMap) Serialization.DeserializeObject(byteBuffer.array());

        System.out.println(linkedHashMap.toString());

    }


}