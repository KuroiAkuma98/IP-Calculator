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
     }

     int temp = this.line.indexOf(".");
     int tmp;
     while(temp != this.line.lastIndexOf("."))
     {
         if(temp+1 == this.line.indexOf(".",temp))
         {
             flag = false;
             break;
         }
         else
             {
             tmp = temp;
             temp = this.line.indexOf(".", tmp);
         }
     }

     return flag;
 }
}
