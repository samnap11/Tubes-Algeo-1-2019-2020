// import java.io; //File Handling belum butuh
import java.util.*;
import java.io.*;

public class MATRIKS{

    //Attributes
    int row,col;
    double [][] matrix;
    
    //Konstruktor
    public void inputmatriks() {

		Scanner scan = new Scanner(System.in);

		System.out.print("Masukkan baris matriks : "); 
		int row = scan.nextInt(); //input baris
		System.out.print("Masukkan kolom matriks : ");
		int col = scan.nextInt(); //input kolom

		this.row = row;
		this.col = col;
		this.matrix = new double[this.row+1][this.col+1];

		/* isi matriks */
		for(int i=1;i<=this.row;i++){
			for(int j=1;j<=this.col;j++){
				System.out.print("Input matrix[" + (i) + "][" + (j) + "] : ");
				this.matrix[i][j] = scan.nextDouble();
			}
        }


    }

    public void inputmatriks(int row, int col) {

        Scanner scan = new Scanner(System.in);

		this.row = row;
		this.col = col;
		this.matrix = new double[this.row+1][this.col+1];

		/* isi matriks */
		for(int i=1;i<=this.row;i++){
			for(int j=1;j<=this.col;j++){
				System.out.print("Input matrix[" + (i) + "][" + (j) + "] : ");
				this.matrix[i][j] = scan.nextDouble();
			}
        }
    }

    public void inputfromfile(MATRIKS hasil){  //
        
        // File inp = new File("MATRIKS.txt");
        FileReader reade;
        String data;
        String temp ="";
        // int colnow,rownow;
        // colnow = 1; rownow = 1;
        try{
            reade = new FileReader("MATRIKS.txt");
            data = String.valueOf(reade.read());
                while (!(Integer.valueOf(data) == -1)){
                if (data == "\n"){
                    this.matrix[this.row][this.col] = Double.valueOf(temp);
                    this.row += 1;
                    this.col = 1;
                    temp ="";
                    data = String.valueOf(reade.read());
                }
                else if (data == " "){
                    this.matrix[this.row][this.col] = Double.valueOf(temp);
                    this.col += 1;
                    temp = "";
                    data = String.valueOf(reade.read());
                }
                else{
                    temp += data;
                    data = String.valueOf(reade.read());
                }
            }

            for (int i = 1; i <= this.row;i++){
                hasil.matrix[i][1] = this.matrix[i][this.col];
            }
            this.col -= 1;
    
        }
        catch (FileNotFoundException e){
            System.err.println(e.toString());
        }
        catch (IOException e){
            System.err.println(e.toString());
        }
    }
    public MATRIKS(int rows, int cols){
        this.row = rows;
        this.col = cols;            //MakeEmpty yang tadi sama persis ama ini wkwk
        this.matrix = new double[this.row+1][this.col+1];
    }                               //Konstruktor namanya samain aja ama classnya, dan bisa 3 gini buat override.

    public MATRIKS(MATRIKS A){
        this.row = A.row;
        this.col = A.col;
        this.matrix = A.matrix;
    }
    public MATRIKS(){
        this.row = 1;
        this.col = 1;
    }

    //Selector
    public double getElmt(int row, int col){
        return this.matrix[row+1][col+1];
    }


    //METHODDDDDDDDDDDDDDDDDDDD
    public void printmatriks(){
        for(int i = 1; i <=this.row;i++){
            for(int j = 1; j<=this.col;j++){
                System.out.print(String.format("%.15f ",getElmt(i,j)));
            }
            System.out.println("");
        }
    }

    public void swap(int i, int j){
        double[] temp;
        temp = this.matrix[i];
        this.matrix[i] = this.matrix[j];
        this.matrix[j] = temp;
    }

    public boolean eqsize(MATRIKS M){
        return (this.row == M.row && this.col == M.col); 
    }   

    public boolean eq(MATRIKS M){
        if (!eqsize(M)){ 
            return false;
        }
        else{
            for(int i = 1;i<=this.row;i++){
                for(int j = 1; j <= this.col;j++){
                    if(!(this.getElmt(i,j)== M.getElmt(i,j))){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public void SetElmt(double x,int i,int j){
        this.matrix[i][j] = x;
    }

    public boolean bujur(){
        return this.row == this.col;
    }

    // public void swaprows(int i, int j){

    // }

    // public MATRIKS inverse(){

    // }

    public MATRIKS transpose(){
        MATRIKS a = new MATRIKS(this.row,this.col);
        for(int i = 1; i <= this.row;i++){
            for(int j = 1;j<= this.col;j++){
                a.SetElmt(getElmt(i,j),j,i);
            }
        }
        return a;
    }

    //gaussian
    // public GaussElim(MATRIKS res){
    //     double divider;
    //     boolean zeroall;

    //     //PIVOTING

        
    // }
    
    public MATRIKS inverse(){
        MATRIKS I = new MATRIKS(this.row,this.col);
        for (int i = 1; i <= this.row;i ++){
            for (int j = 1; j<= this.col;j++){
                I.matrix[i][j] = 0;
                if (i == j){            //MATRIKS IDENTITAS
                    I.matrix[i][j] = 1;
                }
            }
        }

        if (this.bujur){
            for (int l =1; l <=this.row;l++){    
                double divider = (1 / this.matrix[l][l]);
                for (int k = l; k <= this.col;k++){             //dividing
                    this.matrix[l][k] *= divider;
                    I.matrix[l][k] *= divider;    
                }
                for (int m = 1; m <= this.row; m++){
                    if ((m != l) && (this.matrix[l][m] != 0)){
                        double substractor = this.matrix[l][m];
                        for (int n = 1; n <= this.col;n++){
                            this.matrix[m][n] -= substractor * this.matrix[l][n]; 
                            I.matrix[m][n] -= substractor * I.matrix[l][n];
                        }
                    }
                }
            }
            return I;
        }
        else{
            return this;
            System.out.println("ilegal dimension, no inverse");
        }
    }

    //fungsi penghilangan suatu rowcol untuk kofaktor
    public MATRIKS removerc(int row, int col){
        MATRIKS res = new MATRIKS(this.row-1,this.col-1);

        int i = 1, j = 1;
        for (int l = 1;l<= this.row; l++) {
            if (l != row){
                for (int k = 1;k<=this.col;k++){
                    if(k != col){
                        res.matrix[i][j] = this.matrix[l][k];
                        j++;
                        if (j > this.row) {
                            j % res.row; i++;
                        } 
                    }
                }
            }
        }
    }

    public MATRIKS cofactor(){
        MATRIKS cof = new MATRIKS(this.row,this.col);

        for (int i = 1; i <= this.row;i++){
            for (int j = 1; j <= this.col; j++){
                cof.matrix[i][j] = this.removerc(i,j).determinant();
            }
        }
        return cof;
    }

    //segitiga atas
    public MATRIKS uppertri(){
        int col = this.col;
        int row = this.row;
    
        for (int i = 1; i <= row ; i++){
            for (int j = i+1; j <= col ; j++){
                double scaler = this.matrix[j][i] / this.matrix[i][i];

                for (int k = 1; k <= this.row; k++){
                    this.matrix[j][k] -= scaler * this.matrix[i][k];
                }
            }
        }
        return res;
    }

    public double determinant(){
        if (this.bujur){
            MATRIKS a = this.uppertri();
            int det = 1
            for (int i = 1; i <= a.row; a++){
                det *= a.matrix[i][i];
            }
            return det;
        }
        else return 0;
    }


    // //gaussjordan
    // public solve2(){

    // }

    // //Cramer method
    // public solve3(){

    // }

    // //kofaktor
    // public solve4(){

    // }


















}