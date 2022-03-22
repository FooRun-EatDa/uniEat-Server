package com.foorun.unieat.domain.file.repository;

import com.foorun.unieat.domain.file.jpo.FileJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileJpo, String> {
}
