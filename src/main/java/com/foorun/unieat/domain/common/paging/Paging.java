package com.foorun.unieat.domain.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@ToString(callSuper = true)
public class Paging extends PageRequest {
    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param page zero-based page index, must not be negative.
     * @param offset the size of the page to be returned, must be greater than 0.
     * @param sort must not be {@literal null}, use {@link Sort#unsorted()} instead.
     */
    protected Paging(int page, int offset, Sort sort) {
        super(page, offset, sort);
    }

    public Paging(int page, int offset) {
        super(page, offset, Sort.unsorted());
    }

    public static Paging fromZeroOf(int offset) {
        return new Paging(0, offset);
    }
}
