import java.net.InetAddress;
import java.util.Scanner;

public class IpCalculator
{
    public static void main(String[] args)
    {
        System.out.println("Wpisz adres komputera:");
        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();

        // Ewentualne pobranie wlasnego adresu w przypadku gdy nie podano adresu
        if(data.isEmpty())
        {
            System.out.println("nie podano adresu, adres zostanie pobrany z uzywanego urzadzeina");
            InetAddress adres= InetAddress.getLocalHost();
            adres.getAddress();
        }



        Data your_data = new Data(data);
        if(your_data.isCorrect())
        {
            //String ip="",a="",b="",c="",d="",mask="";
            //String[] splitedArray = null;
            System.out.println("Brawo! Wpisales/as dobre dane");
        }
        else
        {
            System.out.println("Caly misterny plan w pizdu :<");
        }






    }
}
