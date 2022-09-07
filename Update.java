import java.util.*;
import java.io.*;
class Update
{
    private String[] double_length(String str[],int len) throws IOException
    {
         String temp[]=new String[2*len];
         for(int i=0;i<len;i++)
            temp[i]=str[i];
         return temp;
    }
    public void update_leave(String[] str) throws IOException
    {
        String[] final_str=new String[5];
        int i=0;
        try{    
           BufferedReader bf=new BufferedReader(new FileReader("leave_data.txt"));
           String line=bf.readLine();
           int f=0;
           while(line!=null)
           {
               String[] str_line=line.split(",");
               if (str_line[0].equals(str[0])&&str_line[1].equals(str[1]))
               {
                   int leave_type=Integer.parseInt(str[2]);
                   int duration=Integer.parseInt(str[3]);
                   int a=Integer.parseInt(str_line[leave_type+1])-duration;
                   if (a<0)
                   {
                      f=1;
                      break;
                   }
                   str_line[leave_type+1]=Integer.toString(a);
                   line=(Arrays.toString(str_line)).replace("[","").replace("]","");
                   for(int j=0;j<line.length();j++)
                   {
                       if (line.charAt(j)==' ')
                           line=line.substring(0,j)+line.substring(j+1,line.length());
                   }
               }
               final_str[i]=line;
               i++;
               if (i==final_str.length)
                  final_str=double_length(final_str,final_str.length);
               line=bf.readLine();  
           }
           member_details obj=new member_details();
           if (f==0)
           {
           PrintWriter pw=new PrintWriter("leave_data.txt");
           for(int j=0;j<i;j++)
              pw.println(final_str[j]);
            pw.flush();
            pw.close();
            obj.dequeue(str[0],str[1],"leave_request.txt");
            System.out.println("\n~~Leave request confirm~~");
           }
           else
           {
               System.out.println("\nMember is not applicable for this request");
               obj.dequeue(str[0],str[1],"leave_request.txt");
           }
        }
        catch(Exception e)
        {
            System.out.println("Problem in file reading");
        }
    }
    public void change_password(String type,String id ,String new_pass) throws IOException
    {
        String[] final_str=new String[5];
        int i=0;
        try{
           BufferedReader bf=new BufferedReader(new FileReader("login_details.txt"));
           String line=bf.readLine();
           while(line!=null)
           {
               String[] str_line=line.split(",");
               if (type.equals(str_line[0])&&id.equals(str_line[1]))
               {
                   str_line[2]=new_pass;
                   line=(Arrays.toString(str_line)).replace("[","").replace("]","");
                   System.out.println(line);
                   for(int j=0;j<line.length();j++)
                   {
                        if (line.charAt(j)==',')
                           line=line.substring(0,j+1)+line.substring(j+2,line.length());
                    }
                    System.out.println(line);
               }
                final_str[i]=line;    
               i++; 
               if (i==final_str.length)
                  final_str=double_length(final_str,final_str.length);
               line=bf.readLine();
           }
        }
        catch(Exception e)
        {
            System.out.println("Problem in file reading");
        }
        PrintWriter pw=new PrintWriter("login_details.txt");
        for(int j=0;j<i;j++)
            pw.println(final_str[j]);
        pw.flush();
        pw.close();
    }
}