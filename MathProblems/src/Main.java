import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int maxim_number(int[] numbers){
        int max = numbers[0];
        for (int i : numbers) {
            max = Math.max(max, i);
        }
        return max;
    }

    static boolean is_prime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n%2 == 0) return false;
        for(int i = 3; i*i <= n; i+=2) {
            if (n%i == 0) return false;
        }
        return true;
    }

    static int[] first_1000_primes(){
        int[] primes = new int[1000];
        int number_of_primes_founded = 0;
        int number = 2;

        while(number_of_primes_founded < 1000){
            if(is_prime(number)){
                primes[number_of_primes_founded++] = number;
            }
            number++;
        }
        return primes;
    }

    static int[] primes = first_1000_primes();

    static int[] extract_numbers(String[] args) {
        ArrayList<Integer> numbersList = new ArrayList<>();

        for (String arg : args) {
            try {
                int number = Integer.parseInt(arg);
                if (number > 0) {
                    numbersList.add(number);
                }
            } catch (NumberFormatException e) {
                // Ignore the none numeric arguments
            }
        }
        return numbersList.stream().mapToInt(i -> i).toArray();
    }

    static int[] extract_numbers_from_string(String input) {
        // Dividir el input por espacios en blanco
        String[] args = input.split("\\s+");
        return extract_numbers(args);
    }

    static int LCM_function(int[] given_input_numbers){
        int LCM = 1;
        int max = maxim_number(given_input_numbers);
        int k = 0;
        boolean ok;

        while(max > 1){
            ok = false;
            int prime_that_divides = primes[k];
            for(int i = 0; i < given_input_numbers.length; i++){
                if(given_input_numbers[i]%prime_that_divides == 0){
                    ok = true;
                    given_input_numbers[i] = given_input_numbers[i]/prime_that_divides;
                }
            }

            if(!ok){
                k++;
            }
            else{
                LCM = LCM * prime_that_divides;
                if(max%prime_that_divides == 0){
                    max = max/prime_that_divides;
                }
            }
        }
        return LCM;
    }

    static void case_without_arguments(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Type:");
            String n = sc.nextLine();
            int[] numbers = extract_numbers_from_string(n);
            int LCM = LCM_function(numbers);
            System.out.println("Least Common Multiple is: " + LCM);
        }
        catch (Exception e){
            System.out.println("An exception occurred: " + e);
        }
    }

    static void case_with_arguments(String[] args){
        System.out.println("There are arguments");
        try{
            int[] numbers = extract_numbers(args);
            int LCM = LCM_function(numbers);
            System.out.println("Least Common Multiple is: " + LCM);
        } catch (Exception nfe) {
            System.out.println("An exeption " + nfe);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            case_without_arguments();
        } else {
            case_with_arguments(args);
        }
    }
}
