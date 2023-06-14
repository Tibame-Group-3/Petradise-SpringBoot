package tw.idv.petradisespringboot;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import tw.idv.petradisespringboot.pet.repo.PetPicRepository;
import tw.idv.petradisespringboot.pet.vo.PetPic;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class DatabaseLoader {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

    private final ResourceLoader loader;

    DatabaseLoader(ResourceLoader loader) {
        this.loader = loader;
    }

    @Bean
    CommandLineRunner initDatabasePetPic(PetPicRepository petPicRepository) {
        return args -> {
            savePetPic(1, 1, petPicRepository);
            savePetPic(1, 2, petPicRepository);
            savePetPic(1, 3, petPicRepository);
            savePetPic(2, 1, petPicRepository);
            savePetPic(2, 2, petPicRepository);
            savePetPic(3, 1, petPicRepository);
            savePetPic(4, 1, petPicRepository);
            savePetPic(7, 1, petPicRepository);
        };
    }

    private void savePetPic(Integer petId, Integer index, PetPicRepository repo) throws Exception {
        String imagePath = "images/pet_pic_" + petId + "_" + index + ".jpeg";
        byte[] image = loadImageBytes(imagePath);
        var pic = new PetPic();
        pic.setPetId(petId);
        pic.setPic(image);
        logger.info("Preloading" + repo.save(pic));
    }

    private byte[] loadImageBytes(String imagePath) throws IOException {
        Resource resource = loader.getResource("classpath:" + imagePath);
        try (InputStream inputStream = resource.getInputStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
