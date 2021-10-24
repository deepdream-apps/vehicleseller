package cm.deepdream.vehicleseller.util;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;

@Component
public class LocalFileStore {

    public void upload (String path, String subPath1, String subPath2, String fileName,  byte[] bytes)throws IOException {
    	
    	if(! Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS))
    		Files.createDirectory(Paths.get(path));
    	
    	if(! Files.exists(Paths.get(path, subPath1), LinkOption.NOFOLLOW_LINKS))
    		Files.createDirectory(Paths.get(path, subPath1));
    	
    	if(! Files.exists(Paths.get(path, subPath1, subPath2), LinkOption.NOFOLLOW_LINKS))
    		Files.createDirectory(Paths.get(path, subPath1, subPath2));
    	
	    Path logoPath = Paths.get(path,  subPath1, subPath2, fileName) ;
	    
	    if(! Files.exists(logoPath, LinkOption.NOFOLLOW_LINKS))
	    	Files.createFile(logoPath);
        
	    try (FileOutputStream outputStream = new FileOutputStream(logoPath.toFile()) ;){
           outputStream.write(bytes);
           outputStream.close();
        } catch (IOException ex) {
             throw ex ;
        }
    }
    
    

    public byte[] download(String path, String subPath1, String subPath2, String fileName) throws IOException{
        try {
        	Path logoPath = Paths.get(path,  subPath1, subPath2, fileName) ;
        	byte[] allBytes = Files.readAllBytes(logoPath);
        	return allBytes ;
        } catch (IOException ex) {
           throw ex;
        }
    }
    
}
