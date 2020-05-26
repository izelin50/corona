package com.tmp;

import static com.tmp.AppController.*;

public class disposition {
    public static void create() {
        int x;
        int y;
        for (int i = 0; i < members; i++) {
            boolean checker = false;
            x = 0;
            y = 0;
            while (checker = false) {
                x = 1 + (int) (Math.random() * (MAX_X + 1));
                y = 1 + (int) (Math.random() * (MAX_Y + 1));
                if (places[x][y].status == false) checker = true;
            }
            persons[i].x = x;
            persons[i].y = y;
        }
    }
}
