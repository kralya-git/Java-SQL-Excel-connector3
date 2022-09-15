

package com.test.idea;

//импортируем библиотеки для работы с excel
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
//для работы с потоками (будем использовать в блоке с excel)
import  java.io.FileOutputStream;
//для работы с массивами данных
import java.util.Arrays;
//для считывания данных с клавиатуры
import java.util.Scanner;
//для работы с sql
import com.mysql.cj.jdbc.Driver;
//в особенности потом понадобятся Connection, ResultSet и Statement
import java.sql.*;


//главный класс
public class int_or_not_database {

    //точка входа в программу + вывод информации об ошибках с бд
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //классу scanner присваиваем в качестве аргумента system.in
        Scanner scan = new Scanner(System.in);

        //начальное значение для switch case
        int x = 0;
        String s = "";

        //ввод названия таблицы с клавиатуры
        System.out.println("Введите название таблицы: ");
        String tablename = scan.nextLine();

        //цикл работает до тех пор, пока пользователь не введет 5
        while (!"5".equals(s)) {
            System.out.println();
            System.out.println("1. Вывести все таблицы из текущей БД.");
            System.out.println("2. Создать таблицу в БД.");
            System.out.println("3. Добавить данные в таблицу.");
            System.out.println("4. Сохранить данные в Excel.");
            System.out.println("5. Выйти из программы.");
            s = scan.next();

            //пробуем перевести пользовательский ввод в int
            try {
                x = Integer.parseInt(s);
            }
            //выдаем сообщение об ошибке ввода, и так до тех пор, пока пользователь не введет число
            catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода.");
            }

            //оператор switch для множества развилок
            switch (x) {

                //если пользователь ввел цифру 1, то...
                case 1 -> {

                    //регистрируем драйвер для дальнейшей работы (управление jdbc)
                    DriverManager.registerDriver(new Driver());

                    //имя драйвера
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //пытаемся установить соединение с заданным url бд
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");
                    System.out.println("Успешно законнектились к БД!");

                    //statement для выполнения sql запросов
                    //соответственно, createStatement создает этот объект для работы с бд
                    Statement stmt = con.createStatement();

                    //ResultSet - объект java, содержащий результаты выполнения sql запросов
                    ResultSet rs = stmt.executeQuery("Show tables");
                    System.out.println("Таблицы из текущей БД: ");

                    //rs.next() - построчный вывод названий таблиц в цикле
                    while (rs.next()) {
                        System.out.print(rs.getString(1));
                        System.out.println();
                    }
                }

                //если пользователь ввел цифру 2, то...
                case 2 -> {

                    //регистрируем драйвер для дальнейшей работы (управление jdbc)
                    DriverManager.registerDriver(new Driver());

                    //имя драйвера
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //пытаемся установить соединение с заданным url бд
                    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");
                    System.out.println("Успешно законнектились к БД!");

                    //statement для выполнения sql запросов
                    //соответственно, createStatement создает этот объект для работы с бд
                    Statement stmt1 = con1.createStatement();

                    //задаем запрос СОЗДАНИЯ, как строку
                    String query = "CREATE TABLE IF NOT EXISTS " + tablename +
                            " (числа varchar(255), целочисленность varchar(255), " +
                            "четность varchar(255))";

                    //отправляем серверу бд sql-выражение
                    //вызваем метод executeQuery объекта Statement и в качестве аргумента передаем скрипт запроса
                    stmt1.executeUpdate(query);

                    //ResultSet - объект java, содержащий результаты выполнения sql запросов
                    ResultSet rs1 = stmt1.executeQuery("Show tables");
                    System.out.println("Таблицы из текущей БД: ");

                    //rs1.next() - построчный вывод названий таблиц в цикле
                    while (rs1.next()) {
                        System.out.print(rs1.getString(1));
                        System.out.println();
                    }
                }

                //если пользователь ввел цифу 3, то...
                case 3 -> {

                    //регистрируем драйвер для дальнейшей работы (управление jdbc)
                    DriverManager.registerDriver(new Driver());

                    //имя драйвера
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    //пытаемся установить соединение с заданным url бд
                    Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");
                    System.out.println("Успешно законнектились к БД!");

                    //nextLine() читает всю текущую строки и возвращает всё, что было в этой строке
                    scan.nextLine();

                    //вводим с клавиатуры количество чисел
                    System.out.println("Введите количество чисел: ");
                    int size = scan.nextInt();

                    //создаем три массива для хранения ряда чисел
                    //результатов их проверки на целочисленность
                    //и результатов их проверки на четность
                    String[] array = new String[size + 1];
                    String[] array_integer = new String[size + 1];
                    String[] array_parity = new String[size + 1];

                    System.out.println("Введите числа: ");

                    //вводим числа
                    //в случае ошибки ввода, выводит сообщение
                    //??не выводит??
                    for (int i = 0; i <= size; i++) {
                        try {
                            array[i] = scan.nextLine();
                        } catch (NumberFormatException e) {
                            System.out.println("Вы вводите что-то не так.");
                        }
                    }

                    //проверка на целочисленность
                    for (int i = 1; i <= size; i++) {
                        try {
                            int arrayi = Integer.parseInt(array[i].trim());
                            array_integer[i] = arrayi + " - целочисленное";
                        } catch (NumberFormatException nfe) {
                            array_integer[i] = array[i] + " - нецелочисленное";
                        }
                    }

                    //проверка на четность
                    for (int i = 1; i <= size; i++) {
                        try {
                            int arrayi = Integer.parseInt(array[i].trim());
                            if ((arrayi % 2) == 0) {
                                array_parity[i] = arrayi + " - четное";
                            } else {
                                array_parity[i] = arrayi + " - нечетное";
                            }
                        } catch (NumberFormatException nfe) {
                            array_parity[i] = array[i] + " - нецелочисленное";
                        }
                    }

                    for (int r = 1; r <= size; r++) {
                        //задаем запрос ЗАПОЛНЕНИЯ, как строку
                        String query2 = "INSERT INTO " + tablename +
                                " (числа, целочисленность, четность)" + " VALUES (?, ?, ?);";

                        //PreparedStatement:
                        //заранее подготавливает запрос с указанием мест, где будут подставляться параметры (знаки '?')
                        //устанавливает параметры определенного типа
                        //и выполняет после этого запрос с уже установленными параметрами
                        PreparedStatement stmt3 = con2.prepareStatement(query2);

                        //установка параметров
                        stmt3.setString(1, array[r]);
                        stmt3.setString(2, array_integer[r]);
                        stmt3.setString(3, array_parity[r]);

                        //выполнение запроса
                        //вызов stmt.executeUpdate() выполняется уже без указания строки запроса
                        stmt3.executeUpdate();

                        System.out.println("Данные в MySQL успешно внесены!");

                        //ResultSet - объект java, содержащий результаты выполнения sql запросов
                        ResultSet rs2 = stmt3.executeQuery("SELECT * FROM " + tablename + "");
                        System.out.println("Введенные данные: ");

                        //statement для выполнения sql запросов
                        Statement statement = con2.createStatement();

                        //ResultSet - объект java, содержащий результаты выполнения sql запросов
                        ResultSet set = statement.executeQuery("SELECT * FROM " + tablename + " LIMIT 0;");

                        //ResultSetMetaData содержит информацию о результирующей таблице
                        //- количество колонок, тип значений колонок и т.д.
                        ResultSetMetaData data = set.getMetaData();

                        //определяем количество колонок
                        int cnt = data.getColumnCount();

                        //выводим названия колонок через пробел
                        for (int i = 1; i <= cnt; i++) {
                            System.out.print(data.getColumnName(i) + " ");
                        }
                        System.out.print("\n");

                        //rs2.next() - построчный вывод введенных данных в цикле
                        while (rs2.next()) {
                            for (int i = 1; i <= cnt; i++) {
                                System.out.print(Arrays.toString(rs2.getString(i).split("   ")));
                            }
                            System.out.println();
                        }

                        ///вывод количества строк в таблице
                        //создаем sql запрос
                        String query = "select count(*) from " + tablename;

                        //пробуем выполнить запрос через try - catch
                        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");
                             Statement stmt = con.createStatement();
                             ResultSet rs = stmt.executeQuery(query)) {
                            while (rs.next()) {
                                int count = rs.getInt(1);
                                System.out.println("Всего внесено строк в таблицу " + tablename + " : " + count);
                            }
                        } catch (SQLException sqlEx) {
                            sqlEx.printStackTrace();
                        }
                    }
                }
                //если пользователь ввел цифру 4, то...
                case 4 -> {

                    //реализуем через try - catch, чтобы программы не руинилась в случае ошибки
                    try{

                        //создаем название excel файла с учетом введеного имени таблицы
                        String filename="c:/" + tablename + ".xls" ;

                        //создаём объект HSSFWorkBook
                        HSSFWorkbook hwb = new HSSFWorkbook();

                        //создаём лист, используя на объекте, созданном в предыдущем шаге, createSheet()
                        HSSFSheet sheet =  hwb.createSheet("new sheet");

                        //создаём на листе строку, используя createRow()
                        HSSFRow rowhead = sheet.createRow((short)0);

                        //создаём в строке ячейку — createCell()
                        //задаём значение ячейки через setCellValue()
                        rowhead.createCell((short) 0).setCellValue("числа");
                        rowhead.createCell((short) 1).setCellValue("целочисленность");
                        rowhead.createCell((short) 2).setCellValue("четность");

                        //имя драйвера
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        //пытаемся установить соединение с заданным url бд
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "кщще");

                        //statement для выполнения sql запросов
                        Statement st = con.createStatement();

                        //ResultSet - объект java, содержащий результаты выполнения sql запросов
                        ResultSet rs = st.executeQuery("Select * from " + tablename);

                        //начальное значение i для while
                        int i = 1;

                        //создаём в строке ячейку — createCell()
                        //задаём значение ячейки через setCellValue()
                        //и всё это через цикл, чтобы заполнить все строчки
                        while(rs.next()){
                            HSSFRow row = sheet.createRow((short) i);
                            row.createCell((short) 0).setCellValue(rs.getString("числа"));
                            row.createCell((short) 1).setCellValue(rs.getString("целочисленность"));
                            row.createCell((short) 2).setCellValue(rs.getString("четность"));
                            i++;
                        }
                        //записываем workbook в file через FileOutputStream
                        FileOutputStream fileOut = new FileOutputStream(filename);

                        //записывает строки в файл
                        hwb.write(fileOut);

                        //закрываем workbook, вызывая close()
                        fileOut.close();
                        System.out.println("Ваш файл Excel успешно сгенерирован!");
                    }

                    //если что-то пойде не так, программа выведет тект ошибки, но не ошибку
                    catch ( Exception ex ) {
                        System.out.println(ex);
                    }
                }
            }
        }
        //если пользователь введет 5, то выйдет из программы
        System.out.println("Вышли из нашей программы.");
    }
}
