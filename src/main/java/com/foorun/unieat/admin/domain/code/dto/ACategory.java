package com.foorun.unieat.admin.domain.code.dto;

import com.foorun.unieat.domain.code.category.jpo.CategoryCodeJpo;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ACategory {
    private long id;
    private String name;

    public static ACategory of(CategoryCodeJpo categoryCodeJpo) {
        ACategory category = new ACategory();
        category.setId(categoryCodeJpo.getId());
        category.setName(categoryCodeJpo.getName());
        return category;
    }

    public CategoryCodeJpo asJpo() {
        CategoryCodeJpo categoryCodeJpo = new CategoryCodeJpo();
        categoryCodeJpo.setId(id);
        categoryCodeJpo.setName(name);
        return categoryCodeJpo;
    }
}
