package com.example.anast.ewnp;

/**
 * Created by anast on 02.03.2016.
 */
public class Check {
    public static boolean rus(String s)
    {
        if(s.length() == 0)
            return false;
        for(int i = 0; i < s.length(); i++)
        {
            if((s.charAt(i) < 'а' || s.charAt(i)> 'я') && (s.charAt(i) != ' '))
                return false;
        }
        return true;
    }

    public static boolean eng(String s)
    {
        if(s.length() == 0)
            return false;
        for(int i = 0; i < s.length(); i++)
        {
            if((s.charAt(i) < 'a' || s.charAt(i)> 'z')&& (s.charAt(i) != ' '))
                return false;
        }
        return true;
    }

    public static String[] parse(String s)
    {
        s += " ";
        boolean flag = false;
        String[] res = new String[2];
        String a = "";
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) != ' ')
            {
                a += s.charAt(i);
            }
            else
            {
                if(!flag)
                {
                    flag = true;
                    res[1] = a;
                    a = "";
                    i += 2;
                }
                else
                {
                    res[0] = a;
                }
            }
        }
        return res;
    }
}
