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
    private AttributeSet attrKeyWords;
    private AttributeSet attrNonKeyWords;
    
    private String keyWords;
	
    public CometEditorDocument(SymbolTable st) {
    	
    	cont = StyleContext.getDefaultStyleContext();
    	attrKeyWords = cont.addAttribute(
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

		int offs = 0;
		
        String text = getText(0, getLength());
        String[] tokens = text.split("\\W");
        
        for(String token : tokens) {
        	if(token.matches("(" + keyWords + ")"))
        		setCharacterAttributes(offs, offs + token.length(), attrKeyWords, false);
        	else
        		setCharacterAttributes(offs, offs + token.length(), attrNonKeyWords, false);
        	offs += token.length() + 1;
        }
 
    }
	
	@Override
	public void remove (int offs, int len) throws BadLocationException {
        super.remove(offs, len);

        int offset = 0;
		
        String text = getText(0, getLength());
        String[] tokens = text.split("\\W");
        
        for(String token : tokens) {
        	if(token.matches("(" + keyWords + ")"))
        		setCharacterAttributes(offset, offset + token.length(), attrKeyWords, false);
        	else
        		setCharacterAttributes(offset, offset + token.length(), attrNonKeyWords, false);
        	offset += token.length() + 1;
        }
    }
	
}
