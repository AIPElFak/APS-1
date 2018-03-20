package guicomponents;

import java.awt.Color;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import languages.SymbolTable;

class CometEditorDocument extends DefaultStyledDocument  {
	
	private static final long serialVersionUID = 1L;
	
	private StyleContext cont;
    private AttributeSet attrKeyWords;
    private AttributeSet attrNumbers;
    private AttributeSet attrNonKeyWords;
    
    private String keyWords;
	
    public CometEditorDocument(SymbolTable st) {
    	
    	cont = StyleContext.getDefaultStyleContext();
    	attrKeyWords = cont.addAttribute(
    			cont.getEmptySet(),
    			StyleConstants.Foreground,
    			new Color(247, 121, 121));
    	attrNumbers = cont.addAttribute(
    			cont.getEmptySet(),
    			StyleConstants.Foreground,
    			new Color(120, 191, 255));
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
		int leftIndex = findLastNonWordChar(text, offset);
		int rightIndex = findLastNonWordChar(text, offset + str.length());
		
		if(leftIndex == rightIndex) return;
		if(leftIndex < 0) leftIndex = 0;
		else leftIndex++;
		
		System.out.println(text.substring(leftIndex, rightIndex));
		
		int i = leftIndex;
		int j = rightIndex - leftIndex;
		
		if(text.substring(leftIndex, rightIndex).matches("\\n*(" + keyWords + ")"))
    		setCharacterAttributes(i, j, attrKeyWords, false);
		else if(text.substring(leftIndex, rightIndex).matches("-?\\d+"))
    		setCharacterAttributes(i, j, attrNumbers, false);
		else
			setCharacterAttributes(i, j, attrNonKeyWords, false);
 
    }
	
	@Override
	public void remove (int offs, int len) throws BadLocationException {
        super.remove(offs, len);

        String text = getText(0, getLength());
		int leftIndex = findLastNonWordChar(text, offs);
		int rightIndex = findLastNonWordChar(text, offs + len);
		
		if(leftIndex == rightIndex) return;
		if(leftIndex < 0) leftIndex = 0;
		else leftIndex++;
		
		System.out.println(text.substring(leftIndex, rightIndex));
		
		int i = leftIndex;
		int j = rightIndex - leftIndex;
		
		if(text.substring(leftIndex, rightIndex).matches("\\n*(" + keyWords + ")"))
    		setCharacterAttributes(i, j, attrKeyWords, false);
		else if(text.substring(leftIndex, rightIndex).matches("-?\\d+"))
    		setCharacterAttributes(i, j, attrNumbers, false);
		else
			setCharacterAttributes(i, j, attrNonKeyWords, false);
       
    }
	
	private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
	
}
