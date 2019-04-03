import java.util.Scanner;

public class IpCalculator
{
    public static void main(String[] args)
    {
        System.out.println("Wpisz adres komputera:");
        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();
        if(data == "")
        {
            System.out.println("nie podano adresu, adres zostanie pobrany z uzywanego urzadzeina");
            
        }
        //System.out.println(data);

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
