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
		Scanner scan = new Scanner(objeto);
		scan.useLocale(Locale.ENGLISH);

		int numVertices = scan.nextInt(); //lê o numero de vertices
		int numTriangulos = scan.nextInt(); //lê o numero de triangulos

		/*
		 * Muda de coordenadas mundiais para coordenadas de vista todos os vértices do objeto.
		 */
		for(int i=0; i<numVertices; i++){
			double x = scan.nextDouble();
			double y = scan.nextDouble();
			double z = scan.nextDouble();
			Ponto3D p = new Ponto3D(x,y,z);
			p.indice=i;
			
			vertices.add(p);
		}

		for(int i=0; i<numTriangulos; i++){ //lê os triângulos
			int v1,v2,v3;
			v1 = scan.nextInt() -1;
			v2 = scan.nextInt() -1;
			v3 = scan.nextInt() -1;
			Triangulo t = new Triangulo(vertices.get(v1),vertices.get(v2),vertices.get(v3),i);
			triangulos.add(t);
		}
		scan.close();
	}
}
