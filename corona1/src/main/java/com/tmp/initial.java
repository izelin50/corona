package com.tmp;

import org.springframework.web.bind.annotation.RequestParam;

import static com.tmp.AppController.*;

public class initial {
    public static void initialize() {
        persons = new person[members];
        places = new place[MAX_X][MAX_Y];
        for (int i=0; i<members; i++){
            persons[i] = new person() ;
        }
        for (int j=0; j<MAX_X; j++){
            for (int u=0; u<MAX_Y; u++){
                places[j][u]= new place();
            }
        }
        for (int i = 0; i < members; i++) {
            persons[i].resourses = (int) (Math.random() * (MAX_RESOURSES) + 1);
            persons[i].expenses = (int) (Math.random() * (MAX_EXPENSES) + 1);
            persons[i].sense = (int) (Math.random() * (MAX_SENSE) + 1);
            persons[i].virus = (int) (Math.random() * (MAX_VIRUS) + 1);
            persons[i].x = (int) (Math.random() * (MAX_X) + 1);
            persons[i].y = (int) (Math.random() * (MAX_Y) + 1);
        }
        for (int x = 0; x < MAX_X; x++) {
            for (int y = 0; y < MAX_Y; y++) {
                places[x][y].food = (int) (Math.random() * (MAX_FOOD) + 1);
                places[x][y].speed = (int) (Math.random() * (MIN_SPEED) + 1);
            }
        }
    }
}
