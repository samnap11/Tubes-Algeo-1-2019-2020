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
            System.out.println("1. SPL, 2. Determinan, 3. Inverse, 4. Kofaktor, 5. Adjoin, 6. Interpolasi, 7. OUT");
            cs = in.nextInt();
            switch (cs){
                case 1:
                this.inputmenu(true,M1);
                System.out.println("1. Gauss, 2. Gaussjordan, 3.Inverse, 4. Cramer");
                solveselect = in.nextInt();
                System.out.println("1. output di layar, 2. output di file");
                oc = in.nextInt();
                    switch (solveselect){
                        case 1:
                        switch (oc){
                            case 1:
                                System.out.println("HASIL : ");
                                (M1.solveSPLGauss()).printmatriks();
                            
                            case 2:
                                (M1.solveSPLGauss()).savetofile();
                        }
                        break display;
                        case 2:
                        switch (oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M1.solveSPLGJ()).printmatriks();
                            case 2:
                                (M1.solveSPLGJ()).savetofile();
                        }   
                        break display;
                        case 4:
                        switch (oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M1.cramerssplsolve()).printmatriks();
                            case 2:
                                (M1.cramerssplsolve()).savetofile();
                        }
                        break display;
                        }
                break display;
                case 2:
                
                this.inputmenu(false,M2);
                System.out.println("1. Gauss");
                solveselect = in.nextInt();
                    switch (solveselect){
                        case 1:
                        System.out.println(M2.determinant());
                        }
                        break display;
                case 3:
                this.inputmenu(false,M2);
                System.out.println("1. GaussJordan(Identitas), 2. Kofaktor");
                solveselect = in.nextInt();
                System.out.println("1. output di layar, 2. output di file");
                oc = in.nextInt();
                    switch (solveselect){
                        case 2:
                        switch (oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M2.inversebycofactor()).printmatriks();
                            case 2:
                                (M2.inversebycofactor()).savetofile();
                        }
                        break display;
                        case 1:
                        switch (oc){
                            case 1:
                            System.out.println("HASIL : ");
                            (M2.inverse()).printmatriks();
                            case 2:
                                (M2.inverse()).savetofile();
                        }
                        
                    }
                    break display;
                case 4:
                this.inputmenu(false,M2);
                System.out.println("1. output di layar, 2. output di file");
                oc = in.nextInt();
                switch (oc){
                    case 1:
                    System.out.println("HASIL : ");
                                (M2.cofactormatrix()).printmatriks();
                    case 2:
                    (M2.cofactormatrix()).savetofile();
                }break display;
                case 5:
                this.inputmenu(false,M2);
                System.out.println("1. output di layar, 2. output di file");
                oc = in.nextInt();
                switch (oc){
                    case 1:
                    System.out.println("HASIL : ");
                                (M2.adjoint()).printmatriks();
                    case 2:
                    (M2.adjoint()).savetofile();
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
        System.out.println("input matriks anda, 1 keyboard, 2 file");
        Scanner in = new Scanner(System.in);
        if (aug){    
            int ci = in.nextInt();
            switch (ci){
                case 1:
                    System.out.println("Untuk matriks spl :");
                    M.inputmatriks();
                    // break display;
                case 2:
                    M.inputfromfile();
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
                    int n = in.nextInt();
                    System.out.println();
                    M.inputmatriks(n,n);
                    // this.M2 = this.M1.augment(this.hasil);
                    // break display;
                case 2:
                    M.inputfromfile();
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