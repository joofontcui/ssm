package com.joofont.cxf;

import javax.jws.WebService;

/**
 * @author cui jun on 2018/11/8.
 * @version 1.0
 */
@WebService
public interface HelloJava {

    String getBook(Integer id);

}
