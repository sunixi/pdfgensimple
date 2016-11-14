package pdfgensimple;





import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.nio.file.Files;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class PdfGenSimple  {
	
    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "bmp","JPG" // and other formats you need
        };
    
 // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    
                    
    public static void main(String[] args) {
       // form where you want to select folder 
       File selectedImageDirectory =new File("images");
       if(!selectedImageDirectory.exists()){
    	   selectedImageDirectory.mkdirs();
       }
    // form where you want to save pdf folder
       File selectedPdfDirectory =new File("images/pdf");
       if(!selectedPdfDirectory.exists()){
    	   selectedPdfDirectory.mkdirs();
       }
     
                                
    try {
        //here direcotry is accessed and files should be chooosed ..
            if (selectedImageDirectory.isDirectory() && selectedPdfDirectory.isDirectory()) { // make sure it's a directory
                for (final File f : selectedImageDirectory.listFiles(IMAGE_FILTER)) {
                    
                   
                    byte[] imageInByte = Files.readAllBytes(f.toPath());
                        
                        Document document = new Document();
                            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(selectedPdfDirectory.getAbsolutePath()+"/"+f.getName()+".pdf"));
                            document.open();
                            document.add(new Paragraph("Some content here"));
                         
                            //Set attributes here
                            document.addAuthor("Lokesh Gupta");
                            document.addCreationDate();
                            document.addCreator("HowToDoInJava.com");
                            document.addTitle("Set Attribute Example");
                            document.addSubject("An example to show how attributes can be added to pdf files.");
                            
                            
                          //Add Image
                            Image image1 = Image.getInstance(imageInByte);
                            //Fixed Positioning
                            image1.setAbsolutePosition(100f, 550f);
                            //Scale to new height and new width of image
                            image1.scaleAbsolute(200, 200);
                            //Add to document
                            document.add(image1);
                            document.close();
                            writer.close();

                        // you probably want something more involved here
                        // to display in your UI
                        System.out.println("image: " + f.getName());
                        System.out.println(" size  : " + f.length());
                    } 
                }
                                    
                      
                            		
        						
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                            
                            
                        }
                        
                        
                                
                                    
                           
                        
                        
                        
                        
                   
    
}
	

