public class Compulsory {
   public static void main(String args[])
   {  System.out.println("Hello World!");
      String[] languages={"C","C++","C#","Python","Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
      for (int i=0; i<languages.length ; i++)
      {
         System.out.println(languages[i]);
      }
    int n = (int) (Math.random() * 1000000);
      //int n=64;
      n*=3;
      n+=0b10101;
      n+=0xFF;
      n*=6;
      System.out.println(n);
      int sum=0,result=0;
      while (n>0)
      {
          result=result+n%10;
         n=n/10;
      }
      System.out.println(result);
      while(result>10) {
         while (result > 0) {
            sum = sum + result % 10;
            result= result / 10;
         }
          result=sum;
      }
      System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
   }
}
