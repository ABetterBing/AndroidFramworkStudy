package net.betterbing.androidframworkstudy.net;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Copyright:   Copyright (c) 2015年 Beijing Yunshan Information Technology Co., Ltd. All rights reserved.<br>
 * Author:  ABB <br>
 * Date:    16/5/12 <br>
 * Desc:     <br>
 * Edit History: <br>
 */
public interface TestService {
    @POST("supplierapp/login")
    Call<BaseResult<User>> login(@Body User user);
}
