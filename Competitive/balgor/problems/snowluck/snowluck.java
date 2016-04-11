import java.util.*;
import java.io.*;
public class snowluck
{
  static int treeInfo[][]=new int [50][4];
  public static void main(String arg[])
  {
    Scanner sc=new Scanner(System.in);
    while(sc.hasNext())
    {
      System.out.println(sc.nextLine());
      int n=sc.nextInt();
      String trash=sc.nextLine();
      for(int i=1;i<n;i++)
      {
        Scanner tmp=new Scanner(sc.nextLine());
        for(int j=0;j<4;j++)
          treeInfo[i][j]=tmp.nextInt();
      }
      path(n);
    }
  }
  public static void path(int n)
  {
    int dp[][][]=new int[n*n+1][n+1][n+1];/*shots fired___ends at___bulls eye */
    /*Arrays.fill(dp,-1); /*apparent does not work*/
    for(int i=0;i<=n*n;i++)
      for(int j=0;j<=n;j++)
        for(int k=0;k<=n;k++)
          dp[i][j][k]=-1;
    dp[0][1][0] = 0;
    for(int i=1;i<=n*n;i++) /*num of shoots*/
    {
      for(int j=1;j<=n-1;j++) /*ends at*/ /*no need to compute last tree*/
      {
        for(int k=0;k<=n;k++) /*num of bulls eye*/
        {
          if(dp[i-1][j][k]!=-1)
          {
            /*for bulls eye*/
            if(k!=n)
            if(dp[i][treeInfo[j][2]][k+1]==-1 || dp[i][treeInfo[j][2]][k+1] > dp[i-1][j][k]+treeInfo[j][3])
              dp[i][treeInfo[j][2]][k+1]=dp[i-1][j][k]+treeInfo[j][3];
            /*for miss*/
            if(dp[i][treeInfo[j][0]][k]==-1 || dp[i][treeInfo[j][0]][k] > dp[i-1][j][k]+treeInfo[j][1])
              dp[i][treeInfo[j][0]][k]=dp[i-1][j][k]+treeInfo[j][1];
          }
        }
      }
    }
    for(int i=0;i<=n;i++)
    {
      int min=dp[1][n][i];
      for(int j=2;j<=n*n;j++)
      {
        if(min == -1 || (min>dp[j][n][i]&&dp[j][n][i]!=-1) )
          min=dp[j][n][i];
      }
      if(min==-1)
        System.out.print("X ");
      else 
        System.out.print(min+" ");
    }
    System.out.println(); /*gives room for next case*/
    /*-------------------------------
 *     /*
 *         for(int i=0;i<=n*n;i++)
 *             {
 *                   System.out.println("For i = "+i);
 *                         for(int j=0;j<=n;j++)
 *                               {
 *                                       for(int k=0;k<=n;k++)
 *                                                 System.out.print(dp[i][j][k]+" ");
 *                                                         System.out.println();
 *                                                               }
 *                                                                   }*/
  }
}
