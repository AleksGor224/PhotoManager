package com.exampleapp.my_example_app.repository;

import com.exampleapp.my_example_app.entity.PhotoEntity;
import com.exampleapp.my_example_app.repository.interfaces.PhotoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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


    /**
     * Method added new PhotoEntities to our data base and save new pictures from object link to pic in local folder /src/pictures
     * in folder with name of album number.
     * <p>
     * Response is List of All PhotoEntities
     * Url photos/init
     *
     * @param list List of PhoneEntities
     * @author AleksGor
     */
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

        return findAll();
    }

    /**
     * Method get PhotoEntities by album number
     * <p>
     * Response is byteArray of current picture
     * Url photos/init
     *
     * @param path local Path of pictures. We can look him from request getAllPhotos
     * @author AleksGor
     */

    @Override
    public byte[] getPhoto(Path path) throws FileNotFoundException {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new FileNotFoundException("Picture was not found!");
        }
    }

    /**
     * Method get PhotoEntities by album number via SpringDataJPA
     * <p>
     * Response is byteArray of current picture
     * Url photos/init
     *
     * @param albumId Integer
     * @author AleksGor
     */
    @Override
    public List<PhotoEntity> findAllByAlbumId(int albumId) {
        PhotoEntity photoEntity = entityManager.find(PhotoEntity.class, albumId);
        List<PhotoEntity> list = findAll();
        return list.stream().filter((entity) -> albumId == entity.getAlbumId())
                .collect(Collectors.toList());
    }

    /**
     * Method for getting all PhotoEntities from our data base via SpringDataJPA
     * <p>
     * <p>
     * <p>
     * Response is list of All PhotoEntities
     * Url photos/all
     *
     * @author AleksGor
     */
    @Override
    public List<PhotoEntity> findAll() {
        return entityManager.createQuery("Select t from " + PhotoEntity.class.getSimpleName() + " t").getResultList();
    }
}
