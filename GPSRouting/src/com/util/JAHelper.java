package com.util;

import net.sf.json.JSONArray;

/**
 * Created by XinLi on 16/6/22.
 */
public class JAHelper {

    static public  int GetCount(String type, JSONArray JA){
        int count=0;
        for(int i = 0; i<JA.size();i++){
            if(type.equals(JA.getJSONObject(i).getString("type"))){
                count++;
            }
        }
        return count;
    }
}
