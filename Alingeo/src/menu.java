import java.util.Scanner;
import java.io.*;
import java.util.*;

public class menu {

    /*atribut*/ 
    MATRIKS M1 = new MATRIKS();
    MATRIKS M2;
    MATRIKS hasil = new MATRIKS();

    /* Konstruktor */
    public menu(){
    }

    /*method*/

    public void start(){
        int cs,oc;
        Scanner in = new Scanner (System.in);
        int solveselect;
        display : while(true){
            System.out.println("1. SPL, 2. Determinan, 3. Inverse, 4. Kofaktor, 5. Adjoin, 6. Interpolasi, 7. OUT");
            cs = in.nextInt();
            switch (cs){
                case 1:
                this.inputmenu(true);
                System.out.println("1. Gauss, 2. Gaussjordan, 3.Inverse, 4. Cramer");
                solveselect = in.nextInt();
                System.out.println("1. output di layar, 2. output di file");
                oc = in.nextInt();
                    switch (solveselect){
                        case 1:
                        switch (oc){
                            case 1:
                                (this.M2.solveSPLGauss()).printmatriks();
                            case 2:
                                (this.M2.solveSPLGauss()).savetofile();
                        }
                        case 4:
                        switch (oc){
                            case 1:
                                (this.M2.cramerssplsolve()).printmatriks();
                            case 2:
                                (this.M2.cramerssplsolve()).savetofile();
                        }
                        }
                break display;
                case 2:
                this.inputmenu(false);
                System.out.println("1. Gauss");
                solveselect = in.nextInt();
                    switch (solveselect){
                        case 1:
                        System.out.println(this.M2.determinant());
                        }
                        break display;
                case 3:
                this.inputmenu(false);
                System.out.println("1. GaussJordan(Identitas), 2. Kofaktor");
                solveselect = in.nextInt();
                System.out.println("1. output di layar, 2. output di file");
                oc = in.nextInt();
                    switch (solveselect){
                        case 2:
                        switch (oc){
                            case 1:
                                (this.M2.inversebycofactor()).printmatriks();
                            case 2:
                                (this.M2.inversebycofactor()).savetofile();
                        }
                        break display;
                        case 1:
                        switch (oc){
                            case 1:
                                (this.M2.inverse()).printmatriks();
                            case 2:
                                (this.M2.inverse()).savetofile();
                        }
                        
                    }
                    break display;
                case 4:
                this.inputmenu(false);
                System.out.println("1. output di layar, 2. output di file");
                oc = in.nextInt();
                switch (oc){
                    case 1:
                    (this.M2.cofactormatrix()).printmatriks();
                    case 2:
                    (this.M2.cofactormatrix()).savetofile();
                }break display;
                case 5:
                this.inputmenu(false);
                System.out.println("1. output di layar, 2. output di file");
                oc = in.nextInt();
                switch (oc){
                    case 1:
                    (this.M2.adjoint()).printmatriks();
                    case 2:
                    (this.M2.adjoint()).savetofile();
                }break display;
                
                case 6:
                System.out.println("Masukkan dari? 1. keyboard, (1+1). file");
                int n = in.nextInt();
                MATRIKS di = new MATRIKS(2,n);
                for (int i = 1; i <= n; i++){
                    System.out.print("Masukkan X" + i + " : ");di.matrix[1][i] = in.nextDouble();System.out.println();
                    System.out.print("Masukkan P(X" + i + ") : ");di.matrix[2][i] = in.nextDouble();System.out.println();
                }
                System.out.println("Masukkan titik xi untuk diinterpolasikan P(xi)nya : ");
                int xi = in.nextInt();
                this.M2.interpolate(di,xi);
                break display;
                case 7:
                    break display;
                
            }
        }
    }

    public void inputmenu(boolean aug){
        System.out.print("input matriks anda, 1 keyboard, 2 file, 3 random");
        Scanner in = new Scanner(System.in);
        if (aug){    
            int ci = in.nextInt();
            switch (ci){
                case 1:
                    System.out.println("Untuk matriks spl :");
                    this.M1.inputmatriks();
                    System.out.println("Matriks hasilnya :");
                    this.hasil.inputmatriks(M1.row,1);
                    this.M2 = (this.M1).augment(this.hasil);
                    // break display;
                case 2:
                    this.M1.inputfromfile(this.hasil);
                    this.M2 = (this.M1).augment(this.hasil);
                    // break display;
                default:
                    System.out.println("");
                    // break display;
            }
        }
        else{
            int ci = in.nextInt();
            switch (ci){
                case 1:
                    System.out.println("Untuk matriks :");
                    System.out.print("N : ");
                    int n = in.nextInt(); System.out.println();
                    this.M2.inputmatriks(n,n);
                    // this.M2 = this.M1.augment(this.hasil);
                    // break display;
                case 2:
                    this.M2.inputfromfile();
                    // break display;
                default:
                    System.out.println("");
                    // break display;
            }
        }
    }

    public static void main(String[] args){
        menu men = new menu();

        men.start();
    
    }

}