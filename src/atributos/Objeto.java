package atributos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Objeto {
	public static ArrayList<Ponto3D> vertices;
	public static ArrayList<Triangulo> triangulos;
	
	public static void setObjeto(String filepath) throws IOException {
		vertices = new ArrayList<Ponto3D>();
		triangulos = new ArrayList<Triangulo>();

		File objeto = new File(filepath);
		System.out.println(objeto.getPath());
		Scanner leitor = new Scanner(objeto);
		leitor.useLocale(Locale.ENGLISH);

		int numVertices = leitor.nextInt(); //l� o numero de vertices
		int numTriangulos = leitor.nextInt(); //l� o numero de triangulos

		/*
		 * Muda de coordenadas mundiais para coordenadas de vista todos os v�rtices do objeto.
		 */
		for(int i=0; i<numVertices; i++){
			double x = leitor.nextDouble();
			double y = leitor.nextDouble();
			double z = leitor.nextDouble();
			Ponto3D p = new Ponto3D(x,y,z);
			p.indice=i;
			
			vertices.add(p);
		}

		for(int i=0; i<numTriangulos; i++){ //l� os tri�ngulos
			int v1,v2,v3;
			v1 = leitor.nextInt() -1;
			v2 = leitor.nextInt() -1;
			v3 = leitor.nextInt() -1;
			Triangulo t = new Triangulo(vertices.get(v1),vertices.get(v2),vertices.get(v3),i);
			triangulos.add(t);
		}
		leitor.reset();
		leitor.close();
	}
}
