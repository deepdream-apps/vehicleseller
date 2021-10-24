package cm.deepdream.vehicleseller.util;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Component
public class FileStore {

	@Autowired
	private  AmazonS3 amazonS3;

    public void upload (String path, String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            amazonS3.putObject(path, fileName, inputStream, objectMetadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    public byte[] download(String path, String key) throws IOException {
            S3Object object = amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(objectContent) ;
            
            Path logoPath = Paths.get(path, key) ;
            if(! Files.exists(logoPath, LinkOption.NOFOLLOW_LINKS)) 
            	Files.createFile(logoPath); 
            
            writeStream(logoPath.toFile().getAbsolutePath(), bytes) ;
            return bytes ;
    }
    
    private void writeStream (String fileName, byte[] bytes) throws IOException{
    	try(FileOutputStream outputStream = new FileOutputStream(fileName) ;){
        	outputStream.write(bytes);
        }catch(IOException ex) {
        	throw ex ;
        }
    }
}
