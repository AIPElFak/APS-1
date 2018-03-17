package guicomponents;

import java.awt.Color;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import languages.SymbolTable;

public class CometEditorDocument extends DefaultStyledDocument  {
	
	private static final long serialVersionUID = 1L;
	
	private StyleContext cont;
    private AttributeSet attr;
    private AttributeSet attrNonKeyWords;
    
    private String keyWords;
	
    public CometEditorDocument(SymbolTable st) {
    	
    	cont = StyleContext.getDefaultStyleContext();
    	attr = cont.addAttribute(
    			cont.getEmptySet(),
    			StyleConstants.Foreground,
    			new Color(247, 121, 121));
    	attrNonKeyWords = cont.addAttribute(
    			cont.getEmptySet(),
    			StyleConstants.Foreground,
    			new Color(238, 238, 255));
    	keyWords = getKeyWords(st);
    	
    }
    
	private String getKeyWords(SymbolTable st) {
		String keyWords = "";
		for(int i = 0; i < st.getKeyWords().size() - 1; i++)
			keyWords += st.getKeyWords().get(i) + "|";
		keyWords += st.getKeyWords().get(st.getKeyWords().size() - 1);
		return keyWords;
	}
	
	@Override
	public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
        
		super.insertString(offset, str, a);

        String text = getText(0, getLength());
        int before = findLastNonWordChar(text, offset);
        if (before < 0) before = 0;
        int after = findFirstNonWordChar(text, offset + str.length());
        int wordL = before;
        int wordR = before;

        while (wordR <= after) {
            if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                if (text.substring(wordL, wordR).matches("(\\W)*(" + keyWords + ")"))
                    setCharacterAttributes(wordL, wordR - wordL, attr, false);
                else
                    setCharacterAttributes(wordL, wordR - wordL, attrNonKeyWords, false);
                wordL = wordR;
            }
            wordR++;
        }
    }
	
	@Override
	public void remove (int offs, int len) throws BadLocationException {
        super.remove(offs, len);

        String text = getText(0, getLength());
        int before = findLastNonWordChar(text, offs);
        if (before < 0) before = 0;
        int after = findFirstNonWordChar(text, offs);

        if (text.substring(before, after).matches("(\\W)*(" + keyWords + ")")) {
            setCharacterAttributes(before, after - before, attr, false);
        } else {
            setCharacterAttributes(before, after - before, attrNonKeyWords, false);
        }
    }
	
	
	private int findLastNonWordChar (String text, int index) {
        while (--index >= 0)
            if (String.valueOf(text.charAt(index)).matches("\\W")) break;
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) break;
            index++;
        }
        return index;
    }
	
}
