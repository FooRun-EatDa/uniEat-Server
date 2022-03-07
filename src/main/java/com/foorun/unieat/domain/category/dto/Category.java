package com.foorun.unieat.domain.category.dto;


import com.foorun.unieat.domain.category.jpo.CategoryJpo;
import com.foorun.unieat.domain.restaurant.dto.Restaurant;
import com.foorun.unieat.util.IdentifyGenerator;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Category {


    private Long id = IdentifyGenerator.number();
    private String categoryName;
    private Restaurant restaurant;



    public static Category createEmpty(){return new Category();}

    public static Category of(CategoryJpo categoryJpo){
        Category category = createEmpty();
        BeanUtils.copyProperties(categoryJpo,category);

        return category;
    }

    public CategoryJpo asJpo(){
        CategoryJpo categoryJpo = new CategoryJpo();
        BeanUtils.copyProperties(this,categoryJpo);
        return categoryJpo;
    }

}
