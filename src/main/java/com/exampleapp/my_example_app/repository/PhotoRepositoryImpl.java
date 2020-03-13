package com.exampleapp.my_example_app.repository;

import com.exampleapp.my_example_app.entity.PhotoEntity;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepository;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepositoryJPA;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(rollbackFor = Exception.class, readOnly = false)
public class PhotoRepositoryImpl implements PhotoRepository {

    private final String ROOT_DIR = "src/images";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DataSource dataSource;

    @Autowired
    PhotoRepositoryJPA photoRepositoryJPA;

    @Override
    public List<PhotoEntity> init(List<PhotoEntity> list) {
        for (PhotoEntity entity : list) {
            try {
                BufferedImage imageBuf = ImageIO.read(new URL(entity.getUrl()));
                String line = entity.getUrl();
                int index = line.lastIndexOf("/");
                String str = line.substring(index + 1);
                String[] array = str.split("\\.");

                File fileRootDir = new File(ROOT_DIR);

                File fileDir = new File(fileRootDir, String.valueOf(entity.getAlbumId()));

                if (array[1].equals("jfif")) array[1] = "jpg";
                File file = new File(fileDir, array[0] + "." + array[1]);

                if (!fileRootDir.isDirectory()) {
                    fileRootDir.mkdir();
                }

                if (!fileDir.isDirectory()) {
                    fileDir.mkdir();
                }

                if (!file.exists()) {
                    file.createNewFile();
                    ImageIO.write(imageBuf, array[1], file);
                }

                entity.setLocalPath(file.getAbsolutePath());
                double bytes = file.length();
                entity.setFileSize((int) bytes);
                entityManager.persist(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return photoRepositoryJPA.findAll();
    }


    @Override
    public byte[] getPhoto(Path path) throws FileNotFoundException {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new FileNotFoundException("Picture was not found!");
        }
    }
}
