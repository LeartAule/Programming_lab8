import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.ServerClasses.*;
import Utils.ServerPart.ServerClasses.Commands.AbstractCommand;
import Utils.ServerPart.DataBaseManager;
import Utils.OtherUtils.ScannerThread;
import Utils.ServerPart.ServerHelper;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

/**
 * Класс сервер.
 * Осуществялет чтение xml файла, обработку поступающих команд от Клиент-стороны.
 *
 * author
 *
 */
public class Server implements Runnable {
    static Scanner scanner = new Scanner(System.in);
    private static ScannerThread scannerThread = new ScannerThread();
    CommandManager commandManager = new CommandManager();
    private DataBaseManager dataBaseManager;

    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private AbstractCommand command = null;

    private static int port = 50000;



    public Server(DataBaseManager dataBaseManager) throws TransformerException, ParserConfigurationException {
        this.dataBaseManager = dataBaseManager;
    }

    public static void main(String[] args){
        System.out.println("Сервер был запущен.");

        try {


            Server server = new Server(new DataBaseManager());
            //new Thread(scannerThread).start();
            new Thread(server).start();
        } catch (IllegalStateException e) {
            System.exit(-1);
        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            socketAddress = new InetSocketAddress(port);
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress(port));
            datagramChannel.configureBlocking(false);
            System.out.println("Порт Сервера " + port);
            while (true) {
                try {
                    receive();
                } catch (TransformerException | ParserConfigurationException | InvalidCountOfArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e){
            port++;
            run();
        } catch (ClosedChannelException e) {
            System.err.println("Что не так с каналом");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //В методе receive осуществялется вся обработка входящих команд
    private void receive() throws IOException, InvalidCountOfArgumentException, TransformerException, ParserConfigurationException {


        ByteBuffer byteBuffer = ByteBuffer.allocate(65536);
        byteBuffer.clear();
        socketAddress = datagramChannel.receive(byteBuffer);
        byteBuffer.flip();
        DatagramChannel d = datagramChannel;

        if (socketAddress != null && !new String(byteBuffer.array()).trim().isEmpty()) {
                ServerHelper serverHelper = new ServerHelper(d, socketAddress, dataBaseManager, byteBuffer);
                serverHelper.run();
            }
    }
}


