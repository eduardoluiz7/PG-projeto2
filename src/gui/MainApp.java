package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import atributos.Objeto;
import atributos.Iluminacao;
import atributos.Camera;


public class MainApp extends JFrame {

	private JPanel contentPane;
	private JTextField txtObjeto;
	private JTextField txtCmera;
	private JTextField textIluminacao;
	private JButton btnOk;
	private JButton btnAtualizar;
	int ResX = 640;
	int ResY = 480;
	TelaG tela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setTitle("Projeto 2 - Processamento Gráfico");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtObjeto = new JTextField();
		txtObjeto.setBounds(12, 25, 116, 22);
		txtObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtObjeto.setText("Objeto");
		contentPane.add(txtObjeto);
		txtObjeto.setColumns(10);
		
		
		/**
		 * Botão que seleciona o objeto e suas açoes 
		 */
		JButton selecionarObjeto = new JButton("Selecionar");
		selecionarObjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(contentPane);
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
		selecionarObjeto.setBounds(135, 24, 111, 25);
		selecionarObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(selecionarObjeto);
		
		txtCmera = new JTextField();
		txtCmera.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtCmera.setBounds(12, 75, 116, 22);
		txtCmera.setText("C\u00E2mera");
		contentPane.add(txtCmera);
		txtCmera.setColumns(10);
		
		/***
		 * Botão que seleciona a cãmera e suas ações
		 */
		JButton selecionarCamera = new JButton("Selecionar");
		selecionarCamera.setBounds(134, 73, 112, 25);
		selecionarCamera.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(selecionarCamera);
		selecionarCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(contentPane);
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			    	if(getExtension(seletorDeArquivos.getSelectedFile()).equals("cfg")){
			    		txtCmera.setText(seletorDeArquivos.getSelectedFile().getName());
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
		
		textIluminacao = new JTextField();
		textIluminacao.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textIluminacao.setText("Ilumina\u00E7\u00E3o");
		textIluminacao.setColumns(10);
		textIluminacao.setBounds(12, 125, 116, 22);
		contentPane.add(textIluminacao);
		
		
		/***
		 * Botão que seleciona a iluminacao e suas ações
		 */
		JButton selecionaIluminacao = new JButton("Selecionar");
		selecionaIluminacao.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		selecionaIluminacao.setBounds(135, 124, 111, 25);
		contentPane.add(selecionaIluminacao);
		selecionaIluminacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(contentPane);
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			    	if(getExtension(seletorDeArquivos.getSelectedFile()).equals("txt")){
			    		textIluminacao.setText(seletorDeArquivos.getSelectedFile().getName());
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
		/*
		 * botão que confirma o desenho
		 */
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setup();
			}
		});
		btnOk.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnOk.setBounds(135, 200, 111, 25);
		contentPane.add(btnOk);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnAtualizar.setBounds(12, 201, 111, 25);
		contentPane.add(btnAtualizar);
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
	public void setup(){
		Camera.setCamera();
		Camera.convertObject(ResX, ResY);

		Iluminacao.setIluminacao();

		Camera.setIntervalos();
		System.out.println("-> Variaveis manipuladas");

		tela.scanLine3D();
		tela.repaint();
		System.out.println("Cores Calculadas e Objeto pintado com Phong");
	}

}
