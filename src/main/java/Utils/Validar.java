package UI;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;


public class Validar {
	protected static Toolkit getToolkit() {
		return Toolkit.getDefaultToolkit();
	}

	public static void validarDigito(JTextField src) {
		src.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) ||(c == KeyEvent.VK_BACK_SPACE) ||(c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}
	
	public static void validarDigitoYComa(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) ||
						(c == '.') || (c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}
	
	public static void validarDigitoYComaYNegacion(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == '-') ||
						(c == '.') || (c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}
	public static void validarLetra(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isLetter(c) ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE) ||
						(Character.isWhitespace(c))))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}

	public static void validarCantDigitos(final JTextField textField,final int cantDigitos){
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				System.out.println("keyTyped()"); 
				if(textField.getText().length() > cantDigitos) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}
	
	public static String  MensgeError(String text){
		String error="";
		int pos=text.indexOf("W");
		error=text.substring(0,pos);
		return error;
	}

}
