/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import edu.stanford.nlp.pipeline.CoreNLPProtos.Language;
import org.apache.tika.language.LanguageIdentifier;

/**
 *
 * @author Unknown
 */
public class LanguageDetector {

    public static Language detect(String text) {
        LanguageIdentifier idl = new LanguageIdentifier(text);
        String language = idl.getLanguage();

        Language detected = null;
        if("es".equals(language)) {
            detected = Language.Spanish;
        }
        else if("en".equals(language)) {
            detected = Language.English;
        }
        else {
            //throw new UnsupportedOperationException("Not supported language");
        }
        return detected;
    }

}
