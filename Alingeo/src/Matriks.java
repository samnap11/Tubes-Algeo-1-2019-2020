// import java.io; //File Handling belum butuh
import java.util.*;

public class Matriks{

    //Attributes
    int row,col;
    double [][] matrix;
    
    //Konstruktor
    public void MakeEmpty(int rows, int cols) {
        this.row = rows;
        this.col = cols;
        this.matrix = new double[this.row+1][this.col+1];
    }

    public void inputmatriks(Matriks A) {
        Scanner in = new scanner (System.in);
        for(int i=1;i<=this.row;i++){
            for(int j=1; j<= this.col;j++){
                this.matrix[i][j] = in.nextDouble();
            }
        }
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
            system.out.println("");
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
        return this.row == this.col;
    }

    public void swaprows(int i, int j){

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