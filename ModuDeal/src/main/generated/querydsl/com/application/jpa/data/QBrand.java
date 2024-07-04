package com.application.jpa.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBrand is a Querydsl query type for Brand
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBrand extends EntityPathBase<Brand> {

    private static final long serialVersionUID = 1509477633L;

    public static final QBrand brand = new QBrand("brand");

    public final StringPath activeYn = createString("activeYn");

    public final NumberPath<Long> brandId = createNumber("brandId", Long.class);

    public final StringPath brandNm = createString("brandNm");

    public final DateTimePath<java.util.Date> enteredDt = createDateTime("enteredDt", java.util.Date.class);

    public QBrand(String variable) {
        super(Brand.class, forVariable(variable));
    }

    public QBrand(Path<? extends Brand> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBrand(PathMetadata metadata) {
        super(Brand.class, metadata);
    }

}

