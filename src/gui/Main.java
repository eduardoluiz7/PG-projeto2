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

public class Main extends JFrame {
	int ResX = 640;
	int ResY = 480;

	//Atributos para interface grafica
	JPanel painel;
	JTextField txtObjeto;
	JTextField txtCamera;
	JTextField txtIluminacao;
	JButton btn;
	JFileChooser seletorDeArquivos;
	String objetPath = "C:/Users/eduar/git/PG-projeto2/src/entradas/objeto.byu";
	String cameraPath = "C:/Users/eduar/git/PG-projeto2/src/entradas/camera.cfg";
	String iluminacaoPath = "C:/Users/eduar/git/PG-projeto2/src/entradas/iluminacao.txt";
	boolean camera, objt, iluminacao;

	//Janela com o objeto
	TelaG phong;
	private JButton selecionarCamera;
	private JButton selecionarIluminacao;

	public Main(){
		// Define nome da janela
		super("Projeto 2 - Processamento Gráfico");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Define tamanho da janela
		setBounds(100, 100, 341, 414);
        painel = new JPanel();
		//getContentPane().add(painel);
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);

		txtObjeto = new JTextField("Objeto.byu");
		txtObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtObjeto.setBounds(44, 108, 130, 25);
		painel.add(txtObjeto);

		txtCamera = new JTextField("Camera.cfg");
		txtCamera.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtCamera.setBounds(44, 163, 131, 25);
		painel.add(txtCamera);

		txtIluminacao = new JTextField("Iluminacao.txt");
		txtIluminacao.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtIluminacao.setBounds(44, 224, 131, 25);
		painel.add(txtIluminacao);

		btn = new JButton("OK");
		btn.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Objeto.setObjeto(objetPath);
					Camera.initCamera(cameraPath);
					Iluminacao.initIluminacao(iluminacaoPath);
					setup();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn.setBounds(44, 301, 120, 23);
		painel.add(btn);
		
		JButton selecionarObjeto = new JButton("Selecionar");
		selecionarObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		selecionarObjeto.setBounds(186, 109, 110, 25);
		painel.add(selecionarObjeto);
		selecionarObjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(painel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			    	if(getExtension(seletorDeArquivos.getSelectedFile()).equals("byu")){
			    		txtObjeto.setText(seletorDeArquivos.getSelectedFile().getName());
			    			objetPath = seletorDeArquivos.getSelectedFile().getAbsolutePath()+"";
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Objeto inválido! Insira arquivo do tipo 'byu'!");
			    	}
			    }
			}
		});
		
		selecionarCamera = new JButton("Selecionar");
		selecionarCamera.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		selecionarCamera.setBounds(187, 164, 109, 25);
		painel.add(selecionarCamera);
		selecionarCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(painel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			    	if(getExtension(seletorDeArquivos.getSelectedFile()).equals("cfg")){
			    		txtCamera.setText(seletorDeArquivos.getSelectedFile().getName());
			    			cameraPath = seletorDeArquivos.getSelectedFile().getAbsolutePath()+"";
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Câmera inválida! Insira arquivo do tipo 'cfg'!");
			    	}
			    }
			}
		});
		
		
		selecionarIluminacao = new JButton("Selecionar");
		selecionarIluminacao.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		selecionarIluminacao.setBounds(187, 225, 109, 25);
		painel.add(selecionarIluminacao);
		selecionarIluminacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seletorDeArquivos = new JFileChooser("C:\\Users\\eduar\\git\\PG-projeto2\\src\\entradas");
				int returnVal = seletorDeArquivos.showOpenDialog(painel);
			    if(returnVal == JFileChooser.APPROVE_OPTION) { 
			    	if(getExtension(seletorDeArquivos.getSelectedFile()).equals("txt")){
			    		txtIluminacao.setText(seletorDeArquivos.getSelectedFile().getName());
			    		try {
			    			iluminacaoPath = seletorDeArquivos.getSelectedFile().getAbsolutePath()+"";
			    			
			    		} catch (Exception e) {
			    			JOptionPane.showMessageDialog(null, "Iluminação inválida!");
			    		}
			    			System.out.println(seletorDeArquivos.getSelectedFile().getAbsolutePath()+"");
			    	}else {
			    		JOptionPane.showMessageDialog(null, "Iluminação inválida! Insira arquivo do tipo 'txt'!");
			    	}
			    }
			}
		});
		
		JLabel lblObjetoTransparente = new JLabel("Objeto Transparente");
		lblObjetoTransparente.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblObjetoTransparente.setBounds(12, 13, 299, 63);
		painel.add(lblObjetoTransparente);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Objeto.setObjeto(objetPath);
					Camera.initCamera(cameraPath);
					Iluminacao.initIluminacao(iluminacaoPath);
					setup();
				}catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Entradas inválidas!");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAtualizar.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btnAtualizar.setBounds(176, 301, 120, 23);
		painel.add(btnAtualizar);
		
		JLabel lblIluminao = new JLabel("Ilumina\u00E7\u00E3o:");
		lblIluminao.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblIluminao.setBounds(12, 201, 80, 16);
		painel.add(lblIluminao);
		
		JLabel lblCmera = new JLabel("C\u00E2mera:");
		lblCmera.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblCmera.setBounds(12, 137, 80, 25);
		painel.add(lblCmera);
		
		JLabel lblObjeto = new JLabel("Objeto:");
		lblObjeto.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblObjeto.setBounds(12, 81, 80, 25);
		painel.add(lblObjeto);

		//Cria-se uma Janela para o objeto apresentado por Phong.
		phong = new TelaG(500, 100, ResX, ResY);
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
