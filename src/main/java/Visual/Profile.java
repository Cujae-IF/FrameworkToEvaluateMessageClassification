/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Model.Clasifier;
import Model.Email;
import Model.EmailAux;
import Model.FilesSend;
import Model.Statistics;
import Source.Classifier;
import Source.FeaturesExtractor;
import Source.PreProcessing;
import static Visual.Options.convertirArchivos;
import static Visual.Options.extractLabel;
import static Visual.Principal.convertirArchivos;
import static Visual.Principal.extractLabel;
import static Visual.Principal.getClasifiers;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import edu.stanford.nlp.pipeline.CoreNLPProtos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import javax.swing.Timer;



/**
 *
 * @author andy
 */
public class Profile {
   private Source source;
   private int cantEmails;
   private int frecEmails;
   private Object aditional;
   private static ArrayList<Email> clasify;
   private Timer timerLoad;
   private Timer timerClasify;
   //private Timer timerAutoUpdate;
   private Timer timerChange=null;
   ArrayList<Email> emails;
   private static ArrayList<File> files1;
   private static PreProcessing preProcessing;
   // Lista que almacenara las URL de los clasificadores
   private ArrayList<String> emailsClasifications;
   private static ArrayList<EmailAux> emailAuxs;
   private static ArrayList<EmailAux> emailAuxsComplete;
   private static Logger LOG_RAIZ = Logger.getLogger("Visual");
   
   private static Logger LOGGER = Logger.getLogger("Visual.Profile");
    
   
    public Profile() {
      
    }

    public Profile(Source source, int cantCorreos, int frecCorreos, Object adicional) throws IOException {  
        
        Handler fileHandler = new FileHandler("data" + File.separator  + "Log" + File.separator + "simulation.xml", true);
//        SimpleFormatter simpleFormatter = new SimpleFormatter(); 
//        fileHandler.setFormatter(simpleFormatter);       
        LOG_RAIZ.addHandler(fileHandler);
        fileHandler.setLevel(Level.ALL);    
        
        
        
        this.source = source;
        this.cantEmails = cantCorreos;
        this.frecEmails = frecCorreos;
        this.aditional = adicional;
        
        if (getSource().getConfiguration() == 1) {
            LOGGER.log(Level.INFO, "Perfil:" + getSource().getName() + ", Cantidad de correos:" + getCantEmails() + ", Tipo:" + getSource().getConfiguration() + ", Frecuencia:" + getFrecEmails() + ", Ofuscacion:" + getAditional().toString());
        }
        if (getSource().getConfiguration() == 2) {
            LOGGER.log(Level.INFO, "Perfil:" + getSource().getName() + ", Cantidad de correos:" + getCantEmails() +  ", Tipo:" + getSource().getConfiguration()  +", Frecuencia:" + getFrecEmails()+  ", Tamaño minimo de correos:" + Integer.valueOf(getAditional().toString()));
        }
        if (getSource().getConfiguration() == 3) {
            LOGGER.log(Level.INFO, "Perfil:" + getSource().getName() + ", Cantidad de correos:" + getCantEmails() + ", Tipo:" + getSource().getConfiguration()  + ", Frecuencia:" + getFrecEmails()+  ", Variar razon de envio:" + getAditional().toString());
        }
        
        emails = new ArrayList<>();
        files1 = new ArrayList<>();
        emailAuxs=new ArrayList<>();
        emailAuxsComplete=new ArrayList<>();
        emailsClasifications=new ArrayList<>();
        this.preProcessing = new PreProcessing();
        this.preProcessing.initialize(CoreNLPProtos.Language.English);
        String path = "data" + File.separator + "dataset" + File.separator + this.source.getName();
        this.timerLoad = new Timer(this.frecEmails/4, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
              if(getSource().getConfiguration()==2){
                  File[] emailBigger=extractEmailsBiggerThan(getFiles(path),Integer.valueOf(getAditional().toString())); 
                  emails.addAll(loadEmails(choosePositionsEmails(emailBigger, getCantEmails()), emailBigger));
                  lanzarEventoCargar(getSource().getName(), emails.size());
          
              }
               if(getSource().getConfiguration()==1){
                   ArrayList<Email> listaAux=new ArrayList<>();
                   listaAux.addAll(loadEmails(choosePositionsEmails(getFiles(path), getCantEmails()), getFiles(path)));
                   
                   emails.addAll(ofuscarCorreos(listaAux, Boolean.valueOf(getAditional().toString())));
                   lanzarEventoCargar(getSource().getName(), emails.size());
              }
               if(getSource().getConfiguration()==3){
                    emails.addAll(loadEmails(choosePositionsEmails(getFiles(path), getCantEmails()), getFiles(path)));
                    lanzarEventoCargar(getSource().getName(), emails.size());
                    
                 }
                // Se cargan los correos los cuales se van annadiendo a la cola de carga a razon de la frecuencia establecida
              
//                    clasificar(cargarCorreos(escogerPosicionesCorreos(getFiles(pathSpammer), cantEmailSpammer), getFiles(pathSpammer)),obfuscaciónEmailSpammer);
//                    calcularEfectividad(clasificar,"Spammer");
//                    fillTable();
         System.out.println(emails.size()+" cargados de " +source.getName());
           
            } 
        });
        this.timerClasify = new Timer(this.frecEmails, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // Se van clasificando los correos en el orden de la cola a razon de la frecuencia establecida
                ArrayList<Email> emailsToClasify = new ArrayList<>();
                //En filesToClasify se guardan los ficheros que se van a clasificar
               // ArrayList<File> filesToClasify = new ArrayList<>();
                
                for (int i = 0; i < getCantEmails(); i++) {
                    emailsToClasify.add(emails.get(i));
                   //para enviar 
                   // filesToClasify.add(files1.get(i));
                    emails.remove(i);
                   // files1.remove(i);
                }                  
                    lanzarEventoEnviar(getSource().getName(), emailsToClasify.size());
                    
                   sendToClasifiers(emailsToClasify);
//                try {
//                    sendFilesToClasifiers(copyFromListToArray(filesToClasify));
//                } catch (IOException ex) {
//                    Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
//                }
                   
                  
                
                //Estas proximas 4 lineas se comentarean  si se quiere probar el envio de ficheros en general         
                calcularEfectividad(emailAuxsComplete);
                Principal.fillTable();
                emailAuxsComplete=new ArrayList<>();
                emailsToClasify.clear();
           
            } 
        });
        if(getSource().getConfiguration()==3){
            if(Boolean.valueOf(getAditional().toString())){
                this.timerChange = new Timer(20000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
       
                setCantEmails((int)(Math.random()* 10 + 1));
                lanzarEventoVariar(getSource().getName(), getCantEmails());               
                
            } 
        });  
            }
           
        }
        
//        this.timerAutoUpdate = new Timer(30000, new ActionListener(){
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    File[] files = Profile.getFiles("data" + File.separator + "dataset" + File.separator + "Usuarios");
//                    ArrayList<FilesSend> filesSend = new ArrayList<>();
//                    
//                    List<byte[]> archivos = convertirArchivos(files);
//                    ArrayList<String> realLabels = extractLabel(files);
//                    
//                    for (int i = 0; i < archivos.size(); i++) {
//                        FilesSend file = new FilesSend();
//                        file.setArray(archivos.get(i));
//                        file.setLabel(realLabels.get(i));
//                        filesSend.add(file);
//                    }
//                    Gson gson = new Gson();
//                    String jsonArray = gson.toJson(filesSend);
//                    System.out.println(jsonArray);
//                    for (int i = 0; i < Principal.getClasifiers().size(); i++) {
//                        Client client = Client.create();
//                        WebResource webResource = client
//                                .resource(Principal.getClasifiers().get(i).getAutoUpdateURL());
//                        
//                        ClientResponse response = webResource.type("application/json")
//                                .post(ClientResponse.class, jsonArray);
//                        
//                        
//                        if (response.getStatus() != 201) {
//                            throw new RuntimeException("Failed : HTTP error code : "
//                                    + response.getStatus());
//                        }
//                        
//                        String outputJson = response.getEntity(String.class);
//                        JOptionPane.showMessageDialog(null, Principal.getClasifiers().get(i).getName() + " "+ outputJson);
//                    }       } catch (IOException ex) {
//                    Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } 
//        });
       

    }
    
    
     public static  List<byte[]> convertirArchivos(File[] selectedFile) throws java.io.FileNotFoundException, java.io.IOException{
        //TODO write your implementation code here:
             
        
        byte[] bytes=null;
        List<byte[]> archivos=new ArrayList<byte[]>();     
        for(int i=0;i<selectedFile.length;i++){ 
            
        FileInputStream fis=new FileInputStream(selectedFile[i]);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] buf=new byte[1024];
        for(int readNum;(readNum=fis.read(buf))!=-1;){
            bos.write(buf,0,readNum);
        }
        bytes=bos.toByteArray();
        archivos.add(bytes);
        }
        
        return archivos;
      
        
     }
    
     public static ArrayList<String> extractLabel(File[] files){
         ArrayList<String> aux = new ArrayList<>();
        
        for (int i = 0; i < files.length; i++) {
                
            if (files[i].getAbsolutePath().contains("(SPAM)")) {                
                aux.add("SPAM");
            } else {
               aux.add("HAM");
            }
        }
              
            return aux;
     }

    public Source getSource() {
        return source;
    }

    public void setSource(Source fuente) {
        this.source = fuente;
    }

//    public Timer getTimerAutoUpdate() {
//        return timerAutoUpdate;
//    }
//
//    public void setTimerAutoUpdate(Timer timerAutoUpdate) {
//        this.timerAutoUpdate = timerAutoUpdate;
//    }
        

    public int getCantEmails() {
        return cantEmails;
    }

    public void setCantEmails(int cantEmails) {
        this.cantEmails = cantEmails;
    }

    public Timer getTimerClasify() {
        return timerClasify;
    }

    public void setTimerClasify(Timer timerClasify) {
        this.timerClasify = timerClasify;
    }
    

    public int getFrecEmails() {
        return frecEmails;
    }

    public void setFrecEmails(int frecEmails) {
        this.frecEmails = frecEmails;
    }

    public Object getAditional() {
        return aditional;
    }

    public Timer getTimerChange() {
        return timerChange;
    }
    

    public void setAditional(Object aditional) {
        this.aditional = aditional;
    }
   
    public Timer getTimerLoad() {
        return timerLoad;
    }

    public void setTimerLoad(Timer timerLoad) {
        this.timerLoad = timerLoad;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }
    

     public  File[] extractEmailsBiggerThan(File[] files, int n) {
        ArrayList<File> biggers = new ArrayList<File>();
//        if(String.valueOf(jComboBoxTamaño.getSelectedItem()).equalsIgnoreCase("KB")){
//            n=n*1024;
//        }
//        if(String.valueOf(jComboBoxTamaño.getSelectedItem()).equalsIgnoreCase("MB")){
//            n=n*1024000;
//        }
//        if(String.valueOf(jComboBoxTamaño.getSelectedItem()).equalsIgnoreCase("GB")){
//            n=n*1024000000;
//        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].length() > n) {
                biggers.add(files[i]);
            }
        }
        File[] biggers1 = new File[biggers.size()];
        for (int k = 0; k < biggers.size(); k++) {
            biggers1[k]=biggers.get(k);
        }        
        return biggers1;        
    }
    
    public static ArrayList<Integer> choosePositionsEmails(File[] file, int cant) {
        
        ArrayList<Integer> posEmails = new ArrayList<>();
        for (int i = 0; i < cant; i++) {
            Random ran = new Random();
            posEmails.add(ran.nextInt(file.length));
//            System.out.println("Se escogio el de la posicion: "+posCorreos.get(i));
        }
        
        return posEmails;
    }
    
     public static ArrayList<Email> loadEmails(ArrayList<Integer> pos, File[] files) {
        clasify = new ArrayList<Email>();
       
        for (int i = 0; i < pos.size(); i++) {
            FeaturesExtractor model = new FeaturesExtractor();
            Email email = model.extractEML(files[pos.get(i)].getAbsolutePath());
            EmailAux emailAux=new EmailAux();
            emailAux.setEmail(email);
            if (files[pos.get(i)].getAbsolutePath().contains("(SPAM)")) {
                emailAux.setRealClasification("SPAM");
            } else {
                emailAux.setRealClasification("HAM");
            }
           
            emailAuxs.add(emailAux);
            clasify.add(email);
            //en files1 voy a guardar todos los archivos cargados,esto es una modificaci'on para que no solo cargue correos sino tambien otros archivos
           // files1.add(files[pos.get(i)]);
        }
        return clasify;
        
    }
    
   public static File[] getFiles(String dir_path) {
       
        File f = new File(dir_path);
        File[] arr_content = null;
        
        if (f.isDirectory()) {
            
            ArrayList<String> res = new ArrayList<>();
            arr_content = f.listFiles();

        } else {
            System.err.println("¡ Path NO válido !");
        }
        
        return arr_content;
    }
  
     public ArrayList<Email> ofuscarCorreos(ArrayList<Email> emails,boolean sust) {
  
        
        // String textHeader = "";
         //String textBody = "";
        int cantObf=0;
        int obfusc = 0;
        if(sust){
       Random ramCantObf=new Random();
       cantObf=ramCantObf.nextInt(emails.size());
      
        }
        for (int i = 0; i < emails.size(); i++) {          
            

            //String text = emails.get(i).getHeader().getSubject() + " " + emails.get(i).getBody().getContent();
           
          
            if (sust && cantObf>0) {
               obfusc++;
               String textHeader = emails.get(i).getHeader().getSubject();
               String textBody = " " + emails.get(i).getBody().getContent();
                //textBody = (String) emails.get(i).getBody().getContent();
                
                textHeader = changeLetterI(textHeader);
                textHeader = changeLetterL(textHeader);
                textBody = changeLetterI(textBody);
                textBody = changeLetterL(textBody);
                
                emails.get(i).getHeader().setSubject(textHeader);
                emails.get(i).getBody().setContent(textBody);
                 
            }
            
           
         
         
            
            // System.out.println(textType);
            cantObf--;
        }
        if(obfusc != 0){
          LOGGER.log(Level.INFO, "Perfil:" + getSource().getName() + ", Cantidad de correos ofuscados:" + obfusc);  
        }
        
        return emails;
        
    }
        public String changeLetterL(String body) {
        String newBody = "";
        String[] aux = null;
        if (body.contains("l")) {
            aux = body.split("l");
            for (int i = 0; i < aux.length; i++) {
                if (i == aux.length - 1) {
                    newBody += aux[i];
                    
                } else {
                    newBody += aux[i] + "1";
                }
            }
        } 
        
        return newBody;
    }
    
    public String changeLetterI(String body) {
        
        String newBody = "";
        String[] aux = null;
        if (body.contains("i")) {
            aux = body.split("i");
            for (int i = 0; i < aux.length; i++) {
                if (i == aux.length - 1) {
                    newBody += aux[i];
                    
                } else {
                    newBody += aux[i] + "¡";
                }
            }
        } 
        
        return newBody;
    }
    
     public void calcularEfectividad(ArrayList<EmailAux> lista) {
        
        float precicion = 0;
        float cobertura = 0;
        float exactitud = 0;
        float TrueNegativeRate = 0;
        float TruePositiveRate = 0;
        float exactitudBalanceada = 0;
        String clasifierName = "";
        
        float FP = 0;
        float FN = 0;
        float TP = 0;
        float TN = 0;
        int value = 0;        
        int x = lista.size()/Principal.getClasifiers().size();
        
        for (int a = 0; a < Principal.getClasifiers().size(); a++) {             
         
        for (int i = value; i < lista.size() && i < x; i++) {
            if (lista.get(i).getRealClasification().equalsIgnoreCase("SPAM") && lista.get(i).getGivenClasification().equalsIgnoreCase("SPAM")) {
                TP++;
            }
            if (lista.get(i).getRealClasification().equalsIgnoreCase("HAM") && lista.get(i).getGivenClasification().equalsIgnoreCase("HAM")) {
                TN++;
            }
            if (lista.get(i).getRealClasification().equalsIgnoreCase("HAM") && lista.get(i).getGivenClasification().equalsIgnoreCase("SPAM")) {
                FP++;
            }
            if (lista.get(i).getRealClasification().equalsIgnoreCase("SPAM") && lista.get(i).getGivenClasification().equalsIgnoreCase("HAM")) {
                FN++;
            }
            
        }
        
               
        if (FP != 0) {
            precicion = TP / (TP + FP);
        } else {
            precicion = 1;
        }
        if (FN != 0) {
            cobertura = TP / (TP + FN);
        } else {
            cobertura = 1;
        }
        exactitud = (TP + TN) / (TP + TN + FP + FN);
        exactitudBalanceada = (TrueNegativeRate + TruePositiveRate) / 2;
        TrueNegativeRate = TN / (TN + FP);
        TruePositiveRate = TP / (TP + FN);
        Statistics statisc1 = new Statistics();
        statisc1.setFP(FP);
        statisc1.setFN(FN);
        statisc1.setTN(TN);
        statisc1.setTP(TP);
        statisc1.setAccuracy(exactitud);
        statisc1.setBalancedAccuracy(exactitudBalanceada);
        statisc1.setPrecision(precicion);
        statisc1.setRecall(cobertura);
        statisc1.setTrueNegativeRate(TrueNegativeRate);
        statisc1.setTruePositiveRate(TruePositiveRate);
        statisc1.setSource(this.getSource().getName());
        statisc1.setClasifierName(Principal.getClasifiers().get(a).getName());
        
        
        Principal.statistics.add(statisc1);
        precicion = 0;
        cobertura = 0;
        exactitud = 0;
        exactitudBalanceada = 0;
         
        FP = 0;
        FN = 0;
        TP = 0;
        TN = 0;
        TrueNegativeRate = 0;
        TruePositiveRate = 0;
        
        value = lista.size()/Principal.getClasifiers().size();
        x += lista.size()/Principal.getClasifiers().size();        
        
       }
        
    }
     
      public void sendToClasifiers(ArrayList<Email> emailsToSend) {
          
          Gson gson = new Gson();
          String jsonString = gson.toJson(emailsToSend);
          
       for (int i = 0; i < Principal.getClasifiers().size(); i++) {
           try {
       
		Client client = Client.create();

		WebResource webResource = client
		   .resource(Principal.getClasifiers().get(i).getURL());

		ClientResponse response = webResource.type("application/json")
                   .post(ClientResponse.class, jsonString);

		if (response.getStatus() != 201) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String outputJson = response.getEntity(String.class);
                
                
                JsonParser parser = new JsonParser();
                JsonArray array = parser.parse(outputJson).getAsJsonArray();
                                
                for (JsonElement jsonElement : array) {
                    JsonPrimitive valor = jsonElement.getAsJsonPrimitive();
                    emailsClasifications.add(valor.getAsString());                       
                 }
                System.out.println("De la direccion: " + Principal.getClasifiers().get(i).getURL());   
                System.out.println("La respuesta es: " + emailsClasifications);   
                
                for (int j = 0; j < emailsClasifications.size(); j++) {
                  emailAuxs.get(j).setGivenClasification(emailsClasifications.get(j));
                  EmailAux emailAux=new EmailAux();
                  emailAux=emailAuxs.get(j);
                  emailAux.setClasifierName(Principal.getClasifiers().get(i).getName());
                  emailAuxsComplete.add(emailAux);
                  emailAuxs.remove(0);
                  
               }
                emailsClasifications.clear();
                                               

	  } catch (Exception e) {

		e.printStackTrace();

	  }
       }
       
   }
      public static boolean copyFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
      public void lanzarEventoCargar(String source, int cant){
                            
        LOGGER.log(Level.INFO, "Perfil:" + source + ", Se cargaron:"  +  cant);
      }
   
      public void lanzarEventoEnviar(String source, int cant){
                            
        LOGGER.log(Level.INFO, "Perfil:" + source + ", Se enviaron:"  +  cant);
      }
      public void lanzarEventoVariar(String source, int cant){
                            
        LOGGER.log(Level.INFO, "Perfil:" + source + ", Se cambio a:"  +  cant);
      }
      public static File[] copyFromListToArray(ArrayList<File> lista){
          File[] aux=new File[lista.size()];
          for(int i=0;i<lista.size();i++){
              aux[i]=lista.get(i);
          }
          return aux;
      }
      public void sendFilesToClasifiers(File[] filesToSend) throws IOException {
          
          List<byte[]> archivos = convertirArchivos(filesToSend);                    
                   
                    
                   
                    Gson gson = new Gson();
                    String jsonArray = gson.toJson(archivos);
                 
                    for (int i = 0; i < getClasifiers().size(); i++) {
                        Client client = Client.create();
                        WebResource webResource = client
                                .resource("http://localhost:8080/LearningAntiSpamServer/webresources/clasify/post/receiveFiles");
                        
                        com.sun.jersey.api.client.ClientResponse response = webResource.type("application/json")
                                .post(com.sun.jersey.api.client.ClientResponse.class, jsonArray);
                        
                        
                        if (response.getStatus() != 201) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + response.getStatus());
                        }
                        
                        String outputJson = response.getEntity(String.class);
                        JOptionPane.showMessageDialog(null, "RelevantSentencesExtractorVBeta:  " + " "+ outputJson);
                    }
       
   }

   
}
