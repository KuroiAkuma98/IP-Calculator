import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class IpCalculator
{
    public static String ipToBin(String ip)
    {

    }
    public static void main(String[] args) throws UnknownHostException, SocketException
    {
        System.out.println("Wpisz adres komputera:");
        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();
        //String data = args[0];
        //System.out.println(data);

        // Ewentualne pobranie wlasnego adresu w przypadku gdy nie podano adresu
        if(data.isEmpty())
        {
            System.out.println("nie podano adresu, adres zostanie pobrany z uzywanego urzadzeina");
            InetAddress ipAdres = InetAddress.getLocalHost();
            System.out.println("twoj adres ip: " + ipAdres.getHostAddress());
            NetworkInterface Mask = NetworkInterface.getByInetAddress(ipAdres);
            System.out.println("twoja maska ip: " + Mask.getInterfaceAddresses().get(0).getNetworkPrefixLength());
            String ip_adres = ipAdres.getHostAddress().toString();
            int mask = Mask.getInterfaceAddresses().get(0).getNetworkPrefixLength();

        }
        else
        {
            Data your_data = new Data(data);
            if(your_data.isCorrect())
            {
                System.out.println("Brawo! Wpisales/as dobre dane");
            }
            else
            {
                System.out.println("Niepoprawne IP!");
            }
        }









    }
}
