import java.io.IOException;
class leave_forward extends Leave_server {
    public void forward(int id,int leave_type,int duration){
        try {
            leave_data obj=new leave_data();
            if (obj.is_available("faculty",id,leave_type,duration))
            {
                System.out.print("You are applicable ");
                enqueue("faculty"+","+id + "," + leave_type + "," + duration);
                System.out.println("and also succesfully apply for leave");
            }
            else
                 System.out.println("You are not applicable for this leave type,please try to choosen different one");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
