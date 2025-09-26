import java.util.Scanner;
public class Demo {
    public static void main(String[] args) {
//        int n = 7653;
//        int n = 962;
        int n = 58987;
        int sum1 = 0 , sum2 = 0 ;
        int count = 0;
        int digit = n ;

        while( n > 0 ){
            count++;
            int rem = n % 10;

            boolean one = count%2 == 0;
            boolean two = (digit %2 == 0 && count %2 == 0);
            boolean three = (digit %2 != 0 && count %2 == 0);

            if( one || two || three  ){ // || - or => one true naalum entire true agirum
                sum1 += rem;
            }else{
                sum2 += rem;
            }

            n /= 10;
        }
        System.out.println("Odd sum: "+ sum2);

        System.out.println("Even sum: "+ sum1);
    }
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        int oddSum = 0 , evenSum = 0;
//        int count = 0;
//        for(int i = 0 ; i < str.length() ; i++){
//            char ch = str.charAt(i);
//            int digit = ch - '0';
//            if(count % 2 == 0){
//                evenSum += digit;
//            }else{
//                oddSum += digit;
//            }
//            count++;
//        }
//        System.out.println("Sum of Odd pos :"+ oddSum);
//        System.out.println("Sum of Even pos :"+ evenSum);
//    }
}
