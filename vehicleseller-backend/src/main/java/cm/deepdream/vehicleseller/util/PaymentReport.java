package cm.deepdream.vehicleseller.util;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.model.Payment;
import cm.deepdream.vehicleseller.model.Reservation;
import cm.deepdream.vehicleseller.model.Vehicle;

@Component
public class PaymentReport extends AbstractReport {
	private Logger logger = Logger.getLogger(PaymentReport.class.getName()) ;
	
	public Path  generatePayment(Payment payment) throws IOException, BadElementException, DocumentException {
            String fileName = "payment_"+payment.getReference()+".pdf";
            
            logger.log(Level.FINEST, "Fichier cible = "+ fileName);
           
            Document document = new Document(PageSize.A4) ;
           
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            
			document.open() ;			
			document.add(getHeader(new ClassPathResource("images/logo.jpeg").getFile().getAbsolutePath(), ""));
			
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setHorizontalAlignment(Element.ALIGN_CENTER) ;
			titleTable.setSpacingBefore (40.0f) ;
			Phrase titlePhrase = new Phrase("Reçu de paiement".toUpperCase(),
					new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD));
			PdfPCell titleCell = new PdfPCell(titlePhrase);
			titleCell.setBorder(0);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);
			
			PdfPCell subTitle1Cell = new PdfPCell(new Phrase(payment.getPaymentDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			subTitle1Cell.setHorizontalAlignment(Element.ALIGN_CENTER) ;
			subTitle1Cell.setBorder(0);
			titleTable.addCell(subTitle1Cell) ;
		
			document.add(titleTable) ;
			
			Reservation reservation = payment.getBill().getReservation() ;
			Customer customer = reservation.getCustomer() ;
			Vehicle vehicle = reservation.getVehicle() ;
			
			int[] columns = new int[]{2, 3, 2, 3} ;
			
			PdfPTable metadataTable = new PdfPTable(columns.length) ; 
			metadataTable.setSpacingBefore (20.0f) ;
			metadataTable.setWidthPercentage(100.f) ;
			metadataTable.setWidths(columns);
			
			PdfPCell clientLabelCell = new PdfPCell(new Phrase("Nom du client :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			clientLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			clientLabelCell.setBorder(0);
			clientLabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(clientLabelCell);
		
			PdfPCell clientCell = new PdfPCell(new Phrase(String.valueOf(customer.getFirstName()+ " "+customer.getLastName()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			clientCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			clientCell.setBorder(0);
			clientCell.setPaddingBottom(5.0f) ;
			clientCell.setColspan(3) ;
			metadataTable.addCell(clientCell);
			
			PdfPCell phoneLabelCell = new PdfPCell(new Phrase("Numéro de téléphone :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			phoneLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phoneLabelCell.setBorder(0);
			phoneLabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(phoneLabelCell);
		
			PdfPCell phoneCell = new PdfPCell(new Phrase(String.valueOf(customer.getPhoneNumber()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			phoneCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phoneCell.setBorder(0);
			phoneCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(phoneCell);
			
			PdfPCell emailLabelCell = new PdfPCell(new Phrase("Adresse e-mail :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			emailLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			emailLabelCell.setBorder(0);
			emailLabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(emailLabelCell);
		
			PdfPCell emailCell = new PdfPCell(new Phrase(customer.getEmailAddress(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			emailCell.setBorder(0);
			emailCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(emailCell);
			
			PdfPCell reasonLabelCell = new PdfPCell(new Phrase("Objet du paiement :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			reasonLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			reasonLabelCell.setBorder(0);
			reasonLabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(reasonLabelCell);
			
			PdfPCell reasonCell = new PdfPCell(new Phrase(payment.getBill().getLabel(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			reasonCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			reasonCell.setBorder(0);
			reasonCell.setColspan(3) ;
			reasonCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(reasonCell);
			
			PdfPCell amountLabelCell = new PdfPCell(new Phrase("Montant (en lettres) :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			amountLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			amountLabelCell.setBorder(0);
			amountLabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(amountLabelCell);
		
			PdfPCell amountCell = new PdfPCell(
					new Phrase(NumberToLetterFormatter.getInstance().convertToLetters(payment.getAmountWithTaxes().longValue())+" FCFA",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			amountCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			amountCell.setBorder(0);
			amountCell.setColspan(3) ;
			amountCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(amountCell);
			
			PdfPCell amount2LabelCell = new PdfPCell(new Phrase("Montant (en chiffres) :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			amount2LabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			amount2LabelCell.setBorder(0);
			amount2LabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(amount2LabelCell);
		
			PdfPCell amount2Cell = new PdfPCell(
					new Phrase(String.valueOf(payment.getAmountWithTaxes())+ "FCFA",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			amount2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			amount2Cell.setBorder(0);
			amount2Cell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(amount2Cell);
			
			PdfPCell modeLabelCell = new PdfPCell(new Phrase("Mode de paiement :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			modeLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			modeLabelCell.setBorder(0);
			modeLabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(modeLabelCell);
		
			PdfPCell modeCell = new PdfPCell(
					new Phrase(payment.getMode(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			modeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			modeCell.setBorder(0);
			modeCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(modeCell);
			
			PdfPCell referenceLabelCell = new PdfPCell(new Phrase("Référence :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			referenceLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			referenceLabelCell.setBorder(0);
			referenceLabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(referenceLabelCell);
		
			PdfPCell referenceCell = new PdfPCell(new Phrase(payment.getReference(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			referenceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			referenceCell.setBorder(0);
			referenceCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(referenceCell);
			
			PdfPCell phoneNumberLabelCell = new PdfPCell(new Phrase("Numéro de téléphone :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			phoneNumberLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phoneNumberLabelCell.setBorder(0);
			phoneNumberLabelCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(phoneNumberLabelCell);
		
			PdfPCell phoneNumberCell = new PdfPCell(new Phrase(payment.getPhoneNumber(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			phoneNumberCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phoneNumberCell.setBorder(0);
			phoneNumberCell.setPaddingBottom(5.0f) ;
			metadataTable.addCell(phoneNumberCell);
			
			document.add(metadataTable) ;

			PdfPTable signatureTable = new PdfPTable(1) ; 
			signatureTable.setSpacingBefore (60.0f) ;
			signatureTable.setHorizontalAlignment(Element.ALIGN_RIGHT) ;
			signatureTable.setWidthPercentage(30.f) ;
			
			PdfPCell managerLabelCell = new PdfPCell(new Phrase("Le Gérant",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			managerLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			managerLabelCell.setBorder(0);
			signatureTable.addCell(managerLabelCell);
			
			Image image = Image.getInstance(new ClassPathResource("images/signature_manager.jpg").getFile().getAbsolutePath()) ;
	        PdfPCell symbolCell = new PdfPCell(image) ;
	        symbolCell.setHorizontalAlignment(Element.ALIGN_CENTER) ;
	        symbolCell.setVerticalAlignment(Element.ALIGN_MIDDLE) ;
	        symbolCell.setBorder(0);
	        signatureTable.addCell(symbolCell);
		
			PdfPCell managerCell = new PdfPCell(new Phrase("Prepore KARCHE",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			managerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			managerCell.setBorder(0);
			signatureTable.addCell(managerCell);
			
			document.add(signatureTable) ;
			
			document.close() ;
			
		    return Paths.get(fileName) ;
	}
	
	
	
}
