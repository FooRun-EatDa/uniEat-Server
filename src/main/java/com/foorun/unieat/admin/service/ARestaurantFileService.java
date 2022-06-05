package com.foorun.unieat.admin.service;

import com.foorun.unieat.admin.repository.ARestaurantFileRepository;
import com.foorun.unieat.admin.repository.ARestaurantRepository;
import com.foorun.unieat.domain.file.dto.FileDetail;
import com.foorun.unieat.domain.file.repository.FileRepository;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantFileIdJpo;
import com.foorun.unieat.domain.restaurant.jpo.RestaurantFileJpo;
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
public class ARestaurantFileService {
    private final ARestaurantRepository restaurantRepository;
    private final ARestaurantFileRepository restaurantFileRepository;
    private final FileRepository fileRepository;

    @Transactional(readOnly = true)
    public List<FileDetail> fetch(long restaurantId) {
        RestaurantJpo restaurantJpo = restaurantRepository.findById(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        return map(restaurantJpo.getFiles(),
                Comparator.comparingInt(RestaurantFileJpo::getSequence),
                FileDetail::of);
    }

    @Transactional
    public long save(long restaurantId, List<FileDetail> fileDetails) {
        RestaurantJpo restaurantJpo = restaurantRepository.findById(restaurantId)
                .orElseThrow(UniEatNotFoundException::new);
        restaurantJpo.setUpdatedAt(LocalDateTime.now());
        fileDetails.forEach(fileDetail -> save(restaurantJpo, fileDetail));
        return restaurantId;
    }

    private void save(RestaurantJpo restaurantJpo, FileDetail fileDetail) {
        if (fileDetail.isNewly()) {
            RestaurantFileJpo restaurantFileJpo = RestaurantFileJpo.of(restaurantJpo,
                    fileDetail.asJpo(), fileDetail.isThumbnail(), fileDetail.getSequence());
            fileRepository.save(restaurantFileJpo.getFile());
            restaurantFileRepository.save(restaurantFileJpo);
        } else if (fileDetail.isDelete()) {
            RestaurantFileJpo restaurantFileJpo = restaurantFileRepository
                    .findById(RestaurantFileIdJpo.of(restaurantJpo.getId(), fileDetail.getId()))
                    .orElseThrow(UniEatLogicalException::new);
            restaurantFileRepository.delete(restaurantFileJpo);
        } else {
            RestaurantFileJpo restaurantFileJpo = restaurantFileRepository
                    .findById(RestaurantFileIdJpo.of(restaurantJpo.getId(), fileDetail.getId()))
                    .orElseThrow(UniEatLogicalException::new);
            restaurantFileJpo.setSequence(fileDetail.getSequence());
            restaurantFileJpo.setThumbnail(fileDetail.isThumbnail());
            restaurantFileRepository.save(restaurantFileJpo);
        }
    }
}
