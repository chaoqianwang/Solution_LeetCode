public class ForJob {

    public int func(int n, int[][] a){
        int[][] f=new int[3][n];

        for(int j=n-2;j>=0;j--){
            for(int i=0;i<3;i++){
                int tmp1=Math.abs(a[i][j]-a[0][j+1])+f[0][j+1];
                int tmp2=Math.abs(a[i][j]-a[1][j+1])+f[1][j+1];
                int tmp3=Math.abs(a[i][j]-a[2][j+1])+f[2][j+1];
                f[i][j]=Math.min(Math.min(tmp1,tmp2),tmp3);
            }
        }

        return Math.min(Math.min(f[0][0],f[1][0]),f[2][0]);
    }

    public static void main(String[] args) {
        int[][] a={{1,2,3},{1,1,1},{1,2,3}};
        int answer=new ForJob().func(3,a);
        System.out.println("pause");
    }
}
