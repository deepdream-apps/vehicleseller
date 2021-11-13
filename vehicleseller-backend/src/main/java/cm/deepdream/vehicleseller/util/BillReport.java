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
import com.itextpdf.text.BaseColor;
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

import cm.deepdream.vehicleseller.model.Bill;
import cm.deepdream.vehicleseller.model.Customer;
import cm.deepdream.vehicleseller.model.Vehicle;

@Component
public class BillReport extends AbstractReport {
	private Logger logger = Logger.getLogger(BillReport.class.getName()) ;
	
	public Path  generateBill(Bill bill) throws IOException, BadElementException, DocumentException {
            String fileName = "bill_"+bill.getReference()+".pdf";
            
            logger.log(Level.FINEST, "Fichier cible = "+ fileName);
           
            Document document = new Document(PageSize.A4) ;
           
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            
			document.open() ;			
			document.add(getHeader(new ClassPathResource("images/logo.jpeg").getFile().getAbsolutePath(), ""));
			
			PdfPTable titleTable = new PdfPTable(1);
			titleTable.setHorizontalAlignment(Element.ALIGN_CENTER) ;
			titleTable.setSpacingBefore (40.0f) ;
			Phrase titlePhrase = new Phrase("FACTURE",
					new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD));
			PdfPCell titleCell = new PdfPCell(titlePhrase);
			titleCell.setBorder(0);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleTable.addCell(titleCell);
			
			PdfPCell subTitle1Cell = new PdfPCell(new Phrase(bill.getBillDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			subTitle1Cell.setHorizontalAlignment(Element.ALIGN_CENTER) ;
			subTitle1Cell.setBorder(0);
			titleTable.addCell(subTitle1Cell) ;
		
			
			document.add(titleTable) ;
			
			
			Customer customer = bill.getReservation().getCustomer() ;
			Vehicle vehicle = bill.getReservation().getVehicle() ;
			
			int[] columns = new int[]{2, 4, 2, 3} ;
			
			PdfPTable metadataTable = new PdfPTable(columns.length) ; 
			metadataTable.setSpacingBefore (20.0f) ;
			metadataTable.setWidthPercentage(100.f) ;
			metadataTable.setWidths(columns);
			
			PdfPCell clientLabelCell = new PdfPCell(new Phrase("Nom du client :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			clientLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			clientLabelCell.setBorder(0);
			metadataTable.addCell(clientLabelCell);
		
			PdfPCell clientCell = new PdfPCell(new Phrase(String.valueOf(customer.getFirstName()+ " "+customer.getLastName()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			clientCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			clientCell.setBorder(0);
			metadataTable.addCell(clientCell);
			
			PdfPCell vehicleLabelCell = new PdfPCell(new Phrase("Modèle du véhicule :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			vehicleLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			vehicleLabelCell.setBorder(0);
			metadataTable.addCell(vehicleLabelCell);
			
			PdfPCell modelCell = new PdfPCell(new Phrase(vehicle.getModel().getLabel(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			modelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			modelCell.setBorder(0);
			metadataTable.addCell(modelCell);
			
			PdfPCell phoneLabelCell = new PdfPCell(new Phrase("Numéro de téléphone :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			phoneLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phoneLabelCell.setBorder(0);
			metadataTable.addCell(phoneLabelCell);
		
			PdfPCell phoneCell = new PdfPCell(new Phrase(String.valueOf(customer.getPhoneNumber()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			phoneCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phoneCell.setBorder(0);
			metadataTable.addCell(phoneCell);
			
			PdfPCell registrationNumberLabelCell = new PdfPCell(new Phrase("Numéro d'immatriculation :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			registrationNumberLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			registrationNumberLabelCell.setBorder(0);
			metadataTable.addCell(registrationNumberLabelCell);
			
			PdfPCell registrationNumberCell = new PdfPCell(new Phrase(String.valueOf(vehicle.getRegistrationNumber()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			registrationNumberCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			registrationNumberCell.setBorder(0);
			metadataTable.addCell(registrationNumberCell);
			
			PdfPCell emailLabelCell = new PdfPCell(new Phrase("Adresse e-mail :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			emailLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			emailLabelCell.setBorder(0);
			metadataTable.addCell(emailLabelCell);
		
			PdfPCell emailCell = new PdfPCell(new Phrase(customer.getEmailAddress(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			emailCell.setBorder(0);
			metadataTable.addCell(emailCell);
			
			PdfPCell seatsLabelCell = new PdfPCell(new Phrase("Nombre de places :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			seatsLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			seatsLabelCell.setBorder(0);
			metadataTable.addCell(seatsLabelCell);
			
			PdfPCell seatsCell = new PdfPCell(new Phrase(String.valueOf(vehicle.getSeats()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			seatsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			seatsCell.setBorder(0);
			metadataTable.addCell(seatsCell);
			
			PdfPCell professionLabelCell = new PdfPCell(new Phrase("Profession :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			professionLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			professionLabelCell.setBorder(0);
			metadataTable.addCell(professionLabelCell);
		
			PdfPCell professionCell = new PdfPCell(new Phrase(String.valueOf(customer.getProfession()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			professionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			professionCell.setBorder(0);
			metadataTable.addCell(professionCell);
			
			PdfPCell colorLabelCell = new PdfPCell(new Phrase("Couleur :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			colorLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			colorLabelCell.setBorder(0);
			metadataTable.addCell(colorLabelCell);
			
			PdfPCell colorCell = new PdfPCell(new Phrase(vehicle.getColor(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			colorCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			colorCell.setBorder(0);
			metadataTable.addCell(colorCell);
			
			PdfPCell townLabelCell = new PdfPCell(new Phrase("Ville de résidence :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			townLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			townLabelCell.setBorder(0);
			metadataTable.addCell(townLabelCell);
		
			PdfPCell townCell = new PdfPCell(new Phrase(String.valueOf(customer.getTown()+", "+customer.getCountry()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			townCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			townCell.setBorder(0);
			metadataTable.addCell(townCell);
			
			
			PdfPCell categoryLabelCell = new PdfPCell(new Phrase("Catégorie :",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			categoryLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			categoryLabelCell.setBorder(0);
			metadataTable.addCell(categoryLabelCell);
		
			PdfPCell categoryCell = new PdfPCell(new Phrase(vehicle.getCategory(),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL)));
			categoryCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			categoryCell.setBorder(0);
			metadataTable.addCell(categoryCell);
			
			
			document.add(metadataTable) ;
			
			PdfPTable bodyTable = new PdfPTable(5) ; 
			bodyTable.setSpacingBefore (30.0f) ;	
			bodyTable.setHorizontalAlignment(Element.ALIGN_CENTER) ;
			bodyTable.setWidthPercentage(100.f) ;
			bodyTable.setWidths(new float[]{10,25,15,15,15});

			
			Phrase matriculeLabelPhrase = new Phrase("REF",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD));
			PdfPCell matriculeLabelCell = new PdfPCell(matriculeLabelPhrase);
			matriculeLabelCell.setBackgroundColor(BaseColor.LIGHT_GRAY) ;
			matriculeLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bodyTable.addCell(matriculeLabelCell);
			
			
			Phrase labelLabelPhrase = new Phrase("Libellé",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD));
			PdfPCell labelLabelCell = new PdfPCell(labelLabelPhrase);
			labelLabelCell.setBackgroundColor(BaseColor.LIGHT_GRAY) ;
			labelLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bodyTable.addCell(labelLabelCell);
			
			Phrase amountWhTPhrase = new Phrase("Montant HT",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD));
			PdfPCell amountWhTLabelCell = new PdfPCell(amountWhTPhrase);
			amountWhTLabelCell.setBackgroundColor(BaseColor.LIGHT_GRAY) ;
			amountWhTLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bodyTable.addCell(amountWhTLabelCell);
			
			Phrase taxesLabelPhrase = new Phrase("Taxes",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD));
			PdfPCell taxesLabelCell = new PdfPCell(taxesLabelPhrase);
			taxesLabelCell.setBackgroundColor(BaseColor.LIGHT_GRAY) ;
			taxesLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bodyTable.addCell(taxesLabelCell);
			
			Phrase amountWtTPhrase = new Phrase("Montant TTC",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD));
			PdfPCell amountWtTLabelCell = new PdfPCell(amountWtTPhrase);
			amountWtTLabelCell.setBackgroundColor(BaseColor.LIGHT_GRAY) ;
			amountWtTLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			bodyTable.addCell(amountWtTLabelCell);
			
			PdfPCell _referenceCell = new PdfPCell(new Phrase(bill.getReference() , new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL))) ;
			_referenceCell.setHorizontalAlignment(Element.ALIGN_MIDDLE) ;
			bodyTable.addCell(_referenceCell);
			
			PdfPCell _labelCell = new PdfPCell(new Phrase(bill.getLabel() , new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL))) ;
			_labelCell.setHorizontalAlignment(Element.ALIGN_LEFT) ;
			bodyTable.addCell(_labelCell);
			
			PdfPCell _amountHTCell = new PdfPCell(new Phrase(String.valueOf(bill.getAmountWithoutTaxes()) , new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL))) ;
			_amountHTCell.setHorizontalAlignment(Element.ALIGN_RIGHT) ;
			bodyTable.addCell(_amountHTCell);
			
			PdfPCell _taxesCell = new PdfPCell(new Phrase(String.valueOf(bill.getTaxesAmount()) , new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL))) ;
			_taxesCell.setHorizontalAlignment(Element.ALIGN_RIGHT) ;
			bodyTable.addCell(_taxesCell);
			
			PdfPCell _amountTTCCell = new PdfPCell(new Phrase(String.valueOf(bill.getAmountWithTaxes()) , new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.NORMAL))) ;
			_amountTTCCell.setHorizontalAlignment(Element.ALIGN_RIGHT) ;
			bodyTable.addCell(_amountTTCCell);
			
			document.add(bodyTable) ;
				
			PdfPTable summaryTable = new PdfPTable(2) ; 
			summaryTable.setSpacingBefore (20.0f) ;
			summaryTable.setHorizontalAlignment(Element.ALIGN_RIGHT) ;
			summaryTable.setWidthPercentage(45.f) ;
			summaryTable.setWidths(new int[]{1, 1});
			
			PdfPCell brutLabelCell = new PdfPCell(new Phrase("Net à Payer (FCFA)",
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			brutLabelCell.setBackgroundColor(BaseColor.LIGHT_GRAY) ;
			brutLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			summaryTable.addCell(brutLabelCell);
		
			PdfPCell salCell = new PdfPCell(new Phrase(String.valueOf(bill.getAmountWithTaxes()),
					new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD)));
			salCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			summaryTable.addCell(salCell);
			
			document.add(summaryTable) ;
			
			PdfPTable signatureTable = new PdfPTable(1) ; 
			signatureTable.setSpacingBefore (40.0f) ;
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
