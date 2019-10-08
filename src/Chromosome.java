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
    }
    Solution toSolution()// 使用分割函数：跑一遍bellman-ford算法获得最优分割，实际上转化为从开始点到结束点的最短路划分问题
    {
        Solution solution = new Solution();
        int [] V = new int[Conf.N];//距离数组
        int j = 0; // 循环的标识
        int cost;// 当前的花费
        for(int i = 1;i<=Conf.N;i++)
            V[i] = INF;
        for(int i = 1;i<=Conf.N;i++)
        {
            cost  = 0;
            j = i;
            do
            {
                if(i == j)
                {
                    cost += Conf.dis_matriax[0][j]; // 行程
                    cost += Conf.customers[j].s_time; // 服务时间
                    cost +=
                }


            }
            while()
        }


        return solution;
    }

}
