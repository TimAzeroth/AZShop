package com.azeroth.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SalesChkDomain {

    // 카드 확인 용
    boolean chkProcess;

    // 카드확인 메세지
    String ereMag;

    public boolean getChkProcess(){
        return this.chkProcess;
    }

}
