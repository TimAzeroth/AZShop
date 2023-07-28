package com.azeroth.project.service;

import com.azeroth.project.domain.QryResult;
import com.azeroth.project.domain.QryReviewList;
import com.azeroth.project.domain.ReviewDomain;

public interface ReviewService {

    QryReviewList list(Long product_id);

    QryResult save(Long user_id, Long product_id, String content);

    QryResult delete(Long id);


}
