package com.iquestgroup.remotelearning.week1.p2.Alexandra;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("No argument passed");
        }

        try {
            int num = Integer.valueOf(args[0]);
            allNumbersAddMemory(num);
            System.out.println("###############");
            allNumbers(num);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input, cannot convert");
        }
    }

    static void allNumbersAddMemory(int n) {

        if (n < 1) System.out.println("Invalid input");
        else {
            boolean prime[] = new boolean[n + 1];

            for (int i = 0; i < n; i++)
                prime[i] = true;

            prime[1] = false;
            for (int aux = 2; aux * aux <= n; aux++) {
                if (prime[aux] == true) {
                    for (int i = aux * aux; i <= n; i += aux)
                        prime[i] = false;
                }
            }

            System.out.print("( ");
            for (int i = 1; i <= n; i++) {
                if (i == n) {
                    if (prime[i] == true)
                        System.out.println(i + "-PRIME )");
                    else
                        System.out.println(i + ")");
                } else {
                    if (prime[i] == true) {
                        System.out.print(i + "-PRIME, ");
                    } else
                        System.out.print(i + ", ");
                }
            }
        }
    }

    static int isPrime(int nr) {

        if (nr == 1) return 0;
        for (int div = 2; div * div <= nr; div++)
            if (nr % div == 0) return 0;
        return 1;
    }

    static void allNumbers(int n) {

        if (n < 1) System.out.println("Invalid input");
        else {
            if (n == 1) {
                System.out.println("(1)");
            } else {
                System.out.print("( ");
                for (int i = 1; i <= n; i++) {

                    int ok = isPrime(i);
                    if (i == n) {
                        if (ok == 1)
                            System.out.println(i + "-PRIME )");
                        else
                            System.out.println(i + ")");
                    } else {
                        if (ok == 1)
                            System.out.print(i + "-PRIME, ");
                        else
                            System.out.print(i + ", ");
                    }
                }
            }
        }
    }
}
