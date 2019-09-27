// package tubes;
// import tubes.MATRIKS;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class menu {

    /*atribut*/ 

    /* Konstruktor */
    public menu(){
    }

    /*method*/

    public void start(){
        MATRIKS M1,M2,H,Retu;
        M1 = new MATRIKS(); M2 = new MATRIKS();
        int cs,oc;
        Scanner in = new Scanner (System.in);
        int solveselect;
        display : while(true){
            System.out.println("___________________");
            System.out.println("|   WELCOME !!!!  |");
            System.out.println("|   MAIN MENU :   |");
            System.out.println("===================");
            System.out.println("|   1.SPL         |");
            System.out.println("|   2.Determinan  |");
            System.out.println("|   3.Inverse     |");
            System.out.println("|   4.Kofaktor    |");
            System.out.println("|   5.Adjoin      |");
            System.out.println("|   6.Interpolasi |");
            System.out.println("|   7.OUT         |");
            System.out.println("===================");
            

            cs = in.nextInt();
            switch (cs){
                case 1:
                this.inputmenu(true,M1);
                System.out.println("***************");
                System.out.println("Pilih metode :");
                System.out.println("1. Gauss");
                System.out.println("2. GaussJordan");
                System.out.println("3. Inverse");
                System.out.println("4. Cramer");
                System.out.println("***************");
                solveselect = in.nextInt();
                System.out.println("1. output di layar");
                System.out.println("2. output di file");
                oc = in.nextInt();
                    switch (solveselect){
                        case 1:
                        switch (oc){
                            case 1:
                                System.out.println("HASIL : ");
                                (M1.solveSPLGauss()).printmatriks();
                                break display;
                            
                            case 2:
                                (M1.solveSPLGauss()).savetofile();
                                break display;
                        }
                        break display;
                        case 2:
                        switch (oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M1.solveSPLGJ()).printmatriks();
                            break display;
                            case 2:
                                (M1.solveSPLGJ()).savetofile();
                                break display;
                        }   
                        break display;
                        case 3:
                        switch(oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M1.splinverse()).printmatriks();
                            break display;
                            case 2:
                                (M1.splinverse()).savetofile();
                                break display;
                        }
                        case 4:
                        switch (oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M1.cramerssplsolve()).printmatriks();

                            break display;
                            case 2:
                                (M1.cramerssplsolve()).savetofile();
                                break display;
                        }
                        break display;
                        }
                break display;
                case 2:
                this.inputmenu(false,M2);
                System.out.println("1. Gauss, 2.Cofactor expansion");
                solveselect = in.nextInt();
                    switch (solveselect){
                        case 1:
                        System.out.println(M2.determinant());
                        break display;
                        case 2:
                        System.out.println(M2.determinantcofactorexpansion());
                        break display;
                    }
                    break display;
                case 3:
                this.inputmenu(false,M2);
                System.out.println("1. GaussJordan(Identitas)");
                System.out.println("2. Kofaktor");
                solveselect = in.nextInt();
                System.out.println("1. output di layar");
                System.out.println("2. output di file");
                oc = in.nextInt();
                    switch (solveselect){
                        case 2:
                        switch (oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M2.inversebycofactor()).printmatriks();
                            break display;
                            case 2:
                                (M2.inversebycofactor()).savetofile();
                                break display;
                        }
                        break display;
                        case 1:
                        switch (oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M2.inverse()).printmatriks();
                            break display;
                            case 2:
                                (M2.inverse()).savetofile();
                                break display;
                        }
                        
                    }
                    break display;
                case 4:
                this.inputmenu(false,M2);
                System.out.println("1. output di layar");
                System.out.println("2. output di file");
                oc = in.nextInt();
                switch (oc){
                    case 1:
                    System.out.println("HASIL : ");
                                (M2.cofactormatrix()).printmatriks();
                                break display;
                    case 2:
                    (M2.cofactormatrix()).savetofile();
                    break display;
                }break display;
                case 5:
                this.inputmenu(false,M2);
                System.out.println("1. output di layar");
                System.out.println("2. output di file");
                oc = in.nextInt();
                switch (oc){
                    case 1:
                    System.out.println("HASIL : ");
                                (M2.adjoint()).printmatriks();
                                break display;
                    case 2:
                    (M2.adjoint()).savetofile();
                    break display;
                }break display;
                
                case 6:
                // System.out.println("Masukkan dari? 1. keyboard, (1+1). file");
                System.out.println("jumlah data : ");
                int n = in.nextInt();
                MATRIKS di = new MATRIKS(2,n);
                for (int i = 1; i <= n; i++){
                    System.out.print("Masukkan X" + i + " : ");di.matrix[1][i] = in.nextDouble();System.out.println();
                    System.out.print("Masukkan P(X" + i + ") : ");di.matrix[2][i] = in.nextDouble();System.out.println();
                }
                System.out.println("Masukkan titik xi untuk diinterpolasikan P(xi)nya : ");
                int xi = in.nextInt();
                M2.interpolate(di,xi);
                break display;
                case 7:
                    break display;
                case 8:
                    inputmenu(false,M2);
                    
                
            }
        }
        System.out.println("FINISH");
    }

    public void inputmenu(boolean aug,MATRIKS M){
        System.out.println("********************");
        System.out.println("input matriks anda :");
        System.out.println("1.keyboard");
        System.out.println("2.file");
        System.out.println("********************");
        Scanner in = new Scanner(System.in);
        
        if (aug){    
            int ci = in.nextInt();
           display1 : switch (ci){
                case 1:
                    System.out.println("Untuk matriks spl :");
                    M.inputmatriks();
                    break display1;
                case 2:
                    M.inputfromfile();
                    break display1;
                default:
                    System.out.println("");
                    // break display;
            }
        }
        else{
            int ci = in.nextInt();
            display2 :switch (ci){
                case 1:
                    System.out.println("Untuk matriks :");
                    System.out.print("N : ");
                    int n = in.nextInt();
                    System.out.println();
                    M.inputmatriks(n,n);
                    // this.M2 = this.M1.augment(this.hasil);
                    break display2;
                case 2:
                    M.inputfromfile();
                    break display2;
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