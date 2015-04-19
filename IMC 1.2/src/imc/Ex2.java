package imc;

//importação das classes necessarias
//layouts para alinhamento dos componentes
import java.awt.FlowLayout;
import java.awt.GridLayout;
//janela
import javax.swing.JFrame;
//botão
import javax.swing.JButton;
//caixa de seleção
import javax.swing.JComboBox;
//inserção de texto e/ou imagem
import javax.swing.JLabel;
//classe Icon junto com ImageIcon anexo uma imagem a um Jlabel
import javax.swing.Icon;
import javax.swing.ImageIcon;
//exibição de textos numa pequena caixa de mensagens
import javax.swing.JOptionPane;
//painel
import javax.swing.JPanel;
//campo para inserção de valores ou caracteres
import javax.swing.JTextField;
//Evento de executar uma ação do evento que foi ouvido(ativado)
import java.awt.event.ActionEvent;

//Criação da classe EX2 extendendo a classe JFrame que foi importada
public class Ex2 extends JFrame {

    private final String sexo[] = {"Mulher", "Homem"};
    int tipo, foto;
    double altura, peso, massa = 0.0;
    double imc_homens[] = {18.5, 25.0, 30.0};
    double imc_mulheres[] = {18.5, 25.0, 30.0};
    private String string = "";

    //criação do FlowLayout que alinha componentes da esquerda para a direita.
    private final FlowLayout flowLayout = new FlowLayout();
    /*criação de GridLayout com 4 linhas e 2 colunas com 10 de espa�o em largura e 1 de altura*/
    private final GridLayout gridLayout = new GridLayout(4, 2, 10, 1);
    //criação de um painel
    private final JPanel gridJPanel = new JPanel();

    //criação de dois bot�es com os nomes Calcular e Limpar Dados
    private final JButton butao = new JButton("Calcular");
    private final JButton butao2 = new JButton("Limpar Dados");
    //criação das label's
    private final JLabel Lsexo = new JLabel("Escolha o sexo:");
    private final JLabel Laltura = new JLabel("Altura em cm:");
    private final JLabel Lpeso = new JLabel("Peso em Kg:");
    private final JLabel Lresultado = new JLabel("");
    private final JLabel Lfoto = new JLabel("");
    //criação de campos com 5 de largura
    private final JTextField Faltura = new JTextField("", 5);
    private final JTextField Fpeso = new JTextField("", 5);

    //criação de uma caixa de seleção
    private JComboBox escolha = new JComboBox(sexo);

    //anexando a imagem um icone de nome limpar
    private final Icon limpar = new ImageIcon(getClass().getResource("blank.gif"));
    //criando um vetor com as demais imagens
    private final Icon imagemM[] = {new ImageIcon(getClass().getResource("esqueleto.gif")), new ImageIcon(getClass().getResource("normalM.jpg")), new ImageIcon(getClass().getResource("obesa.jpg")), new ImageIcon(getClass().getResource("acimaPesoM.JPG"))};
    private final Icon imagemH[] = {new ImageIcon(getClass().getResource("esqueleto.gif")), new ImageIcon(getClass().getResource("normalH.jpg")), new ImageIcon(getClass().getResource("obeso.jpg")), new ImageIcon(getClass().getResource("acimaPesoH.JPG"))};

    //construtor de Ex2 sem argumentos
    public Ex2() {
        //t�tulo  da janela
        super("Cálculo do IMC (Índice de massa corporal)");
        //alinhamento do frame com o uso do objeto flowLayout
        super.setLayout(flowLayout);
        //tamanho da janela
        setSize(370, 160);
        //inclusão dos componentes de maximinizar, miniminizar e fechar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //exibe 2 linhas da caixa de seleção ao clicá-la
        escolha.setMaximumRowCount(2);
        //alinha o painel com o objeto gridLayout
        gridJPanel.setLayout(gridLayout);
        //adiciona os componentes
        gridJPanel.add(Lsexo);
        gridJPanel.add(escolha);
        gridJPanel.add(Laltura);
        gridJPanel.add(Faltura);
        gridJPanel.add(Lpeso);
        gridJPanel.add(Fpeso);
        gridJPanel.add(butao);
        gridJPanel.add(butao2);

        //adiciona a janela principal
        super.add(gridJPanel);
        super.add(Lfoto);
        super.add(Lresultado);

        //criação de uma classe interna anonima para butao
        butao.addActionListener((ActionEvent event) -> {
            switch (escolha.getSelectedIndex()) {
                /*caso seja a primeira op��o que foi selecionada na caixa de sele��o
                configure tipo=0 e v� para o m�todo Calculos*/
                case 0: {
                    tipo = 0; //mulher
                    calculaMassa(altura,peso);
                    Calculos(massa);
                    break;
                }
                //caso seja a segunda opção, configura tipo como 1 e vá para o método Calculos.
                case 1: {
                    tipo = 1; //homem
                    calculaMassa(altura,peso);
                    Calculos(massa);
                    break;
                }
            }
        } //Fim da classe interna anonima
        );//fim da chamada para addActionListerner

        //classe interna an�nima para butao2
        butao2.addActionListener((ActionEvent event) -> {
            limpar();
        } //ao clicar no butao2 de nome limpar chama o método limpar
        //Fim da classe interna anonima
        );//fim da chamada para addActionListerner
    }
    
    public final double calculaMassa(double peso, double altura) {
        //pega e converte os caracteres em ponto flutuante do campo Faltura para a variavel altura
        altura = Double.parseDouble(Faltura.getText());
        //converte para metros
        altura /= 100;
        peso = Double.parseDouble(Fpeso.getText());
        return massa = peso / (altura * altura);   
    }       
    
    //método que realiza os calculos
    public void Calculos(double massa) {
  
        try //tratador de erros com try e catch
        {
            if (tipo == 0) {
                if (imc_mulheres[0] > massa) {
                    string = String.format("Você está abaixo do peso recomendado. IMC %.2f", massa);
                    foto = 0;
                } else if ((imc_mulheres[0] < massa) && (massa <= imc_mulheres[1])) {
                    string = String.format("Você está muito bem! Continue assim! IMC %.2f", massa);
                    foto = 1;
                } else if ((imc_mulheres[1] < massa ) && (massa <= imc_mulheres[2])){
                    string = String.format("Você está acima do peso recomendado. Cuidado! IMC %.2f",massa);
                    foto = 3;
                } else {
                    string = String.format("Você está Obeso.Procure o acompanhamento de um nutricionista e realizar mais atividades físicas! IMC %.2f", massa);
                    foto = 2;
                }
                //configure a foto conforme a posição da variável foto
                Lfoto.setIcon(imagemM[foto]);
            } else if (tipo == 1) {
                if (imc_homens[0] > massa) {
                    string = String.format("Você está abaixo do peso recomendado. IMC %.2f", massa);
                    //configura a posição que será exibido a imagem
                    foto = 0;
                } else if ((imc_homens[0] < massa) && (massa < imc_homens[1])) {
                    string = String.format("Você está muito bem! Continue assim! IMC %.2f", massa);
                    foto = 1;
                } else if ((imc_homens[1] < massa) && (massa < imc_homens[2])) {
                    string = String.format("Você está acima do peso recomendado. Cuidado! IMC %.2f", massa);
                    foto = 3;
                } else {
                    string = String.format("Você está Obeso.Procure o acompanhamento de um nutricionista e realizar mais atividades físicas! IMC %.2f", massa);
                    foto = 2;
                }
                //configure a foto conforme a posição da variável foto
                Lfoto.setIcon(imagemH[foto]);
            }
            //reconfigure o tamanho da tela
            setSize(370, 500);
            //configure a label Lresultado com a variável string
            Lresultado.setText(string);
        } //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem
        catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "N�mero inv�lido!\nEx: Use '.' ao inv�s de ',' para separar as casas decimais.", "ERROR FATAL!!!", JOptionPane.ERROR_MESSAGE);
            //limpe s campos e vari�veis
            Fpeso.setText("");
            Faltura.setText("");
            peso = 0;
            altura = 0;
        }
    }

    //método para limpar os dados da tela e retornar a tela ao seu tamanho original
    private void limpar() {
        Fpeso.setText("");
        Faltura.setText("");
        Lresultado.setText("");
        //substitua a imagem atual por essa
        Lfoto.setIcon(limpar);
        setSize(300, 160);
    }
}//Fim da classe Ex2
