import java.util.Scanner;


public class menu {

    /*atribut*/ 
    MATRIKS M1 = new MATRIKS();
    MATRIKS hasil = new MATRIKS();

    /*method*/

    public void start(){
        int ci; int cs;
        Scanner in = new Scanner (System.in);

        display : while(true){
            System.out.print("pilih, 1 input, 2 file, 3 random");
            ci = in.nextInt();
            switch (ci){
                case 1:
                    System.out.println("Untuk matriks spl :");
                    M1.inputmatriks();
                    System.out.println("Matriks hasilnya :");
                    hasil.inputmatriks(M1.row,1);
                    M1.printmatriks();
                    hasil.printmatriks();
                    break display;
                case 2:
                    M1.inputfromfile(hasil);
                    M1.printmatriks();hasil.printmatriks();
                    break display;
            }
        }

    }
    public static void main(String[] args){
        menu men = new menu();

        menu.start();
    }
}