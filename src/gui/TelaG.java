package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

import atributos.Camera;
import atributos.Iluminacao;
import atributos.Ponto3D;
import atributos.Triangulo;

/**
 * Tela onde o objeto ser� pintado
 * @author Eduardo Luiz - els6
 *
 */
public class TelaG extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double[][] z_buffer;
	public ArrayList<Triangulo> t;
	public ArrayList<Triangulo> t2;
	public int ResX = 1000;
	public int ResY = 1000;
	int qtdPontos =0;
	BufferedImage objeto;

	public TelaG(int x, int y, int resX, int resY){
		super("Projeto 2 - Objeto transparente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, resX, resY);
		this.ResX = resX;
		this.ResY = resY;
		z_buffer = new double[ResX+1][ResY+1];
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(objeto, 0, 0, null);
	}

	public void scanLine3D(){
		objeto = new BufferedImage(ResX+1, ResY+1, BufferedImage.TYPE_INT_ARGB); 
		this.t = Camera.triangulosConvertidos;
		this.t2 = Camera.triangulos2D;
		for (double[] row: z_buffer)
			Arrays.fill(row, Double.MAX_VALUE);
		for(int i=0;i<t2.size();i++){
			pinte(Camera.intervalos.get(i),t.get(i).indice, i);
		}
		fix();
	}

	private void pinte(Ponto3D[][] intervalos, int indice, int k){
		for(int i=0;i<intervalos.length;i++){
			if(intervalos[i][0]!=null){
				//System.out.println("x_min: "+ret[i][0].x+" x_max:"+ret[i][1].x+" y: "+ret[i][0].y);
				for(int j= (int) intervalos[i][0].x;j<=intervalos[i][1].x;j++){
					Ponto3D pixel = new Ponto3D(j, intervalos[i][0].y);

					int x = (int) pixel.x;
					int y = (int) pixel.y;

					if(x>=0 && x<=ResX && y>=0 && y<=ResY ){
						double[] bary = t2.get(indice).getBaryCoefs(pixel);
						if (bary[0] < 0 || bary[1] < 0 || bary[2] < 0) continue;

						Ponto3D v1 = t.get(indice).v1;
						Ponto3D v2 = t.get(indice).v2;
						Ponto3D v3 = t.get(indice).v3;
						Ponto3D p = v1.multiply(bary[0]).add(v2.multiply(bary[1])).add(v3.multiply(bary[2])); 

						//System.out.println("x: "+pixel.x+" y: "+ pixel.y);
					if(Iluminacao.kt==0) {	
						if(x <= ResX && y <= ResY && z_buffer[x][y]>p.z && p.z>0){
							z_buffer[x][y] = p.z;
							Ponto3D I = p.getColor();
							int r,g,b;
							r = (int) Math.round(I.x); 
							g = (int) Math.round(I.y); 
							b = (int) Math.round(I.z); 
							qtdPontos++;
							int rgb = new Color(r,g,b).getRGB();
							objeto.setRGB(x, y, rgb);
						}
					}
					/*
					else {
						Ponto3D I = (p.getColor().multiply(1-Iluminacao.kt));
						double corob = objeto.getRGB(x, y)*Iluminacao.kt;
						int r,g,b;
						r = (int) Math.round(I.x); 
						g = (int) Math.round(I.y); 
						b = (int) Math.round(I.z);
						qtdPontos++;
						int rgb = new Color(r,g,b).getRGB();
						objeto.setRGB(x, y, rgb+ (int)corob);
					}
					*/
				}
					}
			}
		}
	}

	private void fix() {
		for (int x=1;x<ResX;x++){
			for (int y=1;y<ResY;y++){
				if (z_buffer[x][y] == Double.MAX_VALUE && z_buffer[x][y+1] != Double.MAX_VALUE && z_buffer[x][y-1] != Double.MAX_VALUE){
					int r2,g2,b2;
					Color color;
					int rgb;
					rgb= objeto.getRGB(x, y+1);
					color = new Color(rgb);
					r2 = color.getRed()/2;
					g2 = color.getGreen()/2;
					b2 = color.getBlue()/2;

					rgb = objeto.getRGB(x, y-1);
					color = new Color(rgb);
					r2 += color.getRed()/2;
					g2 += color.getGreen()/2;
					b2 += color.getBlue()/2;

					rgb = objeto.getRGB(x, y);
					color = new Color(r2,g2,b2);
					rgb = color.getRGB();

					objeto.setRGB(x, y, rgb);
				}
			}
		}
	}
}