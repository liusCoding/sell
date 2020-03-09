package com.ls.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @className: SellerInfo
 * @description:
 * @author: liusCoding
 * @create: 2020-03-06 09:41
 */

@Entity
@Data
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

}
