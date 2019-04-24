import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.lang.Math;


public class IpCalculator
{
    public static String maskToBin(int mask)
    {
        String tmp ="";
        for (int i = 0; i < 32 ; i++)
        {
            if(i % 8 == 0 && i != 0)tmp += ".";
            if(i < mask)
            {
                tmp += "1";
            }
            else tmp += 0;
        }
        return tmp;
    }
    public static String maskToDec(int mask)
    {
        String binMask = maskToBin(mask);
        String[] splitedArray = null;
        splitedArray = binMask.split("\\.");
        String a,b,c,d;
        a = Integer.toString(binToDec(splitedArray[0]));
        b = Integer.toString(binToDec(splitedArray[1]));
        c = Integer.toString(binToDec(splitedArray[2]));
        d = Integer.toString(binToDec(splitedArray[3]));
        String finalMask = a + "." + b + "."+ c + "." + d;
        return finalMask;
        //System.out.println("decimal mask: " + a + "." + b + "."+ c + "." + d);
    }

    public static String ipToBin(String ip) throws UnknownHostException
    {
        byte[] bytes = InetAddress.getByName(ip).getAddress();
        String data_out = new BigInteger(1, bytes).toString(2);
        String tmp = "";
        for(int i = 0; i < 32; i++ )
        {
            if(i % 8 == 0 && i != 0)tmp += ".";
            tmp += data_out.charAt(i);
        }

        return tmp;
    }

    public static String netAdress(String ip,int mask)throws UnknownHostException
    {
        String adress = "";
        String binIP = ipToBin(ip);
        String binMask = maskToBin(mask);
        for(int i = 0; i < 35; i++)
        {
            //if(i % 8 == 0 && i != 0)adress += ".";
            if(binIP.charAt(i) == '.') adress += ".";
            else
            {
                if(binIP.charAt(i) == '1' && binMask.charAt(i) == '1')
                {
                    adress += "1";
                }
                else adress += "0";
            }
        }
        return adress;
    }

    public static int binToDec(String bin)
    {
        int pot;
        int result = 0;
        //System.out.println(bin);
        int p = bin.length();
        for(int i = 0;i < bin.length();i++)
        {
            p--;
            if(bin.charAt(i) == '1')
            {

                //System.out.println(p);
                result += Math.pow(2,p);
                //System.out.println(Math.pow(2,p));
                //System.out.println(result);
            }
        }
        return result;
    }

    public static String adressToDecimal(String adress, int mask)throws UnknownHostException
    {
        String tmp = netAdress(adress,mask);
        //System.out.println(tmp);
        String a,b,c,d;

        String[] splitedArray = null;
        splitedArray = tmp.split("\\.");
        //System.out.println(splitedArray[0] + " " + splitedArray[1] +" " + splitedArray[2] + " " + splitedArray[3] );
        a = Integer.toString(binToDec(splitedArray[0]));
        b = Integer.toString(binToDec(splitedArray[1]));
        c = Integer.toString(binToDec(splitedArray[2]));
        d = Integer.toString(binToDec(splitedArray[3]));

        String stringMask = Integer.toString(mask);
        String decAdress = a + "." + b + "." + c + "." + d + "/" + stringMask;
        return decAdress;
    }

    public static String classAdress(String ip,int mask)throws UnknownHostException
    {
        String tmp = netAdress(ip,mask);
        String a;

        String[] splitedArray = null;
        splitedArray = tmp.split("\\.");
        a = splitedArray[0];

        if(a.charAt(0) == '0') return "A";
        if(a.charAt(0) == '1' && a.charAt(1) == '0') return "B";
        if(a.charAt(0) == '1' && a.charAt(1) == '1' && a.charAt(2) == '0') return "C";
        if(a.charAt(0) == '1' && a.charAt(1) == '1' && a.charAt(2) == '1' && a.charAt(3) == '0') return "D";
        if(a.charAt(0) == '1' && a.charAt(1) == '1' && a.charAt(2) == '1' && a.charAt(3) == '1') return "E";
        return "Unknown class";
    }

    public static String publicOrPrivate(String ip, int mask)throws UnknownHostException
    {
        String tmp = netAdress(ip,mask);
        String a,b;

        String[] splitedArray = null;
        splitedArray = tmp.split("\\.");
        a = splitedArray[0];
        b = splitedArray[1];
        if(classAdress(ip,mask) == "A" && Integer.valueOf(a) == 10) return "public";
        if(classAdress(ip,mask) == "B" && Integer.valueOf(a) == 172 && (Integer.valueOf(b) >=16 && Integer.valueOf(b) <= 31) ) return "public";
        if(classAdress(ip,mask) == "C" && Integer.valueOf(a) == 192 && Integer.valueOf(b) == 168 ) return "public";
        return "private";
    }

    public static int amountOfAdress(int mask)
    {
        int tmp = 32 - mask;
        return (int)(Math.pow(2,tmp)- 2);
    }

    public static String broadcastBin(String ip,int mask)throws UnknownHostException
    {
        String binMask = maskToBin(mask);
        String tmpMask = "";
        for(int i = 0; i < binMask.length(); i++)
        {
            if(binMask.charAt(i) == '1')
            {
                tmpMask += "0";
            }
            if(binMask.charAt(i) == '0')
            {
                tmpMask += "1";
            }
            if(binMask.charAt(i) == '.')
            {
                tmpMask += ".";
            }
        }
        String bc = "";
        String tmpIP = ipToBin(ip);
        for(int j = 0; j < binMask.length(); j++)
        {
            if(tmpMask.charAt(j) == '1' || tmpIP.charAt(j) == '1')
            {
                bc += "1";
            }
            if(tmpIP.charAt(j) == '.')
            {
                bc += ".";
            }
            if(tmpMask.charAt(j) == '0' && tmpIP.charAt(j) == '0')
            {
                bc += "0";
            }
        }
        return bc;
        //System.out.println("E1:\t binary broadcast: " + bc);
    }
    public static String broadcastDec(String ip,int mask)throws UnknownHostException
    {
        String broadcast = broadcastBin(ip,mask);
        String[] splitedArray = null;
        splitedArray = broadcast.split("\\.");
        String a,b,c,d;
        a = Integer.toString(binToDec(splitedArray[0]));
        b = Integer.toString(binToDec(splitedArray[1]));
        c = Integer.toString(binToDec(splitedArray[2]));
        d = Integer.toString(binToDec(splitedArray[3]));
        String finalBc = a + "." + b + "." + c + "." + d;
        return  finalBc;
    }

    public static String firstAdressBin(String ip,int mask)throws UnknownHostException
    {
        String adress = "";
        String binIP = ipToBin(ip);
        String binMask = maskToBin(mask);
        for(int i = 0; i < 35; i++)
        {
            if(binIP.charAt(i) == '.') adress += ".";
            else
            {
                if(binIP.charAt(i) == '1' && binMask.charAt(i) == '1')
                {
                    adress += "1";
                }
                else adress += "0";
            }
        }
        String[] splitedArray = null;
        splitedArray = adress.split("\\.");
        String a,b,c,d;
        a = splitedArray[0];
        b = splitedArray[1];
        c = splitedArray[2];
        d = "00000001";
        String finalAdress = a + "." + b + "." + c + "." + d;
        return finalAdress;
    }

    public static String firstAdressDec(String ip,int mask) throws UnknownHostException
    {
        String binIP = firstAdressBin(ip,mask);
        String[] splitedArray1 = null;
        splitedArray1 = binIP.split("\\.");
        String a,b,c,d;
        a = Integer.toString(binToDec(splitedArray1[0]));
        b = Integer.toString(binToDec(splitedArray1[1]));
        c = Integer.toString(binToDec(splitedArray1[2]));
        d = "1";
        String finalIP = a + "." + b + "." + c + "." + d;
        return finalIP;
    }

    public static String lastAdressBin(String ip,int mask)throws UnknownHostException
    {
        String adress = "";
        String binIP = ipToBin(ip);
        String binMask = maskToBin(mask);
        for(int i = 0; i < 35; i++)
        {
            if(binIP.charAt(i) == '.') adress += ".";
            else
            {
                if(binIP.charAt(i) == '1' && binMask.charAt(i) == '1')
                {
                    adress += "1";
                }
                else adress += "0";
            }
        }
        String[] splitedArray = null;
        splitedArray = adress.split("\\.");
        String a,b,c,d;
        a = splitedArray[0];
        b = splitedArray[1];
        c = "11111111";
        d = "11111110";
        String finalAdress = a + "." + b + "." + c + "." + d;
        return finalAdress;
    }

    public static String lastAdressDec(String ip,int mask) throws UnknownHostException
    {
        String binIP = firstAdressBin(ip,mask);
        String[] splitedArray1 = null;
        splitedArray1 = binIP.split("\\.");
        String a,b,c,d;
        a = Integer.toString(binToDec(splitedArray1[0]));
        b = Integer.toString(binToDec(splitedArray1[1]));
        c = "255";
        d = "254";
        String finalIP = a + "." + b + "." + c + "." + d;
        return finalIP;
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
            //System.out.println("maska binarnie: " + maskToBin(mask));
            //System.out.println("ip binarnie: " + ipToBin(ip_adres));
            //System.out.println("adres binarnie: " + netAdress(ip_adres,mask));
            System.out.println("A:\t adress: " + adressToDecimal(ip_adres,mask));
            System.out.println("B:\t adress class: " + classAdress(ip_adres,mask));
            System.out.println("C:\t adress type: " + publicOrPrivate(ip_adres,mask));
            System.out.println("D1:\t bin mask: " + maskToBin(mask));
            System.out.println("D2:\t decimal mask: " + maskToDec(mask));
            System.out.println("E1:\t binary broadcast: " + broadcastBin(ip_adres,mask));
            System.out.println("E2:\t dec broadcast: " + broadcastDec(ip_adres,mask));
            System.out.println("F1:\t binary first adress: " + firstAdressBin(ip_adres,mask));
            System.out.println("F2:\t dec first adress: " + firstAdressDec(ip_adres,mask));
            System.out.println("G1:\t binary last adress: " + lastAdressBin(ip_adres,mask));
            System.out.println("G2:\t dec last adress: " + lastAdressDec(ip_adres,mask));
            System.out.println("H:\t amount of hosts: " + amountOfAdress(mask));
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
