import java.util.Arrays;
import java.util.Collections;

public class LNS
{
    static double alpha = 0.75;
    static double beta = 0.1;
    static double gama = 0.1;
    static double [] related_matraix = new double[Conf.N];
    static Boolean [] if_relax = new Boolean[Conf.N];
    static double nor_dis_martraix;

    //用于归一化的四个数据
    static int max_time=0;
    static int min_time=0;
    static double max_dis=0;
    static double min_dis=0;
    static void get_normal()
    {
        for(int i=1;i<=Conf.N;i++) {
            if (min_time > Conf.customers[i].r_time) min_time = Conf.customers[i].r_time;
            if (max_time < Conf.customers[i].r_time) max_time = Conf.customers[i].r_time;
        }
    }
    static void set_max_min(int id)
    {
        min_dis = 1000;
        max_dis = 0;
        for(int i=1;i<=Conf.N;i++)
        {
            if(min_dis > Conf.dis_matriax[id][i]) min_dis = Conf.dis_matriax[id][i];
            if(max_dis > Conf.dis_matriax[id][i]) max_dis = Conf.dis_matriax[id][i];
        }
    }

    // 三个用于关联函数的超参数
    static void related_function(int id,Route route)// 传入一个customer,序号为i，返回基础关联函数值，route是和customer同组的顾客
    { 
        set_max_min(id);
        for(int i=1;i<=Conf.N;i++)
        {
            int T;

            if(!if_relax[i])//have not relax
            {
             T = 0;
             if(route.cus_list.contains(i))
             {
                 T = 1;
             }
             related_matraix[i] = 1/(((alpha*(Conf.dis_matriax[id][i]-min_dis))/(max_dis-min_dis))+beta*Math.abs(((Conf.customers[id].r_time-Conf.customers[i].r_time)-min_time)/(max_time-min_time))+gama*(Conf.customers[id].demand-Conf.customers[i].demand)+T);
             }
        }

    }
}

