package at.fhv;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;


public class CreatePDFDocument {

    public static void createFile(String pdfDocPath, String name, String damageType, Integer damageAmount) throws IOException {

        PdfWriter writer = new PdfWriter(pdfDocPath);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(70f, 70f, 70f, 70f);

        //Erstellen von Text
        Text companyName = new Text("Insurance GmbH | Hochschulstraße 1 | 6850 Dornbirn").setFontSize(9).setUnderline();
        Text personData = new Text(name +"\nMusterstraße 1"+"\n6800 Feldkirch").setFontSize(9);
        Text date = new Text("Date:   "
                +LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).setFontSize(9);
        Text remuneration = new Text("Remuneration\n").setFontSize(12).setBold();
        Text endText = new Text("\nWe issue your remuneration of the case: "+damageType +"."
                +"\nDamage amount: " +damageAmount
                +"\nWe have transferred the amount to your bank account.").setFontSize(11);
        Text greetings = new Text("Best regards!").setFontSize(11);
        Text team = new Text("Insurance GmbH").setFontSize(11);

        //Erstellen von Paragraphs
        Paragraph paragraph1 = new Paragraph().add(companyName);
        Paragraph paragraph2 = new Paragraph().add(personData);
        Paragraph paragraph3 = new Paragraph().setTextAlignment(TextAlignment.RIGHT).add(date);
        Paragraph paragraph4 = new Paragraph().add(remuneration);
        Paragraph paragraph6 = new Paragraph().add(endText);
        Paragraph paragraph7 = new Paragraph().add(greetings);
        Paragraph paragraph8 = new Paragraph().add(team);


        //Einfügen der Paragraphe zu dem Dokument
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph6);
        document.add(paragraph7);
        document.add(paragraph8);

        TextFooterEventHandler eventHandler = new TextFooterEventHandler(document);

        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, eventHandler);

        eventHandler.lastPage = pdf.getLastPage();

        document.close();

        System.out.println("Document got created!");

    }

    static class TextFooterEventHandler implements IEventHandler {
        Document doc;
        PdfPage lastPage = null;

        public TextFooterEventHandler(Document doc) {
            this.doc = doc;
        }

        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfCanvas canvas = new PdfCanvas(docEvent.getPage());
            canvas.beginText();
            try {
                canvas.setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 9);
            } catch (IOException e) {
                e.printStackTrace();
            }
            canvas.beginText()
                    .moveText((doc.getRightMargin()+5), (doc.getBottomMargin()+50))
                    .showText("Telefon:   +464546516                                                  Insurance GmbH")
                    .moveText(0, (doc.getBottomMargin()-80))
                    .showText("Fax:         01234/987655                                                  Bank Loophausen")
                    .moveText(0, (doc.getBottomMargin()-90))
                    .showText("Email:       insurance_gmbh@gmail.com                   BLZ: 30033301n")
                    .moveText(0, (doc.getBottomMargin()-80))
                    .showText("Web:         www.insurance-gmbh.com                                       KTO: 32165498")
                    .endText()
                    .release();

        }
    }
}