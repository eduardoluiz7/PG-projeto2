# Projeto 2 da disciplina de Processamento Gráfico 2017.2 - CIN UFPE

## TEMA 20: Objeto transparente.
### Objetivo:
#### Parte Geral: 
  Implementar o método de visualização de objetos triangulados, através do
algoritmo de conversão por varredura, com métodos de interpolação de Phong, com a
visibilidade garantida pelo algoritmo do “z-buffer”.
#### Parte Específica: 
  Permitir o objeto ficar transparente. O seu sistema deve utilizar um “frame-buffer”.
#### Descrição:
O usuário, através de arquivos-texto ou interface gráfica, entra com dados do
objeto (um campo a mais no arquivo indica se é uma superfície de Bézier ou um objeto
comum, triangulado, com lista dos vértices e da conectividade, que determina os triângulos,
de um arquivo-texto); atributos do objeto: k a , k d , k s e k t (coeficiente de transparência), pontos
flutuantes entre 0 e 1, η, ponto flutuante positivo e O d , tripla de pontos flutuantes entre 0 e
1; atributos da cena (I a , I L , triplas de ponto flutuante entre 0 e 255, P L , tripla de ponto
flutuante) e os atributos da câmera virtual (C, N e V, triplas de pontos flutuantes, d, h x , e h y ,
pontos flutuantes positivos). O usuário também escolhe uma cor de fundo C f (R,G,B); o
frame-buffer é uma região da memória que é um clone da tela. A cena deve ser pintada no
frame-buffer primeiro, para no final ser clonado na tela. A vantagem do frame-buffer é que
podemos consultar as cores pintadas em iterações anteriores, para definir a cor atual, algo
desejável para modelar transparência. O seu sistema começa preparando a câmera,
ortogonalizando V e gerando U, e depois os normalizando, fazer a mudança de coordenadas
para o sistema de vista de todos os vértices do objeto (ou de todos os pontos de controle, se
for o caso, para logo em seguida se produzirem os triângulos) e da posição da fonte de luz
P L , gerar as normais dos triângulos e gerar as normais dos vértices (como recomendado em
sala de aula). Para cada triângulo, calculam-se as projeções dos seus vértices e inicia-se a
sua conversão por varredura. Para cada pixel (x, y scan ), calculam-se suas coordenadas
baricêntricas com relação aos vértices projetados, e multiplicam-se essas coordenadas pelos
correspondentes vértices do triângulo 3D original para se obter uma aproximação para o
ponto 3D original correspondente ao pixel atual. Se k t = 0 (objeto opaco) faz-se uma
consulta ao z-buffer e, se for o caso (distância menor que a do z-buffer: OK), calcula-se
uma aproximação para a normal do ponto utilizando-se mesmas coordenadas baricêntricas
multiplicadas pelas normais dos respectivos vértices originais. Calculam-se também os
demais vetores (L, V e R) e os substitui na equação do modelo de iluminação de Phong
produzindo a cor do pixel atual no frame-buffer. Depois de todos os triângulos terem sido
pintados, copiam-se as cores do frame-buffer para a tela. Caso o k t seja não nulo, há um
certo grau de transparência, então não se faz uma consulta ao z-buffer. Calcula-se a cor do
pixel como se a consulta ao z-buffer tivesse retornado OK. A cor a ser colocada no frame-
buffer é a cor calculada para o pixel, multiplicada por (1 – k t ) somada com a cor do frame-
buffer multiplicada por k t .
