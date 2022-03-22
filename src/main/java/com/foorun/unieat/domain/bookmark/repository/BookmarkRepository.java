package com.foorun.unieat.domain.bookmark.repository;

import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository  extends JpaRepository<BookmarkJpo,Long> {


    @Query("SELECT b from BookmarkJpo b where b.member.id = :id ")
    Optional<List<BookmarkJpo>> findBookmarkListByMemberId (Long id);
}
