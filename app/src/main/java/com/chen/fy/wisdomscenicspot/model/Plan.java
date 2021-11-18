package com.chen.fy.wisdomscenicspot.model;
import java.util.Random;
public class Plan {

    /**
     * 年轻 人文 一日游
     */
    public static  Object[][]his={{"上海宋庆龄故居纪念馆",0,39.99769949,116.4756576},
            {"上海豫园",634,39.88816697,116.2377458},
            {"东方明珠广播电视塔",2821,40.0615841,116.086286},
            {"上海城隍庙",100,39.910843,116.417403},
            {"上海宝山国际民间艺术博览馆景区",145,31.35046021,121.3956044}

    };
    public static  Object[][]pop={{"上海杜莎夫人蜡像馆",109,39.97334263,116.4174031},
        {"周浦花海",283,39.8754278,116.5042406},
        {"东方明珠广播电视塔",2821,40.0615841,116.086286},
            {"上海国际旅游度假区--迪士尼乐园",0,40.65086498,117.273062},
            {"上海国际旅游度假区--生态园",0,40.65086498,117.273062},
            {"上海国际赛车场旅游景区",104,40.65086498,117.273062},
            {"上海国际时尚中心",129,40.01139092,116.4978904},
            {"上海中心",0,39.96000424,116.4802319},
            {"外滩",100,39.90,116.417},
            {"上海国际旅游度假区",11229,40.65086498,117.273062}

    };
    public static  Object[][]study={{"上海中医药博物馆",41,39.91092455,116.4133837},
    {"上海自然博物馆（上海科技馆分馆",23,39.97334263,116.4174031},
    {"上海野生动物园",1461,39.97334263,116.4174031},
    {"上海科技馆",0,30.73845584,121.382658},
    {"上海市青少年校外活动营地——东方绿舟",1688,31.10062988,121.198551},
    {"上海海洋水族馆",0,30.99034281,121.335761},
    {"上海韩湘水博园",0,39.9684611,116.429979}
    };

    public static  Object[][]realx={{"黄兴公园",500,39.91092455,116.4133837},
            {"上海都市菜园景区",34,39.97334263,116.4174031},
            {"上海海湾国家森林公园",106,39.97334263,116.4174031},
            {"上海鲜花港",34,39.97334263,116.4174031},
            {"上海田子坊景区",523,31.25092357,121.4880908}
    };

    public static  Object[][]nature={{"上海佘山国家森林公园·东佘山园",81,39.96670684,116.4578886},
            {"上海佘山国家森林公园·西佘山园",	106,40.34971033,116.5647884},
            {"上海海湾国家森林公园",106,39.97334263,116.4174031},
            {"上海明珠湖·西沙湿地景区",278,39.77096012,116.341114},
            {"上海东林寺景区",0,39.91092455,116.4133837}
    };

    public static Object[][] plan_travel(float day)
    {
        Random r = new Random();
        if(day==1)
        {
            Object[][]plan=new Object[4][2];
            int i = r.nextInt(4);
            plan[0][0]=his[i][0];plan[0][1]=his[i][2];plan[0][2]=his[i][3];
            i = r.nextInt(9);
            plan[0][0]=pop[i][0];plan[0][1]=pop[i][2];plan[0][2]=pop[i][3];
            i = r.nextInt(9);
            plan[0][0]=pop[i][0];plan[0][1]=pop[i][2];plan[0][2]=pop[i][3];
            return plan;
        }
        if(day==2||day==1.5||day==2.5)
        {
            Object[][]plan=new Object[6][2];
            int i = r.nextInt(4);
            plan[0][0]=his[i][0];plan[0][1]=his[i][2];plan[0][2]=his[i][3];
            i = r.nextInt(9);
            plan[0][0]=pop[i][0];plan[0][1]=pop[i][2];plan[0][2]=pop[i][3];
            i = r.nextInt(7);
            plan[0][0]=study[i][0];plan[0][1]=study[i][2];plan[0][2]=study[i][3];
            i = r.nextInt(4);
            plan[0][0]=nature[i][0];plan[0][1]=nature[i][2];plan[0][2]=nature[i][3];
            i = r.nextInt(4);
            plan[0][0]= realx[i][0];plan[0][1]=realx[i][2];plan[0][2]=realx[i][3];
            i = r.nextInt(4);
            plan[0][0]=his[i][0];plan[0][1]=his[i][2];plan[0][2]=his[i][3];
            return plan;
        }
        else
        {
            Object[][]plan=new Object[8][2];
            int i = r.nextInt(4);
            plan[0][0]=nature[i][0];plan[0][1]=nature[i][2];plan[0][2]=nature[i][3];
            i = r.nextInt(4);
            plan[0][0]= realx[i][0];plan[0][1]=realx[i][2];plan[0][2]=realx[i][3];
            i = r.nextInt(4);
            plan[0][0]=his[i][0];plan[0][1]=his[i][2];plan[0][2]=his[i][3];
            i = r.nextInt(4);
            plan[0][0]=his[i][0];plan[0][1]=his[i][2];plan[0][2]=his[i][3];
            i = r.nextInt(9);
            plan[0][0]=pop[i][0];plan[0][1]=pop[i][2];plan[0][2]=pop[i][3];
            i = r.nextInt(7);
            plan[0][0]=study[i][0];plan[0][1]=study[i][2];plan[0][2]=study[i][3];
            i++;
            plan[0][0]=pop[i][0];plan[0][1]=pop[i][2];plan[0][2]=pop[i][3];
            i++;
            return plan;
        }

    }

}