import java.io.IOException;
import java.text.DecimalFormat;

public class Main {

    static class Teste {

        public static volatile Long x = 0l;
        public static volatile Long y = 0l;
        public static volatile Long z = 0l;
        public static volatile Long x0 = 0l;
        public static Long x1 = 0l;
        public static Long x2 = 0l;
        public static Long x3 = 0l;
        public static Long x4 = 0l;
        public static Long x6 = 0l;
        public static Long x7 = 0l;
        public static Long x8 = 0l;
        public static Long x9 = 0l;
        public static Long x10 = 0l;
        public static Long x11 = 0l;
        public static Long x12 = 0l;
        public static Long x13 = 0l;
        public static Long x14 = 0l;
        public static Long x16 = 0l;
        public static Long x17 = 0l;
        public static Long x18 = 0l;
        public static Long x19 = 0l;
        public static Long x20 = 0l;
        public static volatile Long y0 = 0l;
        public static Long y1 = 0l;
        public static Long y2 = 0l;
        public static Long y3 = 0l;
        public static Long y4 = 0l;
        public static Long y5 = 0l;
        public static Long y6 = 0l;
        public static Long y7 = 0l;
        public static Long y8 = 0l;
        public static Long y9 = 0l;
        public static Long y10 = 0l;
        public static Long y11 = 0l;
        public static Long y12 = 0l;
        public static Long y13 = 0l;
        public static Long y14 = 0l;
        public static Long y15 = 0l;
        public static Long y16 = 0l;
        public static Long y17 = 0l;
        public static Long y18 = 0l;
        public static Long y19 = 0l;
        public static Long y20 = 0l;
        public static volatile Long z0= 0l;
        public static Long z1 = 0l;

    }

    public Teste teste = new Teste();

    public static void main(String[] args) throws InterruptedException, IOException {


        Long beginTime = 0l;
        Long endTime = 0l;
        Long teste1 = 0l;
        Long teste2 = 0l;
        Long teste3 = 0l;

        System.out.println("Vamos brincar com alguns testes de performance em Java");
        System.in.read();
        //SIZE
        System.out.println("-------------------------------TESTE DE TAMANHO DE LOOPS E MÉTODOS------------------------------------\n");
        System.out.println("Primeiro vamos executar um método que executa 70 operações matemáticas em um único loop de 1 milhão de iterações");
        System.out.println("Ele tem 72 linhas.");
        System.in.read();
        System.out.println("Iniciando...");
        beginTime = System.currentTimeMillis();
        testeMalucoGrande();
        endTime = System.currentTimeMillis();
        teste1 = endTime - beginTime;
        System.out.println("Finalizado. O teste levou " + teste1 + "ms");
        System.in.read();

        System.out.println("Agora vamos executar o mesmo número operações, mas chamando 7 vzs um método que realiza 10 operações");
        System.out.println("Isso totaliza 7 milhões de iterações.");
        System.in.read();
        System.out.println("Iniciando...");
        beginTime = System.currentTimeMillis();
        testeMalucoPequeno1();
        testeMalucoPequeno1();
        testeMalucoPequeno1();
        testeMalucoPequeno1();
        testeMalucoPequeno1();
        testeMalucoPequeno1();
        testeMalucoPequeno1();
        endTime = System.currentTimeMillis();
        teste2 = endTime - beginTime;
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("Terminou, duração do teste: " + teste2 + "ms. O segundo cenário foi " + (teste1 - teste2) + "ms mais rápido que o primeiro.");
        System.out.println("Ou seja, uma redução de " + df.format((100.0 - ((100.0 / teste1) * teste2))) + "% no tempo de processamento");
        System.in.read();
        System.out.println("Moral da história. Use sempre métodos pequenos e quebre as iterações em pequenos blocos.");
        System.out.println("Como se vê, 7 loops pequenos foram mais rápidos do que apenas 1 loop grande.");

        //FALSE SHARE
        System.in.read();

        System.out.println("-------------------------------TESTE DE FALSE SHARE------------------------------------\n");
        System.out.println("Agora vamos demonstrar False Sharing.");
        System.out.println("Inicialmente vamos criar três threads que vão incrementar três diferentes variávels não voláteis");
        System.in.read();
        System.out.println("Iniciando...");
        beginTime = System.currentTimeMillis();
        testeFalseSharing(1);
        endTime = System.currentTimeMillis();
        teste1 = endTime - beginTime;
        System.out.println("Deu. Levou " + teste1 + "ms");
        System.in.read();

        System.out.println("Agora vamos fazer a mesma coisa com variáveis voláteis que estão próximas no código.");
        System.in.read();
        System.out.println("Iniciando...");
        beginTime = System.currentTimeMillis();
        testeFalseSharing(2);
        endTime = System.currentTimeMillis();
        teste2 = endTime- beginTime;
        System.out.println("Dessa vez levou " + teste2 + "ms. Um aumento enorme pq as variáveis estão no mesmo bloco de memória.");
        System.in.read();

        System.out.println("Agora vamos fazer a mesma coisa com variáveis voláteis que estão afastadas no código.");
        System.in.read();
        System.out.println("Iniciando...");
        beginTime = System.currentTimeMillis();
        testeFalseSharing(3);
        endTime = System.currentTimeMillis();
        teste3 = endTime - beginTime;
        System.out.println("Finalizou em " + teste3 + "ms. Variáveis afastadas no código acabaram ficando em blocos de memória separados.");
        System.out.println("Com isso a notificação de invalidate não afetou o acesso as demais variáveis e o tempo melhorou =)");
        System.in.read();
        System.out.println("Moral da história. Ao trabalhar com multi threads deve-se ter cuidado com o posicionamento das variáveis na memória.");
        System.out.println("Manter os valores em classes separadas ajudaria a evitar false sharing.");

        System.out.println("Por hoje é só pessoal");

    }

    public static void testeFalseSharing(Integer teste) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 99999999; i++) {
                    if (teste == 1) {
                        Teste.x1++;
                    } else if (teste == 2) {
                        Teste.x++;
                    } else if (teste == 3) {
                        Teste.x0++;
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 99999999; i++) {
                    if (teste == 1) {
                        Teste.y1++;
                    } else if (teste == 2) {
                        Teste.y++;
                    } else if (teste == 3) {
                        Teste.y0++;
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 99999999; i++) {
                    if (teste == 1) {
                        Teste.z1++;
                    } else if (teste == 2) {
                        Teste.z++;
                    } else if (teste == 3) {
                        Teste.z0++;
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

    }

    public static void testeMalucoPequeno1() {
        for (int i = 0; i < 1000000; i++) {
            Double conta1 = Math.exp(i * 999);
            Double conta2 = Math.pow(conta1, 999);
            Double conta3 = Math.random() + conta2;
            Double conta4 = Math.abs(conta3 - 9999);
            Double conta5 = conta4 + 123456;
            Double conta6 = Math.exp(conta5 * 999);
            Double conta7 = Math.pow(conta6, 999);
            Double conta8 = Math.random() + conta7;
            Double conta9 = Math.abs(conta8 - 9999);
            Double conta10 = conta9 + 123456;
        }
    }

    public static void testeMalucoGrande() {

        for (int i = 0; i < 1000000; i++) {
            Double conta1 = Math.exp(i * 999);
            Double conta2 = Math.pow(conta1, 999);
            Double conta3 = Math.random() + conta2;
            Double conta4 = Math.abs(conta3 - 9999);
            Double conta5 = conta4 + 123456;
            Double conta6 = Math.exp(conta5 * 999);
            Double conta7 = Math.pow(conta6, 999);
            Double conta8 = Math.random() + conta7;
            Double conta9 = Math.abs(conta8 - 9999);
            Double conta10 = conta9 + 123456;
            Double conta11 = Math.exp(conta10 * 999);
            Double conta12 = Math.pow(conta11, 999);
            Double conta13 = Math.random() + conta12;
            Double conta14 = Math.abs(conta13 - 9999);
            Double conta15 = conta14 + 123456;
            Double conta16 = Math.exp(conta15 * 999);
            Double conta17 = Math.pow(conta16, 999);
            Double conta18 = Math.random() + conta17;
            Double conta19 = Math.abs(conta18 - 9999);
            Double conta20 = conta19 + 123456;
            Double conta21 = Math.exp(conta20 * 999);
            Double conta22 = Math.pow(conta21, 999);
            Double conta23 = Math.random() + conta22;
            Double conta24 = Math.abs(conta23 - 9999);
            Double conta25 = conta24 + 123456;
            Double conta26 = Math.exp(conta25 * 999);
            Double conta27 = Math.pow(conta26, 999);
            Double conta28 = Math.random() + conta27;
            Double conta29 = Math.abs(conta28 - 9999);
            Double conta30 = conta29 + 123456;
            Double conta31 = Math.exp(conta30 * 999);
            Double conta32 = Math.pow(conta31, 999);
            Double conta33 = Math.random() + conta32;
            Double conta34 = Math.abs(conta33 - 9999);
            Double conta35 = conta34 + 123456;
            Double conta36 = Math.exp(conta35 * 999);
            Double conta37 = Math.pow(conta36, 999);
            Double conta38 = Math.random() + conta37;
            Double conta39 = Math.abs(conta38 - 9999);
            Double conta40 = conta39 + 123456;
            Double conta41 = Math.exp(conta40 * 999);
            Double conta42 = Math.pow(conta41, 999);
            Double conta43 = Math.random() + conta42;
            Double conta44 = Math.abs(conta43 - 9999);
            Double conta45 = conta44 + 123456;
            Double conta46 = Math.exp(conta45 * 999);
            Double conta47 = Math.pow(conta46, 999);
            Double conta48 = Math.random() + conta47;
            Double conta49 = Math.abs(conta48 - 9999);
            Double conta50 = conta49 + 123456;
            Double conta51 = Math.exp(conta50 * 999);
            Double conta52 = Math.pow(conta51, 999);
            Double conta53 = Math.random() + conta52;
            Double conta54 = Math.abs(conta53 - 9999);
            Double conta55 = conta54 + 123456;
            Double conta56 = Math.exp(conta55 * 999);
            Double conta57 = Math.pow(conta56, 999);
            Double conta58 = Math.random() + conta57;
            Double conta59 = Math.abs(conta58 - 9999);
            Double conta60 = conta59 + 123456;
            Double conta61 = Math.exp(conta60 * 999);
            Double conta62 = Math.pow(conta61, 999);
            Double conta63 = Math.random() + conta62;
            Double conta64 = Math.abs(conta63 - 9999);
            Double conta65 = conta64 + 123456;
            Double conta66 = Math.exp(conta65 * 999);
            Double conta67 = Math.pow(conta66, 999);
            Double conta68 = Math.random() + conta67;
            Double conta69 = Math.abs(conta68 - 9999);
            Double conta70 = conta69 + 123456;
        }
    }

}
