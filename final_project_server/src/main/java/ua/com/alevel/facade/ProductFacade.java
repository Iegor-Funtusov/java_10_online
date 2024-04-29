package ua.com.alevel.facade;

import ua.com.alevel.dto.request.ProductRequest;
import ua.com.alevel.dto.response.ProductResponse;

public interface ProductFacade extends CrudFacade<ProductRequest, ProductResponse> { }
