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
	JPanel areaGraph;
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setTitle("Projeto 2 - Processamento Gr�fico");
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
		setBounds(100, 100, 881, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtObjeto = new JTextField();
		txtObjeto.setBounds(44, 26, 116, 22);
		txtObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtObjeto.setText("Objeto");
		contentPane.add(txtObjeto);
		txtObjeto.setColumns(10);
		
		
		/**
		 * Bot�o que seleciona o objeto e suas a�oes 
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
			    			JOptionPane.showMessageDialog(null, "Objeto inv�lido!");
			    		}
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Objeto inv�lido! Insira arquivo do tipo 'byu'!");
			    	}
			    }
			}
		});
		selecionarObjeto.setBounds(167, 25, 111, 25);
		selecionarObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		contentPane.add(selecionarObjeto);
		
		txtCmera = new JTextField();
		txtCmera.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtCmera.setBounds(303, 27, 116, 22);
		txtCmera.setText("C\u00E2mera");
		contentPane.add(txtCmera);
		txtCmera.setColumns(10);
		
		/***
		 * Bot�o que seleciona a c�mera e suas a��es
		 */
		JButton selecionarCamera = new JButton("Selecionar");
		selecionarCamera.setBounds(425, 25, 111, 25);
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
			    			JOptionPane.showMessageDialog(null, "C�mera inv�lida!");
			    		}
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "C�mera inv�lida! Insira arquivo do tipo 'cfg'!");
			    	}
			    }
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(290, 26, 1, 22);
		separator.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(548, 27, 1, 22);
		contentPane.add(separator_1);
		
		textIluminacao = new JTextField();
		textIluminacao.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textIluminacao.setText("Ilumina\u00E7\u00E3o");
		textIluminacao.setColumns(10);
		textIluminacao.setBounds(561, 28, 116, 22);
		contentPane.add(textIluminacao);
		
		
		/***
		 * Bot�o que seleciona a iluminacao e suas a��es
		 */
		JButton selecionaIluminacao = new JButton("Selecionar");
		selecionaIluminacao.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		selecionaIluminacao.setBounds(689, 26, 111, 25);
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
			    			JOptionPane.showMessageDialog(null, "Objeto inv�lido!");
			    		}
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Objeto inv�lido! Insira arquivo do tipo 'txt'!");
			    	}
			    }
			}
		});
		//JPanel da �rea onde os objetos ser�o exibidos
		areaGraph = new JPanel();
		areaGraph.setLayout(null);
		areaGraph.setBorder(new EmptyBorder(5, 20, 5, 5));
		areaGraph.setBounds(4, 132, 853, 524);
		contentPane.add(areaGraph);
		areaGraph.setBackground(Color.WHITE);
		/*
		 * bot�o que confirma o desenho
		 */
		btnOk = new JButton("OK");
		btnOk.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnOk.setBounds(740, 80, 111, 25);
		contentPane.add(btnOk);
	}
	
	/*
     * M�todo auxiliar para verificar a extens�o dos arquivos
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
