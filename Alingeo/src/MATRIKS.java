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

        this.inputmatriks(row,col);
        
/*		this.row = row;
		this.col = col;
		this.matrix = new double[this.row+1][this.col+1];

		 isi matriks 
		for(int i=1;i<=this.row;i++){
			for(int j=1;j<=this.col;j++){
				System.out.print("Input matrix[" + (i) + "][" + (j) + "] : ");
				this.matrix[i][j] = scan.nextDouble();
			}
        }
        scan.close(); */

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
        scan.close();
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
    
    public MATRIKS kalimatriks(MATRIKS M2){
        MATRIKS result = new MATRIKS(this.row,M2.col);

        for (int i = 1; i <= result.row; i++){
            for (int j = 1; j <= result.col; j++){
                result.matrix[i][j] = 0;
                for (int k = 1; k <= this.col; k++){
                    result.matrix[i][j] += this.matrix[i][k] * M2.matrix[k][j];
                }
            }
        }
        return result;
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
    public MATRIKS solveSPLGauss(){
        boolean zeroall;

        //PIVOTING
        MATRIKS bantuan = this.uppertri(true);
        // bikin leading 1 dulu
        for(int i=1;i<=bantuan.row;i++) {
            int temp_index=i;
            while(bantuan.row==0 && temp_index<=bantuan.col-1) {
                temp_index++;
            }
            if(temp_index==bantuan.col) {
                continue;
            }
            if(bantuan.matrix[i][temp_index]==1) {
                continue;
            } else {
                double divider = bantuan.matrix[i][temp_index];
                for(int k=1;k<=bantuan.col;k++) {
                    bantuan.matrix[i][k] /= divider;
                }
            }

        }
        
        

        
    }
    
    public MATRIKS inverse(){
        MATRIKS I = new MATRIKS(this.row,this.col);
        for (int i = 1; i <= this.row;i ++){
            for (int j = 1; j<= this.col;j++){
                if (i == j){            //MATRIKS IDENTITAS
                    I.matrix[i][j] = 1;
                } else {
                    I.matrix[i][j] = 0;
                }
            }
        }

        if (this.bujur()){
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
            System.out.println("ilegal dimension, no inverse");
            return this;
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
                            j = j % res.row; i++;
                        } 
                    }
                }
            }
        }
        return res;
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
    public MATRIKS uppertri(boolean aug){
        MATRIKS a = new MATRIKS(this);
        if(aug) {
            for(int i=1;i<this.col-1;i++) {
                int temp_index=i;
                while(this.matrix[temp_index][i]==0 && temp_index<=this.row) {
                    temp_index++;
                }
                if(temp_index==this.row+1) {
                    continue;
                }
                if(temp_index!=i) {
                    swap(i,temp_index);
                }
                for(int k=i+1;k<=this.row;k++) {
                    if(this.matrix[k][i]!=0) {
                        double scalar = this.matrix[k][i]/this.matrix[i][i];
                        for(int z=i;z<=this.col;z++) {
                            a.matrix[k][z] = this.matrix[k][z] - (this.matrix[i][z]*scalar);
                        }
                    }
                }
            }
        } else {
            for(int i=1;i<this.col;i++) {
                int temp_index=i;
                while(this.matrix[temp_index][i]==0 && temp_index<=this.row) {
                    temp_index++;
                }
                if(temp_index==this.row+1) {
                    continue;
                }
                if(temp_index!=i) {
                    swap(i,temp_index);
                }
                for(int k=i+1;k<=this.row;k++) {
                    if(this.matrix[k][i]!=0) {
                        double scalar = this.matrix[k][i]/this.matrix[i][i];
                        for(int z=i;z<=this.col;z++) {
                            a.matrix[k][z] = this.matrix[k][z] - (this.matrix[i][z]*scalar);
                        }
                    }
                }
            }
        }
        return a;
    }

    public double determinant(){
        double det = 1;
        if (this.bujur()){
            for(int i=1;i<this.col;i++) {
                int temp_index=i;
                while(this.matrix[temp_index][i]==0 && temp_index<=this.row) {
                    temp_index++;
                }
                if(temp_index==this.row+1) {
                    return 0.0000000;
                }
                if(temp_index!=i) {
                    swap(i,temp_index);
                    det = det * (-1.000000000);
                }
                for(int k=i+1;k<=this.row;k++) {
                    if(this.matrix[k][i]!=0) {
                        double scalar = this.matrix[k][i]/this.matrix[i][i];
                        for(int z=i;z<=this.col;z++) {
                            this.matrix[k][z] -= (this.matrix[i][z]*scalar);
                        }
                    }
                }
            }
            for(int l=1;l<=this.row;l++) {
                det=det*this.matrix[l][l];
            }
            if(det==-0.0000000000) {
                return 0.0000000000;
            } else {
                return det;
            }
        }
        else {
            System.out.println("Tidak ada determinan");
            return 0.0000000000000;
        }
    }

    public MATRIKS cramerssplsolve(){
        MATRIKS result = new MATRIKS (this.row,1);
        double detcol;
        MATRIKS retu = new MATRIKS (this.row,1);
        MATRIKS SPL = new MATRIKS (this.row, this.col-1);
         
        for (int i =1; i <= this.row; i ++){
            result.matrix[i][1] = this.matrix[i][this.col]; 
            for (int j = 1; j < this.col; j++){             //augmented matrix di pisah ke matrix spl dan matrix result
                SPL.matrix[i][j] = this.matrix[i][j];
            }
        }
        double deter = SPL.determinant();
        for (int k =1; k <= this.col;k++){
            MATRIKS ASPL = new MATRIKS(SPL);
            for(int l = 1;l<=this.row;l++){
                ASPL.matrix[l][k] = result.matrix[l][1];
            }
            detcol = ASPL.determinant();
            retu.matrix[k][1] = detcol / deter;
        }
        return retu;
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
