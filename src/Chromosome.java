import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author minasora
 * @date 2019/10/8 14:38
 * @description 染色体类，可以通过split函数和Solution类进行转化
 */
public class Chromosome {
    final int INF = 999999;
    ArrayList<Integer> cur_list;
    Chromosome()// 构造函数，随机生成一个初始解
    {
        cur_list = new ArrayList<>();
        for(int i=1;i<=Conf.N;i++)
        {
            this.cur_list.add(i);
        }
        Collections.shuffle(this.cur_list);
        cur_list.add(0,0);
    }
    Solution toSolution()// 使用分割函数：跑一遍bellman-ford算法获得最优分割，实际上转化为从开始点到结束点的最短路划分问题
    {
        Solution solution = new Solution();
        double [] V = new double[Conf.N+1];//距离数组
        int [] P = new int[Conf.N+1]; // 储存了连接该点的上一点
        int j; // 循环的标识
        int cost;// 当前的花费
        double time;// 当前的时间
        for(int i = 1;i<=Conf.N;i++)//到达的点的最少花费
            V[i] = INF;
        for(int i = 1;i<=Conf.N;i++)
            P[i] = 0;//最开始所有点都没连上
        for(int i = 1;i<=Conf.N;i++)
        {
            cost  = 0;
            time = 0;
            j = i;
            while(true)
            {
                if(i == j)//行程的开始
                {
                    time += Math.max(Conf.customers[cur_list.get(j)].r_time,Conf.dis_matriax[0][cur_list.get(j)]);
                    time += Conf.customers[cur_list.get(j)].s_time; // 服务时间
                    time += Conf.dis_matriax[cur_list.get(j)][0];
                    cost += Conf.customers[cur_list.get(j)].demand;
                }
                else
                {
                    double next_time = time - Conf.dis_matriax[cur_list.get(j-1)][0] + Conf.dis_matriax[cur_list.get(j)][cur_list.get(j-1)];
                    time = Math.max(next_time,Conf.customers[cur_list.get(j)].r_time);
                    time += Conf.dis_matriax[cur_list.get(j)][0];
                    cost += Conf.customers[cur_list.get(j)].demand;
                }
                if(cost<=Conf.Cap && time <= Conf.customers[0].d_time)//假如满足容量约束和时间约束
                {
                    if(V[cur_list.get(j)] > V[cur_list.get(j-1)] + time)
                    {
                        V[cur_list.get(j)] = V[cur_list.get(j-1)] + time;//不断更新当前最短路
                        P[cur_list.get(j)] = i;
                    }
                    j++;
                }
                if(j>Conf.N ||time >= Conf.customers[0].d_time || cost>=Conf.Cap )
                    break;

            }


        }
        Boolean [] if_arr = new Boolean[Conf.N];//是否访问过节点
        Route route = new Route();
        for(int i=1;i<=Conf.N;i++)
        {

            if(if_arr[i])
                continue;
            else
                {
                    while(true) {
                        int tmp = P[cur_list.get(i)];
                        if (tmp == 0)//假如上个点连接的是depot，新建route
                        {
                            solution.rou_list.add(route);
                            route = new Route();
                            break;
                        }
                        else // 把沿途的点设为以访问
                        {
                            if_arr[tmp] = true;
                            route.cus_list.add(tmp);//加入到route中
                        }

                    }


                }
        }




        return solution;
    }

}
