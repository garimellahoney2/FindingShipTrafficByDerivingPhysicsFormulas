import java.util.*;
import java.io.*;
class Reporttraffic
{
public static void main(String args[]) throws IOException
{
String ship=null;
ShipHighWay obj =null;
 FileInputStream fin = new FileInputStream("F:/shiphighwayfile.txt");
ObjectInputStream ois = new ObjectInputStream(fin);
/*Conside only x cordinates of distance here consider acceleration is zero*/
double totaltimetowait =0;
Scanner sc = new Scanner(System.in);
double x1,x2;
int v1,v2;
double angle1,angle2;
System.out.println("enter date");
String date=sc.next();
System.out.println("enter x coordinate value in km");
x1=sc.nextDouble();
System.out.println("enter velocity value in kmph");
v1=sc.nextInt();
System.out.println("enter angle in degrees");
angle1=sc.nextDouble();
System.out.println("enter time");
Double t1=sc.nextDouble();
/*kinematics*/
try
{
while((obj=(ShipHighWay)ois.readObject())!=null)
{
double t;
if(date.equals(obj.date))
{
x2=obj.startingx;
double t2=obj.startingTime;
v2=obj.speed;
angle2=obj.angle;
double timediff = t2-t1;
if(angle1==angle2&&v2==v1)
{
if(x2==x1+(v1)*timediff*Math.cos(angle1))
t=1;
else
t=-1;
}
else
t=(double)(Math.abs(x2-x1)-v2*Math.cos(angle2)*timediff)/(v2*Math.cos(angle2)-v1*Math.cos(angle1));//since distance is postive we took absolut value of x2-x1


if(t>0)//means boats will meet if t<0 they will not meet
{//take negative value of t and substitute u wiil get time difference re
System.out.println("time after which they meet is(w.r.t ship1):"+t);
System.out.println("starting time is"+t2);

int tochange=-1;
double requiredtimedifference = (double)(tochange*(v2*Math.cos(angle2)-v1*Math.cos(angle1))-(x2-x1))/v2*Math.cos(angle2);
if(requiredtimedifference>totaltimetowait||totaltimetowait==0)
{ship=obj.ship_name;
totaltimetowait=requiredtimedifference;
}
}
}//end if for date
}
}

catch(Exception e)
{
System.out.println("");
}
if(totaltimetowait>0)
{

System.out.println("\n"+"YOUR REQUIRED TO WAIT FOR:"+totaltimetowait+"hr"+"because of ship:"+ship);
}
else if(totaltimetowait<0)
System.out.println("\n"+"YOUR REQUIRED TO START BEFORE :"+Math.abs(totaltimetowait)+"hr"+" because of ship:"+ship);//t2-t1 we are finding
else
System.out.println("\n"+"                                                  NO TRAFFIC HAVE A HAPPY JOURNEY  :)                                          ");

fin.close();
ois.close();
}
}
class ShipHighWay implements Serializable
{
String ship_name;
String date;
double startingTime;
int speed;
double startingx;
double angle;
}
