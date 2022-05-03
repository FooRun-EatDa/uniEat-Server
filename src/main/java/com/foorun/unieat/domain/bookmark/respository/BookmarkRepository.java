package com.foorun.unieat.domain.bookmark.respository;


import com.foorun.unieat.domain.bookmark.jpo.BookmarkJpo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository  extends JpaRepository<BookmarkJpo,Long> {
}
