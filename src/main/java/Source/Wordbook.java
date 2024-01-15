/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

/**
 *
 * @author Unknown
 */
import Model.Word;
import Tools.Files;
import edu.stanford.nlp.pipeline.CoreNLPProtos;
import java.io.File;
import java.util.List;

public class Wordbook {
    private static PreProcessing preProcessing;
    private static boolean init = false;
    private static boolean loadVerified = false;
    private static Word[] words;
        
    // iniciar el Aprendizaje
    public static void init(File words,File spamPath,File hamPath) throws Exception {
        Wordbook.preProcessing = new PreProcessing();
        Wordbook.preProcessing.initialize(CoreNLPProtos.Language.English);
        initVocabulary(words);
        initWordbook(spamPath, hamPath);
        saveChanges();
        loadVerified = true;
        init = true;
    }
     
    // Cargar las palabras del vocabulario 
    private static void initVocabulary(File file) throws Exception{
        List<String> words = Files.loadText(file);
        Wordbook.words = new Word[words.size()];
        for(int i = 0; i < words.size(); i ++) {
            Wordbook.words[i] = new Word(words.get(i));
        }
    }
    
    // Cargar dataset ( SPAM / HAM ) 
    private static void initWordbook(File spamPath,File hamPath){
        File[] spams = spamPath.listFiles();
        File[] hams = hamPath.listFiles();
        loadEmails(spams,new Increase() {
            @Override public void word(Word word) {
                word.increaseSpamCount();
            }
        });
        loadEmails(hams,new Increase() {
            @Override public void word(Word word) {
                word.increaseHamCount();
            }
        });
    }
    
    // Cargar lista de correos del dataset
    private static void loadEmails(File[] files, Increase increase) {
        for(File file : files) {
            FeaturesExtractor extractor = new FeaturesExtractor();
            String text = extractor.extractISO_8859_1(file);
            List<String> words = preProcessing.tokenizer(text,
            "JJ","JJR","JJS","MD","NN","NNS","NNP","NNPS","RBR",
            "RBS","VB","VBD","VBG","VBN","VBP","VBZ");
            for(String word : words) {
                Word w = findWord(word);
                if(w != null){
                    increase.word(w);
                }
            }
        }
    }
    
    // Cargar las palabras
    public static Word[] getWords() {
        if(!loadVerified){
            loadData();
        }
        return (init)? words : null;  
    }
    
    // Cargar archivo wordbook.dat
    private static void loadData(){
        File file = new File("wordbook.dat");
        try { 
            if(file.exists()){
                words = Files.loadFile(file);
                init = true;
            }
            loadVerified = true;
        }
        catch(Exception ex) {
            throw new Error("File error: "+file.getPath());
        }
    }
    
    // Guardar los cambios
    public static void saveChanges() throws Exception {
        Files.writeFile(Wordbook.words,"wordbook.dat");
    }
    
    // Encontrar una palabra el la lista
    private static Word findWord(String word){
        for(int i = 0; i < words.length; i++) {
            if(words[i].getText().equalsIgnoreCase(word)){
                return words[i];
            }
        }
        return null;
    }
 
}

abstract class Increase {
    public abstract void word(Word word);
}