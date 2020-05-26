<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Корона-симулятор</title>
    <script>
        function view(n) {
            style = document.getElementById(n).style;
            style.display = (style.display == 'block') ? 'none' : 'block';
        }
    </script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" type="text/javascript"></script>
    <script src="https://code.highcharts.com/highcharts.js" type="text/javascript"></script>

    <script type="text/javascript">
        var virusUp = [${virus1}, ${virus2}, ${virus3}, ${virus4}, ${virus5}, ${virus6}, ${virus7}, ${virus8}, ${virus9}, ${virus10}];
        var dieUp = [${died1}, ${died2}, ${died3}, ${died4}, ${died5}, ${died6}, ${died7}, ${died8}, ${died9}, ${died10}];
        var food = [${food1}, ${food2}, ${food3}, ${food4}, ${food5}, ${food6}, ${food7}, ${food8}, ${food9}, ${food10}];
    </script>
    <script type="text/javascript">
        var chart1;

        $(document).ready(function () {
            chart1 = new Highcharts.Chart({
                title: {text: 'Разброс по циклам'},
                chart: {renderTo: 'container1'},
                series: [{data: virusUp, name: 'Заболеваемость популяции'}, {data: dieUp, name: 'Общая смертность'},
                    {data: food, name: 'Рациональность потребления'}]
            });
        });
    </script>
</head>
<body background="resources/background.png">
<font face="arial">
    <center>
        <div id="content" style=" padding-top: 0; width: 50%; background-color: white; height: 100%;">
            <h1 style="padding-top: 7%">Привет! Добро пожаловать на корона-симулятор!</h1>

            <h3><a href="#hidden1" style="color:black;" onclick="view('hidden1'); return false">Здесь есть небольшой
                мануал</a></h3>
            <div id="hidden1" style="display: none; text-align: left;">
                <strike>
                    &nbsp;&nbsp;&nbsp;&nbsp;Здесь должно бы было быть нечто вроде "очень лестно, что вы зашли...", но
                    это неправда,
                    поэтому перейдём сразу к делу.
                </strike>
                <br>
                &nbsp;&nbsp;&nbsp;&nbsp;Итак, перед вами самый что ни на есть настоящий корона-симулятор. Он отражает
                процессы, происходящие в
                корона-мире. Жители корона-мира могут питаться, передвигаться, заражаться вирусом и заражать им других
                жителей, болеть, умирать... список можно продолжать долго.<br>
                &nbsp;&nbsp;&nbsp;&nbsp;Время в корона-мире измеряется циклами. По окончании цикла подводятся итоги:
                смертность, концентрация вируса в популяции. Средние за цикл данные по этим параметрам отображаются на
                графиках.<br>
                &nbsp;&nbsp;&nbsp;&nbsp;<b>По поводу "средней заболеваемости":</b> величина эта отражает среднее
                значение заболеваемости <b>живого</b> населения во всех циклах. Заболеваемость популяции отражает его в
                конкретном цикле<br>
                &nbsp;&nbsp;&nbsp;&nbsp;Подробнее о работе модели вы можете почитать
                <a href="resources/allabout.pdf" style="color: black">здесь</a>, ну а теперь приступайте к
                тестированию!

            </div>

            <h3><a href="#hidden3" style="color: black;" onclick="view('hidden3'); return false">Субъективные выводы по
                работе проекта</a></h3>
            <div id="hidden3" style="color: black; display: none;" onclick="view('hidden3'); return false">
                &nbsp;&nbsp;&nbsp;&nbsp;Проект работает очень малый промежуток времени, поэтому пока что проанализирована
                не вся статистика, однако выявлены интересные закономерности:<br>
                1. Ресурсы многократно превышают потребности популяции, однако используются эффективно лишь в критической
                ситуации <br>
                2. С увеличением смертности уменьшается общий уровень "болезни" популяции. Как будто бы очевидно, но ещё
                раз наводит на мысль о трактовке фразы "число заболевших уменьшилось".<br>
                3. При малой скорости возобнвления ресурсов смертность действительно растёт по экспотенциальному закону,
                что говорит о кричащей необходимости наращивания этой скорости
            </div>

            <h3><a href="#hidden2" style="color: black;" onclick="view('hidden2'); return false">А здесь сам
                симулятор</a></h3>
            <div id="hidden2" style="display: none;">

                <h2>Введите, пожалуйста, количество циклов и участников</h2>
                <form method="post" action="input">
                    <input min="3" type="number" onkeyup="this.value = this.value.replace(/[^\d]/g,'');" name="time" required
                           placeholder="Продолжительность"></input>
                    <input min="2" type="number" onkeyup="this.value = this.value.replace(/[^\d]/g,'');" name="members" required
                           placeholder="Количество участников"></input>
                    <a href="#hidden4" style="color: black;" onclick="view('hidden4'); return false">Показать ещё</a></h3>
                    <div id="hidden4" style="display: none;"><br>
                        Ниже вы можете изменить максимальное значение величин, рандомно задающихся для генерирующих клеток
                        и участников<br>
                        <input min="1" type="number" onkeyup="this.value = this.value.replace(/[^\d]/g,'');" name="MAX_RESOURSES" required
                               value="10" placeholder="Ресурсы сначала"></input>
                        <input min="0" type="number" onkeyup="this.value = this.value.replace(/[^\d]/g,'');" name="MAX_EXPENSES" required
                               value="10" placeholder="Метаболизм"></input>
                        <input min="1" type="number" onkeyup="this.value = this.value.replace(/[^\d]/g,'');" name="MAX_SENSE" required
                               value="10" placeholder="Нюх"></input>
                        <input min="0" type="number" onkeyup="this.value = this.value.replace(/[^\d]/g,'');" name="MAX_FOOD" required
                              value="10" placeholder="Еда сначала"></input>
                        <input min="1" type="number" onkeyup="this.value = this.value.replace(/[^\d]/g,'');" name="MIN_SPEED" required
                               value="10" placeholder="Скорость генерации"></input>
                        <input min="1" type="number" onkeyup="this.value = this.value.replace(/[^\d]/g,'');" name="MAX_VIRUS" required
                               value="10" placeholder="Вирус сначала"></input>
                    </div><br>
                    <button type="submit">Отправить</button>
                </form>

                Помните, пожалуйста, что общество состоит из n>=2 человек!
            </div>
            <br><br>
            <div style="visibility: ${visibility};">
                <h1>Результаты:</h1>
                <table border="1">
                    <tr>
                        <td>Количество умерших</td>
                        <td>Средняя заболеваемость</td>
                    </tr>
                    <tr>
                        <td>${died}</td>
                        <td>${virus}</td>
                    </tr>
                </table>
                <div id="container1" style="width: 700px; height: 400px "></div>


            </div>

            <br><br>

        </div>
    </center>
</font>
</body>
</html>