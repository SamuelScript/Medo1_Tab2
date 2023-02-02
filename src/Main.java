import javax.swing.UIManager;
import java.util.Arrays;

public class Main {
    public static class Main {
        public Main() {
        }

        public static void main(String[] args) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception var3) {
                var3.printStackTrace();
            }
            MainFrame main = new MainFrame();
            main.setVisible(true);

            Trabalho.t2part1(main.getGChart(0));
            Trabalho.t2part2(main.getGChart(1));
        }
    }
}
class Trabalho {
    public static void t2part1(GChart g) {
        int nodes[] = {5, 10, 20, 50, 100};
        double L = 5.0;
        double alpha = 1.3e-6;
        double beta = 3.9e-6;

        for(int i=0; i<nodes.length; i++) {
            g.setLabel(i,"n = "+nodes[i]);
            g.addData(i,0,0.1); //Contorno Esquerdo
            g.addData(i, L,0.0); //Contorno Direito

            MalhaComputacional mcomp = new MalhaComputacional(L/(nodes[i]-1),beta,L,0.1,0.0,alpha);
            double[][] matrix = mcomp.gerar_matriz_A(nodes[i]-2);//Número de nós internos
            double[] vetor_solucao = mcomp.gerar_vetor_b(nodes[i]-2);
            TDM tdm = new TDM(matrix,vetor_solucao);
            tdm.solve();
            for(int j = 0; j < nodes[i] - 2; j++) g.addData(i, (j+1)*(L/(nodes[i]-1)), vetor_solucao[j]);
        }
    }
    public static void t2part2(GChart g) {

    }
    public static void analise_sensibilidade_part1() {
        int nodes = 100;
        double L = 5.0;
        double alpha = 1.3e-6;
        double beta = 3.9e-6;
        for (int i = 2; i <= 5; i++) {
            MalhaComputacional mcomp = new MalhaComputacional(L / (nodes - 1), beta, L, 0.1, 0.0, beta * (1.0 / i));
            double[][] matrix = mcomp.gerar_matriz_A(nodes - 2);//Número de nós internos
            double[] vetor_solucao = mcomp.gerar_vetor_b(nodes - 2);
            TDM tdm = new TDM(matrix, vetor_solucao);
            tdm.solve();
            System.out.println(Arrays.toString(vetor_solucao));
        }
    }
    public static void analise_sensibilidade_part2() {

    }
}
