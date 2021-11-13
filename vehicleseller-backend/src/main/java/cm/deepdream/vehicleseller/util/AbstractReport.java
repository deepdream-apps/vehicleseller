package cm.deepdream.vehicleseller.util;
import java.io.IOException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public  class AbstractReport {
	
	
	protected  PdfPTable getHeader(String logoFileName,   String companyName) throws IOException, BadElementException {
        PdfPTable tableHeader = new PdfPTable(1) ;
        tableHeader.setWidthPercentage(40.0f);
        tableHeader.setSpacingAfter(15.0f) ;
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER) ;
        
        Image image = Image.getInstance(logoFileName) ;
        PdfPCell logoCell = new PdfPCell(image) ;
        logoCell.setHorizontalAlignment(Element.ALIGN_CENTER) ;
        logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE) ;
        logoCell.setBorder(0);
        tableHeader.addCell(logoCell);
            
        Phrase titlePhrase = new Phrase(companyName, new Font(FontFamily.TIMES_ROMAN, 15.0f, Font.BOLD)) ;
        PdfPCell titleCell = new PdfPCell(titlePhrase) ;
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE) ;
        titleCell.setBorder(0);
        tableHeader.addCell(titleCell);
        
        return tableHeader ;
	}
	

}
