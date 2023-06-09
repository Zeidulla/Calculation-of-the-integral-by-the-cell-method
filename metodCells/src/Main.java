public class Main {
    public static void main(String[] args){
        System.out.println("Дан двойной определённый интеграл с подынтегральной функцией f(x, y) = sin(x + y), x ∈ [1, 6], y ∈ [4, 7]");
        System.out.println("Метод ячеек:");

        double a1 = 1, b1 = 6, a2 = 4, b2 = 7, a3 = 0, b3 = 0; // Задаются пределы интегрирования
        methodCellsTwo(a1, b1, a2, b2); // Вызывается метод ячеек для двойного интеграла

        System.out.println("Дан тройной определённый интеграл с подынтегральной функцией f(x, y, z) = sin(x + y + z), x ∈ [1, 2], y ∈ [2, 3], z ∈ [3, 4]");
        System.out.println("Метод ячеек:");
        a1 = 1; // Задаются новые пределы интегрирования
        b1 = 2;
        a2 = 2;
        b2 = 3;
        a3 = 3;
        b3 = 4;
        methodCellsThree(a1, b1, a2, b2, a3, b3); // Вызывается метод ячеек для тройного интеграла
    }

    public static void methodCellsTwo(double a1, double b1, double a2, double b2){ // Метод для вычисления двойного определённого интеграла методом ячеек
        int n = 100; // Здесь количество разбиений сетки по одной из осей координат, соответственно всего будет n * n = 10000 квадратных ячеек в области разбиения
        double x[] = new double[n + 1]; // Массивы для значений точек, лежащих на осях координат, отстоящих друг от друга на расстоянии шага
        double y[] = new double[n + 1];
        x[0] = a1;
        x[n] = b1;
        y[0] = a2;
        y[n] = b2;
        double h1 = (x[n] - x[0]) / n; // Шаги разбиения
        double h2 = (y[n] - y[0]) / n;
        double sum = 0;

        for (int i = 1; i <= n; i++){
            x[i] = x[i - 1] + h1;
            y[i] = y[i - 1] + h2;
        }

        System.out.println(" x\t\t|Сумма");
        System.out.println(String.format("%.1f", x[0]) + "\t\t|" + String.format("%.5f", sum));
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                sum += h1 * h2 * func1((x[i - 1] + x[i]) / 2, (y[j - 1] + y[j]) / 2); // Суммирование значений середины каждой ячейки, умноженных на площадь этой ячейки
            }
            if (i % 10 == 0) // Поскольку значений суммы всего 10000, выводится каждое 1000 значение суммы
//            if(i % 1000 == 0)
                System.out.println(String.format("%.1f", x[i]) + "\t\t|" + String.format("%.5f", sum));
        }
        System.out.println("\nЗначение двойного интеграла равно " + String.format("%.5f", sum) + "\n");
    }

    public static void methodCellsThree(double a1, double b1, double a2, double b2, double a3, double b3){ // Метод для вычисления тройного определённого интеграла методом ячеек
        int n = 10; // Здесь количество разбиений сетки по одной из осей координат, соответственно всего будет n * n * n = 1000 кубических ячеек в области разбиения
        double x[] = new double[n + 1]; // Массивы для значений точек, лежащих на осях координат, отстоящих друг от друга на расстоянии шага
        double y[] = new double[n + 1];
        double z[] = new double[n + 1];
        x[0] = a1;
        x[n] = b1;
        y[0] = a2;
        y[n] = b2;
        z[0] = a3;
        z[n] = b3;
        double h1 = (x[n] - x[0]) / n; // Шаги разбиения
        double h2 = (y[n] - y[0]) / n;
        double h3 = (z[n] - z[0]) / n;
        double sum = 0;

        for (int i = 1; i <= n; i++){
            x[i] = x[i - 1] + h1;
            y[i] = y[i - 1] + h2;
            z[i] = z[i - 1] + h3;
        }

        System.out.println(" x\t\t|Сумма");
        System.out.println(String.format("%.1f", x[0]) + "\t\t|" + String.format("%.5f", sum));
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                for (int k = 1; k <= n; k++) {
                    sum += h1 * h2 * h3 * func2((x[i - 1] + x[i]) / 2, (y[j - 1] + y[j]) / 2, (z[k - 1] + z[k]) / 2); // Суммирование значений середины каждой кубической ячейки, умноженных на объём этой ячейки
                }
            }
//            if (i % 100 == 0)
                System.out.println(String.format("%.1f", x[i]) + "\t\t|" + String.format("%.5f", sum));
        }
        System.out.println("\nЗначение тройного интеграла равно " + String.format("%.5f", sum) + "\n");
    }

    public static double func1(double x, double y){ // Подынтегральная функция для двойного интеграла
        return Math.sin(x + y);
    }

    public static double func2(double x, double y, double z){ // Подынтегральная функция для тройного интеграла
        return Math.sin(x + y + z);
    }
}
