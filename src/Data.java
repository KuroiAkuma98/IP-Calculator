public class Data
{
    String line;
    Data(String a)
    {
        line = a;
    }
    boolean isCorrect()
    {
        boolean flag = true;

        for(int i=0 ; i < this.line.length() ; i++ )
        {
            if(!(this.line.charAt(i) == '.' || this.line.charAt(i) == '/' || (this.line.charAt(i) >= '0' && this.line.charAt(i) <= '9')))
            {
                flag =  false;
                break;

            }
            if(this.line.charAt(i) == '.' && this.line.charAt(i+1) == '.' )
            {
                flag = false;
                break;
            }
            //System.out.println(i);
        }

        String ip="";
        int a,b,c,d,mask;

        String[] splitedArray1 = null;
        splitedArray1 = this.line.split("/");
        ip = splitedArray1[0];
        mask = Integer.parseInt(splitedArray1[1]);
        System.out.println("Twoje IP: " + ip + " twoja maska: " + mask);
        String[] splitedArray2;
        splitedArray2 = ip.split("\\.");
        a = Integer.parseInt(splitedArray2[0]);
        b = Integer.parseInt(splitedArray2[1]);
        c = Integer.parseInt(splitedArray2[2]);
        d = Integer.parseInt(splitedArray2[3]);

        System.out.println(a + " " + b + " " + c + " " + d );
        if(!((mask >=0 && mask <=32) && (a > 0 && a < 256) && (b >= 0 && b < 256) && (c >= 0 && c < 256) && (d >= 0 && d < 256)))
        {
            flag = false;
        }
        return flag;
    }
}
