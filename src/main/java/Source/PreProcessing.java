/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import Tools.LanguageDetector;
import edu.stanford.nlp.coref.data.WordLists;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreNLPProtos.Language;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Unknown
 */
public class PreProcessing {
    private StanfordCoreNLP pipeline;
    private Properties props;
    private Set<String> stopWords;
    private Language language;

    public PreProcessing() {
        this.language = Language.Unknown;
    }

    //Solo está para inglés
    public void initialize(String text){
        props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        setLanguage(detectLanguage(text));
        pipeline = new StanfordCoreNLP(props);
    }
    
    public void initialize(Language language) {
        props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        setLanguage(language);
        pipeline = new StanfordCoreNLP(props);
    }
    
    private void setLanguage(Language language) {
        switch(language) {
            case English:
                stopWords = WordLists.stopWordsEn; break;
//            case Spanish:
//                props.setProperty("tokenize.language", "en");
//                props.setProperty("sentiment.model", "edu/stanford/nlp/international/spanish");
//                props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/spanish/spanish-distsim.tagger");
//                props.setProperty("ner.model", "edu/stanford/nlp/models/ner/spanish.ancora.distsim.s512.crf.ser.gz");
//                props.setProperty("ner.applyNumericClassifiers", "false");
//                props.setProperty("ner.useSUTime", "false");
//                stopWords = WordLists.stopWordsEs;
//                break;
            default:
                throw new UnsupportedOperationException("Not supported language");
        }
    }
    
    private Language detectLanguage(String text) {
        return language = LanguageDetector.detect(text);
    }
        
    public String getLanguage() {
        switch(language) {
            case English:
                return "en";
            case Spanish:
                return "es";
            default:
                return "";
        }
    }

    //elimina los caracteres especiales del texto a procesar
    public String clean(String text){
        return text.replaceAll("[^ a-zA-Z0-9']+", "");
    }
    
    //tokeniza el texto segun las tags requeridas
    public List<String> tokenizer(String text, String... tag) {
        List<String> result = new LinkedList<String>();
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            for(CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String partOfSpeechTag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                for(int i = 0, fin = tag.length; i < fin; i ++) {
                    if(partOfSpeechTag.equals(tag[i])) {
                        result.add(token.originalText());
                    }
                }
            }
        }
        return result;
    }
    
    //remueve las stopwords
    public String removeStopWords(String text) {
        String result = text;
        for(String stopWord : stopWords) {
            result = result.replaceAll(" " + stopWord + " ", " ");
        }
        return result;
    }

    //funcion para stemming de las palabras contenidas
//    public List<String> stemmer(List<String> words) {
//        List<String> result = new ArrayList<String>();
//        Stemmer stemmer = new Stemmer();
//        for(String word : words) {
//            result.add(stemmer.stem(word));
//        }
//        return result;
//    }

    // funcion para lematizar el texto
//    public List<String> lemmatizer(String text) {
//        List<String> result = new ArrayList<String>();
//        Annotation document = pipeline.process(text);
//        for(CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
//            for(CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
//                // String word = token.get(CoreAnnotations.TextAnnotation.class);      
//                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
//                result.add(lemma);
//            }
//        }
//        return result;
//    }

}
