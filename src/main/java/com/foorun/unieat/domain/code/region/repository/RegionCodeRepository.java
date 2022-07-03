package com.foorun.unieat.domain.code.region.repository;

import com.foorun.unieat.domain.code.region.jpo.RegionCodeJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionCodeRepository extends JpaRepository<RegionCodeJpo, Long> {
}
