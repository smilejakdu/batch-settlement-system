create table claim_item
(
    claim_item_id    bigint                             not null
        primary key,
    claim_receipt_id bigint                             not null,
    created_at       datetime default CURRENT_TIMESTAMP not null,
    updated_at       datetime default CURRENT_TIMESTAMP not null,
    deleted_at       datetime                           null,
    order_item_id    bigint                             not null,
    claim_count      int                                null
);

create table claim_receipt
(
    claim_receipt_id bigint                             not null
        primary key,
    order_id         bigint                             not null,
    created_at       datetime default CURRENT_TIMESTAMP not null,
    updated_at       datetime default CURRENT_TIMESTAMP not null,
    deleted_at       datetime                           null,
    completed_at     datetime                           null,
    request_type     varchar(50)                        not null,
    claim_status     int                                not null,
    extra_fee_payer  int                                not null,
    claim_reason     int                                not null
);

create table order_item
(
    order_item_id          bigint auto_increment comment '주문 상품 no'
        primary key,
    order_id               bigint                             not null comment '주문원장 ',
    order_item_snapshot_id bigint                             not null comment '주문 상품 스냅샷 no ',
    order_count            int      default 1                 not null comment '주문수량 ',
    purchase_confirmed_at  datetime                           null comment '구매확정시간 ',
    item_delivery_status   int                                null comment '아이템 배송 상태 ',
    created_at             datetime default CURRENT_TIMESTAMP not null,
    deleted_at             datetime default CURRENT_TIMESTAMP null comment '삭제시간 ',
    updated_at             datetime default CURRENT_TIMESTAMP not null comment '수정날짜',
    shipped_complete_at    datetime                           null comment '배송완료 시간 '
)
    comment '주문한 상품의 목록 원장 ';


create table order_item_snapshot
(
    order_item_snapshot_id  bigint auto_increment comment '주문 상품 스냅샷 테이블 Pk'
        primary key,
    product_id              bigint                                   not null comment '상품번호',
    seller_id               bigint                                   not null comment '셀러 no ',
    sell_price              decimal(14, 5) default 0.00000           not null comment '판매가 ',
    supply_price            decimal(14, 5) default 0.00000           null comment '공급가 ',
    promotion_amount        decimal(14, 5) default 0.00000           null comment '할인 가격 ',
    default_delivery_amount decimal(14, 5) default 3000.00000        null comment '3000',
    tax_rate                int            default 3                 not null comment '세금',
    tax_type                varchar(4)     default 'TAX'             not null comment '세금유형 ',
    item_category           int                                      not null comment '상품 유형 ',
    created_at              datetime       default CURRENT_TIMESTAMP not null,
    updated_at              datetime       default CURRENT_TIMESTAMP not null,
    deleted_at              datetime       default CURRENT_TIMESTAMP null,
    mileage_usage_amount    decimal(14, 5)                           null
)
    comment '주문 상품 스냅샷 테이블 ';

create table order_origin
(
    order_id               bigint auto_increment comment '주문번호 (pk)'
        primary key,
    created_at             datetime       default CURRENT_TIMESTAMP not null comment '주문 생성일 ',
    updated_at             datetime       default CURRENT_TIMESTAMP not null,
    deleted_at             datetime                                 null comment '삭제된 시간 ',
    paid_confirmed_at      datetime       default CURRENT_TIMESTAMP null comment '결제 완료 날짜 ',
    paid_pg_amount         decimal(14, 5) default 0.00000           not null comment ' 결제 금액 ',
    used_mileage_amount    decimal(14, 5) default 0.00000           not null comment '마일리지 사용 금액 ',
    coupon_discount_amount decimal(14, 5) default 0.00000           not null
)
    comment '주문 테이블 ';

create table product
(
    product_id   bigint auto_increment comment '상품 식별키 '
        primary key,
    created_at   datetime       default CURRENT_TIMESTAMP not null comment '생성시간',
    updated_at   datetime       default CURRENT_TIMESTAMP not null comment '수정시간',
    deleted_at   datetime                                 null comment '삭제시간',
    product_name varchar(100)                             not null,
    seller_id    bigint                                   not null comment '셀러pk',
    category     int                                      not null comment '상품 카테고리',
    tax_type     char(4)        default 'TAX'             not null comment '세금유형',
    sell_price   decimal(14, 5) default 0.00000           not null comment '판매가 ',
    supply_price decimal(14, 5) default 0.00000           not null comment '공급가 ',
    is_active    tinyint(1)     default 1                 null comment '상품 활성화 여부 '
)
    comment '상품정보 테이블 ';

create table seller
(
    seller_id               bigint auto_increment comment '셀러를 구별하는 PK '
        primary key,
    seller_name             varchar(50)                          not null comment '셀러명',
    business_id             int                                  not null comment '셀러의 사업자번호',
    sell_type               char       default 'C'               not null comment '판매유형 ( P : 매입 , C : 위탁 )  ',
    created_at              datetime   default CURRENT_TIMESTAMP not null comment '생성시간',
    updated_at              datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '수정시간',
    deleted_at              datetime                             null comment '삭제시간',
    bank_type               varchar(10)                          null comment '은행타입',
    account_id              int                                  null comment '계좌번호',
    account_owner_name      varchar(50)                          null comment '계좌주 ',
    is_active               tinyint(1) default 1                 not null comment '셀러 활성화 여부 ',
    default_delivery_amount int        default 3000              not null comment '기본 배송 금액 ',
    commission              int        default 5                 not null comment '수수료'
)
    comment '셀러 정보 관리 테이블 ';

create table Settlement_total
(
    settlement_total_id       bigint auto_increment
        primary key,
    settlement_date           date                                     not null,
    seller_id                 bigint                                   not null,
    seller_name               varchar(255)                             not null,
    seller_business_number    varchar(255)                             null,
    tax_type                  varchar(255)   default 'TAX'             null,
    sell_type                 varchar(255)   default 'CONSIGNMENT'     null,
    pg_sales_amount           decimal(14, 5) default 0.00000           not null,
    coupon_discount_amount    decimal(14, 5) default 0.00000           not null,
    mileage_usage_amount      decimal(14, 5) default 0.00000           not null,
    shipping_fee_amount       decimal(14, 5) default 0.00000           not null,
    claim_shipping_fee_amount decimal(14, 5) default 0.00000           not null,
    commission_amount         decimal(14, 5) default 0.00000           not null,
    created_at                datetime       default CURRENT_TIMESTAMP null,
    updated_at                datetime       default CURRENT_TIMESTAMP null,
    deleted_at                datetime                                 null
);

create index idx_seller_id
    on Settlement_total (seller_id);

create index idx_settlement_date
    on Settlement_total (settlement_date);

create table BATCH_JOB_EXECUTION_SEQ
(
    ID         bigint not null,
    UNIQUE_KEY char   not null,
    constraint UNIQUE_KEY_UN
        unique (UNIQUE_KEY)
);

create table BATCH_JOB_INSTANCE
(
    JOB_INSTANCE_ID bigint       not null
        primary key,
    VERSION         bigint       null,
    JOB_NAME        varchar(100) not null,
    JOB_KEY         varchar(32)  not null,
    constraint JOB_INST_UN
        unique (JOB_NAME, JOB_KEY)
);

create table BATCH_JOB_EXECUTION
(
    JOB_EXECUTION_ID bigint        not null
        primary key,
    VERSION          bigint        null,
    JOB_INSTANCE_ID  bigint        not null,
    CREATE_TIME      datetime(6)   not null,
    START_TIME       datetime(6)   null,
    END_TIME         datetime(6)   null,
    STATUS           varchar(10)   null,
    EXIT_CODE        varchar(2500) null,
    EXIT_MESSAGE     varchar(2500) null,
    LAST_UPDATED     datetime(6)   null,
    constraint JOB_INST_EXEC_FK
        foreign key (JOB_INSTANCE_ID) references BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
);

create table BATCH_JOB_EXECUTION_CONTEXT
(
    JOB_EXECUTION_ID   bigint        not null
        primary key,
    SHORT_CONTEXT      varchar(2500) not null,
    SERIALIZED_CONTEXT text          null,
    constraint JOB_EXEC_CTX_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

create table BATCH_JOB_EXECUTION_PARAMS
(
    JOB_EXECUTION_ID bigint        not null,
    PARAMETER_NAME   varchar(100)  not null,
    PARAMETER_TYPE   varchar(100)  not null,
    PARAMETER_VALUE  varchar(2500) null,
    IDENTIFYING      char          not null,
    constraint JOB_EXEC_PARAMS_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

create table BATCH_JOB_SEQ
(
    ID         bigint not null,
    UNIQUE_KEY char   not null,
    constraint UNIQUE_KEY_UN
        unique (UNIQUE_KEY)
);

create table BATCH_STEP_EXECUTION
(
    STEP_EXECUTION_ID  bigint        not null
        primary key,
    VERSION            bigint        not null,
    STEP_NAME          varchar(100)  not null,
    JOB_EXECUTION_ID   bigint        not null,
    CREATE_TIME        datetime(6)   not null,
    START_TIME         datetime(6)   null,
    END_TIME           datetime(6)   null,
    STATUS             varchar(10)   null,
    COMMIT_COUNT       bigint        null,
    READ_COUNT         bigint        null,
    FILTER_COUNT       bigint        null,
    WRITE_COUNT        bigint        null,
    READ_SKIP_COUNT    bigint        null,
    WRITE_SKIP_COUNT   bigint        null,
    PROCESS_SKIP_COUNT bigint        null,
    ROLLBACK_COUNT     bigint        null,
    EXIT_CODE          varchar(2500) null,
    EXIT_MESSAGE       varchar(2500) null,
    LAST_UPDATED       datetime(6)   null,
    constraint JOB_EXEC_STEP_FK
        foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
);

create table BATCH_STEP_EXECUTION_CONTEXT
(
    STEP_EXECUTION_ID  bigint        not null
        primary key,
    SHORT_CONTEXT      varchar(2500) not null,
    SERIALIZED_CONTEXT text          null,
    constraint STEP_EXEC_CTX_FK
        foreign key (STEP_EXECUTION_ID) references BATCH_STEP_EXECUTION (STEP_EXECUTION_ID)
);

create table BATCH_STEP_EXECUTION_SEQ
(
    ID         bigint not null,
    UNIQUE_KEY char   not null,
    constraint UNIQUE_KEY_UN
        unique (UNIQUE_KEY)
);
