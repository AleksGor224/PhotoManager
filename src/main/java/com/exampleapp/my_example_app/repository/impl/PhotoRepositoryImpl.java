package com.exampleapp.my_example_app.repository.impl;

import com.exampleapp.my_example_app.dtos.PhotoRequestDTO;
import com.exampleapp.my_example_app.entities.PhotoEntity;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {

    private final String ROOT_DIR = "src/images";
    private final String IMAGE_MINI_DIR = "thumbnails";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DataSource source;


    @Override
    @Transactional(readOnly = false)
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

                File file = new File(fileDir, array[0]);

                if (!fileRootDir.isDirectory()) {
                    fileRootDir.mkdir();
                }

                if (!fileDir.isDirectory()) {
                    fileDir.mkdir();
                }

                if (!file.exists()) {
                    file.createNewFile();
                }


                entity.setLocalPath(file.getAbsolutePath());
                double bytes = file.length();
                double kilobytes = (bytes / 1024);
                entity.setFileSize((int) kilobytes);
                entityManager.persist(entity);
            } catch (IOException e) {
                System.out.println("Image was not saved. Caused by: " + e.getCause() + "\n" + e.getMessage());
            }
        }
        Session session = (Session) entityManager.getDelegate();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PhotoEntity> cq = cb.createQuery(PhotoEntity.class);
        Root<PhotoEntity> rootEntry = cq.from(PhotoEntity.class);
        CriteriaQuery<PhotoEntity> all = cq.select(rootEntry);

        TypedQuery<PhotoEntity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<PhotoEntity> getAllPhotos() {
        return null;
    }

    @Override
    public List<PhotoEntity> getAllPhotosFromAlbum(int album) {
        return null;
    }

    @Override
    public ResponseEntity<PhotoEntity> getPhoto(Path path) {
        return null;
    }

}
