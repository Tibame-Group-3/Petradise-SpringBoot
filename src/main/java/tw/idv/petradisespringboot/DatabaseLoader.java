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
import tw.idv.petradisespringboot.pet.repo.PetRepository;
import tw.idv.petradisespringboot.pet.vo.Pet;
import tw.idv.petradisespringboot.pet.vo.PetPic;
import tw.idv.petradisespringboot.pet.vo.enums.PetSize;
import tw.idv.petradisespringboot.pet.vo.enums.PetType;

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
    CommandLineRunner initDatabasePetPic(PetRepository petRepository){
        if (petRepository.countPetPics() != 0) {
           return args -> { logger.info("寵物圖片已有資料，跳過初始化");};
        }
        return args -> {
            savePetPic(1, 1, petRepository);
            savePetPic(1, 2, petRepository);
            savePetPic(1, 3, petRepository);
            savePetPic(2, 1, petRepository);
            savePetPic(2, 2, petRepository);
            savePetPic(3, 1, petRepository);
            savePetPic(4, 1, petRepository);
            savePetPic(7, 1, petRepository);
        };
    }

    @Bean
    CommandLineRunner initRowlet(PetRepository petRepository, PetPicRepository petPicRepository) {
        return args -> {
            createPetRowlet(petRepository, petPicRepository);
        };
    }
    
   private void createPetRowlet(PetRepository petRepository, PetPicRepository petPicRepository) throws Exception {
        var petName = "木木梟";
        var rowletExist = petRepository.existsByName(petName);
        if (!rowletExist) {
            var pet = new Pet();
            pet.setMemberId(1);
            pet.setName(petName);
            pet.setSize(PetSize.LARGE);
            pet.setType(PetType.BIRD);
            pet = petRepository.save(pet);
            var image = loadImageBytes("pet_pics/pet_pic_rowlet.png");
            var petPic = new PetPic();
            petPic.setPet(pet);
            petPic.setPic(image);
            petPicRepository.save(petPic);
            logger.info(petName + "初始化完成");
        } else {
            logger.info("已有" + petName + "，略過初始化");
        }
   }

    private void savePetPic(Integer petId, Integer index, PetRepository repo) throws Exception {
        String imagePath = "pet_pics/pet_pic_" + petId + "_" + index + ".png";
        byte[] image = loadImageBytes(imagePath);

        // 尋找資料庫有無此Pet ID之寵物
        var pet = repo.findById(petId).orElseThrow(() -> new Exception("Pet not found"));
        pet.setId(petId);

        // 把此寵物已擁有的圖片先拿出來
        var pics = pet.getPetPics();

        // 加入一個新圖片
        var pic = new PetPic();
        pic.setPet(pet);
        pic.setPic(image);
        pics.add(pic);

        // 在把List塞回寵物
        pet.setPetPics(pics);

        logger.info("Preloading pet pic, pet id: " + repo.save(pet).getId());
    }

    private byte[] loadImageBytes(String imagePath) throws IOException {
        Resource resource = loader.getResource("classpath:images/" + imagePath);
        try (InputStream inputStream = resource.getInputStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
