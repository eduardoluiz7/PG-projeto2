package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import atributos.Camera;
import atributos.Iluminacao;
import atributos.Objeto;
import atributos.Plano;

public class Main extends JFrame {
	int ResX = 640;
	int ResY = 480;

	//Atributos para interface grafica
	JPanel painel;
	JTextField txtObjeto;
	JTextField txtCamera;
	JTextField txtIluminacao;
	JTextField fieldPlanoX;
	JTextField fieldPlanoY;
	JButton btn;

	//Janela com o objeto
	TelaG phong;
	private JButton selecionarCamera;
	private JButton selecionarIluminacao;

	public Main(){
		// Define nome da janela
		super("Projeto 2 - Processamento Gráfico");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Define tamanho da janela
		setBounds(100, 100, 341, 480);
        painel = new JPanel();
		//getContentPane().add(painel);
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);

		txtObjeto = new JTextField("Objeto");
		txtObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtObjeto.setBounds(80, 108, 120, 25);
		painel.add(txtObjeto);

		txtCamera = new JTextField("Camera");
		txtCamera.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtCamera.setBounds(80, 140, 120, 25);
		painel.add(txtCamera);

		txtIluminacao = new JTextField("Ilumina\u00E7\u00E3o");
		txtIluminacao.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtIluminacao.setBounds(80, 170, 120, 25);
		painel.add(txtIluminacao);

		fieldPlanoX = new JTextField("1000");
		fieldPlanoX.setBounds(80, 200, 30, 25);
		painel.add(fieldPlanoX);

		JLabel labelX = new JLabel(" x ");
		labelX.setBounds(110, 203, 80, 20);
		labelX.setFont(new Font("Tahoma", Font.PLAIN, 16));
		painel.add(labelX);

		fieldPlanoY = new JTextField("1000");
		fieldPlanoY.setBounds(130, 200, 30, 25);
		painel.add(fieldPlanoY);

		labelX = new JLabel("pxls");
		labelX.setBounds(165, 203, 80, 20);
		labelX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		painel.add(labelX);

		btn = new JButton("OK");
		btn.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setup();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn.setBounds(43, 236, 120, 23);
		painel.add(btn);
		
		JButton selecionarObjeto = new JButton("Selecionar");
		selecionarObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		selecionarObjeto.setBounds(212, 109, 98, 25);
		painel.add(selecionarObjeto);
		selecionarObjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(painel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			    	if(getExtension(seletorDeArquivos.getSelectedFile()).equals("byu")){
			    		txtObjeto.setText(seletorDeArquivos.getSelectedFile().getName());
			    		
			    		try {
			    			Objeto.setObjeto(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    		} catch (IOException e) {
			    			JOptionPane.showMessageDialog(null, "Objeto inválido!");
			    		}
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Objeto inválido! Insira arquivo do tipo 'byu'!");
			    	}
			    }
			}
		});
		
		selecionarCamera = new JButton("Selecionar");
		selecionarCamera.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		selecionarCamera.setBounds(212, 141, 98, 25);
		painel.add(selecionarCamera);
		selecionarCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(painel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			    	if(getExtension(seletorDeArquivos.getSelectedFile()).equals("cfg")){
			    		txtCamera.setText(seletorDeArquivos.getSelectedFile().getName());
			    		try {
			    			Camera.initCamera(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    		} catch (IOException e) {
			    			JOptionPane.showMessageDialog(null, "Câmera inválida!");
			    		}
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Câmera inválida! Insira arquivo do tipo 'cfg'!");
			    	}
			    }
			}
		});
		
		
		selecionarIluminacao = new JButton("Selecionar");
		selecionarIluminacao.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		selecionarIluminacao.setBounds(212, 171, 98, 25);
		painel.add(selecionarIluminacao);
		selecionarIluminacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(painel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			    	if(getExtension(seletorDeArquivos.getSelectedFile()).equals("txt")){
			    		txtIluminacao.setText(seletorDeArquivos.getSelectedFile().getName());
			    		try {
			    			Iluminacao.initIluminacao(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    		} catch (IOException e) {
			    			JOptionPane.showMessageDialog(null, "Objeto inválido!");
			    		}
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Objeto inválido! Insira arquivo do tipo 'txt'!");
			    	}
			    }
			}
		});
		
		JLabel lblObjetoTransparente = new JLabel("Objeto Transparente");
		lblObjetoTransparente.setFont(new Font("Century Gothic", Font.BOLD, 27));
		lblObjetoTransparente.setBounds(31, 24, 279, 53);
		painel.add(lblObjetoTransparente);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btnAtualizar.setBounds(175, 236, 120, 23);
		painel.add(btnAtualizar);

		//Cria-se uma Janela para o objeto apresentado por Phong.
		phong = new TelaG(415, 100, ResX, ResY);
		phong.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public static void main(String[] args) {
		Main frame = new Main();
		frame.setVisible(true);
	}

	public void setup(){
		Camera.setCamera();
		Camera.convertObject(ResX, ResY);

		Iluminacao.setIluminacao();

		Camera.setIntervalos();
		System.out.println("-> Variaveis manipuladas");

		phong.scanLine3D();
		phong.repaint();
		System.out.println("Cores Calculadas e Objeto pintado com Phong");
	}

	public void readFiles() throws IOException{
		// Carrega Objeto
		String workingDir=System.getProperty("user.dir");

		String objectName = txtObjeto.getText();
		//"/home/wellington/Documents/UFPE/PG/Processamento-Grafico/P4-2/Java/src/Entradas/Cameras/calice2.cfg"
		Objeto.setObjeto("C:/Users/eduar/git/PG-projeto2/src/entradas/Objetos/"+objectName+".byu");
		System.out.println("1 -> Objeto lido e inicializado");

		//"C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas"
		
		// Carrega IluminaÃ§Ã£o
		String iluminacaoName = txtIluminacao.getText();
		Iluminacao.initIluminacao("C:/Users/eduar/git/PG-projeto2/src/entradas/"+iluminacaoName+".txt");
		

		// Carrega plano
		String planoBase = fieldPlanoX.getText();
		String planoAltura = fieldPlanoY.getText();
		Plano.setPlano(planoBase, planoAltura);
		System.err.println("3 -> Plano lido e inicializado");

	}
	/*
     * Método auxiliar para verificar a extensão dos arquivos
     */  
    private String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}
