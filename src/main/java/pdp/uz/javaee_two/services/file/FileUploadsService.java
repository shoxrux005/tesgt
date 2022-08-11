package pdp.uz.javaee_two.services.file;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.util.StringUtils;
import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.dao.Dao;
import pdp.uz.javaee_two.dao.fileDao.FileDao;
import pdp.uz.javaee_two.domains.Uploads;

import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class FileUploadsService implements Dao {

    private static FileUploadsService instance;
    private final FileDao fileDao = ApplicationContextHolder.getBean(FileDao.class);

    public static FileUploadsService getInstance() {
        if (instance == null) {
            instance = new FileUploadsService();
        }
        return instance;
    }

    public Uploads uploadCover(Part part) throws IOException {

        String contentType = part.getContentType();
        String originalFilename = part.getSubmittedFileName();
        long size = part.getSize();
        String filename = StringUtils.getFilename(originalFilename);
        String filenameExtension = StringUtils.getFilenameExtension(originalFilename);
        String generatedName = System.currentTimeMillis() + "." + "png";
        String rootPath = "/home/shoxrux/IdeaProjects/apps/uploads/" + generatedName;


        Uploads uploads = Uploads
                .builder()
                .contentType(contentType)
                .originalName(filename)
                .size(size)
                .generatedName(generatedName)
                .path(rootPath)
                .build();

        Path uploadPath = Path.of(rootPath);

        fileDao.save(uploads);

        PDDocument document = PDDocument.load(part.getInputStream());
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
        ImageIOUtil.writeImage(bufferedImage, uploadPath.toString(), 300);


        return uploads;
    }

    public Uploads uploadFile(Part partFile) throws IOException {

        String contentType = partFile.getContentType();
        String originalFilename = partFile.getSubmittedFileName();
        long size = partFile.getSize();
        String filenameExtension = originalFilename.split("\\.")[1];
        String generatedName = System.currentTimeMillis() + "." + filenameExtension;
        String rootPath = "/home/shoxrux/IdeaProjects/apps/uploads/" + originalFilename;
        Uploads uploads = Uploads
                .builder()
                .contentType(contentType)
                .originalName(originalFilename)
                .size(size)
                .generatedName(generatedName)
                .path(rootPath)
                .build();
        Path uploadPath = Path.of(rootPath);
        fileDao.save(uploads);
        Files.copy(partFile.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
        return uploads;

    }
}
