package com.example.deepmeditation.myapplication;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Deep Meditation on 11/26/2014.
 */
public class Pallete {
    HashMap palletes = new HashMap<String, int[]>();

    Pallete(){
        palletes.put("fancy",new int[]{0xFFB4EEB4, 0xff5be7a7,0xffff7373,0xffb5cbbb,0xff,0xffcbbeb5,0xff0099cc});
        palletes.put("grey",new int[]{0xFFBAB6AF,0xff7A7873,0xff3A3937,0xffC7C2BB,0xffA09D97,0xff605E5B,0xffECE8DF});
        palletes.put("mix",new int[]{0xFF3E867B,0xff448BB9,0xffCCE0EC,0xff936F69,0xffB95F5F,0xffEC8A62,0xff531F09});
    }

    int[] getColors(String theme){
        return (int[]) this.palletes.get(theme);
    }

}
