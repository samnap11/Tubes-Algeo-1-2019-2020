// import java.io; //File Handling belum butuh
// package tubes;
import java.util.*;
import java.io.*;
import java.lang.Math;

public class MATRIKS{

    //Attributes
    int row,col;
    double [][] matrix;
    
    //METHOD INPUT
    public void inputmatriks() {

		Scanner scan = new Scanner(System.in);

		System.out.print("Masukkan baris matriks : "); 
		int row = scan.nextInt(); //input baris
		System.out.print("Masukkan kolom matriks : ");
		int col = scan.nextInt(); //input kolom

        this.inputmatriks(row,col);
    }

    public void inputmatriks(int row, int col) {

        Scanner scan = new Scanner(System.in);

		this.row = row;
		this.col = col;

        /* isi matriks */
        System.out.println("Masukkan matriks "+row +" x " + col );
		for(int i=1;i<=this.row;i++){
			for(int j=1;j<=this.col;j++){
				// System.out.print("Input matrix[" + (i) + "][" + (j) + "] : ");
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

    public void inputfromfile(){  //
        try{
        File file = new File("MATRIKS.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        this.col = 0;
        this.row = 0;
        int numline =0;
        String[] separate = line.split(" ",100);
        while (line  != null){
            this.row += 1;
            for (int i = 0; i < separate.length; i ++){
                separate[i] = "not";    
            }
            numline = 0;
            separate = line.split(" ",100);
            for (int i = 0; i < separate.length; i ++){
                if (separate[i] == "not") {
                    break;
                }
                else{
                    numline += 1;
                }
            }
            for (int i=0;i < numline;i++){
                this.matrix[this.row][i+1] = Double.valueOf(separate[i]);
            }
            this.col = numline;
            line = br.readLine();
        }}
        catch(IOException e){
            System.err.println(e.toString());
        }
    

        // // File inp = new File("MATRIKS.txt");
        // FileReader reade;
        // String data;
        // String temp ="";
        // // int colnow,rownow;
        // // colnow = 1; rownow = 1;
        // try{
        //     reade = new FileReader("MATRIKS.txt");
        //     data = String.valueOf(reade.read());
        //         while (!(Integer.valueOf(data) == -1)){
        //         if (data == "\n"){
        //             this.matrix[this.row][this.col] = Double.valueOf(temp);
        //             this.row += 1;
        //             this.col = 1;
        //             temp ="";
        //             data = String.valueOf(reade.read());
        //         }
        //         else if (data == " "){
        //             this.matrix[this.row][this.col] = Double.valueOf(temp);
        //             this.col += 1;
        //             temp = "";
        //             data = String.valueOf(reade.read());
        //         }
        //         else{
        //             temp += data;
        //             data = String.valueOf(reade.read());
        //         }
        //     }
        // }
        // catch (FileNotFoundException e){
        //     System.err.println(e.toString());
        // }
        // catch (IOException e){
        //     System.err.println(e.toString());
        // }
        this.printmatriks();
    }
    //INI BARU KONSTRUKTORNYA
    public MATRIKS(int rows, int cols){
        this.row = rows;
        this.col = cols;            //MakeEmpty yang tadi sama persis ama ini wkwk
        this.matrix = new double[this.row+1][this.col+1];
    }                               //Konstruktor namanya samain aja ama classnya, dan bisa 3 gini buat override. benar sekaleeeee

    public MATRIKS(MATRIKS A){
        this.row = A.row;
        this.col = A.col;
        for (int i =1; i <= this.row;i++){
            for (int j =1; j<= this.col;j++){
                this.matrix[i][j] = A.matrix[i][j];
            }
        }
    }
    public MATRIKS(){
        this.row = 100;
        this.col = 100;
        this.matrix = new double[this.row+1][this.col+1];
    }


    //Selector
    public double getElmt(int row, int col){
        return this.matrix[row][col];
    }


    //METHODDDDDDDDDDDDDDDDDDDD
    public void printmatriks(){
        // System.out.println(this.row +" "+ this.col);
        for(int i = 1; i <=this.row;i++){
            for(int j = 1; j<=this.col;j++){
                System.out.print(String.format("%.15f ",this.getElmt(i,j)));
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

    public double determinantcofactorexpansion(){
        double det=0;
        for (int i = 1; i <= this.row; i++){
            det += (Math.pow(-1,(i+1))*this.cofactor(i,1)*this.matrix[i][1]);
        }
        return det;
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
        //PIVOTING
        MATRIKS bantuan = new MATRIKS(this.row,this.col);
        bantuan.salin(this.uppertri(true));
        // bikin leading 1 dulu
        for(int i=1;i<=bantuan.row;i++) {
            int temp_index=i;
            while(bantuan.matrix[i][temp_index]==0 && temp_index<=bantuan.col-1) {
                temp_index++;
            }
            if(temp_index==bantuan.col) {
                continue;
            }
            if(bantuan.matrix[i][temp_index]==1) {
                continue;
            } else {
                double divider = bantuan.matrix[i][temp_index];
                for(int k=temp_index;k<=bantuan.col;k++) {
                    bantuan.matrix[i][k] /= divider;
                }
            }
        }
        // Gauss form is achieved
        MATRIKS equation = new MATRIKS(bantuan.row,bantuan.col-1);
        for(int z=1;z<=bantuan.row;z++) {
            for(int y=1;y<=bantuan.col-1;y++) {
                equation.matrix[z][y] = bantuan.matrix[z][y];
            }
        }
        MATRIKS b = new MATRIKS(this.row,1);
        for(int x=1;x<=bantuan.row;x++) {
            b.matrix[x][1] = bantuan.matrix[x][this.col]; 
        }
        MATRIKS hasil = new MATRIKS(this.row,1);
        if(equation.determinant()==0) {
            int o=1;
            while(o<=this.col && bantuan.matrix[this.row][o]==0) {
                o++;
            }
            if(o==this.col-1) {
                System.out.println("Solusi tidak ada");
                for(int y=1;y<=this.row;y++) {
                    hasil.matrix[y][1]=Double.NaN;
                }
            }
        } else {
            for(int l=this.row;l>=1;l--) {
                hasil.matrix[l][1] = bantuan.matrix[l][this.col];
                for(int m=l+1;m<this.col;m++) {
                    hasil.matrix[l][1] -= bantuan.matrix[l][m]*hasil.matrix[m][1];
                }
                hasil.matrix[l][1] /= bantuan.matrix[l][l];
            }
        }
        return hasil;
    }
    public MATRIKS solveSPLGJ() {
        MATRIKS bantuan = this.uppertri(true);
        // bikin leading 1 dulu
        for(int i=1;i<=bantuan.row;i++) {
            int temp_index=i;
            while(bantuan.matrix[i][temp_index]==0 && temp_index<=bantuan.col-1) {
                temp_index++;
            }
            if(temp_index==bantuan.col) {
                continue;
            }
            if(bantuan.matrix[i][temp_index]==1) {
                continue;
            } else {
                double divider = bantuan.matrix[i][temp_index];
                for(int k=temp_index;k<=bantuan.col;k++) {
                    bantuan.matrix[i][k] /= divider;
                }
            }
        }
        // menghilangkan angka yang ada di kolom leading 1, selain leading 1 itu sendiri
        for(int j=bantuan.col-1;j>=1;j--) {
            int index1=1;
            while(index1<=this.row && bantuan.matrix[index1][j]!=1) {
                index1++;
            }
            if(index1==this.row+1) {
                continue;
            } else {
                int l=1;
                while(l<=bantuan.row) {
                    if(bantuan.matrix[l][j]!=0 && l!=index1) {
                        double multiplier = bantuan.matrix[l][j]/bantuan.matrix[index1][j];
                        for(int m=1;m<=bantuan.col;m++) {
                            bantuan.matrix[l][m] -= bantuan.matrix[index1][m]*multiplier;
                        }
                    }
                    l++;
                }
            }
        }
        // Gauss Jordan form is achieved
        MATRIKS equation = new MATRIKS(bantuan.row,bantuan.col-1);
        for(int z=1;z<=bantuan.row;z++) {
            for(int y=1;y<=bantuan.col-1;y++) {
                equation.matrix[z][y] = bantuan.matrix[z][y];
            }
        }
        MATRIKS b = new MATRIKS(this.row,1);
        for(int x=1;x<=bantuan.row;x++) {
            b.matrix[x][1] = bantuan.matrix[x][this.col]; 
        }
        MATRIKS hasil = new MATRIKS(this.row,1);
        if(equation.determinant()==0) {
            int o=1;
            while(o<=this.col && bantuan.matrix[this.row][o]==0) {
                o++;
            }
            if(o==this.col-1) {
                System.out.println("Solusi tidak ada");
                for(int y=1;y<=this.row;y++) {
                    hasil.matrix[y][1]=Double.NaN;
                }
            }
        } else {
            for(int t=this.row;t>=1;t--) {
                hasil.matrix[t][1] = bantuan.matrix[t][this.col];
                for(int r=t+1;r<this.col;r++) {
                    hasil.matrix[t][1] -= bantuan.matrix[t][r]*hasil.matrix[r][1];
                }
                hasil.matrix[t][1] /= bantuan.matrix[t][t];
            }
        }
        return hasil;
    }
    
    public MATRIKS augment(MATRIKS hasil){
        MATRIKS a = new MATRIKS(this.row,this.col+1);
        for (int j = 1; j<= this.row; j ++){
            for (int k = 1; k < this.col; k++){
                a.matrix[j][k] = this.matrix[j][k];
            } 
        }
        for (int i = 1; i <= this.row;i++){
            a.matrix[i][a.col] = hasil.matrix[i][1];
        }
        return a;   
    }

    public MATRIKS inversebycofactor(){
        MATRIKS a = new MATRIKS(this.row,this.col);
        a.salin(this.adjoint());
        a.kalikons(1/(this.determinant()));
        return a;
    }

    public void kalikons(double x){
        for (int i = 1; i<= this.row;i++){
            for (int j = 1; j <= this.col; j++){
                this.matrix[i][j] *= x;
            }
        }
    }

    public void savetofile(){
        try{
            File file = new File ("save.txt");
            FileWriter w = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(w);
            
        
        String matrice ="";
        for (int i =1;i <= this.row;i++){
            for (int j=1;j<= this.col;j++){
                matrice += Double.toString(this.matrix[i][j]);
                if (j == this.col) matrice += "\n";
                else matrice += " ";
            }
        }
        
        bw.write(matrice);   
        bw.close();
        w.close();
    }catch (IOException e){
        System.err.println(e.toString());
    }
}

    public MATRIKS inverse(){ //inverse menggunakan gaussjordan 
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
                double divider = this.matrix[l][l];
                for (int k = 1; k <= this.col;k++){             //dividing
                    this.matrix[l][k] /= divider;
                    I.matrix[l][k] /= divider;    
                }
                for (int m = 1; m <= this.row; m++){
                    if ((m != l) && (this.matrix[m][l] != 0)){
                        double substractor = this.matrix[m][l];
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
            System.out.println("illegal dimension, no inverse");
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
                        if (j > this.row-1) {
                            j = 1; 
                            i++;
                        } 
                    }
                }
            }
        }
        return res;
    }

    public MATRIKS cofactormatrix(){
        MATRIKS cof = new MATRIKS(this.row,this.col);

        for (int i = 1; i <= this.row;i++){
            for (int j = 1; j <= this.col; j++){
                cof.matrix[i][j] = this.cofactor(i,j);
            }
        }
        return cof;
    } 

    public double cofactor(int row,int col){
        return (Math.pow(-1,row+col)*(this.removerc(row,col)).determinant());
    }

    public MATRIKS adjoint(){
        return (this.cofactormatrix()).transpose();
    }

    public MATRIKS splinverse(){
        MATRIKS spl = new MATRIKS(this.row,this.col-1);
        MATRIKS hasil = new MATRIKS(this.row,1);
        MATRIKS retu = new MATRIKS(this.row,1);
        for (int i =1 ; i <= this.row;i++){
            hasil.matrix[i][1] = this.matrix[i][this.col];
            for (int j = 1; j < this.col; j++){
                spl.matrix[i][j] = this.matrix[i][j];
            }
        }
        hasil.printmatriks();
        spl.printmatriks();
        MATRIKS invspl = new MATRIKS(this.row,this.col-1);
        invspl.salin(spl.inversebycofactor());
        invspl.printmatriks();
        retu = invspl.kalimatriks(hasil);
        return retu;
    }
    
    //segitiga atas
    public MATRIKS uppertri(boolean aug){
        MATRIKS a = new MATRIKS(this.row,this.col);
        a.salin(this);
        if(aug) {
            for(int i=1;i<this.col-1;i++) {
                int temp_index=i;
                while(this.matrix[temp_index][i]==0 && temp_index<this.row) {
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
                while(this.matrix[temp_index][i]==0 && temp_index<this.row) {
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
            Double a = Double.NaN;
            return a;
        }
    }

    public void salin (MATRIKS A){
        this.row = A.row;
        this.col = A.col;
        for (int i =1; i <= this.row;i++){
            for (int j =1; j<= this.col;j++){
                this.matrix[i][j] = A.matrix[i][j];
            }
        }
    }

    public MATRIKS cramerssplsolve(){
        MATRIKS result = new MATRIKS (this.row,1);
        double detcol;
        MATRIKS retu = new MATRIKS (this.row,1);
        MATRIKS SPL = new MATRIKS (this.row, this.col-1);
        MATRIKS DETSPL = new MATRIKS(this.row,this.col-1);
        for (int i =1; i <= this.row; i ++){
            result.matrix[i][1] = this.matrix[i][this.col]; 
            for (int j = 1; j < this.col; j++){             //augmented matrix di pisah ke matrix spl dan matrix result
                SPL.matrix[i][j] = this.matrix[i][j];
            }
        }
        // SPL.printmatriks();
        // result.printmatriks();
        
        MATRIKS ASPL = new MATRIKS(SPL.row,SPL.col);
        ASPL.salin(SPL);
        // ASPL.printmatriks();
        DETSPL.salin(SPL);
        double deter = DETSPL.determinant();
        for (int k =1; k <= ASPL.col;k++){
            ASPL.salin(SPL);
            // ASPL.printmatriks();
            for(int l = 1;l<=ASPL.row;l++){
                ASPL.matrix[l][k] = result.matrix[l][1];
            }
            detcol = ASPL.determinant();
            retu.matrix[k][1] = detcol / deter;
        }
        return retu;
    }



    public void interpolate(MATRIKS dat, int xi){ //dat adalah matriks 2 row n col, row 1 berisi x dan row 2 berisi f(x)
        int n = dat.col;
        MATRIKS spl = new MATRIKS(n,n+1);
        double result;

        for (int i = 1; i <= spl.row; i++){
            for(int j=1; j < spl.col; j++){
                spl.matrix[i][j] = Math.pow((dat.matrix[1][i]),j-1);
            }
        }
        
        for (int k = 1; k <= spl.row;k++){
            spl.matrix[k][spl.col] = dat.matrix[2][k];
        }
        result = 0;
        MATRIKS res = new MATRIKS(this.row,1);
        res.salin(spl.cramerssplsolve());
        System.out.print("P(X) = ");
        for (int l =1; l<= res.row; l++){
            System.out.print(res.matrix[l][1]+"X^"+(l-1)+" "); if (l != res.row) System.out.print("+ ");
            result += Math.pow(res.matrix[l][1],l-1)*xi;
        }
        System.out.println("");
        System.out.print("Hasil interpolasi P(X) pada titik " + xi + " : "); System.out.println(result);
        
    }
}
