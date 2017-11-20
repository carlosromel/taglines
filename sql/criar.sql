create table tagline (
    tagline_id  serial       not null,
    tag         varchar(512) not null,
    usage_count integer      not null default 0,

    primary key (tagline_id)
);
