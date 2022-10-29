import java.io.IOException;
import java.util.Scanner;

class App {
    public App() {

    }

    public void showSubMenuLabirinto(){
        Scanner menu = new Scanner(System.in);

        System.out.print("##--Labirintos--##\n\n");
        System.out.print("1 - maze3.txt\n");
        System.out.print("2 - maze10.txt\n");
        System.out.print("3 - maze20.txt\n");
        System.out.print("4 - maze30.txt\n");
        System.out.print("5 - maze40.txt\n");
        System.out.print("6 - maze50.txt \n");

        System.out.print("Digite o arquivo de entrada para ser executado: ");

        int file = menu.nextInt();

        String filePath = "";

        if(file == 1){
            filePath = "files/maze/maze3.txt"; 
        }
        else if(file == 2){
            filePath = "files/maze/maze10.txt"; 
        }
        else if(file == 3){
            filePath = "files/maze/maze20.txt"; 
        }
        else if(file == 4){
            filePath = "files/maze/maze30.txt"; 
        }
        else if(file == 5){
            filePath = "files/maze/maze40.txt"; 
        }
        else if(file == 6){
            filePath = "files/maze/maze50.txt"; 
        }

        
        return;
    }

    public void showSubMenu() {
        Scanner menu = new Scanner(System.in);

        System.out.print("##--Caminho Mínimo--##\n\n");
        System.out.print("##--Arquivos--##\n\n");
        System.out.print("1 - toy.txt\n");
        System.out.print("2 - rg300_4730.txt\n");
        System.out.print("3 - rome99c.txt\n");
        System.out.print("4 - facebook combined.txt\n");
        System.out.print("5 - USA-road-dt.DC.txt\n");
        System.out.print("6 - USA-road-dt.NY.txt \n");

        System.out.print("Digite o arquivo de entrada para ser executado: ");

        int file = menu.nextInt();

        String filePath = "";

        if(file == 1){
            filePath = "files/cm/toy.txt"; 
        }
        else if(file == 2){
            filePath = "files/cm/rg300_4730.txt"; 
        }
        else if(file == 3){
            filePath = "files/cm/rome99c.txt"; 
        }
        else if(file == 4){
            filePath = "files/cm/facebook_combined.txt"; 
        }
        else if(file == 5){
            filePath = "files/cm/USA-road-dt.DC.txt"; 
        }
        else if(file == 6){
            filePath = "files/cm/USA-road-dt.NY.txt"; 
        }

        System.out.print("1 - Dijkstra\n");
        System.out.print("2 - Bellman Ford\n");
        System.out.print("3 - Bellman Ford melhorado\n");
        System.out.print("4 - Floyd Warshall\n");
        System.out.print("5 - Sair\n");

        System.out.print("Escolha a opção do algoritmo: ");

        int algorithm = menu.nextInt();

        if (algorithm == 1) {
            GraphList graph;
            try {
                System.out.println(filePath);
                graph = new GraphList(filePath);

                System.out.print("Digite o vértice de início: ");

                int startNode = menu.nextInt();

                System.out.print("Digite o vértice final: ");

                int finishNode = menu.nextInt();

                graph.dijkstra(startNode, finishNode);

                menu.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (algorithm == 2) {
            GraphList graph;
            try {
                graph = new GraphList(filePath);

                System.out.print("Digite o vértice de início: ");

                int startNode = menu.nextInt();

                System.out.print("Digite o vértice final: ");

                int finishNode = menu.nextInt();

                graph.bellmanFord(startNode, finishNode);

                menu.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (algorithm == 3) {
            GraphList graph;
            try {
                graph = new GraphList(filePath);

                System.out.print("Digite o vértice de início: ");

                int startNode = menu.nextInt();

                System.out.print("Digite o vértice final: ");

                int finishNode = menu.nextInt();

                graph.improvedBellmanFord(startNode, finishNode);

                menu.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (algorithm == 4){
            GraphMatrix graph;
            try {
                graph = new GraphMatrix(filePath);

                System.out.print("Digite o vértice de início: ");

                int startNode = menu.nextInt();

                System.out.print("Digite o vértice final: ");

                int finishNode = menu.nextInt();

                graph.floydWarshall(startNode, finishNode);

                menu.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        App application = new App();
        /*
         * GraphList graphList = new GraphList("files/cm/toy.txt");
         * GraphMatrix graphMatrix = new GraphMatrix("files/graph3.txt");
         */

        Scanner menu = new Scanner(System.in);

        System.out.print("##--Menu--##\n\n");
        System.out.print("1 - Caminho Mínimo\n");
        System.out.print("2 - Labirinto\n");
        System.out.print("3 - Sair\n");

        System.out.print("Digite uma opção: ");

        int opcao = menu.nextInt();

        if (opcao == 3) {
            System.out.print("\nAté logo!");
            menu.close();
        }

        switch (opcao) {
            case 1:
                application.showSubMenu();
                break;
            case 2:
                application.showSubMenuLabirinto();
                break;
            default:
                System.out.print("\nOpção Inválida!");
                break;
        }

    }
}