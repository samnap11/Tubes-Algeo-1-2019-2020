// import java.io; //File Handling belum butuh
import java.util;
import java.io.Reader;
import java.util.Scanner;

class MATRIKS{

    //attributes
    int row,col;
    double [][] matrix;
    
    //Konstruktor
    public MATRIKS(int rows, int cols){
        this.row = rows;
        this.col = cols;
        this.matrix = new double[this.row+1][this.col+1];
    }

    public MATRIKS(MATRIKS A){
        this.row = A.row;
        this.col = A.col;
        this.matrix = A.matrix;
    }

    public MATRIKS(){
        this.row = 5;
        this.col = 5;
    }

    void inputmatriks(){
        Scanner in = new scanner (system.in);
        for(int i=1;i<=this.row;i++){
            for(int j = 1; j<= this.col;j++){
                    this.matrix[i][j] = in.nextDouble();
            }
        }
    }

    void inputfromfile(MATRIKS hasil){
        
        File inp = new File("MATRIKS.txt");
        Reader read = null;

    }

    //Selector
    public double getElmt(int row, int col){
        return this.matrix[row+1][col+1];
    }

    public void printmatriks(){
        for(int i = 1; i <=this.row;i++){
            for(int j = 1; j<=this.col;j++){
                system.out.print("%.15f ",getElmt(i,j));
            }
            system.out.print("\n");
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
                    if(!(this.Elmt(i,j)==M.Elmt(i,j))){
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
        return (this.row == this.col);
    }

    public MATRIKS inverse(){

    }

    public MATRIKS transpose(){
        MATRIKS a = new MATRIKS(this.row,this.col);
        for(int i = 1; i <= this.row;i++){
            for(int j = 1;j<= this.col;j++){
                a.SetElmt(getElmt(i,j),j,i);
            }
        }
    }

    //gaussian
    public solveSPL(MATRIKS res){
        
    }

    //gaussjordan
    public solve2(){

    }

    //Cramer method
    public solve3(){

    }

    //kofaktor
    public solve4(){

    }


















}