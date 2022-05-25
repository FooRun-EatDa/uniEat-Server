package com.foorun.unieat.domain.event;

import com.foorun.unieat.domain.event.jpo.EventJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRespository extends JpaRepository<EventJpo,Long> {
}
