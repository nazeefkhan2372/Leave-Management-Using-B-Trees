import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
public class member_details extends leave_data {
    private final String login_details="login_details.txt";
    final String name="member.txt";
    public void  add_member() throws IOException{
        PrintWriter pw=new PrintWriter(new FileWriter("member.txt",true));
        StringBuilder sb=new StringBuilder();
        String extra;
        Scanner obj=new Scanner(System.in);
        String type=null;
        while(true){
        System.out.println("Enter member type(Admin/faculty) :");
        type =obj.nextLine();
        if(type.equals("admin")||type.equals("faculty")||type.equals("Faculty")||type.equals("Admin")){
            sb.append(type);
        sb.append(",");
        break;
        }
        else{
            System.out.println("sorry ,please enter the correct type");
            System.out.println("-----");
        }
    }
        System.out.println("Assign ID : ");
        String id=obj.nextLine();
        Integer number=Integer.parseInt(id);
        System.out.println("Adding to bplustree"+number);
        // btree BPT=new btree();
        btree.insert(number);
        // BPT.insert(number);
        sb.append(id);
        sb.append(",");
        System.out.println("Enter Name :");
        String name =obj.nextLine();
        sb.append(name);
        sb.append(",");
        String dob=null;
        SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
        while(true){
        System.out.println("Enter DOB ( format DD/MM/YYYY) : "  );
        dob=obj.nextLine();
        try{
            Date d1 = sf.parse(dob);
            
            sb.append(dob);
            sb.append(",");
            break;
            } catch (Exception e) {
            System.out.println("sorry wrong format");
        }
    }
        System.out.println("Enter employee category");

        String x=obj.nextLine();
        //int type=Integer.parseInt(x);
        sb.append(x);
        sb.append(",");
        System.out.println("Gender (M/F) : ");
        char gender=obj.next().charAt(0);
        extra=obj.nextLine();
        sb.append(gender);
        sb.append(",");
        while(true)
        {
            System.out.println("Enter Mobile no. : ");
            String mob=obj.nextLine();
          
            boolean c = true;
            c = Pattern.matches("[0-9]{10}", mob);//true
            while(c!=true)
            {
                System.out.println("Invalid phone number format ");
                break;   
            }
            if(c==true)
            {
            sb.append(mob);
            sb.append(",");
            break;
            }
        }
        
        System.out.println("Enter E-mail : ");
        String email=obj.nextLine();
        sb.append(email);
        pw.write(sb.toString());
        pw.write("\n");
        pw.flush();
        pw.close();
        Assign_pass(Integer.parseInt(id),type);
        try{
            leave(id,type);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean dequeue(String type,String id,String filename) throws IOException  // dequeue first one 
    {                                                                                 // with this type and id(required in approve and reject leave) 
        BufferedReader bf=new BufferedReader(new FileReader(filename));
        String line=bf.readLine();
        int max_size=10;
        String str_arr[]=new String[10];
        int size=0;
        int f=0;
        while(line!=null)
        {
            String str[]=line.split(",");
            if(f==1||(!str[0].equals(type))||(!str[1].equals(id)))
            {
                str_arr[size]=line;
                size++;
                if (size==max_size)
                {
                   str_arr=double_length(str_arr,size);
                   max_size=2*max_size;
                }
            }
            else 
                f=1;
            line=bf.readLine();
        }
        bf.close();
        FileWriter fw=new FileWriter(filename);
        PrintWriter pw=new PrintWriter(fw);
        for(int i=0;i<size;i++)
        {
            String str[]=str_arr[i].split(",");
            for(int j=0;j<str.length;j++)
            {
                if (j==str.length-1)
                   pw.println(str[j]);
                else
                {
                    pw.print(str[j]);
                    pw.print(",");
                }      
            }
        }  
        pw.flush();
        pw.close();
        if (f==0)
            return false;
        else 
            return true;
    }
    public void pending_leave_request() throws IOException
    {
        Scanner scan=new Scanner(System.in);
        BufferedReader bf=new BufferedReader(new FileReader("leave_request.txt"));
        String line=null;
        line=bf.readLine();
        int f=0;
        if (line!=null)
        {
           System.out.println("\nPendding requests are -");
           System.out.println(line);
           f=1;
        }
        else
           System.out.println("\nThere is no pending leave request");
        while((line=bf.readLine())!=null)
            System.out.println(line);
            if(line!=null){
            String[] splitter=line.split("\\,");

            int id=Integer.parseInt(splitter[1]);
            // btree BPT=new btree();
            System.out.println("Searching the "+ id+ " in B Plus Tree");
            btree.search(id);
            }
        if (f==1)
        {
            admin obj=new admin();
            try{obj.approve_reject();}
            catch(Exception e)
            {
                System.out.println(e);
            }
        }   
    }
    private  String[] double_length(String str[],int len) throws IOException
    {
         String temp[]=new String[2*len];
         for(int i=0;i<len;i++)
            temp[i]=str[i];
        return temp;
    }
        public void member_info1()throws IOException{
        BufferedReader bf=new BufferedReader(new FileReader(name));
        String line=null;
        int flag=0;
        while((line=bf.readLine())!=null){
            String arr[]=line.split(",");
                for(int  i=0;i<arr.length-1;i++)
                    System.out.print(arr[i]+" | ");
                System.out.println(arr[arr.length-1]);
                flag=1;
        }
        if(flag==0){
            System.out.println("faculty or admin not found");
        }
    }
    public void member_info2(String type,int login_id)throws IOException{
        BufferedReader bf=new BufferedReader(new FileReader(name));
        String line=null;
        int flag=0;
        while((line=bf.readLine())!=null){
            String arr[]=line.split(",");
            if(arr[0].equals(type)&&Integer.parseInt(arr[1])==login_id){
                for(int  i=1;i<arr.length-1;i++)
                    System.out.print(arr[i]+" | ");
                System.out.println(arr[arr.length-1]);
                flag=1;
                break;
                }
        }
        if(flag==0){
            System.out.println("faculty or admin not found");
        }
    }
    public void Assign_pass(int id,String type){
         try {
             Scanner scan=new Scanner(System.in);
             PrintWriter pw=new PrintWriter(new FileWriter(login_details,true));
             StringBuilder sb=new StringBuilder();
             sb.append(type);
             sb.append(",");
             sb.append(id);
             sb.append(",");
             System.out.println("Assign password for this id holder");
             sb.append(scan.nextLine());
             pw.write(sb.toString());
             pw.write("\n");
             pw.flush();
             pw.close();
             return;
         }
         catch (Exception e){
             System.out.println("error occured, please try again");
         }
    }   
}
