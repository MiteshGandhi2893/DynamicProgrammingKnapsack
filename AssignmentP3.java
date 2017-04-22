/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package dynamicknapack;

import java.io.*;
import java.util.*;



/**
 *
 * @author Mitesh
 */
public class AssignmentP3{

    /**
     * @param args the command line arguments
     */
   static Map<Integer,ArrayList<Integer>>Inputs;
  static Map<Integer,Integer>Problem;
    static int Knapsack[][];
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        
      int count =0;
      int size=0;
      int cursor=0;
       int knapsack_size=0;
       int Knapsack_weight=0;
      String sizesarray[]=null;
      String dataarray[]=null;
        try
      {
           LineNumberReader ln=new LineNumberReader(new FileReader(args[0]));
           FileOutputStream BW=new FileOutputStream(new File(args[1]));

            Problem=new HashMap<Integer,Integer>();
            String TempStr[]=ln.readLine().split("\\s+");
            knapsack_size=Integer.parseInt(TempStr[0]);
            Knapsack_weight=Integer.parseInt(TempStr[1]);
            
            Problem.put(knapsack_size,Knapsack_weight);
            int counter=0;           
            String data="";
            
             ln.setLineNumber(cursor+1);
            String Line=null;
            cursor++;
           
            while((Line=ln.readLine())!=null)
             {
                    if(ln.getLineNumber()==((knapsack_size+1+cursor)))
                    {
                        //System.out.println(cursor +" "+counter);
                          TempStr=Line.split("\\s+");
                        
                         knapsack_size=Integer.parseInt(TempStr[0]);
                        Knapsack_weight=Integer.parseInt(TempStr[1]);
                        Problem.put(knapsack_size, Knapsack_weight);
                         
                        data=data+"@"; 
                        cursor=counter+2;
                       ln.setLineNumber(cursor);
                        //ln.setLineNumber(knapsack_size+2);

                    }
                    else
                    {
                       data=data+Line+"\n";
                       counter++;
                    }
           }

                     
           dataarray=data.split("@");
         
           
           int counter1=0;
           
           
           for(Map.Entry ex:Problem.entrySet())
           {
                 Inputs=new HashMap<Integer, ArrayList<Integer>>();
               counter1++;
               String datatemp[]=dataarray[counter1-1].split("\n");
               Inputs.put(0, null);
              // System.out.println(counter1+"\n"+dataarray[counter1-1]);
               for(int i=0;i<datatemp.length;i++)
               {
                   ArrayList<Integer> temparray=new ArrayList<Integer>();
                   String sumprob[]=datatemp[i].split("\\s+");
                   temparray.add(Integer.parseInt(sumprob[0]));
                   temparray.add(Integer.parseInt(sumprob[1]));
                   Inputs.put(i+1, temparray);
                   
                   
               }
               
               
           
               
               Integer ksize=(Integer)ex.getKey();
               Integer kWeight= (Integer)ex.getValue();
               Knapsack=new int[ksize+1][kWeight+1];
               
              
               
             long time1 = System.currentTimeMillis();
              int maxProfit=DynamicKnapsackProb(Inputs, ksize,kWeight);
        for(int i=0;i<Knapsack.length;i++)
        {
              for(int j=0;j<=kWeight;j++)               {
                   System.out.print(Knapsack[i][j]+"\t");
              }
               System.out.println();
          }
                 String Knap="";
                 int countSack=0;
                 Map<Integer,ArrayList<Integer>>OptSol=OptimalSolution(ksize,kWeight);
                 long time2 = System.currentTimeMillis();
                 long runtime=time2-time1;
                 
                 for(Map.Entry Es:OptSol.entrySet())
                 {
                     countSack++;
                     ArrayList<Integer>Temp=(ArrayList<Integer>)Es.getValue();
                     Knap=Knap+" Item "+Es.getKey()+"(W="+Temp.get(0)+",P="+Temp.get(1)+")\n";
                  }
                 
                 String Content="Problem Size :"+ksize+" Max Profit :"+maxProfit+" runtime :"+runtime+"\n Items in Knapsack :\n"+Knap+"\n";
                  byte[]con=(Content).getBytes();
                 BW.write(con);
                 
                 
          }
           
           
          
           
           
           
           
        }
            catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }
        
    public static int DynamicKnapsackProb(Map<Integer,ArrayList<Integer>> Input,int size,int Weight)
    {
       int row=0;
       int column=0;
       for(column=0;column<=Weight;column++)
       {
           Knapsack[0][column]=0;
       }
        for(row=1;row<=size;row++)
       {
           Knapsack[row][0]=0;
          int kweight=Input.get(row).get(0);
          int kprofit=Input.get(row).get(1);
          // System.out.println(kweight+"    "+kprofit);
          for(column=1;column<=Weight;column++)
           {
               
               if((kweight<=column)&&(Knapsack[row-1][column-kweight]+kprofit>Knapsack[row-1][column]))
               {
                   Knapsack[row][column]=Knapsack[row-1][column-kweight]+kprofit;
                   
               }
               else
               {
                   Knapsack[row][column]=Knapsack[row-1][column];

               }
               
            }
          
          
       }
        
        
                  return Knapsack[size][Weight];

        
        
        
        
        
        
        
    }
        
        
        public static Map<Integer,ArrayList<Integer>>OptimalSolution(int n,int W)
        {
            Map<Integer,ArrayList<Integer>>OptSol=new HashMap<Integer,ArrayList<Integer>>();

           int problem=n;
           int weight=W;
           int count=0;
           while(problem>0&&weight>0)
           {
               if(Knapsack[problem][weight]!=Knapsack[problem-1][weight])
               {
                  ArrayList<Integer>TempArray=new ArrayList<Integer>();
                  TempArray.add(Inputs.get(problem).get(0));
                  
                   TempArray.add(Inputs.get(problem).get(1));

                  OptSol.put(problem,TempArray);

                  weight=weight-Inputs.get(problem).get(0);
                  problem=problem-1;
               }
               else
               {
                   problem=problem-1;
               }
           }
            
            
            
            
            
            return OptSol;
        }
        
        
        
        
        
        
        
        
    }

