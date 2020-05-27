package com.tmp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class AppController {

    public static  int MAX_RESOURSES = 10;
    public static  int MAX_EXPENSES = 10;
    public static  int MAX_SENSE = 10;
    public static  int MAX_VIRUS = 10;
    public static  int MAX_X = 100;
    public static  int MAX_Y = 100;
    public static  int MAX_FOOD = 10;
    public static  int MIN_SPEED = 10;
    public static int sumFood = 0;
    public static int sumSpeed =0;
    public static int members;
    public static int time;
    public static int resultDied = 0;
    public static int localDied = 0;
    public static int absVirus = 0;
    public static int localVirus = 0;
    public static person[] persons;
    public static place[][] places;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("visibility", "hidden");
        model.put("color", "red");
        return "main";
    }

    @PostMapping("input")
    public String input(Map<String, Object> model, @RequestParam Integer time, @RequestParam Integer members, Integer MAX_EXPENSES
    , Integer MAX_FOOD, Integer MAX_RESOURSES, Integer MAX_SENSE, Integer MIN_SPEED, Integer MAX_VIRUS) {
        model.put("color", "green");
        this.time = time;
        this.members = members;
        this.MAX_EXPENSES = MAX_EXPENSES;
        this.MAX_RESOURSES = MAX_RESOURSES;
        this.MAX_FOOD = MAX_FOOD;
        this.MAX_SENSE = MAX_SENSE;
        this.MAX_VIRUS = MAX_VIRUS;
        this.MIN_SPEED = MIN_SPEED;
        initial.initialize();
        disposition.create();
        for (int i = 0; i < time; i++) {
            cycle.repeat();
            localVirus*=10;
            localVirus /= members;
            sumFood*=1000;
            sumFood /= sumSpeed;

            model.put("food" + i, sumFood);
            model.put("virus" + i, localVirus);
            model.put("died" + i, localDied);
        }

        absVirus /= members;

        model.put("died", resultDied);
        model.put("virus", absVirus);
        return "main";
    }
}
