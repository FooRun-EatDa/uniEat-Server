package com.foorun.unieat.domain.event;

import com.foorun.unieat.domain.event.jpo.EventJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRespository extends JpaRepository<EventJpo,Long> {

}
