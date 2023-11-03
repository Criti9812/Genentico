package Genetico2;

import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.Border;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class viajero extends JFrame implements ActionListener{
  private Color color, colorL=null, colorEv;
  private JPanel panel, panel2;
  private JButton b1, lim, b2, b3;
  private JTextField t1, t4, t5;
  private JLabel l1, l2, punto, l11, l12, l3;
  private Color gris = new Color(240, 128, 128);
  private Color grisA = new Color(0, 0, 0);
  private Border borde = BorderFactory.createLineBorder(Color.BLACK, 2);
  private JTextArea area, area2;
  private JScrollPane scrol, scrol2;
  //********************************************
  private JLabel[] arrEtiquetas;
  private ArrayList<ArrayList<Float>> tabla = new ArrayList<>();
  private ArrayList<Float> fila;
  private ArrayList<Coordenadas>coord=new ArrayList<>();
  private ArrayList<Distancias>dist=new ArrayList<>();
  private List<String> cadenas;
  private List<String> mejoresCad=new ArrayList<>();
  private ArrayList<Distancias> cadenasMasCortas=new ArrayList<>();
  private Random random = new Random();
  //********************************************
  private String ciudadesP="ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789&%$#*+";
  private int contK=0, k, i1=0, individuos, generaciones, iteracion=1; 
  private String genes="", cadF;
  //******************************************** cruza
  public int tamama, div, div2, busqueda,divisionList; 
  public double division, residuo;
  private char [] guarda1, guarda2;
  private int it, it2;
  private ArrayList <String> caden1, caden2,cadenaInt1,cadenaInt2;
  private ArrayList<ArrayList<Character>> num1, num2, numCruzados, num2Inverso;
  public String nums1 = "lista 1: ", nums2="lista 2: ";
  private int alea, alea2;
  private boolean finish=false;
  //******************************************** mutacion

  public void extra(){
    setSize(1015, 760);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(3);
    setResizable(false);
    setTitle("Problema del viajero");
    comp();
    setVisible(true);
    }

  public void comp(){
    panel();
    repa();
  }

  public void repa(){
    panel2();
    boton();
    demas();
    area();
  }

  public void panel(){
    panel=new JPanel();
    panel.setLayout(null);
    panel.setBackground(gris);
    this.getContentPane().add(panel);
  }

  public void panel2(){
    panel2=new JPanel();;
    panel2.setLayout(null);
    panel2.addMouseListener(new MouseClickListener());
    panel2.setBounds(35,20,600,450);
    panel.add(panel2);
  }

  public void area(){
    area=new JTextArea();
    area.setBackground(Color.WHITE);
    scrol= new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrol.setBounds(690,60,250,235);
    panel.add(scrol);

    area2=new JTextArea();
    area2.setBackground(Color.WHITE);
    scrol2= new JScrollPane(area2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrol2.setBounds(690,340,250,320);
    panel.add(scrol2);

  }

  public void boton(){
    b1=new JButton("Generar"); 
    b1.setBounds(330,500,150,30);
    b1.setBackground(grisA);
    b1.setForeground(Color.WHITE);
    b1.addActionListener(this);
    panel.add(b1);

    lim=new JButton("Limpiar");
    lim.setBounds(330,600,150,30);
    lim.setBackground(grisA);
    lim.setForeground(Color.WHITE);
    lim.addActionListener(this);
    panel.add(lim);

    b2=new JButton("Poblacion");
    b2.setBounds(330,550,150,30);
    b2.setBackground(grisA);
    b2.setForeground(Color.WHITE);
    b2.addActionListener(this);
    panel.add(b2);

    b3=new JButton("Comenzar");
    b3.setBounds(500,540,150,50);
    b3.setBackground(grisA);
    b3.setForeground(Color.WHITE);
    b3.addActionListener(this);
    panel.add(b3);
  }

  public void demas(){

    l1=new JLabel("POBLACIÓN INICIAL");
    l1.setBounds(690,20,250,30);
    l1.setBorder(borde);
    l1.setHorizontalAlignment(SwingConstants.CENTER);
    l1.setVerticalAlignment(SwingConstants.CENTER);
    panel.add(l1);

    l11=new JLabel("N° Ciudades: ");
    l11.setBounds(50,500,150,28);
    panel.add(l11);

    l12=new JLabel("NUEVAS POBLACIONES");
    l12.setBounds(690,300,250,25);
    l12.setBorder(borde);
    l12.setHorizontalAlignment(SwingConstants.CENTER);
    l12.setVerticalAlignment(SwingConstants.CENTER);
    panel.add(l12);

    l1=new JLabel("N° Individuos: ");
    l1.setBounds(50,550,100,22);
    panel.add(l1);

    l3=new JLabel("N° Generaciones: ");
    l3.setBounds(50,600,120,22);
    panel.add(l3);

    t1=new JTextField(); //ciudades
    t1.setBounds(170,500,100,30);
    t1.setHorizontalAlignment(JTextField.CENTER);
    panel.add(t1);

    t4=new JTextField(); //individuos
    t4.setBounds(170,550,100,30);
    t4.setHorizontalAlignment(JTextField.CENTER);
    panel.add(t4);

    t5=new JTextField(); //generaciones
    t5.setBounds(170,600,100,30);
    t5.setHorizontalAlignment(JTextField.CENTER);
    panel.add(t5);

  }

  class MouseClickListener implements MouseListener {
    public void mouseClicked(MouseEvent e) {
        if(contK<k){
            int x22=e.getX();
            int y22=e.getY();
            graficarpunto(x22,y22);
            coord.add(new Coordenadas(x22, y22));
            contK++; //mientras
        }
        else{
          JOptionPane.showMessageDialog(null,"Ya has colocado todas las ciudades","Advertencia",JOptionPane.INFORMATION_MESSAGE);
        }
        if(contK==k){
          distancia();
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
  }

  public void actionPerformed(ActionEvent ev){
    if(ev.getSource()==b1){
        JOptionPane.showMessageDialog(null,"Coloca las ciudades en el mapa ",":)",JOptionPane.INFORMATION_MESSAGE);
        k=Integer.parseInt(t1.getText());
        arrEtiquetas=new JLabel[k];
    }
    else if(ev.getSource()==b2){
      individuos=Integer.parseInt(t4.getText());
      generaciones=Integer.parseInt(t5.getText());
      generarInd();
    }
    else if(ev.getSource()==lim){
      limpiarTodo();
    }
    else if(ev.getSource()==b3){
      while(iteracion<=generaciones){
        seleccion(cadenas);
      } 
      Collections.sort(cadenasMasCortas);
      cadF =cadenasMasCortas.get(0).getCad();
      String mensajeFinal="El mejor camino se obtuvo desde la "+cadF+" con una distancia de: "+cadenasMasCortas.get(0).getDistancia();
      JOptionPane.showMessageDialog(null, mensajeFinal,"FIN",JOptionPane.INFORMATION_MESSAGE);
      area.append("\n\n"+mensajeFinal);
      finish=true;
      repaint();
    }

  }//actionPerformed

  public void seleccion(List<String> cadenas){ //se obtiene la mitad de los individuos generados para la cruza
    for(int i=0; i<individuos/2; i++){
      mejoresCad.add(cadenas.get(i));
    }
    cadenas.clear();
    divisionMC(mejoresCad);
  }

  public void divisionMC(List<String> mejoresCad){
    num1 = new ArrayList<>();
    num2 = new ArrayList<>();
    numCruzados = new ArrayList<>();
    caden1 = new ArrayList <String>();
    caden2 = new ArrayList <String>();
    num2Inverso = new ArrayList<>();
    divisionList=(int) Math.floor(mejoresCad.size()/2);
    int itt=0;
    for(String iteracion : mejoresCad){
      if(itt<divisionList){
        caden1.add(iteracion);
      }else{
        caden2.add(iteracion);
      }
      itt++;
    }


    for (String palabra : caden1) {
      ArrayList <Character> numeros1 = new ArrayList<>();
      for (char caracter : palabra.toCharArray()) {
        numeros1.add(caracter);
      }
      num1.add(numeros1);
    }

     for (String palabra : caden2) {
      ArrayList <Character> numeros2 = new ArrayList<>();
      for (char caracter : palabra.toCharArray()) {
        numeros2.add(caracter);
      }
      num2.add(numeros2);
    }
    
    for(int i = 0; i<num1.size(); i++){
       cruza(num1.get(i),num2.get(i), 1);
    }

    for (int i = num2.size() - 1; i >= 0; i--) {
            num2Inverso.add(num2.get(i));
        }

    for(int i = 0; i<num1.size(); i++){
       cruza(num1.get(i),num2Inverso.get(i), 2);
    }

    if (mejoresCad.size() % 2 != 0) {
      alea = (int) random.nextInt(num1.size());
      alea2 = (int) random.nextInt(num2.size());
      cruza(num1.get(alea),num2.get(alea2), 1);
    } 

    mutacion(numCruzados);

    for (ArrayList <Character> este : numCruzados){
      StringBuilder sb = new StringBuilder(k);
      for (Character c : este) {
          sb.append(c);
      }
      cadenas.add(sb.toString());
    }

    for (String cadena : cadenas) {
      float apt=calcularAptitud(cadena);
      dist.add(new Distancias(cadena, apt));
    }
    Collections.sort(dist); 
    area2.append("\nGENERACION "+iteracion+"\n\n");
    for (Distancias distancia : dist) {
      area2.append(distancia.getCad()+" - "+distancia.getDistancia()+"\n");
    }
    String cmc="generacion "+iteracion+": "+dist.get(0).getCad();
    cadenasMasCortas.add(new Distancias(cmc, dist.get(0).getDistancia() ));
    dist.clear(); num1.clear(); num2.clear(); numCruzados.clear(); num2Inverso.clear();
    mejoresCad.clear();
    iteracion++;
  }

  public void cruza(ArrayList<Character> num1, ArrayList<Character> num2, int opc){
    ArrayList <Character> numeCruzados1 = new ArrayList<>();
    ArrayList <Character> numeCruzados2 = new ArrayList<>();
        it = 0;
        it2=0;
        tamama = num2.size();
        division = tamama/3;
        div = (int) Math.floor(division);
        residuo = division - (div * 3);
        div2 = div;
        if(residuo != 0){
            div2= div + 1;
        }
        guarda1 = new char [div2];
        guarda2 = new char [div2];
        for(int i = div; i< div+div2; i++){
            guarda1[it]=num1.get(i);
            guarda2[it]=num2.get(i);
            nums1 +=  num1.get(i)+",";
            nums2 +=  num2.get(i)+",";
            it++;
        }


        for(int i = div; i< div+div2; i++){
            for(int j = 0; j < tamama; j++){
                if(num1.get(j)==guarda2[it2]){
                    busqueda=j;
                    break;
                }
            }
            num1.set(busqueda,num1.get(i));
            num1.set(i,guarda2[it2]);
            it2++;
        }

        it2=0;
        for(int i = div; i< div+div2; i++){
            for(int j = 0; j < tamama; j++){
                if(num2.get(j)==guarda1[it2]){
                    busqueda=j;
                    break;
                }
            }
            num2.set(busqueda,num2.get(i));
            num2.set(i,guarda1[it2]);
            it2++;
        }

        for (int i = 0; i < num1.size(); i++) { 
            numeCruzados1.add(num1.get(i));
        }
        for (int i = 0; i < num1.size(); i++) {
            numeCruzados2.add(num2.get(i));
        }
        numCruzados.add(numeCruzados1);
        numCruzados.add(numeCruzados2);

    }

  public void mutacion(ArrayList<ArrayList<Character>> numCruzados){
    int porcentaje =(int) Math.ceil(numCruzados.size() * (5 / 100.0));
    

    for(int i=0; i<porcentaje; i++){
      int numMuta = random.nextInt(numCruzados.size());
      int numAleatorio1 = random.nextInt(numCruzados.get(numMuta).size());
      int numAleatorio2;
      do {
          numAleatorio2 = random.nextInt(numCruzados.get(numMuta).size());
      } while (numAleatorio1 == numAleatorio2);

      char numeroElegido1=numCruzados.get(numMuta).get(numAleatorio1);
      char numeroElegido2=numCruzados.get(numMuta).get(numAleatorio2);
      numCruzados.get(numMuta).set(numAleatorio1, numeroElegido2);
      numCruzados.get(numMuta).set(numAleatorio2, numeroElegido1);
    }

  }

  public float calcularAptitud(String cadena){ 
    float apt=0.0f;
    for(int i=0; i<k-1; i++){
      int indice1=ciudadesP.indexOf(cadena.charAt(i));
      int indice2=ciudadesP.indexOf(cadena.charAt(i+1));
      apt+=tabla.get(indice1).get(indice2);
    }
    return apt;
  }

  public void generarInd(){
    char[] caracteres = genes.toCharArray();
    cadenas = generarCad(caracteres, individuos);
    for (String cadena : cadenas) {
        float apt=calcularAptitud(cadena);
        dist.add(new Distancias(cadena, apt));
    }
    Collections.sort(dist);    //ordena la lista de menor a mayor distancia
    for (Distancias distancia : dist) {
      area.append(distancia.getCad()+" - "+distancia.getDistancia()+"\n");
    }
    String cmc="generacion "+iteracion+": "+dist.get(0).getCad();
    cadenasMasCortas.add(new Distancias(cmc, dist.get(0).getDistancia() ));
    dist.clear();
  }

  public static List<String> generarCad(char[] caracteres, int n) {
    List<String>result= new ArrayList<>();
    while (n > 0) {
        List<Character>caracteresRestantes= new ArrayList<>();
        for (char c : caracteres) {
            caracteresRestantes.add(c);
        }

        Collections.shuffle(caracteresRestantes);
        StringBuilder cadena= new StringBuilder();
        for (char c : caracteresRestantes) {
            cadena.append(c);
        }

        result.add(cadena.toString());
        n--;
    }
    return result;
  }

  public void graficarpunto(int xP, int yP){
    String mientras=String.valueOf(ciudadesP.charAt(i1));
    arrEtiquetas[i1]=new JLabel(mientras);
    arrEtiquetas[i1].setBounds(xP, yP, 20, 10);
    panel2.add(arrEtiquetas[i1]);
    panel2.repaint();
    genes+=mientras;
    i1++;
  }//met

  public void distancia(){
    for(int i=0; i<k; i++){
      Coordenadas c1=coord.get(i);
      fila = new ArrayList<>();
      for(int j=0; j<k; j++){
        Coordenadas c2=coord.get(j);
        float dist=calcDistancia(c1, c2); 
        fila.add(dist);
      }
      tabla.add(fila);
    }
    escribirTabla("tabla.csv");
    panel2.removeAll();
    repaint();
  }

  public void escribirTabla(String nombreArchivo) {
    try (FileWriter writer = new FileWriter(nombreArchivo)) {
        for (ArrayList<Float> fila : tabla) {
            for (Float valor : fila) {
                writer.append(valor.toString());
                writer.append(",");
            }
            writer.append("\n");
        }
        writer.flush();
        writer.close();
        JOptionPane.showMessageDialog(null,"Contenido de la tabla escrito en tabla.csv","Datos",JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

  public float calcDistancia(Coordenadas coord, Coordenadas c2){
    float resultado;
    resultado=(float)Math.sqrt( Math.pow((c2.getX()-coord.getX()),2 ) + Math.pow((c2.getY()-coord.getY()),2 ) );
    return resultado;
  }

  public void limpiarTodo(){
    individuos=0; generaciones=0; k=0; contK=0; i1=0; iteracion=0;
    tabla.clear(); fila.clear(); coord.clear(); dist.clear(); mejoresCad.clear();
    num1.clear(); num2.clear(); numCruzados.clear(); num2Inverso.clear();
    cadenasMasCortas.clear();
    genes=""; finish=false; cadF="";
    area.setText(""); area2.setText("");
    panel2.removeAll(); panel2.revalidate(); panel2.repaint();
    t1.setText(""); t4.setText(""); t5.setText("");
    JOptionPane.showMessageDialog(null,"No olvides borrar el archivo 'tabla.csv' que ya no utilizas ","Limpiando...",JOptionPane.INFORMATION_MESSAGE);
  }

  public void paint(Graphics g) {
    super.paint(g);
    int x1=0, y1=0;
    try{
      for (int i = 0; i < arrEtiquetas.length - 1; i++) {
        JLabel label1 = arrEtiquetas[i];
        for(int j=i; j<arrEtiquetas.length - 1; j++){
          JLabel label2 = arrEtiquetas[j + 1];

          x1 = label1.getX() + (label1.getWidth() / 2) +35 ;
          y1 = label1.getY() + (label1.getHeight() / 2) +50;
  
          int x2 = label2.getX() + (label2.getWidth() / 2) +35;
          int y2 = label2.getY() + (label2.getHeight() / 2) +50;
          g.setColor(Color.PINK);
          g.drawLine(x1, y1, x2, y2);
        }
      }
      for(int i=0; i<arrEtiquetas.length; i++){
        JLabel label1 = arrEtiquetas[i];
        x1 = label1.getX() + (label1.getWidth() / 2) +35 ;
        y1 = label1.getY() + (label1.getHeight() / 2) +50; 
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString(label1.getText(), x1, y1);
      }
      if(finish){
        
        String[] partes = cadF.split(":");
        String cadF2 = partes.length > 1 ? partes[1] : "";
        
        int in=1;
        int cond=in+k;
        while(in<cond){
          
            for (int i = 0; i < arrEtiquetas.length; i++) {
            JLabel label1 = arrEtiquetas[i];
            String c1=String.valueOf(cadF2.charAt(in));
            String c2=String.valueOf(cadF2.charAt(in+1));
            
            if(label1.getText().equals(c1)){
              
              for (int j = 0; j < arrEtiquetas.length; j++){
                JLabel label2 = arrEtiquetas[j];
                if(label2.getText().equals(c2)){
                  
                  x1 = label1.getX() + (label1.getWidth() / 2) +35 ;
                  y1 = label1.getY() + (label1.getHeight() / 2) +50;
          
                  int x2 = label2.getX() + (label2.getWidth() / 2) +35;
                  int y2 = label2.getY() + (label2.getHeight() / 2) +50;
                  g.setColor(Color.BLUE);
                  g.drawLine(x1, y1, x2, y2);
                  in++;
                }
              }
            }
          }
        }

      }
    }catch(Exception ex){
      System.out.println("");
    }      
  }

  public static void main (String [] args){
    viajero ventana=new viajero();
    ventana.extra();
  }
}