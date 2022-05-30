package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.repository.AFoodFileRepository;
import com.foorun.unieat.admin.repository.AFoodRepository;
import com.foorun.unieat.admin.repository.ARestaurantRepository;
import com.foorun.unieat.domain.file.dto.FileDetail;
import com.foorun.unieat.domain.file.repository.FileRepository;
import com.foorun.unieat.domain.food.dto.Food;
import com.foorun.unieat.domain.food.jpo.FoodFileIdJpo;
import com.foorun.unieat.domain.food.jpo.FoodFileJpo;
import com.foorun.unieat.domain.food.jpo.FoodJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantJpo;
import com.foorun.unieat.exception.UniEatLogicalException;
import com.foorun.unieat.exception.UniEatNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.foorun.unieat.util.StreamUtil.map;

@Service
@RequiredArgsConstructor
public class ARestaurantFoodService {
    private final ARestaurantRepository restaurantRepository;
    private final AFoodRepository foodRepository;
    private final FileRepository fileRepository;
    private final AFoodFileRepository foodFileRepository;

    @Transactional(readOnly = true)
    public List<Food> fetch(long restaurantId) {
        RestaurantJpo restaurantJpo = restaurantRepository.findById(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        return map(restaurantJpo.getFoods(), Food::of, Comparator.comparingInt(Food::getSequence));
    }

    @Transactional
    public long save(long restaurantId, List<Food> foods) {
        RestaurantJpo restaurantJpo = restaurantRepository.findById(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        restaurantJpo.setUpdatedAt(LocalDateTime.now());
        foods.forEach(food -> save(restaurantJpo, food));
        return restaurantId;
    }

    private void save(RestaurantJpo restaurantJpo, Food food) {
        if (food.isDelete()) {
            foodRepository.deleteById(food.getId());
            return;
        }
        FoodJpo foodJpo;
        if (food.isNewly()) {
            foodJpo = food.asJpo();
        } else {
            foodJpo = foodRepository.findById(food.getId())
                    .orElseThrow(UniEatLogicalException::new);
            foodJpo.setName(food.getName());
            foodJpo.setContent(food.getContent());
            foodJpo.setSequence(food.getSequence());
            foodJpo.setPrice(food.getPrice());
        }
        foodRepository.save(foodJpo);
        saveFiles(foodJpo, food.getFiles());
    }

    private void saveFiles(FoodJpo foodJpo, List<FileDetail> fileDetails) {
        fileDetails.forEach(fileDetail -> saveFile(foodJpo, fileDetail));
    }

    private void saveFile(FoodJpo foodJpo, FileDetail fileDetail) {
        if (fileDetail.isNewly()) {
            FoodFileJpo foodFileJpo = FoodFileJpo.of(foodJpo,
                    fileDetail.asJpo(), fileDetail.isThumbnail(), fileDetail.getSequence());
            fileRepository.save(foodFileJpo.getFile());
            foodFileRepository.save(foodFileJpo);
        } else if (fileDetail.isDelete()) {
            FoodFileJpo foodFileJpo = foodFileRepository
                    .findById(FoodFileIdJpo.of(foodJpo.getId(), fileDetail.getId()))
                    .orElseThrow(UniEatLogicalException::new);
            foodFileRepository.delete(foodFileJpo);
        }
    }
}
