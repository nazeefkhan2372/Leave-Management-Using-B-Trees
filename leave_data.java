import java.io.*;
class leave_data {
    final String file_name="leave_data.txt";
    int id = 0;
    int leave_type = 0;
    leave_data(int id, int type) {
        this.id = id;
        this.leave_type = type;
    }
    leave_data(){
    }
    public void leave(String id,String type) throws IOException {
        PrintWriter pw=new PrintWriter(new FileWriter(file_name,true));
        StringBuilder sb=new StringBuilder();
        sb.append(type);
        sb.append(",");
        sb.append(id);
        sb.append(",");
     // defaulting that the number of leaves is 20 for faculty member;
        for(int i=0;i<4;i++)    // we assume 5 types of leave with their id 1,2,...
        {
           sb.append(20);
           sb.append(",");
        }
        sb.append(20);
        pw.write(sb.toString());
        pw.write("\n");
        pw.flush();
        pw.close();
        }
    public void show(String type,int id) throws IOException{
        BufferedReader read=new BufferedReader(new FileReader(file_name));
        String line=null;
        int f=0;
        while((line=read.readLine())!=null){
            String str[]={"Casual Leave","Sick Leave","Maternity Leave","Vacation Leave" ,"Marriage Leave"};
            String [] arr=line.split(",");
            if(arr[0].equals(type)&&Integer.parseInt(arr[1])==id){
                for(int i=2;i<arr.length;i++)
                   System.out.println(str[i-2]+" "+arr[i]);
                System.out.println("");
                f=1;
            }
        }
        if (f==0)
            System.out.println("Member with given id is not found");
    } 
    public boolean is_available(String type,int id,int leave_type,int duration) throws IOException{
        BufferedReader read=new BufferedReader(new FileReader(file_name));
        String line=null;
        while((line=read.readLine())!=null){
            String [] arr=line.split(",");
            if(arr[0].equals(type)&&Integer.parseInt(arr[1])==id){
                if(duration<=Integer.parseInt(arr[leave_type+1])){
                   return true;
                }
            }
        }
        return false;
    } 
}

