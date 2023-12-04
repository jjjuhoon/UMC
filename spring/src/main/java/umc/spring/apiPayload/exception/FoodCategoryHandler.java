package umc.spring.apiPayload.exception;

import umc.spring.apiPayload.code.BaseErrorCode;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}