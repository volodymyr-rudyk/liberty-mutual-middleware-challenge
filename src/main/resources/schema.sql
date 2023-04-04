create table poker_sessions
(
    id         varchar(255) primary key,
    title      varchar(255) not null,
    desk_type  varchar(255) not null,
    created_at timestamp default CURRENT_TIMESTAMP()
);

create table members
(
    id         serial primary key,
    nickname   varchar(255) not null,
    created_at timestamp default CURRENT_TIMESTAMP()
);


create table poker_sessions_members
(
    poker_session_id varchar(255) not null,
    member_id        int          not null,
    created_at       timestamp default CURRENT_TIMESTAMP(),

    foreign key (poker_session_id) references poker_sessions (id),
    foreign key (member_id) references members (id)
);

create table stories
(
    id               serial primary key,
    title            varchar(255) not null,
    project          varchar(255) not null,
    description      varchar(255) not null,
    vote_status      varchar(255) not null check ( vote_status in ('PENDING', 'VOTING', 'VOTED') ),
    poker_session_id varchar(255) not null,
    created_at       timestamp default CURRENT_TIMESTAMP(),

    foreign key (poker_session_id) references poker_sessions (id)
);

create table votes
(
    story_id   int not null,
    member_id  int not null,
    vote_value int not null,

    primary key (story_id, member_id),
    foreign key (story_id) references stories (id),
    foreign key (member_id) references members (id)
);
