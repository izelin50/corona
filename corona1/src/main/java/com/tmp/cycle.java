package com.tmp;

import static com.tmp.AppController.*;

public class cycle {
    public static void repeat() {
        int x, y;
        sumSpeed = 0;
        sumFood = 0;
        int[] vectorSearcher = new int[8];//вверх, в-п, право, п-н, низ, н-л, лево, л-в
        for (int i = 0; i < MAX_X; i++) {  //клетки пополняют запасы
            for (int j = 0; j < MAX_Y; j++) {
                places[i][j].food += places[i][j].speed;

                sumSpeed += places[i][j].speed;
            }
        }
        absVirus += localVirus;
        resultDied = localDied;
        localVirus = 0;
        localDied = 0;

        for (int i = 0; i < members; i++) {
            x = persons[i].x;
            y = persons[i].y;
            if (persons[i].resourses >= persons[i].expenses + persons[i].virus && persons[i].ill < 3) { //если есть ресурсы и имеет право двигаться
                persons[i].resourses = persons[i].resourses - persons[i].expenses - persons[i].virus;
                sumFood += persons[i].resourses + persons[i].expenses - persons[i].virus;
                for (int a = 0; a < 8; a++) vectorSearcher[a] = 0;
                for (int j = 0; j < persons[j].sense; j++) { //сканируем местность
                    vectorSearcher[0] += places[x][y + i].food;
                    vectorSearcher[1] += places[x + i][y + i].food;
                    vectorSearcher[2] += places[x + i][y].food;
                    if ((x - i) >= 0 && (y - i) >= 0) {
                        vectorSearcher[3] += places[x + i][y - i].food;
                        vectorSearcher[4] += places[x][y - i].food;
                        vectorSearcher[5] += places[x - i][y - i].food;
                        vectorSearcher[6] += places[x - i][y].food;
                        vectorSearcher[7] += places[x - i][y + i].food;
                    }
                }
                int a = 0;
                int b = -1;
                for (int j = 0; j < 8; j++) { //выбираем направление
                    if (vectorSearcher[j] > a) {
                        a = vectorSearcher[j];
                        b = i;
                    }//b - номер выбранного направления
                }
                int dx, dy;
                if ((x - 1) >= 0 && (y - 1) >= 0) {
                    switch (b) { //делаем ход в в выбранном направлении
                        case (-1):
                            b = (int) (Math.random() * (8));
                        case (0):
                            if (places[x][y + 1].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x][y + 1].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x][y + 1].visitor = i; //пропишемся
                                persons[i].y += 1; //переместимся
                                persons[i].resourses += places[x][y + 1].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x][y + 1].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x][y + 1].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x][y + 1].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (1):
                            if (places[x + 1][y + 1].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x + 1][y + 1].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x + 1][y + 1].visitor = i; //пропишемся
                                persons[i].y += 1;
                                persons[i].x += 1;//переместимся
                                persons[i].resourses += places[x + 1][y + 1].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x + 1][y + 1].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x + 1][y + 1].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x + 1][y + 1].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (2):
                            if (places[x + 1][y].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x + 1][y].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x + 1][y].visitor = i; //пропишемся
                                persons[i].x += 1; //переместимся
                                persons[i].resourses += places[x + 1][y].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x + 1][y].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x + 1][y].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x + 1][y].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (3):
                            if (places[x + 1][y - 1].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x + 1][y - 1].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x + 1][y - 1].visitor = i; //пропишемся
                                persons[i].y -= 1;
                                persons[i].x += 1;//переместимся
                                persons[i].resourses += places[x + 1][y - 1].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x + 1][y - 1].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x + 1][y - 1].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x + 1][y - 1].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (4):
                            if (places[x][y - 1].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x][y - 1].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x][y - 1].visitor = i; //пропишемся
                                persons[i].y -= 1; //переместимся
                                persons[i].resourses += places[x][y - 1].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x][y - 1].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x][y - 1].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x][y - 1].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (5):
                            if (places[x - 1][y - 1].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x - 1][y - 1].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x - 1][y - 1].visitor = i; //пропишемся
                                persons[i].y -= 1;
                                persons[i].x -= 1;//переместимся
                                persons[i].resourses += places[x][y + 1].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x - 1][y - 1].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x - 1][y - 1].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x - 1][y - 1].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (6):
                            if (places[x - 1][y].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x - 1][y].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x - 1][y].visitor = i; //пропишемся
                                persons[i].x -= 1; //переместимся
                                persons[i].resourses += places[x - 1][y].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x - 1][y].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x - 1][y].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x - 1][y].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (7):
                            if (places[x - 1][y + 1].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x - 1][y + 1].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x - 1][y + 1].visitor = i; //пропишемся
                                persons[i].y += 1; //переместимся
                                persons[i].resourses += places[x - 1][y + 1].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x - 1][y + 1].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x - 1][y + 1].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x - 1][y + 1].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                    }
                } else {
                    switch (b) { //делаем ход в в выбранном направлении
                        case (-1):
                            b = (int) (Math.random() * (8));
                        case (0):
                            if (places[x][y + 1].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x][y + 1].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x][y + 1].visitor = i; //пропишемся
                                persons[i].y += 1; //переместимся
                                persons[i].resourses += places[x][y + 1].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x][y + 1].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x][y + 1].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x][y + 1].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (1):
                            if (places[x + 1][y + 1].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x + 1][y + 1].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x + 1][y + 1].visitor = i; //пропишемся
                                persons[i].y += 1;
                                persons[i].x += 1;//переместимся
                                persons[i].resourses += places[x + 1][y + 1].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x + 1][y + 1].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x + 1][y + 1].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x + 1][y + 1].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                        case (2):
                            if (places[x + 1][y].status == false) { //когда клетка свободна
                                places[x][y].status = false; //разбронируем место
                                places[x + 1][y].status = true; //забронируем место
                                places[x][y].visitor = -1; //выпишемся
                                places[x + 1][y].visitor = i; //пропишемся
                                persons[i].x += 1; //переместимся
                                persons[i].resourses += places[x + 1][y].food; //поедим
                            } else { //в выбранном направлении на соседней клетке кто-то стоит
                                if (persons[i].virus > persons[places[x + 1][y].visitor].virus) { //заражаемся или заражаем
                                    persons[places[x + 1][y].visitor].virus = persons[i].virus;
                                } else {
                                    persons[i].virus = persons[places[x + 1][y].visitor].virus;
                                }
                                persons[i].resourses += places[x][y].food; //поедим
                            }
                            break;
                    }
                }


            } else if (persons[i].ill < 3) {
                persons[i].resourses += places[x][y].food;
                persons[i].ill += 1;
            } else {
                persons[i].resourses = -1;
                persons[i].virus = 0;
                places[persons[i].x][persons[i].y].status = false;
                places[persons[i].x][persons[i].y].visitor = -1;
            }


            localVirus += persons[i].virus;
            if (persons[i].ill >= 3) localDied++;


        }





    }
}
