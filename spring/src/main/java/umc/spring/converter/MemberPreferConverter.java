package umc.spring.converter;

import umc.spring.domain.Food;
import umc.spring.domain.mapping.Preference_food;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {
    public static List<Preference_food> toMemberPreferList(List<Food> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        Preference_food.builder()
                                .food(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
